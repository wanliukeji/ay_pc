package com.example.demo.HttpClient;

import com.example.demo.Utils.ListUtil;
import io.swagger.models.HttpMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
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

import org.hibernate.validator.internal.util.privilegedactions.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //6.关闭
//            HttpClientUtils.closeQuietly(response);
//            HttpClientUtils.closeQuietly(httpClient);
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
    }

    public static void main(String[] args) {
        String url = "https://nb.58.com/jiancai/40077555335973x.shtml?spm=u-2d2yxv86y3v43nkddh1.BDPCPZ_BT&utm_source=market&adtype=1&entinfo=40077555335973_q&adact=3&psid=136300149206191784905597613&iuType=q_2&link_abtest=&ClickID=1&PGTID=0d3000ea-0008-baca-459b-8ad3187f4083&slot=1000616";
//        String name = get_WB_head_name(url);
//        String time = get_WB_time(url);
//        String link = get_WB_lk(url);
//        String addr = get_WB_addr(url);
//        String desc = get_WB_desc(url);
//        String price = get_WB_price(url);
        String fw = get_WB_fw(url);
//        String num = get_WB_num(url);

//        System.out.println("正在抓取取数据..........");
//        System.out.println("标题:" + name);
//        System.out.println("发布时间:" + time);
//        System.out.println("联系人:" + link);
//        System.out.println("地址:" + addr);
//        System.out.println("描述:" + desc);
//        System.out.println("价格:" + price);
        System.out.println("服务:" + fw);
//        System.out.println("图片:" + imgs);
//        System.out.println("手机号:" + num);
//        System.out.println("数据获取..........");

//        try {
//            List imgs = get_WB_imgs(url);
//            for (int i = 0; i < imgs.size(); i++) {
//                String img = imgs.get(i).toString();
//                System.out.println(img);
//                String fileName = img.substring(27, img.length()-12);
//                System.out.println(fileName);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
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

    //   获取
    public static List<?> getTextArr(String url, String strName) {
        ArrayList<Object> arr = ListUtil.ArrayList();
        Elements elements = pareHtmlForClass(getHttpHtml(url), strName);
        String text = "";
        for (Element e : elements) {
            arr.add(e.text());
        }
        return arr;
    }

    //    获取图片
    public static List<String> getImg(String url) {

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
        return list;
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

    //获取五八同城  标题 SUCCESS
    public static String get_WB_head_name(String url) {
        return getText(url, "detail-title__name");
    }

    //获取五八同城  时间 SUCCESS
    public static String get_WB_time(String url) {
        String str = getText(url, "bigimg-info");
//        return str.substring(0, str.indexOf("更") - 1).trim();
        return str.replace("更新","").replace("|","").trim();
    }

    //获取五八同城  价格 SUCCESS
    public static String get_WB_price(String url) {
        String str = getText(url, "newpost-price__big");
        return str;
    }

    //获取五八同城  服务 SUCCESS
    public static String get_WB_fw(String url) {
        List arr = getTextArr(url, "infocard__container__item__main");
        return arr.get(1) + "";
    }

    //获取五八同城  联系人 SUCCESS
    public static String get_WB_lk(String url) {
        List arr = getTextArr(url, "infocard__container__item__main");
        return arr.get(3) + "";
    }

    //获取五八同城  地址 SUCCESS
    public static String get_WB_addr(String url) {
        List arr = getTextArr(url, "infocard__container__item__main");
        return arr.get(4).toString();
    }

    //获取五八同城  手机号码 SUCCESS
    public static String get_WB_num(String url) {
        String str = getText(url, "free_tel ");
        return str;
    }

    //获取五八同城  商家描述 SUCCESS
    public static String get_WB_desc(String url) {
        String str = getText(url, "description_con");
        return str;
    }

    //获取五八同城  图片展示 SUCCESS
    public static List<Object> get_WB_imgs(String url) {
        List imgs = getImg(url);
        List<Object> jpgs = ListUtil.ArrayList();
        for (int i = 0; i < imgs.size(); i++) {
            String img = (String) imgs.get(i);
            if (img.contains("h=220")) {
                jpgs.add(img.replace("'", ""));
            }
        }
        return jpgs;
    }
}

