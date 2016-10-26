package com.xwysun.account.Utils;

import android.content.Context;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by xwysun on 2016/4/3.
 */
public class Utils {
    public static void toast(Context context,int resId){
        Toast.makeText(context,resId,Toast.LENGTH_SHORT).show();
    }
    public static void toast(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }
    public static long getTimeOfWeekStart(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        ca.set(Calendar.DAY_OF_WEEK, ca.getFirstDayOfWeek());
        return ca.getTimeInMillis();
    }

    public static long getTimeOfMonthStart(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        return ca.getTimeInMillis();
    }

    public static long getTimeOfYearStart(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        ca.set(Calendar.DAY_OF_YEAR, 1);
        return ca.getTimeInMillis();
    }
    public static long getTimeOfDayStart(){
        Date date = new Date();
        long l = 24*60*60*1000; //每天的毫秒数
        //date.getTime()是现在的毫秒数，它 减去 当天零点到现在的毫秒数（ 现在的毫秒数%一天总的毫秒数，取余。），理论上等于零点的毫秒数，不过这个毫秒数是UTC+0时区的。
        //减8个小时的毫秒值是为了解决时区的问题。
        return (date.getTime() - (date.getTime()%l) - 8* 60 * 60 *1000);
    }
    public static long getTimeOfLastMonthStart(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        ca.add(Calendar.MONTH,-2);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        return ca.getTimeInMillis();
    }
    public static Calendar getCalendarOfLastMonthStart(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        ca.add(Calendar.MONTH,-2);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        return ca;
    }
    public static String DoubletoString(Double d){
        return String.format("%.2f", d);
    }
}
