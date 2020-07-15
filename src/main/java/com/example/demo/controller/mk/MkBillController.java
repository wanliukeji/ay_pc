package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkBillApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkBillService;
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
 * @describe 房屋设施配套
 */
@RestController
@Slf4j
public class MkBillController implements MkBillApi {

    @Resource
    private MkBillService service;

    @Override
    public ResultJSON<?> add(String uid, String payUid, Integer pm, String title, String ftype, BigDecimal amount, String mark, String fid) {
        return service.add(uid, pm, title, ftype, amount, mark, fid);
    }

    @Override
    public ResultJSON<?> page(String uid, Integer limit, Integer row) {
        return service.page(uid, limit, row);
    }

    @Override
    public ResultJSON<?> del(Integer id) {
        return service.del(id);
    }

    @Override
    public ResultJSON<?> getInfo(Integer id) {
        return service.getInfo(id);
    }
}
