package com.example.demo.api;

import com.example.demo.entity.Classified;
import com.example.demo.entity.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 10:50
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Api(value = "用户信息模块", description = "用户信息接口")
public interface UserApi {

    @PostMapping(value = "/api/saveUser", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "用户保存数据接口", notes = "用户发布保存数据接口")
    public void saveUser(@RequestBody Users entity) throws Exception;

}