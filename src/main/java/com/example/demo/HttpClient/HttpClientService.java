package com.example.demo.HttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/10/19 14:26
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Service
public class HttpClientService {

    /**
     * 爬取整个网页内容
     * @param url 目标网址
     * @return 内容
     */
    public static String getHttpHtml(String url) {
        String html = "";
        //1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //2.创建get请求，相当于在浏览器地址栏输入 网址
        HttpGet request = new HttpGet(url);
        //反爬虫 伪装成浏览器
        request.setHeader("User-Agent", " Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        //更换IP代理 反封杀
//        HttpHost proxy = new HttpHost("112.85.168.223", 9999);
//        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
//        request.setConfig(config);

        try {
            //3.执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);

            //4.判断响应状态为200，进行处理
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                html = EntityUtils.toString(httpEntity, "utf-8");
            } else {
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }

        return html;
    }


    public static void main(String[] args) {
        String url = "https://www.cnblogs.com/";
        url = "https://www.tuicool.com/";
        url = "https://www.csdn.net/";
        pareHtmlForTag(getHttpHtml(url), "a");
    }

    /**
     * 解析获取页面内容
     *
     * @param html 页面内容
     * @param tag  页面标签名
     */
    public static void pareHtmlForTag(String html, String tag) {
        // 解析网页 得到文档对象
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag(tag); // 获取tag是a的所有DOM元素，数组

        for (int i = 3; i < 17; ++i) {
            Element element = elements.get(i); // 获取第i个元素
            String title = element.text(); // 返回元素的文本
            System.out.println("左侧菜单内容" + title);
        }
    }

    /**
     * 解析获取页面内容
     *
     * @param html      页面内容
     * @param className 页面类
     */
    public static void pareHtmlForClass(String html, String className) {
        // 解析网页 得到文档对象
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByClass(className); // 获取tag是a的所有DOM元素，数组

        for (int i = 3; i < 17; ++i) {
            Element element = elements.get(i); // 获取第i个元素
            String title = element.text(); // 返回元素的文本
            System.out.println("左侧菜单内容" + title);
        }
    }

    /**
     * 解析获取页面内容
     *
     * @param html 页面内容
     * @param id   页面 ID
     */
    public static void pareHtmlForId(String html, String id) {
        // 解析网页 得到文档对象
        Document doc = Jsoup.parse(html);
        Element element = doc.getElementById(id); // 获取tag是a的所有DOM元素，数组

        String title = element.text(); // 返回元素的文本
        System.out.println("左侧菜单内容" + title);
    }
}
