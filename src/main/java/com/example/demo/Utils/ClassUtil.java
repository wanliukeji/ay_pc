package com.example.demo.Utils;

import com.example.demo.entity.Fied;
import org.apache.tomcat.jni.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/26 12:07
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
public class ClassUtil {

    /**
     * 获取属性名数组
     */
    public static String[] getFiledName(String path) {
        try {
            Class clazz = Class.forName(path);
            // 获取属性
            Field[] fields = clazz.getDeclaredFields(); // 返回所有的属性
            String[] fieldNames = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fieldNames[i] = fields[i].getName();
            }
            return fieldNames;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
