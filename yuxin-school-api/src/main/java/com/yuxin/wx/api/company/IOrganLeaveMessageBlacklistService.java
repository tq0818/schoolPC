package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.OrganLeaveMessageBlacklist;
/**
 * Service Interface:OrganLeaveMessageBlacklist
 * @author chopin
 * @date 2016-8-5
 */
public interface IOrganLeaveMessageBlacklistService  {
	/**
	 * 
	* @Title: saveOrganLeaveMessageBlacklist
	* @Description: 新增OrganLeaveMessageBlacklist
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by wangzx
	 */
	void insert(OrganLeaveMessageBlacklist entity);
	
	/**
	 * 
	* @Title: batchSaveOrganLeaveMessageBlacklist 
	* @Description: 批量新增OrganLeaveMessageBlacklist
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by wangzx
	 */
	void batchInsert(List<OrganLeaveMessageBlacklist> list);
	
	/**
	 * 
	* @Title: updateOrganLeaveMessageBlacklist 
	* @Description: 编辑OrganLeaveMessageBlacklist
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by wangzx
	 */
	void update(OrganLeaveMessageBlacklist entity);
	
	/**
	 * 
	* @Title: deleteOrganLeaveMessageBlacklistById 
	* @Description: 根据id删除OrganLeaveMessageBlacklist
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by wangzx
	 */
	void deleteOrganLeaveMessageBlacklistById(Integer id);
	
	/**
	 * 
	* @Title: deleteOrganLeaveMessageBlacklistByIds 
	* @Description: 根据id批量删除OrganLeaveMessageBlacklist
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by wangzx
	 */
	void deleteOrganLeaveMessageBlacklistByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findOrganLeaveMessageBlacklistById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by wangzx
	 */
	OrganLeaveMessageBlacklist findOrganLeaveMessageBlacklistById(Integer id);
	
	/**
	 * 
	* @Title: findOrganLeaveMessageBlacklistByPage 
	* @Description: 分页查询
	* @return
	* @return List<OrganLeaveMessageBlacklist>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-8-5
	* @user by wangzx
	 */
	List<OrganLeaveMessageBlacklist> findOrganLeaveMessageBlacklistByPage(OrganLeaveMessageBlacklist search);
	
	/**
	 * 
	 * Class Name: IOrganLeaveMessageBlacklistService.java
	 * @Description: 根据ip查询黑名单
	 * @author dongshuai
	 * @date 2016年8月5日 下午6:58:53
	 * @modifier
	 * @modify-date 2016年8月5日 下午6:58:53
	 * @version 1.0
	 * @param search
	 * @return
	 */
	OrganLeaveMessageBlacklist queryByIp(OrganLeaveMessageBlacklist search);
	
	/**
	 * 
	 * Class Name: IOrganLeaveMessageBlacklistService.java
	 * @Description: 查询黑名单
	 * @author dongshuai
	 * @date 2016年8月8日 上午10:42:55
	 * @modifier
	 * @modify-date 2016年8月8日 上午10:42:55
	 * @version 1.0
	 * @return
	 */
	List<String> queryList();
}