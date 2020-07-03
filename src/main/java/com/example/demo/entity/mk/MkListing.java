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
  private String units;
  private String unitTypeA;
  private String unitTypeB;
  private String creadCode;
  private Date createDate;
  private String rentType;
  private long fidentity;
  private String rentCollection;
  private String remark;
  private String depositMethod;
  private String fileCodes;
  private double area;
  private long leaseType;
  private String labels;
  private double bounty;
  private String unitTypeC;
  private String longCode;
  private String payType;
  private String decoration;
  private String towards;
  private String supporting;
  private String features;
  private String expectations;
  private String hostType;
  private Long apartmentId;
  private Long rentalId;
  private Long addrId;
  private String yPay;
  private String jPay;
  private String nPay;
  private String bPay;
  private Date payTime;
  private Integer fstatus;
  private Integer jstatus;
  private Integer tstatus;
  private Integer sortNo;
  private Integer xyNo;
}
