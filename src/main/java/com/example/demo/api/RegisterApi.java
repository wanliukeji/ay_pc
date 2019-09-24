package com.example.demo.api;

import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @PostMapping(value = "/api/register", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "角色保存数据接口", notes = "角色保存数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号码", required = true, dataType = "String")
    })
    ResultJSON<?> send(@RequestParam String mobile);
}
