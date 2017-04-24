package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyLiveRecordZs;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyLiveRecordZsMapper extends BaseMapper<CompanyLiveRecordZs> {

	/**
	 * 
	 * Class Name: ICompanyLiveRecordZsService.java
	 * @Description: 查询资源
	 * @author 周文斌
	 * @date 2015-12-7 下午7:12:37
	 * @version 1.0
	 * @param name
	 * @return
	 */
	List<CompanyLiveRecordZs> findRecord(CompanyLiveRecordZs lrzs);
	
	/**
	 * 
	 * Class Name: ICompanyLiveRecordZsService.java
	 * @Description: 查询资源总数
	 * @author 周文斌
	 * @date 2015-12-7 下午7:12:37
	 * @version 1.0
	 * @param name
	 * @return
	 */
	Integer findCountRecord(CompanyLiveRecordZs lrzs);

	/**
	 * 
	 * Class Name: ICompanyLiveRecordZsService.java
	 * @Description: 根据recordID 查询name
	 * @author 周文斌
	 * @date 2015-12-11 下午6:59:57
	 * @version 1.0
	 * @param recordId
	 * @return
	 */
	String findNameByRecordId(String recordId);
}