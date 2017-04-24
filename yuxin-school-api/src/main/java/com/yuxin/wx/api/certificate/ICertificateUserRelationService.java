package com.yuxin.wx.api.certificate;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.certificate.CertificateUserRelation;
import com.yuxin.wx.vo.certificate.CertificateUserRelationVo;
import com.yuxin.wx.vo.certificate.ViewCertificateVo;
/**
 * Service Interface:CertificateUserRelation
 * @author chopin
 * @date 2016-9-22
 */
public interface ICertificateUserRelationService  {
	/**
	 * 
	* @Title: saveCertificateUserRelation
	* @Description: 新增CertificateUserRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void insert(CertificateUserRelation entity);
	
	/**
	 * 
	* @Title: batchSaveCertificateUserRelation 
	* @Description: 批量新增CertificateUserRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void batchInsert(List<CertificateUserRelation> list);
	
	/**
	 * 
	* @Title: updateCertificateUserRelation 
	* @Description: 编辑CertificateUserRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void update(CertificateUserRelation entity);
	
	/**
	 * 
	* @Title: deleteCertificateUserRelationById 
	* @Description: 根据id删除CertificateUserRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void deleteCertificateUserRelationById(Integer id);
	
	/**
	 * 
	* @Title: deleteCertificateUserRelationByIds 
	* @Description: 根据id批量删除CertificateUserRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void deleteCertificateUserRelationByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCertificateUserRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	CertificateUserRelation findCertificateUserRelationById(Integer id);
	
	/**
	 * 
	* @Title: findCertificateUserRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<CertificateUserRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	List<CertificateUserRelation> findCertificateUserRelationByPage(CertificateUserRelation search);
	
	/**
	 * 
	 * Class Name: ICertificateUserRelationService.java
	 * @Description: 根据StuId,CerId,ClassTypeId查证书
	 * @author dongshuai
	 * @date 2016年9月23日 下午7:35:24
	 * @modifier
	 * @modify-date 2016年9月23日 下午7:35:24
	 * @version 1.0
	 * @param map
	 * @return
	 */
	CertificateUserRelationVo queryCertificateUserRelationByStuIdAndCerIdAndClassId(Map<String, Integer> map);

	
	/**
	 * 
	 * Class Name: ICertificateUserRelationService.java
	 * @Description: 根据cerId查询获得该证书的学员总数
	 * @author male
	 * @date 2016年9月28日 下午7:35:24
	 * @modifier
	 * @modify-date 2016年9月28日 下午7:35:24
	 * @version 1.0
	 * @param Integer
	 * @return Integer
	 */
	Integer queryUserCountByCerId(Integer cerId);
	
	List<ViewCertificateVo> queryUserAndCertificateInfo(ViewCertificateVo search);
	
	Integer queryUserAndCertificateInfoCount(ViewCertificateVo search);
	
	Integer queryReleaseCount(ViewCertificateVo search);
	
	List<ViewCertificateVo> findListBySearchCondtion(ViewCertificateVo search);
	
}