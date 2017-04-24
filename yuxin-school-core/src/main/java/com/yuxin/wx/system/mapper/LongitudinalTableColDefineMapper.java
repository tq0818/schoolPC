package com.yuxin.wx.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.LongitudinalTableColDefine;
import com.yuxin.wx.model.system.SysBlackList;

public interface LongitudinalTableColDefineMapper extends BaseMapper<LongitudinalTableColDefine>{
	/**
	 * 查询某公司的纵向表设置
	 * @param companyId
	 * @return
	 */
	 List<LongitudinalTableColDefine> findByCompany(@Param("companyId") Integer companyId,@Param("tableName") String tableName);
}
