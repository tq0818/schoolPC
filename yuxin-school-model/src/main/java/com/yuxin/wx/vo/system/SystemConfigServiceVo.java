package com.yuxin.wx.vo.system;

import java.util.Date;

import com.yuxin.wx.common.BaseEntity;

/**
 * 
 * @ClassName: SystemConfigServiceVo
 * @author zhang.zx
 * @date 2015年8月12日 下午4:39:33
 * @modifier
 * @modify-date 2015年8月12日 下午4:39:33
 * @version 1.0
 */

@SuppressWarnings("serial")
public class SystemConfigServiceVo extends BaseEntity{
	
	private String groupCode;
	private String updator;
	private String updateTime;
	private String delFlag;
	private String companyId;
	
	public SystemConfigServiceVo() {
	}

	public SystemConfigServiceVo(String groupCode, String updator,
			String updateTime, String delFlag, String companyId) {
		super();
		this.groupCode = groupCode;
		this.updator = updator;
		this.updateTime = updateTime;
		this.delFlag = delFlag;
		this.companyId = companyId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
