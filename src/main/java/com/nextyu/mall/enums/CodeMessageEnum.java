package com.nextyu.mall.enums;

/**
 * created on 2016-07-01 10:29
 *
 * @author nextyu
 */
public enum CodeMessageEnum {
    OK(200, "OK"),
    ERROR(500, "ERROR"),;
    private Integer code;
    private String msg;

    CodeMessageEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer code() {
        return code;
    }

    public String msg() {
        return msg;
    }
}
