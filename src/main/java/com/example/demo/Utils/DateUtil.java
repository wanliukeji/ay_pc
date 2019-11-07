package com.example.demo.Utils;

import java.text.DateFormat;
import java.text.ParseException;
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

    /**
     * 字符串转时间
     * @return
     */
    public static Date getStringToDate(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            // 注意格式需要与上面一致，不然会出现异常
            date = sdf.parse("2005-12-15 15:30:23");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
