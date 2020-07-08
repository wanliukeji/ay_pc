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
@Table(name = "mk_apartment")
public class MkApartment implements Serializable {
  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private String fileCode;
  private String userId;
  private Date createDate;
  private Integer roomNum;
  private String cityCode;
  private String areaCode;
  private String townCode;
  private String communityName;
  private String x;
  private String y;
  private String addr;
  private Integer del;
  private Integer fstatus;

}
