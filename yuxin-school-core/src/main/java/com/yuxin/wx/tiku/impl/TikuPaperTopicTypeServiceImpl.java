package com.yuxin.wx.tiku.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuPaperTopicTypeService;
import com.yuxin.wx.model.tiku.TikuPaperTopicType;
import com.yuxin.wx.tiku.mapper.TikuPaperTopicTypeMapper;

/**
 * Service Implementation:TikuPaperTopicType
 * @author wang.zx
 * @date 2015-7-8
 */
@Service
@Transactional
public class TikuPaperTopicTypeServiceImpl extends BaseServiceImpl implements ITikuPaperTopicTypeService {

	@Autowired
	private TikuPaperTopicTypeMapper tikuPaperTopicTypeMapper;
	
	/**
	 * 
	* @Title: saveTikuPaperTopicType
	* @Description: 新增TikuPaperTopicType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void insert(TikuPaperTopicType entity){
		tikuPaperTopicTypeMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuPaperTopicType 
	* @Description: 批量新增TikuPaperTopicType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuPaperTopicType> entity){
		tikuPaperTopicTypeMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuPaperTopicType 
	* @Description: 编辑TikuPaperTopicType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void update(TikuPaperTopicType entity){
		tikuPaperTopicTypeMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuPaperTopicTypeById 
	* @Description: 根据id删除TikuPaperTopicType
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	 @Override
	public void deleteTikuPaperTopicTypeById(Integer id){
		tikuPaperTopicTypeMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuPaperTopicTypeByIds 
	* @Description: 根据id批量删除TikuPaperTopicType
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void deleteTikuPaperTopicTypeByIds(Integer[] ids){
		tikuPaperTopicTypeMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuPaperTopicTypeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public TikuPaperTopicType findTikuPaperTopicTypeById(Integer id){
		return tikuPaperTopicTypeMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuPaperTopicTypeByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuPaperTopicType>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public List<TikuPaperTopicType> findTikuPaperTopicTypeByPage(TikuPaperTopicType search){
		return tikuPaperTopicTypeMapper.page(search);
	}

	@Override
	public Double findScoreByPaperid(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return tikuPaperTopicTypeMapper.findScoreByPaperid(param);
	}

	@Override
	public TikuPaperTopicType findScoreById(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return tikuPaperTopicTypeMapper.findScoreById(param);
	};
}
