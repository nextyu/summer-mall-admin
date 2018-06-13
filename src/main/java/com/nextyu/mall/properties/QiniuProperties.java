package com.nextyu.mall.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * created on 2017-10-30 10:26
 *
 * @author nextyu
 */
@Setter
@Getter
public class QiniuProperties {
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String imgDomain;
}
