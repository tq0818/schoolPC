package com.yuxin.wx.tiku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuSubjectService;
import com.yuxin.wx.model.tiku.TikuSubject;
import com.yuxin.wx.tiku.mapper.TikuChapterMapper;
import com.yuxin.wx.tiku.mapper.TikuExampointMapper;
import com.yuxin.wx.tiku.mapper.TikuSectionMapper;
import com.yuxin.wx.tiku.mapper.TikuSubjectMapper;
import com.yuxin.wx.tiku.mapper.TikuTopicMapper;

/**
 * Service Implementation:TikuSubject
 * @author wang.zx
 * @date 2015-7-8
 */
@Service
@Transactional
public class TikuSubjectServiceImpl extends BaseServiceImpl implements ITikuSubjectService {

	@Autowired
	private TikuSubjectMapper tikuSubjectMapper;
	
	@Autowired
	private TikuChapterMapper tikuChapterMapper;
	
	@Autowired
	private TikuExampointMapper tikuExampointMapper;
	
	@Autowired
	private TikuSectionMapper tikuSectionMapper;
	
	@Autowired
	private TikuTopicMapper tikuTopicMapper;
	
	/**
	 * 
	* @Title: saveTikuSubject
	* @Description: 新增TikuSubject
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void insert(TikuSubject entity){
		tikuSubjectMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuSubject 
	* @Description: 批量新增TikuSubject
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuSubject> entity){
		tikuSubjectMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuSubject 
	* @Description: 编辑TikuSubject
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void update(TikuSubject entity){
		tikuSubjectMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuSubjectById 
	* @Description: 根据id删除TikuSubject
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	 @Override
	public void deleteTikuSubjectById(Integer id){
		tikuChapterMapper.deleteBySubId(id);
		tikuSectionMapper.deleteBySubId(id);
		tikuExampointMapper.deleteBySubId(id);
		tikuTopicMapper.delBySubId(id);
		tikuSubjectMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuSubjectByIds 
	* @Description: 根据id批量删除TikuSubject
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void deleteTikuSubjectByIds(Integer[] ids){
		tikuSubjectMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuSubjectById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public TikuSubject findTikuSubjectById(Integer id){
		return tikuSubjectMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuSubjectByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuSubject>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public List<TikuSubject> findTikuSubjectByPage(TikuSubject search){
		return tikuSubjectMapper.page(search);
	}

	@Override
	public List<TikuSubject> findTikuSubject(TikuSubject search) {
		// TODO Auto-generated method stub
		return tikuSubjectMapper.findTikuSubject(search);
	};

	@Override
	public List<TikuSubject> findSubByCategoryId(Integer categoryId) {
		// TODO Auto-generated method stub
		return tikuSubjectMapper.findSubByCategoryId(categoryId);
	}

	@Override
	public Integer findCountBySubName(TikuSubject subject) {
		// TODO Auto-generated method stub
		return tikuSubjectMapper.findCountBySubName(subject);
	};
}
