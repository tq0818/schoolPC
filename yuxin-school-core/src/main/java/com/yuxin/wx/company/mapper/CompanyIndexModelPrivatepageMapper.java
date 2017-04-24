package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyIndexModelPrivatepage;
import com.yuxin.wx.vo.company.CompanyIndexModelPrivatepageVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyIndexModelPrivatepageMapper extends BaseMapper<CompanyIndexModelPrivatepage> {
	void copyToCompany(CompanyIndexModelPrivatepage page);
	List<CompanyIndexModelPrivatepageVo> findList(CompanyIndexModelPrivatepage search);
	List<CompanyIndexModelPrivatepage> findByTemplate(CompanyIndexModelPrivatepage search);
}