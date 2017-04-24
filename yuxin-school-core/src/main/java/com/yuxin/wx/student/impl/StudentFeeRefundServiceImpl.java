package com.yuxin.wx.student.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.util.WebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.student.IStudentFeeRefundService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.student.StudentFeeRefund;
import com.yuxin.wx.model.student.StudentPayChangeInfo;
import com.yuxin.wx.student.mapper.StudentFeeRefundMapper;
import com.yuxin.wx.vo.fee.RefundVo;
import com.yuxin.wx.vo.fee.StagingTotalVo;
import com.yuxin.wx.vo.fee.StagingVo;


/**
 * Service Implementation:StudentFeeRefund
 * @author chopin
 * @date 2015-5-7
 */
@Service
@Transactional
public class StudentFeeRefundServiceImpl extends BaseServiceImpl implements IStudentFeeRefundService {

	@Autowired
	private StudentFeeRefundMapper studentFeeRefundMapper;
	
	/**
	 * 
	* @Title: saveStudentFeeRefund
	* @Description: 新增StudentFeeRefund
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by chopin
	 */
	@Override
	public void insert(StudentFeeRefund entity){
		studentFeeRefundMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveStudentFeeRefund 
	* @Description: 批量新增StudentFeeRefund
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<StudentFeeRefund> entity){
		studentFeeRefundMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateStudentFeeRefund 
	* @Description: 编辑StudentFeeRefund
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by chopin
	 */
	@Override
	public void update(StudentFeeRefund entity){
		studentFeeRefundMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteStudentFeeRefundById 
	* @Description: 根据id删除StudentFeeRefund
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by chopin
	 */
	 @Override
	public void deleteStudentFeeRefundById(Integer id){
		studentFeeRefundMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteStudentFeeRefundByIds 
	* @Description: 根据id批量删除StudentFeeRefund
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by chopin
	 */
	@Override
	public void deleteStudentFeeRefundByIds(Integer[] ids){
		studentFeeRefundMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findStudentFeeRefundById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by chopin
	 */
	@Override
	public StudentFeeRefund findStudentFeeRefundById(Integer id){
		return studentFeeRefundMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findStudentFeeRefundByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentFeeRefund>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by chopin
	 */
	@Override
	public List<StudentFeeRefund> findStudentFeeRefundByPage(StudentFeeRefund search){
		return studentFeeRefundMapper.page(search);
	}
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
    @Override
    public PageFinder<RefundVo> queryList(RefundVo search) {
        List<RefundVo> al=studentFeeRefundMapper.queryRefundList(search);
        int pageCount=studentFeeRefundMapper.pageCount2(search);
        PageFinder<RefundVo> pf=new PageFinder<RefundVo>(search.getPage(),search.getPageSize(),pageCount,al);
        return pf;
    }
	/**
	 * 
	* @Title: queryList 
	* @Description: 根据订单查退费信息
	* @return
	* @return List<StudentFeeRefund>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-7
	* @user by chopin
	 */
    @Override
    public List<StudentFeeRefund>  findByPayMasterId(Integer payMasterId){
    	return studentFeeRefundMapper.queryByPayMasterId(payMasterId);
    }

	@Override
	public StagingTotalVo querythTotalMoney(RefundVo search) {
		// TODO Auto-generated method stub
		return studentFeeRefundMapper.querythTotalMoney(search);
	}


}
