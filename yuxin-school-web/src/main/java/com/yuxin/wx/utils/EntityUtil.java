package com.yuxin.wx.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.yuxin.wx.common.BaseEntity;

public class EntityUtil {

	/**
	 * 
	 * Class Name: VoUtil.java
	 * @Description: 判断VO对象是否不为空对象
	 * @author Chopin
	 * @date 2014年12月12日 
	 * @version 1.0
	 * @param model
	 * @return
	 */
	public static Boolean isNotBlank(BaseEntity model){
		if(model==null){
			return false;
		}
		Boolean notNull=false;
		try{
			Class clazz=model.getClass();
			Field[] fields=clazz.getDeclaredFields();
			for(Field field: fields){
				Method method =clazz.getDeclaredMethod("get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1),null);
				if(null!=method.invoke(model, null)){
					notNull=true;
				}
			}
		}catch(Exception e){
			notNull=false;
		}
		return notNull;
		
	}
	
	/**
	 * 
	 * Class Name: VoUtil.java
	 * @Description: 判断VO对象是否为空对象
	 * @author Chopin
	 * @date 2014年12月12日 
	 * @version 1.0
	 * @param model
	 * @return
	 */
	public static Boolean isBlank(BaseEntity model){
		if(model==null){
			return true;
		}
		Boolean notNull=true;
		try{
			Class clazz=model.getClass();
			Field[] fields=clazz.getDeclaredFields();
			for(Field field: fields){
				Method method =clazz.getDeclaredMethod("get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1),null);
				if(null!=method.invoke(model, null)){
					notNull=false;
				}
			}
		}catch(Exception e){
			notNull=true;
		}
		return notNull;
		
	}
}
