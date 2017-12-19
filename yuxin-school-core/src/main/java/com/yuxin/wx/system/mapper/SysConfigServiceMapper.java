package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigService;
import com.yuxin.wx.vo.system.SysConfigServiceVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysConfigServiceMapper extends BaseMapper<SysConfigService> {
	/**
	 * 查询列表
	 * @param search
	 * @return
	 * @author chopin
	 */
	List<SysConfigService> findList(SysConfigService search);
	
	List<SysConfigServiceVo> findPrivilegeByCompanyId(Integer companyId);


	/**
	 * 
	 * Class Name: ISysConfigServiceService.java
	 * @Description: 查询是否存在
	 * @author 周文斌
	 * @date 2015-8-13 上午11:53:07
	 * @version 1.0
	 * @param param
	 * @return
	 */
	public SysConfigService findByCodeId(Map<String, Object> param);

	/**
	 * 
	 * Class Name: ISysConfigServiceService.java
	 * @Description: 查询这个公司已开通服务
	 * @author 周文斌
	 * @date 2015-8-13 下午12:20:05
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<SysConfigService> findByCompanyId(Integer companyId);

	/**
	 * 
	 * Class Name: ISysConfigServiceService.java
	 * @Description: 查询是否存在
	 * @author 周文斌
	 * @date 2015-8-17 下午9:18:56
	 * @version 1.0
	 * @param serv
	 * @return
	 */
	SysConfigService findExist(SysConfigService serv);
	
	List<SysConfigService> findServiceByCompanyId(Integer companyId);
	
	void updateFlag(SysConfigService serv);
	Integer addConfigService(SysConfigService serv);
	Integer deletConfigService(SysConfigService serv);
	Integer finConfigServiceSet(SysConfigService serv);
	
}