package com.example.demo.api;

import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 登录
 */
@Api(value = "后台登录模块", description = "后台登录接口")
public interface AdminApi extends Serializable {

    /**
     * 登录
     * @param account
     * @param account
     */
    @ApiOperation(value = "登录接口", notes = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账户"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping(value = "/api/user/admin")
    public ResultJSON<Boolean> admin(@RequestParam("account") String account,
                                     @RequestParam("password") String password);

}
