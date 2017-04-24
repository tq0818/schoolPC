package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigPageRedirect;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysConfigPageRedirectMapper extends BaseMapper<SysConfigPageRedirect> {
	

	/**
	 * 
	 * Class Name: ISysConfigPageRedirectService.java
	 * @Description: 查询是否存在
	 * @author 周文斌
	 * @date 2015-10-9 下午12:45:33
	 * @version 1.0
	 * @param scpr
	 * @return
	 */
	SysConfigPageRedirect findPageRedirect(SysConfigPageRedirect scpr);
	
	void updateByTempleteId(SysConfigPageRedirect redirect);
	/**
	 * 
	 * @param scpr
	 * @return
	 */
	List<SysConfigPageRedirect> findBySearch(SysConfigPageRedirect scpr);
}