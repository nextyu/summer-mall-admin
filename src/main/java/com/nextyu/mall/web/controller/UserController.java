package com.nextyu.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.nextyu.mall.common.ServiceResponse;
import com.nextyu.mall.query.UserQuery;
import com.nextyu.mall.service.UserService;
import com.nextyu.mall.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listPage(Model model) {
        return "user/list";
    }

    @GetMapping()
    @ResponseBody
    public Object listPage(UserQuery query) {
        PageInfo<UserVO> pageInfo = userService.getPageInfo(query);
        return ServiceResponse.buildOk(pageInfo.getPages(), pageInfo.getList());
    }
}