package com.example.demo.controller.sys;

import com.example.demo.Utils.FileUploadTool;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.entity.FileEntity;
import com.example.demo.entity.SysUser;
import com.example.demo.entity.Users;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
//import com.example.demo.service.FileService;
import com.example.demo.service.FileService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 文件上传工具
 */
@RestController
@Slf4j
@Api(value = "上传模块", description = "上传接口")
public class UploadController {

    @Autowired
    private FileService fileService;

    /**
     * 上传广告图片
     * @param multipartFile
     */
    @PostMapping(value = "/uploadfile")
    @ResponseBody
    public ResultJSON<FileEntity> uploadfile(@RequestParam(value = "fileName", required = false) MultipartFile multipartFile) {
        HttpServletRequest request = HttpServletRequestUtil.getRequest();
        FileEntity entity = new FileEntity();
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(multipartFile, request);
            if (entity != null) {
                fileService.save(entity);
                return ResultJSON.success(entity);
            }
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }

    /**
     * 上传作品图片
     * @param multipartFile
     */
    @PostMapping(value = "/uploadfile_z_img")
    @ResponseBody
    public ResultJSON<FileEntity> uploadfile_z_img(@RequestParam(value = "fileName", required = false) MultipartFile multipartFile) {
        HttpServletRequest request = HttpServletRequestUtil.getRequest();
        FileEntity entity = new FileEntity();
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(multipartFile, request);
            if (entity != null) {
                fileService.save(entity);
                return ResultJSON.success(entity);
            }
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }
}