package com.example.demo.api;

import com.example.demo.entity.Users;
import io.swagger.annotations.*;
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
@Api(value = "角色信息模块", description = "角色信息接口")
public interface RoleApi {

    @PostMapping(value = "/api/saveRole", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "角色保存数据接口", notes = "角色保存数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "mail", value = "用户邮箱", required = true, dataType = "String")
    })
    public void inseRole(
            @RequestParam String name) throws Exception;

}
