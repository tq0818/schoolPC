package com.yuxin.wx.controller.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.yuxin.wx.utils.DateUtil;

public class DateTest {
	public static void main(String[] args){
		Date startDate = new Date();
		//将开始日期至结束日期都做为Key
		List<String> dateKey = new ArrayList<String>();
		String startDateStr = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
		
		dateKey.add(startDateStr);
		//共查询三周的教室时间
		for(int i = 0; i < 20; i++){
			Date date = DateUtil.addDate(startDate, 1);
			startDate = date;
			dateKey.add(new SimpleDateFormat("yyyy-MM-dd").format(date));
		}
		
		for(int i = 0; i < dateKey.size(); i++){
			System.out.println(dateKey.get(i));
		}
		
		System.out.println(DateUtil.dayOfWeek(startDate));
	}
}
