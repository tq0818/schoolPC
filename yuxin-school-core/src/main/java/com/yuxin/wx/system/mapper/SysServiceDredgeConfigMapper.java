package com.yuxin.wx.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysServiceDredgeConfig;
import com.yuxin.wx.vo.system.SysServiceDredgeVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysServiceDredgeConfigMapper extends BaseMapper<SysServiceDredgeConfig> {

	/**
	 * 
	 * Class Name: ISysServiceDredgeConfigService.java
	 * @Description: 查询是否开启
	 * @author 周文斌
	 * @date 2016-12-12 下午7:28:23
	 * @modify	2016-12-12 下午7:28:23
	 * @version 
	 * @param companyId
	 * @return
	 */
	List<SysServiceDredgeVo> findDredgeByCom(@Param("companyId") Integer companyId);
	
}