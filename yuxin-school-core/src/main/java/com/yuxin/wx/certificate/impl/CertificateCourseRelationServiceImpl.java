package com.yuxin.wx.certificate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.certificate.ICertificateCourseRelationService;
import com.yuxin.wx.certificate.mapper.CertificateCourseRelationMapper;
import com.yuxin.wx.model.certificate.CertificateCourseRelation;
import com.yuxin.wx.vo.certificate.CertificateUserCourseVo;
import com.yuxin.wx.vo.certificate.ReleaseSearchVo;


/**
 * Service Implementation:CertificateCourseRelation
 * @author chopin
 * @date 2016-9-22
 */
@Service
@Transactional
public class CertificateCourseRelationServiceImpl extends BaseServiceImpl implements ICertificateCourseRelationService {

	@Autowired
	private CertificateCourseRelationMapper certificateCourseRelationMapper;
	
	/**
	 * 
	* @Title: saveCertificateCourseRelation
	* @Description: 新增CertificateCourseRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public void insert(CertificateCourseRelation entity){
		certificateCourseRelationMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCertificateCourseRelation 
	* @Description: 批量新增CertificateCourseRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CertificateCourseRelation> entity){
		certificateCourseRelationMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCertificateCourseRelation 
	* @Description: 编辑CertificateCourseRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public void update(CertificateCourseRelation entity){
		certificateCourseRelationMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCertificateCourseRelationById 
	* @Description: 根据id删除CertificateCourseRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	 @Override
	public void deleteCertificateCourseRelationById(Integer id){
		certificateCourseRelationMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCertificateCourseRelationByIds 
	* @Description: 根据id批量删除CertificateCourseRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public void deleteCertificateCourseRelationByIds(Integer[] ids){
		certificateCourseRelationMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCertificateCourseRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public CertificateCourseRelation findCertificateCourseRelationById(Integer id){
		return certificateCourseRelationMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCertificateCourseRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<CertificateCourseRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public List<CertificateCourseRelation> findCertificateCourseRelationByPage(CertificateCourseRelation search){
		return certificateCourseRelationMapper.page(search);
	}

	@Override
	public List<CertificateCourseRelation> queryRelationByCerId(Integer cerId) {
		return certificateCourseRelationMapper.queryRelationByCerId(cerId);
	}

	@Override
	public List<CertificateUserCourseVo> pageReleaseCertificateList(ReleaseSearchVo search) {
		return certificateCourseRelationMapper.pageReleaseCertificateList(search);
	}

	@Override
	public Integer pageReleaseCertificateListCount(ReleaseSearchVo search) {
		return certificateCourseRelationMapper.pageReleaseCertificateListCount(search);
	}

	@Override
	public void deletByCerId(Integer cerId) {
		certificateCourseRelationMapper.deleteByCerId(cerId);
	}
}
