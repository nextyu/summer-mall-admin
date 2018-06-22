package com.nextyu.mall.service;

import com.nextyu.mall.entity.Admin;

/**
 * created on 2017-10-27 17:19
 *
 * @author nextyu
 */
public interface UserService {
    Admin getByUsername(String username);
}
