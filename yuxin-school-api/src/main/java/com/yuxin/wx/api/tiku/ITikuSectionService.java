package com.yuxin.wx.api.tiku;

import java.util.List;

import com.yuxin.wx.model.tiku.TikuSection;
/**
 * Service Interface:TikuSection
 * @author wang.zx
 * @date 2015-7-8
 */
public interface ITikuSectionService  {
	/**
	 * 
	* @Title: saveTikuSection
	* @Description: 新增TikuSection
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void insert(TikuSection entity);
	
	/**
	 * 
	* @Title: batchSaveTikuSection 
	* @Description: 批量新增TikuSection
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void batchInsert(List<TikuSection> list);
	
	/**
	 * 
	* @Title: updateTikuSection 
	* @Description: 编辑TikuSection
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void update(TikuSection entity);
	
	/**
	 * 
	* @Title: deleteTikuSectionById 
	* @Description: 根据id删除TikuSection
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuSectionById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuSectionByIds 
	* @Description: 根据id批量删除TikuSection
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuSectionByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuSectionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	TikuSection findTikuSectionById(Integer id);
	
	/**
	 * 
	* @Title: findTikuSectionByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuSection>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	List<TikuSection> findTikuSectionByPage(TikuSection search);
	
	/**
	 * 
	 * Class Name: ITikuSectionService.java
	 * @Description: 不分页查询
	 * @author yuchanglong
	 * @date 2015年7月10日 下午3:20:46
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<TikuSection> findTikuSection(TikuSection search);
	/**
	 * 
	 * Class Name: ITikuSectionService.java
	 * @Description: 查询该节下知识点的数量
	 * @author yuchanglong
	 * @date 2015年7月10日 下午3:27:02
	 * @version 1.0
	 * @param secId
	 * @return
	 */
	Integer secCount(Integer secId);

	/**
	 * 
	 * Class Name: ITikuSectionService.java
	 * @Description: 查询节
	 * @author 周文斌
	 * @date 2015-7-10 下午2:13:06
	 * @version 1.0
	 * @param chapterId
	 * @return
	 */
	List<TikuSection> findSectionByChapterId(Integer chapterId);
	
	/**
	 * 
	 * Class Name: ITikuSectionService.java
	 * @Description: 根据章查询节顺序最大值
	 * @author yuchanglong
	 * @date 2015年7月14日 下午6:49:23
	 * @version 1.0
	 * @param chapterId
	 * @return
	 */
	Integer getMaxSectionOrder(Integer chapterId);
	
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