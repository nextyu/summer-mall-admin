package com.nextyu.mall.service;

import com.github.pagehelper.PageInfo;
import com.nextyu.mall.query.OrderQuery;
import com.nextyu.mall.vo.OrderVO;

import java.util.List;

public interface OrderService {
    Boolean save(OrderVO orderVO);

    OrderVO getById(Long id);

    Boolean update(OrderVO orderVO);

    List<OrderVO> listAll();

    List<OrderVO> listPage(OrderQuery query);

    PageInfo<OrderVO> getPageInfo(OrderQuery query);
}