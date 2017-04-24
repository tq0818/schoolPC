package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.course.VideoCourseComment;
import com.yuxin.wx.vo.system.TeacherCommentVo;
/**
 * Service Interface:VideoCourseComment
 * @author wang.zx
 * @date 2015-9-29
 */
public interface IVideoCourseCommentService  {
	/**
	 * 
	* @Title: saveVideoCourseComment
	* @Description: 新增VideoCourseComment
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	void insert(VideoCourseComment entity);
	
	/**
	 * 
	* @Title: batchSaveVideoCourseComment 
	* @Description: 批量新增VideoCourseComment
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	void batchInsert(List<VideoCourseComment> list);
	
	/**
	 * 
	* @Title: updateVideoCourseComment 
	* @Description: 编辑VideoCourseComment
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	void update(VideoCourseComment entity);
	
	/**
	 * 
	* @Title: deleteVideoCourseCommentById 
	* @Description: 根据id删除VideoCourseComment
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	void deleteVideoCourseCommentById(Integer id);
	
	/**
	 * 
	* @Title: deleteVideoCourseCommentByIds 
	* @Description: 根据id批量删除VideoCourseComment
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	void deleteVideoCourseCommentByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findVideoCourseCommentById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	VideoCourseComment findVideoCourseCommentById(Integer id);
	
	/**
	 * 
	* @Title: findVideoCourseCommentByPage 
	* @Description: 分页查询
	* @return
	* @return List<VideoCourseComment>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	List<VideoCourseComment> findVideoCourseCommentByPage(VideoCourseComment search);

	PageFinder<TeacherCommentVo> findVideoCourseCommentByTeacherId(
			TeacherCommentVo teacherCommentVo);
}