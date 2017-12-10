package com.yuxin.wx.api.company;

import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyVo;

public interface ICompanyManageService {
	/**
	 * 通过map查询条件查询分校值
	 * @return 分校集合
	 */
	public PageFinder2<CompanyVo> queryCompanyVoListByCondition(CompanyVo search);
	
	/**
	 * 通过brachCode查询对应的分校
	 * @param brachCode
	 * @return
	 */
	public CompanyVo queryCompanyVoByCondition(String brachCode);
	
	/**
	 * 
	 * @author jishangyang 2017年12月7日 上午9:40:38
	 * @Method: addBerkeley 
	 * @Description: 添加分校
	 * @param search
	 * @param cms
	 * @param clc
	 * @return 
	 * @throws
	 */
	public void addBerkeley(CompanyVo search,CompanyMemberService cms,CompanyLiveConfig clc,Integer userId);
	/**
	 * 
	 * @author jishangyang 2017年12月8日 上午12:11:19
	 * @Method: eidtBerkeley 
	 * @Description: 修改分校
	 * @param search
	 * @param cms
	 * @param clc 
	 * @throws
	 */
	public void eidtBerkeley(CompanyVo search,CompanyMemberService cms,CompanyLiveConfig clc);
}
