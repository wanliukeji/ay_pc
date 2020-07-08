package com.example.demo.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 错误页面的配置
 */
@Component
public class ErrorPageConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/error");
        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error");
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error");
        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error");
        registry.addErrorPages(error400Page,error401Page,error404Page,error500Page);
    }
}

