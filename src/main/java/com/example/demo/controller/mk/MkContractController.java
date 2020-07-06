package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkContractApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class MkContractController implements MkContractApi {

    @Resource(name = "mkContractService")
    private MkContractService service;

//    @Override
//    public ResultJSON<?> gen() {
//        return service.gen();
//    }


    @Override
    public ResultJSON<?> gen(String zTime, String startDate, String endDate, String fuid,
                             String zuid, String fileId, String addr, String fid) {
        return service.gen(zTime,startDate,endDate,fuid,zuid,fileId,addr,fid);
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
