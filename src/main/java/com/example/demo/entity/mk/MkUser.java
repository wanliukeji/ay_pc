package com.example.demo.entity.mk;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "mk_user")
public class MkUser  implements Serializable {


  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private Integer id;
  private String account;
  private String pwd;
  private String uname;
  private String userName;
  private String imgUrl;
  private String sex;
  private String phone;
  private String iDcard;
  private Date createDate;
  private Integer utype;
  private String email;
  private String openId;
  private String compnyName;
  private String compnyAddr;
  private Integer age;
  private String compnyChilds;
  private String pOpenid;
}
