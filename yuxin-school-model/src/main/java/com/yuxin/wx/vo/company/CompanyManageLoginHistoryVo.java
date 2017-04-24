package com.yuxin.wx.vo.company;


import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * CompanyManageLoginHistoryVo
 * 
 * @author zhangzuxiang
 */
@SuppressWarnings("serial")
public class CompanyManageLoginHistoryVo extends BaseEntity {
	
	private String operator;
	private String operateTime;
	private String targetCompanyId;
	private String targetUserId;
	private String targetSchoolId;
	public CompanyManageLoginHistoryVo() {

	}
	public CompanyManageLoginHistoryVo(String operator, String operateTime,
			String targetCompanyId, String targetUserId, String targetSchoolId) {
		this.operator = operator;
		this.operateTime = operateTime;
		this.targetCompanyId = targetCompanyId;
		this.targetUserId = targetUserId;
		this.targetSchoolId = targetSchoolId;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getTargetCompanyId() {
		return targetCompanyId;
	}
	public void setTargetCompanyId(String targetCompanyId) {
		this.targetCompanyId = targetCompanyId;
	}
	public String getTargetUserId() {
		return targetUserId;
	}
	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}
	public String getTargetSchoolId() {
		return targetSchoolId;
	}
	public void setTargetSchoolId(String targetSchoolId) {
		this.targetSchoolId = targetSchoolId;
	}
	
}
