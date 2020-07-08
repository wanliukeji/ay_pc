package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
//@Table(name = "fied")
public class Fied implements Serializable {

    @Getter
    private static final long serialVersionUID = -6775222679549090142L;

    @Id
    @TableId(type = IdType.AUTO)
    @Column
    private Long id;

//    @Column(name = "headline")
    private String head_line;

    private String telephone;

    private String coverage;

    private String user_qq;

    private String user_wx;

    private String phone;

    private String link;

    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private Date createDate;

    private String company_name;

    private Integer procode;

    private Integer citycode;

    private Integer countycode;

    private String video_url;

    private String details;

    private String address;

    private Integer status;

    private Integer del;

    private String service;

    private String fw;

    private String type;

    private String userId;

    private String price;

    private String msgCode;

    private String timeNum;

}
