package com.yuxin.wx.classes.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassModuleNoOnsale;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface ClassModuleNoOnsaleMapper extends BaseMapper<ClassModuleNoOnsale> {
	
	ClassModuleNoOnsale queryClassModuleOnSale(ClassModuleNoOnsale moduleOnsale);
	ClassModuleNoOnsale queryClassNoOnSale(ClassModuleNoOnsale moduleOnsale);
	ClassModuleNoOnsale queryModuleOnSaleModuleNo(Integer moduleId);
}