package com.yuxin.wx.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @Description:(datetime工具类)
 * @author wang.zx
 * @date 2014-12-20 下午10:26:11
 * @version 1.0
 */
public class DatetimeUtil {

	public static final String LONG_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * @Description:( 功 能：返回当前日期 入口参数：String pattern: 返回当前日期的格式, 如YY-MM-DD,
	 *                YYYYMMDD)
	 * @author wang.zx
	 * @date 2014-12-20 下午10:26:59
	 * @version 1.0
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDate(String pattern) {

		String result;

		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		result = formatter.format(new Date());

		return result;

	}

	/**
	 * @Description:( 功 能：返回当前日期 返 回：String型的当前日期, 格式yyyymmdd )
	 * @author wang.zx
	 * @date 2014-12-20 下午10:29:11
	 * @version 1.0
	 * @return
	 */

	public static String getDate() {

		Calendar calendar = Calendar.getInstance();
		String strDate = "" + calendar.get(Calendar.DATE);
		String strMonth = "" + (calendar.get(Calendar.MONTH) + 1);

		if ((calendar.get(Calendar.MONTH) + 1) > 12) {
			strMonth = "01";
		}
		String strYear = "" + calendar.get(Calendar.YEAR);

		if (strDate.length() < 2) {
			strDate = "0" + strDate;
		}
		if (strMonth.length() < 2) {
			strMonth = "0" + strMonth;
		}
		String curDate = strYear + strMonth + strDate;
		return curDate;
	}

	/**
	 * @Description:( 功 能：返回当前时间 返 回：String型的当前日期, 格式hhmmss )
	 * @author wang.zx
	 * @date 2014-12-20 下午10:31:08
	 * @version 1.0
	 * @return
	 */
	public static String getTime() {

		Calendar calendar = Calendar.getInstance();

		String strHour = "" + calendar.get(Calendar.HOUR_OF_DAY);

		if (strHour.length() == 1)

			strHour = "0" + strHour;

		String strMinute = "" + calendar.get(Calendar.MINUTE);

		if (strMinute.length() == 1)

			strMinute = "0" + strMinute;

		String strSecond = "" + calendar.get(Calendar.SECOND);

		if (strSecond.length() == 1)

			strSecond = "0" + strSecond;

		String curTime = strHour + strMinute + strSecond;

		return curTime;

	}

	/**
	 * @Description:( 功 能：返回当前日期时间 返 回：String型的当前的日期时间, 格式yyyymmddhhmmss )
	 * @author wang.zx
	 * @date 2014-12-20 下午10:32:06
	 * @version 1.0
	 * @return
	 */
	public static String getDatetime() {

		Calendar calendar = Calendar.getInstance();

		String strDate = "" + calendar.get(Calendar.DATE);

		String strMonth = "" + (calendar.get(Calendar.MONTH) + 1);

		if ((calendar.get(Calendar.MONTH) + 1) > 12) {
			strMonth = "01";
		}

		String strYear = "" + calendar.get(Calendar.YEAR);
		String strHour = "" + calendar.get(Calendar.HOUR_OF_DAY);
		String strMinute = "" + calendar.get(Calendar.MINUTE);
		String strSecond = "" + calendar.get(Calendar.SECOND);

		if (strDate.length() < 2) {
			strDate = "0" + strDate;
		}
		if (strMonth.length() < 2) {
			strMonth = "0" + strMonth;
		}
		if (strHour.length() < 2) {
			strHour = "0" + strHour;
		}
		if (strMinute.length() < 2) {
			strMinute = "0" + strMinute;
		}
		if (strSecond.length() < 2) {
			strSecond = "0" + strSecond;
		}
		String curNow = strYear + "-" + strMonth + "-" + strDate + "  " + strHour + ":" + strMinute + ":" + strSecond;
		return curNow;
	}

	/**
	 * @Description:( 功 能：返回当前日期时间 返 回：String型的当前的日期时间, 格式yyyymmddhhmmss )
	 * @author wang.zx
	 * @date 2014-12-20 下午10:33:33
	 * @version 1.0
	 * @return
	 */
	public static String getCurrentDatetime() {

		Calendar calendar = Calendar.getInstance();

		String strDate = "" + calendar.get(Calendar.DATE);

		String strMonth = "" + (calendar.get(Calendar.MONTH) + 1);

		if ((calendar.get(Calendar.MONTH) + 1) > 12) {
			strMonth = "01";
		}
		String strYear = "" + calendar.get(Calendar.YEAR);
		String strHour = "" + calendar.get(Calendar.HOUR_OF_DAY);
		String strMinute = "" + calendar.get(Calendar.MINUTE);
		String strSecond = "" + calendar.get(Calendar.SECOND);
		String strHSecond = "" + calendar.get(Calendar.MILLISECOND);

		if (strDate.length() < 2)

			strDate = "0" + strDate;

		if (strMonth.length() < 2)

			strMonth = "0" + strMonth;

		if (strHour.length() < 2)

			strHour = "0" + strHour;

		if (strMinute.length() < 2)

			strMinute = "0" + strMinute;

		if (strSecond.length() < 2)

			strSecond = "0" + strSecond;

		String curNow = strYear + strMonth + strDate + strHour + strMinute + strSecond + strHSecond;

		return curNow;

	}

	/**
	 * @Description:( 功 能：返回当前日期 返 回：String型的当前日期, 格式yyyy-mm-dd )
	 * @author wang.zx
	 * @date 2014-12-20 下午10:35:06
	 * @version 1.0
	 * @return
	 */
	public static String getFormatDate() {

		GregorianCalendar gCalendar = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strFormatDate;
		try {
			strFormatDate = formatter.format(gCalendar.getTime());
			strFormatDate = strFormatDate.substring(0, 10);
		} catch (Exception ex) {
			System.out.println("errMsg:".concat(String.valueOf(String.valueOf(ex.toString()))));
			String s = null;
			return s;
		}
		return strFormatDate;
	}

	/**
	 * @Description:( 功 能：返回当前时间 返 回：String型的当前日期, 格式hh:mm:ss )
	 * @author wang.zx
	 * @date 2014-12-20 下午10:36:28
	 * @version 1.0
	 * @return
	 */
	public static String getFormatTime() {
		GregorianCalendar gCalendar = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strFormatTime;
		try {
			strFormatTime = formatter.format(gCalendar.getTime());
			strFormatTime = strFormatTime.substring(11, 19);
		} catch (Exception ex) {
			System.out.println("errMsg:".concat(String.valueOf(String.valueOf(ex.toString()))));
			String s = null;
			return s;
		}
		return strFormatTime;
	}

	/**
	 * @Description:( 功 能：返回当前日期时间 返 回：String型的当前的日期时间, 格式yyyy-MM-dd HH:mm:ss)
	 * @author wang.zx
	 * @date 2014-12-20 下午10:37:21
	 * @version 1.0
	 * @return
	 * @throws Exception
	 */
	public static String getFormatDatetime() throws Exception {
		GregorianCalendar gCalendar = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDateTime;
		try {
			strDateTime = formatter.format(gCalendar.getTime());
		} catch (Exception ex) {
			System.out.println("Error Message:".concat(String.valueOf(String.valueOf(ex.toString()))));
			String s = null;
			return s;
		}
		return strDateTime;
	}

	/**
	 * @Description:( 功 能：取出某一年的所有星期几的日期 入口参数：int year: 年份, YYYY格式 int week: 星期,
	 *                1~7 返 回：String型的当前的日期时间, 格式yyyy-MM-dd HH:mm:ss )
	 * @author wang.zx
	 * @date 2014-12-20 下午10:38:20
	 * @version 1.0
	 * @param year
	 * @param week
	 * @return
	 */
	public static String[] getYearWeek(int year, int week) {
		String result = "";
		Calendar cal = new GregorianCalendar(Locale.CHINESE);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date day = new Date(year - 1900, 0, 1);
		for (int i = 0; i < 366; i++) {
			cal.setTime(day);
			if (cal.get(Calendar.DAY_OF_WEEK) == week) {
				if (day.getYear() == year - 1900)
					result += "/" + formatter.format(day);
			}
			day.setDate(day.getDate() + 1);
			cal.setTime(day);
		}
		return (result.split("/"));
	}

	/**
	 * @Description:( 功 能：根据一个输入串转换成日期，并判断是星期几 入口参数：String类型日期YYYYMMDD 返 回：int
	 *                类型，周1～7 )
	 * @author wang.zx
	 * @date 2014-12-20 下午10:39:12
	 * @version 1.0
	 * @param strDate
	 * @return
	 * @throws Exception
	 */
	public static int getWeekOfDate(String strDate) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		try {
			date = formatter.parse(strDate);
			if (date.getDay() == 0)
				return 7;
			else
				return date.getDay();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public static String getWeekByDateStr(String strDate) {
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(5, 7));
		int day = Integer.parseInt(strDate.substring(8, 10));

		Calendar c = Calendar.getInstance();

		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);

		String week = "";
		int weekIndex = c.get(Calendar.DAY_OF_WEEK);

		switch (weekIndex) {
		case 1:
			week = "周日";
			break;
		case 2:
			week = "周一";
			break;
		case 3:
			week = "周二";
			break;
		case 4:
			week = "周三";
			break;
		case 5:
			week = "周四";
			break;
		case 6:
			week = "周五";
			break;
		case 7:
			week = "周六";
			break;
		}
		return week;
	}

	/**
	 * @Description:( 功 能：根据一个输入串转换成日期 入口参数：String类型日期YYYYMMDD 返 回：date 类型的日期 )
	 * @author wang.zx
	 * @date 2014-12-20 下午10:39:56
	 * @version 1.0
	 * @param s
	 * @return
	 */
	public static Date StringToDate(String s) {
		Date date = new Date(0L);
		try {
			Calendar calendar = Calendar.getInstance();
			int year = Integer.parseInt(s.substring(0, s.indexOf("-")));
			int month = Integer.parseInt(s.substring(s.indexOf("-") + 1, s.lastIndexOf("-")));
			int day = Integer.parseInt(s.substring(s.lastIndexOf("-") + 1, s.length()));
			calendar.set(year, month - 1, day);
			date.setTime(calendar.getTime().getTime());
		} catch (Exception e) {
			System.out.println(String.valueOf((new StringBuffer(String.valueOf(e))).append(",").append(s)));
		}
		return date;
	}

	/**
	 * @Description:( 功 能：根据指定格式，把date型日期转换为String 入口参数：Date dt: 要转换的日期 String
	 *                fmtStr: 格式字符串， 如YYYYMMDD, YYYY/MM/DD 返 回：date 类型日期 )
	 * @author wang.zx
	 * @date 2014-12-20 下午10:40:59
	 * @version 1.0
	 * @param dt
	 * @param fmtStr
	 * @return
	 */
	public static String DateToString(Date dt, String fmtStr) {
		SimpleDateFormat format = new SimpleDateFormat(fmtStr);
		return format.format(dt);
	}

	/**
	 * @Description:( 功 能：对日期字符串进行格式化输出，如YYYYMMDD->YYYY/MM/DD 入口参数：String
	 *                strDate: 要转换的日期字符串, YYYYMMDD格式 char DateSeparator:
	 *                分隔符，/或-；如YYYY-MM-DD, YYYY/MM/DD 返 回：格式化（插入了分割符）后的日期字符串 )
	 * @author wang.zx
	 * @date 2014-12-20 下午10:41:30
	 * @version 1.0
	 * @param strDate
	 * @param DateSeparator
	 * @return
	 */
	public static String FormatDate(String strDate, char DateSeparator) {
		String strOutDate;
		int Len;
		Len = strDate.length();
		if ((Len != 6) && (Len != 8))
			strOutDate = strDate;
		else {
			if (Len == 6) {
				strDate = strDate.substring(0, 2) + DateSeparator + strDate.substring(2, 4) + DateSeparator
						+ strDate.substring(4);
				strOutDate = strDate;
			} else {
				strDate = strDate.substring(0, 4) + DateSeparator + strDate.substring(4, 6) + DateSeparator
						+ strDate.substring(6);
				strOutDate = strDate;
				strOutDate = strDate;
			}
		}
		return strOutDate;
	}

	/********************************************************
	 * 
	 * 功 能：对日期字符串进行英文化(日/月/年)格式化输出，如YYYYMMDD->DD/MM/YYYY
	 * 
	 * 入口参数：String strDate: 要转换的日期字符串, YYYYMMDD格式
	 * 
	 * char DateSeparator: 分隔符，/或-；如DD-MM-YYYY, DD/MM/YYYY
	 * 
	 * 出口参数：
	 * 
	 * 返 回：格式化（插入了分割符）后的日期字符串
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：20071218
	 * 
	 * 修改备注：香港分行报表日期格式为DD/MM/YYYY,此为英式英语日期习惯表示法,美式英语表示方法为:MM/DD/YYYY
	 * 
	 ********************************************************/

	public static String EngFormatDate8(String strDate, char DateSeparator)

	{

		String strOutDate;

		int Len;

		Len = strDate.length();

		if ((Len != 6) && (Len != 8))

			strOutDate = strDate;

		else

		{

			if (Len == 6)

			{

				strDate = strDate.substring(4, 6) + DateSeparator + strDate.substring(2, 4) + DateSeparator
						+ strDate.substring(0, 2);

				strOutDate = strDate;

			}

			else

			{

				strDate = strDate.substring(6, 8) + DateSeparator + strDate.substring(4, 6) + DateSeparator
						+ strDate.substring(0, 4);

				strOutDate = strDate;

				strOutDate = strDate;

			}

		}

		return strOutDate;

	}

	/********************************************************
	 * 
	 * 功 能：对6位日期字符串进行格式化输出，YYMMDD->YY-MM-DD
	 * 
	 * 入口参数：String strDate: 要转换的6位日期字符串, YYMMDD格式
	 * 
	 * 出口参数：
	 * 
	 * 返 回：格式化后的日期字符串, YY-MM-DD
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：20141220
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static String FormatDate6(String strDate)

	{

		if (strDate == null) {
			return "";
		}

		strDate = strDate.trim();

		if (strDate.length() < 6) {
			return "";
		}

		else {
			return "20" + strDate.substring(0, 2) + "-" + strDate.substring(2, 4) + "-" + strDate.substring(4);
		}

	}

	/********************************************************
	 * 
	 * 功 能：对8位日期字符串进行格式化输出，YYYYMMDD->YYYY-MM-DD
	 * 
	 * 入口参数：String strDate: 要转换的8位日期字符串, YYYYMMDD格式
	 * 
	 * 出口参数：
	 * 
	 * 返 回：格式化后的日期字符串, YYYY-MM-DD
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：20141220
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static String FormatDate8(String strDate)

	{

		if (strDate == null) {
			return "";
		}

		strDate = strDate.trim();

		if (strDate.length() < 8) {
			return "";
		}

		else {
			return strDate.substring(0, 4) + "-" + strDate.substring(4, 6) + "-" + strDate.substring(6);
		}

	}

	/********************************************************
	 * 
	 * 功 能：去掉日期的分隔符, 即把带分隔符格式日期字符串转为YYYYMMDD
	 * 
	 * 入口参数：String strDate: 要转换的日期字符串, YYYYMMDD格式
	 * 
	 * char DateSeparator: 分隔符，/或-；如YYYY-MM-DD, YYYY/MM/DD
	 * 
	 * 出口参数：
	 * 
	 * 返 回：删除了分割符后的日期字符串, YYYYMMDD格式
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static String DeFormatDate(String strDate, char DateSeparator) {

		strDate = strDate + DateSeparator;

		String[] strs = StringUtil.doSplit(strDate, DateSeparator);

		if (strs[1].length() == 1)

			strs[1] = "0" + strs[1];

		if (strs[2].length() == 1)

			strs[2] = "0" + strs[2];

		return strs[0] + strs[1] + strs[2];

	}

	/********************************************************
	 * 
	 * 功 能：去掉日期的分隔符, 即把带分隔符格式日期字符串转为YYMMDD
	 * 
	 * 入口参数：String strDate: 要转换的6位日期字符串, 如YY-MM-DD格式
	 * 
	 * 出口参数：
	 * 
	 * 返 回：删除了分割符后的日期字符串, YYMMDD格式
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static String DeFormatDate6(String strDate)

	{

		if (strDate == null) {
			return "";
		}

		else if (strDate.length() < 10) {
			return "";
		}

		else {
			return strDate.substring(2, 4) + strDate.substring(5, 7) + strDate.substring(8);
		}

	}

	/********************************************************
	 * 
	 * 功 能：去掉日期的分隔符, 即把带分隔符格式日期字符串转为YYYYMMDD
	 * 
	 * 入口参数：String strDate: 要转换的8位日期字符串, 如YYYY-MM-DD格式
	 * 
	 * 出口参数：
	 * 
	 * 返 回：删除了分割符后的日期字符串, YYYYMMDD格式
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static String DeFormatDate8(String strDate)

	{

		if (strDate == null) {
			return "";
		}

		else if (strDate.length() < 10) {
			return "";
		}

		else {
			return strDate.substring(0, 4) + strDate.substring(5, 7) + strDate.substring(8);
		}

	}

	/********************************************************
	 * 
	 * 功 能：对时间字符串进行格式化输出，如HHMMSS->HH:MM:SS
	 * 
	 * 入口参数：String strTime: 要转换的时间字符串, HHMMSS格式
	 * 
	 * char TimeSepartor: 分隔符，:或-；如HH:MM:SS
	 * 
	 * 出口参数：
	 * 
	 * 返 回：格式化（插入了分割符）后的时间字符串
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static String FormatTime(String strTime, char TimeSepartor)

	{

		String strOutTime;

		if (strTime.length() != 6)

			return strTime;

		strTime = strTime.substring(0, 2) + TimeSepartor + strTime.substring(2, 4) + TimeSepartor
				+ strTime.substring(4);

		strOutTime = strTime;

		return strOutTime;

	}

	/********************************************************
	 * 
	 * 功 能：去掉时间的分隔符, 如把带分隔符格式时间字符串HH:MM:SS转为HHMMSS
	 * 
	 * 入口参数：String strTime: 要转换的时间字符串, HH:MM:SS格式
	 * 
	 * char TimeSepartor: 分隔符，:或-；如HH:MM:SS
	 * 
	 * 出口参数：
	 * 
	 * 返 回：删除了分割符后的时间字符串, HHMMSS格式
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static String DeFormatTime(String strTime, char TimeSepartor) {

		strTime = strTime + TimeSepartor;

		String[] strs = StringUtil.doSplit(strTime, TimeSepartor);

		if (strs[1].length() == 1)

			strs[1] = "0" + strs[1];

		if (strs[2].length() == 1)

			strs[2] = "0" + strs[2];

		return strs[0] + strs[1] + strs[2];

	}

	/********************************************************
	 * 
	 * 功 能：计算两个日期的时间差
	 * 
	 * 入口参数：String sDate: 起始日期, YYYY-MM-DD格式
	 * 
	 * String fDate: 结束日期, YYYY-MM-DD格式
	 * 
	 * 出口参数：
	 * 
	 * 返 回：int 两个日期的时间差，返回天数
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static int diffDate(String sDate, String fDate) throws Exception {

		if (sDate.trim().equals("") ||

				fDate.trim().equals("")) {

			return 0;

		}

		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");

		java.util.Date sdate = myFormatter.parse(sDate);

		java.util.Date fdate = myFormatter.parse(fDate);

		int diffdate = (int) ((fdate.getTime() - sdate.getTime()) /

				(1000 * 60 * 60 * 24));

		return diffdate;

	}

	/********************************************************
	 * 
	 * 功 能：计算两个日期的时间差
	 * 
	 * 入口参数：String sDate: 起始日期
	 * 
	 * String fDate: 结束日期
	 * 
	 * String dateformat1, 日期格式
	 * 
	 * 出口参数：
	 * 
	 * 返 回：int 两个日期的时间差，返回天数
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static int diffDate(String sDate, String fDate, String dateformat1) throws

	Exception {

		SimpleDateFormat myFormatter = new SimpleDateFormat(dateformat1);

		java.util.Date sdate = myFormatter.parse(sDate);

		java.util.Date fdate = myFormatter.parse(fDate);

		int diffdate = (int) ((fdate.getTime() - sdate.getTime()) /

				(1000 * 60 * 60 * 24));

		return diffdate;

	}

	/********************************************************
	 * 
	 * 功 能：计算两个日期的时间差
	 * 
	 * 入口参数：String sTime: 起始日期时间, yyyy-MM-dd HH:mm:ss格式
	 * 
	 * String fTime: 结束日期时间, yyyy-MM-dd HH:mm:ss格式
	 * 
	 * 出口参数：
	 * 
	 * 返 回：int 时间差，返回秒
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static int diffTime(String sTime, String fTime) throws Exception {

		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		java.util.Date stime = myFormatter.parse(sTime);

		java.util.Date ftime = myFormatter.parse(fTime);

		int second = (int) ((ftime.getTime() - stime.getTime()) / 1000);

		return second;

	}

	/********************************************************
	 * 
	 * 功 能：计算两个日期的时间差
	 * 
	 * 入口参数：String sTime: 起始日期时间
	 * 
	 * String fTime: 结束日期时间
	 * 
	 * String dateformat1, 日期格式
	 * 
	 * 
	 * 
	 * 出口参数：
	 * 
	 * 返 回：int 时间差，返回秒
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static int diffTime(String sTime, String fTime, String Dateformat1) throws

	Exception {

		SimpleDateFormat myFormatter = new SimpleDateFormat(Dateformat1);

		java.util.Date stime = myFormatter.parse(sTime);

		java.util.Date ftime = myFormatter.parse(fTime);

		int second = (int) ((ftime.getTime() - stime.getTime()) / 1000);

		return second;

	}

	//

	/********************************************************
	 * 
	 * 功 能：判断strDate是否是正确的日期，格式为YYYYMMDD
	 * 
	 * 入口参数：String strDate:要判断的日期字符串
	 * 
	 * 出口参数：
	 * 
	 * 返 回：boolean , true: 传入的是正确日期
	 * 
	 * false: 传入的是错误日期
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static boolean isdate_yyyymmdd(String strDate)

	{

		if (strDate == null || strDate.length() != 8)
			return false;

		// 年份在0000~9999

		if (strDate.charAt(0) > '9' || strDate.charAt(0) <= '0') {

			return false;

		}

		if (strDate.charAt(1) > '9' || strDate.charAt(1) < '0') {

			return false;

		}

		if (strDate.charAt(2) > '9' || strDate.charAt(2) < '0') {

			return false;

		}

		if (strDate.charAt(3) > '9' || strDate.charAt(3) < '0') {

			return false;

		}

		int intyear = Integer.parseInt(strDate.substring(0, 4));

		// 月份在00~12

		if (strDate.charAt(4) > '1' || strDate.charAt(4) < '0') {

			return false;

		}

		if (strDate.charAt(5) > '9' || strDate.charAt(5) < '0') {

			return false;

		}

		int intmonth = Integer.parseInt(strDate.substring(4, 6));

		if (intmonth > 12 || intmonth < 0)
			return false;

		// 日期

		if (strDate.charAt(6) > '3' || strDate.charAt(6) < '0') {

			return false;

		}

		if (strDate.charAt(7) > '9' || strDate.charAt(7) < '0') {

			return false;

		}

		int intday = Integer.parseInt(strDate.substring(6, 8));

		if (intmonth == 1 || intmonth == 3 || intmonth == 5 ||

				intmonth == 7 || intmonth == 8 || intmonth == 10 || intmonth == 12)

		{

			if (intday > 31 || intday < 0)
				return false;

		}

		else if (intmonth == 4 || intmonth == 6 || intmonth == 9 || intmonth == 11)

		{

			if (intday > 30 || intday < 0)
				return false;

		}

		else if (intmonth == 2 && intyear % 4 == 0 && intyear % 100 == 0)

		{

			if (intday > 28 || intday < 0)
				return false;

		}

		else if (intmonth == 2 && intyear % 4 == 0)

		{

			if (intday > 29 || intday < 0)
				return false;

		}

		else if (intmonth == 2)

		{

			if (intday > 28 || intday < 0)

				return false;

		}

		return true;

	}

	/*******************************
	 * mark by yebin, 名字改为formatdate, deformatdate
	 * 
	 * //日期型处理 返回yymmdd
	 * 
	 * public static String getDateValue(String str1)
	 * 
	 * {
	 * 
	 * if (str1==null) {return "";}
	 * 
	 * else if (str1.length()<10) {return "";}
	 * 
	 * else {return str1.substring(2,4)+str1.substring(5,7)+str1.substring(8);}
	 * 
	 * }
	 * 
	 * //日期型处理 返回yyyymmdd
	 * 
	 * public static String getDateValue1(String str1)
	 * 
	 * {
	 * 
	 * if (str1==null) {return "";}
	 * 
	 * else if (str1.length()<10) {return "";}
	 * 
	 * else {return str1.substring(0,4)+str1.substring(5,7)+str1.substring(8);}
	 * 
	 * }
	 * 
	 * 
	 * 
	 * //日期型处理 送入yymmdd 返回yyyy-mm-dd
	 * 
	 * public static String getrealDateValue(String str1)
	 * 
	 * {
	 * 
	 * if (str1==null) {return "";}
	 * 
	 * str1=str1.trim();
	 * 
	 * if (str1.length()<6) {return "";}
	 * 
	 * else {return
	 * "20"+str1.substring(0,2)+"-"+str1.substring(2,4)+"-"+str1.substring(4);}
	 * 
	 * }
	 * 
	 * //日期型处理 送入yyyymmdd 返回yyyy-mm-dd
	 * 
	 * public static String getrealDateValue1(String str1)
	 * 
	 * {
	 * 
	 * if (str1==null) {return "";}
	 * 
	 * str1=str1.trim();
	 * 
	 * if (str1.length()<8) {return "";}
	 * 
	 * else {return
	 * str1.substring(0,4)+"-"+str1.substring(4,6)+"-"+str1.substring(6);}
	 * 
	 * }
	 * 
	 *******************************************************/

	/********************************************************
	 * 
	 * 功 能：转换6位日期为8位日期, 如051219->
	 * 
	 * 入口参数：String str1:6位日期字符串, YYMMDD格式
	 * 
	 * 出口参数：
	 * 
	 * 返 回：8位日期字符串, YYYYMMDD格式
	 * 
	 * 编 写 人：
	 * 
	 * 编写日期：
	 * 
	 * 修改备注：
	 * 
	 ********************************************************/

	public static String Date6To8(String str1)

	{

		if (str1 == null) {
			return "";
		}

		str1 = str1.trim();

		if (str1.length() != 6) {
			return "";
		}

		String yy = str1.substring(0, 2);

		String mm = str1.substring(2, 4);

		String dd = str1.substring(4, 6);

		try {

			if (Integer.parseInt(yy) >= 70)
				yy = "19" + yy;

			else
				yy = "20" + yy;

			if ((Integer.parseInt(mm) < 1) || (Integer.parseInt(mm) > 12))

			{
				return "";
			}

			if ((Integer.parseInt(dd) < 1) || (Integer.parseInt(dd) > 31))

			{
				return "";
			}

		}

		catch (Exception e) {
			return "";
		}

		return yy + mm + dd;

	}

	/**
	 * 
	 * 取得与上一次明细提取最后日期同年同月的本次日期
	 * 
	 * @param strSysDate
	 * 
	 * @param strLastEndDate
	 * 
	 * @return
	 * 
	 */

	public static String getEndDate(String strSysDate, String strLastEndDate) {

		String strEndDate = null;

		if (strLastEndDate.substring(0, 6).equals(strSysDate.substring(0, 6))) {

			strEndDate = strSysDate;

		} else {

			if (strLastEndDate.substring(0, 4).equals(strSysDate.substring(0, 4))) {

				strEndDate = getDate(strLastEndDate.substring(0, 6));

			} else {

				strEndDate = strLastEndDate.substring(0, 4) + "1231"; // 跨年，取前一年的12月31日

			}

		}

		return strEndDate;

	}

	/**
	 * 
	 * 取某年某月的最后一天日期YYYYMMDD
	 * 
	 * @param strYYYYMM
	 * 
	 * @return
	 * 
	 */

	public static String getDate(String strYYYYMM) {

		String strDate = null;

		if (strYYYYMM.substring(4).equals("01")

				|| strYYYYMM.substring(4).equals("03")

				|| strYYYYMM.substring(4).equals("05")

				|| strYYYYMM.substring(4).equals("07")

				|| strYYYYMM.substring(4).equals("08")

				|| strYYYYMM.substring(4).equals("10")

				|| strYYYYMM.substring(4).equals("12"))

		{

			strDate = strYYYYMM + "31";

		}

		else

		if (strYYYYMM.substring(4).equals("04")

				|| strYYYYMM.substring(4).equals("06")

				|| strYYYYMM.substring(4).equals("09")

				|| strYYYYMM.substring(4).equals("11"))

		{

			strDate = strYYYYMM + "30";

		}

		else

		{

			if (isLeapYear(strYYYYMM.substring(0, 4)))

			{

				strDate = strYYYYMM + "29";

			}

			else

			{

				strDate = strYYYYMM + "28";

			}

		}

		return strDate;

	}

	/**
	 * 
	 * 判断是否闰年
	 * 
	 * @param strYYYY
	 * 
	 * @return
	 * 
	 */

	public static boolean isLeapYear(String strYYYY) {

		boolean bLeap = false;

		Calendar cal = Calendar.getInstance();

		bLeap = ((GregorianCalendar) cal).isLeapYear(Integer.parseInt(strYYYY));

		return bLeap;

	}

	// 将YYYYMMDD转成日期型
	public static Date strtoDate(String strDate) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		try {
			date = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	// 取之后的第i天
	public static Date GetNextiDate(Date date, int i) {
		long ni = i * 1000 * 60 * 60 * 24 + date.getTime();

		Date ndate = new Date(ni);
		return ndate;
	}

	// 取之后的第month月日期
	public static String getNextiMonth(Date date, int month) {
		Calendar c=Calendar.getInstance();   
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
		c.setTime(new Date());   
		c.add(Calendar.DATE,month);   
		Date d2=c.getTime();   
		String s=df.format(d2);   
		return s;   
	}

	// 将日期转换成String YYMMDD
	public static String DateToString(java.util.Date dt) {
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		return format.format(dt);
	}

	// 获取前或者后N天的日期
	// strdate:起始日期YYYYMMDD
	// i:推算天数
	// strPorNflag:N:向前;P:向后
	public static String GetNextDate(String strdate, int i, char chPorNflag) throws Exception {
		Date ndate = strtoDate(strdate);
		if (chPorNflag == 'N')
			ndate = GetNextiDate(ndate, i);
		else if (chPorNflag == 'P')
			ndate = GetNextiDate(ndate, -i);
		String nstrdate = "20" + DateToString(ndate);
		return nstrdate;
	}

	/**
	 * 判断系统时间是否在指定的时间范围
	 * 
	 * @param starttime
	 * @param endtime
	 * @throws Exception
	 *             add by yczheng on 20070928
	 */
	public static boolean bjudgeRange(String starttime, String endtime) {
		String nowtime = DatetimeUtil.getTime();

		// 如起始时间 小于 结束时间
		if (starttime.compareToIgnoreCase(endtime) < 0) {
			if (nowtime.compareToIgnoreCase(starttime) > 0 && nowtime.compareToIgnoreCase(endtime) < 0) {
				return true;
			} else {
				return false;
			}
		} else {
			if (nowtime.compareToIgnoreCase(endtime) > 0 && nowtime.compareToIgnoreCase(starttime) < 0) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * 时间字符串转换成Date对象
	 * 
	 * @param dateStr
	 *            时间字符串
	 * @param formatStr
	 *            时间格式
	 * @return
	 */
	public static Date string2Date(String dateStr, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取和给定时间间隔指定(年/月/日/小时/分钟/秒)数的时间
	 * 
	 * @param dSrc
	 *            给定的时间
	 * @param num
	 *            间隔时间
	 * @param type
	 *            年/月/日/小时/分钟/秒
	 * @return
	 */
	public static Date getNewDate(Date date, int num, int type) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		switch (type) {
		case Calendar.YEAR:
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + num);
			break;

		case Calendar.MONTH:
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + num);
			break;

		case Calendar.DATE:
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + num);
			break;

		case Calendar.HOUR:
			cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + num);
			break;

		case Calendar.MINUTE:
			cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + num);
			break;

		case Calendar.SECOND:
			cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + num);
			break;

		default:
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + num);
			break;
		}

		Date newDate = cal.getTime();
		return newDate;
	}

	/**
	 * 根据日期格式长短类型，及系统的Locale类型,获取相应日期格式
	 * 
	 * @param date
	 *            日期 格式与iso对应
	 * @param formatType
	 *            >>DateFormat.SHORT、
	 *            DateFormat.MEDIUM、DateFormat.LONG、DateFormat.FULL
	 * @param locale
	 *            >> Locale.UK 、Locale.CHINA
	 * @param iso
	 *            格式 如:"MM/dd/yy" 、"yyyy/MM/dd"、"MM-dd-yyyy"、"yyyy-MM-dd"
	 * @return
	 */
	public static String getDatebylocale(String date, int formatType, Locale locale, String iso) {
		String datestrs = "";
		try {
			Date myDate = new Date();
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(iso);
			myDate = format.parse(date);
			DateFormat dateformat = DateFormat.getDateInstance(formatType, locale);
			datestrs = dateformat.format(myDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datestrs;
	}

	/**
	 * 对指定日期时间进行加减
	 * 
	 * @param date
	 *            日期 格式与iso对应
	 * @param timeType
	 *            >>Calendar.DAY_OF_YEAR、 Calendar.MINUTE、Calendar.SECOND;
	 * @param n
	 *            增加或减少数值
	 * @param iso
	 *            格式 如:"MM/dd/yy" 、"yyyy/MM/dd"、"MM-dd-yyyy"、"yyyyMMddhhmmss"
	 * @return 格式是当前iso格式
	 */
	public static String getDateByadd(String date, int timeType, int n, String iso) {
		String tReturn = "";
		Date myDate = new Date();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(iso);
		try {
			myDate = format.parse(date);
			Calendar now = Calendar.getInstance();
			now.setTime(myDate);
			SimpleDateFormat formatter = new SimpleDateFormat(iso);
			now.add(timeType, n);
			tReturn = formatter.format(now.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tReturn;

	}

	/**
	 * 根据格式获取当前时间
	 * 
	 * @param iso
	 * @return
	 */
	public static String getDateTime(String iso) {
		String tReturn = "";
		Date now = new Date();
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(iso);
		tReturn = format.format(now);
		return tReturn;
	}

	/**
	 * 
	 * @Title: getDay @Description: (有效期) @param @param date @param @return
	 * 设定文件 @return Date 返回类型 @throws
	 */
	public static Date getDay(int date) {
		Calendar calendar = Calendar.getInstance();
		// getTime()方法是取得当前的日期，其返回值是一个java.util.Date类的对象
		Date currentDate = calendar.getTime();
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day - date);
		// 投票的有效期30天
		return calendar.getTime();
	}
}
