package com.zmj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class TimeUtil {
    public static Date compareSystemTime() {//与系统时间进行比较
        while (true) {
            try {
                Scanner input;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                System.out.println("请输入开始时间：(1997-05-05 00:00:00进行输入)");
                Date datebegin;
                input = new Scanner(System.in);
                String begintime = InputUtil.getInputByTime(input);
                datebegin = df.parse(begintime);
                System.out.println(datebegin);
                String systemTime = df.format(new Date());// new Date()为获取当前系统时间
                String nowTime = df.format(datebegin);//输入时间
                if (systemTime.compareTo(nowTime) < 0) {
                    return datebegin;
                } else {
                    System.out.println("不能在系统时间前！请重新输入！");
                }
            } catch (ParseException e) {
                System.out.println("时间输入格式有误，请重新输入！");
            }
        }
    }


    public static Date getEndTime(Date beginTime,int time) {//获得结束时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(beginTime);
        nowTime.add(Calendar.MINUTE, time);//要增加什么，在这里写
        System.out.println("结束时间为：" + df.format(nowTime.getTime()));
        return nowTime.getTime();
    }

    public static boolean compareTime(Date Time1, Date Time2) {//比较开始和结束时间是否在这个影厅所在时间内
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String begin = df.format(Time1);//输入时间
        String end = df.format(Time2);//输入时间
        if (begin.compareTo(end)<=0) {
            return true;//存在
        } else {
            return false;//不存在
        }
    }

    public static Date getSystemTime(){//获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date time=new Date();
        String time1=df.format(time);
        try {
            time=df.parse(time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

}
