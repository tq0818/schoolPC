package com.yuxin.wx.api.student;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.vo.student.StudentPaySlaveVo;
/**
 * Service Interface:StudentPaySlave
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IStudentPaySlaveService  {
	/**
	 * 
	* @Title: saveStudentPaySlave
	* @Description: 新增StudentPaySlave
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(StudentPaySlave studentPaySlave);
	
	/**
	 * 
	* @Title: batchSaveStudentPaySlave 
	* @Description: 批量新增StudentPaySlave
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<StudentPaySlave> studentPaySlave);
	
	/**
	 * 
	* @Title: updateStudentPaySlave 
	* @Description: 编辑StudentPaySlave
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(StudentPaySlave studentPaySlave);
	
	/**
	 * 
	* @Title: deleteStudentPaySlaveById 
	* @Description: 根据id删除StudentPaySlave
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentPaySlaveById(Integer id);
	
	/**
	 * 
	* @Title: deleteStudentPaySlaveByIds 
	* @Description: 根据id批量删除StudentPaySlave
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteStudentPaySlaveByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findStudentPaySlaveById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	StudentPaySlave findStudentPaySlaveById(Integer id);
	
	/**
	 * 
	* @Title: findStudentPaySlaveById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	List<StudentPaySlave> findStudentPaySlaveByStuId(Integer stuId);
	
	/**
	 * 
	* @Title: findStudentPaySlaveByPayId 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	public List<StudentPaySlaveVo> findStudentPaySlaveByPayMasterId(Integer mid);
	public List<StudentPaySlaveVo> findStudentPaySlaveByPayMasterId2(Integer mid);
	
	/**
	 * 
	* @Title: findStudentPaySlaveByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentPaySlave>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<StudentPaySlave> findStudentPaySlaveByPage(StudentPaySlave search);
	/**
	 * 
	 * Class Name: IStudentPaySlaveService.java
	 * @Description: 更改班号
	 * @author 权飞虎
	 * @date 2015年5月9日 下午3:21:23
	 * @modifier
	 * @modify-date 2015年5月9日 下午3:21:23
	 * @version 1.0
	 * @param payMasterId
	 * @param str
	 */
	void update2(Integer payMasterId, String str);
	
	/**
	 * 
	 * Class Name: IStudentPaySlaveService.java
	 * @Description: 根据资源id查询人数
	 * @author 周文斌
	 * @date 2015-5-26 下午2:38:37
	 * @version 1.0
	 * @param resourceId
	 * @return
	 */
	Integer findCountByResourceId(Integer resourceId);
	
	/**
	 * 
	 * Class Name: IStudentPaySlaveService.java
	 * @Description: 条件查询子订单
	 * @author zhang.zx
	 * @date 2016年3月25日 上午10:40:48
	 * @modifier
	 * @modify-date 2016年3月25日 上午10:40:48
	 * @version 1.0
	 * @return
	 */
	List<StudentPaySlave> queryStuSlaveBywhere(StudentPaySlave search);
	
	/**
	 * 
	 * Class Name: IStudentPaySlaveService.java
	 * @Description: 删除子订单
	 * @author zhang.zx
	 * @date 2016年3月25日 上午10:42:20
	 * @modifier
	 * @modify-date 2016年3月25日 上午10:42:20
	 * @version 1.0
	 * @param id
	 */
	void updateStuPaySlave(Integer id);

	void updateStatus(Map map);
}