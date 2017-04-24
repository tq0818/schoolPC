package com.yuxin.wx.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigIndexClasstype;
import com.yuxin.wx.model.system.SysConfigIndexCustom;
import com.yuxin.wx.model.system.SysConfigIndexItem;
import com.yuxin.wx.model.system.SysConfigIndexModel;
import com.yuxin.wx.model.system.SysConfigIndexNews;
import com.yuxin.wx.model.system.SysConfigIndexPrivatepage;
import com.yuxin.wx.system.mapper.SysConfigDictMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexClasstypeMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexCustomMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexItemMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexModelMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexNewsMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexPrivatepageMapper;
import com.yuxin.wx.util.ComparatorForPrivatePage;
import com.yuxin.wx.util.DataBaseUtil;
import com.yuxin.wx.util.JedisUtil;
import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;
import com.yuxin.wx.vo.system.SysPageHeadFootVo;


@Component
public class CacheService {
	static Log log=LogFactory.getLog("log");
	
	private static Properties props = new Properties();
	private static Resource resource = new ClassPathResource("/config.properties");
	
	
	// key组成规则  模块-公司ID-分校ID-关键字
	
	public static class Ckey{
		private String module;
		private String companyId="0";
		private String schoolId="0";
		private String tag="";
		
		public Ckey(){
			this.module="";
			this.companyId="0";
			this.schoolId="0";
			this.tag="";
		}
		
		public Ckey(String module){
			this.module=module;
			this.companyId="0";
			this.schoolId="0";
			this.tag="";
		}
		public Ckey(String module,String company){
			this.module=module;
			this.companyId=company;
			this.schoolId="0";
			this.tag="";
		}
		
		public Ckey(String module,String company,String schoolId){
			this.module=module;
			this.companyId=company;
			this.schoolId=schoolId;
			this.tag="";
		}
		
		public Ckey(String module,String company,String schoolId,String tag){
			this.module=module;
			this.companyId=company;
			this.schoolId=schoolId;
			this.tag=tag;
		}
		
		public String getModule() {
			return module;
		}
		public void setModule(String module) {
			this.module = module;
		}
		public String getCompanyId() {
			return companyId;
		}
		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}
		public String getSchoolId() {
			return schoolId;
		}
		public void setSchoolId(String schoolId) {
			this.schoolId = schoolId;
		}
		public String getTag() {
			return tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
		@Override
		public String toString() {
			StringBuffer keyString=new StringBuffer();
			keyString.append(getModule()).append("-").append(companyId);
			if(StringUtils.isNotBlank(schoolId)){
				keyString.append("-").append(schoolId);
			}
			if(StringUtils.isNotBlank(tag)){
				keyString.append("@").append(tag);
			}
			
			return keyString.toString();
		}
		
		public Ckey toObject(String keyString){
			Ckey ckey=new Ckey();
			String[] keys=keyString.split("-");
			ckey.setModule(keys[0]);
			ckey.setCompanyId(keys[1]);
			if(keys.length<3){
				ckey.setTag(keys[2]);
			}else{
				ckey.setSchoolId(keys[2]);
				ckey.setTag(keys[3]);
			}
			return ckey;
		}
	}

	/****
	 * 系统启动时初始化
	 */
	@PostConstruct
	public void init(){
		try{
			props=PropertiesLoaderUtils.loadProperties(resource);
		}catch(Exception e){
			log.error("properties配置文件读取失败:"+e,e);
			e.printStackTrace();
		}
	}
	
	public static String getUserPages(Integer companyId,Integer schoolId){
		
		Ckey ckey=new Ckey(Constants.MODULE_BODY,""+companyId,""+schoolId,Constants.TAG_PAGE);
		List<SysConfigIndexPrivatepage> pages=getList(ckey);
		Collections.sort(pages,new ComparatorForPrivatePage());
		return JSONArray.toJSONString(pages);
	}
	
	public static String getModels(){
		Ckey ckey=new Ckey(Constants.MODULE_BODY);
		ckey.setTag(Constants.TAG_MODEL);
		List<SysConfigIndexModel> models=getList(ckey);
		return JSONArray.toJSONString(models);
	}
	
	public static String getData(String json){
		List<SysConfigIndexPrivatepage> pages= JSONArray.parseArray(json, SysConfigIndexPrivatepage.class);
		if(pages==null || pages.isEmpty()){
			return null;
		}
		List<String> list= new ArrayList<String>();
		for(SysConfigIndexPrivatepage page: pages){	
			Ckey ckey=new Ckey(Constants.MODULE_BODY);
			ckey.setCompanyId(""+page.getCompanyId());
			ckey.setSchoolId(""+page.getSchoolId());
			if(page.getType().equals("INDEX_ITEM")){
				ckey.setTag(Constants.TAG_ITEM);
				List<SysConfigIndexItem> items=getList(ckey);
				for(SysConfigIndexItem item: items){
					if(item!=null && item.getId().equals(page.getConfigId())){
						list.add(JSONObject.toJSONString(item));
					}
				}
			}else if(page.getType().equals("INDEX_CLASSTYPE")){
				ckey.setTag(Constants.TAG_CLASSTYPE);
				List<SysConfigIndexClasstype> classTypes=getList(ckey);
				for(SysConfigIndexClasstype classType: classTypes){
					if(classType!=null && classType.getId().equals(page.getConfigId())){
						list.add(JSONObject.toJSONString(classType));
					}
				}
			}else if(page.getType().equals("INDEX_CUSTOM")){
				ckey.setTag(Constants.TAG_CUSTOM);
				List<SysConfigIndexCustom> customs=getList(ckey);
				for(SysConfigIndexCustom custom: customs){
					if(custom!=null && custom.getId().equals(page.getConfigId())){
						list.add(JSONObject.toJSONString(custom));
					}
				}
			}else if(page.getType().equals("INDEX_NEWS")){
				ckey.setTag(Constants.TAG_NEWS);
				List<SysConfigIndexNews> newss=getList(ckey);
				for(SysConfigIndexNews news: newss){
					if(news != null && news.getId().equals(page.getConfigId())){
						list.add(JSONObject.toJSONString(news));
					}
				} 
			}else if(page.getType().equals("code")){
				
			}
		}
		StringBuffer jsonArray=new StringBuffer();
		jsonArray.append("[");
		for(int i=0;i<list.size();i++){
			String l=list.get(i);
			if(i+1==list.size()){
				jsonArray.append(l);
			}else{
				jsonArray.append(l).append(",");
			}
		}
		jsonArray.append("]");
		return jsonArray.toString();
	}
	
	public static  List getList(Ckey key){
		List list=new ArrayList();
		if(Constants.CACHE_SOURCE_REDIS.equals(props.getProperty("yuuxin.cache.source"))){
			list=JedisUtil.getList(key.toString());
			if(list==null || list.isEmpty()){
				list=DataBaseUtil.getList(key);
				updateCache(key, list);
			}
		}else{
			list=DataBaseUtil.getList(key);
		}
		return list;
	}
	
	public static BaseEntity getObject(Ckey key){
		
		return null;
	}
	
	public static String getString(Ckey key){
		
		return "";
	}
	
	/**
	 * 更新到缓存
	 * @param module
	 * @param tag
	 * @return boolean 如果为true,说明从库里加载到了数据，false则没有
	 */
	public static Boolean updateCache(Ckey ckey,List list){
		log.info("[CacheService]->[updateCache]:ckey="+ckey);
		if(Constants.MODULE_BODY.equals(ckey.getModule())){
			if(Constants.TAG_MODEL.equals(ckey.getTag())){
				List<SysConfigIndexModel> models=(List<SysConfigIndexModel>)list;
				if(list!=null && !list.isEmpty()){
					log.info("[CacheService]->[updateCache]:body->存redis，list.length="+list.size());
					JedisUtil.put(ckey.toString(), list);
					return true;
				}else{
					return false;
				}
			}else{
				//更新分校全套配置数据
				List<SysConfigIndexItem> items=new ArrayList<SysConfigIndexItem>();
				List<SysConfigIndexClasstype > classTypes=new ArrayList<SysConfigIndexClasstype>();
				List<SysConfigIndexCustom > customs=new ArrayList<SysConfigIndexCustom>();
				List<SysConfigIndexNews> newss=new ArrayList<SysConfigIndexNews>();
				if(Constants.TAG_ITEM.equals(ckey.getTag())){
					items=(List<SysConfigIndexItem>)list;
					JedisUtil.put(ckey.toString(), items);
				}
				if(Constants.TAG_CLASSTYPE.equals(ckey.getTag())){
					classTypes=(List<SysConfigIndexClasstype>)list;
					JedisUtil.put(ckey.toString(), classTypes);
				}
				if(Constants.TAG_CUSTOM.equals(ckey.getTag())){
					customs=(List<SysConfigIndexCustom>)list;
					JedisUtil.put(ckey.toString(), customs);
				}
				if(Constants.TAG_NEWS.equals(ckey.getTag())){
					newss=(List<SysConfigIndexNews>)list;
					JedisUtil.put(ckey.toString(), newss);
				}
				
				if((items!=null && !items.isEmpty())||(classTypes!=null && !classTypes.isEmpty())
						||(customs!=null && !customs.isEmpty())||(newss!=null && !newss.isEmpty())){
					return true;
				}else{
					return false;
				}
			}
		}
		if(Constants.MODULE_DICT.equals(ckey.getModule())){
			List<SysConfigDict> dict=list;
			JedisUtil.put(ckey.toString(), dict);
		}
		if(Constants.MODULE_COMPANY.equals(ckey.getModule())){
			List<Company> company=list;
			JedisUtil.put(ckey.toString(), company);
		}
		if(Constants.MODULE_HEADFOOT.equals(ckey.getModule())){
			List<SysPageHeadFootVo> headFoot=list;
			JedisUtil.put(ckey.toString(), headFoot);
		}
		return true;
	}
	
	
	/**
	 * 
	 * Class Name: SysConfigUtil.java
	 * @Description: 根据itemcode获取对应字典对象
	 * @author Chopin
	 * @date 2014年12月30日
	 * @version 1.0
	 * @param itemCode
	 * @return SysConfigDict
	 */
	public static SysConfigDict getDictionary(String itemCode) {
		Ckey ckey=new Ckey();
		ckey.setModule(Constants.MODULE_DICT);
		List<SysConfigDict> dict=getList(ckey);
		for(SysConfigDict d : dict){
			if(d.getItemCode().equals(itemCode)){
				return d;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * Class Name: SysConfigUtil.java
	 * @Description: 根据itemcode获取对应字典的itemvalue
	 * @author Chopin
	 * @date 2014年12月30日
	 * @version 1.0
	 * @param itemCode
	 * @return SysConfigDict
	 */
	public static SysConfigDict getDict(String itemCode) {
		return getDictionary(itemCode);
	}

	/**
	 * 
	 * Class Name: SysConfigUtil.java
	 * @Description: 设置字典
	 * @author Chopin
	 * @date 2014年12月30日
	 * @version 1.0
	 * @param itemCode
	 * @param itemValue
	 */
	public static void setDictionary(String itemCode,String itemValue) {
		Ckey ckey=new Ckey(Constants.MODULE_DICT);
		List<SysConfigDict> dict=getList(ckey);
		for(SysConfigDict d : dict){
			if(d.getItemCode().equals(itemCode)){
				d.setItemValue(itemValue);
				break;
			}
		}
		JedisUtil.put(ckey.toString(), dict);
	}
	
	/**
	 * 
	 * Class Name: SysConfigUtil.java
	 * @Description: 设置字典
	 * @author Chopin
	 * @date 2014年12月30日
	 * @version 1.0
	 * @param itemCode
	 * @param entity
	 */
	public static void setDictionary(String itemCode,SysConfigDict entity) {
		Ckey ckey=new Ckey(Constants.MODULE_DICT);
		List<SysConfigDict> dict=getList(ckey);
		for(SysConfigDict d : dict){
			if(d.getItemCode().equals(itemCode)){
				d=entity;
				break;
			}
		}
		JedisUtil.put(ckey.toString(), dict);
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
	public static List<SysConfigDict>  getSysDicList(String dictName,Integer companyId){
		List<SysConfigDict> dict=getList(new Ckey(Constants.MODULE_DICT));
		return dict;
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
	public static List<SysConfigDict> getSysDicByDicCode(String dicCode,Integer companyId){
		List<SysConfigDict> dict=getList(new Ckey(Constants.MODULE_DICT));
		List<SysConfigDict> dic=new ArrayList<SysConfigDict>();
		for(SysConfigDict d : dict){
			if(d.getDictCode().equals(dicCode)){
				dic.add(d);
			}
		}
		return dic;
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
	public static SysConfigDict getSysDicByItemCode(String itemCode){
		return getDict(itemCode);
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
	public static String getSysDicById(String id){
		List<SysConfigDict> dict=getList(new Ckey(Constants.MODULE_DICT));
		for(SysConfigDict d : dict){
			if(d.getId().equals(id)){
				return d.getItemValue();
			}
		}
		return null;
	}
	
	/**
	 * 
	 * Class Name: SysLoader.java
	 * @Description: 根据字典父ID查对应子字典列表
	 * @author Chopin
	 * @date 2014年12月11日 上午12:16:57
	 * @version 1.0
	 * @param dicCode
	 * @return
	 */
	public static List<SysConfigDict> getSysDicByPid(Integer pid,String dcode){
		List<SysConfigDict> dict=getList(new Ckey(Constants.MODULE_DICT));
		List<SysConfigDict> list=new ArrayList<SysConfigDict>();
		for(SysConfigDict d : dict){
			if(pid.equals(d.getParentItemId())){
				if(StringUtils.isNotBlank(dcode) && dcode.equals(d.getDictCode())){
					list.add(d);
				}
			}
		}
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
	    if(getDict(itemCode)!=null){
	        return getDict(itemCode).getItemValue();
	    }else{
	        return null;
	    }
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
		List<SysConfigDict> dict=getList(new Ckey(Constants.MODULE_DICT));
		for(SysConfigDict d : dict){
			if(d.getItemValue().equals(itemValue)){
				return d.getItemCode();
			}
		}
		return null;
	}

}
