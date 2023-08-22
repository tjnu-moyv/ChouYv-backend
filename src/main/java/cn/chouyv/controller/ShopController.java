package cn.chouyv.controller;

import cn.chouyv.domain.Order;
import cn.chouyv.domain.OrderShopProductsItem;
import cn.chouyv.domain.Shop;
import cn.chouyv.dto.shop.ShopLoginRequest;
import cn.chouyv.dto.shop.ShopRegisterRequest;
import cn.chouyv.service.OrderService;
import cn.chouyv.service.OrderShopProductsItemService;
import cn.chouyv.service.ShopProductsService;
import cn.chouyv.service.ShopService;
import cn.chouyv.utils.Result;
import cn.chouyv.vo.AuthResponse;
import cn.chouyv.vo.BaseResponse;
import cn.chouyv.vo.pay.OrderInfoResponse;
import cn.chouyv.vo.shop.ShopAndProductResponse;
import cn.chouyv.vo.shop.ShopListResponse;
import cn.chouyv.vo.shop.ShopResponse;
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

    @PostMapping("/login")
    public BaseResponse<AuthResponse> login(
            @RequestBody ShopLoginRequest loginRequest
    ) {
        log.info("Login: {}", loginRequest);
        AuthResponse response = shopService.loginShop(loginRequest);
        return Result.success(response);
    }

    @PostMapping("/register")
    public BaseResponse<AuthResponse> register(
            @RequestBody ShopRegisterRequest registerRequest
    ) {
        log.info("Register: {}", registerRequest);
        AuthResponse response = shopService.registerShop(registerRequest);
        return Result.success(response);
    }

    public ShopController(ShopService shopService, ShopProductsService shopProductsService, OrderService orderService, OrderShopProductsItemService orderShopProductsItemService) {
        this.shopService = shopService;
        this.shopProductsService = shopProductsService;
        this.orderService = orderService;
        this.orderShopProductsItemService = orderShopProductsItemService;
    }


//    @GetMapping
//    public BaseResponse<ShopResponse> getShopInfoById(@RequestParam Integer id) {
//        log.info("收到请求 参数id={}", id);
//        Shop shopInfoById = shopService.getShopInfoByid(id);
//        ShopResponse shopResponse = ShopResponse.toShopResponse(shopInfoById);
//        return Result.success(200, shopResponse);
//    }

    @GetMapping
    public BaseResponse<ShopAndProductResponse> getShopAndProductResponse(@RequestParam long id) {
        Shop shopInfoById = shopService.getShopInfoById(id);
        ShopResponse shopResponse = ShopResponse.toShopResponse(shopInfoById);
        ShopAndProductResponse shopAndProductResponse = new ShopAndProductResponse(shopResponse, shopProductsService.getShopProductsById(id));
        return Result.success(shopAndProductResponse);
    }

    @PostMapping
    public BaseResponse<ShopListResponse> getAllShopsInfo(
    ) {

        ShopListResponse shopListResponse = shopService.getAllShopsInfo();
        return Result.success(shopListResponse);
    }


    @GetMapping("/order")
    public BaseResponse<OrderInfoResponse> order(
            @RequestParam long id,
            HttpServletRequest request
    ) {
        Order orderInfoById = orderService.getOderInfoById(id,request);
        List<OrderShopProductsItem> orderShopProductsItemInfoById = orderShopProductsItemService.getOrderShopProductsItem(id);
        OrderInfoResponse orderInfoResponse = new OrderInfoResponse(orderInfoById, orderShopProductsItemInfoById);
        return Result.success(orderInfoResponse);
    }




}
