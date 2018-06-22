package com.nextyu.mall.query;

import lombok.Data;

@Data
public class OrderQuery {
    private Integer pageNum = 1;

    private Integer pageSize = 10;
}