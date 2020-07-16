package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkSrinfoApi;
import com.example.demo.entity.mk.MkSrinfo;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkSrunfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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
public class MkSrinfoController implements MkSrinfoApi {

    @Autowired
    private MkSrunfoService service;


    @Override
    public ResultJSON<MkSrinfo> add(String ftype, String payType, String details, BigDecimal amount, String userId, String remark, String payId) {
        return service.add(ftype, payType, details, amount, userId, remark, payId);
    }

    @Override
    public ResultJSON<?> page(String val, String userId, Integer limit, Integer row) {
        return service.page(val, userId, limit, row);
    }

    @Override
    public ResultJSON<?> del(Integer id) {
        return service.del(id);
    }
}
