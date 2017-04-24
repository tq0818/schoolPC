package com.yuxin.wx.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigTermService;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.system.SysConfigTerm;
import com.yuxin.wx.system.mapper.SysConfigSchoolMapper;
import com.yuxin.wx.system.mapper.SysConfigTermMapper;
import com.yuxin.wx.vo.system.ConfigTermVo;

/**
 * Service Implementation:SysConfigTerm
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class SysConfigTermServiceImpl extends BaseServiceImpl implements ISysConfigTermService {

	@Autowired
	private SysConfigTermMapper sysConfigTermMapper;
	
	@Autowired
	private SysConfigSchoolMapper sysConfigSchoolMapper;
	
	/**
	 * 
	* @Title: saveSysConfigTerm
	* @Description: 新增SysConfigTerm
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(SysConfigTerm sysConfigTerm){
		sysConfigTerm.setDelFlag(SysConfigConstant.NO_DELTE_FLAG);
		// 如果是所有分校，则每个分校插入一条数据
		if (sysConfigTerm.getSchoolId() == -1) {
			for (SysConfigSchool school : sysConfigSchoolMapper.queryAll(sysConfigTerm.getCompanyId())){
				sysConfigTerm.setId(null);
				sysConfigTerm.setSchoolId(school.getId());
				sysConfigTerm.setSchoolName(school.getSchoolName());
				sysConfigTermMapper.insert(sysConfigTerm);
			}
		} else {
			sysConfigTermMapper.insert(sysConfigTerm);
		}
	}
	
	/**
	 * 
	* @Title: batchSaveSysConfigTerm 
	* @Description: 批量新增SysConfigTerm
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<SysConfigTerm> sysConfigTerms){
		if(sysConfigTerms != null && !sysConfigTerms.isEmpty()){
			sysConfigTermMapper.batchInsert(sysConfigTerms);
		}
	}
	
	/**
	 * 
	* @Title: updateSysConfigTerm 
	* @Description: 编辑SysConfigTerm
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(SysConfigTerm sysConfigTerm){
		sysConfigTermMapper.update(sysConfigTerm);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigTermById 
	* @Description: 根据id删除SysConfigTerm
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigTermById(Integer id){
		sysConfigTermMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigTermByIds 
	* @Description: 根据id批量删除SysConfigTerm
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigTermByIds(Integer[] ids){
		sysConfigTermMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findSysConfigTermById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public SysConfigTerm findSysConfigTermById(Integer id){
		return sysConfigTermMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findSysConfigTermById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by sunxb
	 */
	@Override
	public List<SysConfigTerm> findSysConfigTermList(Integer itemOneId) {
		return sysConfigTermMapper.findList(itemOneId);
	}
	
	/**
	 * 
	* @Title: findSysConfigTermById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by sunxb
	 */
	@Override
	public List<SysConfigTerm> findSysConfigTermList(SysConfigTerm search) {
		return sysConfigTermMapper.findList(search);
	}
	
	/**
	 * 
	* @Title: findSysConfigTermByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigTerm>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<ConfigTermVo> findSysConfigTermByPage(SysConfigTerm search){
		Integer totalRecords = sysConfigTermMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return sysConfigTermMapper.page(search);
	}

	/**
	 * 根据条件查询考期，不分页
	 */
	@Override
	public List<SysConfigTerm> findTerm(SysConfigTerm search) {
		return sysConfigTermMapper.queryTerm(search);
	}

	@Override
	public List<SysConfigTerm> getTermByItemOne(Integer itemOneId,Integer companyId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("itemOneId", itemOneId);
		map.put("companyId", companyId);
		return sysConfigTermMapper.getTermByItemOne(map);
	}

	@Override
	public List<ConfigTermVo> findByCompandyId(SysConfigTerm configTerm) {
		// TODO Auto-generated method stub
		return sysConfigTermMapper.findByCompandyId(configTerm);
	}

	@Override
	public List<SysConfigTerm> findTermByOneItemId(Integer oneItemId) {
		// TODO Auto-generated method stub
		return sysConfigTermMapper.findTermByOneItemId(oneItemId);
	}
	
	@Override
	public List<SysConfigTerm> findTermByName(SysConfigTerm search){
		return sysConfigTermMapper.findByName(search);
	}
}
