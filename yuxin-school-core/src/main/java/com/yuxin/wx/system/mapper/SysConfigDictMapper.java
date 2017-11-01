package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:SysConfigDict
 * @author wang.zx
 * @date 2014-12-5
 */
public interface SysConfigDictMapper extends BaseMapper<SysConfigDict> {
	 SysConfigDict findByCode(SysConfigDict dict);

		/**
		 * 
		 * Class Name: ISysConfigDictService.java
		 * @Description: 查询教室相关 字典表
		 * @author 周文斌
		 * @date 2015-5-6 下午2:23:57
		 * @version 1.0
		 * @return
		 */
		List<SysConfigDict> findDictByClassroom();

		List<SysConfigDict> findByDicCode(String code);
		
		List<SysConfigDict> queryConfigDictList();
		
		List<SysConfigDict> findByCompanyId(Integer companyId);
		List<SysConfigDict> queryConfigDictListByDictCode(SysConfigDict sysConfigDict);
	SysConfigDict queryConfigDictValue(SysConfigDict sysConfigDict);

    List<SysConfigDict> querySchoolListByStepCode(SysConfigDict areaDict);
}