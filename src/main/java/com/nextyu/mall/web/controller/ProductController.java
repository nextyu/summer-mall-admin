package com.nextyu.mall.web.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.nextyu.mall.common.ServiceResponse;
import com.nextyu.mall.query.ProductQuery;
import com.nextyu.mall.service.BackCategoryService;
import com.nextyu.mall.service.ProductService;
import com.nextyu.mall.util.CastUtil;
import com.nextyu.mall.vo.BackCategoryVO;
import com.nextyu.mall.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {

    private static final String VIEW_PREFIX = "product/";

    @Autowired
    private BackCategoryService backCategoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/save")
    public String save(Model model) {
        List<BackCategoryVO> backCategoryVOS = backCategoryService.listAll();
        model.addAttribute("backCategories", backCategoryVOS);

        return VIEW_PREFIX + "save";
    }

    @PostMapping()
    @ResponseBody
    public Object save(ProductVO productVO) {
        Boolean isSuccess = productService.save(productVO);
        return ServiceResponse.buildOk().reload();
    }


    @PutMapping("/updateStatus")
    @ResponseBody
    public Object updateStatus(Long id, Integer status) {
        Boolean isSuccess = productService.updateStatus(id, status);
        return ServiceResponse.buildOk();
    }

    @PutMapping("/updateStatusBatch")
    @ResponseBody
    public Object updateStatus(String id, Integer status) {
        List<String> ids = StrUtil.split(id, ',');
        if (CollUtil.isNotEmpty(ids)) {
            ids.forEach(oneId -> {
                productService.updateStatus(CastUtil.castLong(oneId), status);
            });
        }
        return ServiceResponse.buildOk().reload();
    }

    @PutMapping("/updateDelete")
    @ResponseBody
    public Object updateDelete(Long id) {
        Boolean isSuccess = productService.updateDelete(id);
        return ServiceResponse.buildOk();
    }

    @GetMapping("/update")
    public String update(Long id, Model model) {

        List<BackCategoryVO> backCategoryVOS = backCategoryService.listAll();
        model.addAttribute("backCategories", backCategoryVOS);

        ProductVO productVO = productService.getById(id);
        model.addAttribute("object", productVO);

        return VIEW_PREFIX + "save";
    }

    /*

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable Long id, ProductVO productVO) {
        Boolean isSuccess = productService.update(productVO);
        return new Object();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getById(@PathVariable Long id) {
        ProductVO productVO = productService.getById(id);
        return new Object();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object listAll() {
        List<ProductVO> productVOS = productService.listAll();
        return new Object();
    }*/

    @GetMapping("/list")
    public String listPage(Model model) {
        List<BackCategoryVO> backCategoryVOS = backCategoryService.listAll();
        model.addAttribute("backCategories", backCategoryVOS);
        return VIEW_PREFIX + "list";
    }

    @GetMapping()
    @ResponseBody
    public Object listPage(ProductQuery query) {
        PageInfo<ProductVO> pageInfo = productService.getPageInfo(query);
        return ServiceResponse.buildOk(pageInfo.getPages(), pageInfo.getList());
    }

    @GetMapping("/duplicate/{id}")
    @ResponseBody
    public Object duplicate(@PathVariable Long id) {
        ProductVO productVO = productService.getById(id);
        productVO.setId(null);
        productService.save(productVO);
        return ServiceResponse.buildOk().reload();
    }


}