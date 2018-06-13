package com.nextyu.mall.security;

import com.nextyu.mall.service.UserService;
import org.apache.commons.lang3.StringUtils;
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
 * created on 2017-10-27 10:57
 *
 * @author nextyu
 */

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.nextyu.mall.entity.User existUser = userService.getByUsername(username);

        String encodePassword = passwordEncoder.encode(existUser.getPassword());

        existUser.setPassword(encodePassword);

        // 数据库查找用户信息
        if (Objects.isNull(existUser)) {
            throw new UsernameNotFoundException(username + " not found");
        }


        // 获取用户角色信息
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return User.withUsername(username).password(existUser.getPassword()).authorities(authorities).build();
    }
}
