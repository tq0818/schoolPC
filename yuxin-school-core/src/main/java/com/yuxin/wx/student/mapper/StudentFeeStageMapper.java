package com.yuxin.wx.student.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.vo.fee.RemoteFeeVo;
import com.yuxin.wx.vo.fee.StagingTotalVo;
import com.yuxin.wx.vo.fee.StagingVo;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:StudentFeeStage
 * @author wang.zx
 * @date 2014-12-5
 */
public interface StudentFeeStageMapper extends BaseMapper<StudentFeeStage> {
    List<StagingVo> queryStageList(StagingVo search);
    Integer pageCount2(StagingVo search);
    List<StagingVo> queryStageGroup(Integer companyId);
    StagingVo findStageByGroup(StagingVo search);
    Integer pageCount3(StagingVo search);
    StudentFeeStage findNextById(int id);
	List<StudentFeeStage> findBypayMasterId(Integer payMasterId);
	Double findSumPayed(Integer id);
	void update2(StudentPayMaster payMaster);
	void deleteByMasterId(Integer payMasterId);
	List<StagingVo> queryStageList2(StagingVo search);
	int pageCount4(StagingVo search);
	
	List<StudentFeeStage> findHasPayed(Integer payMasterId);
	List<StudentFeeStage> findAll(Integer payMasterId);
	StagingTotalVo querylsFeeTotal(StagingVo search);
	
	List<RemoteFeeVo> queryRemoteStageList(RemoteFeeVo search);
	int remoteCount(RemoteFeeVo search);
	RemoteFeeVo queryRemoteById(RemoteFeeVo search);
	void insertFee(RemoteFeeVo search);
	void insertRemoteFee(RemoteFeeVo search);
	void updateRemoteFee(RemoteFeeVo search);
	List<RemoteFeeVo> queryFee(RemoteFeeVo search);
	double queryOnoffFeeTotal(StagingVo search);
	double queryOnLineFeeTotal(StagingVo search);
}