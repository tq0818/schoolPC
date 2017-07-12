package com.yuxin.wx.company.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyLiveStaticDetailService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyLiveStaticDetailMapper;
import com.yuxin.wx.model.company.CompanyLiveStaticDetail;
import com.yuxin.wx.model.statistics.QueryLessonByClassTypeVo;
import com.yuxin.wx.vo.company.CompanyLiveStaticDetailVo;

/**
 * Service Implementation:CompanyLiveStaticDetail
 * @author chopin
 * @date 2015-4-23
 */
@Service
@Transactional
public class CompanyLiveStaticDetailServiceImpl extends BaseServiceImpl implements ICompanyLiveStaticDetailService {
	private  static  Log  log=LogFactory.getLog("log");
	@Autowired
	private CompanyLiveStaticDetailMapper companyLiveStaticDetailMapper;
	
	/**
	 * 
	* @Title: saveCompanyLiveStaticDetail
	* @Description: 新增CompanyLiveStaticDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void insert(CompanyLiveStaticDetail entity){
		companyLiveStaticDetailMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyLiveStaticDetail 
	* @Description: 批量新增CompanyLiveStaticDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyLiveStaticDetail> entity){
		companyLiveStaticDetailMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyLiveStaticDetail 
	* @Description: 编辑CompanyLiveStaticDetail
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void update(CompanyLiveStaticDetail entity){
		companyLiveStaticDetailMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveStaticDetailById 
	* @Description: 根据id删除CompanyLiveStaticDetail
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyLiveStaticDetailById(Integer id){
		companyLiveStaticDetailMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyLiveStaticDetailByIds 
	* @Description: 根据id批量删除CompanyLiveStaticDetail
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public void deleteCompanyLiveStaticDetailByIds(Integer[] ids){
		companyLiveStaticDetailMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveStaticDetailById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public CompanyLiveStaticDetail findCompanyLiveStaticDetailById(Integer id){
		return companyLiveStaticDetailMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyLiveStaticDetailByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyLiveStaticDetail>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by chopin
	 */
	@Override
	public List<CompanyLiveStaticDetail> findCompanyLiveStaticDetailByPage(CompanyLiveStaticDetail search){
		return companyLiveStaticDetailMapper.page(search);
	}

	@Override
	public Integer findMaxOnline(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return companyLiveStaticDetailMapper.findMaxOnline(param);
	};
	/**
	 * Class Name: CompanyLiveStaticDetailController.java
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
	@Override
	public PageFinder<CompanyLiveStaticDetailVo> queryAllCompanyLiveStaticDetail(CompanyLiveStaticDetailVo companyLiveStaticDetailVo) {
		Integer pageSize = companyLiveStaticDetailVo.getPageSize();
		int rowCount=companyLiveStaticDetailMapper.pageCountQueryAllCompanyLiveStaticDetail(companyLiveStaticDetailVo);
//		if(companyLiveStaticDetailVo.getFirstIndex()+companyLiveStaticDetailVo.getPageSize() > rowCount){
//			companyLiveStaticDetailVo.setPageSize(rowCount - companyLiveStaticDetailVo.getFirstIndex());
//		}
		List<CompanyLiveStaticDetailVo> data = companyLiveStaticDetailMapper.queryAllCompanyLiveStaticDetail(companyLiveStaticDetailVo);
		log.info("查询直播上课统计：根据课次查询,总共查询出"+rowCount+"条数据");
		companyLiveStaticDetailVo.setPageSize(pageSize);
		PageFinder<CompanyLiveStaticDetailVo> pageFinder = new PageFinder<CompanyLiveStaticDetailVo>(companyLiveStaticDetailVo.getPage(), companyLiveStaticDetailVo.getPageSize(), rowCount, data);
		return pageFinder;
		
	};
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
	public List<QueryLessonByClassTypeVo> queryLessonByClassTypeId(Integer classTypeId) {
		return companyLiveStaticDetailMapper.queryLessonByClassTypeId(classTypeId);
	}
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
	@Override
	public PageFinder<CompanyLiveStaticDetailVo> queryAllCompanyLiveStaticDetailByStudentNamePhone(CompanyLiveStaticDetailVo companyLiveStaticDetailVo) {
		List<CompanyLiveStaticDetailVo> data = companyLiveStaticDetailMapper.queryAllCompanyLiveStaticDetailByStudentNamePhone(companyLiveStaticDetailVo);
		Integer rowCount=companyLiveStaticDetailMapper.pageCountQueryAllCompanyLiveStaticDetailByStudentNamePhone(companyLiveStaticDetailVo);
		rowCount=rowCount==null?0:rowCount;
		PageFinder<CompanyLiveStaticDetailVo> pageFinder = new PageFinder<CompanyLiveStaticDetailVo>(companyLiveStaticDetailVo.getPage(), companyLiveStaticDetailVo.getPageSize(), rowCount, data);
		return pageFinder;
	}
	
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
	public int sumCountClass(CompanyLiveStaticDetailVo companyLiveStaticDetailVo){
		return companyLiveStaticDetailMapper.sumCountClass(companyLiveStaticDetailVo);
	}
}
