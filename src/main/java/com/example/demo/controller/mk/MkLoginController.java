package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkLoginApi;
import com.example.demo.entity.mk.MkUser;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkUserService;
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
public class MkLoginController implements MkLoginApi {

    @Autowired
    private MkUserService mkUserService;

    public ResultJSON<MkUser> login(String account, String password) {
        return mkUserService.login(account, password);
    }

    @Override
    public ResultJSON<?> wxlogin(String code) {
        return mkUserService.wxlogin(code);
    }


    @Override
    public ResultJSON<?> codelogin(String phone) {
        return mkUserService.codelogin(phone);
    }

    @Override
    public ResultJSON loginOut() {
        return mkUserService.loginOut();
    }

    @Override
    public ResultJSON sendSms(String phone) {
        return mkUserService.sendSms(phone);
    }

//    @Override
//    public ResultJSON<MkUser> getUserInfo(Integer id) {
//        return mkUserService.getUserInfo(id);
//    }

    @Override
    public ResultJSON<MkUser> verify(Integer userId, String ctype, String name, String iDcard) {
        return mkUserService.verify(userId, ctype, name, iDcard);
    }

}
