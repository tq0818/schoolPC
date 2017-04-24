package com.yuxin.wx.company.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyStudyCardLibService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyConfigProxyOrgMapper;
import com.yuxin.wx.company.mapper.CompanyStudyCardLibMapper;
import com.yuxin.wx.model.company.CompanyConfigProxyOrg;
import com.yuxin.wx.model.company.CompanyStudyCard;
import com.yuxin.wx.model.company.CompanyStudyCardLib;
import com.yuxin.wx.util.RandomCodesUtil;
import com.yuxin.wx.vo.company.CompanyStudyCardLibVo;

/**
 * Service Implementation:CompanyStudyCardLib
 * 
 * @author chopin
 * @date 2017-3-14
 */
@Service
@Transactional
public class CompanyStudyCardLibServiceImpl extends BaseServiceImpl implements ICompanyStudyCardLibService {

	@Autowired
	private CompanyStudyCardLibMapper companyStudyCardLibMapper;
	@Autowired
	private CompanyConfigProxyOrgMapper companyConfigProxyOrgMapper;

	/**
	 * 
	 * @Title: saveCompanyStudyCardLib
	 * @Description: 新增CompanyStudyCardLib
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2017-3-14
	 * @user by chopin
	 */
	@Override
	public void insert(CompanyStudyCardLib entity) {
		companyStudyCardLibMapper.insert(entity);
	};

	/**
	 * 
	 * @Title: batchSaveCompanyStudyCardLib
	 * @Description: 批量新增CompanyStudyCardLib
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2017-3-14
	 * @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyStudyCardLib> entity) {
		companyStudyCardLibMapper.batchInsert(entity);
	};

	/**
	 * 
	 * @Title: updateCompanyStudyCardLib
	 * @Description: 编辑CompanyStudyCardLib
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2017-3-14
	 * @user by chopin
	 */
	@Override
	public void update(CompanyStudyCardLib entity) {
		companyStudyCardLibMapper.update(entity);
	};

	/**
	 * 
	 * @Title: deleteCompanyStudyCardLibById
	 * @Description: 根据id删除CompanyStudyCardLib
	 * @param id
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2017-3-14
	 * @user by chopin
	 */
	@Override
	public void deleteCompanyStudyCardLibById(Integer id) {
		companyStudyCardLibMapper.deleteById(id);
	};

	/**
	 * 
	 * @Title: deleteCompanyStudyCardLibByIds
	 * @Description: 根据id批量删除CompanyStudyCardLib
	 * @param ids
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2017-3-14
	 * @user by chopin
	 */
	@Override
	public void deleteCompanyStudyCardLibByIds(Integer[] ids) {
		companyStudyCardLibMapper.deleteByIds(ids);
	};

	/**
	 * 
	 * @Title: findCompanyStudyCardLibById
	 * @Description: 根据id查询
	 * @param id
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2017-3-14
	 * @user by chopin
	 */
	@Override
	public CompanyStudyCardLib findCompanyStudyCardLibById(Integer id) {
		return companyStudyCardLibMapper.findById(id);
	};

	/**
	 * 
	 * @Title: findCompanyStudyCardLibByPage
	 * @Description: 分页查询
	 * @return
	 * @return List<CompanyStudyCardLib> 返回类型
	 * @throws @exception
	 * @date 2017-3-14
	 * @user by chopin
	 */
	@Override
	public List<CompanyStudyCardLib> findCompanyStudyCardLibByPage(CompanyStudyCardLib search) {
		return companyStudyCardLibMapper.page(search);
	}

	@Override
	public List<CompanyStudyCardLibVo> queryStudyCardLibsListByCardId(CompanyStudyCardLibVo lib) {
		return companyStudyCardLibMapper.queryStudyCardLibsListByCardId(lib);
	}

	@Override
	public PageFinder<CompanyStudyCardLibVo> queryStudyCardLibs(CompanyStudyCardLibVo search) {
		List<CompanyStudyCardLibVo> data = companyStudyCardLibMapper.queryStudyCardLibs(search);
		int count = companyStudyCardLibMapper.queryStudyCardLibsCount(search);
		PageFinder<CompanyStudyCardLibVo> pageFinder = new PageFinder<CompanyStudyCardLibVo>(search.getPage(),
				search.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public boolean createStudyCardLibs(CompanyStudyCard studyCard) {
		
		List<String> cardsList = new ArrayList<String>();

		cardsList = RandomCodesUtil.getCodes(RandomCodesUtil.CODETYPE_STUDYCARD, studyCard.getTotalNum());

		String pefix = "XM";
		pefix += studyCard.getPrefix();
		if (studyCard.getProxyOrgId() == null) {
			pefix += "0000";
		} else {
			CompanyConfigProxyOrg proxyOrg = this.companyConfigProxyOrgMapper
					.findById(Integer.parseInt(studyCard.getProxyOrgId()));
			pefix += proxyOrg.getPrefix().toUpperCase();
		}
		
		CompanyStudyCardLib card = null;
		for (String code : cardsList) {
			card = new CompanyStudyCardLib();
			card.setCode(pefix + code);
			card.setStatus(0);
			card.setCompanyId(studyCard.getCompanyId());
			if (studyCard.getProxyOrgId() != null)
				card.setProxyOrganId(Integer.parseInt(studyCard.getProxyOrgId()));
			card.setCardId(studyCard.getId());
			this.companyStudyCardLibMapper.insert(card);
		}
		return true;
	};

}
