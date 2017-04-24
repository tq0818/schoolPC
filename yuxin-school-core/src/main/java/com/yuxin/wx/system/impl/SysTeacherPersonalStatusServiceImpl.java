package com.yuxin.wx.system.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysTeacherPersonalStatusService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.system.SysTeacherPersonalStatus;
import com.yuxin.wx.model.system.SysTeacherPersonalStatusPic;
import com.yuxin.wx.model.system.SysTeacherPersonalStatusReplay;
import com.yuxin.wx.system.mapper.SysConfigTeacherMapper;
import com.yuxin.wx.system.mapper.SysTeacherPersonalStatusMapper;
import com.yuxin.wx.system.mapper.SysTeacherPersonalStatusPicMapper;
import com.yuxin.wx.system.mapper.SysTeacherPersonalStatusReplayMapper;
import com.yuxin.wx.vo.system.TeacherDynamicsVo;

/**
 * Service Implementation:SysTeacherPersonalStatus
 * @author chopin
 * @date 2015-10-26
 */
@Service
@Transactional
public class SysTeacherPersonalStatusServiceImpl extends BaseServiceImpl implements ISysTeacherPersonalStatusService {

	@Autowired
	private SysTeacherPersonalStatusMapper sysTeacherPersonalStatusMapper;
	@Autowired
	private SysConfigTeacherMapper sysConfigTeacherMapper;
	@Autowired
	private SysTeacherPersonalStatusReplayMapper sysTeacherPersonalStatusReplayMapper;
	@Autowired
	private SysTeacherPersonalStatusPicMapper sysTeacherPersonalStatusPicMapper;
	/**
	 * 
	* @Title: saveSysTeacherPersonalStatus
	* @Description: 新增SysTeacherPersonalStatus
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	@Override
	public void insert(SysTeacherPersonalStatus entity){
		sysTeacherPersonalStatusMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysTeacherPersonalStatus 
	* @Description: 批量新增SysTeacherPersonalStatus
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysTeacherPersonalStatus> entity){
		sysTeacherPersonalStatusMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysTeacherPersonalStatus 
	* @Description: 编辑SysTeacherPersonalStatus
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	@Override
	public void update(SysTeacherPersonalStatus entity){
		sysTeacherPersonalStatusMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysTeacherPersonalStatusById 
	* @Description: 根据id删除SysTeacherPersonalStatus
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	 @Override
	public void deleteSysTeacherPersonalStatusById(Integer id){
		sysTeacherPersonalStatusMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysTeacherPersonalStatusByIds 
	* @Description: 根据id批量删除SysTeacherPersonalStatus
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	@Override
	public void deleteSysTeacherPersonalStatusByIds(Integer[] ids){
		sysTeacherPersonalStatusMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysTeacherPersonalStatusById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	@Override
	public SysTeacherPersonalStatus findSysTeacherPersonalStatusById(Integer id){
		return sysTeacherPersonalStatusMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysTeacherPersonalStatusByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysTeacherPersonalStatus>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	@Override
	public List<SysTeacherPersonalStatus> findSysTeacherPersonalStatusByPage(SysTeacherPersonalStatus search){
		return sysTeacherPersonalStatusMapper.page(search);
	}
	/**
	 * 查询老师动态
	 */
	@Override
	public PageFinder<TeacherDynamicsVo> findStatusByTeacherId(
			TeacherDynamicsVo teacherDynamicsVo) {
		List<TeacherDynamicsVo> list;
		list=sysTeacherPersonalStatusMapper.findStatusByTeacherId(teacherDynamicsVo);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat format2 = new SimpleDateFormat("HH:mm");
		for (TeacherDynamicsVo teacherDynamicsVo2 : list) {
			teacherDynamicsVo2.setPublishTimeString(format.format(teacherDynamicsVo2.getPublishTime()));
			teacherDynamicsVo2.setPublishTime2String(format2.format(teacherDynamicsVo2.getPublishTime()));
			//设置老师头像 名字
			SysConfigTeacher teacher=sysConfigTeacherMapper.findById(teacherDynamicsVo2.getTeacherId());
			if (teacher!=null) {
				teacherDynamicsVo2.setName(teacher.getName());
				teacherDynamicsVo2.setHeadpicUrl(teacher.getHeadpicUrl());
			}
			//设置点赞数量
			SysTeacherPersonalStatusReplay sysTeacherPersonalStatusReplay=new SysTeacherPersonalStatusReplay();
			sysTeacherPersonalStatusReplay.setStatusId(teacherDynamicsVo2.getId());
			sysTeacherPersonalStatusReplay.setReplayType(0);
			sysTeacherPersonalStatusReplay.setDelFlag(0);
			int goodCount=sysTeacherPersonalStatusReplayMapper.pageCount(sysTeacherPersonalStatusReplay);
			teacherDynamicsVo2.setGoodCount(goodCount);
			//设置评论数量
			SysTeacherPersonalStatusReplay sysTeacherPersonalStatusReplay2=new SysTeacherPersonalStatusReplay();
			sysTeacherPersonalStatusReplay2.setStatusId(teacherDynamicsVo2.getId());
			sysTeacherPersonalStatusReplay2.setReplayType(1);
			sysTeacherPersonalStatusReplay2.setDelFlag(0);
			int replayCount=sysTeacherPersonalStatusReplayMapper.pageCount(sysTeacherPersonalStatusReplay2);
			teacherDynamicsVo2.setCommentCount(replayCount);
			//设置图片
			List<SysTeacherPersonalStatusPic> sysTeacherPersonalStatusPicList=sysTeacherPersonalStatusPicMapper.queryPicByStatusId(teacherDynamicsVo2.getId());
			List<String> imgList=new ArrayList<String>();
			for (SysTeacherPersonalStatusPic sysTeacherPersonalStatusPic : sysTeacherPersonalStatusPicList) {
				imgList.add(sysTeacherPersonalStatusPic.getUrl());
			}
			teacherDynamicsVo2.setPicList(imgList);
			}
		//查询页数
		int count=sysTeacherPersonalStatusMapper.statusCount(teacherDynamicsVo);
		PageFinder<TeacherDynamicsVo> pageFinder=new PageFinder<TeacherDynamicsVo>(teacherDynamicsVo.getPage(), teacherDynamicsVo.getPageSize(), count, list);
		return pageFinder;
	}
	/**
	 * 
	 * @fileName : SysTeacherPersonalStatusServiceImpl.java
	 * @author   : 杨延博
	 * @date     : 2015年11月4日下午7:04:05
	 * @description :机构查询动态
	 */
	@Override
	public PageFinder<TeacherDynamicsVo> findManageStatusByTeacherId(
			TeacherDynamicsVo teacherDynamicsVo, Integer searchType) {
		//通过searchType 判断搜索类型 1.最新 2.热门
				List<TeacherDynamicsVo> list;
				if (searchType==1) {
					 list=sysTeacherPersonalStatusMapper.findManageStatusByTeacherId(teacherDynamicsVo);
				}else{
					 list=sysTeacherPersonalStatusMapper.findManageHotStatusByTeacherId(teacherDynamicsVo);
				}
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat format2 = new SimpleDateFormat("HH:mm");
				for (TeacherDynamicsVo teacherDynamicsVo2 : list) {
					teacherDynamicsVo2.setPublishTimeString(format.format(teacherDynamicsVo2.getPublishTime()));
					teacherDynamicsVo2.setPublishTime2String(format2.format(teacherDynamicsVo2.getPublishTime()));
					//设置老师头像 名字
					SysConfigTeacher teacher=sysConfigTeacherMapper.findById(teacherDynamicsVo2.getTeacherId());
					if (teacher!=null) {
						teacherDynamicsVo2.setName(teacher.getName());
						teacherDynamicsVo2.setHeadpicUrl(teacher.getHeadpicUrl());
					}
					//设置点赞数量
					SysTeacherPersonalStatusReplay sysTeacherPersonalStatusReplay=new SysTeacherPersonalStatusReplay();
					sysTeacherPersonalStatusReplay.setStatusId(teacherDynamicsVo2.getId());
					sysTeacherPersonalStatusReplay.setReplayType(0);
					sysTeacherPersonalStatusReplay.setDelFlag(0);
					int goodCount=sysTeacherPersonalStatusReplayMapper.pageCount(sysTeacherPersonalStatusReplay);
					teacherDynamicsVo2.setGoodCount(goodCount);
					//设置评论数量
					SysTeacherPersonalStatusReplay sysTeacherPersonalStatusReplay2=new SysTeacherPersonalStatusReplay();
					sysTeacherPersonalStatusReplay2.setStatusId(teacherDynamicsVo2.getId());
					sysTeacherPersonalStatusReplay2.setReplayType(1);
					sysTeacherPersonalStatusReplay2.setDelFlag(0);
					int replayCount=sysTeacherPersonalStatusReplayMapper.pageCount(sysTeacherPersonalStatusReplay2);
					teacherDynamicsVo2.setCommentCount(replayCount);
					//设置图片
					List<SysTeacherPersonalStatusPic> sysTeacherPersonalStatusPicList=sysTeacherPersonalStatusPicMapper.queryPicByStatusId(teacherDynamicsVo2.getId());
					List<String> imgList=new ArrayList<String>();
					for (SysTeacherPersonalStatusPic sysTeacherPersonalStatusPic : sysTeacherPersonalStatusPicList) {
						imgList.add(sysTeacherPersonalStatusPic.getUrl());
					}
					teacherDynamicsVo2.setPicList(imgList);
					}
				//查询页数
				int count=sysTeacherPersonalStatusMapper.manageStatusCount(teacherDynamicsVo);
				PageFinder<TeacherDynamicsVo> pageFinder=new PageFinder<TeacherDynamicsVo>(teacherDynamicsVo.getPage(), teacherDynamicsVo.getPageSize(), count, list);
				return pageFinder;
	};
}
