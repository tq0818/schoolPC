package com.yuxin.wx.certificate.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.certificate.CertificateConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CertificateConfigMapper extends BaseMapper<CertificateConfig> {
	
	Integer checkNameIsExist(CertificateConfig search);
	
	List<CertificateConfig> queryAllCertificate(CertificateConfig search);
}