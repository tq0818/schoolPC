package com.yuxin.wx.student.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.student.IStudentFeeStageService;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.student.mapper.StudentFeeStageMapper;
import com.yuxin.wx.vo.fee.StagingTotalVo;
import com.yuxin.wx.vo.fee.StagingVo;

/**
 * Service Implementation:StudentFeeStage
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class StudentFeeStageServiceImpl extends BaseServiceImpl implements IStudentFeeStageService {

	@Autowired
	private StudentFeeStageMapper studentFeeStageMapper;
	
	/**
	 * 
	* @Title: saveStudentFeeStage
	* @Description: 新增StudentFeeStage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(StudentFeeStage studentFeeStage){
		studentFeeStageMapper.insert(studentFeeStage);
	}
	
	/**
	 * 
	* @Title: batchSaveStudentFeeStage 
	* @Description: 批量新增StudentFeeStage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<StudentFeeStage> studentFeeStages){
		if(studentFeeStages != null && !studentFeeStages.isEmpty()){
			studentFeeStageMapper.batchInsert(studentFeeStages);
		}
	}
	
	/**
	 * 
	* @Title: updateStudentFeeStage 
	* @Description: 编辑StudentFeeStage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(StudentFeeStage studentFeeStage){
		studentFeeStageMapper.update(studentFeeStage);
	}
	
	/**
	 * 
	* @Title: deleteStudentFeeStageById 
	* @Description: 根据id删除StudentFeeStage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteStudentFeeStageById(Integer id){
		studentFeeStageMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteStudentFeeStageByIds 
	* @Description: 根据id批量删除StudentFeeStage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteStudentFeeStageByIds(Integer[] ids){
		studentFeeStageMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findStudentFeeStageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public StudentFeeStage findStudentFeeStageById(Integer id){
		return studentFeeStageMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findStudentFeeStageByPage 
	* @Description: 分页查询
	* @return
	* @return List<StudentFeeStage>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<StudentFeeStage> findStudentFeeStageByPage(StudentFeeStage search){
		Integer totalRecords = studentFeeStageMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return studentFeeStageMapper.page(search);
	}

	@Override
	public List<StudentFeeStage> findUnPayed(Integer payMasterId) {
		
		return studentFeeStageMapper.findBypayMasterId(payMasterId);
	}
	
	@Override
	public List<StudentFeeStage> findHasPayed(Integer payMasterId) {
		return studentFeeStageMapper.findHasPayed(payMasterId);
	}
	
	@Override
	public List<StudentFeeStage> findAll(Integer payMasterId) {
		return studentFeeStageMapper.findAll(payMasterId);
	}

	@Override
	public Double findSumPayed(Integer id) {
		
		return studentFeeStageMapper.findSumPayed(id);
	}

	@Override
	public void deleteByMasterId(Integer payMasterId) {
		studentFeeStageMapper.deleteByMasterId(payMasterId);
	}

	@Override
	public StagingTotalVo querylsFeeTotal(StagingVo search) {
		// TODO Auto-generated method stub
		StagingTotalVo stage=studentFeeStageMapper.querylsFeeTotal(search);
		search.setApplyChannelCode("CHANNEL_ONLINE");
	    Double onFee= studentFeeStageMapper.queryOnLineFeeTotal(search);
	    if(null!=stage && null!=stage.getTotalMoney()&& null!=onFee){
	    	stage.setTotalMoney(stage.getTotalMoney()+onFee);
	    }else{
	    	stage.setTotalMoney(onFee);
	    }
		return stage;
	}

	@Override
	public List<Map> queryOnoffFeeTotal(StagingVo search) {
		List<Map> arr=new ArrayList<Map>();
		Map<String, Double> map=new HashMap<String, Double>();
		search.setApplyChannelCode("CHANNEL_ONLINE");
	    Double onFee= studentFeeStageMapper.queryOnLineFeeTotal(search);
		search.setApplyChannelCode("CHANNEL_OFFLINE");
		Double offFee= studentFeeStageMapper.queryOnoffFeeTotal(search);
		map.put("onCountFee", onFee);
		map.put("offCountFee", offFee);
		arr.add(map);
		return arr;
	}
	
	
}
