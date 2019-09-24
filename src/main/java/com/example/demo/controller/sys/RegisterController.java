package com.example.demo.controller.sys;

import com.example.demo.api.RegisterApi;
import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
public class RegisterController implements RegisterApi {

    @Override
    public ResultJSON<?> send(String mobile) {
        return null;
    }
}
