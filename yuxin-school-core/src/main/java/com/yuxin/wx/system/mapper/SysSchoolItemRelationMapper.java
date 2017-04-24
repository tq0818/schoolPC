package com.yuxin.wx.system.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysSchoolItemRelation;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysSchoolItemRelationMapper extends BaseMapper<SysSchoolItemRelation> {
	/**
	 * 
	 * Class Name: SysSchoolItemRelationMapper.java
	 * @Description: 根据学科id查询
	 * @author yuchanglong
	 * @date 2016年1月7日 上午10:30:26
	 * @version 1.0
	 * @param itemId
	 * @return
	 */
	SysSchoolItemRelation findSysSchoolItemRelationByItemId(SysSchoolItemRelation search);
	
}