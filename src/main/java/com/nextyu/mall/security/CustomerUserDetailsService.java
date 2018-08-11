package com.nextyu.mall.security;

import com.nextyu.mall.entity.Admin;
import com.nextyu.mall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author nextyu
 */

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 数据库查找商家信息
        Admin existAdmin = adminService.getByUsername(username);

        if (Objects.isNull(existAdmin)) {
            throw new UsernameNotFoundException(username + " not found");
        }


        // 获取商家角色信息
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return User.withUsername(username).password(existAdmin.getPassword()).authorities(authorities).build();
    }
}
