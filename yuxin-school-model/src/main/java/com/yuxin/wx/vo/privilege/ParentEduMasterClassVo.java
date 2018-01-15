package com.yuxin.wx.vo.privilege;

import java.util.List;

public class ParentEduMasterClassVo {
	//科目代码
	private String subjectCode;
	//用户标识号
	private String userId;
	
	private List<EduMasterClassVo> eduMasterClassVo;

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<EduMasterClassVo> getEduMasterClassVo() {
		return eduMasterClassVo;
	}

	public void setEduMasterClassVo(List<EduMasterClassVo> eduMasterClassVo) {
		this.eduMasterClassVo = eduMasterClassVo;
	}
	
	
}
