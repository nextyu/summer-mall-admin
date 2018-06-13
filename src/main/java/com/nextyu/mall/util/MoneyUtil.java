package com.nextyu.mall.util;

import java.math.BigDecimal;

/**
 * @author zhouyu
 */
public class MoneyUtil {
    public static Long yuan2Fen(BigDecimal yuan) {
        if (yuan == null) {
            return 0L;
        }
        return yuan.multiply(new BigDecimal(100)).longValue();
    }

    public static BigDecimal fen2Yuan(Long fen) {
        if (fen == null) {
            return new BigDecimal(0);
        }
        return new BigDecimal(fen.toString()).divide(new BigDecimal(100));
    }
}
