package com.nextyu.mall.query;

import lombok.Data;

/**
 * created on 2017-06-05 14:48
 *
 * @author nextyu
 */
@Data
public class BaseQuery {
    private Integer pageNum = 0; // 页码
    private Integer pageSize = 10;// 每页显示数量


}
