package com.nextyu.mall.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * created on 2017-10-30 10:24
 *
 * @author nextyu
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "mall")
public class MallProperties {
    private QiniuProperties qiniu = new QiniuProperties();
}
