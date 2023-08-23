package cn.chouyv.controller;

import cn.chouyv.domain.Shop;
import cn.chouyv.domain.ShopProducts;
import cn.chouyv.dto.shop.ShopLoginDTO;
import cn.chouyv.dto.shop.ShopRegisterDTO;
import cn.chouyv.service.OrderService;
import cn.chouyv.service.OrderShopProductsItemService;
import cn.chouyv.service.ShopProductsService;
import cn.chouyv.service.ShopService;
import cn.chouyv.utils.Result;
import cn.chouyv.vo.AuthVO;
import cn.chouyv.vo.BaseVO;
import cn.chouyv.vo.shop.ShopAndProductVO;
import cn.chouyv.vo.shop.ShopListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wang
 * @Date: 2023/08/09/16:16
 * @Description:
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;
    private final ShopProductsService shopProductsService;
    private final OrderService orderService;
    private final OrderShopProductsItemService orderShopProductsItemService;

    public ShopController(ShopService shopService, ShopProductsService shopProductsService, OrderService orderService, OrderShopProductsItemService orderShopProductsItemService) {
        this.shopService = shopService;
        this.shopProductsService = shopProductsService;
        this.orderService = orderService;
        this.orderShopProductsItemService = orderShopProductsItemService;
    }

    @PostMapping("/login")
    public BaseVO<AuthVO> login(
            @RequestBody ShopLoginDTO loginRequest
    ) {
        log.info("Login: {}", loginRequest);
        AuthVO response = shopService.loginShop(loginRequest);
        return Result.success(response);
    }

    @PostMapping("/register")
    public BaseVO<AuthVO> register(
            @RequestBody ShopRegisterDTO registerRequest
    ) {
        log.info("Register: {}", registerRequest);
        AuthVO response = shopService.registerShop(registerRequest);
        return Result.success(response);
    }

    @GetMapping
    public BaseVO<ShopAndProductVO> getShopAndProductResponse(@RequestParam long shopId) {
        Shop shopInfoById = shopService.getById(shopId);
        List<ShopProducts> productsList = shopProductsService.getShopProductsById(shopId);
        return Result.success(new ShopAndProductVO(
                ShopAndProductVO.oneShopInfo(shopInfoById),
                ShopAndProductVO.shopProductsInfoList(productsList)
        ));
    }

    @PostMapping
    public BaseVO<ShopListVO> getAllShopsInfo(
            HttpServletRequest request
    ) {
        return Result.success(shopService.getAllShopsInfo(request));
    }

}
