package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_user")
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = -6775222679549090142L;

    @Id
    @TableId(type = IdType.AUTO)
    @Column
    private String id;

    private String account;
    private String password;
    private String salt;
    private String name;
    private String email;
    private String phone;
    private long sex;
    private String avatar;
    private long deptId;
    private long locked;
    private long enable;
    private String remark;

    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private Date updateTime;

    private String createBy;
    private String updateBy;
    private long delState;
    private String businessId;
    private long userType;
    private long roleId;
    private String imgUrl;

}
