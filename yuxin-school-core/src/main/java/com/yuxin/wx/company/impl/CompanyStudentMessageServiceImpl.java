package com.yuxin.wx.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyStudentMessageService;
import com.yuxin.wx.company.mapper.CompanyStudentMessageMapper;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.user.UserMessage;
import com.yuxin.wx.vo.student.StuMessageVo;

/**
 * Service Implementation:CompanyStudentMessage
 * @author wang.zx
 * @date 2015-6-4
 */
@Service
@Transactional
public class CompanyStudentMessageServiceImpl extends BaseServiceImpl implements ICompanyStudentMessageService {

	@Autowired
	private CompanyStudentMessageMapper companyStudentMessageMapper;
	
	/**
	 * 
	* @Title: saveCompanyStudentMessage
	* @Description: 新增CompanyStudentMessage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by chopin
	 */
	@Override
	public void insert(CompanyStudentMessage entity){
		companyStudentMessageMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyStudentMessage 
	* @Description: 批量新增CompanyStudentMessage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyStudentMessage> entity){
		companyStudentMessageMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyStudentMessage 
	* @Description: 编辑CompanyStudentMessage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by chopin
	 */
	@Override
	public void update(CompanyStudentMessage entity){
		companyStudentMessageMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyStudentMessageById 
	* @Description: 根据id删除CompanyStudentMessage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyStudentMessageById(Integer id){
		companyStudentMessageMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyStudentMessageByIds 
	* @Description: 根据id批量删除CompanyStudentMessage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by chopin
	 */
	@Override
	public void deleteCompanyStudentMessageByIds(Integer[] ids){
		companyStudentMessageMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyStudentMessageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by chopin
	 */
	@Override
	public CompanyStudentMessage findCompanyStudentMessageById(Integer id){
		return companyStudentMessageMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyStudentMessageByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyStudentMessage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by chopin
	 */
	@Override
	public List<CompanyStudentMessage> findCompanyStudentMessageByPage(CompanyStudentMessage search){
		return companyStudentMessageMapper.page(search);
	}

	@Override
	public String findCreatorName(Integer id) {
		// TODO Auto-generated method stub
		return companyStudentMessageMapper.findCreatorName(id);
	}

	@Override
	public List<CompanyStudentMessage> selMsgByCond(
			CompanyStudentMessage companyStudentMessage) {
		// TODO Auto-generated method stub
		return companyStudentMessageMapper.selMsgByCond(companyStudentMessage);
	}

	@Override
	public Integer selMsgCount(CompanyStudentMessage companyStudentMessage) {
		// TODO Auto-generated method stub
		return companyStudentMessageMapper.selMsgCount(companyStudentMessage);
	}

	@Override
	public List<StuMessageVo> findStudentMsgByClassId(StuMessageVo stuMessageVo) {
		// TODO Auto-generated method stub
		return companyStudentMessageMapper.findStudentMsgByClassId(stuMessageVo);
	}

	@Override
	public List<StuMessageVo> findStudentMsgByModuleId(StuMessageVo stuMessageVo) {
		// TODO Auto-generated method stub
		return companyStudentMessageMapper.findStudentMsgByModuleId(stuMessageVo);
	}

	@Override
	public Integer findStudentMsgCountByClassId(StuMessageVo stuMessageVo) {
		// TODO Auto-generated method stub
		return companyStudentMessageMapper.findStudentMsgCountByClassId(stuMessageVo);
	}

	@Override
	public Integer findStudentMsgCountByModuleId(StuMessageVo stuMessageVo) {
		// TODO Auto-generated method stub
		return companyStudentMessageMapper.findStudentMsgCountByModuleId(stuMessageVo);
	}

	@Override
	public void insertUserMessage(UserMessage um) {
		// TODO Auto-generated method stub
		companyStudentMessageMapper.insertUserMessage(um);
	}

	@Override
	public List<StuMessageVo> findNoticeList(StuMessageVo stuMessageVo) {
		// TODO Auto-generated method stub
		return companyStudentMessageMapper.findNoticeList(stuMessageVo);
	}

	@Override
	public Integer findNoticeListCount(StuMessageVo stuMessageVo) {
		// TODO Auto-generated method stub
		return companyStudentMessageMapper.findNoticeListCount(stuMessageVo);
	}
	
	@Override
	public Integer findSameCount(UserMessage message) {
		// TODO Auto-generated method stub
		return companyStudentMessageMapper.findSameCount(message);
	}

	@Override
	public List<StuMessageVo> findStudentMsgByGroup(StuMessageVo stuMessageVo) {
		return companyStudentMessageMapper.findStudentMsgByGroup(stuMessageVo);
	}

	@Override
	public Integer findStudentMsgByGroupCount(StuMessageVo stuMessageVo) {
		return companyStudentMessageMapper.findStudentMsgByGroupCount(stuMessageVo);
	}
}
