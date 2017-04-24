package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.company.CompanyFunctionSet;

/**
 * Service Interface:CompanyFunctionSet
 * 
 * @author wang.zx
 * @date 2015-8-19
 */
public interface ICompanyFunctionSetService {
    /**
     * 
     * @Title: saveCompanyFunctionSet
     * @Description: 新增CompanyFunctionSet
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by wangzx
     */
    void insert(CompanyFunctionSet entity);

    /**
     * 
     * @Title: batchSaveCompanyFunctionSet
     * @Description: 批量新增CompanyFunctionSet
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by wangzx
     */
    void batchInsert(List<CompanyFunctionSet> list);

    /**
     * 
     * @Title: updateCompanyFunctionSet
     * @Description: 编辑CompanyFunctionSet
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by wangzx
     */
    void update(CompanyFunctionSet entity);

    /**
     * 
     * Class Name: ICompanyFunctionSetService.java
     * 
     * @Description: 根据公司id和code修改
     * @author yuchanglong
     * @date 2015年9月24日 下午2:46:49
     * @version 1.0
     * @param entity
     */
    void updateByComIdAndCode(CompanyFunctionSet entity);

    /**
     * 
     * @Title: deleteCompanyFunctionSetById
     * @Description: 根据id删除CompanyFunctionSet
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by wangzx
     */
    void deleteCompanyFunctionSetById(Integer id);

    /**
     * 
     * @Title: deleteCompanyFunctionSetByIds
     * @Description: 根据id批量删除CompanyFunctionSet
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by wangzx
     */
    void deleteCompanyFunctionSetByIds(Integer[] ids);

    /**
     * 
     * @Title: findCompanyFunctionSetById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by wangzx
     */
    CompanyFunctionSet findCompanyFunctionSetById(Integer id);

    /**
     * 
     * @Title: findCompanyFunctionSetByPage
     * @Description: 分页查询
     * @return
     * @return List<CompanyFunctionSet> 返回类型
     * @throws @exception
     * @date 2015-8-19
     * @user by wangzx
     */
    List<CompanyFunctionSet> findCompanyFunctionSetByPage(CompanyFunctionSet search);

    /**
     * 
     * Class Name: ICompanyFunctionSetService.java
     * 
     * @Description: 根据公司Id查找
     * @author yuchanglong
     * @date 2015年8月19日 下午6:09:42
     * @version 1.0
     * @param companyId
     * @return
     */
    List<CompanyFunctionSet> findCompanyFunctionSet(CompanyFunctionSet search);

    /**
     * 
     * Class Name: ICompanyFunctionSetService.java
     * 
     * @Description: 条件查询公司使用课程方式
     * @author zhang.zx
     * @date 2015年9月6日 下午7:16:11
     * @modifier
     * @modify-date 2015年9月6日 下午7:16:11
     * @version 1.0
     * @param search
     * @return
     */
    CompanyFunctionSet findCompanyUseCourse(CompanyFunctionSet search);

    /**
     * 
     * Class Name: ICompanyFunctionSetService.java
     * 
     * @Description: 查询公司使用的服务
     * @author zhang.zx
     * @date 2015年12月24日 下午8:09:32
     * @modifier
     * @modify-date 2015年12月24日 下午8:09:32
     * @version 1.0
     * @param search
     * @return
     */
    List<CompanyFunctionSet> findCompanyFunctions(CompanyFunctionSet search);

    List<CompanyFunctionSet> findCompanyFunctionSetBySearch(CompanyFunctionSet search);

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
    Integer findHaveTeachSchedulePrivilege(Map<String, Object> map);

    boolean isCurrentFuSheng(Integer id);

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