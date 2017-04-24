package com.yuxin.wx.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数工具,用于处理参数
 * @author chopin
 *
 */
public class ParameterUtil {
	
	/**
	 * 判断字符串是否为手机号
	 * @return
	 */
	public static Boolean isMobilePhone(String str){
		Pattern pattern = Pattern.compile("^09[0-9]{8}|[1][3,4,5,7,8][0-9]{9}$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	
	public static Boolean isEmail(String str){
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+.[a-zA-Z]{2,3}$");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	public static boolean isLetter(String str){ 
	       Pattern pattern = java.util.regex.Pattern.compile("[a-zA-Z0-9]+"); 
	       Matcher m = pattern.matcher(str);
	          return m.matches();     
	 }
	
	public static boolean isUserName(String str){
		  Pattern pattern = java.util.regex.Pattern.compile("^[a-zA-Z]+[a-zA-Z0-9_]+"); 
	      Matcher m = pattern.matcher(str);
	      return m.matches(); 
	}
	
	public static boolean isChinese(String str){
		Pattern pattern = java.util.regex.Pattern.compile("^[\u4e00-\u9fa5]+$"); 
	    Matcher m = pattern.matcher(str);
	    return m.matches(); 
	}
	
	public static boolean isChinaeseAndWord(String str){
		Pattern pattern = java.util.regex.Pattern.compile("^[\u4e00-\u9fa5\\w]+$"); 
	    Matcher m = pattern.matcher(str);
	    return m.matches(); 
	}
	
	public static void main(String[] args){
		String str="李四";
		System.out.println(str.length());
		System.out.println(isChinese("张三3_"));
	}

	public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        DecimalFormat df = new DecimalFormat("#.00");
        if (size >= gb) {
            return df.format((double) size / gb) + "GB";
        } else if (size >= mb) {
            return df.format((double) size / mb) + "MB";
        } else if (size >= kb) {
            return df.format((double) size / kb) + "KB";
        } else
            return df.format((double) size) + "B";
    }
	
	public static Double convertFileSized(long size) {
		long kb = 1024;
		long mb = kb * 1024;
		long gb = mb * 1024;
		Double s = (double) size / gb;
		return new BigDecimal(s).setScale(3, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}
	
	public static String GenseeMd5(Integer userId,Integer lessonId,Integer companyId
			,long timestamp,String password){
		String codes = "userId=" + userId + "lessonId=" + lessonId + "companyId="
				+companyId;
		String oneMd5 = MD5.getMD5ofStr(codes);
		oneMd5 += "timestamp=" + timestamp + "password="+password;
		String twoMd5 = MD5.getMD5ofStr(oneMd5);
		return twoMd5;
	}
	public static String Unicode(String string) {
		 
	    StringBuffer unicode = new StringBuffer();
	 
	    for (int i = 0; i < string.length(); i++) {
	 
	        // 取出每一个字符
	        char c = string.charAt(i);
	 
	        // 转换为unicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
	 
	    return unicode.toString();
	}
}
