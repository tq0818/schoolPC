package com.yuxin.wx.tiku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuTopicExampointService;
import com.yuxin.wx.model.tiku.TikuTopicExampoint;
import com.yuxin.wx.tiku.mapper.TikuTopicExampointMapper;

/**
 * Service Implementation:TikuTopicExampoint
 * @author wang.zx
 * @date 2015-7-8
 */
@Service
@Transactional
public class TikuTopicExampointServiceImpl extends BaseServiceImpl implements ITikuTopicExampointService {

	@Autowired
	private TikuTopicExampointMapper tikuTopicExampointMapper;
	
	/**
	 * 
	* @Title: saveTikuTopicExampoint
	* @Description: 新增TikuTopicExampoint
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void insert(TikuTopicExampoint entity){
		tikuTopicExampointMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuTopicExampoint 
	* @Description: 批量新增TikuTopicExampoint
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuTopicExampoint> entity){
		tikuTopicExampointMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuTopicExampoint 
	* @Description: 编辑TikuTopicExampoint
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void update(TikuTopicExampoint entity){
		tikuTopicExampointMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuTopicExampointById 
	* @Description: 根据id删除TikuTopicExampoint
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	 @Override
	public void deleteTikuTopicExampointById(Integer id){
		tikuTopicExampointMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuTopicExampointByIds 
	* @Description: 根据id批量删除TikuTopicExampoint
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void deleteTikuTopicExampointByIds(Integer[] ids){
		tikuTopicExampointMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuTopicExampointById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public TikuTopicExampoint findTikuTopicExampointById(Integer id){
		return tikuTopicExampointMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuTopicExampointByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuTopicExampoint>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public List<TikuTopicExampoint> findTikuTopicExampointByPage(TikuTopicExampoint search){
		return tikuTopicExampointMapper.page(search);
	}

	@Override
	public TikuTopicExampoint findPointByTopicId(Integer topicId) {
		// TODO Auto-generated method stub
		return tikuTopicExampointMapper.findPointByTopicId(topicId);
	}

	@Override
	public Integer findIdByTopicId(Integer topicId) {
		// TODO Auto-generated method stub
		return tikuTopicExampointMapper.findIdByTopicId(topicId);
	}

	@Override
	public void deleteByTopicId(List<Integer> list) {
		// TODO Auto-generated method stub
		tikuTopicExampointMapper.deleteByTopicId(list);
	};
}
