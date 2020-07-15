package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkContractApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class MkContractController implements MkContractApi {

    @Autowired
    private MkContractService service;

    @Override
    public ResultJSON<?> gen(String addr, Double area,
                             String startDate, String endDate, String payDate,
                             Integer payDay, BigDecimal amount,
                             BigDecimal yamount, Integer startDay,
                             String fuid, String zuid, String fid, Integer payType) {
        return service.gen(addr,area,startDate,endDate,payDate,payDay,amount,yamount,startDay,fuid,zuid,fid, payType);
    }

    @Override
    public ResultJSON<?> list() {
        return null;
    }

    @Override
    public ResultJSON<?> page(String userId, Integer limit, Integer row) {
        return service.page(userId, limit, row);
    }
}
