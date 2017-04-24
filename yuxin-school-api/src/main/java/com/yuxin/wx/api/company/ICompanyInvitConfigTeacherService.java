package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.model.company.CompanyInvitConfigTeacher;

/**
 * Service Interface:CompanyInvitConfigTeacher
 * 
 * @author chopin
 * @date 2017-3-13
 */
public interface ICompanyInvitConfigTeacherService {
    /**
     * 
     * @Title: saveCompanyInvitConfigTeacher
     * @Description: 新增CompanyInvitConfigTeacher
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void insert(CompanyInvitConfigTeacher entity);

    /**
     * 
     * @Title: batchSaveCompanyInvitConfigTeacher
     * @Description: 批量新增CompanyInvitConfigTeacher
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void batchInsert(List<CompanyInvitConfigTeacher> list);

    /**
     * 
     * @Title: updateCompanyInvitConfigTeacher
     * @Description: 编辑CompanyInvitConfigTeacher
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void update(CompanyInvitConfigTeacher entity);

    /**
     * 
     * @Title: deleteCompanyInvitConfigTeacherById
     * @Description: 根据id删除CompanyInvitConfigTeacher
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void deleteCompanyInvitConfigTeacherById(Integer id);

    /**
     * 
     * @Title: deleteCompanyInvitConfigTeacherByIds
     * @Description: 根据id批量删除CompanyInvitConfigTeacher
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    void deleteCompanyInvitConfigTeacherByIds(Integer[] ids);

    /**
     * 
     * @Title: findCompanyInvitConfigTeacherById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    CompanyInvitConfigTeacher findCompanyInvitConfigTeacherById(Integer id);

    /**
     * 
     * @Title: findCompanyInvitConfigTeacherByPage
     * @Description: 分页查询
     * @return
     * @return List<CompanyInvitConfigTeacher> 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by wangzx
     */
    List<CompanyInvitConfigTeacher> findCompanyInvitConfigTeacherByPage(CompanyInvitConfigTeacher search);

    CompanyInvitConfigTeacher findByCompanyId(Integer companyId);
}