package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkRegisterApi;
import com.example.demo.json.ApiJSON;
import com.example.demo.service.mk.MkRegService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


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
public class MkRegisterController implements MkRegisterApi {

    @Autowired
    MkRegService service;

    @Override
    public ApiJSON<?> register(String account,
                               String email,
                               String pwd,
                               String name,
                               String userName,
                               String imgUrl,
                               String sex,
                               String phone,
                               String iDcard,
                               Integer ctype,
                               String openId,
                               Integer age
                               ) {
        return service.register(account, email, pwd, name, userName, imgUrl,
                sex, phone, iDcard, ctype, openId, age);
    }
}
