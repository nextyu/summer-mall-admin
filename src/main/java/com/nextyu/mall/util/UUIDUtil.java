package com.nextyu.mall.util;

import java.util.UUID;

/**
 * created on 2017-07-11 9:49
 *
 * @author nextyu
 */
public final class UUIDUtil {
    private UUIDUtil() {
        throw new AssertionError();
    }

    public static String uuid() {
        String s = UUID.randomUUID().toString();
        return s.replace("-", "");
    }
}
