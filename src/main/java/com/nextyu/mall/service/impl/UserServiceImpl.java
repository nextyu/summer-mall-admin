package com.nextyu.mall.service.impl;

import com.nextyu.mall.entity.Admin;
import com.nextyu.mall.service.UserService;
import org.springframework.stereotype.Service;

/**
 * created on 2017-10-27 17:19
 *
 * @author nextyu
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public Admin getByUsername(String username) {
        return new Admin(1L, "root", "root");
    }
}
