package com.yuxin.wx.company.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyLiveStaticDetail;
import com.yuxin.wx.model.statistics.QueryLessonByClassTypeVo;
import com.yuxin.wx.vo.company.CompanyLiveStaticDetailVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyLiveStaticDetailMapper extends BaseMapper<CompanyLiveStaticDetail> {

	/**
	 * 
	 * Class Name: CompanyLiveStaticDetailMapper.java
	 * @Description: 定时任务 获得最大直播并发数
	 * @author 周文斌
	 * @date 2015-5-26 下午7:24:09
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findMaxOnline(Map<String,Object> param);

	/**
	 * Class Name: CompanyLiveStaticDetailMapper.java
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
	List<CompanyLiveStaticDetailVo> queryAllCompanyLiveStaticDetail(CompanyLiveStaticDetailVo companyLiveStaticDetailVo);
	/**
	 * Class Name: CompanyLiveStaticDetailMapper.java
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
	 * Class Name: CompanyLiveStaticDetailController.java
	 * 
	 * @Description: 查询直播上课统计列表-学员手机号-学员名称
	 * @author xukaiqiang
	 * @date 2016年5月23日 下午2:54:35
	 * @modifier
	 * @modify-date 2016年5月23日 下午2:54:35
	 * @version 1.0
	 * @param model
	 * @param statistics
	 * @return
	 */
	List<CompanyLiveStaticDetailVo> queryAllCompanyLiveStaticDetailByStudentNamePhone(
			CompanyLiveStaticDetailVo companyLiveStaticDetailVo);
	/**
	 * Class Name: CompanyLiveStaticDetailMapper.java
	 * @Description: 查询所有的记录条数---1
	 * @author xukaiqiang
	 * @date 2016年5月24日 下午7:18:41
	 * @modifier
	 * @modify-date 2016年5月24日 下午7:18:41
	 * @version 1.0
	 * @param companyLiveStaticDetailVo
	 * @return
	 */
	int pageCountQueryAllCompanyLiveStaticDetail(CompanyLiveStaticDetailVo companyLiveStaticDetailVo);
	/**
	 * Class Name: CompanyLiveStaticDetailMapper.java
	 * @Description: 查询所有的记录条数---2
	 * @author xukaiqiang
	 * @date 2016年5月25日 下午5:35:40
	 * @modifier
	 * @modify-date 2016年5月25日 下午5:35:40
	 * @version 1.0
	 * @param companyLiveStaticDetailVo
	 * @return
	 */
	Integer pageCountQueryAllCompanyLiveStaticDetailByStudentNamePhone(
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