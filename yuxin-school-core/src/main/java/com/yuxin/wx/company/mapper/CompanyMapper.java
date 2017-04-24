
package com.yuxin.wx.company.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.vo.company.CompanyOrgMessageReadVo;
import com.yuxin.wx.vo.company.CompanyOrgMessageVo;
import com.yuxin.wx.vo.company.CompanyPicsVo;
import com.yuxin.wx.vo.company.CompanyTotalVo;
import com.yuxin.wx.vo.company.CompanyVo;
import com.yuxin.wx.vo.company.companySpecialDomain;
import com.yuxin.wx.vo.query.RegisterInfoVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyMapper extends BaseMapper<Company> {
	Integer findCompanyLevel(Integer companyId);
	
	List<CompanyPicsVo> queryCompanyPic(CompanyPicsVo companyPicsVo);
	
	int queryCompanyPicCount(CompanyPicsVo companyPicsVo);
	
	List<CompanyVo> findCompanyVoByPage(Company search);
	Integer findTotalCount(Company search);
	
	List<CompanyPicsVo> queryCompanyPrivatePic(CompanyPicsVo companyPicsVo);
	int queryCompanyPrivatePicCount(CompanyPicsVo companyPicsVo);
	
	List<CompanyPicsVo> queryOneCondtion();
	
	Company queryCompanyByCopanyCondition(Company company);
	
	void insertCompany(Company company);

	String findStatus(Integer companyId);

	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 定时任务   获得所有公司的id
	 * @author 周文斌
	 * @date 2015-5-21 下午12:59:19
	 * @version 1.0
	 * @return
	 */
	List<Integer> findCompanyId(@Param("types") String types);

	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 定时任务 查询所有收费 公司 id 和等级
	 * @author 周文斌
	 * @date 2015-5-21 下午2:15:18
	 * @version 1.0
	 * @return
	 */
	List<Company> findCompanyIdAndLevel();
	
	void memberLevelUp(Company CEntity,CompanyMemberService CMSEntity);
	
	List<companySpecialDomain> querySpecialName(String domainName);
	
	List<CompanyOrgMessageVo> queryMessageList(CompanyOrgMessageVo search);
	
	int queryMessageCount(CompanyOrgMessageVo search);
	
	CompanyOrgMessageVo queryMessageById(Integer id);
	
	void insertMsg(CompanyOrgMessageVo c);	
	void updateMsg(CompanyOrgMessageVo c);
	void deleteMsg(Integer id);
	List<Company> queryAllCompany();
	List<CompanyOrgMessageReadVo> queryMessageCenterList(CompanyOrgMessageReadVo c);
	int queryMessageCenterCount(CompanyOrgMessageReadVo c);
	void insertCompanyReadDetail(CompanyOrgMessageReadVo c);
	void updateMsgReadFlag(CompanyOrgMessageReadVo c);
	CompanyOrgMessageReadVo querymessageCenterById(Map<String, Integer> map);
	Integer queryCompanyNotReadNum(CompanyOrgMessageReadVo c);
	List<CompanyTotalVo> querySourceList(CompanyTotalVo c);
	List<CompanyTotalVo>  queryRegistNumList(CompanyTotalVo c);
	int queryRegistNumListCount(CompanyTotalVo c);
	List<CompanyTotalVo> queryRegistNumChar(CompanyTotalVo c);
	
	List<Company> queryCompanyBuyFlag(Company search);
	
	List<Company> queryRegisterCompany(Map map);
	
	List<Company> checkCompanyDomain(String search);
	
	List<Company> checkCompanyName(String search);

	List<CompanyPicsVo> queryPrivatePic(CompanyPicsVo companyPicsVo);

	int queryPrivatePicCount(CompanyPicsVo companyPicsVo);

	List<CompanyPicsVo> queryPublicPic(CompanyPicsVo companyPicsVo);

	int queryPublicPicCount(CompanyPicsVo companyPicsVo);

	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询域名
	 * @author 周文斌
	 * @date 2016-7-26 下午6:32:27
	 * @version 1.0
	 * @param id
	 * @return
	 */
	String findDomainById(Integer id);
	
	List<RegisterInfoVo> queryRegisterInfoList(RegisterInfoVo riv);
	Integer queryRegisterInfoCount(RegisterInfoVo riv);
	
	List<RegisterInfoVo> queryRegisterInfoExport(RegisterInfoVo riv);

	List<CompanyOrgMessageVo> queryMessageServiceList(CompanyOrgMessageVo search);
	
	Integer queryServiceOpenFlag(Map<String, Object> map);
}