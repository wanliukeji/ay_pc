package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.api.FiedApi;
import com.example.demo.api.RelApi;
import com.example.demo.entity.Fied;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import com.example.demo.service.FiedService;
import com.example.demo.service.RelService;
import io.swagger.annotations.Api;
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
@Api(value = "加工信息模块", description = "加工信息模块")
public class FiedController implements FiedApi {

    @Autowired
    private FiedService fiedService;

    public ResultJSON<IPage<Fied>> getPage(ReqParam param) throws Exception {
        return fiedService.getPage(param);
    }

    @Override
    public ResultJSON<?> export(String ids) throws Exception {
        return fiedService.export(ids);
    };
}

