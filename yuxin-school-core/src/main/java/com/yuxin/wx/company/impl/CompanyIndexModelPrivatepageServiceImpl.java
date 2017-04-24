package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyIndexModelPrivatepageService;
import com.yuxin.wx.company.mapper.CompanyIndexModelClasstypeMapper;
import com.yuxin.wx.company.mapper.CompanyIndexModelCustomMapper;
import com.yuxin.wx.company.mapper.CompanyIndexModelItemMapper;
import com.yuxin.wx.company.mapper.CompanyIndexModelNewsMapper;
import com.yuxin.wx.company.mapper.CompanyIndexModelPrivatepageMapper;
import com.yuxin.wx.model.company.CompanyIndexModelPrivatepage;
import com.yuxin.wx.vo.company.CompanyIndexModelPrivatepageVo;

/**
 * Service Implementation:CompanyIndexModelPrivatepage
 * @author chopin
 * @date 2015-5-18
 */
@Service
@Transactional
public class CompanyIndexModelPrivatepageServiceImpl extends BaseServiceImpl implements ICompanyIndexModelPrivatepageService {

	@Autowired
	private CompanyIndexModelPrivatepageMapper companyIndexModelPrivatepageMapper;
	@Autowired
	private CompanyIndexModelClasstypeMapper  companyIndexModelClasstypeMapper;
	@Autowired
	private CompanyIndexModelCustomMapper companyIndexModelCustomMapper;
	@Autowired
	private CompanyIndexModelItemMapper companyIndexModelItemMapper;
	@Autowired
	private CompanyIndexModelNewsMapper companyIndexModelNewsMapper;
	
	/**
	 * 
	* @Title: saveCompanyIndexModelPrivatepage
	* @Description: 新增CompanyIndexModelPrivatepage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void insert(CompanyIndexModelPrivatepage entity){
		companyIndexModelPrivatepageMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyIndexModelPrivatepage 
	* @Description: 批量新增CompanyIndexModelPrivatepage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyIndexModelPrivatepage> entity){
		companyIndexModelPrivatepageMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyIndexModelPrivatepage 
	* @Description: 编辑CompanyIndexModelPrivatepage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void update(CompanyIndexModelPrivatepage entity){
		companyIndexModelPrivatepageMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelPrivatepageById 
	* @Description: 根据id删除CompanyIndexModelPrivatepage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyIndexModelPrivatepageById(Integer id){
		companyIndexModelPrivatepageMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIndexModelPrivatepageByIds 
	* @Description: 根据id批量删除CompanyIndexModelPrivatepage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void deleteCompanyIndexModelPrivatepageByIds(Integer[] ids){
		companyIndexModelPrivatepageMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexModelPrivatepageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public CompanyIndexModelPrivatepage findCompanyIndexModelPrivatepageById(Integer id){
		return companyIndexModelPrivatepageMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexModelPrivatepageByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public List<CompanyIndexModelPrivatepage> findCompanyIndexModelPrivatepageByPage(CompanyIndexModelPrivatepage search){
		return companyIndexModelPrivatepageMapper.page(search);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexModelPrivatepageByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexModelPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public List<CompanyIndexModelPrivatepageVo> findCompanyConfigList(CompanyIndexModelPrivatepage search){
		List<CompanyIndexModelPrivatepageVo> vo=companyIndexModelPrivatepageMapper.findList(search);
		for(CompanyIndexModelPrivatepageVo v: vo){
			if("INDEX_ITEM".equals(v.getType())){
				v.setItem(companyIndexModelItemMapper.findById(v.getConfigId()));
			}
			if("INDEX_CLASSTYPE".equals(v.getType())){
				v.setClasstype(companyIndexModelClasstypeMapper.findById(v.getConfigId()));
			}
			if("INDEX_CUSTOM".equals(v.getType())){
				v.setCustom(companyIndexModelCustomMapper.findById(v.getConfigId()));
			}
			if("INDEX_NEWS".equals(v.getType())){
				v.setNews(companyIndexModelNewsMapper.findById(v.getConfigId()));
			}
		}
		return vo;
	};
	
	
	
	/**
	 * 
	* @Title: findCompanyIndexModelPrivatepageByPage 
	* @Description: 根据模板查询对应配置
	* @return
	* @return List<CompanyIndexModelPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public List<CompanyIndexModelPrivatepageVo> findByTemplate(CompanyIndexModelPrivatepage search){
		return companyIndexModelPrivatepageMapper.findList(search);
	};
	
	
	/**
	 * 
	* @Title: copyToCompany 
	* @Description: 复制系统配置信息到公司
	* @return
	* @return List<CompanyIndexModelPrivatepage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void copyToCompany(Integer companyId,Integer schoolId,Integer oid,Integer nid){
		CompanyIndexModelPrivatepage search=new CompanyIndexModelPrivatepage();
		search.setTemplateId(oid);
		search.setCompanyId(0);
		search.setSchoolId(0);
		List<CompanyIndexModelPrivatepage> pages=companyIndexModelPrivatepageMapper.findByTemplate(search);
		for(CompanyIndexModelPrivatepage page : pages){
			page.setId(null);
			page.setCompanyId(companyId);
			page.setSchoolId(schoolId);
			page.setTemplateId(nid);
			companyIndexModelPrivatepageMapper.insert(page);
		}
		
	}
	
}
