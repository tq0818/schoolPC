package com.yuxin.wx.certificate.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.certificate.CertificateCourseRelation;
import com.yuxin.wx.vo.certificate.CertificateUserCourseVo;
import com.yuxin.wx.vo.certificate.ReleaseSearchVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CertificateCourseRelationMapper extends BaseMapper<CertificateCourseRelation> {
	
	List<CertificateCourseRelation> queryRelationByCerId(Integer cerId);
	
	List<CertificateUserCourseVo> pageReleaseCertificateList(ReleaseSearchVo search);
	
	Integer pageReleaseCertificateListCount(ReleaseSearchVo search);
	
	public void deleteByCerId(Integer cerId);
}