package com.yuxin.wx.common;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.user.Users;

/*****
 * 系统启动时初始化，加载常用系统参数至内存
 * 目前包括字典表
 * @author sun.xb
 *
 */
@Controller
@RequestMapping("/sysLoader")
public class SysLoader {
	
	/**
	 * 
	 * Class Name: SysLoader.java
	 * @Description: 根据itemcode获取对应字典对象
	 * @author Chopin
	 * @date 2014年12月30日
	 * @version 1.0
	 * @param itemCode
	 * @return SysConfigDict
	 */
	public static SysConfigDict getDictionary(String itemCode) {
		return CacheService.getDictionary(itemCode);
	}
	
	/**
	 * 
	 * Class Name: SysLoader.java
	 * @Description: 根据itemcode获取对应字典的itemvalue
	 * @author Chopin
	 * @date 2014年12月30日
	 * @version 1.0
	 * @param itemCode
	 * @return SysConfigDict
	 */
	public static SysConfigDict getDict(String itemCode) {
		return CacheService.getDict(itemCode);
	}

	/**
	 * 
	 * Class Name: SysLoader.java
	 * @Description: 设置字典
	 * @author Chopin
	 * @date 2014年12月30日
	 * @version 1.0
	 * @param itemCode
	 * @param itemValue
	 */
	public static void setDictionary(String itemCode,String itemValue) {
		CacheService.setDictionary(itemCode, itemValue);
	}
	
	/**
	 * 
	 * Class Name: SysLoader.java
	 * @Description: 设置字典
	 * @author Chopin
	 * @date 2014年12月30日
	 * @version 1.0
	 * @param itemCode
	 * @param entity
	 */
	public static void setDictionary(String itemCode,SysConfigDict entity) {
		CacheService.setDictionary(itemCode, entity);
	}
	

	/**
	 * 
	 * Class Name: SysLoader.java
	 * @Description: 根据字典名称查对应字典列表
	 * @author Chopin
	 * @date 2014年12月11日 上午12:16:47
	 * @version 1.0
	 * @param dictName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getdict/dictname/{dictName}", method = RequestMethod.POST)
	public static List<SysConfigDict>  getSysDicList(@PathVariable String dictName){
		Subject subject = SecurityUtils.getSubject();
		Integer companyId=null;
		if(subject != null){
			Users user = (Users)subject.getSession().getAttribute("loginUser");
			companyId=user.getCompanyId();
		}
		return CacheService.getSysDicList(dictName,companyId);
	}
	
	/**
	 * 
	 * Class Name: SysLoader.java
	 * @Description: 根据字典代码查对应字典列表
	 * @author Chopin
	 * @date 2014年12月11日 上午12:16:57
	 * @version 1.0
	 * @param dicCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getdict/dictcode/{dicCode}", method = RequestMethod.POST)
	public static List<SysConfigDict> getSysDicByDicCode(@PathVariable String dicCode){
		/*Subject subject = SecurityUtils.getSubject();
		Integer companyId=null;
		if(subject != null){
			Users user = (Users)subject.getSession().getAttribute("loginUser");
			companyId=user.getCompanyId();
		}*/
		return CacheService.getSysDicByDicCode(dicCode,1);
	}
	
	/**
	 * 
	 * Class Name: SysLoader.java
	 * @Description: 根据字典代码查对应字典列表
	 * @author Chopin
	 * @date 2014年12月11日 上午12:16:57
	 * @version 1.0
	 * @param dicCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getdict/{itemCode}", method = RequestMethod.GET)
	public static String getSysDicByItemCode(@PathVariable String itemCode){
		return CacheService.dictCode2Name(itemCode);
	}
	
	
	/**
	 * 
	 * Class Name: SysLoader.java
	 * @Description: 根据字典代码查对应字典列表
	 * @author Chopin
	 * @date 2014年12月11日 上午12:16:57
	 * @version 1.0
	 * @param dicCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getdict/{id}", method = RequestMethod.POST)
	public static String getSysDicById(@PathVariable String id){
		return CacheService.getSysDicById(id);
	}
	
	/**
	 * 
	 * Class Name: SysLoader.java
	 * @Description: 根据字典代码查对应字典列表
	 * @author Chopin
	 * @date 2014年12月11日 上午12:16:57
	 * @version 1.0
	 * @param dicCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/subdict/{pcode}&{type}", method = RequestMethod.POST)
	public static List<SysConfigDict> getSubDic(@PathVariable String pcode,@PathVariable String type){
		Integer pid=CacheService.getSysDicByItemCode(pcode).getId();
		String dcode="";
		if("reason".equals(type)){
			if(pid==94){
				dcode="STU_REASON_DETAIL";
			}else if(pid==95){
				dcode="SCH_REASON_DETAIL";
			}else if(pid==96){
				dcode="SCH_END_REASON_DETAIL";
			}else {
				return null;
			}
			
		}else if("depart".equals(type)){
			if(pid==94){
				dcode="STU_RESP_PARTY";
			}else if(pid==95){
				dcode="SCH_RESP_PARTY";
			}else if(pid==96){
				dcode="SCH_END_RESP_PARTY";
			}else{
				return null;
			}
		}
		List<SysConfigDict> list=CacheService.getSysDicByPid(pid,dcode);
		return list;
	}
	
	/**
	 * 
	 * Class Name: SysLoader.java
	 * @Description: 转换字典，代码转为汉字
	 * @author Chopin
	 * @date 2014年12月22日
	 * @version 1.0
	 * @param dicCode
	 * @param itemCode
	 * @return
	 */
	public static String dictCode2Name(String itemCode){
		return CacheService.dictCode2Name(itemCode);
	}
	
	/**
	 * 
	 * Class Name: SysLoader.java
	 * @Description: 转换字典，汉字转为代码
	 * @author Chopin
	 * @date 2014年12月22日
	 * @version 1.0
	 * @param dicCode
	 * @param itemValue
	 * @return
	 */
	public static String dictName2Code(String itemValue){
		return CacheService.dictName2Code(itemValue);
	}

	public static List<SysConfigDict> dict(String dictCode){
		Subject subject = SecurityUtils.getSubject();
		Integer companyId=null;
		if(subject != null){
			Users user = (Users)subject.getSession().getAttribute("loginUser");
			companyId=user.getCompanyId();
		}
		return CacheService.getSysDicByDicCode(dictCode,companyId);
	}
}
