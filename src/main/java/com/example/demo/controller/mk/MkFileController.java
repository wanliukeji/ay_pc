package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkFileApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 登录控制器
 */
@RestController
@Slf4j
public class MkFileController implements MkFileApi {

    @Autowired
    private MkFileService service;

    @Override
    public ResultJSON<?> upload(MultipartFile multipartFile, String userId) {
        return service.uploadfile(multipartFile,userId);
    }
}
