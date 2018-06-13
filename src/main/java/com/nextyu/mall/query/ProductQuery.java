package com.nextyu.mall.query;

import lombok.Data;

@Data
public class ProductQuery extends BaseQuery {
    private Long categoryId;
    private String title;
}