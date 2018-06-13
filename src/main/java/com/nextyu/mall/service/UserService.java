package com.nextyu.mall.service;

import com.nextyu.mall.entity.User;

/**
 * created on 2017-10-27 17:19
 *
 * @author nextyu
 */
public interface UserService {
    User getByUsername(String username);
}
