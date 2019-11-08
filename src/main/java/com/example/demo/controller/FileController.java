package com.example.demo.controller;

import com.example.demo.api.FiedApi;
import com.example.demo.api.FileApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import com.example.demo.service.FiedService;
import com.example.demo.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 10:45
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@RestController
@Slf4j
public class FileController implements FileApi {

    @Autowired
    private FileService fileService;

    @Override
    public ResultJSON<?> getInfos(String fiedId) throws Exception {
        return fileService.getInfos(fiedId);
    }

    @Override
    public ResultJSON<?> gethoppyList(String text) throws Exception {
        return fileService.gethoppyList(text);
    }
}

