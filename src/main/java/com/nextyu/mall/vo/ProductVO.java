package com.nextyu.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductVO {

    private String detail;
    private List<String> images;
    private BigDecimal originalPriceYuan;
    private BigDecimal currentPriceYuan;

    /**
     * id
     */
    private Long id;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 类别id
     */
    private Long categoryId;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 简介
     */
    private String summary;

    /**
     * 地址
     */
    private String address;

    /**
     * 营业时间
     */
    private String hours;

    /**
     * 主图
     */
    private String mainImage;

    /**
     * 子图，多张
     */
    private String subImages;

    /**
     * 编号
     */
    private String number;

    /**
     * 原价
     */
    private Long originalPrice;

    /**
     * 现价
     */
    private Long currentPrice;

    /**
     * 查看量
     */
    private Long viewQuantity;

    /**
     * 库存
     */
    private Long stockQuantity;

    /**
     * 销量
     */
    private Long saleQuantity;

    /**
     * 状态，0：下架中，1：上架中
     */
    private Integer status;

    /**
     * 是否删除，0：未删除，1：已删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 版本号
     */
    private Long version;

}