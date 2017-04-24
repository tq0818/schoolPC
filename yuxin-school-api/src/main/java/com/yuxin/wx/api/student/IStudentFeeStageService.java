package com.yuxin.wx.api.student;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.vo.fee.StagingTotalVo;
import com.yuxin.wx.vo.fee.StagingVo;
/**
 * Service Interface:StudentFeeStage
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IStudentFeeStageService  {
	/**
	 * 
	* @Title: saveStudentFeeStage
	* @Description: 新增StudentFeeStage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(StudentFeeStage studentFeeStage);
	
	/**
	 * 
	* @Title: batchSaveStudentFeeStage 
	* @Description: 批量新增StudentFeeStage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<StudentFeeStage> studentFeeStage);
	
	/**
	 * 
	* @Title: updateStudentFeeStage 
	* @Description: 编辑StudentFeeStage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(StudentFeeStage studentFeeStage);
	
	/**
	 * 
	* @Title: deleteStudentFeeStageById 
	* @Description: 根据id删除StudentFeeStage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentFeeStageById(Integer id);
	
	/**
	 * 
	* @Title: deleteStudentFeeStageByIds 
	* @Description: 根据id批量删除StudentFeeStage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentFeeStageByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findStudentFeeStageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	StudentFeeStage findStudentFeeStageById(Integer id);
	
	/**
	 * 
	* @Title: findStudentFeeStageByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentFeeStage>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<StudentFeeStage> findStudentFeeStageByPage(StudentFeeStage search);
	/**
	 * 
	 * Class Name: IStudentFeeStageService.java
	 * @Description: 根据订单查询分期信息
	 * @author 权飞虎
	 * @date 2015年4月27日 下午12:13:55
	 * @modifier
	 * @modify-date 2015年4月27日 下午12:13:55
	 * @version 1.0
	 * @param payMasterId
	 * @return
	 */
	List<StudentFeeStage> findUnPayed(Integer payMasterId);
	
	/**
	 * 
	 * Class Name: IStudentFeeStageService.java
	 * @Description: 根据订单查询分期信息
	 * @author 权飞虎
	 * @date 2015年4月27日 下午12:13:55
	 * @modifier
	 * @modify-date 2015年4月27日 下午12:13:55
	 * @version 1.0
	 * @param payMasterId
	 * @return
	 */
	List<StudentFeeStage> findHasPayed(Integer payMasterId);
	
	/**
	 * 
	 * Class Name: IStudentFeeStageService.java
	 * @Description: 根据订单查询分期信息
	 * @author 权飞虎
	 * @date 2015年4月27日 下午12:13:55
	 * @modifier
	 * @modify-date 2015年4月27日 下午12:13:55
	 * @version 1.0
	 * @param payMasterId
	 * @return
	 */
	List<StudentFeeStage> findAll(Integer payMasterId);
	
	
	/**
	 * 
	 * Class Name: IStudentFeeStageService.java
	 * @Description: 根据订单查询已交费用
	 * @author 权飞虎
	 * @date 2015年5月4日 下午6:02:22
	 * @modifier
	 * @modify-date 2015年5月4日 下午6:02:22
	 * @version 1.0
	 * @param id	订单id
	 * @return
	 */
	Double findSumPayed(Integer id);
	/**
	 * 
	 * Class Name: IStudentFeeStageService.java
	 * @Description: 将分期表delete字段修改为1
	 * @author 权飞虎
	 * @date 2015年5月22日 下午12:19:09
	 * @modifier
	 * @modify-date 2015年5月22日 下午12:19:09
	 * @version 1.0
	 * @param payMasterId
	 */
	void deleteByMasterId(Integer payMasterId);
	
	/**
	 * 
	 * Class Name: IStudentFeeStageService.java
	 * @Description: 流水费用统计查询
	 * @author zhang.zx
	 * @date 2015年6月12日 下午3:20:51
	 * @modifier
	 * @modify-date 2015年6月12日 下午3:20:51
	 * @version 1.0
	 * @param search
	 * @return
	 */
	StagingTotalVo querylsFeeTotal(StagingVo search);
	
	/**
	 * 
	 * Class Name: IStudentFeeStageService.java
	 * @Description: 线上，线下统计金额
	 * @author zhang.zx
	 * @date 2015年6月25日 下午6:28:29
	 * @modifier
	 * @modify-date 2015年6月25日 下午6:28:29
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<Map> queryOnoffFeeTotal(StagingVo search);
}