package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 文件实体类 通用
 */
@Data
@TableName("file")
public class FileEntity {

    private static final long serialVersionUID = -6775222679549090142L;
    /**
     * id
     */
    @Id
    @TableId(type = IdType.AUTO)
    @Column
    private Long id;
    /**
     * 类型
     */
    private String type;

    /**
     * 大小
     */
    private String size;

    /**
     * 路径
     */
    private String path;

    /**
     *文件名
     */
    private String fileName;

    /**
     * 新文件名
     */
    private String nfileName;

    /**
     * 上传时间
     */
    private Timestamp uploadTime;
}