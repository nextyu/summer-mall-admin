package com.nextyu.mall.service;

import com.github.pagehelper.PageInfo;
import com.nextyu.mall.query.UserQuery;
import com.nextyu.mall.vo.UserVO;

import java.util.List;

public interface UserService {
    Boolean save(UserVO userVO);

    UserVO getById(Long id);

    Boolean update(UserVO userVO);

    List<UserVO> listAll();

    List<UserVO> listPage(UserQuery query);

    PageInfo<UserVO> getPageInfo(UserQuery query);
}