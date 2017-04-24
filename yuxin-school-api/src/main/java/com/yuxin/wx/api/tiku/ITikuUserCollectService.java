package com.yuxin.wx.api.tiku;

import java.util.List;

import com.yuxin.wx.model.tiku.TikuUserCollect;
/**
 * Service Interface:TikuUserCollect
 * @author wang.zx
 * @date 2015-8-24
 */
public interface ITikuUserCollectService  {
	/**
	 * 
	* @Title: saveTikuUserCollect
	* @Description: 新增TikuUserCollect
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void insert(TikuUserCollect entity);
	
	/**
	 * 
	* @Title: batchSaveTikuUserCollect 
	* @Description: 批量新增TikuUserCollect
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void batchInsert(List<TikuUserCollect> list);
	
	/**
	 * 
	* @Title: updateTikuUserCollect 
	* @Description: 编辑TikuUserCollect
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void update(TikuUserCollect entity);
	
	/**
	 * 
	* @Title: deleteTikuUserCollectById 
	* @Description: 根据id删除TikuUserCollect
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void deleteTikuUserCollectById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuUserCollectByIds 
	* @Description: 根据id批量删除TikuUserCollect
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	void deleteTikuUserCollectByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuUserCollectById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	TikuUserCollect findTikuUserCollectById(Integer id);
	
	/**
	 * 
	* @Title: findTikuUserCollectByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuUserCollect>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-24
	* @user by wangzx
	 */
	List<TikuUserCollect> findTikuUserCollectByPage(TikuUserCollect search);
	
	/**
	 * @Description: 根据用户id 科目Id查询对应的收藏
	 * @author zx
	 * @date 2015-8-24 下午8:36:53
	 * @version 1.0
	 * @param userId
	 * @return
	 */
	Integer findCollectCountByUserIdAndSubjectId(Integer userId, Integer categoryId);
	
	/**
	 * 
	 * Class Name: ITikuUserCollectService.java
	 * @Description: 查询是否收藏
	 * @author 周文斌
	 * @date 2015-8-26 下午6:59:35
	 * @version 1.0
	 * @param collect
	 * @return
	 */
	Integer findCollectByMore(TikuUserCollect collect);
	
	/**
	 * 
	 * Class Name: ITikuUserCollectService.java
	 * @Description: 查询用户收藏
	 * @author 周文斌
	 * @date 2015-8-27 下午4:41:11
	 * @version 1.0
	 * @param collect
	 * @return
	 */
	List<TikuUserCollect> findTopicByCollect(TikuUserCollect collect);
}