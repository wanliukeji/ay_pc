package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkPayApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
public class MkPayController implements MkPayApi {

    @Autowired
    private MkPayService service;

    @Override
    public ResultJSON<?> add(String name, Double amount, String fid, Double kdCosts, Double dCosts, Double sCosts,
                             Double wyCosts, Double tcCosts, Double rqCosts, Double sAmount, String userId, String remark,String zType) {
        return service.add(name, amount, fid, kdCosts, dCosts, sCosts, wyCosts, tcCosts, rqCosts, sAmount, userId, remark, zType);
    }

    @Override
    public ResultJSON<?> page(String val, Integer limit, Integer row) {
        return service.page(val, limit, row);
    }

    @Override
    public ResultJSON<?> getInfo(String zType, String name, Integer fid) {
        return service.getInfo(zType, name , fid);
    }
}
