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
@Table(name = "mk_bounty")
public class MkBounty implements Serializable {

  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private long fstatus;
  private double proportion;
  private String bountyType;
  private String userId;
  private Date createDate;
  private String details;
  private Integer del;
  private BigDecimal amount;
}
