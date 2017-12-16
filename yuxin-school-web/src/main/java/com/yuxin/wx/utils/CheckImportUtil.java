package com.yuxin.wx.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckImportUtil {
   

	public static Boolean isNum(String str){
		Pattern pattern = Pattern.compile("^-?[0-9]+");
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		String str="1234";
		System.out.println(isNum(str));
    }
}
