package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysConfigCampus;
import com.yuxin.wx.vo.system.ConfigCampusVo;
/**
 * Service Interface:SysConfigCampus
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ISysConfigCampusService  {
	/**
	 * 
	* @Title: saveSysConfigCampus
	* @Description: 新增SysConfigCampus
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(SysConfigCampus sysConfigCampus);
	
	/**
	 * 
	* @Title: batchSaveSysConfigCampus 
	* @Description: 批量新增SysConfigCampus
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigCampus> sysConfigCampus);
	
	/**
	 * 
	* @Title: updateSysConfigCampus 
	* @Description: 编辑SysConfigCampus
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(SysConfigCampus sysConfigCampus);
	
	/**
	 * 
	* @Title: deleteSysConfigCampusById 
	* @Description: 根据id删除SysConfigCampus
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigCampusById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigCampusByIds 
	* @Description: 根据id批量删除SysConfigCampus
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigCampusByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigCampusById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	SysConfigCampus findSysConfigCampusById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigCampusByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigCampus>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<SysConfigCampus> findSysConfigCampusByPage(SysConfigCampus search);
	
	/**
	 * 
	 * Class Name: ISysConfigCampusService.java
	 * @Description: 根据条件查询校区，不分页
	 * @author liuxindong
	 * @date 2014-12-13 下午4:22:16
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigCampus> findCampus(SysConfigCampus search);
	
	/**
	 * 
	 * Class Name: ISysConfigCampusService.java
	 * @Description: 根据schoolId 查询校区数量
	 * @author 周文斌
	 * @date 2015-4-30 下午2:13:31
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
	
	/**
	 * 
	 * Class Name: ISysConfigCampusService.java
	 * @Description: 查询校区信息
	 * @author ycl
	 * @date 2015-5-5 下午5:47:06
	 * @modifier
	 * @modify-date 2015-5-5 下午5:47:06
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<ConfigCampusVo> findCampusVo(SysConfigCampus search);
	
	/**
	 * 
	 * Class Name: ISysConfigCampusService.java
	 * @Description: 根据分校查询校区
	 * @author zhang.zx
	 * @date 2015-5-5 下午5:47:06
	 * @modifier
	 * @modify-date 2015-5-5 下午5:47:06
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigCampus> queryCampusBySchoolId(Integer schoolId);
	
	/**
	 * 
	 * Class Name: ISysConfigCampusService.java
	 * @Description: 根据编号和名称查询是否有重复数据
	 * @author yuchanglong
	 * @date 2015年7月27日 下午4:18:42
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigCampus> checkedCampus(SysConfigCampus search);
}