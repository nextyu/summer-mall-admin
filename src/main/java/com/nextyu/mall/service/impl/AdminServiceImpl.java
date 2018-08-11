package com.nextyu.mall.service.impl;

import com.nextyu.mall.entity.Admin;
import com.nextyu.mall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * created on 2017-10-27 17:19
 *
 * @author nextyu
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin getByUsername(String username) {
        return new Admin(1L, "admin", passwordEncoder.encode("123456"));
    }
}
