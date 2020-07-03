package com.example.demo.entity.mk;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "mk_facility")
public class MkFacility implements Serializable {

  private static final long serialVersionUID = -6775222679549090142L;

  @Id
  @TableId(type = IdType.AUTO)
  @Column
  private long id;
//  private long fid;
  /**
   * 1装修/2 费用/3 朝向/4配套/ 5特色
   */
  private Integer ftype;
  private String name;
  private Date createDate;
  private String creatCode;
//  @Column(name = "del" , columnDefinition = "1")
  private Integer del;


  @TableField(exist = false)
  private List children;

}
