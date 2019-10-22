package com.example.demo.controller;

import com.example.demo.api.FiedApi;
import com.example.demo.api.RentApi;
import com.example.demo.entity.Fied;
import com.example.demo.entity.Rent;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqFiedParam;
import com.example.demo.req.ReqParam;
import com.example.demo.service.FiedService;
import com.example.demo.service.RelService;
import com.example.demo.service.RentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 10:45
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe
 */
@RestController
@Slf4j
public class RentController implements RentApi {

    @Autowired
    private RentService rentService;

    @Override
    public ApiJSON saveRent(Rent entity) throws Exception {
        try {
            boolean flag = rentService.save(entity);
            return ApiJSON.data(flag);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ApiJSON.error(ex.getMessage());
        }
    }

}

