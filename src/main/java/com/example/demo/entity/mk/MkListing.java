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
@Table(name = "mk_listing")
public class MkListing implements Serializable {

  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private String unitType;
  private String userId;
  private Date createDate;
  private long fidentity;
  private String remark;
  private Integer fileId;
  private double area;
  private long leaseType;
  private String labels;
  private String decoration;
  private String towards;
  private String supporting;
  private String features;
  private String expectations;
  private Long apartmentId;
  private Long rentalId;
  private Long addrId;
  private String yPay;
  private String jPay;
  private String nPay;
  private String bPay;
  private Integer fstatus;
  private Integer jstatus;
  private Integer tstatus;
  private Integer sortNo;
}
