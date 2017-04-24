package com.yuxin.wx.system.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysTeacherPersonalStatusReplayService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.system.SysTeacherPersonalStatus;
import com.yuxin.wx.model.system.SysTeacherPersonalStatusReplay;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.student.mapper.StudentMapper;
import com.yuxin.wx.system.mapper.SysConfigTeacherMapper;
import com.yuxin.wx.system.mapper.SysTeacherPersonalStatusReplayMapper;
import com.yuxin.wx.user.impl.UsersFrontServiceImpl;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import com.yuxin.wx.vo.system.TeacherDynamicsReplayAndStatusVo;
import com.yuxin.wx.vo.system.TeacherDynamicsReplayVo;


/**
 * Service Implementation:SysTeacherPersonalStatusReplay
 * @author chopin
 * @date 2015-10-26
 */
@Service
@Transactional
public class SysTeacherPersonalStatusReplayServiceImpl extends BaseServiceImpl implements ISysTeacherPersonalStatusReplayService {

	@Autowired
	private SysTeacherPersonalStatusReplayMapper sysTeacherPersonalStatusReplayMapper;
	@Autowired
	private SysConfigTeacherMapper sysConfigTeacherMapper;
	@Autowired
	private UsersFrontMapper usersFrontMapper;
	@Autowired
	private StudentMapper studentMapper;
	/**
	 * 
	* @Title: saveSysTeacherPersonalStatusReplay
	* @Description: 新增SysTeacherPersonalStatusReplay
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	@Override
	public void insert(SysTeacherPersonalStatusReplay entity){
		sysTeacherPersonalStatusReplayMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysTeacherPersonalStatusReplay 
	* @Description: 批量新增SysTeacherPersonalStatusReplay
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysTeacherPersonalStatusReplay> entity){
		sysTeacherPersonalStatusReplayMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysTeacherPersonalStatusReplay 
	* @Description: 编辑SysTeacherPersonalStatusReplay
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	@Override
	public void update(SysTeacherPersonalStatusReplay entity){
		sysTeacherPersonalStatusReplayMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysTeacherPersonalStatusReplayById 
	* @Description: 根据id删除SysTeacherPersonalStatusReplay
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	 @Override
	public void deleteSysTeacherPersonalStatusReplayById(Integer id){
		sysTeacherPersonalStatusReplayMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysTeacherPersonalStatusReplayByIds 
	* @Description: 根据id批量删除SysTeacherPersonalStatusReplay
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	@Override
	public void deleteSysTeacherPersonalStatusReplayByIds(Integer[] ids){
		sysTeacherPersonalStatusReplayMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysTeacherPersonalStatusReplayById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	@Override
	public SysTeacherPersonalStatusReplay findSysTeacherPersonalStatusReplayById(Integer id){
		return sysTeacherPersonalStatusReplayMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysTeacherPersonalStatusReplayByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysTeacherPersonalStatusReplay>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-10-26
	* @user by chopin
	 */
	@Override
	public List<SysTeacherPersonalStatusReplay> findSysTeacherPersonalStatusReplayByPage(SysTeacherPersonalStatusReplay search){
		return sysTeacherPersonalStatusReplayMapper.page(search);
	}
	/**
	 * 
	 * @fileName : SysTeacherPersonalStatusReplayServiceImpl.java
	 * @author   : 杨延博
	 * @date     : 2015年11月3日上午10:35:49
	 * @description :通过动态查询动态评论
	 */
	@Override
	public PageFinder<TeacherDynamicsReplayVo> findSysTeacherPersonalStatusReplayByStatusId(
			TeacherDynamicsReplayVo vo) {
		vo.setDelFlag(0);
		vo.setReplayType(1);
		List<TeacherDynamicsReplayVo> list=sysTeacherPersonalStatusReplayMapper.findSysTeacherPersonalStatusReplayByStatusId(vo);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
		DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
		for (TeacherDynamicsReplayVo teacherDynamicsReplayVo : list) {
			int id=0;
			if(null!=teacherDynamicsReplayVo&&null!=teacherDynamicsReplayVo.getUserId()&&!"".equals(teacherDynamicsReplayVo.getUserId())){
				id=teacherDynamicsReplayVo.getUserId();
			}
			//设置头像姓名
			if (id!=0) {
				int idType=teacherDynamicsReplayVo.getUserType();
				//判断评论是老师id还是用户id
				if (idType==0) {
					UsersFront usersFront=usersFrontMapper.findById(id);
					if (usersFront!=null) {
						teacherDynamicsReplayVo.setUserPic(usersFront.getHeadPicMax());
						Student stu = studentMapper.findByUserId(usersFront.getId());
						if(stu!= null && !StringUtils.isEmpty(stu.getUsername()))
							teacherDynamicsReplayVo.setUserName(stu.getUsername());
						else if(!StringUtils.isEmpty(usersFront.getUsername()))
							teacherDynamicsReplayVo.setUserName(usersFront.getUsername());
						else
							teacherDynamicsReplayVo.setUserName("");
						//teacherDynamicsReplayVo.setUserName(studentMapper.findByUserId(usersFront.getId()).getName());
					}
				}else if(idType==1){
					SysConfigTeacher sysConfigTeacher=sysConfigTeacherMapper.findById(id);
					if (sysConfigTeacher!=null) {
						teacherDynamicsReplayVo.setUserPic(sysConfigTeacher.getHeadpicUrl());
						teacherDynamicsReplayVo.setUserName(sysConfigTeacher.getName());
					}
				}
			}
			//设置时间
			teacherDynamicsReplayVo.setPublishTimeString(format.format(teacherDynamicsReplayVo.getPublishTime()));
			teacherDynamicsReplayVo.setPublishTimeString2(format2.format(teacherDynamicsReplayVo.getPublishTime()));
			//设置回复人名字
			if (teacherDynamicsReplayVo.getParentId()!=null) {
				SysTeacherPersonalStatusReplay 	sysTeacherPersonalStatusReplay =sysTeacherPersonalStatusReplayMapper.findById(teacherDynamicsReplayVo.getParentId());
				int idType2=sysTeacherPersonalStatusReplay.getUserType();
				int id2=sysTeacherPersonalStatusReplay.getUserId();
				if (idType2==0) {
					UsersFront usersFront=usersFrontMapper.findById(id2);
					if (usersFront!=null) {
						Student stu = studentMapper.findByUserId(usersFront.getId());
						if(stu!=  null && !StringUtils.isEmpty(stu.getName()))
							teacherDynamicsReplayVo.setParentName(stu.getName());
						else if(!StringUtils.isEmpty(usersFront.getUsername()))
							teacherDynamicsReplayVo.setParentName(usersFront.getUsername());
						else
							teacherDynamicsReplayVo.setParentName("");
						//teacherDynamicsReplayVo.setParentName(studentMapper.findByUserId(usersFront.getId()).getName());
					}
				}else if(idType2==1){
					SysConfigTeacher sysConfigTeacher=sysConfigTeacherMapper.findById(id2);
					if (sysConfigTeacher!=null) {
						teacherDynamicsReplayVo.setParentName(sysConfigTeacher.getName());
					}
				}
			}
		}
		int count=sysTeacherPersonalStatusReplayMapper.findReplayCountBystatusId(vo);
		PageFinder<TeacherDynamicsReplayVo> pageFinder=new PageFinder<TeacherDynamicsReplayVo>(vo.getPage(), vo.getPageSize(), count, list);
		return pageFinder;
	}
	/**
	 * 
	 * @fileName : SysTeacherPersonalStatusReplayServiceImpl.java
	 * @author   : 杨延博
	 * @date     : 2015年11月3日上午10:35:30
	 * @description :逻辑删除动态
	 */
	@Override
	public void updateByStatusId(
			SysTeacherPersonalStatusReplay sysTeacherPersonalStatusReplay) {
		sysTeacherPersonalStatusReplayMapper.updateByStatusId(sysTeacherPersonalStatusReplay);
		
	}

	@Override
	public List<TeacherDynamicsReplayVo> findSysTeacherPersonalStatusReplayByStatusId2(TeacherDynamicsReplayVo vo) {
		vo.setDelFlag(0);
		vo.setReplayType(1);
		List<TeacherDynamicsReplayVo> list=sysTeacherPersonalStatusReplayMapper.findSysTeacherPersonalStatusReplayByStatusId2(vo);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
		DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
			for (TeacherDynamicsReplayVo teacherDynamicsReplayVo : list) {
				int id=0;
				if(null!=teacherDynamicsReplayVo&&null!=teacherDynamicsReplayVo.getUserId()&&!"".equals(teacherDynamicsReplayVo.getUserId())){
					id=teacherDynamicsReplayVo.getUserId();
				}
				//设置头像姓名
				if (id!=0) {
					int idType=teacherDynamicsReplayVo.getUserType();
					//判断评论是老师id还是用户id
					if (idType==0) {
						UsersFront usersFront=usersFrontMapper.findById(id);
						if (usersFront!=null) {
							teacherDynamicsReplayVo.setUserPic(usersFront.getHeadPicMax());
							teacherDynamicsReplayVo.setUserName(studentMapper.findByUserId(usersFront.getId()).getName());
						}
					}else if(idType==1){
						SysConfigTeacher sysConfigTeacher=sysConfigTeacherMapper.findById(id);
						if (sysConfigTeacher!=null) {
							teacherDynamicsReplayVo.setUserPic(sysConfigTeacher.getHeadpicUrl());
							teacherDynamicsReplayVo.setUserName(sysConfigTeacher.getName());
						}
					}
				}
				//设置时间
				teacherDynamicsReplayVo.setPublishTimeString(format.format(teacherDynamicsReplayVo.getPublishTime()));
				teacherDynamicsReplayVo.setPublishTimeString2(format2.format(teacherDynamicsReplayVo.getPublishTime()));
				//设置回复人名字
				if (teacherDynamicsReplayVo.getParentId()!=null) {
					SysTeacherPersonalStatusReplay 	sysTeacherPersonalStatusReplay =sysTeacherPersonalStatusReplayMapper.findById(teacherDynamicsReplayVo.getParentId());
					int idType2=sysTeacherPersonalStatusReplay.getUserType();
					int id2=sysTeacherPersonalStatusReplay.getUserId();
					if (idType2==0) {
						UsersFront usersFront=usersFrontMapper.findById(id2);
						if (usersFront!=null) {
							teacherDynamicsReplayVo.setParentName(studentMapper.findByUserId(usersFront.getId()).getName());
						}
					}else if(idType2==1){
						SysConfigTeacher sysConfigTeacher=sysConfigTeacherMapper.findById(id2);
						if (sysConfigTeacher!=null) {
							teacherDynamicsReplayVo.setParentName(sysConfigTeacher.getName());
						}
					}
				}
			}
			return list;
	}
	
	@Override
	public List<TeacherDynamicsReplayAndStatusVo> findTeacherStatusReplay(SysTeacherPersonalStatus vo) {
		List<TeacherDynamicsReplayAndStatusVo> list=sysTeacherPersonalStatusReplayMapper.findTeacherStatusReplay(vo);
		for (TeacherDynamicsReplayAndStatusVo teacherDynamicsReplayVo : list) {
			UsersFront front = usersFrontMapper.findById(teacherDynamicsReplayVo.getUserId());
			if(front == null){
				continue;
			}
			//获得提问者昵称
			if(front.getNickName() != null){
				teacherDynamicsReplayVo.setUserName(front.getNickName());
			}else if(front.getMobile() != null){
				teacherDynamicsReplayVo.setUserName("*******" + front.getMobile().substring(7));
			}else{
				teacherDynamicsReplayVo.setUserName(front.getUsername());
			}
		}
		return list;
	}

}
