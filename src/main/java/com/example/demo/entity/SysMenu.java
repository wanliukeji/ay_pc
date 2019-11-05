package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_menu")
@Data
public class SysMenu {

    @Getter
    private static final Long serialVersionUID = -6775222679549090142L;

    @Id
    @TableId(type = IdType.AUTO)
    @Column
    private Integer id;

    private String name;

    private Integer menuType;

    private String icon;

    private String href;

    private String navigation;

    private Integer parentId;

    private String permission;

    private Integer enable;

    private Integer sortNo;

    private String remark;

    private String createBy;

    private String updateBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")

    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private Date updateTime;

    private Integer delState;

    private Integer hasChildren;

    private String parentName;

    private Integer moduleId;

    private Integer level;

    private String className;

    private String path;

    private String recommend;

    private Integer target;

}
