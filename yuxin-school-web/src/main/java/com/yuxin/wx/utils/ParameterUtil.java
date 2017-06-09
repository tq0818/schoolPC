package com.yuxin.wx.utils;

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

		Pattern pattern = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
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
	
	public static boolean isNum(String str){
		Pattern pattern = Pattern.compile("[0-9X|x]*"); 
		return pattern.matcher(str).matches();    
	}
	
	public static void main(String[] args){
		String str="李四";
		System.out.println(str.length());
		System.out.println(isChinese("张三3_"));
	}

}
