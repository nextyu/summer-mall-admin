package com.nextyu.mall.dao;

import com.nextyu.mall.entity.BackCategory;
import com.nextyu.mall.query.BackCategoryQuery;
import com.nextyu.mall.vo.BackCategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BackCategory record);

    int insertSelective(BackCategory record);

    BackCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BackCategory record);

    int updateByPrimaryKey(BackCategory record);

    List<BackCategoryVO> list(BackCategoryQuery query);
}