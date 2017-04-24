package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyCouponsLib;
import com.yuxin.wx.vo.company.CompanyCouponsLibOrderVo;
import com.yuxin.wx.vo.company.CompanyCouponsLibVo;

/**
 * Service Interface:CompanyCouponsLib
 * @author chopin
 * @date 2016-6-20
 */
public interface ICompanyCouponsLibService  {
	/**
	 * 
	* @Title: saveCompanyCouponsLib
	* @Description: 新增CompanyCouponsLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void insert(CompanyCouponsLib entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyCouponsLib 
	* @Description: 批量新增CompanyCouponsLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void batchInsert(List<CompanyCouponsLib> list);
	
	/**
	 * 
	* @Title: updateCompanyCouponsLib 
	* @Description: 编辑CompanyCouponsLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void update(CompanyCouponsLib entity);
	
	/**
	 * 
	* @Title: deleteCompanyCouponsLibById 
	* @Description: 根据id删除CompanyCouponsLib
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void deleteCompanyCouponsLibById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyCouponsLibByIds 
	* @Description: 根据id批量删除CompanyCouponsLib
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	void deleteCompanyCouponsLibByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyCouponsLibById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	CompanyCouponsLib findCompanyCouponsLibById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyCouponsLibByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyCouponsLib>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by wangzx
	 */
	List<CompanyCouponsLib> findCompanyCouponsLibByPage(CompanyCouponsLib search);
	
	/**
	 * 
	 * Class Name: ICompanyCouponsLibService.java
	 * @Description: 查询优惠码库BY PatchId
	 * @author dongshuai
	 * @date 2016年6月21日 下午6:58:49
	 * @modifier
	 * @modify-date 2016年6月21日 下午6:58:49
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<CompanyCouponsLib> queryLibsListByPatchId(CompanyCouponsLib search);
	
	/**
	 * 
	 * Class Name: ICompanyCouponsLibService.java
	 * @Description: 查询优惠券使用情况
	 * @author dongshuai
	 * @date 2016年6月22日 下午1:14:33
	 * @modifier
	 * @modify-date 2016年6月22日 下午1:14:33
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<CompanyCouponsLibOrderVo> queryLibsForUseOrder(CompanyCouponsLibVo search);
	
	/**
	 * 
	 * Class Name: ICompanyCouponsLibService.java
	 * @Description: 导出优惠券
	 * @author dongshuai
	 * @date 2016年6月22日 下午2:47:24
	 * @modifier
	 * @modify-date 2016年6月22日 下午2:47:24
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<CompanyCouponsLib> queryLibsListByPatchIdExport(CompanyCouponsLib search);
	/**
	 * 
	 * Class Name: ICompanyCouponsLibService.java
	 * @Description: 根据code查询优惠券
	 * @author yuchanglong
	 * @date 2016年6月22日 上午10:20:14
	 * @version 1.0
	 * @param code
	 * @return
	 */
	CompanyCouponsLib findOneByCode(String code);
}