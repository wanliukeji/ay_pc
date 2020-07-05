package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkContractApi;
import com.example.demo.json.ResultJSON;
import com.example.demo.service.mk.MkContractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class MkContractGen implements MkContractApi {

    @Resource
    private MkContractService service;

    @Override
    public ResultJSON<?> gen() {





        return service.gen();
    }
}
