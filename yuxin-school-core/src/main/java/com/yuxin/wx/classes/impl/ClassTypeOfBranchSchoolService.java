package com.yuxin.wx.classes.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.classes.IClassTypeOfBranchSchoolService;
import com.yuxin.wx.classes.mapper.ClassTypeMapper;
import com.yuxin.wx.classes.mapper.ClassTypeOfBranchSchoolMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassTypeResource;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.system.mapper.SysConfigDictMapper;
import com.yuxin.wx.vo.classes.ClassTypeResourceVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;

@Service
@Transactional
public class ClassTypeOfBranchSchoolService extends BaseServiceImpl implements
		IClassTypeOfBranchSchoolService {
	
	@Autowired
	private SysConfigDictMapper sysConfigDictMapper;
	
	@Autowired
	private ClassTypeOfBranchSchoolMapper classTypeOfBranchSchoolMapper;


	@Override
	public List<SysConfigDict> findByParentId(String parentId) {
		// TODO Auto-generated method stub
		return sysConfigDictMapper.findByParentId(parentId);
	}
	
	@Override
	public List<SysConfigDict> findByDicCode(String code) {
		return sysConfigDictMapper.findByDicCode(code);
	}

	@Override
	public List<ClassTypeVo> queryClassTypeOfBranchSchool(Map<String, Object> param) {
		return classTypeOfBranchSchoolMapper.queryClassTypeOfBranchSchool(param);
	}

	@Override
	public int queryCountClassTypeOfBranchSchool(Map<String, Object> param) {
		return classTypeOfBranchSchoolMapper.queryCountClassTypeOfBranchSchool(param);
	}


	@Override
	public List<SysConfigItemRelation> findItemFront(
			Map<String, Object> param) {
		return classTypeOfBranchSchoolMapper.findItemFront(param);
	}

	@Override
	public void battchUpOrDown(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classTypeOfBranchSchoolMapper.battchUpOrDown(param);
	}

	public ClassTypeVo findClassTypeDetail(Map<String,String> map) {
		ClassTypeVo classvo=classTypeOfBranchSchoolMapper.findDetailById(map);
		List<ClassModule> classModules=classTypeOfBranchSchoolMapper.findListByClassId(map);
		List<CourseVideoChapter> chapters=classTypeOfBranchSchoolMapper.findByClassId(map);
		Integer totalClass=0;
		if(null!=classModules&&classModules.size()>0){
			for(ClassModule classModule: classModules){
				if(classModule.getTotalClassHour()!=null){
					totalClass+=classModule.getTotalClassHour();
				}
			}
		}
		classvo.setModules(classModules);
		classvo.setVideos(chapters);
		//classvo.setRemotes(classTypeOfBranchSchoolMapper.findRemotesByClassTypeId(map));
		classvo.setTotalClass(totalClass);
		return classvo;
	}

	@Override
	public void setCddsRecommendFlag(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classTypeOfBranchSchoolMapper.setCddsRecommendFlag(param);
	}

	@Override
	public void setSaleOrNoSale(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classTypeOfBranchSchoolMapper.setSaleOrNoSale(param);
	}

	@Override
	public List<ClassTypeResourceVo> findResBy(ClassTypeResource res) {
		// TODO Auto-generated method stub
		return classTypeOfBranchSchoolMapper.findResBy(res);
	}

	@Override
	public Integer findResCountBy(ClassTypeResource res) {
		// TODO Auto-generated method stub
		return classTypeOfBranchSchoolMapper.findResCountBy(res);
	};
	
	
	
}
