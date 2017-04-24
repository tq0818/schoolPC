package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.vo.system.SysPageHeadFootVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysPageHeadFootMapper extends BaseMapper<SysPageHeadFoot> {
	
	List<SysPageHeadFootVo> querySysPageHeadFootList(SysPageHeadFootVo sysPageHeadFoot);
	//查询二级标题
	List<SysPageHeadFootVo> queryTwoSysPagerList();
	
	List<SysPageHeadFootVo> selectAll();
	
	void insertHeadTitle(SysPageHeadFoot sysPageHeadFoot);
	
    Integer queryIsUseHeadTitle(SysPageHeadFoot sysPageHeadFoot);

	List<SysPageHeadFoot> findByConpanyId(int id);
	
	void changStatus(SysPageHeadFoot entity);

	void updateByNameAndCpId(Map<String, String> map);
	String selectByCpIdAndName(Map<String, String> map);
	
	List<SysPageHeadFoot> queryHeadIsUse(SysPageHeadFoot sysPageHeadFoot);
	int selectHeadCount(Integer companyId);
	int selectFootCount(Integer companyId);
	
	List<SysPageHeadFoot> queryAllByCompanyId(Integer companyId);

	/**
	 * 
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 查询单个是否存在
	 * @author 周文斌
	 * @date 2015-10-8 下午6:17:25
	 * @version 1.0
	 * @param sphf
	 * @return
	 */
	SysPageHeadFoot findHeadFoot(SysPageHeadFoot sphf);
	
	List<SysPageHeadFootVo> queryFootContentsByConfigId(SysPageHeadFoot search);
	
	List<SysPageHeadFoot> queryFootFlex(SysPageHeadFoot search);
	
	List<SysPageHeadFootVo> queryFootContentByParentId(SysPageHeadFoot search);
	
	/**
	 * Class Name: ISysPageHeadFootService.java
	 * @Description: 根据公司id和page_head_type进行查询
	 * @author xukaiqiang
	 * @date 2016年5月23日 下午12:56:19
	 * @modifier
	 * @modify-date 2016年5月23日 下午12:56:19
	 * @version 1.0
	 * @param sysPageHeadFoot
	 * @return
	 */
	SysPageHeadFoot findEntityByCompanyIdAndType(SysPageHeadFoot sysPageHeadFoot);
	
	/**
	 * 
	 * Class Name: SysPageHeadFootMapper.java
	 * @Description: 查询mobile模块
	 * @author dongshuai
	 * @date 2017年2月17日 下午2:21:16
	 * @modifier
	 * @modify-date 2017年2月17日 下午2:21:16
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<SysPageHeadFoot> queryWapheadByCompanyId(Integer companyId);
	
	SysPageHeadFoot queryWapheadByCompanyIdAndUrlName(SysPageHeadFoot search);
}