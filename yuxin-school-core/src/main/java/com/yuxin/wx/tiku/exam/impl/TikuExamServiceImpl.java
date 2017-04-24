package com.yuxin.wx.tiku.exam.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.exam.ITikuExamService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.tiku.exam.TikuExam;
import com.yuxin.wx.tiku.exam.mapper.TikuExamMapper;
import com.yuxin.wx.vo.tiku.TikuPaperVo;

/**
 * Service Implementation:TikuExam
 * @author wang.zx
 * @date 2016-2-17
 */
@Service
@Transactional
public class TikuExamServiceImpl extends BaseServiceImpl implements ITikuExamService {

	@Autowired
	private TikuExamMapper tikuExamMapper;
	
	/**
	 * 
	* @Title: saveTikuExam
	* @Description: 新增TikuExam
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	@Override
	public void insert(TikuExam entity){
		tikuExamMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuExam 
	* @Description: 批量新增TikuExam
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuExam> entity){
		tikuExamMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuExam 
	* @Description: 编辑TikuExam
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	@Override
	public void update(TikuExam entity){
		tikuExamMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuExamById 
	* @Description: 根据id删除TikuExam
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	 @Override
	public void deleteTikuExamById(Integer id){
		tikuExamMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuExamByIds 
	* @Description: 根据id批量删除TikuExam
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	@Override
	public void deleteTikuExamByIds(Integer[] ids){
		tikuExamMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuExamById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	@Override
	public TikuExam findTikuExamById(Integer id){
		return tikuExamMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuExamByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuExam>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	@Override
	public List<TikuExam> findTikuExamByPage(TikuExam search){
		return tikuExamMapper.page(search);
	}

	@Override
	public PageFinder<TikuExam> findTikuExamsByPage(TikuExam search) {
		// TODO Auto-generated method stub
		List<TikuExam> list = tikuExamMapper.findTikuExamsByPage(search);
		Integer count = tikuExamMapper.pageCount(search);
		PageFinder<TikuExam> pageFinder=new PageFinder<TikuExam>(search.getPage(), search.getPageSize(), count, list);
		return pageFinder;
	}

	@Override
	public void updateById(TikuExam entity) {
		// TODO Auto-generated method stub
		tikuExamMapper.updateById(entity);
	};
}
