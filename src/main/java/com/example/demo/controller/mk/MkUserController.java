package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkUserApi;
import com.example.demo.entity.mk.MkUser;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 房屋设施配套
 */
@RestController
@Slf4j
public class MkUserController implements MkUserApi {

    @Resource
    private MkUserService service;


    @Override
    public ResultJSON<?> edit(String email, String pwd, String name, String userName, String imgUrl, String sex, String phone,
                              Integer ctype, Integer age) {
        return service.edit(email, pwd, name, userName, imgUrl, sex, phone, ctype, age);
    }

    @Override
    public ResultJSON<MkUser> getUserInfo(String userId) {
        return service.getUserInfo(userId);
    }

    @Override
    public ResultJSON<?> addChilds(String userId, String name, String phone, String pwd) {
        return service.addChilds(userId, name, phone, pwd);
    }

    @Override
    public ResultJSON<?> getChilds(String userId) {
        return service.getChilds(userId);
    }

    @Override
    public ResultJSON<?> delChilds(String userId) {
        return service.delChilds(userId);
    }
}
