package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 日志
 */
@Data
@Table (name = "sys_log")
public class SysLog {

  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private Long id;
  private String remoteAddr;
  private String requestUrl;
  private String methodName;
  private long logType;
  private String logName;
  private String bizName;
  private String description;
  private String requestData;
  private String className;
  private Date startTime;
  private Date endTime;
  private long executeTime;
  private String location;
  private String responseData;
  private String userId;

}
