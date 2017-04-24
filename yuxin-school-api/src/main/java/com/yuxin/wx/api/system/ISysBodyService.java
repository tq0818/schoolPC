package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;

public interface ISysBodyService {

	/**
	 * 发布到前台
	 * @author chopin
	 */
	public List<SysConfigPrivatePageVo> publishToFront(Integer companyId,Integer schoolId,Integer templateId);
}
