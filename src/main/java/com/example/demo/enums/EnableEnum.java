package com.example.demo.enums;

import com.example.demo.state.EnableState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/5/22 16:43
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@NoArgsConstructor
public enum EnableEnum {

    ENABLE(1, "启用"),

    LOCKED(2, "锁定");

    private Integer value;

    private String name;

    EnableEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer value() {
        return value;
    }

    public static List<EnableState> getStates() {
        List<EnableState> list = new ArrayList();
        EnableEnum[] array = EnableEnum.values();
        for (EnableEnum l1 : array) {
            list.add(new EnableState(l1.name, l1.value));
        }
        return list;
    }
}
