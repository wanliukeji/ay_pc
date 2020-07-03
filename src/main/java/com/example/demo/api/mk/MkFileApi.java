package com.example.demo.api.mk;

import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 收入明细接口
 */

@Api(value = "MK图片上传模块", description = "MK图片上传模块")
public interface MkFileApi extends Serializable {

    /**
     * 文件上传
     * @return
     */
    @ApiOperation(value = "MK文件上传接口", notes = "MK文件上传接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "fileName", value = "文件"),
//            @ApiImplicitParam(name = "userId", value = "用户ID")
//    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/mkfile/upload")
    @ResponseBody
    public ResultJSON<?> upload(
            @RequestParam(value = "fileName", required = false) MultipartFile multipartFile,
            @RequestParam(value = "userId", required = false) String userId);
}
