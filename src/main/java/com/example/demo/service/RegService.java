package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.EncryptUtil;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.RegMapper;
import com.example.demo.entity.SysUser;
import com.example.demo.json.ApiJSON;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 11:03
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Service
public class RegService extends ServiceImpl<RegMapper, SysUser> implements Serializable {

    public ApiJSON<?> register(String account, String email, String password) {

        if (StringUtil.isNotEmty(account)) {
            SysUser user = this.getOne(new QueryWrapper<SysUser>().eq("account", account));
            if (null != user) {
                return ApiJSON.error("该账号已使用");
            }
        }

        SysUser user = new SysUser();
        //加密
        password = EncryptUtil.Base64Encode(password);
        user.setPassword(password);
        user.setEmail(email);
        user.setAccount(account);
        user.setCreateTime(new Date());

        boolean flag = this.save(user);

        if (flag) {
            HttpServletRequestUtil.getRequest().getSession().setAttribute("user", user);
            return ApiJSON.success("注册成功");
        }
        return ApiJSON.error("注册失败");
    }
}
