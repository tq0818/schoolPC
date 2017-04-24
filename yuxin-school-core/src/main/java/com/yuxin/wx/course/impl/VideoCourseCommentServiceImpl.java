package com.yuxin.wx.course.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.IVideoCourseCommentService;
import com.yuxin.wx.classes.mapper.ClassTypeMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.course.mapper.CourseVideoChapterMapper;
import com.yuxin.wx.course.mapper.CourseVideoLectureMapper;
import com.yuxin.wx.course.mapper.VideoCourseCommentMapper;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.model.course.CourseVideoLecture;
import com.yuxin.wx.model.course.VideoCourseComment;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.student.mapper.StudentMapper;
import com.yuxin.wx.system.mapper.SysConfigTeacherMapper;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import com.yuxin.wx.vo.system.TeacherCommentVo;


/**
 * Service Implementation:VideoCourseComment
 * @author wang.zx
 * @date 2015-9-29
 */
@Service
@Transactional
public class VideoCourseCommentServiceImpl extends BaseServiceImpl implements IVideoCourseCommentService {

	@Autowired
	private VideoCourseCommentMapper videoCourseCommentMapper;
	@Autowired
	private UsersFrontMapper usersFrontMapper;
	@Autowired
	private ClassTypeMapper classTypeMapper;
	@Autowired
	private CourseVideoChapterMapper courseVideoChapterMapper;
	@Autowired
	private CourseVideoLectureMapper courseVideoLectureMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private SysConfigTeacherMapper sysConfigTeacherMapper;
	/**
	 * 
	* @Title: saveVideoCourseComment
	* @Description: 新增VideoCourseComment
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	@Override
	public void insert(VideoCourseComment entity){
		videoCourseCommentMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveVideoCourseComment 
	* @Description: 批量新增VideoCourseComment
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<VideoCourseComment> T){
		videoCourseCommentMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateVideoCourseComment 
	* @Description: 编辑VideoCourseComment
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	@Override
	public void update(VideoCourseComment T){
		videoCourseCommentMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteVideoCourseCommentById 
	* @Description: 根据id删除VideoCourseComment
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	 @Override
	public void deleteVideoCourseCommentById(Integer id){
		videoCourseCommentMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteVideoCourseCommentByIds 
	* @Description: 根据id批量删除VideoCourseComment
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	@Override
	public void deleteVideoCourseCommentByIds(Integer[] ids){
		videoCourseCommentMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findVideoCourseCommentById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	@Override
	public VideoCourseComment findVideoCourseCommentById(Integer id){
		return videoCourseCommentMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findVideoCourseCommentByPage 
	* @Description: 分页查询
	* @return
	* @return List<VideoCourseComment>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	@Override
	public List<VideoCourseComment> findVideoCourseCommentByPage(VideoCourseComment search){
		return videoCourseCommentMapper.page(search);
	}

	/**
	 * @Description 通过教师id查询评论
	 * @author 杨延博
	 */
	@Override
	public PageFinder<TeacherCommentVo> findVideoCourseCommentByTeacherId(
			TeacherCommentVo teacherCommentVo) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
			DateFormat format2 = new SimpleDateFormat("HH:mm");
			List<TeacherCommentVo> teahcerCommentList=videoCourseCommentMapper.findByTeacherId(teacherCommentVo);
			for (TeacherCommentVo teacherCommentVo2 : teahcerCommentList) {
				//设置时间
				 teacherCommentVo2.setCreateTimeText(format.format(teacherCommentVo2.getCreateTime()));
			    //设置分钟
				 teacherCommentVo2.setCreateTime2Text(format2.format(teacherCommentVo2.getCreateTime()));
				//设置用户名、头像
				 UsersFront usersFront=usersFrontMapper.findById(teacherCommentVo2.getUserId());
				 if (usersFront!=null) {
//					 Student student = studentMapper.findByUserId(usersFront.getId());
//					 if(null!=student){
//						 if(null!=student.getName()){
//							 teacherCommentVo2.setUserName(student.getName());
//						 }else if(null!=student.getMobile()){
//							 teacherCommentVo2.setUserName(student.getMobile());
//						 }
//					 }
					 if(null!=usersFront.getMobile() && !"".equals(usersFront.getMobile()) &&( null==teacherCommentVo2.getUserName() || "".equals(teacherCommentVo2.getUserName()) )){
						 teacherCommentVo2.setUserName(usersFront.getMobile());
					 }else if(null!=usersFront.getUsername() && !"".equals(usersFront.getUsername()) &&( null==teacherCommentVo2.getUserName() || "".equals(teacherCommentVo2.getUserName()) )){
						 teacherCommentVo2.setUserName(usersFront.getUsername());
					 }
					 teacherCommentVo2.setUserImage(usersFront.getHeadPicMax());
				}
				 
				 Integer classTypeId = teacherCommentVo2.getClassTypeId();
				 Integer teacherId = null;
				 if(classTypeId != null){
					 ClassType classType=classTypeMapper.findById(classTypeId);
					 teacherCommentVo2.setClassTypeName(classType.getName());
					 if(teacherCommentVo2.getTeacherId() != null){
						 teacherId = teacherCommentVo2.getTeacherId();
					 }else{
						 String tid = classType.getTeacherId();
						 if(tid != null && !"".equals(tid)){
							 teacherId = Integer.parseInt(tid);
						 }
					 }
				 }
				 
				//设置老师名字
				if(teacherId != null){
					SysConfigTeacher sysConfigTeacher = sysConfigTeacherMapper.findById(teacherId);
					if(sysConfigTeacher != null){
						if(StringUtils.isNotBlank(sysConfigTeacher.getName())){
							teacherCommentVo2.setTeacherName(sysConfigTeacher.getName());
						}else{
							teacherCommentVo2.setTeacherName(sysConfigTeacher.getMobile());
						}
					}
				}
				
				//设置源自哪里 
				 Integer fromId=teacherCommentVo2.getFromId();
				 if (fromId!=null&& fromId!=-1 ) {
					 ClassType classType=classTypeMapper.findById(fromId);
					 if (classType!=null) {
						 teacherCommentVo2.setFrom(classType.getName());
					}
					 CourseVideoChapter courseVideoChapter=courseVideoChapterMapper.findById(teacherCommentVo2.getVideoChapterId());
					 if (courseVideoChapter!=null) {
						 teacherCommentVo2.setVideoChapter(courseVideoChapter.getChapterName());
					}
					 CourseVideoLecture courseVideoLecture=courseVideoLectureMapper.findById(teacherCommentVo2.getVideoLectureId());
					 if (courseVideoLecture!=null) {
						 teacherCommentVo2.setVideoLecture(courseVideoLecture.getLectureName());
					}
				}
			}
			Integer count=videoCourseCommentMapper.commentCount(teacherCommentVo);
			PageFinder<TeacherCommentVo> pageFinder= new PageFinder<TeacherCommentVo>(teacherCommentVo.getPage(), teacherCommentVo.getPageSize(), count, teahcerCommentList);	
		return pageFinder;
	};
}
