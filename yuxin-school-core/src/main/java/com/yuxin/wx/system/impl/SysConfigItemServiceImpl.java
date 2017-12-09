package com.yuxin.wx.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysSchoolItemRelation;
import com.yuxin.wx.system.mapper.SysConfigItemMapper;

/**
 * Service Implementation:SysConfigItem
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class SysConfigItemServiceImpl extends BaseServiceImpl implements ISysConfigItemService {

	@Autowired
	private SysConfigItemMapper sysConfigItemMapper;
	
	/**
	 * 
	* @Title: saveSysConfigItem
	* @Description: 新增SysConfigItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(SysConfigItem sysConfigItem){
		sysConfigItemMapper.insert(sysConfigItem);
	}
	
	/**
	 * 
	* @Title: batchSaveSysConfigItem 
	* @Description: 批量新增SysConfigItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<SysConfigItem> sysConfigItems){
		if(sysConfigItems != null && !sysConfigItems.isEmpty()){
			sysConfigItemMapper.batchInsert(sysConfigItems);
		}
	}
	
	/**
	 * 
	* @Title: updateSysConfigItem 
	* @Description: 编辑SysConfigItem
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(SysConfigItem sysConfigItem){
		sysConfigItemMapper.update(sysConfigItem);
	}
	
	@Override
	public void update(List<SysConfigItem> sysConfigItems){
		for (int i = 0; i < sysConfigItems.size(); i++) {
			sysConfigItemMapper.update(sysConfigItems.get(i));
		}
	}
	
	/**
	 * 
	* @Title: deleteSysConfigItemById 
	* @Description: 根据id删除SysConfigItem
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigItemById(Integer id){
		sysConfigItemMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigItemByIds 
	* @Description: 根据id批量删除SysConfigItem
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigItemByIds(Integer[] ids){
		sysConfigItemMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findSysConfigItemById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public SysConfigItem findSysConfigItemById(Integer id){
		return sysConfigItemMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findSysConfigItemById 
	* @Description: 查询一二级项目
	* @param itemType
	* @param pid  二级项目的父id
	* @param companyId 公司Id
	* @return List<SysConfigItem>   
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	@Override
	public List<SysConfigItem> findSysConfigItemByPid(String itemType,Integer pid,Integer companyId,Integer schoolId) {
		if("1".equals(itemType)){
			return (List<SysConfigItem>)sysConfigItemMapper.findByItemOne(""+companyId,""+schoolId);
			
		}else if("2".equals(itemType)){
			HashMap<String,String> map=new HashMap<String,String>();
			map.put("pid", ""+pid);
			map.put("companyId",""+companyId);
			map.put("schoolId", ""+schoolId);
			return (List<SysConfigItem>)sysConfigItemMapper.findByItemTwo(map);
		}else{
			return null;
		}
		
	}

	/**
	 * 根据条件查询项目，不分页
	 * @param search
	 * @return
	 */
	@Override
	public List<SysConfigItem> findItem(SysConfigItem search) {
		//company_id 和 school_id 不能为空
		if(search.getCompanyId()==null || search.getSchoolId()==null){
			return null;
		}
		return sysConfigItemMapper.selectItem(search);
	}

	@Override
	public Integer findProjectBySchoolId(Integer schoolId) {
		return sysConfigItemMapper.findProjectBySchoolId(schoolId);
	}

	@Override
	public List<SysConfigItem> findItemByCompanyId(Integer itemType, Integer companyId) {
		return sysConfigItemMapper.findItemByCompanyId(itemType, companyId);
	}

	@Override
	public List<SysConfigItem> findTwoByOneId(Integer oneItemId) {
		return sysConfigItemMapper.findTwoByOneId(oneItemId);
	}

	@Override
	public void insertRelation(Map<String, Object> param) {
		sysConfigItemMapper.insertRelation(param);
	}

	@Override
	public Integer findUnquieItem(Map<String, Object> param) {
		return sysConfigItemMapper.findUnquieItem(param);
	}

	@Override
	public void updateTwoByOne(SysConfigItem sci) {
		sysConfigItemMapper.updateTwoByOne(sci);
	}

	@Override
	public SysSchoolItemRelation findExist(Map<String, Object> params) {
		return sysConfigItemMapper.findExist(params);
	}

	@Override
	public void updateRelation(Map<String, Object> params) {
		sysConfigItemMapper.updateRelation(params);
	}

	@Override
	public List<SysConfigItem> findItemBySchoolCompanyId(
			Map<String, Object> param) {
		return sysConfigItemMapper.findItemBySchoolCompanyId(param);
	}

	@Override
	public int selectCount(Integer companyId) {
		return sysConfigItemMapper.selectCount(companyId);
	}

	@Override
	public List<SysConfigItem> findStatus(SysConfigItem item) {
		return sysConfigItemMapper.findStatus(item);
	}

	@Override
	public List<SysConfigItem> findNotInByItemId(Map<String,Object> param) {
		return sysConfigItemMapper.findNotInByItemId(param);
	}

	@Override
	public Integer findUnquieItemByUpdate(Map<String, Object> param) {
		return sysConfigItemMapper.findUnquieItemByUpdate(param);
	}

	@Override
	public List<SysConfigItem> selectSecondItem(SysConfigItem item) {
		return sysConfigItemMapper.selectSecondItem(item);
	}

	@Override
	public SysConfigItem findDelNullByName(SysConfigItem item) {
		return sysConfigItemMapper.findDelNullByName(item);
	}

	@Override
	public List<SysSchoolItemRelation> findUseByItemId(Map<String, Object> param) {
		return sysConfigItemMapper.findUseByItemId(param);
	}
	
	@Override
	public List<SysConfigItem> findItemByIds(List<Integer> list) {
		return sysConfigItemMapper.findItemByIds(list);
	}

	@Override
	public List<SysConfigItem> findByParentCode(SysConfigItem item) {
		return sysConfigItemMapper.findByParentCode(item);
	}

	@Override
    public Integer findschooIdByCompanyId(Integer companyId) {
		Integer id=sysConfigItemMapper.findschooIdByCompanyId(companyId);
	    return id;
    }


}
