package com.yuxin.wx.api.tiku;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.tiku.TikuChapter;
/**
 * Service Interface:TikuChapter
 * @author wang.zx
 * @date 2015-7-8
 */
public interface ITikuChapterService  {
	/**
	 * 
	* @Title: saveTikuChapter
	* @Description: 新增TikuChapter
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void insert(TikuChapter entity);
	
	/**
	 * 
	* @Title: batchSaveTikuChapter 
	* @Description: 批量新增TikuChapter
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void batchInsert(List<TikuChapter> list);
	
	/**
	 * 
	* @Title: updateTikuChapter 
	* @Description: 编辑TikuChapter
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void update(TikuChapter entity);
	
	/**
	 * 
	* @Title: deleteTikuChapterById 
	* @Description: 根据id删除TikuChapter
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuChapterById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuChapterByIds 
	* @Description: 根据id批量删除TikuChapter
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuChapterByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuChapterById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	TikuChapter findTikuChapterById(Integer id);
	
	/**
	 * 
	* @Title: findTikuChapterByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuChapter>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	List<TikuChapter> findTikuChapterByPage(TikuChapter search);
	
	/**
	 * 
	 * Class Name: ITikuChapterService.java
	 * @Description: 不分页查询
	 * @author yuchanglong
	 * @date 2015年7月10日 下午12:15:38
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<TikuChapter> findTikuChapter(TikuChapter search);
	/**
	 * 
	 * Class Name: ITikuChapterService.java
	 * @Description: 查询该章下有多少节
	 * @author yuchanglong
	 * @date 2015年7月10日 下午12:41:48
	 * @version 1.0
	 * @param chapterId
	 * @return
	 */
	Integer selectSecByChapter(Integer chapterId);
	
	/**
	 * 
	 * Class Name: ITikuChapterService.java
	 * @Description: 查询章
	 * @author 周文斌
	 * @date 2015-7-10 下午1:58:13
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<TikuChapter> findChapterByCond(Map<String, Object> param);
	
	/**
	 * 
	 * Class Name: ITikuChapterService.java
	 * @Description: 根据科目查询出章顺序的最大值
	 * @author yuchanglong
	 * @date 2015年7月14日 下午3:32:09
	 * @version 1.0
	 * @param subId
	 * @return
	 */
	Integer findMaxChapterOrder(Integer subId);
	/**
	 * 
	 * Class Name: ITikuChapterService.java
	 * @Description: 查询科目下是否存在章
	 * @author yuchanglong
	 * @date 2015年7月28日 上午11:40:20
	 * @version 1.0
	 * @param subId
	 * @return
	 */
	Integer findInfoBySubId(Integer subId);
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