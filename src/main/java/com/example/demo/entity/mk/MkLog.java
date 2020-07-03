package com.example.demo.entity.mk;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "mk_log")

public class MkLog implements Serializable {



  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private long creadCode;
  private String operation;
  private String clientIp;
  private java.sql.Timestamp createtime;
  private long lstatus;
  private String details;
  private String remark;

}
