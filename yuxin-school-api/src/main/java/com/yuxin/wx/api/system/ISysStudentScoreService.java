package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysStudentScore;
/**
 * Service Interface:SysStudentScore
 * @author chopin
 * @date 2016-11-16
 */
public interface ISysStudentScoreService  {
	/**
	 * 
	* @Title: saveSysStudentScore
	* @Description: 新增SysStudentScore
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by wangzx
	 */
	void insert(SysStudentScore entity);
	
	/**
	 * 
	* @Title: batchSaveSysStudentScore 
	* @Description: 批量新增SysStudentScore
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by wangzx
	 */
	void batchInsert(List<SysStudentScore> list);
	
	/**
	 * 
	* @Title: updateSysStudentScore 
	* @Description: 编辑SysStudentScore
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by wangzx
	 */
	void update(SysStudentScore entity);
	
	/**
	 * 
	* @Title: deleteSysStudentScoreById 
	* @Description: 根据id删除SysStudentScore
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by wangzx
	 */
	void deleteSysStudentScoreById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysStudentScoreByIds 
	* @Description: 根据id批量删除SysStudentScore
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by wangzx
	 */
	void deleteSysStudentScoreByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysStudentScoreById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by wangzx
	 */
	SysStudentScore findSysStudentScoreById(Integer id);
	
	/**
	 * 
	* @Title: findSysStudentScoreByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysStudentScore>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by wangzx
	 */
	List<SysStudentScore> findSysStudentScoreByPage(SysStudentScore search);
	
	/**
	 * 
	 * Class Name: ISysStudentScoreService.java
	 * @Description: 导入竞赛成绩
	 * @author dongshuai
	 * @date 2016年11月17日 下午4:25:22
	 * @modifier
	 * @modify-date 2016年11月17日 下午4:25:22
	 * @version 1.0
	 * @param list
	 * @return
	 */
	boolean importCompetitionScore(List<List<String>> list,Integer companyId);
	
	/**
	 * 
	 * Class Name: ISysStudentScoreService.java
	 * @Description: 竞赛成绩分页查询
	 * @author dongshuai
	 * @date 2016年11月17日 下午8:20:30
	 * @modifier
	 * @modify-date 2016年11月17日 下午8:20:30
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<SysStudentScore> querySysStudentScoreData(SysStudentScore search);
}