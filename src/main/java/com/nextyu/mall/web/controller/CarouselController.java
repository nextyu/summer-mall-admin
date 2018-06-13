package com.nextyu.mall.web.controller;

import com.github.pagehelper.PageInfo;
import com.nextyu.mall.common.ServiceResponse;
import com.nextyu.mall.query.CarouselQuery;
import com.nextyu.mall.service.CarouselService;
import com.nextyu.mall.vo.CarouselVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carouses")
public class CarouselController extends BaseController {

    private static final String VIEW_PREFIX = "carousel/";

    @Autowired
    private CarouselService carouselService;

    @GetMapping("/save")
    public String save() {
        return VIEW_PREFIX + "save";
    }

    @PostMapping()
    @ResponseBody
    public Object save(CarouselVO carouselVO) {
        Boolean isSuccess = carouselService.save(carouselVO);
        return ServiceResponse.buildOk().reload();
    }

    @GetMapping("/update")
    public String update(Long id, Model model) {

        CarouselVO carouselVO = carouselService.getById(id);
        model.addAttribute("object", carouselVO);

        return VIEW_PREFIX + "save";
    }

    @PutMapping("/updateStatus")
    @ResponseBody
    public Object updateStatus(Long id, Integer status) {
        Boolean isSuccess = carouselService.updateStatus(id, status);
        return ServiceResponse.buildOk();
    }

    @GetMapping("/list")
    public String listPage() {
        return VIEW_PREFIX + "list";
    }

    @GetMapping()
    @ResponseBody
    public Object listPage(CarouselQuery query) {
        PageInfo<CarouselVO> pageInfo = carouselService.getPageInfo(query);
        return ServiceResponse.buildOk(pageInfo.getPages(), pageInfo.getList());
    }

}