package com.yuxin.wx.api.company;


import java.util.List;

import com.yuxin.wx.model.teacher.TeacherInviteRewardRecord;
import com.yuxin.wx.vo.company.TeacherInviteRecordDetailListVo;
import com.yuxin.wx.vo.company.TeacherInviteRecordListVo;
/**
 * Service Interface:TeacherInviteRewardRecord
 * @author chopin
 * @date 2017-3-13
 */
public interface ITeacherInviteRewardRecordService  {
	/**
	 * 
	* @Title: saveTeacherInviteRewardRecord
	* @Description: 新增TeacherInviteRewardRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void insert(TeacherInviteRewardRecord entity);
	
	/**
	 * 
	* @Title: batchSaveTeacherInviteRewardRecord 
	* @Description: 批量新增TeacherInviteRewardRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void batchInsert(List<TeacherInviteRewardRecord> list);
	
	/**
	 * 
	* @Title: updateTeacherInviteRewardRecord 
	* @Description: 编辑TeacherInviteRewardRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void update(TeacherInviteRewardRecord entity);
	
	/**
	 * 
	* @Title: deleteTeacherInviteRewardRecordById 
	* @Description: 根据id删除TeacherInviteRewardRecord
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void deleteTeacherInviteRewardRecordById(Integer id);
	
	/**
	 * 
	* @Title: deleteTeacherInviteRewardRecordByIds 
	* @Description: 根据id批量删除TeacherInviteRewardRecord
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	void deleteTeacherInviteRewardRecordByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTeacherInviteRewardRecordById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	TeacherInviteRewardRecord findTeacherInviteRewardRecordById(Integer id);
	
	/**
	 * 
	* @Title: findTeacherInviteRewardRecordByPage 
	* @Description: 分页查询
	* @return
	* @return List<TeacherInviteRewardRecord>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by wangzx
	 */
	List<TeacherInviteRewardRecord> findTeacherInviteRewardRecordByPage(TeacherInviteRewardRecord search);

	int getTotalInviteNumber(Integer companyId);
	
	int getTotalConsumerTimes(Integer companyId);
	
	double getTotalConsumeBalance(Integer companyId);
	
	double getTotalRewardBalance(Integer companyId);
	
	List<TeacherInviteRecordListVo> queryRewardsRecordList(TeacherInviteRecordListVo search);
	
	int queryRewardsRecordListCount(TeacherInviteRecordListVo search);
	
	List<TeacherInviteRecordListVo> exportRewardsRecordList(TeacherInviteRecordListVo search);
	
	List<TeacherInviteRecordDetailListVo> queryRewardsRecordDetailList(TeacherInviteRecordDetailListVo search);
	
	int queryRewardsRecordDetailListCount(TeacherInviteRecordDetailListVo search);
	
	int getTeacherInviteNumber(TeacherInviteRecordDetailListVo search);
	
	int getTeacherConsumerTimes(TeacherInviteRecordDetailListVo search);
	
	double getTeacherConsumeBalance(TeacherInviteRecordDetailListVo search);
	
	double getTeacherRewardBalance(TeacherInviteRecordDetailListVo search);
}