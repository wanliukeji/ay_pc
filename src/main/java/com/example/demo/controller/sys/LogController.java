package com.example.demo.controller.sys;/*
package com.example.demo.controller.sys;

import com.example.demo.JSON.ApiJSON;
import com.example.demo.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

*/
/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 日志控制器
 */
/*

@RestController
@Slf4j
public class logController {

    @GetMapping("/login")
    public ModelAndView login() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>LOGIN<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return new ModelAndView("login");
    }

    @GetMapping(value = "/login/getDate")
    @Transactional
    public ApiJSON<SysUser> getDate(@RequestParam String name) {
        try {
            SysUser user = new SysUser();
            user.setPassword("111111111");
            user.setAccount("admin");
            user.setName("chenny");
            return ApiJSON.data(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
            return ApiJSON.error("错啦");
        }
    }
}
*/
