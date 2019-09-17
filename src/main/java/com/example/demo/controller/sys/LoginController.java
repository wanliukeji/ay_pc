package com.example.demo.controller.sys;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.api.LoginApi;
import com.example.demo.exception.CodeMsg;
import com.example.demo.entity.SysUser;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 登录控制器
 */
@RestController
@Slf4j
public class LoginController implements LoginApi {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录口
     * @param info
     * @return
     */
    @Override
    public ResultJSON<Boolean> login(SysUser info) {
        boolean flag = false;
        try {
            if (null != info){
                SysUser user = sysUserService.getOne(new QueryWrapper<SysUser>().eq("account",info.getAccount()).eq("password",info.getPassword()));
                if (null != user ){
                    //解密
                    //String pwd = EncryptUtil.Base64Decode(user.getPassword());
                    if ( info.getPassword().equalsIgnoreCase(user.getPassword())){
                        HttpServletRequestUtil.getRequest().getSession().setAttribute("user",user);
                        return ResultJSON.success(true);
                    }
                }else {
                    return ResultJSON.error(CodeMsg.SERVER_ERROR);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
            return ResultJSON.error(CodeMsg.SERVER_ERROR);
        }
        return ResultJSON.error(CodeMsg.SERVER_ERROR);
    }

}
