package com.yuxin.wx.company.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyMemberConfig;
import com.yuxin.wx.vo.company.MemberLevelVo;
import com.yuxin.wx.vo.company.VipDateVo;

/**
 * Service Interface:Users
 * 
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyMemberConfigMapper extends BaseMapper<CompanyMemberConfig> {

	CompanyMemberConfig queryMemberSets(CompanyMemberConfig search);

	/**
	 * Class Name: CompanyMemberConfigMapper.java
	 * 
	 * @Description: 根据公司编号查询数据库中是否有该公司的会员设置
	 * @author xukaiqiang
	 * @date 2016年5月23日 下午7:09:49
	 * @modifier
	 * @modify-date 2016年5月23日 下午7:09:49
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	CompanyMemberConfig findCompanyMemberConfigByCompanyId(Integer companyId);
	
	/**
	 * 
	 * @Description: 查询会员等级以及该会员等级所包含的课程
	 * @author: dongshuai
	 * @date: 2016年5月26日
	 * @param param
	 * @return
	 *
	 */
	List<MemberLevelVo> findMemberLevelByCompanyId(Map<String, Object> param);

	/**
	 * 
	 * Class Name: ICompanyMemberConfigService.java
	 * @Description: 查询开通会员功能的公司配置
	 * @author 周文斌
	 * @date 2016-6-13 下午5:22:07
	 * @version 1.0
	 * @return
	 */
	List<CompanyMemberConfig> findCompanyMemberConfigta();

	List<VipDateVo> findVipDateByCompanyId(Map<String, Object> param);

	CompanyMemberConfig findByCompanyId(Integer companyId);
}