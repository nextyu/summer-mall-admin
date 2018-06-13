package com.nextyu.mall.dao;

import com.nextyu.mall.entity.Product;
import com.nextyu.mall.query.ProductQuery;
import com.nextyu.mall.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<ProductVO> list(ProductQuery query);
}