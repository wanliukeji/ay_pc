package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkOpinionApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkOpinionService;
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
public class MkOpinionController implements MkOpinionApi {

    @Resource
    private MkOpinionService service;


    @Override
    public ResultJSON<?> add(String userId, String details, String phone) {
        return service.add(userId, details, phone);
    }
}
