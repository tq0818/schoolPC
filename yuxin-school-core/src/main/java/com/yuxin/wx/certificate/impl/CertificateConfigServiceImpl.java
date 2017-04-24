package com.yuxin.wx.certificate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.certificate.ICertificateConfigService;
import com.yuxin.wx.certificate.mapper.CertificateConfigMapper;
import com.yuxin.wx.model.certificate.CertificateConfig;


/**
 * Service Implementation:CertificateConfig
 * @author chopin
 * @date 2016-9-22
 */
@Service
@Transactional
public class CertificateConfigServiceImpl extends BaseServiceImpl implements ICertificateConfigService{

	@Autowired
	private CertificateConfigMapper certificateConfigMapper;
	
	/**
	 * 
	* @Title: saveCertificateConfig
	* @Description: 新增CertificateConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public void insert(CertificateConfig entity){
		certificateConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCertificateConfig 
	* @Description: 批量新增CertificateConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CertificateConfig> entity){
		certificateConfigMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCertificateConfig 
	* @Description: 编辑CertificateConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public void update(CertificateConfig entity){
		certificateConfigMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCertificateConfigById 
	* @Description: 根据id删除CertificateConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	 @Override
	public void deleteCertificateConfigById(Integer id){
		certificateConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCertificateConfigByIds 
	* @Description: 根据id批量删除CertificateConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public void deleteCertificateConfigByIds(Integer[] ids){
		certificateConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCertificateConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public CertificateConfig findCertificateConfigById(Integer id){
		return certificateConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCertificateConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CertificateConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public List<CertificateConfig> findCertificateConfigByPage(CertificateConfig search){
		return certificateConfigMapper.page(search);
	}

	@Override
	public Integer pageCount(CertificateConfig search) {
		return certificateConfigMapper.pageCount(search);
	}

	@Override
	public Integer checkNameIsExist(CertificateConfig search) {
		return certificateConfigMapper.checkNameIsExist(search);
	}

	@Override
	public List<CertificateConfig> queryAllCertificate(CertificateConfig search) {
		return certificateConfigMapper.queryAllCertificate(search);
	};
}
