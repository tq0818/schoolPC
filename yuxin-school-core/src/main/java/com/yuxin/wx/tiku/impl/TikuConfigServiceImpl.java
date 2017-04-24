package com.yuxin.wx.tiku.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuConfigService;
import com.yuxin.wx.model.tiku.TikuConfig;
import com.yuxin.wx.tiku.mapper.TikuConfigMapper;


/**
 * Service Implementation:TikuConfig
 * @author yuchanglong
 * @date 2016-3-2
 */
@Service
@Transactional
public class TikuConfigServiceImpl extends BaseServiceImpl implements ITikuConfigService {

	@Autowired
	private TikuConfigMapper tikuConfigMapper;
	
	/**
	 * 
	* @Title: saveTikuConfig
	* @Description: 新增TikuConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by chopin
	 */
	@Override
	public void insert(TikuConfig entity){
		tikuConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuConfig 
	* @Description: 批量新增TikuConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuConfig> T){
		tikuConfigMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateTikuConfig 
	* @Description: 编辑TikuConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by chopin
	 */
	@Override
	public void update(TikuConfig T){
		tikuConfigMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteTikuConfigById 
	* @Description: 根据id删除TikuConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by chopin
	 */
	 @Override
	public void deleteTikuConfigById(Integer id){
		tikuConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuConfigByIds 
	* @Description: 根据id批量删除TikuConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by chopin
	 */
	@Override
	public void deleteTikuConfigByIds(Integer[] ids){
		tikuConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by chopin
	 */
	@Override
	public TikuConfig findTikuConfigById(Integer id){
		return tikuConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by chopin
	 */
	@Override
	public List<TikuConfig> findTikuConfigByPage(TikuConfig search){
		return tikuConfigMapper.page(search);
	}

	@Override
	public List<TikuConfig> findList(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return tikuConfigMapper.findList(param);
	}

	@Override
	public TikuConfig findByCodeValue(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return tikuConfigMapper.findByCodeValue(param);
	};
}
