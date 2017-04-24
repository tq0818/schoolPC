package com.yuxin.wx.tiku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuExampointService;
import com.yuxin.wx.model.tiku.TikuExampoint;
import com.yuxin.wx.tiku.mapper.TikuExampointMapper;

/**
 * Service Implementation:TikuExampoint
 * @author wang.zx
 * @date 2015-7-8
 */
@Service
@Transactional
public class TikuExampointServiceImpl extends BaseServiceImpl implements ITikuExampointService {

	@Autowired
	private TikuExampointMapper tikuExampointMapper;
	
	/**
	 * 
	* @Title: saveTikuExampoint
	* @Description: 新增TikuExampoint
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void insert(TikuExampoint entity){
		tikuExampointMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuExampoint 
	* @Description: 批量新增TikuExampoint
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuExampoint> entity){
		tikuExampointMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuExampoint 
	* @Description: 编辑TikuExampoint
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void update(TikuExampoint entity){
		tikuExampointMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuExampointById 
	* @Description: 根据id删除TikuExampoint
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	 @Override
	public void deleteTikuExampointById(Integer id){
		tikuExampointMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuExampointByIds 
	* @Description: 根据id批量删除TikuExampoint
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void deleteTikuExampointByIds(Integer[] ids){
		tikuExampointMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuExampointById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public TikuExampoint findTikuExampointById(Integer id){
		return tikuExampointMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuExampointByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuExampoint>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public List<TikuExampoint> findTikuExampointByPage(TikuExampoint search){
		return tikuExampointMapper.page(search);

	}

	@Override
	public List<TikuExampoint> findTikuExampoint(TikuExampoint search) {
		// TODO Auto-generated method stub
		return tikuExampointMapper.findTikuExampoint(search);

	}

	@Override
	public List<TikuExampoint> findPointBySectionId(Integer sectionId) {
		// TODO Auto-generated method stub
		return tikuExampointMapper.findPointBySectionId(sectionId);

	}

	@Override
	public Integer getCPointCount(Integer pointId) {
		// TODO Auto-generated method stub
		return tikuExampointMapper.getCPointCount(pointId);
	}

	@Override
	public void delByChecked(Integer id) {
		// TODO Auto-generated method stub
		Integer childrenPoint = tikuExampointMapper.getCPointCount(id);
		if(childrenPoint>0){
			tikuExampointMapper.delByPid(id);
			tikuExampointMapper.deleteById(id);
		}else{
			tikuExampointMapper.deleteById(id);
		}
	}

	@Override
	public void deleteBySubId(Integer subId) {
		// TODO Auto-generated method stub
		tikuExampointMapper.deleteBySubId(subId);
	};
}
