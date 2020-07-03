package com.example.demo.entity.mk;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "mk_pointxy")
@Data
public class MkPointxy {

  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private String name;
  private String fid;
  private String X;
  private String Y;
  private Date creadDate;

  @TableField(exist = false)
  private Double dis;
}
