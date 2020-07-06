package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

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
    @Getter
    private Long id;
    /**
     * 类型 (G 广告/ Z作品 /A 案例)
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

    /**
     * 用户
     */
    private String userId;

    private String msgCode;

    /**
     * 备用路径
     */
    private String imgUrl;

    /**
     * 信息编号
     */
    private Long fiedId;

}