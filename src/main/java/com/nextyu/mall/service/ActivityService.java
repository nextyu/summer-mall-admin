package com.nextyu.mall.service;

import com.github.pagehelper.PageInfo;
import com.nextyu.mall.query.ActivityQuery;
import com.nextyu.mall.vo.ActivityVO;

import java.util.List;

public interface ActivityService {
    Boolean save(ActivityVO activityVO);

    ActivityVO getById(Long id);

    Boolean update(ActivityVO activityVO);

    List<ActivityVO> listAll();

    List<ActivityVO> listPage(ActivityQuery query);

    PageInfo<ActivityVO> getPageInfo(ActivityQuery query);

    Boolean updateStatus(Long id, Integer status);
}