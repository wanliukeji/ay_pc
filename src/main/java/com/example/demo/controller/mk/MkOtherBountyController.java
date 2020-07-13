package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkOtherBountyApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkOtherBountyService;
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
public class MkOtherBountyController implements MkOtherBountyApi {


    @Resource(name = "mkOtherBountyService")
    private MkOtherBountyService service;

    @Override
    public ResultJSON<?> add(String fname, BigDecimal amount, Integer fstatus, String remark, Integer ftype) {
        return service.add(fname, amount, fstatus, remark, ftype);
    }

    @Override
    public ResultJSON<?> getInfo(Integer id) {
        return service.getInfo(id);
    }

    @Override
    public ResultJSON<?> list(String userId, Integer ftype) {
        return service.list(userId, ftype);
    }
}
