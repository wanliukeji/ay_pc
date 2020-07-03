package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkFshareApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkFshareService;
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
public class MkFShareController implements MkFshareApi {

    @Resource
    private MkFshareService service;


    @Override
    public ResultJSON<?> add(Integer ftype, String userId, String fid) {
        return service.add(ftype, userId, fid);
    }

    @Override
    public ResultJSON<?> del(String userId, String fid) {
        return service.del(userId, fid);
    }

    @Override
    public ResultJSON<?> page(Integer limit, Integer row, Integer userId) {
        return service.page(limit, row, userId);
    }

//    @Override
//    public ResultJSON<?> page(String userId, Integer limit, Integer row) {
//        return service.page(userId, limit, row);
//    }
}
