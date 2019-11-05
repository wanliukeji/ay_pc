package com.example.demo.HttpClient;

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
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/11/4 16:58
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
public class HttClientMain {

    /**
     * 爬取整个网页内容
     *
     * @param url 目标网址
     * @return 内容
     */
    public static String getHttpHtml(String url) {


        String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
                "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
                "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
                "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};


        String html = "";
        //1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        //2.创建get请求，相当于在浏览器地址栏输入 网址
        HttpGet request = new HttpGet(url);
        //反爬虫 伪装成浏览器
        request.setHeader("User-Agent", " Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
        //更换IP代理 反封杀
        HttpHost proxy = new HttpHost("112.85.168.223", 9999);
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        request.setConfig(config);

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

    /**
     * 解析获取页面内容
     *
     * @param html 页面内容
     * @param tag  页面标签名
     */
    public static Elements pareHtmlForTag(String html, String tag) {
        // 解析网页 得到文档对象
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByTag(tag); // 获取tag是a的所有DOM元素，数组
        return elements;
    }

    /**
     * 解析获取页面内容
     *
     * @param html      页面内容
     * @param className 页面类
     */
    public static Elements pareHtmlForClass(String html, String className) {
        // 解析网页 得到文档对象
        Document doc = Jsoup.parse(html);
        Elements elements = doc.getElementsByClass(className); // 获取tag是a的所有DOM元素，数组

        return elements;
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

    public static void main(String[] args) {
        String url = "https://nb.58.com/jiazhuang/38807150932633x.shtml?spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&utm_source=market&adtype=1&entinfo=38807150932633_q&adact=3&psid=122789251206157578926992444&iuType=q_2&link_abtest=&ClickID=1&PGTID=0d30678d-0008-74b6-488a-876450699bb3&slot=1000961";
//         获取标题
        Elements elements = pareHtmlForClass(getHttpHtml(url), "detail-title__name");
        for (Element e : elements) {
            System.err.println(e.text());
        }
//     获取发布时间
//     Elements elements = pareHtmlForClass(getHttpHtml(url), "bigimg-info");
//     信息标题
//      Elements elements = pareHtmlForClass(getHttpHtml(url), "infocard__container__item__title");
        for (Element e : elements) {
            System.err.println(e.text());
        }
//      信息
        Elements elementes = pareHtmlForClass(getHttpHtml(url), "infocard__container__item__main");
//     电话号码
//     Elements elementes = pareHtmlForClass(getHttpHtml(url), "num_cont");
//     描述
//     Elements elementes = pareHtmlForClass(getHttpHtml(url), "foldingbox");
//        Elements elementes = pareHtmlForTag(getHttpHtml(url), "img");
        for (Element e : elementes) {
            System.err.println(e.text());
        }
//
    }
//     获取
//
}

