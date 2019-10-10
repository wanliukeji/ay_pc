package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.FileMapper;
import com.example.demo.entity.FileEntity;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 16:56
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 文件实现业务类
 */
@Service
@Slf4j
public class FileService extends ServiceImpl<FileMapper, FileEntity> implements Serializable {

    public ResultJSON<?> getInfos(String userId) {
        try {
            List <FileEntity> files = baseMapper.getInfoList(userId);
            return ResultJSON.success(files);
        } catch (Exception ex) {
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        }
    }

    public ResultJSON<?> gethoppyList(String text) {
        try {
            List <FileEntity> files = baseMapper.gethoppyList(text);
            return ResultJSON.success(files);
        } catch (Exception ex) {
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        }
    }
}
