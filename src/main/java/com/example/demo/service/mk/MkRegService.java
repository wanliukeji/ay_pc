package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.EncryptUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkUSerMapper;
import com.example.demo.entity.mk.MkUser;
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
public class MkRegService extends ServiceImpl<MkUSerMapper, MkUser> implements Serializable {


    public ApiJSON<?> register(String account, String email, String pwd, String name, String userName, String imgUrl, String sex, String phone, String iDcard, Integer ctype, String openId, Integer age) {

        try {
            if (StringUtil.isNotEmty(account)) {
                MkUser user = this.getOne(new QueryWrapper<MkUser>().eq("account", account));
                if (null != user) {
                    return ApiJSON.error("该账号已注册");
                }

                MkUser user2 = this.getOne(new QueryWrapper<MkUser>().eq("phone", phone));
                if (null != user2) {
                    return ApiJSON.error("该手机号已注册");
                }

                MkUser user3 = this.getOne(new QueryWrapper<MkUser>().eq("iDcard", iDcard));
                if (null != user3) {
                    return ApiJSON.error("该身份证件号已注册");
                }

                MkUser entity = new MkUser();
                //加密
                pwd = EncryptUtil.Base64Encode(pwd);
                entity.setPwd(pwd);
                entity.setUname(name);
                entity.setIDcard(iDcard);
                entity.setAccount(account);
                entity.setUtype(ctype);
                entity.setEmail(email);
                entity.setImgUrl(imgUrl);
                entity.setUtype(1);
                openId = StringUtil.isEmty(openId) ? EncryptUtil.Base64Encode(phone)+EncryptUtil.Base64Encode(new Date()+"") : openId;
                //测试 Openid
                entity.setOpenId(openId);
                //entity.setOpenId(openId);
                entity.setCreateDate(new Date());
                entity.setPhone(phone);
                entity.setAge(age);
                entity.setSex(sex);
                entity.setUserName(userName);
                boolean flag = this.save(entity);
                if (flag) {
                    return ApiJSON.data(entity);
                }
                return ApiJSON.error("注册失败");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ApiJSON.error(ex.getMessage());
        }
        return ApiJSON.error("注册失败");
    }
}
