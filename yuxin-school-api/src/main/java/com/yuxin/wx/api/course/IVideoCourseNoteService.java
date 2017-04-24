package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.model.course.VideoCourseNote;
/**
 * Service Interface:VideoCourseNote
 * @author wang.zx
 * @date 2015-9-29
 */
public interface IVideoCourseNoteService  {
	/**
	 * 
	* @Title: saveVideoCourseNote
	* @Description: 新增VideoCourseNote
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	void insert(VideoCourseNote entity);
	
	/**
	 * 
	* @Title: batchSaveVideoCourseNote 
	* @Description: 批量新增VideoCourseNote
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	void batchInsert(List<VideoCourseNote> list);
	
	/**
	 * 
	* @Title: updateVideoCourseNote 
	* @Description: 编辑VideoCourseNote
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	void update(VideoCourseNote entity);
	
	/**
	 * 
	* @Title: deleteVideoCourseNoteById 
	* @Description: 根据id删除VideoCourseNote
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	void deleteVideoCourseNoteById(Integer id);
	
	/**
	 * 
	* @Title: deleteVideoCourseNoteByIds 
	* @Description: 根据id批量删除VideoCourseNote
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	void deleteVideoCourseNoteByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findVideoCourseNoteById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	VideoCourseNote findVideoCourseNoteById(Integer id);
	
	/**
	 * 
	* @Title: findVideoCourseNoteByPage 
	* @Description: 分页查询
	* @return
	* @return List<VideoCourseNote>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by wangzx
	 */
	List<VideoCourseNote> findVideoCourseNoteByPage(VideoCourseNote search);
}