package com.example.demo.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.entity.base.BaseEntity;
import lombok.Data;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/5/22 16:43
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Data
@TableName("user")
public class SysUser extends BaseEntity{
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
    private String businessId;
    private long userType;
    private long roleId;

}
