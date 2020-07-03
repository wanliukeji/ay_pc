package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 程序入口
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.demo.dao")
@ComponentScan(basePackages = {
        "com.example.demo.*"})
@EnableSwagger2
@EnableCaching //redis 缓存机制
@EnableRedisHttpSession

//@EnableWebSocket
//开启WebSocket
// 禁用redis
//@SpringBootApplication(exclude = { RedisAutoConfiguration.class })
public class AnyiApplication extends  ServletInitializer{

    public static void main(String[] args) {

        SpringApplication.run(AnyiApplication.class, args);
    };

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AnyiApplication.class);
    };
}
