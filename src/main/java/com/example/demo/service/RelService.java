package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.FileUploadTool;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.dao.FiedMapper;
import com.example.demo.entity.Fied;
import com.example.demo.entity.FileEntity;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

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
            while ((s = input.readLine()) != null) { // 判断是否读到了最后一行
                Fied fied = new Fied();
                String info[] = s.split("\t");
                if (info.length >= 12) {
                    fied.setHead_line(info[0]);
                    fied.setSercode(info[1]);
                    fied.setCoverage(info[2]);
                    fied.setDetails(info[3]);
                    fied.setLink(info[4]);
                    fied.setAddress(info[5]);
                    fied.setA_a_img(info[6]);
                    fied.setZ_a_img(info[7]);
                    fied.setA_b_img(info[8]);
                    fied.setZ_c_img(info[9]);
                    fied.setA_c_img(info[10]);
                    fied.setGg_src(info[11]);
                    fiedService.save(fied);
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
