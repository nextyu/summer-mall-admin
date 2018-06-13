package com.nextyu.mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created on 2017-08-01 15:17
 *
 * @author nextyu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpiderVO {
    private Long id;
    private String name;
    /**
     * 状态，0：关闭中，1：开启中
     */
    private Integer status;
}
