package com.example.demo.controller;

import com.example.demo.api.RoleApi;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 14:44
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@RestController
public class RoleAction implements RoleApi {

    @Autowired
    RoleService service;

    @Override
    public void inseRole(String name) throws Exception {

        Role entity = new Role();
        entity.setName(name);
        entity.setCreateDate(new Date());
        entity.setDescription("测试用");
        entity.setIsSystem(1);
        entity.setModifyDate(new Date());
        entity.setPermissions(1);
        boolean flag = service.save(entity);
        int num = 0;
    }
}
