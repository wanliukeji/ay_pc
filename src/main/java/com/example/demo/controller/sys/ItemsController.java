package com.example.demo.controller.sys;

import com.example.demo.Utils.ClassUtil;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.json.ApiJSON;
import com.example.demo.pojoUtil.Service;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 异常控制器
 */
@RestController
@Api(value = "类属性获取模块", description = "类属性获取接口")
public class ItemsController {

    @GetMapping("/getClassField")
    public ApiJSON getClassField() {
        try {
            List<?> list = ClassUtil.getFieldValueByNameToList(new Service());
            return ApiJSON.data(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ApiJSON.error(null);
        }

    }
}
