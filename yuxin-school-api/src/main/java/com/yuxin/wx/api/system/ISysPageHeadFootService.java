package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.vo.system.SysPageHeadFootVo;

/**
 * Service Interface:SysPageHeadFoot
 * @author chopin
 * @date 2015-4-11
 */
public interface ISysPageHeadFootService  {
	/**
	 * 
	* @Title: saveSysPageHeadFoot
	* @Description: 新增SysPageHeadFoot
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	void insert(SysPageHeadFoot entity);
	
	/**
	 * 
	* @Title: batchSaveSysPageHeadFoot 
	* @Description: 批量新增SysPageHeadFoot
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	void batchInsert(List<SysPageHeadFoot> list);
	
	/**
	 * 
	* @Title: updateSysPageHeadFoot 
	* @Description: 编辑SysPageHeadFoot
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	void update(SysPageHeadFoot entity);
	
	/**
	 * 
	* @Title: deleteSysPageHeadFootById 
	* @Description: 根据id删除SysPageHeadFoot
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	void deleteSysPageHeadFootById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysPageHeadFootByIds 
	* @Description: 根据id批量删除SysPageHeadFoot
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	void deleteSysPageHeadFootByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysPageHeadFootById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	SysPageHeadFoot findSysPageHeadFootById(Integer id);
	
	/**
	 * 
	* @Title: findSysPageHeadFootByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysPageHeadFoot>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	List<SysPageHeadFoot> findSysPageHeadFootByPage(SysPageHeadFoot search);
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 条件查询首页页头相关信息
	 * @author zhang.zx
	 * @date 2015年4月13日 上午10:00:14
	 * @modifier
	 * @modify-date 2015年4月13日 上午10:00:14
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysPageHeadFootVo> findSysPageHeadFoot(SysPageHeadFootVo search);
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 查询二级标题
	 * @author zhang.zx
	 * @date 2015年4月13日 上午10:00:14
	 * @modifier
	 * @modify-date 2015年4月13日 上午10:00:14
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysPageHeadFootVo> queryTwoSysPagerList();
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 查询所有页头页尾
	 * @author zhang.zx
	 * @date 2015年4月13日 上午10:00:14
	 * @modifier
	 * @modify-date 2015年4月13日 上午10:00:14
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysPageHeadFootVo> findAll();
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 添加页头标题
	 * @author zhang.zx
	 * @date 2015年4月24日 上午9:48:15
	 * @modifier
	 * @modify-date 2015年4月24日 上午9:48:15
	 * @version 1.0
	 * @param sysPageHeadFoot
	 */
	void insertHeadTitle(SysPageHeadFoot sysPageHeadFoot);
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 查询公司可用标题数量
	 * @author zhang.zx
	 * @date 2015年4月24日 上午9:48:47
	 * @modifier
	 * @modify-date 2015年4月24日 上午9:48:47
	 * @version 1.0
	 * @param sysPageHeadFoot
	 * @return
	 */
	Integer findIsUseHeadTitle(SysPageHeadFoot sysPageHeadFoot);
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 通过
	 * @author 权飞虎
	 * @date 2015年4月23日 下午6:36:36
	 * @modifier
	 * @modify-date 2015年4月23日 下午6:36:36
	 * @version 1.0
	 * @param i
	 * @return
	 */
	List<SysPageHeadFoot> findByConpanyId(int i);
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 改变状态
	 * @author zhang.zx
	 * @date 2015年4月24日 上午11:48:06
	 * @modifier
	 * @modify-date 2015年4月24日 上午11:48:06
	 * @version 1.0
	 * @param entity
	 */
	void changeStatus(SysPageHeadFoot entity);

	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 根据公司id和SysPageHeadFoot.name修改content
	 * @author 权飞虎
	 * @date 2015年4月24日 上午10:32:01
	 * @modifier
	 * @modify-date 2015年4月24日 上午10:32:01
	 * @version 1.0
	 * @param map
	 */
	void updateByNameAndCpId(Map<String, String> map);
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 根据公司id和SysPageHeadFoot.name查询content
	 * @author 权飞虎
	 * @date 2015年4月24日 上午11:01:16
	 * @modifier
	 * @modify-date 2015年4月24日 上午11:01:16
	 * @version 1.0
	 * @param map
	 * @return
	 */
	String selectByCpIdAndName(Map<String, String> map);
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 查询一级页头有效标题
	 * @author zhang.zx
	 * @date 2015年4月24日 下午6:27:40
	 * @modifier
	 * @modify-date 2015年4月24日 下午6:27:40
	 * @version 1.0
	 * @param sysPageHeadFoot
	 * @return
	 */
	List<SysPageHeadFoot> queryHeadIsUse(SysPageHeadFoot sysPageHeadFoot);
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 查询该公司页头的数量
	 * @author 权飞虎
	 * @date 2015年5月19日 下午2:39:25
	 * @modifier
	 * @modify-date 2015年5月19日 下午2:39:25
	 * @version 1.0
	 * @param currentCompanyId
	 * @return
	 */
	int selectHeadCount(Integer currentCompanyId);
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 查询该公司配置页脚的数量
	 * @author 权飞虎
	 * @date 2015年5月19日 下午2:39:25
	 * @modifier
	 * @modify-date 2015年5月19日 下午2:39:25
	 * @version 1.0
	 * @param currentCompanyId
	 * @return
	 */
	int seleFootCount(Integer currentCompanyId);
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 根据公司Id查询所有
	 * @author ycl
	 * @date 2015-5-22 下午3:24:05
	 * @modifier
	 * @modify-date 2015-5-22 下午3:24:05
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<SysPageHeadFoot> queryAllByCompanyId(Integer companyId);
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 查询单个是否存在
	 * @author 周文斌
	 * @date 2015-10-8 下午6:17:25
	 * @version 1.0
	 * @param sphf
	 * @return
	 */
	SysPageHeadFoot findHeadFoot(SysPageHeadFoot sphf);
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 根据配置id查询模板详细信息
	 * @author zhang.zx
	 * @date 2016-03-1 
	 * @version 1.0
	 * @return
	 */
	List<SysPageHeadFootVo> queryFootContentsByConfigId(SysPageHeadFoot search);
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 条件查询页尾信息
	 * @author 
	 * @date 2016年3月2日 下午2:02:22
	 * @modifier
	 * @modify-date 2016年3月2日 下午2:02:22
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysPageHeadFoot> queryFootFlex(SysPageHeadFoot search);
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 根据parentId查询
	 * @author zhang.zx
	 * @date 2016年3月2日 下午3:56:58
	 * @modifier
	 * @modify-date 2016年3月2日 下午3:56:58
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysPageHeadFootVo> queryFootContentByParentId(SysPageHeadFoot search);
	/**
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 根据公司id和page_head_type进行查询
	 * @author xukaiqiang
	 * @date 2016年5月23日 下午12:56:19
	 * @modifier
	 * @modify-date 2016年5月23日 下午12:56:19
	 * @version 1.0
	 * @param sysPageHeadFoot
	 * @return
	 */
	SysPageHeadFoot findEntityByCompanyIdAndType(SysPageHeadFoot sysPageHeadFoot);
	
	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 查询mobile模块
	 * @author dongshuai
	 * @date 2017年2月17日 下午2:22:27
	 * @modifier
	 * @modify-date 2017年2月17日 下午2:22:27
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<SysPageHeadFoot> queryWapheadByCompanyId(Integer companyId);
	
	SysPageHeadFoot queryWapheadByCompanyIdAndUrlName(SysPageHeadFoot search);
}