package com.example.demo.controller;

import com.example.demo.api.AddrApi;
import com.example.demo.api.MenuApi;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import com.example.demo.service.AddrService;
import com.example.demo.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
public class AddrAction implements AddrApi {

    @Autowired
    private AddrService service;

    @Override
    public ResultJSON<?> getByPage(ReqParam param) throws Exception {
        return null;
    }

    @Override
    public ResultJSON<?> getInfos(Integer pid) throws Exception {
        return service.getInfos(pid);
    }

    @Override
    public ResultJSON<?> getInfos(String name) throws Exception {
        return service.getInfos(name);
    }
}

