package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyConfigProxyOrg;

/**
 * Service Interface:CompanyConfigProxyOrg
 * 
 * @author chopin
 * @date 2017-3-13
 */
public interface ICompanyConfigProxyOrgService {
    /**
     * 
     * @Title: saveCompanyConfigProxyOrg
     * @Description: 新增CompanyConfigProxyOrg
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void insert(CompanyConfigProxyOrg entity);

    /**
     * 
     * @Title: batchSaveCompanyConfigProxyOrg
     * @Description: 批量新增CompanyConfigProxyOrg
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void batchInsert(List<CompanyConfigProxyOrg> list);

    /**
     * 
     * @Title: updateCompanyConfigProxyOrg
     * @Description: 编辑CompanyConfigProxyOrg
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void update(CompanyConfigProxyOrg entity);

    /**
     * 
     * @Title: deleteCompanyConfigProxyOrgById
     * @Description: 根据id删除CompanyConfigProxyOrg
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void deleteCompanyConfigProxyOrgById(Integer id);

    /**
     * 
     * @Title: deleteCompanyConfigProxyOrgByIds
     * @Description: 根据id批量删除CompanyConfigProxyOrg
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void deleteCompanyConfigProxyOrgByIds(Integer[] ids);

    /**
     * 
     * @Title: findCompanyConfigProxyOrgById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    CompanyConfigProxyOrg findCompanyConfigProxyOrgById(Integer id);

    /**
     * 
     * @Title: findCompanyConfigProxyOrgByPage
     * @Description: 分页查询
     * @return
     * @return List<CompanyConfigProxyOrg> 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    List<CompanyConfigProxyOrg> findCompanyConfigProxyOrgByPage(CompanyConfigProxyOrg search);

    PageFinder<CompanyConfigProxyOrg> findProxysList(CompanyConfigProxyOrg search);

    int findProxysListCount(CompanyConfigProxyOrg search);

    List<CompanyConfigProxyOrg> queryProxyByCompanyId(CompanyConfigProxyOrg search);

    int queryCountByNameOrPrefix(Map<String, Object> map);

    CompanyConfigProxyOrg findByInviteCode(CompanyConfigProxyOrg search);
    
    List<CompanyConfigProxyOrg> queryCompanyUnsetRateOrg(Integer companyId);
}