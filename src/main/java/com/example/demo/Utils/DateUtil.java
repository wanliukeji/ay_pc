package com.example.demo.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/25 23:49
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 时间工具类
 */
public class DateUtil {

    public static String getDate(){
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }
}
