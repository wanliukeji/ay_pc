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
@Table(name = "mk_opinion")
public class MkOpinion implements Serializable {


  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private String details;
  private String phone;
  private String uid;
  private Date creadDate;
  private String creadCode;

}
