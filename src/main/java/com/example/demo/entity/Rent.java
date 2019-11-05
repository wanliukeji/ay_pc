package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "rent")
public class Rent implements Serializable {

  @Getter
  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private Long id;

  @Column(name = "headline")
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

  private Integer procode;

  private Integer citycode;

  private Integer countycode;

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

  private String isExit;

  private String type;

}
