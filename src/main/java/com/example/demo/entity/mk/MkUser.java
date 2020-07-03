package com.example.demo.entity.mk;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "mk_user")

public class MkUser implements Serializable {


  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private String accout;
  private String pwd;
  private String name;
  private String userName;
  private String imgUrl;
  private Integer sex;
  private Integer age;
  private String phone;
  private String email;
  private String iDcard;
  private Date createDate;
  private long ctype;
  private long incomeId;
  private BigDecimal amount;
  private long empCode;
  private String openId;
  private String compnyName;
  private String compnyAddr;
  private String compnyCode;
  private String compnyChilds;

}
