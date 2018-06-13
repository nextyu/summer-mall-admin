package com.nextyu.mall.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * created on 2017-07-13 14:52
 *
 * @author nextyu
 */
public class UserContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserContext.class);

    private static final ThreadLocal<UserInfo> current = new ThreadLocal<>();

    public UserContext(UserInfo user) {
        current.set(user);
    }

    public static void set(UserInfo user) {
        current.set(user);
    }

    public static UserInfo get() {
        return current.get();
    }

    public static void remove() {
        if (get() != null) {
            LOGGER.debug("remove user from ThreadLocal : {}", get().getToken());
            current.remove();
        }
    }
}
