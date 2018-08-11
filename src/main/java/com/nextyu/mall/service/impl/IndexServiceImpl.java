package com.nextyu.mall.service.impl;

import com.nextyu.mall.dao.OrderMapper;
import com.nextyu.mall.dao.ProductMapper;
import com.nextyu.mall.dao.UserMapper;
import com.nextyu.mall.service.IndexService;
import com.nextyu.mall.vo.WelcomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public WelcomeVO getWelcomeInfo() {

        Long userTotalCount = userMapper.getTotalCount();
        Long productTotalCount = productMapper.getTotalCount();
        Long orderTotalCount = orderMapper.getTotalCount();

        return new WelcomeVO(userTotalCount, productTotalCount, orderTotalCount);
    }
}
