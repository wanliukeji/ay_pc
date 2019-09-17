package com.example.demo.api;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.aspect.Log;
import com.example.demo.entity.Audio;
import com.example.demo.entity.SysUser;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 登录
 */
public interface LoginApi extends Serializable {

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("api/login")
    public ResultJSON<Boolean> login(@RequestBody SysUser user);

}
