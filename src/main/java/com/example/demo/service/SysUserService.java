package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.EncryptUtil;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.dao.SysUSerMapper;
import com.example.demo.entity.SysUser;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/24 10:43
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Service
@Slf4j
public class SysUserService extends ServiceImpl<SysUSerMapper, SysUser> {

    public ResultJSON<SysUser> login(String account, String password) {
        boolean flag = false;
        //解密
        try {
            if (null != account && password != null) {
                SysUser user = this.getOne(new QueryWrapper<SysUser>().eq("account", account));
                if (null != user) {
                    //解密
                    String pwd = EncryptUtil.Base64Decode(user.getPassword());
                    if (password.equalsIgnoreCase(password)) {
                        HttpServletRequestUtil.setSessionUser(user);
                        return ResultJSON.success(user);
                    }
                } else {
                    return ResultJSON.error(CodeMsg.LOGIN_ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultJSON.error(CodeMsg.SERVER_ERROR);
        }
        return ResultJSON.error(CodeMsg.SERVER_ERROR);
    }
}
