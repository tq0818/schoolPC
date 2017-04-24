package com.yuxin.wx.tiku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuSectionService;
import com.yuxin.wx.model.tiku.TikuSection;
import com.yuxin.wx.tiku.mapper.TikuSectionMapper;

/**
 * Service Implementation:TikuSection
 * @author wang.zx
 * @date 2015-7-8
 */
@Service
@Transactional
public class TikuSectionServiceImpl extends BaseServiceImpl implements ITikuSectionService {

	@Autowired
	private TikuSectionMapper tikuSectionMapper;
	
	/**
	 * 
	* @Title: saveTikuSection
	* @Description: 新增TikuSection
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void insert(TikuSection entity){
		tikuSectionMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuSection 
	* @Description: 批量新增TikuSection
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuSection> entity){
		tikuSectionMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuSection 
	* @Description: 编辑TikuSection
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void update(TikuSection entity){
		tikuSectionMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuSectionById 
	* @Description: 根据id删除TikuSection
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	 @Override
	public void deleteTikuSectionById(Integer id){
		tikuSectionMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuSectionByIds 
	* @Description: 根据id批量删除TikuSection
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void deleteTikuSectionByIds(Integer[] ids){
		tikuSectionMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuSectionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public TikuSection findTikuSectionById(Integer id){
		return tikuSectionMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuSectionByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuSection>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public List<TikuSection> findTikuSectionByPage(TikuSection search){
		return tikuSectionMapper.page(search);
	}

	@Override
	public List<TikuSection> findTikuSection(TikuSection search) {
		// TODO Auto-generated method stub
		return tikuSectionMapper.findTikuSection(search);
	}

	@Override
	public Integer secCount(Integer secId) {
		// TODO Auto-generated method stub
		return tikuSectionMapper.secCount(secId);
	};

	@Override
	public List<TikuSection> findSectionByChapterId(Integer chapterId) {
		// TODO Auto-generated method stub
		return tikuSectionMapper.findSectionByChapterId(chapterId);
	}

	@Override
	public Integer getMaxSectionOrder(Integer chapterId) {
		// TODO Auto-generated method stub
		return tikuSectionMapper.getMaxSectionOrder(chapterId);
	}

	@Override
	public void deleteBySubId(Integer subId) {
		// TODO Auto-generated method stub
		tikuSectionMapper.deleteBySubId(subId);
	};
}
