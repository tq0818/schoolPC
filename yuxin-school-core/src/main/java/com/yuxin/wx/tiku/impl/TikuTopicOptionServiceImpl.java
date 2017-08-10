package com.yuxin.wx.tiku.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.api.tiku.ITikuTopicOptionService;
import com.yuxin.wx.model.tiku.TikuTopicOption;
import com.yuxin.wx.tiku.mapper.TikuTopicOptionMapper;

/**
 * Service Implementation:TikuTopicOption
 * @author wang.zx
 * @date 2015-7-8
 */
@Service
@Transactional
public class TikuTopicOptionServiceImpl extends BaseServiceImpl implements ITikuTopicOptionService {

	@Autowired
	private TikuTopicOptionMapper tikuTopicOptionMapper;
	
	/**
	 * 
	* @Title: saveTikuTopicOption
	* @Description: 新增TikuTopicOption
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void insert(TikuTopicOption entity){
		tikuTopicOptionMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuTopicOption 
	* @Description: 批量新增TikuTopicOption
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuTopicOption> entity){
		tikuTopicOptionMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuTopicOption 
	* @Description: 编辑TikuTopicOption
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void update(TikuTopicOption entity){
		tikuTopicOptionMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuTopicOptionById 
	* @Description: 根据id删除TikuTopicOption
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	 @Override
	public void deleteTikuTopicOptionById(Integer id){
		tikuTopicOptionMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuTopicOptionByIds 
	* @Description: 根据id批量删除TikuTopicOption
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void deleteTikuTopicOptionByIds(Integer[] ids){
		tikuTopicOptionMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuTopicOptionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public TikuTopicOption findTikuTopicOptionById(Integer id){
		return tikuTopicOptionMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuTopicOptionByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuTopicOption>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public List<TikuTopicOption> findTikuTopicOptionByPage(TikuTopicOption search){
		return tikuTopicOptionMapper.page(search);
	}

	@Override
	public List<TikuTopicOption> findOptionByTopicId(Integer topicId) {
		// TODO Auto-generated method stub
		return tikuTopicOptionMapper.findOptionByTopicId(topicId);
	}

	@Override
	public void deleteByTopicId(List<Integer> list) {
		// TODO Auto-generated method stub
		tikuTopicOptionMapper.deleteByTopicId(list);
	}

	@Override
	public List<TikuTopicOption> findOptionByListTopicId(Map<String, Object> param) {
		List<TikuTopicOption> optionList = tikuTopicOptionMapper.findOptionByListTopicId(param);
		return optionList;
	}
}
