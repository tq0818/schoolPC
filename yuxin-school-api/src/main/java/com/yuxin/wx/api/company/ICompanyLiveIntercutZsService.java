package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyLiveIntercutZs;
/**
 * Service Interface:CompanyLiveIntercutZs
 * @author wang.zx
 * @date 2015-12-11
 */
public interface ICompanyLiveIntercutZsService  {
	/**
	 * 
	* @Title: saveCompanyLiveIntercutZs
	* @Description: 新增CompanyLiveIntercutZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	void insert(CompanyLiveIntercutZs entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveIntercutZs 
	* @Description: 批量新增CompanyLiveIntercutZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	void batchInsert(List<CompanyLiveIntercutZs> list);
	
	/**
	 * 
	* @Title: updateCompanyLiveIntercutZs 
	* @Description: 编辑CompanyLiveIntercutZs
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	void update(CompanyLiveIntercutZs entity);
	
	/**
	 * 
	* @Title: deleteCompanyLiveIntercutZsById 
	* @Description: 根据id删除CompanyLiveIntercutZs
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	void deleteCompanyLiveIntercutZsById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyLiveIntercutZsByIds 
	* @Description: 根据id批量删除CompanyLiveIntercutZs
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	void deleteCompanyLiveIntercutZsByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyLiveIntercutZsById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	CompanyLiveIntercutZs findCompanyLiveIntercutZsById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyLiveIntercutZsByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveIntercutZs>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-11
	* @user by wangzx
	 */
	List<CompanyLiveIntercutZs> findCompanyLiveIntercutZsByPage(CompanyLiveIntercutZs search);
	
	/**
	 * 
	 * Class Name: ICompanyLiveIntercutZsService.java
	 * @Description: 查看已存在插播
	 * @author 周文斌
	 * @date 2015-12-11 下午7:38:46
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<CompanyLiveIntercutZs> findAllByCont(CompanyLiveIntercutZs search);
	
	/**
	 * 
	 * Class Name: ICompanyLiveIntercutZsService.java
	 * @Description: 查看已存在插播总数
	 * @author 周文斌
	 * @date 2015-12-11 下午7:38:46
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Integer findCountAllByCont(CompanyLiveIntercutZs search);
	
	/**
	 * 
	 * Class Name: ICompanyLiveIntercutZsService.java
	 * @Description: 根据条件取消插播课件
	 * @author 周文斌
	 * @date 2015-12-11 下午9:00:50
	 * @version 1.0
	 * @param search
	 */
	void delByCont(CompanyLiveIntercutZs search);

	/**
	 * 
	 * Class Name: CompanyLiveIntercutZsMapper.java
	 * @Description: 查询是否存在
	 * @author 周文斌
	 * @date 2015-12-14 下午9:23:50
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<CompanyLiveIntercutZs> findExist(CompanyLiveIntercutZs search);
}