package com.example.demo.api;
import com.example.demo.entity.SysUser;
import com.example.demo.json.ResultJSON;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 注册
 */
public interface RegisterApi extends Serializable {

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("api/register")
    public ResultJSON<Boolean> register(@RequestBody SysUser user);

}
