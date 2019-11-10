package com.example.demo.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/10/9 17:09
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 返回临时对象
 */
@Data
public class RentVo implements Serializable {

    private Long id;

    private String headLine;

    private String telephone;

    private String msgCode;

    private String userQq;

    private String userWx;

    private String phone;

    private String link;

    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private java.sql.Timestamp createDate;

    private String companyName;

    private String procode;

    private String citycode;

    private String countycode;

    private String videoUrl;

    private String details;

    private String address;

    private Integer status;

    private Integer del;

    private String facility;

    private String transfer;

    private String pay;

    private String userId;

    private String price;

    private String area;

    private String haveOther;

    private String nature;

    private String path;

    private String isExit;

    private String type;
}
