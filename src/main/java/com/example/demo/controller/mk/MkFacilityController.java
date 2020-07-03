package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkFacilityApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkFacilityService;
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
public class MkFacilityController implements MkFacilityApi {

    @Autowired
    private MkFacilityService service;

    @Override
    public ResultJSON<?> add(String ftype, String name, String creatCode) {
        return service.add(ftype, name ,creatCode);
    }

    @Override
    public ResultJSON<?> page(String val,Integer ftype, Integer limit, Integer row) {
        return service.page(val,ftype, limit, row);
    }

    @Override
    public ResultJSON<?> list() {
        return service.list();
    }

    @Override
    public ResultJSON<?> getList(Integer ftype) {
        return service.getList(ftype);
    }

    @Override
    public ResultJSON<?> del(Integer id) {
        return service.del(id);
    }

    @Override
    public ResultJSON<?> edit(Integer id, Integer ftype, String name, String creatCode) {
        return service.edit(id, ftype, name, creatCode);
    }
}
