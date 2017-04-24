package com.yuxin.wx.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyConfigProxyOrgService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyConfigProxyOrgMapper;
import com.yuxin.wx.model.company.CompanyConfigProxyOrg;

/**
 * Service Implementation:CompanyConfigProxyOrg
 * 
 * @author chopin
 * @date 2017-3-13
 */
@Service
@Transactional
public class CompanyConfigProxyOrgServiceImpl extends BaseServiceImpl implements ICompanyConfigProxyOrgService {

    @Autowired
    private CompanyConfigProxyOrgMapper companyConfigProxyOrgMapper;

    /**
     * 
     * @Title: saveCompanyConfigProxyOrg
     * @Description: 新增CompanyConfigProxyOrg
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void insert(CompanyConfigProxyOrg entity) {
        companyConfigProxyOrgMapper.insert(entity);
    };

    /**
     * 
     * @Title: batchSaveCompanyConfigProxyOrg
     * @Description: 批量新增CompanyConfigProxyOrg
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void batchInsert(List<CompanyConfigProxyOrg> entity) {
        companyConfigProxyOrgMapper.batchInsert(entity);
    };

    /**
     * 
     * @Title: updateCompanyConfigProxyOrg
     * @Description: 编辑CompanyConfigProxyOrg
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void update(CompanyConfigProxyOrg entity) {
        companyConfigProxyOrgMapper.update(entity);
    };

    /**
     * 
     * @Title: deleteCompanyConfigProxyOrgById
     * @Description: 根据id删除CompanyConfigProxyOrg
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void deleteCompanyConfigProxyOrgById(Integer id) {
        companyConfigProxyOrgMapper.deleteById(id);
    };

    /**
     * 
     * @Title: deleteCompanyConfigProxyOrgByIds
     * @Description: 根据id批量删除CompanyConfigProxyOrg
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void deleteCompanyConfigProxyOrgByIds(Integer[] ids) {
        companyConfigProxyOrgMapper.deleteByIds(ids);
    };

    /**
     * 
     * @Title: findCompanyConfigProxyOrgById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public CompanyConfigProxyOrg findCompanyConfigProxyOrgById(Integer id) {
        return companyConfigProxyOrgMapper.findById(id);
    };

    /**
     * 
     * @Title: findCompanyConfigProxyOrgByPage
     * @Description: 分页查询
     * @return
     * @return List<CompanyConfigProxyOrg> 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public List<CompanyConfigProxyOrg> findCompanyConfigProxyOrgByPage(CompanyConfigProxyOrg search) {
        return companyConfigProxyOrgMapper.page(search);
    }

    @Override
    public PageFinder<CompanyConfigProxyOrg> findProxysList(CompanyConfigProxyOrg search) {

        List<CompanyConfigProxyOrg> list = companyConfigProxyOrgMapper.findProxysList(search);
        int count = this.findProxysListCount(search);
        return new PageFinder<CompanyConfigProxyOrg>(search.getPage(), search.getPageSize(), count, list);
    }

    @Override
    public int findProxysListCount(CompanyConfigProxyOrg search) {
        return companyConfigProxyOrgMapper.findProxysListCount(search);
    }

    @Override
    public List<CompanyConfigProxyOrg> queryProxyByCompanyId(CompanyConfigProxyOrg search) {
        return companyConfigProxyOrgMapper.queryProxyByCompanyId(search);
    }

    @Override
    public int queryCountByNameOrPrefix(Map<String, Object> map) {
        return companyConfigProxyOrgMapper.queryCountByNameOrPrefix(map);
    }

    @Override
    public CompanyConfigProxyOrg findByInviteCode(CompanyConfigProxyOrg search) {

        return companyConfigProxyOrgMapper.findByInviteCode(search);
    }

	@Override
	public List<CompanyConfigProxyOrg> queryCompanyUnsetRateOrg(Integer companyId) {
		return companyConfigProxyOrgMapper.queryCompanyUnsetRateOrg(companyId);
	};
}
