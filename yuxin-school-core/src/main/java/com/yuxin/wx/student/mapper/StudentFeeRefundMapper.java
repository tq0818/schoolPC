package com.yuxin.wx.student.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.student.StudentFeeRefund;
import com.yuxin.wx.vo.fee.RefundVo;
import com.yuxin.wx.vo.fee.StagingTotalVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface StudentFeeRefundMapper extends BaseMapper<StudentFeeRefund> {
	 List<RefundVo> queryRefundList(RefundVo search);
	 int pageCount2(RefundVo search);
	 StagingTotalVo querythTotalMoney(RefundVo search);
	 List<StudentFeeRefund> queryByPayMasterId(Integer payMasterId);
	
}