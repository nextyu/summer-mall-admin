package com.nextyu.mall.web.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.nextyu.mall.common.ServiceResponse;
import com.nextyu.mall.query.OrderQuery;
import com.nextyu.mall.query.UserQuery;
import com.nextyu.mall.service.OrderService;
import com.nextyu.mall.util.CastUtil;
import com.nextyu.mall.vo.OrderVO;
import com.nextyu.mall.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public String listPage(Model model) {
        return "order/list";
    }

    @GetMapping()
    @ResponseBody
    public Object listPage(OrderQuery query) {
        PageInfo<OrderVO> pageInfo = orderService.getPageInfo(query);
        return ServiceResponse.buildOk(pageInfo.getPages(), pageInfo.getList());
    }

    @PutMapping("/confirm")
    @ResponseBody
    public Object updateStatus(Long id) {
        boolean isSuccess = orderService.confirm(id);
        return ServiceResponse.buildOk().reload();
    }
}