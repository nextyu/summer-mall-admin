package com.nextyu.mall.entity;

import lombok.Data;

@Data
public class Carousel {
    /**
     * id
     */
    private Long id;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String image;

    /**
     * 链接
     */
    private String link;

    /**
     * 数据
     */
    private String data;

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