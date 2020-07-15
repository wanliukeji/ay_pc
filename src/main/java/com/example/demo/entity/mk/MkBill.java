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
@Table(name = "mk_bill")
public class MkBill implements Serializable {
  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private String uid;
  private String fid;
  private long pm;
  private String title;
  private String category;
  private String ftype;
  private BigDecimal amount;
  private BigDecimal balance;
  private String mark;
  private long fstatus;
  private Date createDate;
  private long del;
  private Date payDate;
  private Long rentId;
}
