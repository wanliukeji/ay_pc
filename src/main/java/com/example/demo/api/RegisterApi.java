package com.example.demo.api;

import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 9:16
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Api(value = "注册模块", description = "注册接口")
public interface RegisterApi {

    @PostMapping(value = "/api/user/register")
    @ApiOperation(value = "注册接口", notes = "注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    ApiJSON<?> register(@RequestParam("account") String account,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password) throws Exception;
}
