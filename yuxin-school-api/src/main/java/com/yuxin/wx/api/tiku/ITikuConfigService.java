package com.yuxin.wx.api.tiku;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.tiku.TikuConfig;
/**
 * Service Interface:TikuConfig
 * @author yuchanglong
 * @date 2016-3-2
 */
public interface ITikuConfigService  {
	/**
	 * 
	* @Title: saveTikuConfig
	* @Description: 新增TikuConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by wangzx
	 */
	void insert(TikuConfig entity);
	
	/**
	 * 
	* @Title: batchSaveTikuConfig 
	* @Description: 批量新增TikuConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by wangzx
	 */
	void batchInsert(List<TikuConfig> list);
	
	/**
	 * 
	* @Title: updateTikuConfig 
	* @Description: 编辑TikuConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by wangzx
	 */
	void update(TikuConfig entity);
	
	/**
	 * 
	* @Title: deleteTikuConfigById 
	* @Description: 根据id删除TikuConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by wangzx
	 */
	void deleteTikuConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuConfigByIds 
	* @Description: 根据id批量删除TikuConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by wangzx
	 */
	void deleteTikuConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by wangzx
	 */
	TikuConfig findTikuConfigById(Integer id);
	
	/**
	 * 
	* @Title: findTikuConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-2
	* @user by wangzx
	 */
	List<TikuConfig> findTikuConfigByPage(TikuConfig search);
	
	/**
	 * 
	 * Class Name: ITikuConfigService.java
	 * @Description: 查询存不存在
	 * @author 周文斌
	 * @date 2016-3-2 下午5:54:20
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<TikuConfig> findList(Map<String, Object> param);
	
	/**
	 * 
	 * Class Name: ITikuConfigService.java
	 * @Description: 根据值查询
	 * @author 周文斌
	 * @date 2016-3-2 下午5:59:27
	 * @version 1.0
	 * @param param
	 * @return
	 */
	TikuConfig findByCodeValue(Map<String, Object> param);
}