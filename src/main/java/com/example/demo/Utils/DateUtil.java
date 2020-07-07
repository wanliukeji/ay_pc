package com.example.demo.Utils;

import cn.hutool.core.date.format.FastDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/25 23:49
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 时间工具类
 */
public class DateUtil {


    private static FastDateFormat formatter = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    private static DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
    private static DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat formatter3 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static DateFormat formatter4 = new SimpleDateFormat("MM-dd");
    public static final SimpleDateFormat date_sdf = new SimpleDateFormat(
            "yyyy-MM-dd");
    public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");


    private static Calendar gregorianCalendar = null;

    /**
     * 日期转字符串(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return formatter.format(date);
    }

    /**
     * 日期转字符串(yyyyMMdd)
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatter1.format(date);
    }

    /**
     * 日期转字符串(yyyyMMdd)
     *
     * @param date
     * @return
     */
    public static String formatDate2(Date date) {
        return formatter2.format(date);
    }

    /**
     * 字符串转日期
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parse(String date) throws ParseException {
        return dateFormatter.parse(date);
    }

    /**
     * 按照指定格式进行字符串转日期
     *
     * @param str
     * @param sdf
     * @return
     */
    public static Date str2Date(String str, SimpleDateFormat sdf) {
        if (null == str || "".equals(str)) {
            return null;
        }
        Date date = null;
        try {
            date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将sql date 转为 字符串
     *
     * @param date
     * @return
     */
    public static String sql2str(java.sql.Date date) {
        if (date == null) {
            return "";
        }
        return formatter2.format(date);
    }


    public static String getNowDate() {
        return dateFormatter.format(new Date());
    }

    /**
     * 提供日志产生的时间
     *
     * @return
     */
    public static String logDate() {
        return "[" + format(new java.util.Date()) + "]: ";
    }

    /**
     * 获取当前月的最后一天
     *
     * @param date
     * @return
     */
    public static String lastDayOfMonth(Date date) {
        gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        gregorianCalendar.roll(Calendar.DAY_OF_MONTH, -1);
        return formatter2.format(gregorianCalendar.getTime());
    }

    /**
     * 获取当前月的第一天
     *
     * @return
     */
    public static String getFirstDay(Date date) {
        gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.add(Calendar.MONTH, 0);
        gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
        return formatter2.format(gregorianCalendar.getTime());
    }

    /**
     * @param firstDate
     * @param secondDate
     * @return
     * @Description:判断<firstDate>时间点是否在<secondDate>时间点之前 如果此 firstDate 的时间在参数<secondDate>表示的时间之前，则返回小于 0 的值
     * @ReturnType boolean
     * @author:
     * @Created 2012 2012-9-20上午08:40:33
     */
    public static boolean isBefore(Date firstDate, Date secondDate) {

        return compare(firstDate, secondDate) < 0 ? true : false;
    }

    /**
     * @param firstDate
     * @param secondDate
     * @Description:判断<firstDate>时间点是否在<secondDate>时间点之后 如果此 firstDate 的时间在参数<secondDate>表示的时间之后，则返回大于 0 的值
     * @ReturnType boolean
     * @author:
     * @Created 2012 2012-9-20上午08:38:48
     */
    public static boolean isAfter(Date firstDate, Date secondDate) {

        return compare(firstDate, secondDate) > 0 ? true : false;
    }

    /**
     * @param firstDate
     * @param secondDate
     * @Description:比较两个时间点是否相等
     * @ReturnType boolean
     * @author:
     * @Created 2012 2012-9-20上午08:37:30
     */
    public static boolean isEqual(Date firstDate, Date secondDate) {

        return compare(firstDate, secondDate) == 0 ? true : false;
    }

    /**
     * @param firstDate
     * @param secondDate
     * @Description:比较两个时间点 如果secondDate表示的时间等于此 firstDate 表示的时间，则返回 0 值；
     * 如果此 firstDate 的时间在参数<secondDate>表示的时间之前，则返回小于 0 的值；
     * 如果此 firstDate 的时间在参数<secondDate>表示的时间之后，则返回大于 0 的值
     * @ReturnType int
     * @author:
     * @Created 2012 2012-9-20上午08:34:33
     */
    public static int compare(Date firstDate, Date secondDate) {

        Calendar firstCalendar = null;
        /**使用给定的 Date 设置此 Calendar 的时间。**/
        if (firstDate != null) {
            firstCalendar = Calendar.getInstance();
            firstCalendar.setTime(firstDate);
        }

        Calendar secondCalendar = null;
        /**使用给定的 Date 设置此 Calendar 的时间。**/
        if (firstDate != null) {
            secondCalendar = Calendar.getInstance();
            secondCalendar.setTime(secondDate);
        }

        try {
            /**
             * 比较两个 Calendar 对象表示的时间值（从历元至现在的毫秒偏移量）。
             * 如果参数表示的时间等于此 Calendar 表示的时间，则返回 0 值；
             * 如果此 Calendar 的时间在参数表示的时间之前，则返回小于 0 的值；
             * 如果此 Calendar 的时间在参数表示的时间之后，则返回大于 0 的值
             * **/
            return firstCalendar.compareTo(secondCalendar);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 两个时间相差几个月
     *
     * @return
     * @throws ParseException
     */
    public static int getMonthSpace(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }

    /**
     * 获取当前时间指定前面多少小时的时间
     *
     * @return
     */
    public static String getBeforeHourTime(int hour) {
        gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, gregorianCalendar.get(Calendar.HOUR_OF_DAY) - hour);
        return dateFormatter.format(gregorianCalendar.getTime());
    }


    /**
     * 获取当前时间前一周的时间
     *
     * @return
     */
    public static String getBeforeWeek(int week) {
        gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(Calendar.WEEK_OF_MONTH, gregorianCalendar.get(Calendar.WEEK_OF_MONTH) - week);
        return dateFormatter.format(gregorianCalendar.getTime());
    }

    /**
     * 获取提前多少个月
     *
     * @return
     */
    public static String getBeforeMonth(int month) {
        gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(Calendar.MONTH, gregorianCalendar.get(Calendar.MONTH) - month);
        return dateFormatter.format(gregorianCalendar.getTime());
    }

    /**
     * 获取提前多少个年
     *
     * @return
     */
    public static String getBeforeYear(int year) {
        gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(Calendar.YEAR, gregorianCalendar.get(Calendar.YEAR) - year);
        return dateFormatter.format(gregorianCalendar.getTime());
    }


    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     *                yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String Date2TimeStamp(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format(sdf.parse(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();            //获取当前日期
        // Calendar.DAY_OF_YEAR:获取这一天是这一年中的第几天
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        String result = format.format(today);
        return result;
    }


    /**
     * 获取过去7天日期数组
     *
     * @return
     */
    public static String[] getPastDateArr() {
        String[] dateArr = new String[7];
        dateArr[0] = formatter4.format(new Date());
        for (int i = 1; i < 7; i++) {
            String date = getPastDate(i);
            dateArr[i] = date;
        }
        Arrays.sort(dateArr);   //从小到大升序排序
        return dateArr;
    }


    /**
     * 返回年月日时分秒数字格式
     *
     * @return
     */
    public static String getDateYMDHMS() {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }

    /**
     * 字符串转时间
     *
     * @return
     */
    public static Date getStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            // 注意格式需要与上面一致，不然会出现异常
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 字符串转时间
     *
     * @return
     */
    public static String getStringToYY_MM_DD(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 注意格式需要与上面一致，不然会出现异常
        return  sdf.format(time);
    }


    /**
     * 字符串转时间
     *
     * @return "yyyy年MM月dd日 HH时mm分ss秒"
     * "yy/MM/dd HH:mm"
     * "yyyy-MM-dd HH:mm:ss"
     * yyyy年MM月dd日 HH时mm分ss秒 E
     */
    public static Date getStringToDate(String time, String fomt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fomt);
        Date date = null;
        try {
            // 注意格式需要与上面一致，不然会出现异常
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String toLongDateString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        return myFmt.format(dt);
    }

    public static String toShortDateString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        return myFmt.format(dt);
    }

    public static String toLongTimeString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("HH mm ss SSSS");
        return myFmt.format(dt);
    }

    public static String toShortTimeString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return myFmt.format(dt);
    }

    public static String toShortTimeString24(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return myFmt.format(dt);
    }

    public static void main(String[] args) {
//        Date time = DateUtil.strToDateLong("2019-02-10");
    }

    /**
     * yyyy-MM-dd
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }



}
