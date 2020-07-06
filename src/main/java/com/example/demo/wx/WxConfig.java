package com.example.demo.wx;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.Data;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("wechat")
@Data
public class WxConfig {
    private String appid;
    private String appSecret;
    private String mchId;
    private String mchKey;
    private String keyPath;

    @Bean
    public WxMpInMemoryConfigStorage initStorage(){
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId(appid);
        config.setSecret(appSecret);
        return  config;
    }

    @Bean
    public WxMpService initWxMpService(){
        WxMpService wxMpService= new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(initStorage());
        return  wxMpService;
    }

    @Bean
    public WxPayService initWxPayService(){
         WxPayService wxPayService= new WxPayServiceImpl();
         WxPayConfig payConfig = new WxPayConfig();
         payConfig.setAppId(appid);
         payConfig.setMchId(mchId);
         payConfig.setMchKey(mchKey);
         payConfig.setKeyPath(keyPath);
         wxPayService.setConfig(payConfig);
        return  wxPayService;
    }

}

