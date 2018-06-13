package com.nextyu.mall.service;

import com.qiniu.storage.model.DefaultPutRet;

import java.io.InputStream;

/**
 * 2017-07-10 20:33
 *
 * @author nextyu
 */
public interface UploadService {
    DefaultPutRet uploadImg(byte[] bytes, String name);

    DefaultPutRet uploadImg(InputStream inputStream, String name);

    String getImgDomain();
}
