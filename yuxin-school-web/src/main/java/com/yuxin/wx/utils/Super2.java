package com.yuxin.wx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Super2{
 public static void main(String args[]){
  Calendar cal = Calendar.getInstance();
  
  Date date = null;
  String da = "2015-01-26";
  try {
	date = new SimpleDateFormat("yyyy-MM-dd").parse(da);
} catch (ParseException e) {
	e.printStackTrace();
}
//  //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
//  int n = 2;
//  String monday;
//  cal.setTime(date);
//  cal.add(Calendar.DATE, n*7);
//  //想周几，这里就传几Calendar.MONDAY（TUESDAY...）
//  cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
//  monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
  
//根据当前日期，获取上周的周一以及下周的周日
	Date startDate = DateUtil.getWeekOfDayByCurentDay(-1, 1, date);
	Date endDate = DateUtil.getWeekOfDayByCurentDay(2, 7, date);
	
	System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(startDate));
	System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(endDate));
 }
}
