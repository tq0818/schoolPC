package com.yuxin.wx.api.certificate;

import java.util.List;

import com.yuxin.wx.model.certificate.CertificateCourseRelation;
import com.yuxin.wx.vo.certificate.CertificateUserCourseVo;
import com.yuxin.wx.vo.certificate.ReleaseSearchVo;
/**
 * Service Interface:CertificateCourseRelation
 * @author chopin
 * @date 2016-9-22
 */
public interface ICertificateCourseRelationService  {
	/**
	 * 
	* @Title: saveCertificateCourseRelation
	* @Description: 新增CertificateCourseRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void insert(CertificateCourseRelation entity);
	
	/**
	 * 
	* @Title: batchSaveCertificateCourseRelation 
	* @Description: 批量新增CertificateCourseRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void batchInsert(List<CertificateCourseRelation> list);
	
	/**
	 * 
	* @Title: updateCertificateCourseRelation 
	* @Description: 编辑CertificateCourseRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void update(CertificateCourseRelation entity);
	
	/**
	 * 
	* @Title: deleteCertificateCourseRelationById 
	* @Description: 根据id删除CertificateCourseRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void deleteCertificateCourseRelationById(Integer id);
	
	/**
	 * 
	* @Title: deleteCertificateCourseRelationByIds 
	* @Description: 根据id批量删除CertificateCourseRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	void deleteCertificateCourseRelationByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCertificateCourseRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	CertificateCourseRelation findCertificateCourseRelationById(Integer id);
	
	/**
	 * 
	* @Title: findCertificateCourseRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<CertificateCourseRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by wangzx
	 */
	List<CertificateCourseRelation> findCertificateCourseRelationByPage(CertificateCourseRelation search);

	List<CertificateCourseRelation> queryRelationByCerId(Integer cerId);
	
	List<CertificateUserCourseVo> pageReleaseCertificateList(ReleaseSearchVo search);
	
	Integer pageReleaseCertificateListCount(ReleaseSearchVo search);
	
	void deletByCerId(Integer cerId);
}