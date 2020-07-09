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
public class MkAddr implements Serializable {
  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
  private String proCode;
  private String cityCode;
  private String areaCode;
  private String comName;
  private String addrName;
  private String dong;
  private Integer floors;
  private Integer floorSum;
  private String roomNo;
  private Date creatDate;
  private String userId;
  private Integer del;
  private Integer unit;
}
