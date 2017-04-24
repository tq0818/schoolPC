package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.OrganLeaveMessage;
import com.yuxin.wx.vo.user.OrganLeaveMessageVo;
/**
 * Service Interface:OrganLeaveMessage
 * @author chopin
 * @date 2016-2-18
 */
public interface IOrganLeaveMessageService  {
	/**
	 * 
	* @Title: saveOrganLeaveMessage
	* @Description: 新增OrganLeaveMessage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	void insert(OrganLeaveMessage entity);
	
	/**
	 * 
	* @Title: batchSaveOrganLeaveMessage 
	* @Description: 批量新增OrganLeaveMessage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	void batchInsert(List<OrganLeaveMessage> list);
	
	/**
	 * 
	* @Title: updateOrganLeaveMessage 
	* @Description: 编辑OrganLeaveMessage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	void update(OrganLeaveMessage entity);
	
	/**
	 * 
	* @Title: deleteOrganLeaveMessageById 
	* @Description: 根据id删除OrganLeaveMessage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	void deleteOrganLeaveMessageById(Integer id);
	
	/**
	 * 
	* @Title: deleteOrganLeaveMessageByIds 
	* @Description: 根据id批量删除OrganLeaveMessage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	void deleteOrganLeaveMessageByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findOrganLeaveMessageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	OrganLeaveMessage findOrganLeaveMessageById(Integer id);
	
	/**
	 * 
	* @Title: findOrganLeaveMessageByPage 
	* @Description: 分页查询
	* @return
	* @return List<OrganLeaveMessage>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by wangzx
	 */
	List<OrganLeaveMessage> findOrganLeaveMessageByPage(OrganLeaveMessage search);
	
	/**
	 * 
	* @Title: findOrganLeaveMessageByPage 
	* @Description: 查询所有数据
	* @return
	* @return List<OrganLeaveMessage>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-18
	* @user by chopin
	 */
	public List<OrganLeaveMessage> findAll();
	
	/**
	 * 
	 * @Description:分页查询
	 * @author: dongshuai
	 * @date: 2016年5月31日
	 * @param olm
	 * @return
	 *
	 */
	PageFinder<OrganLeaveMessage> queryOrganLeaveMessageList(OrganLeaveMessageVo olm);
	/**
	 * 
	 * @Description:导出
	 * @author: dongshuai
	 * @date: 2016年5月31日
	 * @param olm
	 * @return
	 *
	 */
	List<OrganLeaveMessage> queryOrganLeaveMessageListForExport(OrganLeaveMessageVo olm);
	
	/**
	 * 
	 * Class Name: IOrganLeaveMessageService.java
	 * @Description: 根据ip查询当天留言次数
	 * @author dongshuai
	 * @date 2016年8月5日 下午6:20:31
	 * @modifier
	 * @modify-date 2016年8月5日 下午6:20:31
	 * @version 1.0
	 * @param olm
	 * @return
	 */
	int queryByIp(OrganLeaveMessage olm);
	
	/**
	 * 
	 * Class Name: IOrganLeaveMessageService.java
	 * @Description: 根据手机号查询当天留言list
	 * @author dongshuai
	 * @date 2016年9月12日 下午5:19:01
	 * @modifier
	 * @modify-date 2016年9月12日 下午5:19:01
	 * @version 1.0
	 * @param olm
	 * @return
	 */
	List<OrganLeaveMessage> queryOrganLeaveMessageListByMobile(OrganLeaveMessage olm);
}