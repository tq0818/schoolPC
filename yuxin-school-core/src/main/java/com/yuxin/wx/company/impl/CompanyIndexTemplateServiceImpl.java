package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyIndexTemplateService;
import com.yuxin.wx.company.mapper.CompanyIndexModelClasstypeMapper;
import com.yuxin.wx.company.mapper.CompanyIndexModelCustomMapper;
import com.yuxin.wx.company.mapper.CompanyIndexModelItemMapper;
import com.yuxin.wx.company.mapper.CompanyIndexModelNewsMapper;
import com.yuxin.wx.company.mapper.CompanyIndexModelPrivatepageMapper;
import com.yuxin.wx.company.mapper.CompanyIndexTemplateMapper;
import com.yuxin.wx.model.company.CompanyIndexModelClasstype;
import com.yuxin.wx.model.company.CompanyIndexModelCustom;
import com.yuxin.wx.model.company.CompanyIndexModelItem;
import com.yuxin.wx.model.company.CompanyIndexModelNews;
import com.yuxin.wx.model.company.CompanyIndexModelPrivatepage;
import com.yuxin.wx.model.company.CompanyIndexTemplate;

/**
 * Service Implementation:CompanyIndexTemplate
 * @author chopin
 * @date 2015-5-18
 */
@Service
@Transactional
public class CompanyIndexTemplateServiceImpl extends BaseServiceImpl implements ICompanyIndexTemplateService {

	@Autowired
	private CompanyIndexTemplateMapper companyIndexTemplateMapper;
	
	@Autowired
	private CompanyIndexModelPrivatepageMapper companyIndexModelPrivatepageMapper;
	@Autowired
	private CompanyIndexModelClasstypeMapper companyIndexModelClasstypeMapper;
	@Autowired
	private CompanyIndexModelCustomMapper companyIndexModelCustomMapper;
	@Autowired
	private CompanyIndexModelItemMapper companyIndexModelItemMapper;
	@Autowired
	private CompanyIndexModelNewsMapper companyIndexModelNewsMapper;
	/**
	 * 
	* @Title: saveCompanyIndexTemplate
	* @Description: 新增CompanyIndexTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void insert(CompanyIndexTemplate entity){
		companyIndexTemplateMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyIndexTemplate 
	* @Description: 批量新增CompanyIndexTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyIndexTemplate> entity){
		companyIndexTemplateMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyIndexTemplate 
	* @Description: 编辑CompanyIndexTemplate
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void update(CompanyIndexTemplate entity){
		companyIndexTemplateMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIndexTemplateById 
	* @Description: 根据id删除CompanyIndexTemplate
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyIndexTemplateById(Integer id){
		companyIndexTemplateMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyIndexTemplateByIds 
	* @Description: 根据id批量删除CompanyIndexTemplate
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void deleteCompanyIndexTemplateByIds(Integer[] ids){
		companyIndexTemplateMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexTemplateById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public CompanyIndexTemplate findCompanyIndexTemplateById(Integer id){
		return companyIndexTemplateMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexTemplateByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public List<CompanyIndexTemplate> findCompanyIndexTemplateByPage(CompanyIndexTemplate search){
		return companyIndexTemplateMapper.page(search);
	};
	
	/**
	 * 
	* @Title: findCompanyIndexTemplateByPage 
	* @Description: 查询公司模板
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public List<CompanyIndexTemplate> findTemplateByCompany(Integer companyId,Integer schoolId){
		CompanyIndexTemplate search=new CompanyIndexTemplate();
		search.setCompanyId(companyId);
		if(schoolId!=null){
			search.setSchoolId(schoolId);
		}
		return companyIndexTemplateMapper.findTemplateByCompany(search);
	};
	
	/**
	 * 
	* @Title: copyToCompany 
	* @Description: 复制系统模板到公司模板
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public CompanyIndexTemplate copyToCompany(Integer companyId,Integer schoolId,Integer id){
		CompanyIndexTemplate template=companyIndexTemplateMapper.findById(id);
		template.setCompanyId(companyId);
		template.setSchoolId(schoolId);
		companyIndexTemplateMapper.copyToCompany(template);
		
		CompanyIndexModelPrivatepage search=new CompanyIndexModelPrivatepage();
		search.setTemplateId(id);
		search.setCompanyId(0);
		search.setSchoolId(0);
		List<CompanyIndexModelPrivatepage> pages=companyIndexModelPrivatepageMapper.findByTemplate(search);
		for(CompanyIndexModelPrivatepage page : pages){
			Integer configId=0;
			if("INDEX_ITEM".equals(page.getType())){
				CompanyIndexModelItem item=companyIndexModelItemMapper.findById(page.getConfigId());
				item.setId(null);
				companyIndexModelItemMapper.insert(item);
				configId=item.getId();
			}
			if("INDEX_CLASSTYPE".equals(page.getType())){
				CompanyIndexModelClasstype classtype=companyIndexModelClasstypeMapper.findById(page.getConfigId());
				classtype.setId(null);
				companyIndexModelClasstypeMapper.insert(classtype);
				configId=classtype.getId();
			}
			if("INDEX_CUSTOM".equals(page.getType())){
				CompanyIndexModelCustom custom=companyIndexModelCustomMapper.findById(page.getConfigId());
				custom.setId(null);
				companyIndexModelCustomMapper.insert(custom);
				configId=custom.getId();
			}
			if("INDEX_NEWS".equals(page.getType())){
				CompanyIndexModelNews news=companyIndexModelNewsMapper.findById(page.getConfigId());
				news.setId(null);
				companyIndexModelNewsMapper.insert(news);
				configId=news.getId();
			}
			page.setId(null);
			page.setCompanyId(companyId);
			page.setSchoolId(schoolId);
			page.setTemplateId(template.getId());
			page.setConfigId(configId);
			companyIndexModelPrivatepageMapper.insert(page);
		}
		
		return template;
	};
	
	/**
	 * 
	* @Title: copyToCompany 
	* @Description: 复制系统模板到公司模板
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public CompanyIndexTemplate copyToCompany2(Integer companyId,Integer schoolId,Integer id){
		CompanyIndexTemplate template=companyIndexTemplateMapper.findById(id);
		template.setCompanyId(companyId);
		template.setSchoolId(schoolId);
		companyIndexTemplateMapper.copyToCompany(template);
		
		CompanyIndexModelPrivatepage search=new CompanyIndexModelPrivatepage();
		search.setTemplateId(id);
		search.setCompanyId(0);
		search.setSchoolId(0);
		List<CompanyIndexModelPrivatepage> pages=companyIndexModelPrivatepageMapper.findByTemplate(search);
		for(CompanyIndexModelPrivatepage page : pages){
			page.setId(null);
			page.setCompanyId(companyId);
			page.setSchoolId(schoolId);
			page.setTemplateId(template.getId());
			companyIndexModelPrivatepageMapper.insert(page);
		}
		
		return template;
	};
	/**
	 * 
	* @Title: useTemplate 
	* @Description: 使用模板
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void useTemplate(Integer companyId,Integer schoolId,Integer templateId){
		companyIndexTemplateMapper.updateStatusAllFalse(companyId, schoolId);
		CompanyIndexTemplate template=new CompanyIndexTemplate();
		template.setId(templateId);
		template.setTemplateStatus("1");
		companyIndexTemplateMapper.update(template);
		
		
	}
	
	/**
	 * 
	* @Title: useTemplate 
	* @Description: 使用模板
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public void unUseTemplate(Integer companyId,Integer schoolId){
		companyIndexTemplateMapper.updateStatusAllFalse(companyId, schoolId);
	}
	
	
	/**
	 * 
	* @Title: useTemplate 
	* @Description: 使用模板
	* @return
	* @return List<CompanyIndexTemplate>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-18
	* @user by chopin
	 */
	@Override
	public Boolean checkByName(CompanyIndexTemplate search){
		int n=companyIndexTemplateMapper.findByName(search);
		return n>0;
	}

	@Override
	public List<CompanyIndexTemplate> findTemplateByCompanyDesc(Integer companyId, Integer schoolId) {
		CompanyIndexTemplate search=new CompanyIndexTemplate();
		search.setCompanyId(companyId);
		if(schoolId!=null){
			search.setSchoolId(schoolId);
		}
		return companyIndexTemplateMapper.findTemplateByCompanyDesc(search);
	}
}
