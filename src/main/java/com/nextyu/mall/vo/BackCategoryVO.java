package com.nextyu.mall.vo;

import lombok.Data;

@Data
public class BackCategoryVO {

    private String imageUrl;

    /**
     * id
     */
    private Long id;

    /**
     * 父类目id
     */
    private Long parentId;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String image;

    /**
     * 简介
     */
    private String summary;

    /**
     * 等级
     */
    private Integer rank;

    /**
     * 状态，0：下架中，1：上架中
     */
    private Integer status;

}