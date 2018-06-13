package com.nextyu.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.nextyu.mall.common.ServiceResponse;
import com.nextyu.mall.parse.MallPageProcessor;
import com.nextyu.mall.query.ProductQuery;
import com.nextyu.mall.service.BackCategoryService;
import com.nextyu.mall.util.DateTimeUtil;
import com.nextyu.mall.vo.BackCategoryVO;
import com.nextyu.mall.vo.ProductVO;
import com.nextyu.mall.vo.SpiderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.HttpConstant;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created on 2017-07-31 19:23
 *
 * @author nextyu
 */
@RequestMapping("/spider")
@Controller
public class SpiderController extends BaseController {

    private static final String VIEW_PREFIX = "spider/";


    private static List<SpiderVO> spiderVOS = new ArrayList<>();
    static {
        spiderVOS.add(new SpiderVO(1L, "曼淘生活爬虫", 0));
    }


    @Autowired
    private BackCategoryService backCategoryService;

    @Autowired
    private Spider spider;

    @Autowired
    private MallPageProcessor mallPageProcessor;

    @GetMapping("/list")
    public String listPage(Model model) {

        List<BackCategoryVO> backCategoryVOS = backCategoryService.listAll();
        model.addAttribute("backCategories", backCategoryVOS);

        return VIEW_PREFIX + "list";
    }

    @GetMapping()
    @ResponseBody
    public Object listPage(ProductQuery query) {
        return ServiceResponse.buildOk(1, spiderVOS);
    }


    @PostMapping("/start")
    @ResponseBody
    public Object start(Long categoryId, int pages) throws UnsupportedEncodingException {

        spiderVOS.get(0).setStatus(1);

        mallPageProcessor.setCategoryId(categoryId);
        mallPageProcessor.setPages(pages);

        spider.addUrl("http://mantao.lovelytao.com/saber/index?cid=" + categoryId + "&d=" + DateTimeUtil.currentTimeSeconds()).start();

        return ServiceResponse.buildOk().reload();
    }

    @GetMapping("/stop")
    @ResponseBody
    public Object stop() {
        spiderVOS.get(0).setStatus(0);
        spider.stop();
        return ServiceResponse.buildOk().reload();
    }

}
