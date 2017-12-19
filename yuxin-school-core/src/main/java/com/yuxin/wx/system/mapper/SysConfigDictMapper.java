package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.classes.EduMasterClass;
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

		List<SysConfigDict> findByParentId(String parentId);
		
		List<SysConfigDict> queryConfigDictList();
		
		List<SysConfigDict> findByCompanyId(Integer companyId);
		List<SysConfigDict> queryConfigDictListByDictCode(SysConfigDict sysConfigDict);
		List<SysConfigDict> querySchoolByArea(SysConfigDict sysConfigDict);
		List<SysConfigDict> queryAreaBySchool(String str);
	SysConfigDict queryConfigDictValue(SysConfigDict sysConfigDict);

    List<SysConfigDict> querySchoolListByStepCode(SysConfigDict areaDict);

    List<SysConfigDict> findSchoolBySchoolType(Map<String, Object> map);
  //获取服务类型及服务名称
    List<SysConfigDict> querSysConfigDictList(Map<String, Object> map);
    Integer querSysConfigDictCount(Integer companyId);
    /**
 	  * 
 	  * @author jishangyang 2017年12月17日 下午4:09:11
 	  * @Method: queryEduMasterClass 
 	  * @Description: 查询学段，学年，班级
 	  * @param ems
 	  * @return 
 	  * @throws
 	  */
    List<EduMasterClass> queryEduMasterClass(EduMasterClass ems);
}
