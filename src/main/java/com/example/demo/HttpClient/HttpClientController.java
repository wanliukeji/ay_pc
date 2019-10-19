package com.example.demo.HttpClient;

import java.io.IOException;

import com.example.demo.json.ApiJSON;
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

    @Override
    public ApiJSON saveContext(String url) throws Exception {
        return ApiJSON.data(service.getHttpHtml(url));
    }
}