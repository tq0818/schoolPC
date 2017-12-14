package com.yuxin.wx.model.classes;

import com.yuxin.wx.common.BaseEntity;

/**
 * 校与校之间课程分享
 * @author cxl
 *
 */
@SuppressWarnings("serial")
public class SchoolShareClassType  extends BaseEntity {
	
	private  Integer sourceCompanyId;
	private  Integer sourceClassTypeId;
	private  Integer targetCompanyId;
	private  Integer targetClassTypeId;
	private  Integer isUsed;
	
	public SchoolShareClassType() {

	}

	public SchoolShareClassType(Integer sourceCompanyId,
			Integer sourceClassTypeId, Integer targetCompanyId,
			Integer targetClassTypeId, Integer isUsed) {
		setId(id);
		this.sourceCompanyId = sourceCompanyId;
		this.sourceClassTypeId = sourceClassTypeId;
		this.targetCompanyId = targetCompanyId;
		this.targetClassTypeId = targetClassTypeId;
		this.isUsed = isUsed;
	}

	public Integer getSourceCompanyId() {
		return sourceCompanyId;
	}

	public void setSourceCompanyId(Integer sourceCompanyId) {
		this.sourceCompanyId = sourceCompanyId;
	}

	public Integer getSourceClassTypeId() {
		return sourceClassTypeId;
	}

	public void setSourceClassTypeId(Integer sourceClassTypeId) {
		this.sourceClassTypeId = sourceClassTypeId;
	}

	public Integer getTargetCompanyId() {
		return targetCompanyId;
	}

	public void setTargetCompanyId(Integer targetCompanyId) {
		this.targetCompanyId = targetCompanyId;
	}

	public Integer getTargetClassTypeId() {
		return targetClassTypeId;
	}

	public void setTargetClassTypeId(Integer targetClassTypeId) {
		this.targetClassTypeId = targetClassTypeId;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
	
	
	
	

}
