package com.yuxin.wx.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")
public class ParameterRequestHandler {
	
	private static List<String> maxLevelUrl = new ArrayList<String>();
	
	private static List<String> minLevelUrl = new ArrayList<String>();
	
	private static List<String> vipUrl = new ArrayList<String>();
	
	private static List<String> maxList;	//高级别		1
	
	private static List<String> minList;	//低级别		2
	
	private static List<String> formalList;	//默认级别	0
	
	private static List<String> vipList;	//特殊级别	-1
	
	/*
	 * 初始替换list
	 */
	static{
		//向下兼容，vip特殊
		maxList = new ArrayList<String>() {{ 
	        add("'"); 
	        add("\""); 
	        add("|"); 
	        add("$"); 
	        add("%"); 
	        add("<"); 
	        add(">"); 
		}}; 
		minList = new ArrayList<String>() {{}}; 
		formalList = new ArrayList<String>() {{ 
			add("script"); 
	        add("iframe");
	        add("insert");
	        add("delete");
	        add("update");
	        add("select"); 
	        add("where");
		}}; 
		maxList.addAll(minList);
		maxList.addAll(formalList);
		minList.addAll(formalList);
		vipList = new ArrayList<String>(){{}};
	}
	
	/*
	 * 主方法 返回处理的request
	 */
	public static HttpServletRequest execute(HttpServletRequest request){
		 request = parameterHandler(request, chooseLevel(request));
		 return request;
	}
	
	/*
	 * 级别判断 0：默认级别 1：最高级别 2：低级别 -1:特殊级别
	 */
	private static Integer chooseLevel(HttpServletRequest request){
		String uri = request.getRequestURI();
		for (String s : maxLevelUrl) {
			if (uri.indexOf(s) != -1) {
				return 1;
			}
		}
		for (String s : minLevelUrl) {
			if (uri.indexOf(s) != -1) {
				return 2;
			}
		}
		for (String s : vipUrl) {
			if (uri.indexOf(s) != -1) {
				return -1;
			}
		}
		return 0;
	}
	
	/*
	 * 参数操作
	 */
	private static HttpServletRequest parameterHandler(HttpServletRequest request,Integer level){
		switch (level) {
			case 0:
				request = new ParameterRequestWrapper(request, parameterReplace(request, formalList));
				break;
			case 1:
				request = new ParameterRequestWrapper(request, parameterReplace(request, maxList));
				break;
			case 2:
				request = new ParameterRequestWrapper(request, parameterReplace(request, minList));
				break;
			case -1:
				request = new ParameterRequestWrapper(request, parameterReplace(request, vipList));
				break;
			default:
				break;
		}
		return request;
	}
	
	/*
	 * 异常参数替换
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static HashMap parameterReplace(HttpServletRequest request,List<String> list){
		HashMap hashMap=new HashMap(request.getParameterMap()); 
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String, String[]>> sets = map.entrySet();
		for (Entry<String, String[]> entry : sets) {
			String paramName = entry.getKey();
			String paramValue = "";
			for (String v : entry.getValue()) {
				paramValue += v;
			}
			for (String s : list) {
				if(paramValue.indexOf(s) != -1){
					paramValue = paramValue.replace(s, "  ");
					hashMap.put(paramName, paramValue);
				}
				if(paramValue.indexOf(s.toUpperCase()) != -1){
					paramValue = paramValue.replace(s.toUpperCase(), "  ");
					hashMap.put(paramName, paramValue);
				}
			}
		}
		return hashMap;
	}

	public List<String> getMaxLevelUrl() {
		return maxLevelUrl;
	}

	public void setMaxLevelUrl(List<String> maxLevelUrl) {
		ParameterRequestHandler.maxLevelUrl = maxLevelUrl;
	}

	public List<String> getMinLevelUrl() {
		return minLevelUrl;
	}

	public void setMinLevelUrl(List<String> minLevelUrl) {
		ParameterRequestHandler.minLevelUrl = minLevelUrl;
	}

	public List<String> getMaxList() {
		return maxList;
	}

	public void setMaxList(List<String> maxList) {
		ParameterRequestHandler.maxList = maxList;
	}

	public List<String> getMinList() {
		return minList;
	}

	public void setMinList(List<String> minList) {
		ParameterRequestHandler.minList = minList;
	}

	public List<String> getFormalList() {
		return formalList;
	}

	public void setFormalList(List<String> formalList) {
		ParameterRequestHandler.formalList = formalList;
	}

	public List<String> getVipUrl() {
		return vipUrl;
	}

	public void setVipUrl(List<String> vipUrl) {
		ParameterRequestHandler.vipUrl = vipUrl;
	}

	public List<String> getVipList() {
		return vipList;
	}

	public void setVipList(List<String> vipList) {
		ParameterRequestHandler.vipList = vipList;
	}

	
}
