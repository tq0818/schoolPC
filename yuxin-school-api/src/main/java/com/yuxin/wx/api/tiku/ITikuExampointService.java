package com.yuxin.wx.api.tiku;

import java.util.List;

import com.yuxin.wx.model.tiku.TikuExampoint;
/**
 * Service Interface:TikuExampoint
 * @author wang.zx
 * @date 2015-7-8
 */
public interface ITikuExampointService  {
	/**
	 * 
	* @Title: saveTikuExampoint
	* @Description: 新增TikuExampoint
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void insert(TikuExampoint entity);
	
	/**
	 * 
	* @Title: batchSaveTikuExampoint 
	* @Description: 批量新增TikuExampoint
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void batchInsert(List<TikuExampoint> list);
	
	/**
	 * 
	* @Title: updateTikuExampoint 
	* @Description: 编辑TikuExampoint
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void update(TikuExampoint entity);
	
	/**
	 * 
	* @Title: deleteTikuExampointById 
	* @Description: 根据id删除TikuExampoint
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuExampointById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuExampointByIds 
	* @Description: 根据id批量删除TikuExampoint
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuExampointByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuExampointById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	TikuExampoint findTikuExampointById(Integer id);
	
	/**
	 * 
	* @Title: findTikuExampointByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuExampoint>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	List<TikuExampoint> findTikuExampointByPage(TikuExampoint search);
	
	/**
	 * 
	 * Class Name: ITikuExampointService.java
	 * @Description: 不分页
	 * @author yuchanglong
	 * @date 2015年7月10日 下午5:05:48
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<TikuExampoint> findTikuExampoint(TikuExampoint search);

	
	/**
	 * 
	 * Class Name: ITikuExampointService.java
	 * @Description: 查询点
	 * @author 周文斌
	 * @date 2015-7-10 下午2:18:31
	 * @version 1.0
	 * @param sectionId
	 * @return
	 */
	List<TikuExampoint> findPointBySectionId(Integer sectionId);
	/**
	 * 
	 * Class Name: ITikuExampointService.java
	 * @Description: 获取该知识点下的子知识点数量
	 * @author yuchanglong
	 * @date 2015年7月13日 下午12:57:07
	 * @version 1.0
	 * @param pointId
	 * @return
	 */
	Integer getCPointCount(Integer pointId); 
	/**
	 * 
	 * Class Name: ITikuExampointService.java
	 * @Description: 如果存在子知识点一并删除
	 * @author yuchanglong
	 * @date 2015年7月13日 下午1:13:27
	 * @version 1.0
	 * @param id
	 */
	void delByChecked(Integer id);
	
	/**
	 * 
	 * Class Name: ITikuChapterService.java
	 * @Description: 根据科目id删除
	 * @author yuchanglong
	 * @date 2015年7月28日 下午12:39:43
	 * @version 1.0
	 * @param subId
	 */
	void deleteBySubId(Integer subId);

}