package com.example.demo.service.mk;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.FileUploadTool;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkFileMapper;
import com.example.demo.entity.mk.MkFile;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/24 10:43
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Service
@Slf4j
public class MkFileService extends ServiceImpl<MkFileMapper, MkFile> {

    /**
     * 上传广告图片
     *
     * @param multipartFile
     */
    public ResultJSON<?> uploadfile(MultipartFile multipartFile, String userId) {
        HttpServletRequest request = HttpServletRequestUtil.getRequest();
        MkFile entity = new MkFile();
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(multipartFile, request);
            if (null != userId && entity != null) {
                entity.setCreadCode(userId);
                entity.setCreadDate(new Date());
                entity.setDel(1);
                this.saveOrUpdate(entity);
                return ResultJSON.success(entity);
            }
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }

    public List<Map<String,Object>> getInfos(String name, List<Map<String,Object>> list) {
        List item = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);
            if (StringUtil.isNotEmty(map.get(name))) {
                String []str = map.get(name).toString().split(",");
                for (int j = 0; j < str.length; j++) {
                    MkFile entity = this.getById(str[j]);
                    item.add(entity);
                }
                map.put(name, item);
            }
        }
        return  list;
    }


//    public MkListing getInfos(String name, MkListing mkListing) {
//            if (StringUtil.isNotEmty(mkListing)) {
//                String []str = map.get(name).toString().split(",");
//                for (int j = 0; j < str.length; j++) {
//                    MkFile entity = this.getById(str[j]);
//                    item.add(entity);
//                }
//                map.put(name, item);
//            }
//        }
//        return  list;
//    }
}


