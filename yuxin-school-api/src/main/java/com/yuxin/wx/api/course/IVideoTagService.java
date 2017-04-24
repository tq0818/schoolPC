package com.yuxin.wx.api.course;

import java.util.List;

import com.yuxin.wx.model.course.VideoTag;
/**
 * Service Interface:VideoTag
 * @author wang.zx
 * @date 2015-5-8
 */
public interface IVideoTagService  {
	/**
	 * 
	* @Title: saveVideoTag
	* @Description: 新增VideoTag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by wangzx
	 */
	void insert(VideoTag entity);
	
	/**
	 * 
	* @Title: batchSaveVideoTag 
	* @Description: 批量新增VideoTag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by wangzx
	 */
	void batchInsert(List<VideoTag> list);
	
	/**
	 * 
	* @Title: updateVideoTag 
	* @Description: 编辑VideoTag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by wangzx
	 */
	void update(VideoTag entity);
	
	/**
	 * 
	* @Title: deleteVideoTagById 
	* @Description: 根据id删除VideoTag
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by wangzx
	 */
	void deleteVideoTagById(Integer id);
	
	/**
	 * 
	* @Title: deleteVideoTagByIds 
	* @Description: 根据id批量删除VideoTag
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by wangzx
	 */
	void deleteVideoTagByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findVideoTagById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by wangzx
	 */
	VideoTag findVideoTagById(Integer id);
	
	/**
	 * 
	* @Title: findVideoTagByPage 
	* @Description: 分页查询
	* @return
	* @return List<VideoTag>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by wangzx
	 */
	List<VideoTag> findVideoTagByPage(VideoTag search);
	/**
	 * 
	* @Title: findVideoTagByPage 
	* @Description: 查询当前公司的标签库
	* @return
	* @return List<VideoTag>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by chopin
	 */
	public List<VideoTag> findList(Integer companyId);
	
	/**
	 * 
	 * Class Name: IVideoTagService.java
	 * @Description: 查询标签是否存在
	 * @author 周文斌
	 * @date 2016-9-28 下午3:06:16
	 * @modify	2016-9-28 下午3:06:16
	 * @version 
	 * @param vt
	 * @return
	 */
	VideoTag findExist(VideoTag vt);
}