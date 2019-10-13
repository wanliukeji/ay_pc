package com.example.demo.controller;

import com.example.demo.api.UserApi;
import com.example.demo.entity.SysUser;
import com.example.demo.entity.Users;
import com.example.demo.json.ApiJSON;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 14:24
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService service;

    @Override
    public void saveUser(SysUser entity) throws Exception {
        service.save(entity);
    }

    @Override
    public ApiJSON<SysUser> edit(SysUser entity) throws Exception {
        return service.edit(entity);
    }

    @Override
    public ApiJSON<SysUser> editPwd(Integer id, String password) throws Exception {
        return service.editPwd(id, password);
    }
}
