package com.example.demo.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Utils.EncryptUtil;
import com.example.demo.api.RegisterApi;
import com.example.demo.entity.SysUser;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.SysUserService;
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
public class RegisterController implements RegisterApi {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public ResultJSON<Boolean> register(SysUser info) {
        try {
            if (null != info){
                SysUser user = sysUserService.getOne(new QueryWrapper<SysUser>().eq("account",info.getAccount()));
                if (null != user){
                    return ResultJSON.error(CodeMsg.REQUEST_ERROR);
                }else {
                    //密码加密
                    String pwd = EncryptUtil.MD5(user.getPassword());
                    user.setPassword(pwd);
                    boolean flag = sysUserService.save(info);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.REQUEST_ERROR);
        }
        return ResultJSON.error(CodeMsg.REQUEST_ERROR);
    }
}
