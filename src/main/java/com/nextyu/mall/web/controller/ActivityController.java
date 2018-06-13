package com.nextyu.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.nextyu.mall.common.ServiceResponse;
import com.nextyu.mall.query.ActivityQuery;
import com.nextyu.mall.service.ActivityService;
import com.nextyu.mall.vo.ActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/activities")
@Controller
public class ActivityController extends BaseController {

    private static final String VIEW_PREFIX = "activity/";

    @Autowired
    private ActivityService activityService;

    @GetMapping("/save")
    public String save() {
        return VIEW_PREFIX + "save";
    }

    @PostMapping()
    @ResponseBody
    public Object save(ActivityVO activityVO) {
        Boolean isSuccess = activityService.save(activityVO);
        return ServiceResponse.buildOk().reload();
    }

    @PutMapping("/updateStatus")
    @ResponseBody
    public Object updateStatus(Long id, Integer status) {
        Boolean isSuccess = activityService.updateStatus(id, status);
        return ServiceResponse.buildOk();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        ActivityVO activityVO = activityService.getById(id);
        model.addAttribute("object", activityVO);
        return VIEW_PREFIX + "save";
    }

/*
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable Long id, ActivityVO activityVO) {
        Boolean isSuccess = activityService.update(activityVO);
        return new Object();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getById(@PathVariable Long id) {
        ActivityVO activityVO = activityService.getById(id);
        return new Object();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object listPage(ActivityQuery query) {
        List<ActivityVO> activityVOS = activityService.listPage(query);
        return new Object();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object listAll() {
        List<ActivityVO> activityVOS = activityService.listAll();
        return new Object();
    }*/

    @GetMapping("/list")
    public String listPage() {
        return VIEW_PREFIX + "list";
    }

    @GetMapping()
    @ResponseBody
    public Object listPage(ActivityQuery query) {
        PageInfo<ActivityVO> pageInfo = activityService.getPageInfo(query);
        return ServiceResponse.buildOk(pageInfo.getPages(), pageInfo.getList());
    }
}