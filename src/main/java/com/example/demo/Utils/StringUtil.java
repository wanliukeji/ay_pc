package com.example.demo.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 字符工具类
 */
public class StringUtil {

    public static String toString(Object data) {
        if (null != data) {
            return data + "";
        }
        return "";
    }

    /**
     * 判断是否为空
     *
     * @param data
     * @return
     */
    public static boolean isNotEmty(Object data) {

        if (null != data || !"".equals(data)) {
            return true;
        }
        return false;
    }

    ;

    public static Collection StringToList(String ids) {

        ArrayList listIds = new ArrayList();

        String [] items = ids.split(",");

        for (int i = 0; i < items.length; i++) {
             listIds.add(items[i]);
        }

        return (Collection)listIds;
    };

    public static ArrayList<Integer> StringToArrayList(String ids) {

        ArrayList listIds = new ArrayList();

        String [] items = ids.split(",");

        for (int i = 0; i < items.length; i++) {
            listIds.add(items[i]);
        }
        return listIds;
    };

}
