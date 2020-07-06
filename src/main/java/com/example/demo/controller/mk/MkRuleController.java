package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkRuleApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 房屋设施配套
 */
@RestController
@Slf4j
public class MkRuleController implements MkRuleApi {

    @Resource
    private MkRuleService service;


    @Override
    public ResultJSON<?> add(Integer ftype, String userId, String details, String title) {
        return service.add(ftype, userId, details, title);
    }

    @Override
    public ResultJSON<?> list(Integer ftype) {
        return service.list(ftype);
    }
}
