package com.yuxin.wx.tiku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuUserBatchService;
import com.yuxin.wx.model.tiku.TikuUserBatch;
import com.yuxin.wx.model.tiku.TikuUserBatchTopic;
import com.yuxin.wx.model.tiku.TikuUserExercise;
import com.yuxin.wx.tiku.mapper.TikuUserBatchMapper;
import com.yuxin.wx.tiku.mapper.TikuUserBatchTopicMapper;
import com.yuxin.wx.tiku.mapper.TikuUserExerciseMapper;

/**
 * Service Implementation:TikuUserBatch
 * @author wang.zx
 * @date 2015-8-24
 */
@Service
@Transactional
public class TikuUserBatchServiceImpl extends BaseServiceImpl implements ITikuUserBatchService {

	@Autowired
	private TikuUserBatchMapper tikuUserBatchMapper;
	
	@Autowired
	private TikuUserBatchTopicMapper tikuUserBatchTopicMapper;
	
	@Autowired
	private TikuUserExerciseMapper tikuUserExerciseMapper;
	
	/**
	 * 
	* @Title: saveTikuUserBatch
	* @Description: 新增TikuUserBatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public void insert(TikuUserBatch entity){
		tikuUserBatchMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuUserBatch 
	* @Description: 批量新增TikuUserBatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuUserBatch> entity){
		tikuUserBatchMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuUserBatch 
	* @Description: 编辑TikuUserBatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public void update(TikuUserBatch entity){
		tikuUserBatchMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuUserBatchById 
	* @Description: 根据id删除TikuUserBatch
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	 @Override
	public void deleteTikuUserBatchById(Integer id){
		tikuUserBatchMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuUserBatchByIds 
	* @Description: 根据id批量删除TikuUserBatch
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public void deleteTikuUserBatchByIds(Integer[] ids){
		tikuUserBatchMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuUserBatchById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public TikuUserBatch findTikuUserBatchById(Integer id){
		return tikuUserBatchMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuUserBatchByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuUserBatch>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by chopin
	 */
	@Override
	public List<TikuUserBatch> findTikuUserBatchByPage(TikuUserBatch search){
		return tikuUserBatchMapper.page(search);
	}

	@Override
	public void insertTopic(TikuUserBatch batch,
			List<TikuUserBatchTopic> batchTopics, TikuUserExercise exercise) {
		// TODO Auto-generated method stub
		tikuUserBatchMapper.insert(batch);
		Integer batchId = batch.getId();
		for (TikuUserBatchTopic tikuUserBatchTopic : batchTopics) {
			tikuUserBatchTopic.setId(null);
			tikuUserBatchTopic.setBatchId(batchId);
		}
		tikuUserBatchTopicMapper.batchInsert(batchTopics);
		exercise.setExerciseId(batchId);
		tikuUserExerciseMapper.insert(exercise);
	}

	@Override
	public String findNameById(Integer id) {
		// TODO Auto-generated method stub
		return tikuUserBatchMapper.findNameById(id);
	};
}
