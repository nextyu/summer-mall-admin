package com.nextyu.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextyu.mall.dao.CarouselMapper;
import com.nextyu.mall.entity.Carousel;
import com.nextyu.mall.query.CarouselQuery;
import com.nextyu.mall.service.CarouselService;
import com.nextyu.mall.service.UploadService;
import com.nextyu.mall.util.DateTimeUtil;
import com.nextyu.mall.vo.CarouselVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Autowired
    private UploadService uploadService;

    @Override
    public Boolean save(CarouselVO carouselVO) {
        Carousel carousel = new Carousel();
        BeanUtils.copyProperties(carouselVO, carousel);

        if (null != carousel.getId()) {
            carousel.setUpdateTime(DateTimeUtil.currentTimeMillis());
            return carouselMapper.updateByPrimaryKeySelective(carousel) > 0;
        }

        carousel.setCreateTime(DateTimeUtil.currentTimeMillis());

        int result = carouselMapper.insertSelective(carousel);
        return result > 0;
    }

    @Override
    public Boolean update(CarouselVO carouselVO) {
        Carousel carousel = new Carousel();
        BeanUtils.copyProperties(carouselVO, carousel);
        int result = carouselMapper.updateByPrimaryKeySelective(carousel);
        return result > 0;
    }

    @Override
    public CarouselVO getById(Long id) {
        Carousel carousel = carouselMapper.selectByPrimaryKey(id);
        if (null == carousel) {
            return null;
        }

        CarouselVO carouselVO = new CarouselVO();
        BeanUtils.copyProperties(carousel, carouselVO);

        carouselVO.setImageUrl(uploadService.getImgDomain() + carouselVO.getImage());

        return carouselVO;
    }

    @Override
    public List<CarouselVO> listPage(CarouselQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<CarouselVO> carouselVOS = null;
        return carouselVOS;
    }

    @Override
    public List<CarouselVO> listAll() {
        List<CarouselVO> carouselVOS = null;
        return carouselVOS;
    }

    @Override
    public PageInfo<CarouselVO> getPageInfo(CarouselQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<CarouselVO> carouselVOS = carouselMapper.list(query);
        for (CarouselVO carouselVO : carouselVOS) {
            carouselVO.setImageUrl(uploadService.getImgDomain() + carouselVO.getImage());
        }
        return new PageInfo<>(carouselVOS);
    }

    @Override
    public Boolean updateStatus(Long id, Integer status) {
        Carousel carousel = carouselMapper.selectByPrimaryKey(id);
        if (null == carousel) {
            return false;
        }

        Carousel updateCarouse = new Carousel();
        updateCarouse.setId(id);
        updateCarouse.setStatus(status);
        updateCarouse.setUpdateTime(DateTimeUtil.currentTimeMillis());

        return carouselMapper.updateByPrimaryKeySelective(updateCarouse) > 0;
    }
}