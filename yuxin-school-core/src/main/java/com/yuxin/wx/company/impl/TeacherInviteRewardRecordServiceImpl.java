package com.yuxin.wx.company.impl;


import java.text.SimpleDateFormat;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ITeacherInviteRewardRecordService;
import com.yuxin.wx.company.mapper.TeacherInviteRewardRecordMapper;
import com.yuxin.wx.model.teacher.TeacherInviteRewardRecord;
import com.yuxin.wx.vo.company.TeacherInviteRecordDetailListVo;
import com.yuxin.wx.vo.company.TeacherInviteRecordListVo;


/**
 * Service Implementation:TeacherInviteRewardRecord
 * @author chopin
 * @date 2017-3-13
 */
@Service
@Transactional
public class TeacherInviteRewardRecordServiceImpl extends BaseServiceImpl implements ITeacherInviteRewardRecordService {

	@Autowired
	private TeacherInviteRewardRecordMapper teacherInviteRewardRecordMapper;
	
	/**
	 * 
	* @Title: saveTeacherInviteRewardRecord
	* @Description: 新增TeacherInviteRewardRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public void insert(TeacherInviteRewardRecord entity){
		teacherInviteRewardRecordMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTeacherInviteRewardRecord 
	* @Description: 批量新增TeacherInviteRewardRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TeacherInviteRewardRecord> entity){
		teacherInviteRewardRecordMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTeacherInviteRewardRecord 
	* @Description: 编辑TeacherInviteRewardRecord
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public void update(TeacherInviteRewardRecord entity){
		teacherInviteRewardRecordMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTeacherInviteRewardRecordById 
	* @Description: 根据id删除TeacherInviteRewardRecord
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	 @Override
	public void deleteTeacherInviteRewardRecordById(Integer id){
		teacherInviteRewardRecordMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTeacherInviteRewardRecordByIds 
	* @Description: 根据id批量删除TeacherInviteRewardRecord
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public void deleteTeacherInviteRewardRecordByIds(Integer[] ids){
		teacherInviteRewardRecordMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTeacherInviteRewardRecordById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public TeacherInviteRewardRecord findTeacherInviteRewardRecordById(Integer id){
		return teacherInviteRewardRecordMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTeacherInviteRewardRecordByPage 
	* @Description: 分页查询
	* @return
	* @return List<TeacherInviteRewardRecord>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-3-13
	* @user by chopin
	 */
	@Override
	public List<TeacherInviteRewardRecord> findTeacherInviteRewardRecordByPage(TeacherInviteRewardRecord search){
		return teacherInviteRewardRecordMapper.page(search);
	}

	@Override
	public int getTotalInviteNumber(Integer companyId) {
		return teacherInviteRewardRecordMapper.getTotalInviteNumber(companyId);
	}

	@Override
	public int getTotalConsumerTimes(Integer companyId) {
		return teacherInviteRewardRecordMapper.getTotalConsumerTimes(companyId);
	}

	@Override
	public double getTotalConsumeBalance(Integer companyId) {
		return teacherInviteRewardRecordMapper.getTotalConsumeBalance(companyId);
	}

	@Override
	public double getTotalRewardBalance(Integer companyId) {
		return teacherInviteRewardRecordMapper.getTotalRewardBalance(companyId);
	}

	@Override
	public List<TeacherInviteRecordListVo> queryRewardsRecordList(TeacherInviteRecordListVo search) {
		return teacherInviteRewardRecordMapper.queryRewardsRecordList(search);
	}

	@Override
	public int queryRewardsRecordListCount(TeacherInviteRecordListVo search) {
		return teacherInviteRewardRecordMapper.queryRewardsRecordListCount(search);
	}

	@Override
	public List<TeacherInviteRecordListVo> exportRewardsRecordList(TeacherInviteRecordListVo search) {
		return teacherInviteRewardRecordMapper.exportRewardsRecordList(search);
	}

	@Override
	public List<TeacherInviteRecordDetailListVo> queryRewardsRecordDetailList(TeacherInviteRecordDetailListVo search) {
		return teacherInviteRewardRecordMapper.queryRewardsRecordDetailList(search);
	}

	
	@Override
	public int queryRewardsRecordDetailListCount(TeacherInviteRecordDetailListVo search) {
		return teacherInviteRewardRecordMapper.queryRewardsRecordDetailListCount(search);
	}

	@Override
	public int getTeacherInviteNumber(TeacherInviteRecordDetailListVo search) {
		return teacherInviteRewardRecordMapper.getTeacherInviteNumber(search);
	}

	@Override
	public int getTeacherConsumerTimes(TeacherInviteRecordDetailListVo search) {
		return teacherInviteRewardRecordMapper.getTeacherConsumerTimes(search);
	}

	@Override
	public double getTeacherConsumeBalance(TeacherInviteRecordDetailListVo search) {
		return teacherInviteRewardRecordMapper.getTeacherConsumeBalance(search);
	}

	@Override
	public double getTeacherRewardBalance(TeacherInviteRecordDetailListVo search) {
		return teacherInviteRewardRecordMapper.getTeacherRewardBalance(search);
	};
}
