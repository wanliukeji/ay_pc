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
@Table(name = "mk_srinfo")
public class MkSrinfo implements Serializable {
  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private String ftype;
  private String payType;
  private String deposit;
  private String rentType;
  private Date createDate;
  private String details;
  private String remark;
  private BigDecimal amount;
  private String userId;
  private Integer del;
  private String fid;
}
