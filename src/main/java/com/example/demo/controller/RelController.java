package com.example.demo.controller;

import com.example.demo.api.RelApi;
import com.example.demo.entity.Classified;
import com.example.demo.service.RelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
public class RelController implements RelApi {

    @Autowired
    private RelService relService;

    @Override
    public Boolean saveRel(Classified fied) {
        return relService.save(fied);
    }
}

