package com.yuxin.wx.api.certificate;

import java.util.List;

import com.yuxin.wx.model.certificate.CertificateConfig;
/**
 * Service Interface:CertificateConfig
 * @author chopin
 * @date 2016-9-22
 */
public interface ICertificateConfigService  {
	/**
	 * 
	* @Title: saveCertificateConfig
	* @Description: 新增CertificateConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void insert(CertificateConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCertificateConfig 
	* @Description: 批量新增CertificateConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void batchInsert(List<CertificateConfig> list);
	
	/**
	 * 
	* @Title: updateCertificateConfig 
	* @Description: 编辑CertificateConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void update(CertificateConfig entity);
	
	/**
	 * 
	* @Title: deleteCertificateConfigById 
	* @Description: 根据id删除CertificateConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void deleteCertificateConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCertificateConfigByIds 
	* @Description: 根据id批量删除CertificateConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void deleteCertificateConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCertificateConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	CertificateConfig findCertificateConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCertificateConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CertificateConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	List<CertificateConfig> findCertificateConfigByPage(CertificateConfig search);
	
	Integer pageCount(CertificateConfig search);
	
	Integer checkNameIsExist(CertificateConfig search);
	
	List<CertificateConfig> queryAllCertificate(CertificateConfig search);
}