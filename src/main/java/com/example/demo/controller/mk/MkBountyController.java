package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkBountyApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkBountyService;
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
public class MkBountyController implements MkBountyApi {

    @Resource
    private MkBountyService service;


    @Override
    public ResultJSON<?> add(Double proportion, String bountyType, String userId, String details, Integer fstatus) {
        return service.add(proportion, bountyType, userId, details, fstatus);
    }

    @Override
    public ResultJSON<?> list(String userId) {
        return service.list(userId);
    }
}
