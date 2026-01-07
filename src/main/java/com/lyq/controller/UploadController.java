package com.lyq.controller;

import com.lyq.pojo.Result;
import com.lyq.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;


@Slf4j
@RestController
public class UploadController {
    private static final String UPLOAD_DIR = "D:/images/";
    /**
     * 上传文件 - 参数名file
     */

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(String name,Integer age, MultipartFile file) throws Exception {
        log.info("上传文件：{}, {}, {}", name, age, file);
        if (!file.isEmpty()) {
//            // 生成唯一文件名，随机码+原文件后缀
//            String originalFilename = file.getOriginalFilename();
//            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
//            String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName;
            // 上传文件，返回一个url,可以不用在生成唯一文件名的方法了，直接调用在AliyunOSSOperator中现有的方法
            String url = aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());
            return Result.success(url);
        }
        return Result.error("上传失败");
    }
}
