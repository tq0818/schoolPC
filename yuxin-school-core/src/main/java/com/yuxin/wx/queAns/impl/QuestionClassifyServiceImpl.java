package com.yuxin.wx.queAns.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.queAns.IQuestionClassifyService;
import com.yuxin.wx.model.queAns.QuestionClassify;
import com.yuxin.wx.queAns.mapper.QuestionClassifyMapper;


/**
 * Service Implementation:QuestionClassifyVo
 * @author wang.zx
 * @date 2015-12-9
 */
@Service
@Transactional
public class QuestionClassifyServiceImpl extends BaseServiceImpl implements IQuestionClassifyService {

	@Autowired
	private QuestionClassifyMapper QuestionClassifyMapper;
	
	/**
	 * 
	* @Title: saveQuestionClassify
	* @Description: 新增QuestionClassify
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	@Override
	public void insert(QuestionClassify entity){
		QuestionClassifyMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveQuestionClassify 
	* @Description: 批量新增QuestionClassify
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<QuestionClassify> T){
		QuestionClassifyMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateQuestionClassify 
	* @Description: 编辑QuestionClassify
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	@Override
	public void update(QuestionClassify T){
		QuestionClassifyMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteQuestionClassifyById 
	* @Description: 根据id删除QuestionClassify
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	 @Override
	public void deleteQuestionClassifyById(Integer id){
		QuestionClassifyMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteQuestionClassifyByIds 
	* @Description: 根据id批量删除QuestionClassify
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	@Override
	public void deleteQuestionClassifyByIds(Integer[] ids){
		QuestionClassifyMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findQuestionClassifyById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	@Override
	public QuestionClassify findQuestionClassifyById(Integer id){
		return QuestionClassifyMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findQuestionClassifyByPage 
	* @Description: 分页查询
	* @return
	* @return List<QuestionClassifyVo>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	@Override
	public List<QuestionClassify> findQuestionClassifyByPage(QuestionClassify search){
		return QuestionClassifyMapper.page(search);
	}

	@Override
	public List<QuestionClassify> findQuestionClassify(
			QuestionClassify search) {
		// TODO Auto-generated method stub
		return QuestionClassifyMapper.findQuestionClassify(search);
	}

	@Override
	public List<QuestionClassify> findQuestionClassifyKc(QuestionClassify search) {
		// TODO Auto-generated method stub
		return QuestionClassifyMapper.findQuestionClassifyKc(search);
	}

	@Override
	public List<QuestionClassify> findClassifyByCompany(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return QuestionClassifyMapper.findClassifyByCompany(param);
	};
}
