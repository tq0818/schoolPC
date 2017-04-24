package com.yuxin.wx.system.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.util.WebUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysBodyService;
import com.yuxin.wx.company.mapper.CompanyIndexModelClasstypeMapper;
import com.yuxin.wx.company.mapper.CompanyIndexModelCustomMapper;
import com.yuxin.wx.company.mapper.CompanyIndexModelItemMapper;
import com.yuxin.wx.company.mapper.CompanyIndexModelNewsMapper;
import com.yuxin.wx.company.mapper.CompanyIndexModelPrivatepageMapper;
import com.yuxin.wx.company.mapper.CompanyIndexTemplateMapper;
import com.yuxin.wx.model.company.CompanyIndexModelClasstype;
import com.yuxin.wx.model.company.CompanyIndexModelCustom;
import com.yuxin.wx.model.company.CompanyIndexModelItem;
import com.yuxin.wx.model.company.CompanyIndexModelNews;
import com.yuxin.wx.model.company.CompanyIndexModelPrivatepage;
import com.yuxin.wx.model.system.SysConfigIndexClasstype;
import com.yuxin.wx.model.system.SysConfigIndexCustom;
import com.yuxin.wx.model.system.SysConfigIndexItem;
import com.yuxin.wx.model.system.SysConfigIndexNews;
import com.yuxin.wx.model.system.SysConfigIndexPrivatepage;
import com.yuxin.wx.system.mapper.SysConfigIndexClasstypeMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexCustomMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexItemMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexModelMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexNewsMapper;
import com.yuxin.wx.system.mapper.SysConfigIndexPrivatepageMapper;
import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;
@Service
@Transactional
public class SysBodyServiceImpl extends BaseServiceImpl implements ISysBodyService{
	@Autowired
	private  SysConfigIndexClasstypeMapper sysConfigIndexClasstypeMapper;
	@Autowired
	private  SysConfigIndexCustomMapper sysConfigIndexCustomMapper;
	@Autowired
	private  SysConfigIndexItemMapper sysConfigIndexItemMapper;
	@Autowired
	private  SysConfigIndexModelMapper sysConfigIndexModelMapper;
	@Autowired
	private  SysConfigIndexNewsMapper sysConfigIndexNewsMapper;
	@Autowired
	private  SysConfigIndexPrivatepageMapper sysConfigIndexPrivatepageMapper;
	@Autowired
	private  CompanyIndexModelClasstypeMapper companyIndexModelClasstypeMapper;
	@Autowired
	private  CompanyIndexModelCustomMapper  companyIndexModelCustomMapper;
	@Autowired
	private  CompanyIndexModelItemMapper  companyIndexModelItemMapper;
	@Autowired
	private  CompanyIndexModelNewsMapper companyIndexModelNewsMapper;
	@Autowired
	private  CompanyIndexTemplateMapper companyIndexTemplateMapper;
	@Autowired
	private  CompanyIndexModelPrivatepageMapper companyIndexModelPrivatepageMapper;
	
	/**
	 * 发布到前台
	 * @author chopin
	 */
	public List<SysConfigPrivatePageVo> publishToFront(Integer companyId,Integer schoolId,Integer templateId){
		CompanyIndexModelPrivatepage search=new CompanyIndexModelPrivatepage();
		search.setCompanyId(companyId);
		search.setSchoolId(schoolId);
		search.setTemplateId(templateId);
		List<CompanyIndexModelPrivatepage> pages=companyIndexModelPrivatepageMapper.findByTemplate(search);
		SysConfigIndexPrivatepage searchBack= new SysConfigIndexPrivatepage();
		searchBack.setCompanyId(companyId);
		searchBack.setSchoolId(schoolId);
		List<SysConfigIndexPrivatepage> pagesBack=sysConfigIndexPrivatepageMapper.findByTemplate(searchBack);
		List<SysConfigPrivatePageVo> vo=new ArrayList<SysConfigPrivatePageVo>();
		for(SysConfigIndexPrivatepage page : pagesBack){
			if("INDEX_CLASSTYPE".equals(page.getType())){
				sysConfigIndexClasstypeMapper.deleteById(page.getConfigId());
			}
			if("INDEX_CUSTOM".equals(page.getType())){
				sysConfigIndexCustomMapper.deleteById(page.getConfigId());
			}
			if("INDEX_ITEM".equals(page.getType())){
				sysConfigIndexItemMapper.deleteById(page.getConfigId());
			}
			if("INDEX_NEWS".equals(page.getType())){
				sysConfigIndexNewsMapper.deleteById(page.getConfigId());
			}
			sysConfigIndexPrivatepageMapper.deleteById(page.getId());
		}
		for(CompanyIndexModelPrivatepage page : pages){
			SysConfigPrivatePageVo v=new SysConfigPrivatePageVo();
			v.setModelId(page.getModelId());
			v.setSchoolId(page.getSchoolId());
			v.setSort(page.getSort());
			v.setTemplateId(page.getTemplateId());
			v.setType(page.getType());
			v.setCompanyId(companyId);
			v.setDivcode(page.getDivcode());
			
			SysConfigIndexPrivatepage p=new SysConfigIndexPrivatepage();
			p.setModelId(page.getModelId());
			p.setSchoolId(page.getSchoolId());
			p.setSort(page.getSort());
			p.setType(page.getType());
			p.setCompanyId(companyId);
			p.setDivcode(page.getDivcode());

			if("INDEX_CLASSTYPE".equals(page.getType())){
				CompanyIndexModelClasstype classType=companyIndexModelClasstypeMapper.findById(page.getConfigId());
				if(classType!=null){
					SysConfigIndexClasstype entity=new SysConfigIndexClasstype ();
					if(classType.getClassTypeOne()!=null){
						entity.setClassTypeOne(String.valueOf(classType.getClassTypeOne()));
					}
					if(classType.getClassTypeTwo()!=null){
						entity.setClassTypeTwo(String.valueOf(classType.getClassTypeTwo()));
					}
					if(classType.getClassTypeThree()!=null){
						entity.setClassTypeThree(String.valueOf(classType.getClassTypeThree()));
					}
					entity.setTitle(classType.getTitle());
					sysConfigIndexClasstypeMapper.insert(entity);
					v.setClasstype(entity);
					v.setConfigId(entity.getId());
					p.setConfigId(entity.getId());
				}
			}
			if("INDEX_CUSTOM".equals(page.getType())){
				CompanyIndexModelCustom custom =companyIndexModelCustomMapper.findById(page.getConfigId());
				if(custom!=null){
					SysConfigIndexCustom entity=new SysConfigIndexCustom();
					entity.setDescription(custom.getDescription());
					entity.setLink(custom.getLink());
					entity.setPicture(custom.getPicture());
					entity.setTitle(custom.getTitle());
					entity.setTile(custom.getTile());
					sysConfigIndexCustomMapper.insert(entity);
					v.setCustom(entity);
					v.setConfigId(entity.getId());
					p.setConfigId(entity.getId());
				}
			}
			if("INDEX_ITEM".equals(page.getType())){
				CompanyIndexModelItem item=companyIndexModelItemMapper.findById(page.getConfigId());
				if(item!=null){
					SysConfigIndexItem entity=new SysConfigIndexItem();
					entity.setItemOneId(item.getItemOneId());
					entity.setShowItemSecond(item.getShowItemSecond());
					entity.setSort(item.getSort());
					entity.setSortBy(item.getSortBy());
					entity.setTitle(item.getTitle());
					entity.setRecommendFlag(item.getRecommendFlag()!=null?String.valueOf(item.getRecommendFlag()):"0");
					entity.setTeachType(item.getTeachType());
					sysConfigIndexItemMapper.insert(entity);
					v.setItem(entity);
					v.setConfigId(entity.getId());
					p.setConfigId(entity.getId());
				}
			}
			if("INDEX_NEWS".equals(page.getType())){
				CompanyIndexModelNews news=companyIndexModelNewsMapper.findById(page.getConfigId());
				if(news!=null){
					SysConfigIndexNews entity=new SysConfigIndexNews();
					entity.setDescription(news.getDescription());
					entity.setNewsId(news.getNewsId());
					entity.setPicture(news.getPicture());
					entity.setRecommentFlag(news.getRecommentFlag());
					entity.setSortBy(news.getSortBy());
					entity.setTitle(news.getTitle());
					sysConfigIndexNewsMapper.insert(entity);
					v.setNews(entity);
					v.setConfigId(entity.getId());
					p.setConfigId(entity.getId());
				}
			}
			sysConfigIndexPrivatepageMapper.insert(p);
			v.setId(p.getId());
			vo.add(v);
		}
		return vo;
	}
	
}
