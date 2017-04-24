package com.yuxin.wx.company.impl;

import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyMapper;
import com.yuxin.wx.company.mapper.CompanyMemberServiceMapper;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.student.mapper.StudentPayMasterMapper;
import com.yuxin.wx.system.mapper.SysConfigDictMapper;
import com.yuxin.wx.vo.company.*;
import com.yuxin.wx.vo.query.RegisterInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service Implementation:Company
 * 
 * @author chopin
 * @date 2015-4-23
 */
@Service
@Transactional
public class CompanyServiceImpl extends BaseServiceImpl implements ICompanyService {

	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private SysConfigDictMapper sysConfigDictMapper;
	@Autowired
	private CompanyMemberServiceMapper companyMemberServiceMapper;
	@Autowired
	private StudentPayMasterMapper studentPayMasterMapper;

	/**
	 * 
	 * @Title: saveCompany
	 * @Description: 新增Company
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2015-4-23
	 * @user by chopin
	 */
	@Override
	public void insert(Company entity) {
		companyMapper.insert(entity);
	};

	/**
	 * 
	 * @Title: batchSaveCompany
	 * @Description: 批量新增Company
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2015-4-23
	 * @user by chopin
	 */
	@Override
	public void batchInsert(List<Company> entity) {
		companyMapper.batchInsert(entity);
	};

	/**
	 * 
	 * @Title: updateCompany
	 * @Description: 编辑Company
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2015-4-23
	 * @user by chopin
	 */
	@Override
	public void update(Company entity) {
		companyMapper.update(entity);
	};

	/**
	 * 
	 * @Title: deleteCompanyById
	 * @Description: 根据id删除Company
	 * @param id
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2015-4-23
	 * @user by chopin
	 */
	@Override
	public void deleteCompanyById(Integer id) {
		companyMapper.deleteById(id);
	};

	/**
	 * 
	 * @Title: deleteCompanyByIds
	 * @Description: 根据id批量删除Company
	 * @param ids
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2015-4-23
	 * @user by chopin
	 */
	@Override
	public void deleteCompanyByIds(Integer[] ids) {
		companyMapper.deleteByIds(ids);
	};

	/**
	 * 
	 * @Title: findCompanyById
	 * @Description: 根据id查询
	 * @param id
	 * @return void 返回类型
	 * @throws
	 * @exception
	 * @date 2015-4-23
	 * @user by chopin
	 */
	@Override
	public Company findCompanyById(Integer id) {
		return companyMapper.findById(id);
	};

	/**
	 * 
	 * @Title: findCompanyByPage
	 * @Description: 分页查询
	 * @return
	 * @return List<Company> 返回类型
	 * @throws
	 * @exception
	 * @date 2015-4-23
	 * @user by chopin
	 */
	@Override
	public List<Company> findCompanyByPage(Company search) {
		return companyMapper.page(search);
	}

	@Override
	public Integer findCompanyLevel(Integer companyId) {
		// TODO Auto-generated method stub
		return companyMapper.findCompanyLevel(companyId);
	}

	@Override
	public PageFinder<CompanyPicsVo> findCompanyPic(CompanyPicsVo companyPicsVo) {
		List<CompanyPicsVo> data = companyMapper.queryCompanyPic(companyPicsVo);
		int count = companyMapper.queryCompanyPicCount(companyPicsVo);
		PageFinder<CompanyPicsVo> pageFinder = new PageFinder<CompanyPicsVo>(
				companyPicsVo.getPage(), companyPicsVo.getPageSize(), count,
				data);
		return pageFinder;
	}
	/**
	 * Class Name: ICompanyService.java
	 * @Description:查询私有云图片
	 * @author xukaiqiang
	 * @date 2016年6月8日 上午11:22:35
	 * @modifier
	 * @modify-date 2016年6月8日 上午11:22:35
	 * @version 1.0
	 * @param picVo
	 * @return
	 */
	public PageFinder<CompanyPicsVo> queryPrivatePic(CompanyPicsVo companyPicsVo){
		List<CompanyPicsVo> data = companyMapper.queryPrivatePic(companyPicsVo);
		int count = companyMapper.queryPrivatePicCount(companyPicsVo);
		PageFinder<CompanyPicsVo> pageFinder = new PageFinder<CompanyPicsVo>(
				companyPicsVo.getPage(), companyPicsVo.getPageSize(), count,
				data);
		return pageFinder;
	}
	/**
	 * Class Name: ICompanyService.java
	 * @Description:查询公有云图片
	 * @author xukaiqiang
	 * @date 2016年6月8日 上午11:22:35
	 * @modifier
	 * @modify-date 2016年6月8日 上午11:22:35
	 * @version 1.0
	 * @param picVo
	 * @return
	 */
	public PageFinder<CompanyPicsVo> queryPublicPic(CompanyPicsVo companyPicsVo){
		List<CompanyPicsVo> data = companyMapper.queryPublicPic(companyPicsVo);
		int count = companyMapper.queryPublicPicCount(companyPicsVo);
		PageFinder<CompanyPicsVo> pageFinder = new PageFinder<CompanyPicsVo>(
				companyPicsVo.getPage(), companyPicsVo.getPageSize(), count,
				data);
		return pageFinder;
	}
	@Override
	public PageFinder<CompanyVo> findCompanyVoByPage(Company search) {
		// TODO Auto-generated method stub
		List<CompanyVo> comlist = companyMapper.findCompanyVoByPage(search);
		int count = companyMapper.findTotalCount(search);
		PageFinder<CompanyVo> pageFinder = new PageFinder<CompanyVo>(
				search.getPage(), search.getPageSize(), count, comlist);
		return pageFinder;
	}

	@Override
	public Integer findTotalCount(Company search) {
		// TODO Auto-generated method stub
		return companyMapper.findTotalCount(search);
	}

	@Override
	public PageFinder<CompanyPicsVo> findCompanyPrivatePic(
			CompanyPicsVo companyPicsVo) {
		List<CompanyPicsVo> data = companyMapper
				.queryCompanyPrivatePic(companyPicsVo);
		int count = companyMapper.queryCompanyPrivatePicCount(companyPicsVo);
		PageFinder<CompanyPicsVo> pageFinder = new PageFinder<CompanyPicsVo>(
				companyPicsVo.getPage(), companyPicsVo.getPageSize(), count,
				data);
		return pageFinder;
	}

	@Override
	public List<CompanyPicsVo> findOneCondtion() {
		// TODO Auto-generated method stub
		return companyMapper.queryOneCondtion();
	}

	@Override
	public Company queryCompanyByCopanyCondition(Company company) {
		// TODO Auto-generated method stub
		return companyMapper.queryCompanyByCopanyCondition(company);
	}

	@Override
	public void insertCompany(Company company) {
		companyMapper.insertCompany(company);
	}

	@Override
	public String findStatus(Integer companyId) {
		return companyMapper.findStatus(companyId);
	}

	@Override
	public List<Integer> findCompanyId(String types) {
		// TODO Auto-generated method stub
		return companyMapper.findCompanyId(types);
	}

	@Override
	public List<Company> findCompanyIdAndLevel() {
		// TODO Auto-generated method stub
		return companyMapper.findCompanyIdAndLevel();
	}
	
	@Override
	public List<Company> findAll(){
		return companyMapper.queryAll();
	}

	@Override
	public void memberLevelUp(Company CEntity, CompanyMemberService CMSEntity) {
		// TODO Auto-generated method stub
		companyMapper.update(CEntity);
		companyMemberServiceMapper.updateByCompanyId(CMSEntity);
	}

	@Override
	public List<companySpecialDomain> findSpecialName(String domainName) {
		// TODO Auto-generated method stub
		return companyMapper.querySpecialName(domainName);
	}
	
	/**
	 * 
	* @Title: findCompanyServiceStaticByPage 
	* @Description: 达到最大招生人数
	* @return
	* @return List<CompanyServiceStatic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-23
	* @user by chopin
	 */
	@Override
	public Boolean reachMaxStudentNum(Integer companyId){
		CompanyMemberService cs=companyMemberServiceMapper.findByCompanyId(companyId);
		Integer rowCount=studentPayMasterMapper.findStuCountByCompanyId(companyId);
		if(cs.getFaceStudentAll()>=rowCount){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 
	* @Title: findCompanyServiceStaticByPage 
	* @Description: 达到服务期限
	* @return
	* @return List<CompanyServiceStatic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-23
	* @user by chopin
	 */
	@Override
	public Boolean reachMaxStudentDate(Integer companyId){
		Company  company =companyMapper.findById(companyId);
		Date now=new Date();
		if(now.after(company.getMemberEndDate())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public PageFinder<CompanyOrgMessageVo> findMessageList(
			CompanyOrgMessageVo search) {
		List<CompanyOrgMessageVo> data=companyMapper.queryMessageList(search);
		int count=companyMapper.queryMessageCount(search);
		PageFinder<CompanyOrgMessageVo> pageFinder=new PageFinder<CompanyOrgMessageVo>(search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	}
	
	
	@Override
	public List<CompanyOrgMessageVo> queryMessageServiceList(CompanyOrgMessageVo search) {
		List<CompanyOrgMessageVo> data=companyMapper.queryMessageServiceList(search);
		return data;
	}

	@Override
	public CompanyOrgMessageVo queryMessageById(Integer id) {
		// TODO Auto-generated method stub
		return companyMapper.queryMessageById(id);
	}

	@Override
	public void insertMsg(CompanyOrgMessageVo c) {
		// TODO Auto-generated method stub
		companyMapper.insertMsg(c);
	}

	@Override
	public void updateMsg(CompanyOrgMessageVo c) {
		// TODO Auto-generated method stub
		companyMapper.updateMsg(c);
	}

	@Override
	public void deleteMsg(Integer id) {
		// TODO Auto-generated method stub
		companyMapper.deleteMsg(id);
	}

	@Override
	public List<Company> queryAllCompany() {
		// TODO Auto-generated method stub
		return companyMapper.queryAllCompany();
	}

	@Override
	public PageFinder<CompanyOrgMessageReadVo> findMessageCenterList(
			CompanyOrgMessageReadVo c) {
		List<CompanyOrgMessageReadVo> data=companyMapper.queryMessageCenterList(c);
		int count=companyMapper.queryMessageCenterCount(c);
		PageFinder<CompanyOrgMessageReadVo> pageFinder=new PageFinder<CompanyOrgMessageReadVo>(c.getPage(), c.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public void insertCompanyReadDetail(CompanyOrgMessageReadVo c) {
		// TODO Auto-generated method stub
		companyMapper.insertCompanyReadDetail(c);
	}

	@Override
	public void updateMsgReadFlag(CompanyOrgMessageReadVo c) {
		// TODO Auto-generated method stub
		companyMapper.updateMsgReadFlag(c);
	}

	@Override
	public CompanyOrgMessageReadVo querymessageCenterById(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return companyMapper.querymessageCenterById(map);
	}

	@Override
	public Integer queryCompanyNotReadNum(CompanyOrgMessageReadVo c) {
		// TODO Auto-generated method stub
		return companyMapper.queryCompanyNotReadNum(c);
	}

	@Override
	public List<CompanyTotalVo> querySourceList(CompanyTotalVo c) {
		List<CompanyTotalVo> arr=companyMapper.querySourceList(c);
		for(CompanyTotalVo com:arr){
			if(null!=com.getSourceType()&&!"".equals(com.getSourceType())){
				SysConfigDict dict=new SysConfigDict();
				dict.setItemCode(com.getSourceType());
				SysConfigDict sys=sysConfigDictMapper.findByCode(dict);
				if(null!=sys&&!"".equals(sys.getItemValue())){
					com.setSourceType(sys.getItemValue());
				}
			}
		}
		return arr;
	}

	@Override
	public PageFinder<CompanyTotalVo> queryCompanyRegistNum(
			CompanyTotalVo search) {
		List<CompanyTotalVo> data=companyMapper.queryRegistNumList(search);
		int count=companyMapper.queryRegistNumListCount(search);
		PageFinder<CompanyTotalVo> pageFinder=new PageFinder<CompanyTotalVo>(search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	}
	
	@Override
	public List<Company> queryRegisterCompany(Map map) {
		List<Company> data=companyMapper.queryRegisterCompany(map);
		return data;
	}

	@Override
	public List<CompanyTotalVo> queryRegistNumChar(CompanyTotalVo c) {
		// TODO Auto-generated method stub
		return companyMapper.queryRegistNumChar(c);
	}

	@Override
	public List<Company> queryCompanyBuyFlag(Company search) {
		// TODO Auto-generated method stub
		return companyMapper.queryCompanyBuyFlag(search);
	}
	/**
	 * 
	* @Title: findCompanyServiceStaticByPage 
	* @Description: 检查公司域名是否被注册
	* @return
	* @return List<CompanyServiceStatic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-23
	* @user by chopin
	 */
	@Override
	public Boolean checkCompanyDomain(String domain) {
		// TODO Auto-generated method stub
		List<Company> list=companyMapper.checkCompanyDomain(domain);
		return list==null || list.size()==0;
	}
	
	/**
	 * 
	* @Title: findCompanyServiceStaticByPage 
	* @Description: 检查公司域名是否被注册
	* @return
	* @return List<CompanyServiceStatic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-23
	* @user by chopin
	 */
	@Override
	public Boolean checkCompanyName(String name) {
		// TODO Auto-generated method stub
		List<Company> list=companyMapper.checkCompanyName(name);
		return list==null || list.size()==0;
	}

	@Override
	public String findDomainById(Integer id) {
		// TODO Auto-generated method stub
		return companyMapper.findDomainById(id);
	}

	@Override
	public PageFinder<RegisterInfoVo> queryRegisterInfo(RegisterInfoVo riv) {
		List<RegisterInfoVo> data=companyMapper.queryRegisterInfoList(riv);
		int count=companyMapper.queryRegisterInfoCount(riv);
		PageFinder<RegisterInfoVo> pageFinder=new PageFinder<RegisterInfoVo>(riv.getPage(), riv.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public List<RegisterInfoVo> queryRegisterInfoExport(RegisterInfoVo riv) {
		return companyMapper.queryRegisterInfoExport(riv);
	}

	@Override
	public Integer queryServiceOpenFlag(Map<String, Object> map) {
		return companyMapper.queryServiceOpenFlag(map);
	}

}
