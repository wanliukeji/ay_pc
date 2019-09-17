package com.example.demo.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/5/22 16:43
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Data
@TableName("log")
public class SysLog implements Serializable {

    private static final long serialVersionUID = -6775222679549090142L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private long id;
    private String remoteAddr;
    private String requestUrl;
    private String methodName;
    private long logType;
    private String logName;
    private String bizName;
    private String description;
    private String requestData;
    private String className;
    private java.util.Date startTime;
    private java.util.Date endTime;
    private long executeTime;
    private String location;
    private String responseData;
    private String userId;

}
