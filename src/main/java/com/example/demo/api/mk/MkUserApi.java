package com.example.demo.api.mk;

import com.example.demo.entity.mk.MkUser;
import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * 用户接口
 */

@Api(value = "MK用户模块", description = "MK用户模块")
public interface MkUserApi extends Serializable {


    /**
     * 用户修改
     *
     * @return
     */
    @ApiOperation(value = "MK用户修改接口", notes = "MK用户修改接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号"),
            @ApiImplicitParam(name = "email", value = "邮箱"),
            @ApiImplicitParam(name = "pwd", value = "密码"),
            @ApiImplicitParam(name = "name", value = "真实姓名"),
            @ApiImplicitParam(name = "userName", value = "用户名"),
            @ApiImplicitParam(name = "imgUrl", value = "头像路径"),
            @ApiImplicitParam(name = "sex", value = "性别"),
            @ApiImplicitParam(name = "phone", value = "电话号码"),
            @ApiImplicitParam(name = "iDcard", value = "身份证号"),
            @ApiImplicitParam(name = "ctype", value = "身份类型(1个人 2 房东 3 企业 4 企业子账户)"),
            @ApiImplicitParam(name = "openId", value = "OPENID 唯一标识"),
            @ApiImplicitParam(name = "age", value = "性别")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/user/edit")
    public ResultJSON<?> edit(
            @RequestParam( required = false, value = "email") String email,
            @RequestParam( required = true, value = "pwd") String pwd,
            @RequestParam( required = false, value = "name") String name,
            @RequestParam( required = false, value = "userName") String userName,
            @RequestParam( required = false, value = "imgUrl") String imgUrl,
            @RequestParam( required = false, value = "sex") String sex,
            @RequestParam( required = false, value = "phone") String phone,
            @RequestParam( required = false, value = "ctype") Integer ctype,
            @RequestParam( required = false, value = "age") Integer age
    );

    /**
     * 获取用户信息
     */
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID")
    })
    @PostMapping(value = "/mk/api/user/getUserInfo")
    public ResultJSON<MkUser> getUserInfo(@RequestParam("userId") String userId);


    /**
     * 添加子账户
     */
    @ApiOperation(value = "添加子账户信息", notes = "添加子账户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "name", value = "员工姓名"),
            @ApiImplicitParam(name = "phone", value = "手机号码"),
            @ApiImplicitParam(name = "pwd", value = "登录密码"),
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/user/addChilds")
    public ResultJSON<?> addChilds(
            @RequestParam("userId") String userId,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("pwd") String pwd
    );

    /**
     * 添加子账户
     */
    @ApiOperation(value = "获取子账户信息", notes = "获取子账户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/user/getChilds")
    public ResultJSON<?> getChilds(
            @RequestParam("userId") String userId
    );

    /**
     * 删除子账户
     */
    @ApiOperation(value = "删除子账户信息", notes = "删除子账户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/user/delChilds")
    public ResultJSON<?> delChilds(
            @RequestParam("userId") String userId
    );



//    /**
//     * 用户添加
//     * @return
//     */
//    @ApiOperation(value = "MK用户添加接口", notes = "MK用户添加接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "proportion", value = "租金比例"),
//            @ApiImplicitParam(name = "bountyType", value = "租金类型"),
//            @ApiImplicitParam(name = "userId", value = "创建人"),
//            @ApiImplicitParam(name = "details", value = "用户详情")
//    })
//    @Transactional(rollbackFor = Exception.class)
//    @PostMapping(value = "/mk/api/bounty/add")
//    public ResultJSON<?> add(@RequestParam(required = false, value = "proportion") Double proportion,
//                             @RequestParam(required = false, value = "bountyType") String bountyType,
//                             @RequestParam(required = false, value = "userId") String userId,
//                             @RequestParam(required = false, value = "details") String details,
//                             @RequestParam(required = false, value = "details") Integer fstatus
//    );

//    /**
//     * 用户获取
//     * @return
//     */
//    @ApiOperation(value = "MK用户所有信息接口", notes = "MK用户所有信息接口")
//    @ApiImplicitParams({})
//    @PostMapping(value = "/mk/api/apart/list")
//    public ResultJSON<?> list();

    /**
     * 用户获取
     * @return
     */
//    @ApiOperation(value = "MK用户获取接口", notes = "MK用户获取接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId", value = "用户编号")
//    })
//    @PostMapping(value = "/mk/api/bounty/list")
//    public ResultJSON<?> list(
//            @RequestParam(required = false, value = "userId") String userId
//    );
//
//    /**
//     * 用户分页删除
//     *
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "MK用户删除接口", notes = "MK用户删除接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "ID"),
//    })
//    @PostMapping(value = "/mk/api/apart/del")
//    @Transactional(rollbackFor = Exception.class)
//    public ResultJSON<?> del(@RequestParam(required = true, value = "id") Integer id);
//
//

}
