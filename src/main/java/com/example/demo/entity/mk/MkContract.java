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
@Table(name = "mk_contract")
public class MkContract implements Serializable {

  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private String zTime;
  private String startDate;
  private String endDate;
  private Date creatDate;
  private String fuid;
  private String zuid;
  private String fileUrl;
  private String addr;
  private String fid;
  private Integer del;
  private Double area;
  private String payDate;
  private Integer payDay;
  private BigDecimal amount;
  private BigDecimal yamount;
  private Integer startDay;
  private String payType;

}
