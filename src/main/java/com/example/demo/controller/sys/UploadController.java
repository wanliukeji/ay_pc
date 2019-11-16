package com.example.demo.controller.sys;

import com.example.demo.Utils.DateUtil;
import com.example.demo.Utils.FileUploadTool;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.entity.FileEntity;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
//import com.example.demo.service.FileService;
import com.example.demo.service.FileService;
import io.lettuce.core.dynamic.annotation.Value;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

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
    public ResultJSON<FileEntity> uploadfile(@RequestParam(value = "fileName", required = false) MultipartFile multipartFile, String userId, String msgCode, String fiedId) {
        HttpServletRequest request = HttpServletRequestUtil.getRequest();
        FileEntity entity = new FileEntity();
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(multipartFile, request, Long.valueOf(fiedId));
            if (null != userId && entity != null) {
                entity.setUserId(userId);
                entity.setType("G");
                entity.setMsgCode(msgCode);
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
    public void uploadfile_z_img(@RequestParam(value = "fileName", required = false) MultipartFile multipartFile, String userId, String msgCode, String fiedId) {
        HttpServletRequest request = HttpServletRequestUtil.getRequest();
        FileEntity entity = new FileEntity();
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(multipartFile, request, Long.valueOf(fiedId));
            if (null != userId && entity != null) {
                entity.setUserId(userId);
                entity.setType("Z");
                entity.setMsgCode(msgCode);
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
    public void uploadfile_a_img(@RequestParam(value = "fileName", required = false) MultipartFile multipartFile, String userId, String msgCode, String fiedId) {
        HttpServletRequest request = HttpServletRequestUtil.getRequest();
        FileEntity entity = new FileEntity();
        FileUploadTool fileUploadTool = new FileUploadTool();
        try {
            entity = fileUploadTool.createFile(multipartFile, request, Long.valueOf(fiedId));
            if (null != userId && entity != null) {
                entity.setUserId(userId);
                entity.setType("A");
                entity.setMsgCode(msgCode);
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
    public ResultJSON<Boolean> uploadfile_txt(@RequestParam(value = "fileName", required = false) MultipartFile multipartFile, String fiedId) {
        BufferedReader input;
        HttpServletRequest req = HttpServletRequestUtil.getRequest();
        try {

            FileEntity entity = new FileEntity();
            FileUploadTool fileUploadTool = new FileUploadTool();
            entity = fileUploadTool.createFile(multipartFile, req, Long.valueOf(fiedId));

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

// --------------------------------------------upload-start----------------------------------------------------------------------------
// ERROR
    @RequestMapping(path = "/uploadfile_save", method = {RequestMethod.POST})
    @ApiOperation(value = "文件上传", notes = "文件上传")
    public ApiJSON addDish(@RequestParam("fileName") MultipartFile file) throws Exception {
        String path = null;// 文件路径
        double fileSize = file.getSize();
        System.out.println("文件的大小是" + fileSize);

        byte[] sizebyte = file.getBytes();
        System.out.println("文件的byte大小是" + sizebyte.toString());


        if (file != null) {// 判断上传的文件是否为空
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:" + fileName);
            HttpServletRequest request = HttpServletRequestUtil.getRequest();
            // 判断文件类型
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {// 判断文件类型是否为空

                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {

                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
                    // 设置存放图片文件的路径
                    String filePath = request.getSession().getServletContext().getRealPath("\\loadFile\\1") + DateUtil.getDateYMDHMS() + fileName;
                    System.out.println("项目的路径:" + filePath);

                    file.transferTo(new File(filePath));
                    System.out.println("文件成功上传到指定目录下");

                    return ApiJSON.data(filePath);

                }

            } else {
                System.out.println("不是我们想要的文件类型,请按要求重新上传");
                return ApiJSON.data("不是我们想要的文件类型,请按要求重新上传");
            }
        } else {
            System.out.println("文件类型为空");
            return ApiJSON.data("文件类型为空");
        }
        return ApiJSON.data("已经成功上传到指定目录");
    }
// --------------------------------------------upload-end----------------------------------------------------------------------------
}