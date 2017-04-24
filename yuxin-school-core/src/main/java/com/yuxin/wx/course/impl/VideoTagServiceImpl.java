package com.yuxin.wx.course.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.course.IVideoTagService;
import com.yuxin.wx.course.mapper.VideoTagMapper;
import com.yuxin.wx.model.course.VideoTag;

/**
 * Service Implementation:VideoTag
 * @author wang.zx
 * @date 2015-5-8
 */
@Service
@Transactional
public class VideoTagServiceImpl extends BaseServiceImpl implements IVideoTagService {

	@Autowired
	private VideoTagMapper videoTagMapper;
	
	/**
	 * 
	* @Title: saveVideoTag
	* @Description: 新增VideoTag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by chopin
	 */
	@Override
	public void insert(VideoTag entity){
		videoTagMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveVideoTag 
	* @Description: 批量新增VideoTag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<VideoTag> videoTag){
		videoTagMapper.batchInsert(videoTag);
	};
	
	/**
	 * 
	* @Title: updateVideoTag 
	* @Description: 编辑VideoTag
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by chopin
	 */
	@Override
	public void update(VideoTag videoTag){
		videoTagMapper.update(videoTag);
	};
	
	/**
	 * 
	* @Title: deleteVideoTagById 
	* @Description: 根据id删除VideoTag
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by chopin
	 */
	 @Override
	public void deleteVideoTagById(Integer id){
		videoTagMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteVideoTagByIds 
	* @Description: 根据id批量删除VideoTag
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by chopin
	 */
	@Override
	public void deleteVideoTagByIds(Integer[] ids){
		videoTagMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findVideoTagById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by chopin
	 */
	@Override
	public VideoTag findVideoTagById(Integer id){
		return videoTagMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findVideoTagByPage 
	* @Description: 分页查询
	* @return
	* @return List<VideoTag>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-8
	* @user by chopin
	 */
	@Override
	public List<VideoTag> findVideoTagByPage(VideoTag search){
		return videoTagMapper.page(search);
	};
	
	
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
	@Override
	public List<VideoTag> findList(Integer companyId){
		return videoTagMapper.findList(companyId);
	}

	@Override
	public VideoTag findExist(VideoTag vt) {
		// TODO Auto-generated method stub
		return videoTagMapper.findExist(vt);
	};
}
