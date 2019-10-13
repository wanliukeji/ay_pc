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
import com.example.demo.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
     *
     * @param multipartFile
     */
    @PostMapping(value = "/uploadfile_g_img")
    @ResponseBody
    public ResultJSON<FileEntity> uploadfile(@RequestParam(value = "fileName", required = false) MultipartFile multipartFile,String userId) {
        HttpServletRequest request = HttpServletRequestUtil.getRequest();
        FileEntity entity = new FileEntity();
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(multipartFile, request);
            if (null != userId && entity != null) {
                entity.setUserId(userId);
                entity.setType("G");
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
     *
     * @param multipartFile
     */
    @PostMapping(value = "/uploadfile_z_img")
    @ResponseBody
    public void uploadfile_z_img(@RequestParam(value = "fileName", required = false) MultipartFile multipartFile,String userId) {
        HttpServletRequest request = HttpServletRequestUtil.getRequest();
        FileEntity entity = new FileEntity();
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(multipartFile, request);
            if (null != userId && entity != null) {
                entity.setUserId(userId);
                entity.setType("Z");
                fileService.save(entity);

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
    }

    /**
     * 上传案例图片
     *
     * @param multipartFile
     */
    @PostMapping(value = "/uploadfile_a_img")
    @ResponseBody
    public void uploadfile_a_img(@RequestParam(value = "fileName", required = false) MultipartFile multipartFile,String userId) {
        HttpServletRequest request = HttpServletRequestUtil.getRequest();
        FileEntity entity = new FileEntity();
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(multipartFile, request);
            if (null != userId && entity != null) {
                entity.setUserId(userId);
                entity.setType("A");
                fileService.save(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
    }

    ;

    /**
     * 上传文件
     *
     * @param multipartFile
     */
    @ApiOperation(value = "文件导入接口", notes = "文件导入接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "文件", value = "fileName")
    })
    @PostMapping(value = "/uploadfile_txt")
    @ResponseBody
    public ResultJSON<Boolean> uploadfile_txt(@RequestParam(value = "fileName", required = false) MultipartFile multipartFile) {
        BufferedReader input;
        HttpServletRequest req = HttpServletRequestUtil.getRequest();
        try {

            FileEntity entity = new FileEntity();
            FileUploadTool fileUploadTool = new FileUploadTool();
            entity = fileUploadTool.createFile(multipartFile, req);

            String s = new String();
            input = new BufferedReader(new FileReader(entity.getPath()));
            while ((s = input.readLine()) != null) { // 判断是否读到了最后一行
                String info[] = s.split(" ");

                for (int i = 0; i < info.length; i++) {
                    System.out.print(info[i] + "\t");
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