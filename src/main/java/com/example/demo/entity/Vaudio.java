package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.entity.base.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/26 13:01
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 音频文件详情
 */
@Data
@TableName("v_audio_file")
public class Vaudio extends BaseEntity {

    /**
     * 名称
     */
    private String vname;

    /**
     * 导演
     */
    private String direct;

    /**
     * 主演
     */
    private String actor;

    /**
     * 地区
     */
    private String address;

    /**
     * 语言
     */
    private String lge;

    /**
     * 点赞
     */
    private Long vlike;

    /**
     * 分享
     */
    private Long vshare;

    /**
     * 评分
     */
    private Integer grade;

    /**
     * 用户
     */
    private long userid;

    /**
     * 视频文件ID
     */
    private long video_id;

    /**
     * 图片文件ID
     */
    private long img_id;

    /**
     * 发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueTime;

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

    /**
     * 视频路径
     */
    private String v_path;

    /**
     * 图片路径
     */
    private String p_path;
}
