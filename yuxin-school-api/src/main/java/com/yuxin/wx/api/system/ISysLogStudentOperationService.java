package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysLogStudentOperation;
import com.yuxin.wx.vo.system.SysLogStudentOperationVo;
/**
 * Service Interface:SysLogStudentOperation
 * @author wang.zx
 * @date 2017-2-14
 */
public interface ISysLogStudentOperationService  {
	/**
	 * 
	* @Title: saveSysLogStudentOperation
	* @Description: 新增SysLogStudentOperation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by wangzx
	 */
	void insert(SysLogStudentOperation entity);
	
	/**
	 * 
	* @Title: batchSaveSysLogStudentOperation 
	* @Description: 批量新增SysLogStudentOperation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by wangzx
	 */
	void batchInsert(List<SysLogStudentOperation> list);
	
	/**
	 * 
	* @Title: updateSysLogStudentOperation 
	* @Description: 编辑SysLogStudentOperation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by wangzx
	 */
	void update(SysLogStudentOperation entity);
	
	/**
	 * 
	* @Title: deleteSysLogStudentOperationById 
	* @Description: 根据id删除SysLogStudentOperation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by wangzx
	 */
	void deleteSysLogStudentOperationById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysLogStudentOperationByIds 
	* @Description: 根据id批量删除SysLogStudentOperation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by wangzx
	 */
	void deleteSysLogStudentOperationByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysLogStudentOperationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by wangzx
	 */
	SysLogStudentOperation findSysLogStudentOperationById(Integer id);
	
	/**
	 * 
	* @Title: findSysLogStudentOperationByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysLogStudentOperation>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by wangzx
	 */
	List<SysLogStudentOperation> findSysLogStudentOperationByPage(SysLogStudentOperation search);
	
	/**
	 * 
	 * Class Name: ISysLogStudentOperationService.java
	 * @Description: 根据条件查询一个 出来  0正序， 1 倒序
	 * @author 周文斌
	 * @date 2017-2-14 下午3:35:19
	 * @modify	2017-2-14 下午3:35:19
	 * @version 
	 * @param slso
	 * @param sort	0 正序 ， 1 倒序
	 * @return
	 */
	SysLogStudentOperation selectOne(SysLogStudentOperation slso,Integer sort);
	
	/**
	 * 
	 * Class Name: ISysLogStudentOperationService.java
	 * @Description: 根据条件查询 分页
	 * @author 周文斌
	 * @date 2017-2-14 下午3:48:24
	 * @modify	2017-2-14 下午3:48:24
	 * @version 
	 * @param slso
	 * @param sort 0 正序 ， 1 倒序
	 * @return
	 */
	PageFinder<SysLogStudentOperation> selectList(SysLogStudentOperation slso,Integer sort);
	
	/**
	 * 
	 * Class Name: ISysLogStudentOperationService.java
	 * @Description: 根据 时间段 查询分页
	 * @author 周文斌
	 * @date 2017-2-14 下午4:19:02
	 * @modify	2017-2-14 下午4:19:02
	 * @version 
	 * @param slsoVo
	 * @param sort 	0 正序 ， 1 倒序
	 * @return
	 */
	PageFinder<SysLogStudentOperation> selectListByDate(SysLogStudentOperationVo slsoVo,Integer sort);

	List<SysLogStudentOperationVo> findBySearch(SysLogStudentOperationVo search);
}