package com.nextyu.mall.util;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouyu
 */
public class OrderStatusHelper {
    private static final Map<Integer, String> MAP = new HashMap<>();
    // 1：待付款，2：待发货，3：待收货，4：已完成，5.已取消
    static {
        MAP.put(1,"待付款");
        MAP.put(2,"待发货");
        MAP.put(3,"待收货");
        MAP.put(4,"已完成");
        MAP.put(5,"已取消");
    }

    public static String getDesc(Integer status) {
        String s = MAP.get(status);
        if (StrUtil.isEmpty(s)) {
            s = "未知状态";
        }
        return s;
    }
}
