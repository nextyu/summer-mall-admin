package com.nextyu.mall.service;

import com.github.pagehelper.PageInfo;
import com.nextyu.mall.query.CarouselQuery;
import com.nextyu.mall.vo.CarouselVO;

import java.util.List;

public interface CarouselService {
    Boolean save(CarouselVO carouselVO);

    CarouselVO getById(Long id);

    Boolean update(CarouselVO carouselVO);

    List<CarouselVO> listAll();

    List<CarouselVO> listPage(CarouselQuery query);

    PageInfo<CarouselVO> getPageInfo(CarouselQuery query);

    Boolean updateStatus(Long id, Integer status);
}