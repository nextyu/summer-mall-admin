package com.nextyu.mall.web.controller;


import com.nextyu.mall.common.ServiceResponse;
import com.nextyu.mall.properties.MallProperties;
import com.nextyu.mall.vo.MenuVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 2017-03-08 上午11:49
 *
 * @author nextyu
 */
@Controller
public class IndexController extends BaseController {
    private static final String VIEW_PREFIX = "index/";

    @GetMapping({"/", "index"})
//    @PreAuthorize("hasRole('ADMIN')")
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }

        model.addAttribute("username", username);

        return VIEW_PREFIX + "index";
    }

    @GetMapping("/welcome")
    public String main() {
        return VIEW_PREFIX + "welcome";
    }

    @GetMapping("/login")
    public String login() {
        return VIEW_PREFIX + "login";
    }

    /*@PostMapping("/login")
    @ResponseBody
    public Object login(String name, String password) {

        if (!StringUtils.equals(name, "smile") || !StringUtils.equals(password, "123456")) {
           return ServiceResponse.buildError("用户名或密码错误");
        }

        return ServiceResponse.buildOk().url("/");
    }*/


    /**
     * 菜单列表
     *
     * @return
     */
    @GetMapping(value = "/menuList")
    @ResponseBody
    public Object menuList() {
        List<MenuVO> menuVOS = new ArrayList<>();

        MenuVO m1 = new MenuVO(1L, "首页管理", "&#xe600;", "");
        MenuVO m11 = new MenuVO(11L, "轮播图", "&#xe60b;", "carouses/list");
        List<MenuVO> m1Subs = new ArrayList<>();
        m1Subs.add(m11);
        m1.setSub(m1Subs);
        menuVOS.add(m1);

        MenuVO m2 = new MenuVO(2L, "产品管理", "&#xe631;", "");
        MenuVO m21 = new MenuVO(21L, "产品列表", "&#xe631;", "products/list");
        List<MenuVO> m2Subs = new ArrayList<>();
        m2Subs.add(m21);
        m2.setSub(m2Subs);
        menuVOS.add(m2);

        MenuVO m3 = new MenuVO(3L, "类目管理", "&#xe610;", "");
        MenuVO m31 = new MenuVO(31L, "后端类目", "&#xe610;", "backCategory/list");
        List<MenuVO> m3Subs = new ArrayList<>();
        m3Subs.add(m31);
        m3.setSub(m3Subs);
        menuVOS.add(m3);



        /*MenuVO m4 = new MenuVO(4L, "爬虫管理", "&#xe617;", "");
        MenuVO m41 = new MenuVO(41L, "爬虫列表", "&#xe617;", "spider/list");
        List<MenuVO> m4Subs = new ArrayList<>();
        m4Subs.add(m41);
        m4.setSub(m4Subs);
        menuVOS.add(m4);*/


        MenuVO m5 = new MenuVO(5L, "系统管理", "&#xe659;", "");
        MenuVO m51 = new MenuVO(51L, "系统监控", "&#xe659;", "druid/index.html");
        List<MenuVO> m5Subs = new ArrayList<>();
        m5Subs.add(m51);
        m5.setSub(m5Subs);
        menuVOS.add(m5);






        /*MenuVO activity = new MenuVO(2L, "活动管理", "&#xe633;", "");
        MenuVO activity21 = new MenuVO(21L, "活动列表", "&#xe633;", "activities/list");
        List<MenuVO> activitySubs = new ArrayList<>();
        activitySubs.add(activity21);
        activity.setSub(activitySubs);

        menuVOS.add(activity);*/


        return ServiceResponse.buildOk(null, menuVOS);
    }
}
