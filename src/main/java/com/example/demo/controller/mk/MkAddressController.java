package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkAddressApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkAddressService;
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
public class MkAddressController implements MkAddressApi {

    @Autowired
    private MkAddressService service;

    @Override
    public ResultJSON<?> list() {
        return service.list();
    }

    @Override
    public ResultJSON<?> getInfo(String name) {
        return service.getInfo(name);
    }

    @Override
    public ResultJSON<?> getChilds(String code) {
        return service.getChilds(code);
    }
}
