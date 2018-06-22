package com.nextyu.mall.entity;

import lombok.Data;

@Data
public class Order {
    /**
     * 
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品数量
     */
    private Integer productQuantity;

    /**
     * 产品单价
     */
    private Long productPrice;

    /**
     * 总价
     */
    private Long totalPrice;

    /**
     * 订单状态，1：待付款，2：待发货，3：待收货，4：已完成，5.已取消
     */
    private Integer orderStatus;

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