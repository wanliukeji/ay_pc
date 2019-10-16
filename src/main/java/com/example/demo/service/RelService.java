package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.FileUploadTool;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.dao.FiedMapper;
import com.example.demo.entity.Fied;
import com.example.demo.entity.FileEntity;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 11:03
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Service
public class RelService extends ServiceImpl<FiedMapper, Fied> implements Serializable {

    @Autowired
    FiedService fiedService;

    @Transactional
    public ApiJSON<Boolean> uploadfile_txt(MultipartFile multipartFile) {
        BufferedReader input;
        HttpServletRequest req = HttpServletRequestUtil.getRequest();

        try {
            FileEntity entity = new FileEntity();
            FileUploadTool fileUploadTool = new FileUploadTool();
            entity = fileUploadTool.createFile(multipartFile, req);
            String s = new String();
            InputStreamReader isr = new InputStreamReader(new FileInputStream(entity.getPath()), "GBK");
            input = new BufferedReader(isr);
            int i = 0;
            while ((s = input.readLine()) != null) { // 判断是否读到了最后一行
                i++;
                Fied fied = new Fied();
                String info[] = s.split("\t");
                if (info.length >= 12) {
                    if (i != 1) { //忽略表头
                        fied.setHead_line(info[1]);
                        fied.setTelephone(info[2]);
                        fied.setCoverage(info[3]);
                        fied.setUser_qq(info[4]);
                        fied.setUser_wx(info[5]);
                        fied.setPhone(info[6]);
                        fied.setLink(info[7]);
                        fied.setCreateDate(new Date());
                        fied.setCompany_name(info[9]);
                        fied.setProcode(101);
                        fied.setCitycode(201);
                        fied.setCountycode(301);
                        fied.setVideo_url(info[13]);
                        fied.setDetails(info[21]);
                        fied.setAddress(info[22]);
                        fiedService.save(fied);
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
