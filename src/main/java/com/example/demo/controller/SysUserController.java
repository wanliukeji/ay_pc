package com.example.demo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.api.SysUserApi;
import com.example.demo.service.SysUserService;
import com.example.demo.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用户控制器
 */
@RestController
@Slf4j
public class SysUserController implements SysUserApi {
    @Autowired
    private SysUserService userService;

    @Override
    public ResultJSON<SysUser> getUser() {
        return null;
    }

    @Override
    public ResultJSON<List<SysUser>> getUsers() {
        try {
            EntityWrapper ew=new EntityWrapper();
//            List <SysUser> users = userService.list();
            List <SysUser> users = userService.list(null);
            return ResultJSON.success(users);
        }catch (Exception ex){
            ex.printStackTrace();
            log.error(ex.getMessage());
            return ResultJSON.error(CodeMsg.REQUEST_ERROR);
        }
    }
}
