package com.nextyu.mall.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2017-03-08 上午11:49
 *
 * @author nextyu
 */
public abstract class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected static final String PAGE_500 = "500";
}
