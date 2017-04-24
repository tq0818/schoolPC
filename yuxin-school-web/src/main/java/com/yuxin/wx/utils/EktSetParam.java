package com.yuxin.wx.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.course.LiveOpenCourse;

public class EktSetParam {
	public static Map<String, Object> setLiveParam(CompanyLiveConfig clc,ClassModuleLesson cml
			,PropertiesUtil properties){
		String customer = "";
		String secretKey = "";
		String liveAddress = "";
		if(clc != null && clc.getLiveType().equals(2)){
			customer = properties.getCustomer();
			secretKey = properties.getSecretKey();
			liveAddress = properties.getLiveAddress();
		}else{
			//截取customer
			String ue = cml.getStudentUrlGh();
			customer = ue.substring(ue.indexOf("&customer=") + 10,
					ue.indexOf("&customerType"));
			if(customer.equals("yxkj")){
				secretKey = properties.getSecretKey();
			}else{
				secretKey = properties.getFreeSecretKey();	
			}
			liveAddress = properties.getLiveAddress();
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customer", customer);
		param.put("secretKey", secretKey);
		param.put("liveAddress", liveAddress);
		return param;
	}
	
	public static Map<String, Object> setOpenParam(CompanyLiveConfig clc,LiveOpenCourse cml
			,PropertiesUtil properties){
		String customer = "";
		String secretKey = "";
		String liveAddress = "";
		if(clc != null && clc.getLiveType().equals(2)){
			customer = properties.getCustomer();
			secretKey = properties.getSecretKey();
			liveAddress = properties.getLiveAddress();
		}else{
			//截取customer
			String ue = cml.getStudentUrlGh();
			customer = ue.substring(ue.indexOf("&customer=") + 10,
					ue.indexOf("&customerType"));
			if(customer.equals("yxkj")){
				secretKey = properties.getSecretKey();
			}else{
				secretKey = properties.getFreeSecretKey();	
			}
			liveAddress = properties.getLiveAddress();
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customer", customer);
		param.put("secretKey", secretKey);
		param.put("liveAddress", liveAddress);
		return param;
	}
}
