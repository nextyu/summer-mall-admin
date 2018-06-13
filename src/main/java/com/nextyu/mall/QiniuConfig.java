package com.nextyu.mall;

import com.nextyu.mall.properties.MallProperties;
import com.qiniu.common.Zone;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 2017-07-10 20:25
 *
 * @author nextyu
 */
@Configuration
public class QiniuConfig {

    @Autowired
    private MallProperties mallProperties;

    @Bean
    public com.qiniu.storage.Configuration config() {
        return new com.qiniu.storage.Configuration(Zone.zone2());
    }

    @Bean
    public UploadManager uploadManager() {
        return new UploadManager(config());
    }

    @Bean
    public Auth auth() {
        return Auth.create(mallProperties.getQiniu().getAccessKey(), mallProperties.getQiniu().getSecretKey());
    }


}
