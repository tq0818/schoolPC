package com.yuxin.wx.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.system.ISysConfigIndexClasstypeService;
import com.yuxin.wx.api.system.ISysConfigIndexCustomService;
import com.yuxin.wx.api.system.ISysConfigIndexItemService;
import com.yuxin.wx.api.system.ISysConfigIndexModelService;
import com.yuxin.wx.api.system.ISysConfigIndexNewsService;
import com.yuxin.wx.api.system.ISysConfigIndexPrivatepageService;
import com.yuxin.wx.api.system.ISysPageHeadFootService;
import com.yuxin.wx.common.CacheService.Ckey;
import com.yuxin.wx.common.Constants;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigIndexClasstype;
import com.yuxin.wx.model.system.SysConfigIndexCustom;
import com.yuxin.wx.model.system.SysConfigIndexItem;
import com.yuxin.wx.model.system.SysConfigIndexModel;
import com.yuxin.wx.model.system.SysConfigIndexNews;
import com.yuxin.wx.model.system.SysConfigIndexPrivatepage;
import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;
import com.yuxin.wx.vo.system.SysPageHeadFootVo;

@Component
public class DataBaseUtil{
	
	private static ISysConfigIndexClasstypeService sysConfigIndexClasstypeServiceImpl;
	private static  ISysConfigIndexCustomService sysConfigIndexCustomServiceImpl;
	private static ISysConfigIndexItemService sysConfigIndexItemServiceImpl;
	private static ISysConfigIndexModelService sysConfigIndexModelServiceImpl;
	private static ISysConfigIndexNewsService sysConfigIndexNewsServiceImpl;
	private static ISysConfigIndexPrivatepageService sysConfigIndexPrivatepageServiceImpl;
	private static ISysConfigDictService sysConfigDictServiceImpl;
	private static ISysPageHeadFootService sysPageHeadFootServiceImpl;
	private static ICompanyService companyServiceImpl;
	
	@Autowired
	public void setSysConfigIndexClasstypeServiceImpl(
			ISysConfigIndexClasstypeService sysConfigIndexClasstypeServiceImpl) {
		DataBaseUtil.sysConfigIndexClasstypeServiceImpl = sysConfigIndexClasstypeServiceImpl;
	}
	@Autowired
	public void setSysConfigIndexCustomServiceImpl(
			ISysConfigIndexCustomService sysConfigIndexCustomServiceImpl) {
		DataBaseUtil.sysConfigIndexCustomServiceImpl = sysConfigIndexCustomServiceImpl;
	}
	@Autowired
	public void setSysConfigIndexItemServiceImpl(
			ISysConfigIndexItemService sysConfigIndexItemServiceImpl) {
		DataBaseUtil.sysConfigIndexItemServiceImpl = sysConfigIndexItemServiceImpl;
	}
	@Autowired
	public void setSysConfigIndexModelServiceImpl(
			ISysConfigIndexModelService sysConfigIndexModelServiceImpl) {
		DataBaseUtil.sysConfigIndexModelServiceImpl = sysConfigIndexModelServiceImpl;
	}
	@Autowired
	public void setSysConfigIndexNewsServiceImpl(
			ISysConfigIndexNewsService sysConfigIndexNewsServiceImpl) {
		DataBaseUtil.sysConfigIndexNewsServiceImpl = sysConfigIndexNewsServiceImpl;
	}
	@Autowired
	public void setSysConfigIndexPrivatepageServiceImpl(
			ISysConfigIndexPrivatepageService sysConfigIndexPrivatepageServiceImpl) {
		DataBaseUtil.sysConfigIndexPrivatepageServiceImpl = sysConfigIndexPrivatepageServiceImpl;
	}
	@Autowired
	public void setSysConfigDictServiceImpl(
			ISysConfigDictService sysConfigDictServiceImpl) {
		DataBaseUtil.sysConfigDictServiceImpl = sysConfigDictServiceImpl;
	}
	@Autowired
	public void setSysPageHeadFootServiceImpl(
			ISysPageHeadFootService sysPageHeadFootServiceImpl) {
		DataBaseUtil.sysPageHeadFootServiceImpl = sysPageHeadFootServiceImpl;
	}
	@Autowired
	public void setCompanyService(ICompanyService companyServiceImpl) {
		DataBaseUtil.companyServiceImpl = companyServiceImpl;
	}
	
	public  static  List getList(Ckey ckey){
		if(Constants.MODULE_BODY.equals(ckey.getModule())){
			if(Constants.TAG_MODEL.equals(ckey.getTag())){
				List<SysConfigIndexModel> list=sysConfigIndexModelServiceImpl.findAll();
				return list;
			}else if(Constants.TAG_PAGE.equals(ckey.getTag())){
				SysConfigIndexPrivatepage search=new SysConfigIndexPrivatepage();
				search.setCompanyId(Integer.parseInt(ckey.getCompanyId()));
				search.setSchoolId(Integer.parseInt(ckey.getSchoolId()));
				List<SysConfigIndexPrivatepage> list=sysConfigIndexPrivatepageServiceImpl.findList2(search);
				return list;
			}else{
				//更新分校全套配置数据
				SysConfigIndexPrivatepage search =new SysConfigIndexPrivatepage();
				if(ckey.getCompanyId()!=null && !ckey.getCompanyId().equals("null")){
					search.setCompanyId(Integer.parseInt(ckey.getCompanyId()));
				}
				if(StringUtils.isNotBlank(ckey.getSchoolId()) && !ckey.getSchoolId().equals("null")){
					search.setSchoolId(Integer.parseInt(ckey.getSchoolId()));
				}
				List<SysConfigPrivatePageVo> list=sysConfigIndexPrivatepageServiceImpl.findList(search);
				List<SysConfigIndexItem> items=new ArrayList<SysConfigIndexItem>();
				List<SysConfigIndexClasstype > classTypes=new ArrayList<SysConfigIndexClasstype>();
				List<SysConfigIndexCustom > customs=new ArrayList<SysConfigIndexCustom>();
				List<SysConfigIndexNews> newss=new ArrayList<SysConfigIndexNews>();
				Ckey nkey=new Ckey();
				nkey.setCompanyId(ckey.getCompanyId());
				nkey.setModule(ckey.getModule());
				nkey.setSchoolId(ckey.getSchoolId());
				for(SysConfigPrivatePageVo v: list){
					if("INDEX_ITEM".equals(v.getType())){
						items.add(sysConfigIndexItemServiceImpl.findSysConfigIndexItemById(v.getConfigId()));
					}
					if("INDEX_CLASSTYPE".equals(v.getType())){
						classTypes.add(sysConfigIndexClasstypeServiceImpl.findSysConfigIndexClasstypeById(v.getConfigId()));
					}
					if("INDEX_CUSTOM".equals(v.getType())){
						customs.add(sysConfigIndexCustomServiceImpl.findSysConfigIndexCustomById(v.getConfigId()));
					}
					if("INDEX_NEWS".equals(v.getType())){
						newss.add(sysConfigIndexNewsServiceImpl.findSysConfigIndexNewsById(v.getConfigId()));
					}
				}
				if(Constants.TAG_ITEM.equals(ckey.getTag())){
					return items;
				}
				if(Constants.TAG_CLASSTYPE.equals(ckey.getTag())){
					return classTypes;
				}
				if(Constants.TAG_CUSTOM.equals(ckey.getTag())){
					return customs;
				}
				if(Constants.TAG_NEWS.equals(ckey.getTag())){
					return newss;
				}
				return list;
			}
		}
		if(Constants.MODULE_DICT.equals(ckey.getModule())){
			List<SysConfigDict> list=sysConfigDictServiceImpl.findAll();
			return list;
		}
		if(Constants.MODULE_HEADFOOT.equals(ckey.getModule())){
			List<SysPageHeadFootVo> list=sysPageHeadFootServiceImpl.findAll();
			return list;
		}
		if(Constants.MODULE_COMPANY.equals(ckey.getModule())){
			List<Company> list=companyServiceImpl.findAll();
			return list;
		}
		
		return null;
	}
	
	public static Object getObject(Ckey ckey){
		return null;
	}
	
	public static String getString(Ckey ckey){
		return "";
	}
	
}
