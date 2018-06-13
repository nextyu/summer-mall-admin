package com.nextyu.mall.dao;

import com.nextyu.mall.entity.Carousel;
import com.nextyu.mall.query.CarouselQuery;
import com.nextyu.mall.vo.CarouselVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarouselMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);

    List<CarouselVO> list(CarouselQuery query);
}