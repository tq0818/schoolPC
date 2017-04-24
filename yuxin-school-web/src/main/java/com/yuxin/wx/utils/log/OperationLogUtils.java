package com.yuxin.wx.utils.log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.common.BaseEntity;
import com.yuxin.wx.common.PageFinder;

public class OperationLogUtils {

	/**
	 * @Description: 获取Request
	 * @author dongshuai
	 */
	public static HttpServletRequest getRequest(JoinPoint joinPoint) {
		try {
			Object[] args = joinPoint.getArgs();
			for (Object obj : args) {
				if (obj instanceof HttpServletRequest)
					return (HttpServletRequest) obj;
			}

			HttpServletRequest request;
			try {
				request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			} catch (Exception e) {
				request = null;
			}
			return request;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Description: 获取返回类型
	 * @author dongshuai
	 */
	public static String getReturnType(Object result) {
		StringBuffer r = new StringBuffer();
		try {
			if (result == null)
				return "NoReturn";
			if (result instanceof ModelAndView)
				return "{\"" + result.getClass().getSimpleName() + "\":" + ((ModelAndView) result).getViewName() + "}";
			else if (result instanceof JSONObject)
				return "{\"" + result.getClass().getSimpleName() + "\":" + ((JSONObject) result).toJSONString() + "}";
			else if (result instanceof PageFinder<?>) {
				boolean hasMessage = false;
				r.append("{\"" + result.getClass().getSimpleName() + "\":{");
				for (Object obj : ((PageFinder<?>) result).getData()) {
					if (!hasMessage)
						hasMessage = true;
					r.append("\"" + obj.getClass().getSimpleName() + "\":" + JSONObject.toJSONString(obj) + ",");
				}
				return hasMessage ? r.deleteCharAt(r.length() - 1).append("}}").toString() : r.append("}}").toString();
			} else if (result instanceof BaseEntity)
				return "{\"" + result.getClass().getSimpleName() + "\":" + JSONObject.toJSONString(result) + "}";
			else if (result instanceof List<?>) {
				boolean hasMessage = false;
				r.append("{\"" + result.getClass().getSimpleName() + "\":{");
				for (Object obj : ((List<?>) result)) {
					if (!hasMessage)
						hasMessage = true;
					r.append("\"" + obj.getClass().getSimpleName() + "\":" + JSONObject.toJSONString(obj) + ",");
				}
				return hasMessage ? r.deleteCharAt(r.length() - 1).append("}}").toString() : r.append("}}").toString();
			} else if (result instanceof Set<?>) {
				boolean hasMessage = false;
				r.append("{\"" + result.getClass().getSimpleName() + "\":{");
				for (Object obj : ((Set<?>) result)) {
					if (!hasMessage)
						hasMessage = true;
					r.append("\"" + obj.getClass().getSimpleName() + "\":" + JSONObject.toJSONString(obj) + ",");
				}
				return hasMessage ? r.deleteCharAt(r.length() - 1).append("}}").toString() : r.append("}}").toString();
			} else
				return "{\"" + result.getClass().getSimpleName() + "\":" + JSONObject.toJSONString(result) + "}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"Exception\":" + e.getMessage() + "}";
		}
	}

	/**
	 * @Description: 获取Url传来的参数
	 * @author dongshuai
	 */
	public static String getUrlParams(JoinPoint joinPoint) {
		try {
			Object[] args = joinPoint.getArgs();

			StringBuffer param = new StringBuffer();
			boolean hasMessage = false;
			Integer count = 1;

			param.append("{");
			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof Integer || args[i] instanceof String) {
					if (!hasMessage)
						hasMessage = true;
					param.append("\"Params" + (count++) + "\":" + args[i].toString() + ",");
				}
			}
			return hasMessage ? param.deleteCharAt(param.length() - 1).append("}").toString()
					: param.append("}").toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"Exception\":" + e.getMessage() + "}";
		}
	}

	/**
	 * @Description: 获取Model返回参数
	 * @author dongshuai
	 */
	public static String getModelParams(JoinPoint joinPoint) {
		try {
			Object[] args = joinPoint.getArgs();

			StringBuffer param = new StringBuffer();

			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof Model) {
					param.append("{\"Model\":" + JSONObject.toJSONString(args[i]) + "}");
				}
			}
			return param.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"Exception\":" + e.getMessage() + "}";
		}
	}

	/**
	 * @Description: 获取Request参数
	 * @author dongshuai
	 */
	@SuppressWarnings("unchecked")
	public static String getRequestParams(HttpServletRequest request) {
		try {
			Map<?, ?> properties = request.getParameterMap();
			Map<String, String> returnMap = new HashMap<String, String>();
			Iterator<?> entries = properties.entrySet().iterator();
			Entry<String, String[]> entry;
			String name = "";
			String value = "";
			while (entries.hasNext()) {
				entry = (Entry<String, String[]>) entries.next();
				name = (String) entry.getKey();
				Object valueObj = entry.getValue();
				value = null;
				if (null == valueObj) {
					value = "";
				} else if (valueObj instanceof String[]) {
					String[] values = (String[]) valueObj;
					for (int i = 0; i < values.length; i++) {
						if (value == null)
							value = (values[i] == null ? "" : values[i]);
						else
							value += "," + (values[i] == null ? "" : values[i]);
					}
				} else {
					value = valueObj.toString();
				}
				returnMap.put(name, value);
			}
			return JSONObject.toJSONString(returnMap);
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"Exception\":" + e.getMessage() + "}";
		}
	}

	/**
	 * @Description: 获取方法名称
	 * @author dongshuai
	 */
	public static String getMethodName(JoinPoint joinPoint) {
		return joinPoint.getSignature().getName();
	}
	
	public static String getFullMethodName(JoinPoint joinPoint){
		return joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
	}

	/**
	 * @Description: 是否需要记录
	 * @author dongshuai
	 */
	public static boolean isNeed(String methodName) {

		String[] starts = { "query", "find", "initBinder", "select", "show", "get" };

		for (String s : starts) {
			if (methodName.startsWith(s))
				return false;
		}

		return true;
	}
	
	/**
	 * @Description: 是否需要记录数据
	 * @author dongshuai
	 */
	public static boolean isNeedData(String fullMethodName){
		
		String[] noNeed = {
			"com.yuxin.wx.common.BaseWebController.login",   							//登录
			"com.yuxin.wx.controller.auth.AuthPrivilegeController.saveUser", 			//添加用户
			"com.yuxin.wx.controller.auth.AuthPrivilegeController.updateUser", 			//修改用户
			"com.yuxin.wx.controller.student.StudentController.changePassword", 		//更改学员密码
			"com.yuxin.wx.controller.system.SysConfigTeacherController.addEducation", 	//添加教务
			"com.yuxin.wx.controller.system.SysConfigTeacherController.add",			//添加教师
			"com.yuxin.wx.controller.system.SysConfigTeacherController.update",			//修改教师
		};
		
		return !Arrays.asList(noNeed).contains(fullMethodName);
	}
	
}
