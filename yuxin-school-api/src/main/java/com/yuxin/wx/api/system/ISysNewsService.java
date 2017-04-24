package com.yuxin.wx.api.system;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysNews;
import com.yuxin.wx.vo.company.CompanyFunctionSetVo;
import com.yuxin.wx.vo.system.SysNewsVo;

/**
 * Service Interface:SysNews
 * @author wang.zx
 * @date 2015-3-14
 */
public interface ISysNewsService  {
	/**
	 * 
	* @Title: saveSysNews
	* @Description: 新增SysNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	void insert(SysNews sysNews);
	
	/**
	 * 
	* @Title: batchSaveSysNews 
	* @Description: 批量新增SysNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	void batchInsert(List<SysNews> sysNews);
	
	/**
	 * 
	* @Title: updateSysNews 
	* @Description: 编辑SysNews
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	void update(SysNews sysNews);
	
	/**
	 * 
	* @Title: deleteSysNewsById 
	* @Description: 根据id删除SysNews
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	void deleteSysNewsById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysNewsByIds 
	* @Description: 根据id批量删除SysNews
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	void deleteSysNewsByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysNewsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	SysNews findSysNewsById(Integer id);
	
	/**
	 * 
	* @Title: findSysNewsByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysNews>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-14
	* @user by wangzx
	 */
	List<SysNews> findSysNewsByPage(SysNews sysNews);
	
	/**
	 * 
	 * @Title: findSysNewByPageByKeys 
	 * @Description: 根据条件分页查询
	 * @author zhang.zx
	 * @date 2015-3-31
	 * @version 1.0
	 * @param sysNews
	 * @return PageFinder<SysNews>  返回类型
	 */
	PageFinder<SysNewsVo> findSysNewByPageByKeys(SysNewsVo sysNews);
	
	/**
	 * 
	 * @Title: changNewsStatus 
	 * @Description: 改变公告状态
	 * @author zhang.zx
	 * @date 2015-3-31
	 * @version 1.0
	 * @param sysNews
	 * @return void  返回类型
	 */
	void changNewsStatus(SysNewsVo sysNews);
	
	/**
	 * 
	 * @Title: addNews 
	 * @Description: 添加公告
	 * @author zhang.zx
	 * @date 2015-3-31
	 * @version 1.0
	 * @param sysNews
	 * @return void  返回类型
	 */
	void addNews(SysNewsVo sysNews);
	
	/**
	 * 
	 * Class Name: SysNewsServiceImpl.java
	 * @Description: 添加公告
	 * @author zhang.zx
	 * @date 2015年3月31日 下午4:58:56
	 * @version 1.0
	 * @param sysNews
	 * @return void 返回类型
	 */
	public List<SysNewsVo> loadData(SysNewsVo search);
	
	/**
	 * 
	 * Class Name: ISysNewsService.java
	 * @Description: 根据公司id查询新闻
	 * @author zhang.zx
	 * @date 2015年8月11日 下午4:17:11
	 * @modifier
	 * @modify-date 2015年8月11日 下午4:17:11
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<SysNews> findNewsByCompanyId(Integer companyId);
	
	/**
	 * 
	 * Class Name: ISysNewsService.java
	 * @Description: 查询公司配置功能
	 * @author zhang.zx
	 * @date 2015年8月13日 下午8:23:50
	 * @modifier
	 * @modify-date 2015年8月13日 下午8:23:50
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<CompanyFunctionSetVo> queryCompanyFunctionById(Integer companyId);
	
	/**
	 * 
	 * Class Name: ISysNewsService.java
	 * @Description: 编辑公司功能设置
	 * @author zhang.zx
	 * @date 2015年8月14日 上午10:03:07
	 * @modifier
	 * @modify-date 2015年8月14日 上午10:03:07
	 * @version 1.0
	 */
	void editCompanyFunctions(CompanyFunctionSetVo fun);

	void updateDelFlag(SysNews sysNews);
	
	public List<SysNewsVo> guanwangNewsList(SysNewsVo search);
	public List<SysNewsVo> guanwangNewsList2(SysNewsVo search);
	
	public int guanwangNewsListCount(SysNewsVo search);

	List<SysNews> findSysNewsList(SysNewsVo search);
}