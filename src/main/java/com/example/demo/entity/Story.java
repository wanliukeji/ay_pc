package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.entity.base.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/26 18:57
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Data
@TableName("story")
public class Story extends BaseEntity {

    /**
     * 名称
     */
    private String vname;

    /**
     * 作者
     */
    private String author;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private Date issueTime;
}
