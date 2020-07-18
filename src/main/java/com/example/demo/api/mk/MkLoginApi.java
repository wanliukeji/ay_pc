package com.example.demo.api.mk;

import com.example.demo.entity.mk.MkUser;
import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * 登录接口
 */

@Api(value = "MK登录模块", description = "MK登录接口")
public interface MkLoginApi extends Serializable {
    /**
     * 账户密码登录
     *
     * @param account
     */
    @ApiOperation(value = "账户密码登录接口", notes = "账户密码登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账户"),
            @ApiImplicitParam(name = "password", value = "密码")
    })
    @PostMapping(value = "/mk/api/user/login")
    public ResultJSON<MkUser> login(@RequestParam("account") String account,
                                    @RequestParam("password") String password);

    /**
     * 微信一键登录
     */
    @ApiOperation(value = "微信一键登录接口", notes = "微信一键登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "wxcode", value = "账户")
    })
    @PostMapping(value = "/mk/api/user/wxlogin")
    public ResultJSON<?> wxlogin(@RequestParam("wxcode") String wxcode);


    /**
     * 手机验证码登录
     */
    @ApiOperation(value = "手机验证码登录接口", notes = "手机验证码登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号")
    })
    @PostMapping(value = "/mk/api/user/codelogin")
    public ResultJSON<?> codelogin(@RequestParam("phone") String phone);


    /**
     * 用户退出登录
     */
    @ApiOperation(value = "用户退出登录", notes = "用户退出登录")
    @PostMapping(value = "/mk/api/user/loginOut")
    public ResultJSON loginOut();

    /**
     * 发送验证码
     */
    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号")
    })
    @PostMapping(value = "/mk/api/user/sendSms")
    public ResultJSON sendSms(@RequestParam("phone") String phone);

    /**
     * 身份认证
     *
     * @param
     */
    @ApiOperation(value = "用户身份认证", notes = "用户身份认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "ctype", value = "身份类型"),
            @ApiImplicitParam(name = "name", value = "真实姓名"),
            @ApiImplicitParam(name = "iDcard", value = "身份证号")
    })
    @PostMapping(value = "/mk/api/user/verify")
    public ResultJSON<MkUser> verify(
            @RequestParam("userId") Integer userId,
            @RequestParam("ctype") String ctype,
            @RequestParam("name") String name,
            @RequestParam("iDcard") String iDcard);
}
