package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyLiveStaticDetail;
import com.yuxin.wx.model.statistics.QueryLessonByClassTypeVo;
import com.yuxin.wx.vo.company.CompanyLiveStaticDetailVo;

/**
 * Service Interface:CompanyLiveStaticDetail
 * 
 * @author chopin
 * @date 2015-4-23
 */
public interface ICompanyLiveStaticDetailService {
	/**
	 * 
	 * @Title: saveCompanyLiveStaticDetail
	 * @Description: 新增CompanyLiveStaticDetail
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-4-23
	 * @user by wangzx
	 */
	void insert(CompanyLiveStaticDetail entity);

	/**
	 * 
	 * @Title: batchSaveCompanyLiveStaticDetail
	 * @Description: 批量新增CompanyLiveStaticDetail
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-4-23
	 * @user by wangzx
	 */
	void batchInsert(List<CompanyLiveStaticDetail> list);

	/**
	 * 
	 * @Title: updateCompanyLiveStaticDetail
	 * @Description: 编辑CompanyLiveStaticDetail
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-4-23
	 * @user by wangzx
	 */
	void update(CompanyLiveStaticDetail entity);

	/**
	 * 
	 * @Title: deleteCompanyLiveStaticDetailById
	 * @Description: 根据id删除CompanyLiveStaticDetail
	 * @param id
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-4-23
	 * @user by wangzx
	 */
	void deleteCompanyLiveStaticDetailById(Integer id);

	/**
	 * 
	 * @Title: deleteCompanyLiveStaticDetailByIds
	 * @Description: 根据id批量删除CompanyLiveStaticDetail
	 * @param ids
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-4-23
	 * @user by wangzx
	 */
	void deleteCompanyLiveStaticDetailByIds(Integer[] ids);

	/**
	 * 
	 * @Title: findCompanyLiveStaticDetailById
	 * @Description: 根据id查询
	 * @param id
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2015-4-23
	 * @user by wangzx
	 */
	CompanyLiveStaticDetail findCompanyLiveStaticDetailById(Integer id);

	/**
	 * 
	 * @Title: findCompanyLiveStaticDetailByPage
	 * @Description: 分页查询
	 * @return
	 * @return List<CompanyLiveStaticDetail> 返回类型
	 * @throws @exception
	 * @date 2015-4-23
	 * @user by wangzx
	 */
	List<CompanyLiveStaticDetail> findCompanyLiveStaticDetailByPage(CompanyLiveStaticDetail search);

	/**
	 * 
	 * Class Name: ICompanyLiveStaticDetailService.java
	 * 
	 * @Description: 定时任务 获得最大直播并发数
	 * @author 周文斌
	 * @date 2015-5-26 下午7:24:09
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findMaxOnline(Map<String, Object> param);

	/**
	 * Class Name: CompanyLiveStaticDetailController.java
	 * 
	 * @Description: 查询直播上课统计列表
	 * @author xukaiqiang
	 * @date 2016年5月23日 下午2:54:35
	 * @modifier
	 * @modify-date 2016年5月23日 下午2:54:35
	 * @version 1.0
	 * @param model
	 * @param statistics
	 * @return
	 */
	PageFinder<CompanyLiveStaticDetailVo> queryAllCompanyLiveStaticDetail(
			CompanyLiveStaticDetailVo companyLiveStaticDetailVo);

	/**
	 * Class Name: IStatisticsService.java
	 * 
	 * @Description: 根据课程编号查询所有的课次
	 * @author xukaiqiang
	 * @date 2016年5月19日 下午3:57:17
	 * @modifier
	 * @modify-date 2016年5月19日 下午3:57:17
	 * @version 1.0
	 * @param classTypeId
	 * @return
	 */
	List<QueryLessonByClassTypeVo> queryLessonByClassTypeId(Integer classTypeId);
	/**
	 * Class Name: ICompanyLiveStaticDetailService.java
	 * @Description: 查询所有直播课程统计/学生名称/手机号
	 * @author xukaiqiang
	 * @date 2016年5月23日 下午6:32:39
	 * @modifier
	 * @modify-date 2016年5月23日 下午6:32:39
	 * @version 1.0
	 * @param companyLiveStaticDetailVo
	 * @return
	 */
	PageFinder<CompanyLiveStaticDetailVo> queryAllCompanyLiveStaticDetailByStudentNamePhone(
			CompanyLiveStaticDetailVo companyLiveStaticDetailVo);
	/**
	 * 
	 * Class Name: ICompanyLiveStaticDetailService.java
	 * @Description: 查询合计上课次数
	 * @author xukaiqiang
	 * @date 2016年5月25日 下午10:04:08
	 * @modifier
	 * @modify-date 2016年5月25日 下午10:04:08
	 * @version 1.0
	 * @param companyLiveStaticDetailVo
	 * @return
	 */
	int sumCountClass(CompanyLiveStaticDetailVo companyLiveStaticDetailVo);
}