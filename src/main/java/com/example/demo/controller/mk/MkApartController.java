package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkApartApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkApartService;
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
public class MkApartController implements MkApartApi {

    @Resource
    private MkApartService service;

    @Override
    public ResultJSON<?> add(String fileCode, String userId, Integer roomNum, String cityCode, String areaCode, String townCode,
                             String addr, String communityName, String x, String y) {
        return service.add(fileCode, userId, roomNum, cityCode, areaCode, townCode, addr, communityName, x, y);
    }

    @Override
    public ResultJSON<?> list(String communityName, String cityCode, String areaCode) {
        return service.list(communityName, cityCode, areaCode);
    }

    @Override
    public ResultJSON<?> page(String val, Integer limit, Integer row) {
        return service.page(val, limit, row);
    }

    @Override
    public ResultJSON<?> del(Integer id) {
        return null;
    }

    @Override
    public ResultJSON<?> edit(Integer id, Integer ftype, String name, String creatCode) {
        return null;
    }
}
