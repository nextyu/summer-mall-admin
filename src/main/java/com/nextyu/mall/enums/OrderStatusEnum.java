package com.nextyu.mall.enums;

/**
 *
 * @author nextyu
 */
public enum OrderStatusEnum {
    UN_USED(1, "待消费"),
    USED(2, "已消费");
    private Integer code;
    private String desc;

    OrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer code() {
        return code;
    }

    public String msg() {
        return desc;
    }

    public static String getDesc(Integer code) {
        OrderStatusEnum[] values = values();
        for (OrderStatusEnum value : values) {
            if (value.code.equals(code)) {
                return value.desc;
            }
        }
        return "未知";
    }
}
