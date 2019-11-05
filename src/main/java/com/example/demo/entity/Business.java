package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table (name = "business")
public class Business {


  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private double balance;
  private String bankAccount;
  private String bankName;
  private String email;
  private String encodedPassword;
  private double frozenFund;
  private String idCard;
  private String idCardImage;
  private String identificationNumber;
  private String legalPerson;
  private String licenseImage;
  private String licenseNumber;
  private String mobile;
  private String name;
  private String organizationCode;
  private String organizationImage;
  private String phone;
  private java.sql.Timestamp safeKeyExpire;
  private String safeKeyValue;
  private String taxImage;
  private String username;
  private String attributeValue0;
  private String attributeValue1;
  private String attributeValue2;
  private String attributeValue3;
  private String attributeValue4;
  private String attributeValue5;
  private String attributeValue6;
  private String attributeValue7;
  private String attributeValue8;
  private String attributeValue9;
  private String attributeValue10;
  private String attributeValue11;
  private String attributeValue12;
  private String attributeValue13;
  private String attributeValue14;
  private String attributeValue15;
  private String attributeValue16;
  private String attributeValue17;
  private String attributeValue18;
  private String attributeValue19;

}
