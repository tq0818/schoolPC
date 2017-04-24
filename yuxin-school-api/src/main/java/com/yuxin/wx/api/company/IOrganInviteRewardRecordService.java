package com.yuxin.wx.api.company;


import java.util.List;


import com.yuxin.wx.model.company.OrganInviteRewardRecord;
import com.yuxin.wx.vo.company.OrgInviteRecordDetailListVo;
import com.yuxin.wx.vo.company.OrgInviteRecordListVo;
/**
 * Service Interface:OrganInviteRewardRecord
 * @author chopin
 * @date 2017-3-13
 */
public interface IOrganInviteRewardRecordService  {
	/**
	 * 
	* @Title: saveOrganInviteRewardRecord
	* @Description: 新增OrganInviteRewardRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void insert(OrganInviteRewardRecord entity);
	
	/**
	 * 
	* @Title: batchSaveOrganInviteRewardRecord 
	* @Description: 批量新增OrganInviteRewardRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void batchInsert(List<OrganInviteRewardRecord> list);
	
	/**
	 * 
	* @Title: updateOrganInviteRewardRecord 
	* @Description: 编辑OrganInviteRewardRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void update(OrganInviteRewardRecord entity);
	
	/**
	 * 
	* @Title: deleteOrganInviteRewardRecordById 
	* @Description: 根据id删除OrganInviteRewardRecord
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void deleteOrganInviteRewardRecordById(Integer id);
	
	/**
	 * 
	* @Title: deleteOrganInviteRewardRecordByIds 
	* @Description: 根据id批量删除OrganInviteRewardRecord
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void deleteOrganInviteRewardRecordByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findOrganInviteRewardRecordById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	OrganInviteRewardRecord findOrganInviteRewardRecordById(Integer id);
	
	/**
	 * 
	* @Title: findOrganInviteRewardRecordByPage 
	* @Description: 分页查询
	* @return
	* @return List<OrganInviteRewardRecord>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	List<OrganInviteRewardRecord> findOrganInviteRewardRecordByPage(OrganInviteRewardRecord search);

int getTotalInviteNumber(Integer companyId);
	
	int getTotalConsumerTimes(Integer companyId);
	
	double getTotalConsumeBalance(Integer companyId);
	
	double getTotalRewardBalance(Integer companyId);
	
	List<OrgInviteRecordListVo> queryRewardsRecordList(OrgInviteRecordListVo search);
	
	int queryRewardsRecordListCount(OrgInviteRecordListVo search);
	
	List<OrgInviteRecordListVo> exportRewardsRecordList(OrgInviteRecordListVo search);

	List<OrgInviteRecordDetailListVo> queryRewardsRecordDetailList(OrgInviteRecordDetailListVo search);
	
	int queryRewardsRecordDetailListCount(OrgInviteRecordDetailListVo search);
	
	int getProxyDetailInviteNumber(OrgInviteRecordDetailListVo search);
	
	int getProxyDetailConsumerTimes(OrgInviteRecordDetailListVo search);
	
	double getProxyDetailConsumeBalance(OrgInviteRecordDetailListVo search);
	
	double getProxyDetailRewardBalance(OrgInviteRecordDetailListVo search);
}