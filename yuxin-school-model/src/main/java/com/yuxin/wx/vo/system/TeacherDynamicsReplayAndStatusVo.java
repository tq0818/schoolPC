package com.yuxin.wx.vo.system;

import java.util.Date;

import com.yuxin.wx.model.system.SysTeacherPersonalStatusReplay;

public class TeacherDynamicsReplayAndStatusVo extends SysTeacherPersonalStatusReplay {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tContent;
	
	private Date testDate;

	

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}


	public String gettContent() {
		return tContent;
	}

	public void settContent(String tContent) {
		this.tContent = tContent;
	}
	

}
