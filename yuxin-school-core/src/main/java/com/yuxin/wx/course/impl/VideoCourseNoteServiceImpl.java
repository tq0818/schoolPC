package com.yuxin.wx.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.IVideoCourseNoteService;
import com.yuxin.wx.course.mapper.VideoCourseNoteMapper;
import com.yuxin.wx.model.course.VideoCourseNote;

/**
 * Service Implementation:VideoCourseNote
 * @author wang.zx
 * @date 2015-9-29
 */
@Service
@Transactional
public class VideoCourseNoteServiceImpl extends BaseServiceImpl implements IVideoCourseNoteService {

	@Autowired
	private VideoCourseNoteMapper videoCourseNoteMapper;
	
	/**
	 * 
	* @Title: saveVideoCourseNote
	* @Description: 新增VideoCourseNote
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	@Override
	public void insert(VideoCourseNote entity){
		videoCourseNoteMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveVideoCourseNote 
	* @Description: 批量新增VideoCourseNote
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<VideoCourseNote> T){
		videoCourseNoteMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateVideoCourseNote 
	* @Description: 编辑VideoCourseNote
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	@Override
	public void update(VideoCourseNote T){
		videoCourseNoteMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteVideoCourseNoteById 
	* @Description: 根据id删除VideoCourseNote
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	 @Override
	public void deleteVideoCourseNoteById(Integer id){
		videoCourseNoteMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteVideoCourseNoteByIds 
	* @Description: 根据id批量删除VideoCourseNote
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	@Override
	public void deleteVideoCourseNoteByIds(Integer[] ids){
		videoCourseNoteMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findVideoCourseNoteById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	@Override
	public VideoCourseNote findVideoCourseNoteById(Integer id){
		return videoCourseNoteMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findVideoCourseNoteByPage 
	* @Description: 分页查询
	* @return
	* @return List<VideoCourseNote>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-9-29
	* @user by chopin
	 */
	@Override
	public List<VideoCourseNote> findVideoCourseNoteByPage(VideoCourseNote search){
		return videoCourseNoteMapper.page(search);
	};
}
