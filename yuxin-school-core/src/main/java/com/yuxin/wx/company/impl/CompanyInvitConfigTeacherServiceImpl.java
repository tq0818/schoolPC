package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyInvitConfigTeacherService;
import com.yuxin.wx.company.mapper.CompanyInvitConfigTeacherMapper;
import com.yuxin.wx.model.company.CompanyInvitConfigTeacher;

/**
 * Service Implementation:CompanyInvitConfigTeacher
 * 
 * @author chopin
 * @date 2017-3-13
 */
@Service
@Transactional
public class CompanyInvitConfigTeacherServiceImpl extends BaseServiceImpl implements ICompanyInvitConfigTeacherService {

    @Autowired
    private CompanyInvitConfigTeacherMapper companyInvitConfigTeacherMapper;

    /**
     * 
     * @Title: saveCompanyInvitConfigTeacher
     * @Description: 新增CompanyInvitConfigTeacher
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void insert(CompanyInvitConfigTeacher entity) {
        companyInvitConfigTeacherMapper.insert(entity);
    };

    /**
     * 
     * @Title: batchSaveCompanyInvitConfigTeacher
     * @Description: 批量新增CompanyInvitConfigTeacher
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void batchInsert(List<CompanyInvitConfigTeacher> entity) {
        companyInvitConfigTeacherMapper.batchInsert(entity);
    };

    /**
     * 
     * @Title: updateCompanyInvitConfigTeacher
     * @Description: 编辑CompanyInvitConfigTeacher
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void update(CompanyInvitConfigTeacher entity) {
        companyInvitConfigTeacherMapper.update(entity);
    };

    /**
     * 
     * @Title: deleteCompanyInvitConfigTeacherById
     * @Description: 根据id删除CompanyInvitConfigTeacher
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void deleteCompanyInvitConfigTeacherById(Integer id) {
        companyInvitConfigTeacherMapper.deleteById(id);
    };

    /**
     * 
     * @Title: deleteCompanyInvitConfigTeacherByIds
     * @Description: 根据id批量删除CompanyInvitConfigTeacher
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public void deleteCompanyInvitConfigTeacherByIds(Integer[] ids) {
        companyInvitConfigTeacherMapper.deleteByIds(ids);
    };

    /**
     * 
     * @Title: findCompanyInvitConfigTeacherById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public CompanyInvitConfigTeacher findCompanyInvitConfigTeacherById(Integer id) {
        return companyInvitConfigTeacherMapper.findById(id);
    };

    /**
     * 
     * @Title: findCompanyInvitConfigTeacherByPage
     * @Description: 分页查询
     * @return
     * @return List<CompanyInvitConfigTeacher> 返回类型
     * @throws @exception
     * @date 2017-3-13
     * @user by chopin
     */
    @Override
    public List<CompanyInvitConfigTeacher> findCompanyInvitConfigTeacherByPage(CompanyInvitConfigTeacher search) {
        return companyInvitConfigTeacherMapper.page(search);
    }

    @Override
    public CompanyInvitConfigTeacher findByCompanyId(Integer companyId) {
        return companyInvitConfigTeacherMapper.findByCompanyId(companyId);
    };
}
