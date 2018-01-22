package com.yuxin.wx.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.company.mapper.CompanyMapper;
import com.yuxin.wx.api.system.ISysConfigServiceService;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.system.mapper.SysConfigServiceMapper;

/**
 * Service Implementation:SysConfigService
 * @author chopin
 * @date 2015-8-12
 */
@Service
@Transactional
public class SysConfigServiceServiceImpl extends BaseServiceImpl implements ISysConfigServiceService {

	@Autowired
	private SysConfigServiceMapper sysConfigServiceMapper;
	@Autowired
	private CompanyMapper companyMapper;
	/**
	 * 
	* @Title: saveSysConfigService
	* @Description: 新增SysConfigService
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigService entity){
		sysConfigServiceMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigService 
	* @Description: 批量新增SysConfigService
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigService> entity){
		sysConfigServiceMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysConfigService 
	* @Description: 编辑SysConfigService
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public void update(SysConfigService entity){
		sysConfigServiceMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigServiceById 
	* @Description: 根据id删除SysConfigService
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigServiceById(Integer id){
		sysConfigServiceMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigServiceByIds 
	* @Description: 根据id批量删除SysConfigService
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigServiceByIds(Integer[] ids){
		sysConfigServiceMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigServiceById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public SysConfigService findSysConfigServiceById(Integer id){
		return sysConfigServiceMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigServiceByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigService>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public List<SysConfigService> findSysConfigServiceByPage(SysConfigService search){
		return sysConfigServiceMapper.page(search);
	};
	/**
	 * 
	* @Title: findSysConfigServiceByPage 
	* @Description: 根据条件查询列表，不分页
	* @return
	* @return List<SysConfigService>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-8-12
	* @user by chopin
	 */
	@Override
	public List<SysConfigService> findSysConfigServiceByCompany(SysConfigService search){
		return sysConfigServiceMapper.findList(search);

	}

	@Override
	public SysConfigService findByCodeId(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sysConfigServiceMapper.findByCodeId(param);
	}

	@Override
	public List<SysConfigService> findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return sysConfigServiceMapper.findByCompanyId(companyId);
	}

	@Override
	public SysConfigService findExist(SysConfigService serv) {
		return sysConfigServiceMapper.findExist(serv);
	}

	@Override
	public Boolean updateDelFlagByCompanyId(SysConfigService serv) {
		int zhuCompanyId= companyMapper.searchCompany();
		serv.setZhuCompanyId(zhuCompanyId);
		
		if(sysConfigServiceMapper.finConfigServiceSet(serv)>0){
		/*	if(sysConfigServiceMapper.deletConfigService(serv)>0){*/
				sysConfigServiceMapper.updateFlag(serv);
			/*}*/
    	}else{
    		if(sysConfigServiceMapper.addConfigService(serv)>0){
    			sysConfigServiceMapper.updateFlag(serv);
    		}
    		
    	}
		return true;
	}

	
	
}
