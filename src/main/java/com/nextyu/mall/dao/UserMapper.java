package com.nextyu.mall.dao;

import com.nextyu.mall.entity.User;
import com.nextyu.mall.query.UserQuery;
import com.nextyu.mall.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<UserVO> list(UserQuery query);
}