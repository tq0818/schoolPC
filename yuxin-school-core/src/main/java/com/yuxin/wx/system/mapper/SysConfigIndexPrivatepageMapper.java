package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigIndexPrivatepage;
import com.yuxin.wx.vo.system.SysConfigPrivatePageVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysConfigIndexPrivatepageMapper extends BaseMapper<SysConfigIndexPrivatepage> {
	List<SysConfigIndexPrivatepage> findBySchoolId(Integer schoolId);
	
	List<SysConfigPrivatePageVo> findList(SysConfigIndexPrivatepage search);
	List<SysConfigIndexPrivatepage> findList2(SysConfigIndexPrivatepage search);
	List<SysConfigIndexPrivatepage> findByTemplate(SysConfigIndexPrivatepage search);
	
}