package com.nextyu.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.nextyu.mall.common.ServiceResponse;
import com.nextyu.mall.query.BackCategoryQuery;
import com.nextyu.mall.service.BackCategoryService;
import com.nextyu.mall.vo.BackCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/backCategory")
public class BackCategoryController extends BaseController {

    private static final String VIEW_PREFIX = "back-category/";

    @Autowired
    private BackCategoryService backCategoryService;

    @GetMapping("/save")
    public String save() {
        return VIEW_PREFIX + "save";
    }

    @PostMapping()
    @ResponseBody
    public Object save(BackCategoryVO backCategoryVO) {
        Boolean isSuccess = backCategoryService.save(backCategoryVO);
        return ServiceResponse.buildOk().reload();
    }

    @GetMapping("/update")
    public String update(Long id, Model model) {

        BackCategoryVO backCategoryVO = backCategoryService.getById(id);
        model.addAttribute("object", backCategoryVO);

        return VIEW_PREFIX + "save";
    }

    @PutMapping("/updateStatus")
    @ResponseBody
    public Object updateStatus(Long id, Integer status) {
        Boolean isSuccess = backCategoryService.updateStatus(id, status);
        return ServiceResponse.buildOk();
    }

    @PutMapping("/updateDelete")
    @ResponseBody
    public Object updateDelete(Long id) {
        Boolean isSuccess = backCategoryService.updateDelete(id);
        return ServiceResponse.buildOk();
    }

    @GetMapping("/list")
    public String listPage() {
        return VIEW_PREFIX + "list";
    }

    @GetMapping()
    @ResponseBody
    public Object listPage(BackCategoryQuery query) {
        PageInfo<BackCategoryVO> pageInfo = backCategoryService.getPageInfo(query);
        return ServiceResponse.buildOk(pageInfo.getPages(), pageInfo.getList());
    }
}