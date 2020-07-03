package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.EncryptUtil;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkUSerMapper;
import com.example.demo.entity.mk.MkUser;
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
public class MkUserService extends ServiceImpl<MkUSerMapper, MkUser> {

    //    账户密码
    public ResultJSON<MkUser> login(String account, String password) {
        boolean flag = false;
        //解密
        try {
            if (null != account && password != null) {
                MkUser user = this.getOne(new QueryWrapper<MkUser>().eq("accout", account).or().eq("email", account).or().eq("phone", account));
                if (null != user) {
                    //解密
                    String pwd = EncryptUtil.Base64Decode(user.getPwd());
                    if (password.equalsIgnoreCase(pwd)) {
                        HttpServletRequestUtil.setSessionUser(user);
                        return ResultJSON.success(user);
                    } else {
                        return ResultJSON.error(CodeMsg.LOGIN_ERROR);
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

    //微信
    public ResultJSON<MkUser> wclogin(String account, String password) {
        boolean flag = false;
        //解密
        try {
            if (null != account && password != null) {
                MkUser user = this.getOne(new QueryWrapper<MkUser>().eq("account", account).or().eq("email", account).or().eq("phone", account));
                if (null != user) {
                    //解密
                    String pwd = EncryptUtil.Base64Decode(user.getPwd());
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

    //    手机验证
    public ResultJSON<MkUser> codelogin(String account, String password) {
        boolean flag = false;
        //解密
        try {
            if (null != account && password != null) {
                MkUser user = this.getOne(new QueryWrapper<MkUser>().eq("account", account).or().eq("email", account).or().eq("phone", account));
                if (null != user) {
                    //解密
                    String pwd = EncryptUtil.Base64Decode(user.getPwd());
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

    //    手机验证
    public ResultJSON loginOut() {
        boolean flag = false;
        //解密
        try {
            HttpServletRequestUtil.delSessionUser();
            return ResultJSON.error(CodeMsg.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultJSON.error(CodeMsg.SERVER_ERROR);
        }
    }

    public ResultJSON<MkUser> verify(Integer userId, String ctype, String name, String iDcard) {
        //解密
        try {
            if (StringUtil.isNotEmty(userId) && StringUtil.isNotEmty(ctype) && StringUtil.isNotEmty(name) && StringUtil.isNotEmty(iDcard)) {
                MkUser user = this.getById(userId);
                if (null != user) {
                    //解密
                    user.setName(name);
                    user.setCtype(Long.valueOf(ctype));
                    user.setIDcard(iDcard);
                    boolean f = this.saveOrUpdate(user);
                    if (f) {
                        HttpServletRequestUtil.setSessionUser(user);
                        return ResultJSON.success(CodeMsg.UPDATE_SUCCESS);
                    } else {
                        return ResultJSON.error(CodeMsg.UPDATE_ERROR);
                    }
                } else {
                    return ResultJSON.error(CodeMsg.NULL_ERROR);
                }
            } else {
                return ResultJSON.error(CodeMsg.EMPTY_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultJSON.error(CodeMsg.SERVER_ERROR);
        }
    }
}


