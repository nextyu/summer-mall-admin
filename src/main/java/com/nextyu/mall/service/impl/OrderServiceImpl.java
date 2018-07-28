package com.nextyu.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextyu.mall.dao.OrderMapper;
import com.nextyu.mall.entity.Order;
import com.nextyu.mall.enums.OrderStatusEnum;
import com.nextyu.mall.query.OrderQuery;
import com.nextyu.mall.service.OrderService;
import com.nextyu.mall.service.UploadService;
import com.nextyu.mall.util.DateTimeUtil;
import com.nextyu.mall.util.MoneyUtil;
import com.nextyu.mall.util.OrderStatusHelper;
import com.nextyu.mall.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UploadService uploadService;

    @Override
    public Boolean save(OrderVO orderVO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderVO, order);
        int rows = orderMapper.insertSelective(order);
        return rows > 0;
    }

    @Override
    public Boolean update(OrderVO orderVO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderVO, order);
        int rows = orderMapper.updateByPrimaryKeySelective(order);
        return rows > 0;
    }

    @Override
    public OrderVO getById(Long id) {
        return null;
    }

    @Override
    public List<OrderVO> listPage(OrderQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<OrderVO> orderVOS = null;
        return orderVOS;
    }

    @Override
    public List<OrderVO> listAll() {
        List<OrderVO> orderVOS = null;
        return orderVOS;
    }

    @Override
    public PageInfo<OrderVO> getPageInfo(OrderQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<OrderVO> orderVOS = orderMapper.list(query);
        if (CollUtil.isNotEmpty(orderVOS)) {
            for (OrderVO orderVO : orderVOS) {
                orderVO.setProductImage(uploadService.getImgDomain() + orderVO.getProductImage());
                orderVO.setOrderStatusStr(OrderStatusEnum.getDesc(orderVO.getOrderStatus()));
                orderVO.setOrderTime(DateTimeUtil.formatMillis(orderVO.getCreateTime()));
                orderVO.setProductPriceYuan(MoneyUtil.fen2Yuan(orderVO.getProductPrice()).toString());
                orderVO.setTotalPriceYuan(MoneyUtil.fen2Yuan(orderVO.getTotalPrice()).toString());
            }
        }
        return new PageInfo<>(orderVOS);
    }

    @Override
    public boolean confirm(Long id) {
        Order existOrder = orderMapper.selectByPrimaryKey(id);
        if (existOrder == null) {
            return false;
        }
        Order orderUpdate = new Order();
        orderUpdate.setId(id);
        orderUpdate.setUpdateTime(DateTimeUtil.currentTimeMillis());
        orderUpdate.setOrderStatus(OrderStatusEnum.USED.code());
        orderUpdate.setVersion(existOrder.getVersion());
        int rows = orderMapper.updateByPrimaryKeySelective(orderUpdate);
        return rows > 0;
    }
}