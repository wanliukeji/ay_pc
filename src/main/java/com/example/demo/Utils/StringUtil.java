package com.example.demo.Utils;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 字符工具类
 */
public class StringUtil{

    public static String toString(Object data){
        if (null != data){
            return data+"";
        }
        return "";
    }

    /**
     * 判断是否为空
     * @param data
     * @return
     */
    public static boolean isNotEmty(Object data){

        if (null != data || !"".equals(data)){
            return true;
        }
        return false;
    }
}
