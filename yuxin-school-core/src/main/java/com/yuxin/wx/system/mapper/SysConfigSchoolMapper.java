package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:SysConfigSchool
 * @author wang.zx
 * @date 2014-12-5
 */
public interface SysConfigSchoolMapper extends BaseMapper<SysConfigSchool> {
	List<SysConfigSchool> queryAll(Integer companyId);

	List<SysConfigSchool> findSchoolByCompanyId(Integer companyId);
	
	Integer queryDefaultSchool(Integer companyId);

	/**
	 * 
	 * Class Name: ISysConfigSchoolService.java
	 * @Description: 根据公司id和学校id 插叙
	 * @author 周文斌
	 * @date 2015-5-13 下午12:33:31
	 * @version 1.0
	 * @param param
	 * @return
	 */
	SysConfigSchool findSchoolByMap(Map<String,Object> param);

	/**
	 * 
	 * Class Name: ISysConfigSchoolService.java
	 * @Description: 根据学校id 查询 学校名字 唯一性
	 * @author 周文斌
	 * @date 2015-5-13 下午4:11:17
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findUnquieSchool(Map<String,Object> param);

	/**
	 * 
	 * Class Name: ISysConfigSchoolService.java
	 * @Description: 查询已开通分校数
	 * @author 周文斌
	 * @date 2015-6-25 上午10:49:06
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	Integer findSchoolCountByCompanyId(Integer companyId);
	
	List<SysConfigSchool> queryAllByCompanyId(Integer companyId);

	/**
	 * 
	 * Class Name: ISysConfigSchoolService.java
	 * @Description: 查询分校域名是否存在
	 * @author 周文斌
	 * @date 2016-7-28 下午3:49:26
	 * @version 1.0
	 * @param scs
	 * @return
	 */
	List<Integer> findExistBySuffix(SysConfigSchool scs);
}