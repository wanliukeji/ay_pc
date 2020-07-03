package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkWallkApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkWallkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe
 */
@RestController
@Slf4j
public class MkWallkController implements MkWallkApi {

    @Resource
    private MkWallkService service;


    @Override
    public ResultJSON<?> add(BigDecimal amount, String userId) {
        return service.edit(amount, userId, 1);
    }

    @Override
    public ResultJSON<?> edit(BigDecimal amount, String userId) {
        return service.edit(amount, userId, 2);
    }

    @Override
    public ResultJSON<?> getInfo(String userId) {
        return service.getInfo(userId);
    }
}
