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
@Table(name = "mk_rental")
public class MkRental implements Serializable {

  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private String zqType;
  private String zqPay;
  private BigDecimal zAmount;
  private BigDecimal zyAmount;
  private BigDecimal sdAmount;
  private BigDecimal wxAmount;
  private BigDecimal kdCosts;
  private BigDecimal dCosts;
  private BigDecimal sCosts;
  private BigDecimal wyCosts;
  private BigDecimal tcCosts;
  private BigDecimal rqCosts;
  private BigDecimal firstAmount;
  private BigDecimal fAmount;
  private String fOther;
  private String userId;
  private Date creartDate;
  private long del;
  private long fstatus;
  private String deposit;
  private String otherDeposit;
  private Date payTime;
}
