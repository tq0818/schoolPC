package com.yuxin.wx.api.student;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.student.StudentFeeRefund;
import com.yuxin.wx.model.student.StudentPayChangeInfo;
import com.yuxin.wx.vo.fee.RefundVo;
import com.yuxin.wx.vo.fee.StagingTotalVo;
/**
 * Service Interface:StudentFeeRefund
 * @author chopin
 * @date 2015-5-7
 */
public interface IStudentFeeRefundService  {
	/**
	 * 
	* @Title: saveStudentFeeRefund
	* @Description: 新增StudentFeeRefund
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by wangzx
	 */
	void insert(StudentFeeRefund entity);
	
	/**
	 * 
	* @Title: batchSaveStudentFeeRefund 
	* @Description: 批量新增StudentFeeRefund
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by wangzx
	 */
	void batchInsert(List<StudentFeeRefund> list);
	
	/**
	 * 
	* @Title: updateStudentFeeRefund 
	* @Description: 编辑StudentFeeRefund
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by wangzx
	 */
	void update(StudentFeeRefund entity);
	
	/**
	 * 
	* @Title: deleteStudentFeeRefundById 
	* @Description: 根据id删除StudentFeeRefund
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by wangzx
	 */
	void deleteStudentFeeRefundById(Integer id);
	
	/**
	 * 
	* @Title: deleteStudentFeeRefundByIds 
	* @Description: 根据id批量删除StudentFeeRefund
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by wangzx
	 */
	void deleteStudentFeeRefundByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findStudentFeeRefundById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by wangzx
	 */
	StudentFeeRefund findStudentFeeRefundById(Integer id);
	
	/**
	 * 
	* @Title: findStudentFeeRefundByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentFeeRefund>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by wangzx
	 */
	List<StudentFeeRefund> findStudentFeeRefundByPage(StudentFeeRefund search);
	
	/**
	 * 
	* @Title: queryList 
	* @Description: 分页查询退费
	* @return
	* @return List<StudentFeeRefund>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by chopin
	 */
    public PageFinder<RefundVo> queryList(RefundVo search);
    
    /**
     * 
     * Class Name: IStudentFeeRefundService.java
     * @Description: 退费金额统计
     * @author zhang.zx
     * @date 2015年6月12日 下午4:28:36
     * @modifier
     * @modify-date 2015年6月12日 下午4:28:36
     * @version 1.0
     * @param search
     * @return
     */
    StagingTotalVo querythTotalMoney(RefundVo search);
	/**
	 * 
	* @Title: queryList 
	* @Description: 分页查询退费
	* @return
	* @return List<StudentFeeRefund>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by chopin
	 */
    public List<StudentFeeRefund>  findByPayMasterId(Integer payMasterId);
}