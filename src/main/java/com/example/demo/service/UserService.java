package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.EncryptUtil;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.SysUser;
import com.example.demo.entity.Users;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 11:03
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Service
public class UserService extends ServiceImpl<UserMapper, SysUser> implements Serializable {

    public ApiJSON<SysUser> editPwd(Integer id, String password) {
        try {
            if (id != null && password != null) {
                SysUser user = this.getById(id);
                if (null != user) {
                    //解密
                    String pwd = EncryptUtil.Base64Encode(password);
                    user.setPassword(pwd);
                    return ApiJSON.data(user);
                }
                return ApiJSON.error("密码修改失败");
            }
            return ApiJSON.error("密码修改失败");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ApiJSON.error("密码修改失败");
        }
    }

    public ApiJSON<SysUser> edit(SysUser entity) {
        try {
            saveOrUpdate(entity);
            return ApiJSON.data(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ApiJSON.error("更新失败");
        }
    }
}
