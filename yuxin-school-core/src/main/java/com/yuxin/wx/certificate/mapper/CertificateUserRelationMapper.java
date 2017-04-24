package com.yuxin.wx.certificate.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.certificate.CertificateUserRelation;
import com.yuxin.wx.vo.certificate.CertificateUserRelationVo;
import com.yuxin.wx.vo.certificate.ViewCertificateVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CertificateUserRelationMapper extends BaseMapper<CertificateUserRelation> {
	
	CertificateUserRelationVo queryCertificateUserRelationByStuIdAndCerIdAndClassId(Map<String, Integer> map);

	Integer queryUserCertificateNum(Integer cerId);
	
	List<ViewCertificateVo> queryUserAndCertificateInfo(ViewCertificateVo search);
	
	Integer queryUserAndCertificateInfoCount(ViewCertificateVo search);
	
	Integer queryReleaseCount(ViewCertificateVo search);
	
	List<ViewCertificateVo> findListBySearchCondtion(ViewCertificateVo search);
}