package com.yuxin.wx.company.impl;


import java.text.SimpleDateFormat;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.IOrganInviteRewardRecordService;
import com.yuxin.wx.company.mapper.OrganInviteRewardRecordMapper;
import com.yuxin.wx.model.company.OrganInviteRewardRecord;
import com.yuxin.wx.vo.company.OrgInviteRecordDetailListVo;
import com.yuxin.wx.vo.company.OrgInviteRecordListVo;


/**
 * Service Implementation:OrganInviteRewardRecord
 * @author chopin
 * @date 2017-3-13
 */
@Service
@Transactional
public class OrganInviteRewardRecordServiceImpl extends BaseServiceImpl implements IOrganInviteRewardRecordService {

	@Autowired
	private OrganInviteRewardRecordMapper organInviteRewardRecordMapper;
	
	/**
	 * 
	* @Title: saveOrganInviteRewardRecord
	* @Description: 新增OrganInviteRewardRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public void insert(OrganInviteRewardRecord entity){
		organInviteRewardRecordMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveOrganInviteRewardRecord 
	* @Description: 批量新增OrganInviteRewardRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<OrganInviteRewardRecord> entity){
		organInviteRewardRecordMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateOrganInviteRewardRecord 
	* @Description: 编辑OrganInviteRewardRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public void update(OrganInviteRewardRecord entity){
		organInviteRewardRecordMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteOrganInviteRewardRecordById 
	* @Description: 根据id删除OrganInviteRewardRecord
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	 @Override
	public void deleteOrganInviteRewardRecordById(Integer id){
		organInviteRewardRecordMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteOrganInviteRewardRecordByIds 
	* @Description: 根据id批量删除OrganInviteRewardRecord
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public void deleteOrganInviteRewardRecordByIds(Integer[] ids){
		organInviteRewardRecordMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findOrganInviteRewardRecordById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public OrganInviteRewardRecord findOrganInviteRewardRecordById(Integer id){
		return organInviteRewardRecordMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findOrganInviteRewardRecordByPage 
	* @Description: 分页查询
	* @return
	* @return List<OrganInviteRewardRecord>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public List<OrganInviteRewardRecord> findOrganInviteRewardRecordByPage(OrganInviteRewardRecord search){
		return organInviteRewardRecordMapper.page(search);
	}

	@Override
	public int getTotalInviteNumber(Integer companyId) {
		return organInviteRewardRecordMapper.getTotalInviteNumber(companyId);
	}

	@Override
	public int getTotalConsumerTimes(Integer companyId) {
		return organInviteRewardRecordMapper.getTotalConsumerTimes(companyId);
	}

	@Override
	public double getTotalConsumeBalance(Integer companyId) {
		return organInviteRewardRecordMapper.getTotalConsumeBalance(companyId);
	}

	@Override
	public double getTotalRewardBalance(Integer companyId) {
		return organInviteRewardRecordMapper.getTotalRewardBalance(companyId);
	}

	@Override
	public List<OrgInviteRecordListVo> queryRewardsRecordList(OrgInviteRecordListVo search) {
		return organInviteRewardRecordMapper.queryRewardsRecordList(search);
	}

	@Override
	public int queryRewardsRecordListCount(OrgInviteRecordListVo search) {
		return organInviteRewardRecordMapper.queryRewardsRecordListCount(search);
	}

	@Override
	public List<OrgInviteRecordListVo> exportRewardsRecordList(OrgInviteRecordListVo search) {
		return organInviteRewardRecordMapper.exportRewardsRecordList(search);
	}

	@Override
	public List<OrgInviteRecordDetailListVo> queryRewardsRecordDetailList(OrgInviteRecordDetailListVo search) {
		return organInviteRewardRecordMapper.queryRewardsRecordDetailList(search);
	}

	@Override
	public int queryRewardsRecordDetailListCount(OrgInviteRecordDetailListVo search) {
		return organInviteRewardRecordMapper.queryRewardsRecordDetailListCount(search);
	}

	@Override
	public int getProxyDetailInviteNumber(OrgInviteRecordDetailListVo search) {
		return organInviteRewardRecordMapper.getProxyDetailInviteNumber(search);
	}

	@Override
	public int getProxyDetailConsumerTimes(OrgInviteRecordDetailListVo search) {
		return organInviteRewardRecordMapper.getProxyDetailConsumerTimes(search);
	}

	@Override
	public double getProxyDetailConsumeBalance(OrgInviteRecordDetailListVo search) {
		return organInviteRewardRecordMapper.getProxyDetailConsumeBalance(search);
	}

	@Override
	public double getProxyDetailRewardBalance(OrgInviteRecordDetailListVo search) {
		return organInviteRewardRecordMapper.getProxyDetailRewardBalance(search);
	}

}
