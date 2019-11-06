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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        //System.out.println("左侧菜单内容" + title);
    }

    public static void main(String[] args) {
//        String url = "https://nb.58.com/jiancai/39224222038933x.shtml?link_abtest=&psid=188135889206181254622851042&entinfo=39224222038933_z&slot=1000945&iuType=z_2&PGTID=0d360415-0008-719e-0672-72d74295ec7f&ClickID=1&adtype=3";
        String url = "https://nb.58.com/huangyezonghe/?key=%E9%97%A8%E7%AA%97&cmcskey=%E9%97%A8%E7%AA%97&final=1&jump=1&specialtype=gls&classpolicy=main_A,service_A";

        // 获取标题
//        String detail_title__name = getText(url, "detail-title__name");
////        String bigimg_info = getText(url, "bigimg-info");
////        String infocard__container__item__title = getText(url, "infocard__container__item__title");
////        String num_cont = getText(url, "num_cont");
////        String foldingbox = getText(url, "foldingbox");
////        String img = getImg(url);
////        String newpost_price__big = getText(url, "newpost-price__big");
////        String infocard__container__item__main = getText(url, "infocard__container__item__main");
////
////        System.out.println("标题:" + detail_title__name);
////        System.out.println("发布时间:" + bigimg_info);
////        System.out.println("发布时间:" + infocard__container__item__title);
////        System.out.println("号码:" + num_cont);
////        System.out.println("描述:" + foldingbox);
////        System.out.println("图片:" + img);
////        System.out.println("价格:" + newpost_price__big);
////        System.out.println("服务:" + infocard__container__item__main);

        String urls = getHref(url);
        System.out.println(" 超链接 :" + urls);

    }

    //   获取
    public static String getText(String url, String strName) {
        Elements elements = pareHtmlForClass(getHttpHtml(url), strName);
        String text = "";
        for (Element e : elements) {
            text += e.text();
        }
        return text;
    }

    //    获取图片
    public static String getImg(String url) {

        String htmlStr = getHttpHtml(url);
        List<String> list = new ArrayList<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        // String regEx_img = "<img.*src=(.*?)[^>]*?>";
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                list.add(m.group(1));
            }
        }
        return list.toString();
    }

    //    获取超链接
    public static String getHref(String url) {
        try {
            Document doc = Jsoup.parse(new URL(url), 1000);
            Elements hrefs = doc.select("a[href]");
            List<String> list = match(getHttpHtml(url), "a", "href");
            return list.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }

    }

    public static List<String> match(String source, String element, String attr) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s.*?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }
}
