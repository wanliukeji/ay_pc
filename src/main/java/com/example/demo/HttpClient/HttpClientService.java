package com.example.demo.HttpClient;

import com.example.demo.Utils.DateUtil;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.entity.Fied;
import com.example.demo.entity.FileEntity;
import com.example.demo.json.ApiJSON;
import com.example.demo.service.FiedService;
import com.example.demo.service.FileService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

    private HttClientMain main = new HttClientMain();

    //获取五八同城 数据抓取
    public Fied get_WB_fied(String url, String phone, String type) throws Exception {
            String name = main.get_WB_head_name(url);
            String time = main.get_WB_time(url);
            String link = main.get_WB_lk(url);
            String addr = main.get_WB_addr(url);
            String desc = main.get_WB_desc(url);
            String price = main.get_WB_price(url);
            String fw = main.get_WB_fw(url);
            String userId = HttpServletRequestUtil.getSessionUser().getId();
            String msgCode = userId + DateUtil.getDateYMDHMS();
            Fied entity = new Fied();
            entity.setHead_line(name);
            entity.setPrice(price);
            entity.setCreateDate(DateUtil.getStringToDate(time));
            entity.setLink(link);
            entity.setAddress(addr);
            entity.setDetails(desc);
            entity.setFw(fw);
            entity.setUserId(userId);
            entity.setService(fw);
            entity.setTelephone(phone);
            entity.setCompany_name(name);
            entity.setMsgCode(msgCode);
            entity.setPhone(phone);
            if (!StringUtil.isEmty(type)) {
                entity.setType(StringUtil.get_Feid_type(name));
            } else {
                entity.setType(type);
            }
            return entity;
    }

    ;

    //获取五八同城 数据抓取
    public List<FileEntity> get_WB_file(String url, Fied fied) throws Exception {
        List imgs = main.get_WB_imgs(url);
        String time = main.get_WB_time(url);
        List files = new ArrayList();
        for (int i = 0; i < imgs.size(); i++) {
            FileEntity fileEntity = new FileEntity();
            String img = imgs.get(i).toString();
            fileEntity.setPath(img);
            fileEntity.setMsgCode(fied.getMsgCode());
            fileEntity.setUserId(fied.getUserId());
            fileEntity.setImgUrl(img);
            String fileName = img.substring(27, img.length()-12);
            fileEntity.setFileName(fileName);
            fileEntity.setNfileName(fileName);
            Timestamp ts = new Timestamp(new Date().getTime());
            fileEntity.setUploadTime(ts);
            fileEntity.setSize("100KB");
            fileEntity.setFiedId(fied.getId());
            if (i == 0) {
                fileEntity.setType("G");
            } else {
                fileEntity.setType("z");
            }
            files.add(fileEntity);

        }
        return  files;
    }

}
