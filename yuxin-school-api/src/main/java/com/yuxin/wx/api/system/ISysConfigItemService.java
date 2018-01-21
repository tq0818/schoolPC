package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysSchoolItemRelation;
/**
 * Service Interface:SysConfigItem
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ISysConfigItemService  {
	/**
	 * 
	* @Title: saveSysConfigItem
	* @Description: 新增SysConfigItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(SysConfigItem sysConfigItem);
	
	/**
	 * 
	* @Title: batchSaveSysConfigItem 
	* @Description: 批量新增SysConfigItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<SysConfigItem> sysConfigItem);
	
	/**
	 * 
	* @Title: updateSysConfigItem 
	* @Description: 编辑SysConfigItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(SysConfigItem sysConfigItem);
	
	/**
	 * 
	* @Title: deleteSysConfigItemById 
	* @Description: 根据id删除SysConfigItem
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigItemById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysConfigItemByIds 
	* @Description: 根据id批量删除SysConfigItem
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteSysConfigItemByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysConfigItemById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	SysConfigItem findSysConfigItemById(Integer id);
	
	/**
	 * 
	* @Title: findSysConfigItemByPid 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<SysConfigItem> findSysConfigItemByPid(String itemType,Integer pid,Integer companyId,Integer schoolId);
	
	/**
	 * 根据条件查询项目，不分页
	 * @param search
	 * @return
	 */
	List<SysConfigItem> findItem(SysConfigItem search);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 公司id id 查询 项目 总数
	 * @author 周文斌
	 * @date 2015-4-30 下午1:49:41
	 * @version 1.0
	 * @param map
	 * @return
	 */
	Integer findProjectBySchoolId(Integer companyId);
	
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据公司id查询 一级项目、二级项目( null:代表1.2级项目， 1:一级项目， 2:二级项目)
	 * @author 周文斌
	 * @date 2015-5-4 下午1:02:47
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<SysConfigItem> findItemByCompanyId(Integer itemType, Integer companyId);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据一级项目id 查询二级项目
	 * @author 周文斌
	 * @date 2015-5-4 下午1:03:42
	 * @version 1.0
	 * @param oneItemId
	 * @return
	 */
	List<SysConfigItem> findTwoByOneId(Integer oneItemId);

	/**
	 * 
	 * Class Name: ISysConfigSchoolService.java
	 * @Description: 插入关联表
	 * @author 周文斌
	 * @date 2015-5-7 下午4:30:54
	 * @version 1.0
	 * @param param
	 */
	void insertRelation(Map<String,Object> param);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据学校id查询 唯一
	 * @author 周文斌
	 * @date 2015-5-7 下午4:48:13
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findUnquieItem(Map<String,Object> param);

	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据公司id查询 唯一
	 * @author 周文斌
	 * @date 2015-5-7 下午4:48:13
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findUnquieItemByUpdate(Map<String,Object> param);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据parentid 修改 状态
	 * @author 周文斌
	 * @date 2015-5-10 下午2:21:24
	 * @version 1.0
	 * @param sci
	 */
	void updateTwoByOne(SysConfigItem sci);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 检查 关系是否存在
	 * @author 周文斌
	 * @date 2015-5-12 下午7:32:27
	 * @version 1.0
	 * @param params
	 * @return
	 */
	SysSchoolItemRelation findExist(Map<String,Object> params);
	void updateDelFlag(Integer id);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 修改关联关系状态
	 * @author 周文斌
	 * @date 2015-5-12 下午7:39:33
	 * @version 1.0
	 * @param params
	 */
	void updateRelation(Map<String,Object> params);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据公司id 和 学校id 查询项目
	 * @author 周文斌
	 * @date 2015-5-14 下午4:49:28
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<SysConfigItem> findItemBySchoolCompanyId(Map<String ,Object> param);
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 查询该公司存在的所有一二级项目
	 * @author 权飞虎
	 * @date 2015年5月20日 上午10:30:06
	 * @modifier
	 * @modify-date 2015年5月20日 上午10:30:06
	 * @version 1.0
	 * @param currentCompanyId
	 * @return
	 */
	int selectCount(Integer currentCompanyId);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 查询状态
	 * @author 周文斌
	 * @date 2015-5-23 下午2:25:18
	 * @version 1.0
	 * @param item
	 * @return
	 */
	List<SysConfigItem> findStatus(SysConfigItem item);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据itemid 查询 当前学校没有使用的项目
	 * @author 周文斌
	 * @date 2015-5-23 下午6:19:14
	 * @version 1.0
	 * @param list
	 * @return
	 */
	List<SysConfigItem> findNotInByItemId(Map<String,Object> param);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据公司和一级项目查询分校
	 * @author zhang.zx
	 * @date 2015年7月27日 上午10:51:45
	 * @modifier
	 * @modify-date 2015年7月27日 上午10:51:45
	 * @version 1.0
	 * @param item
	 * @return
	 */
	List<SysConfigItem> selectSecondItem(SysConfigItem item);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 查询整个项目是否存在名字
	 * @author 周文斌
	 * @date 2015-8-14 下午12:50:18
	 * @version 1.0
	 * @param item
	 * @return
	 */
	SysConfigItem findDelNullByName(SysConfigItem item);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 查询整个项目是否存在名字
	 * @author 周文斌
	 * @date 2015-8-14 下午12:50:18
	 * @version 1.0
	 * @param item
	 * @return
	 */
	SysConfigItem dfindDelNullByName(SysConfigItem item);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 查询是否还有其他分校使用
	 * @author 周文斌
	 * @date 2015-8-14 下午3:20:22
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<SysSchoolItemRelation> findUseByItemId(Map<String, Object> param);

	void update(List<SysConfigItem> sysConfigItems);

	List<SysConfigItem> findItemByIds(List<Integer> list);
	List<SysConfigItem>  findByParentCode(SysConfigItem item);
	List<SysConfigItem> findItemNameByItemCode(Map<String,Integer> itema);

	/**
	 *
	 *
	 * @author jishangyang 2017年12月9日 下午3:10:25
	 * @Method: findschooIdByCompanyId 
	 * @Description:根据公司ID查询学校ID
	 * @param companyId
	 * @return 
	 * @throws
	 */
	Integer findschooIdByCompanyId(Integer companyId);
	Integer findItemNameByLessonMap(Map<String,Object> lessonMap);
}