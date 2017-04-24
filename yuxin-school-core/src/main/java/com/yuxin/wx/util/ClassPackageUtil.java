package com.yuxin.wx.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.classes.IClassPackageCategoryService;
import com.yuxin.wx.api.classes.IClassPackageService;
import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.model.classes.ClassPackageCategory;

@Component
public class ClassPackageUtil {
	
	public static final String IDTYPE_COMMODITYID = "COMMODITYID";
	
	private static IClassPackageService classPackageServiceImpl;
	
	@Autowired
	public void setClassPackageServiceImpl(IClassPackageService classPackageServiceImpl) {
		ClassPackageUtil.classPackageServiceImpl = classPackageServiceImpl;
	}
	
	private static IClassPackageCategoryService classPackageCategoryServiceImpl;
	
	@Autowired
	public void setClassPackageCategoryServiceImpl(IClassPackageCategoryService classPackageCategoryServiceImpl) {
		ClassPackageUtil.classPackageCategoryServiceImpl = classPackageCategoryServiceImpl;
	}

	/**
	 * 
	 * Class Name: ClassPackageUtil.java
	 * @Description: 获取课程包url
	 * @author dongshuai
	 * @date 2017年4月7日 下午7:20:12
	 * @modifier
	 * @modify-date 2017年4月7日 下午7:20:12
	 * @version 1.0
	 * @param code
	 * @return
	 */
	public static String getClassPackageUrl(Integer companyId, String code){
		StringBuffer stringBuffer = new StringBuffer();
		
		ClassPackageCategory search = new ClassPackageCategory();
		search.setDelFlag(0);
		search.setCompanyId(companyId);
		try {
			String[] codes = resolveCode(code).split("-");
			for (String string : codes) {
				search.setCode(string);
				ClassPackageCategory category = classPackageCategoryServiceImpl.queryClassPackageCategoryByCode(search);
				if(category != null){
					stringBuffer.append(category.getName()).append("-");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuffer.length()>0?stringBuffer.deleteCharAt(stringBuffer.length()-1).toString():stringBuffer.toString();
	}
	
	
	
	/**
	 * 
	 * Class Name: ClassPackageUtil.java
	 * @Description: 解析课程包code
	 * @author dongshuai
	 * @date 2017年4月7日 下午7:18:56
	 * @modifier
	 * @modify-date 2017年4月7日 下午7:18:56
	 * @version 1.0
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static String resolveCode(String code) throws Exception{
		StringBuffer s = new StringBuffer();
		
		if(StringUtils.isEmpty(code))  		throw new Exception("code is empty");
		if(code.length()>9) 		   		throw new Exception("code is too long");
		if(code.length() % 3 != 0) 	   		throw new Exception("code error");
		if(!StringUtils.isNumeric(code)) 	throw new Exception("code error");
		
		switch(code.length()){
		case 3:
			s.append(code);
			break;
		case 6:
			s.append(code.substring(0, 3)).append("-").append(code.substring(0,code.length()));
			break;
		case 9:
			s.append(code.substring(0, 3)).append("-").append(code.substring(0, 6)).append("-").append(code.substring(0,code.length()));
			break;
		}
		
		return s.toString();
	}
	
	/**
	 * 
	 * Class Name: ClassPackageUtil.java
	 * @Description: 获取课程包名称
	 * @author dongshuai
	 * @date 2017年4月10日 上午10:57:19
	 * @modifier
	 * @modify-date 2017年4月10日 上午10:57:19
	 * @version 1.0
	 * @param map
	 * @param type
	 * @return
	 */
	public static String getClassPackageName(Integer id, String type){
		String name = "";
		
		switch (type) {
		case IDTYPE_COMMODITYID:
			ClassPackage classPackage = classPackageServiceImpl.queryClassPackageByComId(id);
			name = classPackage != null? classPackage.getName(): "";
			break;
		}
		
		return name;
	}
}
