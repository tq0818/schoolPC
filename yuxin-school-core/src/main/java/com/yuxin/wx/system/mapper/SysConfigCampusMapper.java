package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigCampus;
import com.yuxin.wx.vo.system.ConfigCampusVo;
/**
 * Service Interface:SysConfigCampus
 * @author wang.zx
 * @date 2014-12-5
 */
public interface SysConfigCampusMapper extends BaseMapper<SysConfigCampus> {
	
	/**
	 * 
	 * Class Name: SysConfigCampusMapper.java
	 * @Description: 根据条件查询校区，不分页
	 * @author liuxindong
	 * @date 2014-12-13 下午4:28:03
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigCampus> queryCampus(SysConfigCampus search);
	
	/**
	 * 
	 * Class Name: SysConfigCampusMapper.java
	 * @Description: 根据schoolId 查询
	 * @author 周文斌
	 * @date 2015-4-30 下午2:15:55
	 * @version 1.0
	 * @param schoolId
	 * @return
	 */
	Integer findCampusBySchoolId(Integer schoolId);
	
	/**
	 * 
	 * Class Name: ISysConfigCampusService.java
	 * @Description: 根据公司id 和 学校id  查询 校区
	 * @author 周文斌
	 * @date 2015-5-5 下午8:56:22
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<SysConfigCampus> findCampusBySchoolCompanyId(Map<String,Object> param);
	
	List<ConfigCampusVo> findCampusVo(SysConfigCampus search);
	
	List<SysConfigCampus> queryCampusBySchoolId(Integer schoolId);
	
	List<SysConfigCampus> checkedCampus(SysConfigCampus search);
	
}