package com.yuxin.wx.api.classes;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.model.pay.PayOrder;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.vo.classes.ClasspackageVo;
import com.yuxin.wx.vo.course.OrderManage;
import com.yuxin.wx.vo.student.StuPayMasterVo;
/**
 * Service Interface:ClassPackage
 * @author chopin
 * @date 2016-3-21
 */
public interface IClassPackageService  {
	/**
	 * 
	* @Title: saveClassPackage
	* @Description: 新增ClassPackage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void insert(ClassPackage entity);
	
	/**
	 * 
	* @Title: batchSaveClassPackage 
	* @Description: 批量新增ClassPackage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void batchInsert(List<ClassPackage> list);
	
	/**
	 * 
	* @Title: updateClassPackage 
	* @Description: 编辑ClassPackage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void update(ClassPackage entity);
	
	/**
	 * 
	* @Title: deleteClassPackageById 
	* @Description: 根据id删除ClassPackage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void deleteClassPackageById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassPackageByIds 
	* @Description: 根据id批量删除ClassPackage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	void deleteClassPackageByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassPackageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	ClassPackage findClassPackageById(Integer id);
	
	/**
	 * 
	* @Title: findClassPackageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	ClasspackageVo findClassPackageVoById(Integer id);
	
	/**
	 * 
	* @Title: findClassPackageByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassPackage>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by wangzx
	 */
	List<ClassPackage> findClassPackageByPage(ClassPackage search);
	
	/**
	 * 
	 * Class Name: IClassPackageService.java
	 * @Description: 分页查询课程包列表
	 * @author zhang.zx
	 * @date 2016年3月21日 下午6:56:17
	 * @modifier
	 * @modify-date 2016年3月21日 下午6:56:17
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<ClassPackage> findClassPackageLists(ClassPackage search);
	
	/**
	 * 
	 * Class Name: IClassPackageService.java
	 * @Description: 查询课程包（不含分页）
	 * @author zhang.zx
	 * @date 2016年3月22日 下午1:51:47
	 * @modifier
	 * @modify-date 2016年3月22日 下午1:51:47
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<ClassPackage> queryClassPackageByWhere(ClassPackage search);
	
	/**
	 * 
	 * Class Name: IClassPackageService.java
	 * @Description: 查询课程包订单列表
	 * @author zhang.zx
	 * @date 2016年3月29日 上午11:43:34
	 * @modifier
	 * @modify-date 2016年3月29日 上午11:43:34
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<OrderManage> findClassPackageOrderList(OrderManage search);
	/**
	 * 查询课程包里所有的版型id
	 */
	List<Integer> findClassPackageAllClasses(Integer classPackageId);
	
	PageFinder<ClassPackage> queryClassPackageHasCountsByCondition(ClassPackage search);

	Object findByCondition(ClasspackageVo search);
	
	List<ClassPackage> queryCommodityByClassPackage(ClassPackage search);
	
	ClassPackage queryClassPackageByComId(Integer id);
}