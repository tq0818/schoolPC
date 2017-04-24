package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysCyclePic;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysCyclePicMapper extends BaseMapper<SysCyclePic> {
	
	List<SysCyclePic> querySysCycles(SysCyclePic sysclePic);
	
	void addCyclePics(SysCyclePic sysclePic);
	
	void updateCyclePic(SysCyclePic sysclePic);
	
	void updateSort(SysCyclePic entity);

	int selectCount(Integer companyId);
	
	List<SysCyclePic> findByCompanyId(Integer companyId);
}