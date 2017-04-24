package com.yuxin.wx.certificate.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.certificate.ICertificateUserRelationService;
import com.yuxin.wx.certificate.mapper.CertificateUserRelationMapper;
import com.yuxin.wx.model.certificate.CertificateUserRelation;
import com.yuxin.wx.vo.certificate.CertificateUserRelationVo;
import com.yuxin.wx.vo.certificate.ViewCertificateVo;


/**
 * Service Implementation:CertificateUserRelation
 * @author chopin
 * @date 2016-9-22
 */
@Service
@Transactional
public class CertificateUserRelationServiceImpl extends BaseServiceImpl implements ICertificateUserRelationService {

	@Autowired
	private CertificateUserRelationMapper certificateUserRelationMapper;
	
	/**
	 * 
	* @Title: saveCertificateUserRelation
	* @Description: 新增CertificateUserRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public void insert(CertificateUserRelation entity){
		certificateUserRelationMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCertificateUserRelation 
	* @Description: 批量新增CertificateUserRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CertificateUserRelation> entity){
		certificateUserRelationMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCertificateUserRelation 
	* @Description: 编辑CertificateUserRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public void update(CertificateUserRelation entity){
		certificateUserRelationMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCertificateUserRelationById 
	* @Description: 根据id删除CertificateUserRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	 @Override
	public void deleteCertificateUserRelationById(Integer id){
		certificateUserRelationMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCertificateUserRelationByIds 
	* @Description: 根据id批量删除CertificateUserRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public void deleteCertificateUserRelationByIds(Integer[] ids){
		certificateUserRelationMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCertificateUserRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public CertificateUserRelation findCertificateUserRelationById(Integer id){
		return certificateUserRelationMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCertificateUserRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<CertificateUserRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-9-22
	* @user by chopin
	 */
	@Override
	public List<CertificateUserRelation> findCertificateUserRelationByPage(CertificateUserRelation search){
		return certificateUserRelationMapper.page(search);
	}

	@Override
	public CertificateUserRelationVo queryCertificateUserRelationByStuIdAndCerIdAndClassId(Map<String, Integer> map) {
		return certificateUserRelationMapper.queryCertificateUserRelationByStuIdAndCerIdAndClassId(map);
	}

	@Override
	public Integer queryUserCountByCerId(Integer cerId) {
		return certificateUserRelationMapper.queryUserCertificateNum(cerId);
	}

	@Override
	public List<ViewCertificateVo> queryUserAndCertificateInfo(ViewCertificateVo search) {
		return certificateUserRelationMapper.queryUserAndCertificateInfo(search);
	}

	@Override
	public Integer queryUserAndCertificateInfoCount(ViewCertificateVo search) {
		return certificateUserRelationMapper.queryUserAndCertificateInfoCount(search);
	}

	@Override
	public Integer queryReleaseCount(ViewCertificateVo search) {
		return certificateUserRelationMapper.queryReleaseCount(search);
	}

	@Override
	public List<ViewCertificateVo> findListBySearchCondtion(ViewCertificateVo search) {
		return certificateUserRelationMapper.findListBySearchCondtion(search);
	}
}
