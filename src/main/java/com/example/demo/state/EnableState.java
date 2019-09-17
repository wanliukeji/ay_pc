package com.example.demo.state;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 16:56
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 请求实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnableState implements Serializable {

    private String name;

    private Integer value;
}
