package cn.chouyv.vo.shop;

import cn.chouyv.domain.ShopProducts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 商店和产品响应
 * Created with IntelliJ IDEA.
 *
 * @author 17986
 * @Author: wang
 * @Date: 2023/08/10/16:04
 * @Description:
 * @date 2023/08/10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopAndProductVO {

    private ShopVO shopVO;
    private List<ShopProducts> shopProducts;

}