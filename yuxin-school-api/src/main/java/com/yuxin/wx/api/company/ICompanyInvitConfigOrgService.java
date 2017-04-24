package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyInvitConfigOrg;

/**
 * Service Interface:CompanyInvitConfigOrg
 * 
 * @author chopin
 * @date 2017-3-13
 */
public interface ICompanyInvitConfigOrgService {
    /**
     * 
     * @Title: saveCompanyInvitConfigOrg
     * @Description: 新增CompanyInvitConfigOrg
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void insert(CompanyInvitConfigOrg entity);

    /**
     * 
     * @Title: batchSaveCompanyInvitConfigOrg
     * @Description: 批量新增CompanyInvitConfigOrg
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void batchInsert(List<CompanyInvitConfigOrg> list);

    /**
     * 
     * @Title: updateCompanyInvitConfigOrg
     * @Description: 编辑CompanyInvitConfigOrg
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void update(CompanyInvitConfigOrg entity);

    /**
     * 
     * @Title: deleteCompanyInvitConfigOrgById
     * @Description: 根据id删除CompanyInvitConfigOrg
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void deleteCompanyInvitConfigOrgById(Integer id);

    /**
     * 
     * @Title: deleteCompanyInvitConfigOrgByIds
     * @Description: 根据id批量删除CompanyInvitConfigOrg
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void deleteCompanyInvitConfigOrgByIds(Integer[] ids);

    /**
     * 
     * @Title: findCompanyInvitConfigOrgById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    CompanyInvitConfigOrg findCompanyInvitConfigOrgById(Integer id);

    /**
     * 
     * @Title: findCompanyInvitConfigOrgByPage
     * @Description: 分页查询
     * @return
     * @return List<CompanyInvitConfigOrg> 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    List<CompanyInvitConfigOrg> findCompanyInvitConfigOrgByPage(CompanyInvitConfigOrg search);

    CompanyInvitConfigOrg findByCompanyId(Integer companyId);
}