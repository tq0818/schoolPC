package com.yuxin.wx.api.tiku;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.tiku.TikuPaperTopicType;
/**
 * Service Interface:TikuPaperTopicType
 * @author wang.zx
 * @date 2015-7-8
 */
public interface ITikuPaperTopicTypeService  {
	/**
	 * 
	* @Title: saveTikuPaperTopicType
	* @Description: 新增TikuPaperTopicType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void insert(TikuPaperTopicType entity);
	
	/**
	 * 
	* @Title: batchSaveTikuPaperTopicType 
	* @Description: 批量新增TikuPaperTopicType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void batchInsert(List<TikuPaperTopicType> list);
	
	/**
	 * 
	* @Title: updateTikuPaperTopicType 
	* @Description: 编辑TikuPaperTopicType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void update(TikuPaperTopicType entity);
	
	/**
	 * 
	* @Title: deleteTikuPaperTopicTypeById 
	* @Description: 根据id删除TikuPaperTopicType
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuPaperTopicTypeById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuPaperTopicTypeByIds 
	* @Description: 根据id批量删除TikuPaperTopicType
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuPaperTopicTypeByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuPaperTopicTypeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	TikuPaperTopicType findTikuPaperTopicTypeById(Integer id);
	
	/**
	 * 
	* @Title: findTikuPaperTopicTypeByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuPaperTopicType>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	List<TikuPaperTopicType> findTikuPaperTopicTypeByPage(TikuPaperTopicType search);
	
	/**
	 * 
	 * Class Name: ITikuPaperTopicTypeService.java
	 * @Description: 查询当前试卷题型的分数
	 * @author 周文斌
	 * @date 2015-7-23 下午9:19:17
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Double findScoreByPaperid(Map<String, Object> param);
	
	/**
	 * 
	 * Class Name: ITikuPaperTopicTypeService.java
	 * @Description: 查询分数试卷关系对象
	 * @author 周文斌
	 * @date 2015-7-24 下午1:08:21
	 * @version 1.0
	 * @param param
	 * @return
	 */
	TikuPaperTopicType findScoreById(Map<String, Object> param);
}