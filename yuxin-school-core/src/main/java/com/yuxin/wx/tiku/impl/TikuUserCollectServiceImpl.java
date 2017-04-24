package com.yuxin.wx.tiku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuUserCollectService;
import com.yuxin.wx.model.tiku.TikuUserCollect;
import com.yuxin.wx.tiku.mapper.TikuUserCollectMapper;

/**
 * Service Implementation:TikuUserCollect
 * @author wang.zx
 * @date 2015-8-24
 */
@Service
@Transactional
public class TikuUserCollectServiceImpl extends BaseServiceImpl implements ITikuUserCollectService {

	@Autowired
	private TikuUserCollectMapper tikuUserCollectMapper;
	
	/**
	 * 
	* @Title: saveTikuUserCollect
	* @Description: 新增TikuUserCollect
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public void insert(TikuUserCollect entity){
		tikuUserCollectMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuUserCollect 
	* @Description: 批量新增TikuUserCollect
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuUserCollect> entity){
		tikuUserCollectMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuUserCollect 
	* @Description: 编辑TikuUserCollect
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public void update(TikuUserCollect entity){
		tikuUserCollectMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuUserCollectById 
	* @Description: 根据id删除TikuUserCollect
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	 @Override
	public void deleteTikuUserCollectById(Integer id){
		tikuUserCollectMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuUserCollectByIds 
	* @Description: 根据id批量删除TikuUserCollect
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public void deleteTikuUserCollectByIds(Integer[] ids){
		tikuUserCollectMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuUserCollectById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public TikuUserCollect findTikuUserCollectById(Integer id){
		return tikuUserCollectMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuUserCollectByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuUserCollect>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public List<TikuUserCollect> findTikuUserCollectByPage(TikuUserCollect search){
		return tikuUserCollectMapper.page(search);
	}

	@Override
	public Integer findCollectCountByUserIdAndSubjectId(Integer userId, Integer subjectId) {
		return tikuUserCollectMapper.findCollectCountByUserIdAndSubjectId(userId, subjectId);
	}

	@Override
	public Integer findCollectByMore(TikuUserCollect collect) {
		// TODO Auto-generated method stub
		return tikuUserCollectMapper.findCollectByMore(collect);
	}

	@Override
	public List<TikuUserCollect> findTopicByCollect(TikuUserCollect collect) {
		// TODO Auto-generated method stub
		return tikuUserCollectMapper.findTopicByCollect(collect);
	};
}
