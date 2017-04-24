package com.yuxin.wx.company.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyFunctionSet;

/**
 * Service Interface:Users
 * 
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyFunctionSetMapper extends BaseMapper<CompanyFunctionSet> {
    List<CompanyFunctionSet> findCompanyFunctionSet(CompanyFunctionSet search);

    CompanyFunctionSet findCompanyUseCourse(CompanyFunctionSet search);

    void updateByComIdAndCode(CompanyFunctionSet entity);

    List<CompanyFunctionSet> findCompanyFunctions(CompanyFunctionSet search);

    List<CompanyFunctionSet> findCompanyFunctionSetBySearch(CompanyFunctionSet search);

    /**
     * Class Name: CompanyFunctionSetMapper.java
     * 
     * @Description: 查找是否允许排课
     * @author xukaiqiang
     * @date 2016年6月5日 下午6:08:01
     * @modifier
     * @modify-date 2016年6月5日 下午6:08:01
     * @version 1.0
     * @param map
     * @return
     */
    Integer findHaveTeachSchedulePrivilege(Map<String, Object> map);

    /**
     * wz 根据 function_code = ‘COMPANY_STUDENT_BIND' company_id 和 status
     * 查询是否存在次数据
     * 
     * @param cfs
     * @return
     */
    CompanyFunctionSet getCompanyFunctionSet(CompanyFunctionSet cfs);

    List<CompanyFunctionSet> queryProtocolFucntionSet(CompanyFunctionSet cfs);
}