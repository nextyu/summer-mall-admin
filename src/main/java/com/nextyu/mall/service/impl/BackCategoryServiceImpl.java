package com.nextyu.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextyu.mall.dao.BackCategoryMapper;
import com.nextyu.mall.entity.BackCategory;
import com.nextyu.mall.entity.Product;
import com.nextyu.mall.query.BackCategoryQuery;
import com.nextyu.mall.service.BackCategoryService;
import com.nextyu.mall.service.UploadService;
import com.nextyu.mall.util.DateTimeUtil;
import com.nextyu.mall.vo.BackCategoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackCategoryServiceImpl implements BackCategoryService {
    @Autowired
    private BackCategoryMapper backCategoryMapper;

    @Autowired
    private UploadService uploadService;

    @Override
    public Boolean save(BackCategoryVO backCategoryVO) {
        BackCategory backCategory = new BackCategory();
        BeanUtils.copyProperties(backCategoryVO, backCategory);

        if (null != backCategory.getId()) {
            backCategory.setUpdateTime(DateTimeUtil.currentTimeMillis());
            return backCategoryMapper.updateByPrimaryKeySelective(backCategory) > 0;
        }

        backCategory.setCreateTime(DateTimeUtil.currentTimeMillis());
        int result = backCategoryMapper.insertSelective(backCategory);
        return result > 0;
    }

    @Override
    public Boolean update(BackCategoryVO backCategoryVO) {
        BackCategory backCategory = new BackCategory();
        BeanUtils.copyProperties(backCategoryVO, backCategory);
        backCategory.setUpdateTime(DateTimeUtil.currentTimeMillis());
        int result = backCategoryMapper.updateByPrimaryKeySelective(backCategory);
        return result > 0;
    }

    @Override
    public BackCategoryVO getById(Long id) {
        BackCategory backCategory = backCategoryMapper.selectByPrimaryKey(id);
        BackCategoryVO backCategoryVO = new BackCategoryVO();
        BeanUtils.copyProperties(backCategory, backCategoryVO);

        backCategoryVO.setImageUrl(uploadService.getImgDomain() + backCategoryVO.getImage());

        return backCategoryVO;
    }

    @Override
    public List<BackCategoryVO> listPage(BackCategoryQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<BackCategoryVO> backCategoryVOS = null;
        return backCategoryVOS;
    }

    @Override
    public List<BackCategoryVO> listAll() {
        List<BackCategoryVO> backCategoryVOS = backCategoryMapper.list(new BackCategoryQuery());
        return backCategoryVOS;
    }

    @Override
    public PageInfo<BackCategoryVO> getPageInfo(BackCategoryQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<BackCategoryVO> backCategoryVOS = backCategoryMapper.list(query);

        for (BackCategoryVO backCategoryVO : backCategoryVOS) {
            backCategoryVO.setImageUrl(uploadService.getImgDomain() + backCategoryVO.getImage());
        }

        return new PageInfo<>(backCategoryVOS);
    }

    @Override
    public Boolean updateStatus(Long id, Integer status) {
        BackCategory updateBackCategory = new BackCategory();
        updateBackCategory.setId(id);
        updateBackCategory.setStatus(status);
        updateBackCategory.setUpdateTime(DateTimeUtil.currentTimeMillis());

        return backCategoryMapper.updateByPrimaryKeySelective(updateBackCategory) > 0;
    }

    @Override
    public Boolean updateDelete(Long id) {
        BackCategory backCategory = backCategoryMapper.selectByPrimaryKey(id);
        if (null == backCategory) {
            return false;
        }
        BackCategory update = new BackCategory();
        update.setId(id);
        update.setIsDelete(1);
        update.setUpdateTime(DateTimeUtil.currentTimeMillis());
        update.setVersion(backCategory.getVersion());
        int rows = backCategoryMapper.updateByPrimaryKeySelective(update);
        return rows > 0;

    }
}