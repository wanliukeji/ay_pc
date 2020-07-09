package com.example.demo.api.mk;

import com.example.demo.json.ApiJSON;
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
public interface MkRegisterApi {

    @ApiOperation(value = "注册接口", notes = "注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",  value = "账号"),
            @ApiImplicitParam(name = "email",  value = "邮箱"),
            @ApiImplicitParam(name = "pwd",  value = "密码"),
            @ApiImplicitParam(name = "name",  value = "真实姓名"),
            @ApiImplicitParam(name = "userName",  value = "用户名"),
            @ApiImplicitParam(name = "imgUrl",  value = "头像路径"),
            @ApiImplicitParam(name = "sex",  value = "性别"),
            @ApiImplicitParam(name = "phone",  value = "电话号码"),
            @ApiImplicitParam(name = "iDcard",  value = "身份证号"),
            @ApiImplicitParam(name = "ctype",  value = "身份类型(1个人 2 房东 3 企业)"),
            @ApiImplicitParam(name = "openId",  value = "OPENID 唯一标识"),
            @ApiImplicitParam(name = "age",  value = "年龄")
    })
    @PostMapping(value = "/mk/api/user/register")
    @Transactional(rollbackFor = Exception.class)
    ApiJSON<?> register(@RequestParam(required = false, value = "account") String account,
                        @RequestParam(required = false, value = "email") String email,
                        @RequestParam(required = false, value = "pwd") String pwd,
                        @RequestParam(required = false, value = "name") String name,
                        @RequestParam(required = false, value = "userName") String userName,
                        @RequestParam(required = false, value = "imgUrl") String imgUrl,
                        @RequestParam(required = false, value = "sex") String sex,
                        @RequestParam(required = false, value = "phone") String phone,
                        @RequestParam(required = false, value = "iDcard") String iDcard,
                        @RequestParam(required = false, value = "ctype") Integer ctype,
                        @RequestParam(required = false, value = "openId") String openId,
                        @RequestParam(required = false, value = "age") Integer age
    );
}
