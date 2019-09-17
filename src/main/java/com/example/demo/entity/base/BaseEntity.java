package com.example.demo.entity.base;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.enums.DeleteEnum;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/5/22 16:43
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -6775222679549090142L;
    /**
     * id
     */
    @Id
    @TableId(type = IdType.AUTO)
    @Column
    private Long id;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private Date updateTime;

    /**
     * 删除标记 --系统只做逻辑删除
     */
    @Column(length = 8)
    private Integer delState = DeleteEnum.FALSE.value();

    /**
     * 启用标记 --默认已启用
     */
    @Column(length = 8)
    private Integer enable = DeleteEnum.TRUE.value();

    /**
     * 描述
     */
    private String context;

}