package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.company.mapper.CompanyMemberServiceMapper;
import com.yuxin.wx.company.mapper.CompanyServiceStaticMapper;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.vo.company.CompanyAlarmLogVo;
import com.yuxin.wx.vo.system.SystemConfigServiceVo;

/**
 * Service Implementation:CompanyServiceStatic
 * @author chopin
 * @date 2015-4-23
 */
@Service
@Transactional
public class CompanyServiceStaticServiceImpl extends BaseServiceImpl implements ICompanyServiceStaticService {

	@Autowired
	private CompanyServiceStaticMapper companyServiceStaticMapper;
	
	@Autowired
	private CompanyMemberServiceMapper companyMemberServiceMapper;
	
	/**
	 * 
	* @Title: saveCompanyServiceStatic
	* @Description: 新增CompanyServiceStatic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void insert(CompanyServiceStatic entity){
		companyServiceStaticMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyServiceStatic 
	* @Description: 批量新增CompanyServiceStatic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyServiceStatic> entity){
		companyServiceStaticMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyServiceStatic 
	* @Description: 编辑CompanyServiceStatic
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void update(CompanyServiceStatic entity){
		companyServiceStaticMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyServiceStaticById 
	* @Description: 根据id删除CompanyServiceStatic
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyServiceStaticById(Integer id){
		companyServiceStaticMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyServiceStaticByIds 
	* @Description: 根据id批量删除CompanyServiceStatic
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void deleteCompanyServiceStaticByIds(Integer[] ids){
		companyServiceStaticMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyServiceStaticById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public CompanyServiceStatic findCompanyServiceStaticById(Integer id){
		return companyServiceStaticMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyServiceStaticByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyServiceStatic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public List<CompanyServiceStatic> findCompanyServiceStaticByPage(CompanyServiceStatic search){
		return companyServiceStaticMapper.page(search);
	}

	@Override
	public CompanyServiceStatic findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return companyServiceStaticMapper.findByCompanyId(companyId);
	}

	@Override
	public List<CompanyServiceStatic> queryCompanyServicesUsed() {
		// TODO Auto-generated method stub
		return companyServiceStaticMapper.queryCompanyServicesUsed();
	}

	@Override
	public void insertCompanyAlarmLog(CompanyAlarmLogVo alarm) {
		// TODO Auto-generated method stub
		companyServiceStaticMapper.insertCompanyAlarmLog(alarm);
	}

	@Override
	public List<CompanyAlarmLogVo> queryCompanyServiceLogExit(
			CompanyAlarmLogVo alarm) {
		// TODO Auto-generated method stub
		return companyServiceStaticMapper.queryCompanyServiceLogExit(alarm);
	}

	@Override
	public List<SystemConfigServiceVo> queryCompanyNoService(Integer companyId) {
		// TODO Auto-generated method stub
		return companyServiceStaticMapper.queryCompanyNoService(companyId);
	};
	@Override
	public List<SystemConfigServiceVo> queryCompanyAllService(Integer companyId) {
		// TODO Auto-generated method stub
		return companyServiceStaticMapper.queryCompanyAllService(companyId);
	};
	@Override
	public void updateByCompanyStatus(Integer companyId){
		companyServiceStaticMapper.updateByCompanyStatus(companyId);
	};
	@Override
	public void updateByCompanyStatusl(Integer companyId){
		companyServiceStaticMapper.updateByCompanyStatusl(companyId);
	};
}
