package cn.chouyv.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品信息表
 * @author SurKaa
 * @TableName shop_products
 */
@TableName(value ="shop_products")
@Data
public class ShopProducts implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = -1565548047521035356L;

    /**
     * 商品主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品外键
     */
    private Long shopId;

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品详细描述
     */
    private String description;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 剩余货量
     */
    private Integer count;

    /**
     * 商品价格
     */
    private Integer price;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 逻辑删除 0-未删除 1-已删除
     */
    private Integer isDeleted;
}