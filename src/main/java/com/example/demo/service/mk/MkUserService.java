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
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;

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
                MkUser user = this.getOne(new QueryWrapper<MkUser>().eq("accout", account).or().or().eq("phone", account));
                if (null != user) {
                    // 判断是不是子账户
                   if (null != user && user.getUtype() == 4) {
                       //解密
                       String pwd = EncryptUtil.Base64Decode(user.getPwd());
                       if (password.equalsIgnoreCase(pwd)) {
                           HttpServletRequestUtil.setSessionUser(user);


                           return ResultJSON.success(user);
                       } else {
                           return ResultJSON.error(CodeMsg.LOGIN_ERROR);
                       }
                   } else {
                       //解密
                       String pwd = EncryptUtil.Base64Decode(user.getPwd());
                       if (password.equalsIgnoreCase(pwd)) {
                           HttpServletRequestUtil.setSessionUser(user);
                           return ResultJSON.success(user);
                       } else {
                           return ResultJSON.error(CodeMsg.LOGIN_ERROR);
                       }
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
        return ResultJSON.error(CodeMsg.LOGIN_ERROR);
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
                    user.setUname(name);

                    Integer type = (ctype == null || ctype == "") ? null : Integer.valueOf(ctype);
                    user.setUtype(type);
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

    public ResultJSON<?> edit(String email, String pwd, String name, String userName, String imgUrl, Integer sex, String phone, Integer ctype, Integer age) {
        try {
            MkUser entity = new MkUser();
            //加密
            pwd = EncryptUtil.Base64Encode(pwd);
            entity.setUname(name);
            entity.setUtype(ctype);
            entity.setEmail(email);
            entity.setImgUrl(imgUrl);
            entity.setCreateDate(new Date());
            entity.setPhone(phone);
            entity.setAge(age);
            entity.setSex(sex);
            entity.setUserName(userName);
            boolean flag = this.save(entity);
            if (flag) {
                return ResultJSON.success(entity);
            }
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        }
    }

    public ResultJSON<MkUser> getUserInfo(String userId) {

        try {
            if (StringUtil.isNotEmty(userId)) {
                MkUser entity = this.getById(userId);
                if (entity != null) {
                    return ResultJSON.success(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
        return ResultJSON.error(CodeMsg.QUERY_ERROR);
    }

    public MkUser getUseroForOpenId(String openId) {

        try {
            if (StringUtil.isNotEmty(openId)) {
                MkUser entity = this.getOne(new QueryWrapper<MkUser>().eq("openId",openId));
                if (entity != null) {
                    return entity;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
        return null;
    }

    public ResultJSON<?> addChilds(String userId, String name, String phone, String pwd) {
        try {
            MkUser pant = this.getById(userId);

            MkUser user = this.getOne(new QueryWrapper<MkUser>().eq("phone", phone));
            if (null != user) {
                return ResultJSON.error("该手机已注册");
            }

            if (null != pant) {
                MkUser entity = new MkUser();
                //加密
                pwd = EncryptUtil.Base64Encode(pwd);
                entity.setPwd(pwd);
                entity.setUname(name);
                entity.setCreateDate(new Date());
                entity.setPhone(phone);
                entity.setUtype(4);
                entity.setPOpenid(pant.getOpenId());
                boolean flag = this.save(entity);
                if (flag) {
                    pant.setCompnyChilds(StringUtil.isNotEmty(pant.getCompnyChilds()) ? pant.getCompnyChilds() + ","+ entity.getId() : entity.getId() + "");
                    this.saveOrUpdate(pant);
                    return ResultJSON.success(pant);
                }
            }
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        }
    }

    public ResultJSON<?> getChilds(String userId) {

        try {
            MkUser entity = this.getById(userId);
            if (null != entity && null != entity.getCompnyChilds()) {
                String [] ids = entity.getCompnyChilds().split(",");
                Collection id = CollectionUtils.arrayToList(ids);
                List item = (List) this.listByIds(id);
                return ResultJSON.success(item);
            }
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }

    public ResultJSON<?> delChilds(String userId) {

        try {
            MkUser pant =  this.getOne(new QueryWrapper<MkUser>().like("compnyChilds", userId));
           boolean flag = this.removeById(Integer.valueOf(userId));
           if (flag) {
                  String str = pant.getCompnyChilds();
                  if (StringUtil.isNotEmty(str)) {
                      String []ids = str.split(",");
                      String val = "";
                      for (int i = 0; i < ids.length ; i++) {
                          if (ids[i].equals(userId)) {
                              ids[i] = "";
                          }
                          val += ids.length > 0 ? ids[i]  + "," : "" ;
                      }

                      pant.setCompnyChilds(val);
                      this.saveOrUpdate(pant);
                      return ResultJSON.success(CodeMsg.UPDATE_SUCCESS);
                  }
              }
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }
}


