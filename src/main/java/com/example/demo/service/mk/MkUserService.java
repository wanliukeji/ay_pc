package com.example.demo.service.mk;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.EncryptUtil;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkUSerMapper;
import com.example.demo.entity.mk.MkUser;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.wx.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

//
//import com.tencentcloudapi.common.Credential;
//import com.tencentcloudapi.common.profile.ClientProfile;
//import com.tencentcloudapi.common.profile.HttpProfile;
//import com.tencentcloudapi.common.exception.TencentCloudSDKException;
//
//import com.tencentcloudapi.sms.v20190711.SmsClient;
//
//import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
//import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

import java.util.*;

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

    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 你的appid
    public static final String WX_LOGIN_APPID = "xxxxxxxxxxxxxxxxxx";
    // 你的密匙
    public static final String WX_LOGIN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

//
    public static final String SECRETID = "AKIDKOcdLoM0i9JUSEIez5tRpxoTtNabwp30";

    public static final String SECRETKEY = "qU9XeCTFhxve7JI0way6x1kEVXJr52C6";

    public static final String SENDURL = "sms.tencentcloudapi.com";

    // 账户密码 登录
    public ResultJSON<MkUser> login(String account, String password) {
        boolean flag = false;
        //解密
        try {
            if (null != account && password != null) {
                MkUser user = this.getOne(new QueryWrapper<MkUser>().eq("account", account).or().eq("uname", account).or().eq("phone", account));
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
        return ResultJSON.error(CodeMsg.LOGIN_ERROR);
    }

    //微信 一键登录
    public ResultJSON<?> wxlogin(
            @RequestParam("code") String code,
            @RequestParam( required = false, value = "email") String email,
            @RequestParam( required = false, value = "name") String name,
            @RequestParam( required = false, value = "userName") String userName,
            @RequestParam( required = false, value = "imgUrl") String imgUrl,
            @RequestParam( required = false, value = "sex") String sex,
            @RequestParam( required = false, value = "phone") String phone,
            @RequestParam( required = false, value = "iDcard") String iDcard,
            @RequestParam( required = false, value = "ctype") Integer ctype,
            @RequestParam( required = false, value = "openId") String openId,
            @RequestParam( required = false, value = "age") Integer age
    ) {
        try {
            // 配置请求参数
            Map<String, String> param = new HashMap<>();
            param.put("appid", WX_LOGIN_APPID);
            param.put("secret", WX_LOGIN_SECRET);
            param.put("js_code", code);
            param.put("grant_type", WX_LOGIN_GRANT_TYPE);
            // 发送请求
            String wxResult = HttpClientUtil.doGet(WX_LOGIN_URL, param);
            JSONObject jsonObject = JSONObject.parseObject(wxResult);
            // 获取参数返回的
            String session_key = jsonObject.get("session_key").toString();
            String open_id = jsonObject.get("openId").toString();
            // 根据返回的user实体类，判断用户是否是新用户，不是的话，更新最新登录时间，是的话，将用户信息存到数据库
            MkUser user = getUseroForOpenId(open_id);
            if(user != null){
                this.updateById(user);
            }else{
                MkUser entity = new MkUser();
                System.out.println("entity:"+entity.toString());
                // 添加到数据库
                entity.setUname(name);
                entity.setIDcard(iDcard);
                entity.setUtype(ctype);
                entity.setEmail(email);
                entity.setImgUrl(imgUrl);
                entity.setOpenId(openId);
                entity.setCreateDate(new Date());
                entity.setPhone(phone);
                entity.setAge(age);
                entity.setSex(sex);
                entity.setUserName(userName);
                boolean flag = this.save(entity);
                if(flag){
                    // 封装返回小程序
                    Map<String, Object> result = new HashMap<>();
                    result.put("session_key", session_key);
                    result.put("open_id", open_id);
                    result.put("entity", entity);
                    return ResultJSON.success(result);
                }
                return ResultJSON.error("登录失败");
            }
            return ResultJSON.error("登录失败");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error("登录失败");
        }
    }


    /**
     * @param phone
     * @return void
     */
    public static ResultJSON<?> codelogin(String phone) {

//        SecretId: AKIDKOcdLoM0i9JUSEIez5tRpxoTtNabwp30

//        SecretKey:qU9XeCTFhxve7JI0way6x1kEVXJr52C6

//        SmsSdkAppid :1400399759

//        url : https://console.cloud.tencent.com/api/explorer?Product=sms&Version=2019-07-11&Action=SendSms&SignVersion=

        try {

            // 短信应用SDK AppID
            // 1400开头
            final Integer APP_ID = 1402126548;

            // 短信应用SDK AppKey
            String APP_KEY = "b67d0bf7876c1d42121ca561953532";

            Integer TEMPLATEID = 148464;

            // 需要发送短信的手机号码
            // String[] phoneNumbers = {"15212111830"};

            // 短信模板ID，需要在短信应用中申请
            //NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
            // 签名
            // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
            String smsSign = "觅客找房";

//            SmsSingleSender sSender = new SmsSingleSender(APP_ID, APP_KEY);
            //第一个参数0表示普通短信,1表示营销短信
//            SmsSingleSenderResult result = sSender.send(0, "86",
//                    phone,
//                    "为您的登录验证码，请于" + 10 + "分钟内填写。如非本人操作，请忽略本短信。", "", "");
//            if (result.result != 0) {
//                return ResultJSON.error(result.errMsg);
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error("发送失败");
        }
        return ResultJSON.success("验证码发送成功");
    }




    public ResultJSON<?> sendSms(String phone){
//        try{
//            Credential cred = new Credential(SECRETID, SECRETKEY);
//            HttpProfile httpProfile = new HttpProfile();
//            httpProfile.setEndpoint(SENDURL);
//
//            ClientProfile clientProfile = new ClientProfile();
//            clientProfile.setHttpProfile(httpProfile);
//
//            SmsClient client = new SmsClient(cred, "", clientProfile);
//
//            String params = "{\"PhoneNumberSet\":[\\/" + phone + "\"],\"TemplateID\":\"662375\",\"SmsSdkAppid\":\"1400399759\"}";
//            SendSmsRequest req = SendSmsRequest.fromJsonString(params, SendSmsRequest.class);
//
//            SendSmsResponse resp = client.SendSms(req);
//
//            System.out.println(SendSmsResponse.toJsonString(resp));
//        } catch (TencentCloudSDKException e) {
//            System.err.println(e.toString());
//            return ResultJSON.error("发送失败");
//        }
//        return ResultJSON.success("验证码发送成功");
//

//        try{
//            Credential cred = new Credential(SECRETID, SECRETKEY);
//            HttpProfile httpProfile = new HttpProfile();
//            httpProfile.setEndpoint(SENDURL);
//            ClientProfile clientProfile = new ClientProfile();
//            clientProfile.setHttpProfile(httpProfile);
//            SmsClient client = new SmsClient(cred, "", clientProfile);
//            //生成4位随机数
//            int code = (int) (Math.random() * 9000 + 1000);
//            String params = "{\"PhoneNumberSet\":[\"+86"+phone+"\"],\"TemplateID\":\"662375\",\"Sign\":\"用户你好\",\"TemplateParamSet\":[\""+code+"\"],\"SmsSdkAppid\":\"1400399759\"}";
//            SendSmsRequest req = SendSmsRequest.fromJsonString(params, SendSmsRequest.class);
//            SendSmsResponse resp = client.SendSms(req);
//            return ResultJSON.success(resp);
//        } catch (TencentCloudSDKException e) {
//            return ResultJSON.error(e.toString());
//        }
        return ResultJSON.error("发送失败");
    }







    // 用户退出
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

    public ResultJSON<?> edit(String email, String pwd, String name, String userName, String imgUrl, String sex, String phone, Integer ctype, Integer age) {
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
                MkUser entity = this.getOne(new QueryWrapper<MkUser>().eq("openId", openId));
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
                    pant.setCompnyChilds(StringUtil.isNotEmty(pant.getCompnyChilds()) ? pant.getCompnyChilds() + "," + entity.getId() : entity.getId() + "");
                    this.saveOrUpdate(pant);
                    return ResultJSON.success(entity);
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
                String[] ids = entity.getCompnyChilds().split(",");
                Collection id = CollectionUtils.arrayToList(ids);
                List<MkUser> item = (List) this.listByIds(id);
                for (int i = 0; i < item.size(); i++) {
                    MkUser user = item.get(i);
                    if (StringUtil.isNotEmty(user)) {
                        String pwd = EncryptUtil.Base64Decode(user.getPwd());
                        user.setPwd(pwd);
                    }

                }
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
            MkUser pant = this.getOne(new QueryWrapper<MkUser>().like("compnyChilds", userId));
            boolean flag = this.removeById(Integer.valueOf(userId));
            if (flag) {
                String str = pant.getCompnyChilds();
                if (StringUtil.isNotEmty(str)) {
                    String[] ids = str.split(",");
                    String val = "";
                    for (int i = 0; i < ids.length; i++) {
                        if (ids[i].equals(userId)) {
                            ids[i] = "";
                        }
                        val += ids.length > 0 ? ids[i] + "," : "";
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


