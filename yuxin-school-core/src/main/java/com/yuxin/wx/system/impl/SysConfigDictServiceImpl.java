package com.yuxin.wx.system.impl;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.user.mapper.UsersAreaRelationMapper;
import com.yuxin.wx.vo.user.UsersAreaRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.classes.EduMasterClass;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.system.mapper.SysConfigDictMapper;

/**
 * Service Implementation:SysConfigDict
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class SysConfigDictServiceImpl extends BaseServiceImpl implements ISysConfigDictService {

	@Autowired
	private SysConfigDictMapper sysConfigDictMapper;
	@Autowired
	private UsersAreaRelationMapper usersAreaRelationMapper;

	/**
	 * 
	* @Title: saveSysConfigDict
	* @Description: 新增SysConfigDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(SysConfigDict sysConfigDict){
		sysConfigDictMapper.insert(sysConfigDict);
	}
	@Override
	public void addEduStepSchool(SysConfigDict sysConfigDict){
		sysConfigDictMapper.addEduStepSchool(sysConfigDict);
	}
	
	/**
	 * 
	* @Title: batchSaveSysConfigDict 
	* @Description: 批量新增SysConfigDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<SysConfigDict> sysConfigDicts){
		if(sysConfigDicts != null && !sysConfigDicts.isEmpty()){
			sysConfigDictMapper.batchInsert(sysConfigDicts);
		}
	}
	
	/**
	 * 
	* @Title: updateSysConfigDict 
	* @Description: 编辑SysConfigDict
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(SysConfigDict sysConfigDict){
		sysConfigDictMapper.update(sysConfigDict);
	}
	@Override
	public void updateUsersAreaRelation(UsersAreaRelation ual){
		sysConfigDictMapper.updateUsersAreaRelation(ual);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigDictById 
	* @Description: 根据id删除SysConfigDict
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigDictById(Integer id){
		sysConfigDictMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteSysConfigDictByIds 
	* @Description: 根据id批量删除SysConfigDict
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteSysConfigDictByIds(Integer[] ids){
		sysConfigDictMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findSysConfigDictById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public SysConfigDict findSysConfigDictById(Integer id){
		return sysConfigDictMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findSysConfigDictById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public SysConfigDict findSysConfigDictByCode(SysConfigDict dict){
		return sysConfigDictMapper.findByCode(dict);
	}
	
	/**
	 * 
	* @Title: findSysConfigDictById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<SysConfigDict> findSysConfigDictList(){
		return sysConfigDictMapper.queryAll();
	}
	
	/**
	 * 
	* @Title: findSysConfigDictByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysConfigDict>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<SysConfigDict> findSysConfigDictByPage(SysConfigDict search){
		Integer totalRecords = sysConfigDictMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return sysConfigDictMapper.page(search);
	}

	@Override
	public List<SysConfigDict> findDictByClassroom() {
		// TODO Auto-generated method stub
		return sysConfigDictMapper.findDictByClassroom();
	}

	@Override
	public List<SysConfigDict> findByDicCode(String code) {
		return sysConfigDictMapper.findByDicCode(code);
	}

	@Override
	public List<SysConfigDict> findConfigDictList() {
		return sysConfigDictMapper.queryConfigDictList();
	}
	
	@Override
	public List<SysConfigDict> findAll() {
		return sysConfigDictMapper.queryAll();
	}

	@Override
	public List<SysConfigDict> findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return sysConfigDictMapper.findByCompanyId(companyId);
	}

	@Override
	public List<SysConfigDict> queryConfigDictListByDictCode(
			SysConfigDict sysConfigDict) {
		// TODO Auto-generated method stub
		return sysConfigDictMapper.queryConfigDictListByDictCode(sysConfigDict);
	}
	@Override
	public List<SysConfigDict> querySchoolByArea( SysConfigDict sysConfigDict) {
		// TODO Auto-generated method stub
		return sysConfigDictMapper.querySchoolByArea(sysConfigDict);
	}
	@Override
	public List<SysConfigDict> queryAreaBySchool( String str) {
		// TODO Auto-generated method stub
		return sysConfigDictMapper.queryAreaBySchool(str);
	}
	@Override
	public SysConfigDict queryConfigDictValue(SysConfigDict sysConfigDict){
		return sysConfigDictMapper.queryConfigDictValue(sysConfigDict);
	}

	@Override
	public List<SysConfigDict> querySchoolListByStepCode(SysConfigDict areaDict) {
		return sysConfigDictMapper.querySchoolListByStepCode(areaDict);
	}

	@Override
	public List<SysConfigDict> findSchoolBySchoolType(Map<String, Object> map) {
		return sysConfigDictMapper.findSchoolBySchoolType(map);
	}

    @Override
    public List<SysConfigDict> querSysConfigDictList(Map<String, Object> map) {
        return sysConfigDictMapper.querSysConfigDictList(map);
    }
    @Override
    public Integer querSysConfigDictCount(Integer companyId) {
    	return sysConfigDictMapper.querSysConfigDictCount(companyId);
    }
    @Override
    public Integer queryAllSchoolsCount(SysConfigDict areaDict) {
    	return sysConfigDictMapper.queryAllSchoolsCount(areaDict);
    }

	@Override
    public List<EduMasterClass> queryEduMasterClass(EduMasterClass ems) {
	    return sysConfigDictMapper.queryEduMasterClass(ems);
    }

	@Override
	public List<SysConfigDict> queryAllSchools(SysConfigDict search) {
		return sysConfigDictMapper.queryAllSchools(search);
	}
	/**
	 * 修改学校性质
	 */
	@Override
	public void updateSchoolProperty(SysConfigDict sysConfigDict) {
		sysConfigDictMapper.updateSchoolProperty(sysConfigDict);
	}

	@Override
	public Integer findId(SysConfigDict areaDict) {
		return sysConfigDictMapper.findId(areaDict);
	}
	@Override
	public Integer checkCodeAndName(SysConfigDict areaDict) {
		return sysConfigDictMapper.checkCodeAndName(areaDict);
	}

	@Override
	public void updateOthserSchoolProperty(SysConfigDict sysConfigDict) {
		sysConfigDictMapper.updateOthserSchoolProperty(sysConfigDict);
		
	}
	@Override
	public List<SysConfigDict> findNameAndComId(Integer id) {
		return sysConfigDictMapper.findNameAndComId(id);
	}

	@Override
	public UsersAreaRelation selectUserByUserId(Integer userId){
		return usersAreaRelationMapper.selectUserByUserId(userId);
	}

	

}
