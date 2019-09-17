package com.example.demo.api;

import com.example.demo.aspect.Log;
import com.example.demo.json.ResultJSON;
import com.example.demo.entity.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用户接口
 */
public interface SysUserApi extends Serializable {

    @GetMapping("/api/user")
    public ResultJSON<SysUser> getUser();

    @PostMapping("/api/getUsers")
    @Log(value = "获取用户列表")
    public ResultJSON<List<SysUser>> getUsers();

}
