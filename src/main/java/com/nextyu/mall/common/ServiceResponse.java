package com.nextyu.mall.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nextyu.mall.enums.CodeMessageEnum;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * created on 2017-07-10 9:52
 *
 * @author nextyu
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ServiceResponse<T> {
    private Integer status;
    private String msg;
    private T data;
    private Integer pages;

    /**
     * 1. 为空或不定义时不操作
     * 2. 为地址时跳转到相应地址
     * 3. 为reload是刷新页面
     * 4. 为reloadTpl是重新加载当前页面的列表
     * 5. 为reloadParentTpl是重新加载当前页面父页面的列表
     */
    private String url;


    private ServiceResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServiceResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServiceResponse(Integer status, String msg, Integer pages, T data) {
        this.status = status;
        this.msg = msg;
        this.pages = pages;
        this.data = data;
    }



    /*public boolean isOk() {
        return CodeMessageEnum.OK.code().equals(status);
    }*/

    public static ServiceResponse buildOk() {
        return new ServiceResponse(CodeMessageEnum.OK.code(), CodeMessageEnum.OK.msg());
    }

    public static <T> ServiceResponse buildOk(T data) {
        return new ServiceResponse<T>(CodeMessageEnum.OK.code(), CodeMessageEnum.OK.msg(), data);
    }

    public static <T> ServiceResponse buildOk(Integer pages, T data) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", data);
        return new ServiceResponse(CodeMessageEnum.OK.code(), CodeMessageEnum.OK.msg(), pages, map);
    }


    public static ServiceResponse buildError() {
        return new ServiceResponse(CodeMessageEnum.ERROR.code(), CodeMessageEnum.ERROR.msg());
    }

    public static ServiceResponse buildError(Integer status, String msg) {
        return new ServiceResponse(status, msg);
    }

    public static ServiceResponse buildError(String msg) {
        return new ServiceResponse(CodeMessageEnum.ERROR.code(), msg);
    }

    public ServiceResponse reload() {
        this.url = "reload";
        return this;
    }

    public ServiceResponse url(String url) {
        this.url = url;
        return this;
    }

}
