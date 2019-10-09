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

    /**
     * 返回年月日时分秒数字格式
     * @return
     */
    public static String getDateYMDHMS(){
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }
}
