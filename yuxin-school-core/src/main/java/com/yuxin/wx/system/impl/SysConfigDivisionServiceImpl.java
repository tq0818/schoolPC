package com.yuxin.wx.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysConfigDivisionService;
import com.yuxin.wx.model.system.SysConfigDivision;
import com.yuxin.wx.system.mapper.SysConfigDivisionMapper;
import com.yuxin.wx.vo.company.ProvinceVo;

/**
 * Service Implementation:SysConfigDivision
 * @author wang.zx
 * @date 2016-7-14
 */
@Service
@Transactional
public class SysConfigDivisionServiceImpl extends BaseServiceImpl implements ISysConfigDivisionService {

	@Autowired
	private SysConfigDivisionMapper sysConfigDivisionMapper;
	
	/**
	 * 
	* @Title: saveSysConfigDivision
	* @Description: 新增SysConfigDivision
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by chopin
	 */
	@Override
	public void insert(SysConfigDivision entity){
		sysConfigDivisionMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysConfigDivision 
	* @Description: 批量新增SysConfigDivision
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysConfigDivision> T){
		sysConfigDivisionMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateSysConfigDivision 
	* @Description: 编辑SysConfigDivision
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by chopin
	 */
	@Override
	public void update(SysConfigDivision T){
		sysConfigDivisionMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigDivisionById 
	* @Description: 根据id删除SysConfigDivision
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by chopin
	 */
	 @Override
	public void deleteSysConfigDivisionById(Integer id){
		sysConfigDivisionMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysConfigDivisionByIds 
	* @Description: 根据id批量删除SysConfigDivision
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by chopin
	 */
	@Override
	public void deleteSysConfigDivisionByIds(Integer[] ids){
		sysConfigDivisionMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysConfigDivisionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by chopin
	 */
	@Override
	public SysConfigDivision findSysConfigDivisionById(Integer id){
		return sysConfigDivisionMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysConfigDivisionByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigDivision>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-7-14
	* @user by chopin
	 */
	@Override
	public List<SysConfigDivision> findSysConfigDivisionByPage(SysConfigDivision search){
		return sysConfigDivisionMapper.page(search);
	}

	@Override
	public List<ProvinceVo> queryAlls() {
		// TODO Auto-generated method stub
		return sysConfigDivisionMapper.queryAlls();
	}

	@Override
	public String findXcode(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return sysConfigDivisionMapper.findXcode(param);
	}

	@Override
	public SysConfigDivision findEntity(String code) {
		// TODO Auto-generated method stub
		return sysConfigDivisionMapper.findEntity(code);
	}

	@Override
	public SysConfigDivision findParentEntity(Integer parentId) {
		// TODO Auto-generated method stub
		return sysConfigDivisionMapper.findParentEntity(parentId);
	}

	@Override
	public List<SysConfigDivision> secLiandong(Integer parentId) {
		//return null;
		List<SysConfigDivision> list = null;
		if(parentId==1||parentId==20||parentId==860||parentId==2462){
			//List<SysConfigDivision> secLiandong = sysConfigDivisionMapper.secLiandong(parentId);
			list = sysConfigDivisionMapper.secLiandong(parentId+1);
			return list;
		}
		list = sysConfigDivisionMapper.secLiandong(parentId);
		return list;
	}

	@Override
	public List<SysConfigDivision> firstLiandong() {
		return sysConfigDivisionMapper.firstLiandong();
	};
}
