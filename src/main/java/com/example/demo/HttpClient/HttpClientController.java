package com.example.demo.HttpClient;

import java.io.IOException;
import java.util.List;

import com.example.demo.entity.Fied;
import com.example.demo.entity.FileEntity;
import com.example.demo.json.ApiJSON;
import com.example.demo.service.FiedService;
import com.example.demo.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;

import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/26 12:07
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 爬虫工具
 */
@RestController
public class HttpClientController implements HttpClientApi {

    @Autowired
    HttpClientService service;

    @Autowired
    FiedService fiedService;

    @Autowired
    FileService fileService;

    @Override
    @Transactional
    public ApiJSON get_WB(String url, String phone, String type) {
        try {
            Fied fied = service.get_WB_fied(url, phone, type);
            fiedService.save(fied);
            List<FileEntity> files = service.get_WB_file(url, fied);
            for (int i = 0; i < files.size(); i++) {
                fileService.save(files.get(i));
            }
            return ApiJSON.success("抓取成功");
        } catch (Exception ex) {
            return ApiJSON.error(ex.getMessage());
        }
    }
}