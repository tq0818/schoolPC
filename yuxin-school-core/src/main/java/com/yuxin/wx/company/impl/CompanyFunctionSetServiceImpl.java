package com.yuxin.wx.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.company.mapper.CompanyFunctionSetMapper;
import com.yuxin.wx.model.company.CompanyFunctionSet;

/**
 * Service Implementation:CompanyFunctionSet
 * 
 * @author wang.zx
 * @date 2015-8-19
 */
@Service
@Transactional
public class CompanyFunctionSetServiceImpl extends BaseServiceImpl implements ICompanyFunctionSetService {

    @Autowired
    private CompanyFunctionSetMapper companyFunctionSetMapper;

    /**
     * 
     * @Title: saveCompanyFunctionSet
     * @Description: 新增CompanyFunctionSet
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by chopin
     */
    @Override
    public void insert(CompanyFunctionSet entity) {
        companyFunctionSetMapper.insert(entity);
    };

    /**
     * 
     * @Title: batchSaveCompanyFunctionSet
     * @Description: 批量新增CompanyFunctionSet
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by chopin
     */
    @Override
    public void batchInsert(List<CompanyFunctionSet> T) {
        companyFunctionSetMapper.batchInsert(T);
    };

    /**
     * 
     * @Title: updateCompanyFunctionSet
     * @Description: 编辑CompanyFunctionSet
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by chopin
     */
    @Override
    public void update(CompanyFunctionSet T) {
        companyFunctionSetMapper.update(T);
    };

    /**
     * 
     * @Title: deleteCompanyFunctionSetById
     * @Description: 根据id删除CompanyFunctionSet
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by chopin
     */
    @Override
    public void deleteCompanyFunctionSetById(Integer id) {
        companyFunctionSetMapper.deleteById(id);
    };

    /**
     * 
     * @Title: deleteCompanyFunctionSetByIds
     * @Description: 根据id批量删除CompanyFunctionSet
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by chopin
     */
    @Override
    public void deleteCompanyFunctionSetByIds(Integer[] ids) {
        companyFunctionSetMapper.deleteByIds(ids);
    };

    /**
     * 
     * @Title: findCompanyFunctionSetById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by chopin
     */
    @Override
    public CompanyFunctionSet findCompanyFunctionSetById(Integer id) {
        return companyFunctionSetMapper.findById(id);
    };

    /**
     * 
     * @Title: findCompanyFunctionSetByPage
     * @Description: 分页查询
     * @return
     * @return List<CompanyFunctionSet> 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by chopin
     */
    @Override
    public List<CompanyFunctionSet> findCompanyFunctionSetByPage(CompanyFunctionSet search) {
        return companyFunctionSetMapper.page(search);
    }

    @Override
    public List<CompanyFunctionSet> findCompanyFunctionSet(CompanyFunctionSet search) {
        return companyFunctionSetMapper.findCompanyFunctionSet(search);
    }

    @Override
    public CompanyFunctionSet findCompanyUseCourse(CompanyFunctionSet search) {
        return companyFunctionSetMapper.findCompanyUseCourse(search);
    }

    @Override
    public void updateByComIdAndCode(CompanyFunctionSet entity) {
        CompanyFunctionSet set = new CompanyFunctionSet();
        set.setCompanyId(entity.getCompanyId());
        set.setFunctionCode(entity.getFunctionCode());
        CompanyFunctionSet functionSet = companyFunctionSetMapper.findCompanyUseCourse(set);
        if (functionSet == null) {
            companyFunctionSetMapper.insert(entity);
        } else {
            companyFunctionSetMapper.updateByComIdAndCode(entity);
        }
    }

    @Override
    public List<CompanyFunctionSet> findCompanyFunctions(CompanyFunctionSet search) {
        return companyFunctionSetMapper.findCompanyFunctions(search);
    }

    @Override
    public List<CompanyFunctionSet> findCompanyFunctionSetBySearch(CompanyFunctionSet search) {
        // TODO Auto-generated method stub
        return companyFunctionSetMapper.findCompanyFunctionSetBySearch(search);
    }

    /**
     * Class Name: ICompanyFunctionSetService.java
     * 
     * @Description:查找是否允许排课
     * @author xukaiqiang
     * @date 2016年6月5日 下午6:06:45
     * @modifier
     * @modify-date 2016年6月5日 下午6:06:45
     * @version 1.0
     * @param map
     * @return
     */
    @Override
    public Integer findHaveTeachSchedulePrivilege(Map<String, Object> map) {
        return companyFunctionSetMapper.findHaveTeachSchedulePrivilege(map);
    }

    @Override
    public boolean isCurrentFuSheng(Integer id) {
        CompanyFunctionSet functionSet = new CompanyFunctionSet();
        functionSet.setCompanyId(id);
        functionSet.setFunctionCode("TEACHER_MODIFY_COURSE");
        functionSet.setStatus("1");
        List<CompanyFunctionSet> list = companyFunctionSetMapper.findCompanyFunctionSet(functionSet);
        if (list == null || list.size() == 0)
            return false;
        return true;
    }

    /**
     * wz 根据 function_code = ‘COMPANY_STUDENT_BIND' company_id 和 status
     * 查询是否存在次数据
     * 
     * @param cfs
     * @return
     */
    @Override
    public CompanyFunctionSet getCompanyFunctionSet(CompanyFunctionSet cfs) {

        return companyFunctionSetMapper.getCompanyFunctionSet(cfs);
    }

    @Override
    public List<CompanyFunctionSet> queryProtocolFucntionSet(CompanyFunctionSet cfs) {
        return companyFunctionSetMapper.queryProtocolFucntionSet(cfs);
    }
}
