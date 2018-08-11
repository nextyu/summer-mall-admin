package com.nextyu.mall.web.controller;


import com.nextyu.mall.common.ServiceResponse;
import com.nextyu.mall.service.IndexService;
import com.nextyu.mall.vo.MenuVO;
import com.nextyu.mall.vo.WelcomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nextyu
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private IndexService indexService;

    private static final String VIEW_PREFIX = "index/";

    @GetMapping({"/", "index"})
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
    public String main(Model model) {
        WelcomeVO welcomeVO = indexService.getWelcomeInfo();
        model.addAttribute("welcomeInfo", welcomeVO);
        return VIEW_PREFIX + "welcome";
    }

    @GetMapping("/login")
    public String login() {
        return VIEW_PREFIX + "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return VIEW_PREFIX + "login";
    }


    /**
     * 菜单列表
     *
     * @return
     */
    @GetMapping(value = "/menuList")
    @ResponseBody
    public Object menuList() {
        List<MenuVO> menuVOS = new ArrayList<>();

        MenuVO m3 = new MenuVO(3L, "类目管理", "&#xe610;", "");
        MenuVO m31 = new MenuVO(31L, "类目列表", "&#xe610;", "backCategory/list");
        List<MenuVO> m3Subs = new ArrayList<>();
        m3Subs.add(m31);
        m3.setSub(m3Subs);
        menuVOS.add(m3);

        MenuVO m2 = new MenuVO(2L, "商品管理", "&#xe631;", "");
        MenuVO m21 = new MenuVO(21L, "商品列表", "&#xe631;", "products/list");
        List<MenuVO> m2Subs = new ArrayList<>();
        m2Subs.add(m21);
        m2.setSub(m2Subs);
        menuVOS.add(m2);




        MenuVO m4 = new MenuVO(4L, "会员管理", "&#xe608;", "");
        MenuVO m41 = new MenuVO(41L, "会员列表", "&#xe608;", "users/list");
        List<MenuVO> m4Subs = new ArrayList<>();
        m4Subs.add(m41);
        m4.setSub(m4Subs);
        menuVOS.add(m4);


        MenuVO m5 = new MenuVO(5L, "订单管理", "&#xe63b;", "");
        MenuVO m51 = new MenuVO(51L, "订单列表", "&#xe63b;", "orders/list");
        List<MenuVO> m5Subs = new ArrayList<>();
        m5Subs.add(m51);
        m5.setSub(m5Subs);
        menuVOS.add(m5);

        return ServiceResponse.buildOk(null, menuVOS);
    }
}
