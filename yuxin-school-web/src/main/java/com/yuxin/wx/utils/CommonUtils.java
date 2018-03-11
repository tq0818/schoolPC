package com.yuxin.wx.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.vo.redis.ClassLectureVO;

public class CommonUtils {

	private static Logger logger = Logger.getLogger(CommonUtils.class);
	
	/**
	 * 根据step、入学年份获取年级
	 * @param step
	 * @param eduYear
	 * @return
	 */
	public static ClassType getClassTypeByStep(String step, String eduYear) {
		if (null == step) {
			return null;
		}
		ClassType classType = new ClassType();
		// 获取当前时间
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		if ("STEP_01".equals(step)) {
			classType.setItemName("小%");
			if (month >= 9) {
				if (year - Integer.parseInt(eduYear) + 1 <= 3) {
					classType.setItemSecondName("TYPE_LOW");
				} else if (year - Integer.parseInt(eduYear) + 1 == 4) {
					classType.setItemSecondName("GRADE_FOUR");
				} else if (year - Integer.parseInt(eduYear) + 1 == 5) {
					classType.setItemSecondName("GRADE_FIVE");
				} else {
					classType.setItemSecondName("GRADE_SIX");
				}
			} else {
				if (year - Integer.parseInt(eduYear) <= 3) {
					classType.setItemSecondName("TYPE_LOW");
				} else if (year - Integer.parseInt(eduYear) == 4) {
					classType.setItemSecondName("GRADE_FOUR");
				} else if (year - Integer.parseInt(eduYear) == 5) {
					classType.setItemSecondName("GRADE_FIVE");
				} else {
					classType.setItemSecondName("GRADE_SIX");
				}
			}
		} else if ("STEP_02".equals(step)) {
			classType.setItemName("初%");
			classType.setItemSecondName("MID_ONE");
		} else if("STEP_03".equals(step)){
			classType.setItemName("高%");
			classType.setItemSecondName("HIHER_ONE");
		}else{
			logger.error("====> STEP error , step = "+step);
			return null;
		}

		return classType;
	}

	
	public static Map<Integer,Integer> initLessonLogMap(Map<Integer,ClassLectureVO> map,Map<Integer,Integer> initMap){
		if(null == map){
			return null;
		}
		//Map<Integer,Integer> initMap = new HashMap<>();
		for(Integer ids : map.keySet()){
			initMap.put(ids, 0);
		}
		return initMap;
	}
	
	
	
}
