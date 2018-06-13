package com.nextyu.mall.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ActivityVO {
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
     * 类型，1：秒杀
     */
    private Integer type;

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
     * 主图
     */
    private String mainImage;

    /**
     * 子图，多张
     */
    private String subImages;

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
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 开始时间
     */
    private String startTimeStr;

    /**
     * 结束时间
     */
    private String endTimeStr;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTimeDate;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTimeDate;

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



}