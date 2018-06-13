package com.nextyu.mall.service;

import com.github.pagehelper.PageInfo;
import com.nextyu.mall.query.BackCategoryQuery;
import com.nextyu.mall.vo.BackCategoryVO;

import java.util.List;

public interface BackCategoryService {
    Boolean save(BackCategoryVO backCategoryVO);

    BackCategoryVO getById(Long id);

    Boolean update(BackCategoryVO backCategoryVO);

    List<BackCategoryVO> listAll();

    List<BackCategoryVO> listPage(BackCategoryQuery query);

    PageInfo<BackCategoryVO> getPageInfo(BackCategoryQuery query);

    Boolean updateStatus(Long id, Integer status);
}