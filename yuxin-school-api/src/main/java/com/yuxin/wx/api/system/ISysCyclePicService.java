package com.yuxin.wx.api.system;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.system.SysCyclePic;

/**
 * Service Interface:SysCyclePic
 * @author chopin
 * @date 2015-4-11
 */
public interface ISysCyclePicService  {
	/**
	 * 
	* @Title: saveSysCyclePic
	* @Description: 新增SysCyclePic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	void insert(SysCyclePic entity);
	
	/**
	 * 
	* @Title: batchSaveSysCyclePic 
	* @Description: 批量新增SysCyclePic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	void batchInsert(List<SysCyclePic> list);
	
	/**
	 * 
	* @Title: updateSysCyclePic 
	* @Description: 编辑SysCyclePic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	void update(SysCyclePic entity);
	
	/**
	 * 
	* @Title: deleteSysCyclePicById 
	* @Description: 根据id删除SysCyclePic
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	void deleteSysCyclePicById(Integer id);
	
	/**
	 * 
	* @Title: deleteSysCyclePicByIds 
	* @Description: 根据id批量删除SysCyclePic
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	void deleteSysCyclePicByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findSysCyclePicById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	SysCyclePic findSysCyclePicById(Integer id);
	
	/**
	 * 
	* @Title: findSysCyclePicByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysCyclePic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by wangzx
	 */
	List<SysCyclePic> findSysCyclePicByPage(SysCyclePic search);
	
	/**
	 * 
	* @Title: findSysCyclePic 
	* @Description: 条件查询
	* @return
	* @return List<SysCyclePic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-13
	* @user zhang.zx
	 */
	List<SysCyclePic> findSysCyclePic(SysCyclePic sysCyclePic);
	
	/**
	 * 
	 * Class Name: ISysCyclePicService.java
	 * @Description: 添加轮播图
	 * @author zhang.zx
	 * @date 2015年4月25日 下午5:13:29
	 * @modifier
	 * @modify-date 2015年4月25日 下午5:13:29
	 * @version 1.0
	 * @param sysCyclePic
	 */
	void addSysCyclePic(SysCyclePic sysCyclePic);
	
	/**
	 * 
	 * Class Name: ISysCyclePicService.java
	 * @Description: 修改轮播图
	 * @author zhang.zx
	 * @date 2015年4月25日 下午5:13:29
	 * @modifier
	 * @modify-date 2015年4月25日 下午5:13:29
	 * @version 1.0
	 * @param sysCyclePic
	 */
	void updateCycles(SysCyclePic sysCyclePic);
	
	void updateSort(SysCyclePic entity);
	/**
	 * 
	 * Class Name: ISysCyclePicService.java
	 * @Description: 查询该公司有效轮播图数量
	 * @author 权飞虎
	 * @date 2015年5月19日 下午3:20:23
	 * @modifier
	 * @modify-date 2015年5月19日 下午3:20:23
	 * @version 1.0
	 * @param currentCompanyId
	 * @return
	 */
	int selectCount(Integer currentCompanyId);
	/**
	 * 
	 * Class Name: ISysCyclePicService.java
	 * @Description: 根据公司Id查询
	 * @author ycl
	 * @date 2015-5-22 下午5:34:14
	 * @modifier
	 * @modify-date 2015-5-22 下午5:34:14
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<SysCyclePic> findByCompanyId(Integer companyId);

}