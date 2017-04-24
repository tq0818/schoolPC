package com.yuxin.wx.tiku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuSetService;
import com.yuxin.wx.model.tiku.TikuSet;
import com.yuxin.wx.tiku.mapper.TikuSetMapper;

/**
 * Service Implementation:TikuSet
 * @author wang.zx
 * @date 2015-7-8
 */
@Service
@Transactional
public class TikuSetServiceImpl extends BaseServiceImpl implements ITikuSetService {

	@Autowired
	private TikuSetMapper tikuSetMapper;
	
	/**
	 * 
	* @Title: saveTikuSet
	* @Description: 新增TikuSet
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void insert(TikuSet entity){
		tikuSetMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuSet 
	* @Description: 批量新增TikuSet
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuSet> entity){
		tikuSetMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuSet 
	* @Description: 编辑TikuSet
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void update(TikuSet entity){
		tikuSetMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuSetById 
	* @Description: 根据id删除TikuSet
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	 @Override
	public void deleteTikuSetById(Integer id){
		tikuSetMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuSetByIds 
	* @Description: 根据id批量删除TikuSet
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void deleteTikuSetByIds(Integer[] ids){
		tikuSetMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuSetById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public TikuSet findTikuSetById(Integer id){
		return tikuSetMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuSetByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuSet>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public List<TikuSet> findTikuSetByPage(TikuSet search){
		return tikuSetMapper.page(search);
	}

	@Override
	public TikuSet findSetByCompanyIdAndCategoryId(TikuSet tiku) {
		// TODO Auto-generated method stub
		return tikuSetMapper.findSetByCompanyIdAndCategoryId(tiku);
	};
}
