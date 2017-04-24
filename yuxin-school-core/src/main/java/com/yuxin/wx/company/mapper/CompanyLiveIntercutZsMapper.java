package com.yuxin.wx.company.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyLiveIntercutZs;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyLiveIntercutZsMapper extends BaseMapper<CompanyLiveIntercutZs> {

	/**
	 * 
	 * Class Name: ICompanyLiveIntercutZsService.java
	 * @Description: 查看已存在插播
	 * @author 周文斌
	 * @date 2015-12-11 下午7:38:46
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<CompanyLiveIntercutZs> findAllByCont(CompanyLiveIntercutZs search);
	
	/**
	 * 
	 * Class Name: ICompanyLiveIntercutZsService.java
	 * @Description: 查看已存在插播总数
	 * @author 周文斌
	 * @date 2015-12-11 下午7:38:46
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Integer findCountAllByCont(CompanyLiveIntercutZs search);

	/**
	 * 
	 * Class Name: ICompanyLiveIntercutZsService.java
	 * @Description: 根据条件取消插播课件
	 * @author 周文斌
	 * @date 2015-12-11 下午9:00:50
	 * @version 1.0
	 * @param search
	 */
	void delByCont(CompanyLiveIntercutZs search);
	 
	/**
	 * 
	 * Class Name: CompanyLiveIntercutZsMapper.java
	 * @Description: 查询是否存在
	 * @author 周文斌
	 * @date 2015-12-14 下午9:23:50
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<CompanyLiveIntercutZs> findExist(CompanyLiveIntercutZs search);
}