package com.yuxin.wx.common;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.company.CompanyVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;

public interface BaseMapper<T> {
	
	/**
	 * 
	* @Title: save 
	* @Description: 添加 
	* @param T
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:08:06
	* @user by wang.zx
	 */
	void insert(T t);
	
	/**
	 * 
	* @Title: batchInsert 
	* @Description: 批量 添加
	* @param list
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-5-13 上午09:44:56
	* @user by wang.zx
	 */
	void batchInsert(List<T> list);
	
	/**
	 * 
	* @Title: update 
	* @Description: 修改
	* @param T
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:09:58
	* @user by wang.zx
	 */
	void update(T t);
	
	/**
	 * 
	* @Title: deleteByIds 
	* @Description: 批量删除 
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:10:24
	* @user by wang.zx
	 */
	void deleteByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: deleteById 
	* @Description: 删除
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午03:42:16
	* @user by wang.zx
	 */
	void deleteById(Integer id);
	
	/**
	 * 
	* @Title: findById 
	* @Description: 根据id查询
	* @param id
	* @return
	* @return T    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:11:46
	* @user by wang.zx
	 */
	T findById(Integer id);
	/**
	 * 
	 * @author jishangyang 2017年12月7日 下午6:27:27
	 * @Method: findNewCompanyById 
	 * @Description: 根据ID查询分校
	 * @param id
	 * @return 
	 * @throws
	 */
	CompanyVo findNewCompanyById(Integer id);
	
	/**
	 * 
	* @Title: queryAll 
	* @Description: 查询所有
	* @return
	* @return List<T>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:12:00
	* @user by wang.zx
	 */
	List<T> queryAll();

	/**
	 * 
	* @Title: page 
	* @Description: 分页查询
	* @param param
	* @return
	* @return List<T>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:12:38
	* @user by wang.zx
	 */
	List<T> page(Object param);
	
	/**
	 * 
	* @Title: pageCount 
	* @Description: 总条数查询
	* @param param
	* @return
	* @return Integer    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:12:51
	* @user by wang.zx
	 */
	Integer pageCount(Object param);
	
	/**
	 * 
	* @Title: queryDetail
	* @Description: 总条数查询
	* @param param
	* @return
	* @return Integer    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:12:51
	* @user by chopin
	 */
	ClassTypeVo queryDetail(Object param);
	
	/**
	 * 
	* @Title: queryDetail
	* @Description: 执行原生SQL查询
	* @param param
	* @return
	* @return Integer    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:12:51
	* @user by chopin
	 */
	List<Map> executeQuery(String sql);
	
	/**
	 * 
	* @Title: queryDetail
	* @Description: 执行原生SQL查询
	* @param param
	* @return
	* @return Integer    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:12:51
	* @user by chopin
	 */
	List<Map> executeInsert(String sql);
	
	/**
	 * 
	* @Title: queryDetail
	* @Description: 执行原生SQL查询
	* @param param
	* @return
	* @return Integer    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:12:51
	* @user by chopin
	 */
	List<Map> executeDelete(String sql);
	
	/**
	 * 
	* @Title: queryDetail
	* @Description: 执行原生SQL查询
	* @param param
	* @return
	* @return Integer    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-04 下午12:12:51
	* @user by chopin
	 */
	List<Map> executeUpdate(String sql);
	
}
