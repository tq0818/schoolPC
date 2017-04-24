package com.yuxin.wx.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysPageHeadFootService;
import com.yuxin.wx.model.system.SysPageHeadFoot;
import com.yuxin.wx.system.mapper.SysPageHeadFootMapper;
import com.yuxin.wx.vo.system.SysPageHeadFootVo;

/**
 * Service Implementation:SysPageHeadFoot
 * @author chopin
 * @date 2015-4-11
 */
@Service
@Transactional
public class SysPageHeadFootServiceImpl extends BaseServiceImpl implements ISysPageHeadFootService{

	@Autowired
	private SysPageHeadFootMapper sysPageHeadFootMapper;
	
	/**
	 * 
	* @Title: saveSysPageHeadFoot
	* @Description: 新增SysPageHeadFoot
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	@Override
	public void insert(SysPageHeadFoot entity){
		sysPageHeadFootMapper.insert(entity);
	};
	
	
	/**
	 * 
	* @Title: deleteSysPageHeadFootById 
	* @Description: 根据id删除SysPageHeadFoot
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	 @Override
	public void deleteSysPageHeadFootById(Integer id){
		sysPageHeadFootMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysPageHeadFootByIds 
	* @Description: 根据id批量删除SysPageHeadFoot
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	@Override
	public void deleteSysPageHeadFootByIds(Integer[] ids){
		sysPageHeadFootMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysPageHeadFootById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	@Override
	public SysPageHeadFoot findSysPageHeadFootById(Integer id){
		return sysPageHeadFootMapper.findById(id);
	};
	
	@Override
	public void batchInsert(List<SysPageHeadFoot> list) {
		sysPageHeadFootMapper.batchInsert(list);
	}

	/**
	 * 
	* @Title: saveSysPageHeadFoot
	* @Description: 修改SysPageHeadFoot
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	@Override
	public void update(SysPageHeadFoot entity) {
		sysPageHeadFootMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: findSysPageHeadFootByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysPageHeadFoot>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-11
	* @user by chopin
	 */
	@Override
	public List<SysPageHeadFoot> findSysPageHeadFootByPage(SysPageHeadFoot search){
		return sysPageHeadFootMapper.page(search);
	}


	/**
	 * 
	 * Class Name: SysPageHeadFootServiceImpl.java
	 * @Description: 条件查询首页页头相关信息
	 * @author zhang.zx
	 * @date 2015年4月13日 上午10:00:14
	 * @modifier
	 * @modify-date 2015年4月13日 上午10:00:14
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@Override
	public List<SysPageHeadFootVo> findSysPageHeadFoot(SysPageHeadFootVo search) {
		return sysPageHeadFootMapper.querySysPageHeadFootList(search);
	}

	/**
	 * 
	 * Class Name: SysPageHeadFootServiceImpl.java
	 * @Description: 查询二级标题
	 * @author zhang.zx
	 * @date 2015年4月13日 上午10:00:14
	 * @modifier
	 * @modify-date 2015年4月13日 上午10:00:14
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@Override
	public List<SysPageHeadFootVo> queryTwoSysPagerList() {
		return sysPageHeadFootMapper.queryTwoSysPagerList();
	}


	@Override
	public List<SysPageHeadFootVo> findAll() {
		return sysPageHeadFootMapper.selectAll();
	}


	@Override
	public void insertHeadTitle(SysPageHeadFoot sysPageHeadFoot) {
		sysPageHeadFootMapper.insertHeadTitle(sysPageHeadFoot);
	}

	/**
	 * 
	 * Class Name: SysPageHeadFootServiceImpl.java
	 * @Description: 查询公司标题可用数量
	 * @author zhang.zx
	 * @date 2015年4月13日 上午10:00:14
	 * @modifier
	 * @modify-date 2015年4月13日 上午10:00:14
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@Override
	public Integer findIsUseHeadTitle(SysPageHeadFoot sysPageHeadFoot) {
		return sysPageHeadFootMapper.queryIsUseHeadTitle(sysPageHeadFoot);
	}


	@Override
	public List<SysPageHeadFoot> findByConpanyId(int id) {
		
		return sysPageHeadFootMapper.findByConpanyId(id);
	}


	@Override
	public void changeStatus(SysPageHeadFoot entity) {
		sysPageHeadFootMapper.changStatus(entity);
	}

	@Override
	public void updateByNameAndCpId(Map<String, String> map) {
		sysPageHeadFootMapper.updateByNameAndCpId(map);
	}


	@Override
	public String selectByCpIdAndName(Map<String, String> map) {
		return sysPageHeadFootMapper.selectByCpIdAndName(map);
	}


	@Override
	public List<SysPageHeadFoot> queryHeadIsUse(SysPageHeadFoot sysPageHeadFoot) {
		return sysPageHeadFootMapper.queryHeadIsUse(sysPageHeadFoot);
	}


	@Override
	public int selectHeadCount(Integer companyId) {
		
		return sysPageHeadFootMapper.selectHeadCount(companyId);
	}


	@Override
	public int seleFootCount(Integer companyId) {
		return sysPageHeadFootMapper.selectFootCount(companyId);
	}


	@Override
	public List<SysPageHeadFoot> queryAllByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return sysPageHeadFootMapper.queryAllByCompanyId(companyId);
	}


	@Override
	public SysPageHeadFoot findHeadFoot(SysPageHeadFoot sphf) {
		// TODO Auto-generated method stub
		return sysPageHeadFootMapper.findHeadFoot(sphf);
	}


	@Override
	public List<SysPageHeadFootVo> queryFootContentsByConfigId(
			SysPageHeadFoot search) {
		// TODO Auto-generated method stub
		return sysPageHeadFootMapper.queryFootContentsByConfigId(search);
	}


	@Override
	public List<SysPageHeadFoot> queryFootFlex(SysPageHeadFoot search) {
		// TODO Auto-generated method stub
		return sysPageHeadFootMapper.queryFootFlex(search);
	}


	@Override
	public List<SysPageHeadFootVo> queryFootContentByParentId(
			SysPageHeadFoot search) {
		// TODO Auto-generated method stub
		return sysPageHeadFootMapper.queryFootContentByParentId(search);
	}

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
	@Override
	public SysPageHeadFoot findEntityByCompanyIdAndType(SysPageHeadFoot sysPageHeadFoot) {
		return sysPageHeadFootMapper.findEntityByCompanyIdAndType(sysPageHeadFoot);
	}


	@Override
	public List<SysPageHeadFoot> queryWapheadByCompanyId(Integer companyId) {
		return sysPageHeadFootMapper.queryWapheadByCompanyId(companyId);
	}


	@Override
	public SysPageHeadFoot queryWapheadByCompanyIdAndUrlName(SysPageHeadFoot search) {
		return sysPageHeadFootMapper.queryWapheadByCompanyIdAndUrlName(search);
	}

}
