package com.nextyu.mall.service;

import com.github.pagehelper.PageInfo;
import com.nextyu.mall.query.ProductQuery;
import com.nextyu.mall.vo.ProductVO;

import java.util.List;

public interface ProductService {
    Boolean save(ProductVO productVO);

    ProductVO getById(Long id);

    Boolean update(ProductVO productVO);

    List<ProductVO> listAll();

    List<ProductVO> listPage(ProductQuery query);

    PageInfo<ProductVO> getPageInfo(ProductQuery query);

    Boolean updateStatus(Long id, Integer status);
}