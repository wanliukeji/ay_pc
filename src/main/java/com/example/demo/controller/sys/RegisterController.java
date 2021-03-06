package com.example.demo.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Utils.EncryptUtil;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.api.RegisterApi;
import com.example.demo.entity.SysUser;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.RegService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


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
@Api(value = "注册模块", description = "注册接口")
public class RegisterController implements RegisterApi {

    @Autowired
    RegService service;

    @Override
    public ApiJSON<?> register(String account, String email, String password) {
        return service.register(account, email, password);
    }
}
