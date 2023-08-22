package cn.chouyv.vo.shop;

import lombok.Data;

/**
 * 商铺信息返回体
 *
 * @Author: wang
 * @Date: 2023/08/11/10:56
 * @Description:
 */
@Data
public class ShopListInfoVO {
    private long id;
    private String nickname;
    private String address;
    private String phone;
}