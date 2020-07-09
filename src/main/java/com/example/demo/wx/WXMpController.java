package com.example.demo.wx;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.mk.MkUser;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@Api(value = "微信登录模块",description = "微信登录模块")
public class WXMpController {
    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 你的appid
    public static final String WX_LOGIN_APPID = "xxxxxxxxxxxxxxxxxx";
    // 你的密匙
    public static final String WX_LOGIN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

    @Autowired
    private MkUserService userService;


    @PostMapping("/api/mk/wx/login")
    public ResultJSON<?> user_login(
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
    ){
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
        MkUser user = userService.getUseroForOpenId(open_id);
        if(user != null){
            userService.updateById(user);
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
            boolean flag = userService.save(entity);
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
    }
}