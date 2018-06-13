package com.nextyu.mall.web.controller;

import com.nextyu.mall.common.ServiceResponse;
import com.nextyu.mall.service.UploadService;
import com.nextyu.mall.util.UUIDUtil;
import com.qiniu.storage.model.DefaultPutRet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 2017-07-10 20:22
 *
 * @author nextyu
 */
@RestController
@RequestMapping("/upload")
public class UploadController extends BaseController {


    @Autowired
    private UploadService uploadService;

    @PostMapping("/img")
    public Object uploadImg(@RequestParam("file") MultipartFile[] files) {

        try {
            StringBuilder imageName = new StringBuilder("");
            for (MultipartFile file : files) {
                String originalName = file.getOriginalFilename();
                String suffix = originalName.substring(originalName.lastIndexOf('.'));
                DefaultPutRet defaultPutRet = uploadService.uploadImg(file.getBytes(), UUIDUtil.uuid() + suffix);
                if (null != defaultPutRet) {
                    if (StringUtils.isNotEmpty(imageName)) {
                        imageName.append(",").append(defaultPutRet.key);
                    } else {
                        imageName.append(defaultPutRet.key);
                    }
                }
            }
            Map<String, String> map = new HashMap<>();
            map.put("imgName", imageName.toString());
            return ServiceResponse.buildOk(map);
        } catch (IOException e) {
            logger.error("upload img to qiniu error", e);
        }
        return ServiceResponse.buildError();

    }

    @PostMapping("/imgLayUI")
    public Object layUIUploadImg(@RequestParam("file") MultipartFile file) {

        try {
            StringBuilder imageName = new StringBuilder("");

            String originalName = file.getOriginalFilename();
            String suffix = originalName.substring(originalName.lastIndexOf('.'));
            DefaultPutRet defaultPutRet = uploadService.uploadImg(file.getBytes(), UUIDUtil.uuid() + suffix);
            Map<String, Object> map = new HashMap<>();
            if (null != defaultPutRet) {
                Map<String, String> data = new HashMap<>();
                map.put("code", 0);
                map.put("msg", "OK");
                data.put("src", uploadService.getImgDomain() + defaultPutRet.key);
                map.put("data", data);
            }

            return map;
        } catch (IOException e) {
            logger.error("upload img to qiniu error", e);
        }
        return ServiceResponse.buildError();

    }

}
