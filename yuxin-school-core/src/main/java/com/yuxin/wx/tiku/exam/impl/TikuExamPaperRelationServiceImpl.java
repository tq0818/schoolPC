package com.yuxin.wx.tiku.exam.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.exam.ITikuExamPaperRelationService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.tiku.exam.TikuExam;
import com.yuxin.wx.model.tiku.exam.TikuExamPaperRelation;
import com.yuxin.wx.tiku.exam.mapper.TikuExamPaperRelationMapper;
import com.yuxin.wx.vo.tiku.exam.TikuExamPaperRelationVo;

/**
 * Service Implementation:TikuExamPaperRelation
 * @author wang.zx
 * @date 2016-2-17
 */
@Service
@Transactional
public class TikuExamPaperRelationServiceImpl extends BaseServiceImpl implements ITikuExamPaperRelationService {

	@Autowired
	private TikuExamPaperRelationMapper tikuExamPaperRelationMapper;
	
	/**
	 * 
	* @Title: saveTikuExamPaperRelation
	* @Description: 新增TikuExamPaperRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	@Override
	public void insert(TikuExamPaperRelation entity){
		tikuExamPaperRelationMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuExamPaperRelation 
	* @Description: 批量新增TikuExamPaperRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuExamPaperRelation> entity){
		tikuExamPaperRelationMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuExamPaperRelation 
	* @Description: 编辑TikuExamPaperRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	@Override
	public void update(TikuExamPaperRelation entity){
		tikuExamPaperRelationMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuExamPaperRelationById 
	* @Description: 根据id删除TikuExamPaperRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	 @Override
	public void deleteTikuExamPaperRelationById(Integer id){
		tikuExamPaperRelationMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuExamPaperRelationByIds 
	* @Description: 根据id批量删除TikuExamPaperRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	@Override
	public void deleteTikuExamPaperRelationByIds(Integer[] ids){
		tikuExamPaperRelationMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuExamPaperRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	@Override
	public TikuExamPaperRelation findTikuExamPaperRelationById(Integer id){
		return tikuExamPaperRelationMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuExamPaperRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuExamPaperRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by chopin
	 */
	@Override
	public List<TikuExamPaperRelation> findTikuExamPaperRelationByPage(TikuExamPaperRelation search){
		return tikuExamPaperRelationMapper.page(search);
	}

	@Override
	public List<TikuExamPaperRelationVo> findPaperByExamId(Integer id) {
		// TODO Auto-generated method stub
		return tikuExamPaperRelationMapper.findPaperByExamId(id);
	}
	
	@Override
	public Integer findExamHavePaperCount(TikuExamPaperRelation search) {
		// TODO Auto-generated method stub
		return tikuExamPaperRelationMapper.pageCount(search);
	}

	@Override
	public PageFinder<TikuExamPaperRelationVo> findByPage(
			TikuExamPaperRelation search) {
		// TODO Auto-generated method stub
		List<TikuExamPaperRelationVo> list = tikuExamPaperRelationMapper.byPage(search);
		Integer count = tikuExamPaperRelationMapper.pageCount(search);
		PageFinder<TikuExamPaperRelationVo> pageFinder=new PageFinder<TikuExamPaperRelationVo>(search.getPage(), search.getPageSize(), count, list);
		return pageFinder;
	}

	@Override
	public TikuExamPaperRelationVo findRateByExamId(Integer examId) {
		// TODO Auto-generated method stub
		return tikuExamPaperRelationMapper.findRateByExamId(examId);
	}

	@Override
	public TikuExamPaperRelation findExist(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return tikuExamPaperRelationMapper.findExist(param);
	}

	@Override
	public Integer examPaperCount(Integer examId) {
		// TODO Auto-generated method stub
		return tikuExamPaperRelationMapper.examPaperCount(examId);
	};
}
