package com.yuxin.wx.tiku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuUserBatchTopicService;
import com.yuxin.wx.model.tiku.TikuUserBatchTopic;
import com.yuxin.wx.tiku.mapper.TikuUserBatchTopicMapper;

/**
 * Service Implementation:TikuUserBatchTopic
 * @author wang.zx
 * @date 2015-8-24
 */
@Service
@Transactional
public class TikuUserBatchTopicServiceImpl extends BaseServiceImpl implements ITikuUserBatchTopicService {

	@Autowired
	private TikuUserBatchTopicMapper tikuUserBatchTopicMapper;
	
	/**
	 * 
	* @Title: saveTikuUserBatchTopic
	* @Description: 新增TikuUserBatchTopic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public void insert(TikuUserBatchTopic entity){
		tikuUserBatchTopicMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuUserBatchTopic 
	* @Description: 批量新增TikuUserBatchTopic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuUserBatchTopic> entity){
		tikuUserBatchTopicMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuUserBatchTopic 
	* @Description: 编辑TikuUserBatchTopic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public void update(TikuUserBatchTopic entity){
		tikuUserBatchTopicMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuUserBatchTopicById 
	* @Description: 根据id删除TikuUserBatchTopic
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	 @Override
	public void deleteTikuUserBatchTopicById(Integer id){
		tikuUserBatchTopicMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuUserBatchTopicByIds 
	* @Description: 根据id批量删除TikuUserBatchTopic
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public void deleteTikuUserBatchTopicByIds(Integer[] ids){
		tikuUserBatchTopicMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuUserBatchTopicById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public TikuUserBatchTopic findTikuUserBatchTopicById(Integer id){
		return tikuUserBatchTopicMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuUserBatchTopicByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuUserBatchTopic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public List<TikuUserBatchTopic> findTikuUserBatchTopicByPage(TikuUserBatchTopic search){
		return tikuUserBatchTopicMapper.page(search);
	}

	@Override
	public Integer findCurrentById(Integer id) {
		// TODO Auto-generated method stub
		return tikuUserBatchTopicMapper.findCurrentById(id);
	};
}
