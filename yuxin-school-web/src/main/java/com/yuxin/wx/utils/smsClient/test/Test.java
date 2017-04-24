package com.yuxin.wx.utils.smsClient.test;

import com.yuxin.wx.util.smsClient.companyInfo.CompanyInfo;
import com.yuxin.wx.util.smsClient.interfacej.SmsClientKeyword;
import com.yuxin.wx.util.smsClient.interfacej.SmsClientOverage;
import com.yuxin.wx.util.smsClient.interfacej.SmsClientSend;
import com.yuxin.wx.util.smsClient.tool.SmsClientAccessTool;

public class Test {

	public static String url = "http://218.244.136.70:8888/sms.aspx";
	public static String userid = "1169";
	public static String account = "刘洋";
	public static String password = "123456";
	public static String checkWord = "这个字符串中是否包含了屏蔽字";

	public static void main(String[] args) {

		sendSms();
		// overage();
	}

	public static void keyword() {

		String keyword = SmsClientKeyword.queryKeyWord(url, userid, account,
				password, checkWord);
		System.out.println(keyword);
	}

	public static void overage() {

		String overage = SmsClientOverage.queryOverage(url, userid, account,
				password);
		System.out.println(overage);
	}

	public static void test() {
		String send = SmsClientAccessTool.getInstance().doAccessHTTPPost(
				"http://118.145.30.35/sms.aspx", "", "utf-8");
		System.out.println(send);
	}
	
	public static void sendSms(){
		
		String send = SmsClientSend.sendSms("13161628396,18010178535", "您在云朵课堂提交的认证已经审核通过，请登录云朵课堂进行网校搭建的后续操作。【在线网校】");
		System.out.println(send);
	}
}
