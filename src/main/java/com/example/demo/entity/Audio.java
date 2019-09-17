package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.entity.base.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("audio")
public class Audio extends BaseEntity {

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
  @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
  private Date issueTime;
}

