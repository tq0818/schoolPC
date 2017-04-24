package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyInvitConfigOrgService;
import com.yuxin.wx.company.mapper.CompanyInvitConfigOrgMapper;
import com.yuxin.wx.model.company.CompanyInvitConfigOrg;

/**
 * Service Implementation:CompanyInvitConfigOrg
 * 
 * @author chopin
 * @date 2017-3-13
 */
@Service
@Transactional
public class CompanyInvitConfigOrgServiceImpl extends BaseServiceImpl implements ICompanyInvitConfigOrgService {

    @Autowired
    private CompanyInvitConfigOrgMapper companyInvitConfigOrgMapper;

    /**
     * 
     * @Title: saveCompanyInvitConfigOrg
     * @Description: 新增CompanyInvitConfigOrg
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void insert(CompanyInvitConfigOrg entity) {
        companyInvitConfigOrgMapper.insert(entity);
    };

    /**
     * 
     * @Title: batchSaveCompanyInvitConfigOrg
     * @Description: 批量新增CompanyInvitConfigOrg
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void batchInsert(List<CompanyInvitConfigOrg> entity) {
        companyInvitConfigOrgMapper.batchInsert(entity);
    };

    /**
     * 
     * @Title: updateCompanyInvitConfigOrg
     * @Description: 编辑CompanyInvitConfigOrg
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void update(CompanyInvitConfigOrg entity) {
        companyInvitConfigOrgMapper.update(entity);
    };

    /**
     * 
     * @Title: deleteCompanyInvitConfigOrgById
     * @Description: 根据id删除CompanyInvitConfigOrg
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void deleteCompanyInvitConfigOrgById(Integer id) {
        companyInvitConfigOrgMapper.deleteById(id);
    };

    /**
     * 
     * @Title: deleteCompanyInvitConfigOrgByIds
     * @Description: 根据id批量删除CompanyInvitConfigOrg
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void deleteCompanyInvitConfigOrgByIds(Integer[] ids) {
        companyInvitConfigOrgMapper.deleteByIds(ids);
    };

    /**
     * 
     * @Title: findCompanyInvitConfigOrgById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public CompanyInvitConfigOrg findCompanyInvitConfigOrgById(Integer id) {
        return companyInvitConfigOrgMapper.findById(id);
    };

    /**
     * 
     * @Title: findCompanyInvitConfigOrgByPage
     * @Description: 分页查询
     * @return
     * @return List<CompanyInvitConfigOrg> 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public List<CompanyInvitConfigOrg> findCompanyInvitConfigOrgByPage(CompanyInvitConfigOrg search) {
        return companyInvitConfigOrgMapper.page(search);
    }

    @Override
    public CompanyInvitConfigOrg findByCompanyId(Integer companyId) {
        return companyInvitConfigOrgMapper.findByCompanyId(companyId);
    };
}
