package com.yuxin.wx.api.classes;

import java.util.List;

import com.yuxin.wx.model.classes.ClassPackageStage;
/**
 * Service Interface:ClassPackageStage
 * @author chopin
 * @date 2016-3-21
 */
public interface IClassPackageStageService  {
	/**
	 * 
	* @Title: saveClassPackageStage
	* @Description: 新增ClassPackageStage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void insert(ClassPackageStage entity);
	
	/**
	 * 
	* @Title: batchSaveClassPackageStage 
	* @Description: 批量新增ClassPackageStage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void batchInsert(List<ClassPackageStage> list);
	
	/**
	 * 
	* @Title: updateClassPackageStage 
	* @Description: 编辑ClassPackageStage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void update(ClassPackageStage entity);
	
	/**
	 * 
	* @Title: deleteClassPackageStageById 
	* @Description: 根据id删除ClassPackageStage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void deleteClassPackageStageById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassPackageStageByIds 
	* @Description: 根据id批量删除ClassPackageStage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void deleteClassPackageStageByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassPackageStageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	ClassPackageStage findClassPackageStageById(Integer id);
	
	/**
	 * 
	* @Title: findClassPackageStageByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassPackageStage>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	List<ClassPackageStage> findClassPackageStageByPage(ClassPackageStage search);
	
	/**
	 * 
	 * Class Name: IClassPackageStageService.java
	 * @Description: 查询课程包下的所有阶段
	 * @author zhang.zx
	 * @date 2016年3月23日 下午3:43:18
	 * @modifier
	 * @modify-date 2016年3月23日 下午3:43:18
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<ClassPackageStage> queryClassPackageStages(ClassPackageStage search);
}