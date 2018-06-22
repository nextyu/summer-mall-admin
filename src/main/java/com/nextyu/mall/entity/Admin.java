package com.nextyu.mall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created on 2017-10-27 17:19
 *
 * @author nextyu
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {
    private Long id;
    private String username;
    private String password;
}
