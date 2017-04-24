package com.yuxin.wx.api.tiku;

import java.util.List;

import com.yuxin.wx.model.tiku.TikuSubject;
/**
 * Service Interface:TikuSubject
 * @author wang.zx
 * @date 2015-7-8
 */
public interface ITikuSubjectService  {
	/**
	 * 
	* @Title: saveTikuSubject
	* @Description: 新增TikuSubject
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void insert(TikuSubject entity);
	
	/**
	 * 
	* @Title: batchSaveTikuSubject 
	* @Description: 批量新增TikuSubject
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void batchInsert(List<TikuSubject> list);
	
	/**
	 * 
	* @Title: updateTikuSubject 
	* @Description: 编辑TikuSubject
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void update(TikuSubject entity);
	
	/**
	 * 
	* @Title: deleteTikuSubjectById 
	* @Description: 根据id删除TikuSubject
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuSubjectById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuSubjectByIds 
	* @Description: 根据id批量删除TikuSubject
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuSubjectByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuSubjectById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	TikuSubject findTikuSubjectById(Integer id);
	
	/**
	 * 
	* @Title: findTikuSubjectByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuSubject>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	List<TikuSubject> findTikuSubjectByPage(TikuSubject search);
	/**
	 * 
	 * Class Name: ITikuSubjectService.java
	 * @Description: 不分页
	 * @author yuchanglong
	 * @date 2015年7月9日 下午4:15:22
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<TikuSubject> findTikuSubject(TikuSubject search);

	/**
	 * 
	 * Class Name: ITikuSubjectService.java
	 * @Description: 查询科目
	 * @author 周文斌
	 * @date 2015-7-9 下午2:35:46
	 * @version 1.0
	 * @param categoryId
	 * @return
	 */
	List<TikuSubject> findSubByCategoryId(Integer categoryId);
	/**
	 * 
	 * Class Name: ITikuSubjectService.java
	 * @Description: 根据科目名字排重
	 * @author yuchanglong
	 * @date 2015年9月21日 上午11:11:54
	 * @version 1.0
	 * @param subName
	 * @return
	 */
	Integer findCountBySubName(TikuSubject subject);
}