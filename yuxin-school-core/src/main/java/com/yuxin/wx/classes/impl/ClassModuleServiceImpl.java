package com.yuxin.wx.classes.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.classes.mapper.ClassModuleMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.vo.classes.ClassModuleVo;
import com.yuxin.wx.vo.classes.ClassVideoModuleVo;
import com.yuxin.wx.vo.classes.ClassmoduleNoOnsaleVo;
import com.yuxin.wx.vo.classes.ModuleLessonVo;

/**
 * Service Implementation:ClassModule
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class ClassModuleServiceImpl extends BaseServiceImpl implements IClassModuleService {

	@Autowired
	private ClassModuleMapper classModuleMapper;
	
	/**
	 * 
	* @Title: saveClassModule
	* @Description: 新增ClassModule
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(ClassModule classModule){
		classModuleMapper.insert(classModule);
	}
	
	/**
	 * 
	* @Title: batchSaveClassModule 
	* @Description: 批量新增ClassModule
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<ClassModule> classModules){
		if(classModules != null && !classModules.isEmpty()){
			classModuleMapper.batchInsert(classModules);
		}
	}
	
	/**
	 * 
	* @Title: updateClassModule 
	* @Description: 编辑ClassModule
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(ClassModule classModule){
		classModuleMapper.update(classModule);
	}
	
	/**
	 * 
	* @Title: deleteClassModuleById 
	* @Description: 根据id删除ClassModule
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassModuleById(Integer id){
		classModuleMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteClassModuleByIds 
	* @Description: 根据id批量删除ClassModule
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassModuleByIds(Integer[] ids){
		classModuleMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findClassModuleById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public ClassModule findClassModuleById(Integer id){
		return classModuleMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findClassModuleByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassModule>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<ClassModule> findClassModuleByPage(ClassModule search){
		Integer totalRecords = classModuleMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return classModuleMapper.page(search);
	}
	
	/**
	 * 
	* @Title: queryClassModuleByKeys
	* @Description:  根据条件查询模块
	* @return
	* @return List<ClassModule>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-7
	* @user by zhangbo
	 */
	@Override
	public List<ClassModuleVo> queryClassModuleByKeys(ClassModule classModule){
		List<ClassModuleVo> list = classModuleMapper.queryClassModuleByKeys(classModule);
		return list;
	}
	
	@Override
	public List<ClassModule> findModulesByModule(ClassModule classModule){
		List<ClassModule> list = classModuleMapper.findModulesByModule(classModule);
		return list;
	}
	
	@Override
	public ClassModuleVo queryOneVoById(Integer moduleId) {
		ClassModuleVo moduleVo=classModuleMapper.queryOneVoById(moduleId);
		return moduleVo;
	}

	@Override
	public int queryClassModuleByKeysCount(ClassModule classModule) {
		return classModuleMapper.queryClassModuleByKeysCount(classModule);
	}

	@Override
	public List<ClassModule> findModulesBySecondItemId(Integer secondItemId) {
		return classModuleMapper.findModulesBySecondItemId(secondItemId);
	}

	@Override
	public List<ClassModule> findModulesByClassTypeId(Integer classTypeId) {
		return classModuleMapper.findModulesByClassTypeId(classTypeId);
	}

	@Override
	public Integer checkUpdate(Integer moduleId) {
		// TODO Auto-generated method stub
		return classModuleMapper.checkUpdate(moduleId);
	}

	@Override
	public Integer checkStop(Integer moduleId) {
		// TODO Auto-generated method stub
		return classModuleMapper.checkStop(moduleId);
	}

	@Override
	public List<ClassModule> findByClassTypeId(Integer id) {
		return classModuleMapper.findByClassTypeId(id);
	}

	@Override
	public List<ClassModuleVo> findAllClassModule(ClassModule classModule) {
		return classModuleMapper.queryClassModules(classModule);
	}

	@Override
	public List<ClassModuleVo> queryAllModules(String classTypeId) {
		return classModuleMapper.queryAllModules(classTypeId);
	}

	@Override
	public List<ClassModule> findByPayMasterId(StudentPaySlave paySlave) {
		return classModuleMapper.findByPayMasterId(paySlave);
	}

	@Override
	public List<ModuleLessonVo> findBySchoolId(ModuleLessonVo mlv) {
		// TODO Auto-generated method stub
		return classModuleMapper.findBySchoolId(mlv);
	}

	@Override
	public PageFinder<ClassModuleVo> querySearchPage(ClassModule module) {
		module.setPageSize(5);
		List<ClassModuleVo> classModuleVos = classModuleMapper.queryList(module);
		Integer count = classModuleMapper.queryCount(module);
		PageFinder<ClassModuleVo> pageFinder = new PageFinder<ClassModuleVo>(module.getPage(), module.getPageSize(), count, classModuleVos);
		return pageFinder;
	}

	@Override
	public void changeStatus(ClassModule module) {
		classModuleMapper.changeStatus(module);
		
	}

	@Override
	public List<ClassModule> findByName(String name,Integer companyId) {
		ClassModule module = new ClassModule();
		module.setName(name);
		module.setCompanyId(companyId);
		return classModuleMapper.findByName(module);
	}

	@Override
	public ClassModuleVo findClassModuleVoById(Integer id) {
		return classModuleMapper.findClassModuleVoById(id);
	}


	@Override
	public Integer findCountBySchoolId(ModuleLessonVo mlv) {
		return classModuleMapper.findCountBySchoolId(mlv);
	}

	@Override
	public List<ClassModule> findModuleByIds(List<String> ids) {
		List<Integer> list = new ArrayList<Integer>();
		Set<Integer> set = new HashSet<Integer>() ;
		//将String，转换为Int
		if(ids != null && ids.size() > 0){
			for(String str : ids){
				set.add(Integer.parseInt(str));
			}
		}
		if(set.size() > 0){
			Iterator<Integer> it = set.iterator();  
			while (it.hasNext()) {  
			  list.add(it.next());
			}
		}
		
		return  classModuleMapper.findModuleByIds(list);
	}

	@Override
	public List<ClassModule> findClassModules(ClassModuleNo moduleNo) {
		return classModuleMapper.findClassModules(moduleNo);
	}

	@Override
	public Integer isUse(Integer id, Integer companyId) {
		Map< String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		map.put("companyId", companyId);
		return classModuleMapper.isUse(map);
	}

	@Override
	public ClassModule queryModuleByName(ClassModule c) {
		return classModuleMapper.queryModuleByName(c);
	}

	@Override
	public void insertClassModuleOnsale(ClassmoduleNoOnsaleVo search) {
		// TODO Auto-generated method stub
		classModuleMapper.insertClassModuleOnsale(search);
	}
	
	@Override
	public List<ClassmoduleNoOnsaleVo> queryModuleNoByClasstypeId(Integer classTypeId) {
		// TODO Auto-generated method stub
		return classModuleMapper.queryModuleNoByClassTypeId(classTypeId);
	}

	@Override
	public List<ClassmoduleNoOnsaleVo> queryModuleNoByModuleId(Integer moduleId) {
		// TODO Auto-generated method stub
		return classModuleMapper.queryModuleNoByModuleId(moduleId);
	}

	@Override
	public void deleteClassModuleOnsaleById(ClassmoduleNoOnsaleVo c) {
		// TODO Auto-generated method stub
		classModuleMapper.deleteClassModuleOnsaleById(c);
	}

	@Override
	public List<ClassVideoModuleVo> queryVideoModule(ClassVideoModuleVo vc) {
		// TODO Auto-generated method stub
		return classModuleMapper.queryVideoModule(vc);
	}

	@Override
	public ClassModule queryModuleByClasstypeId(Integer classTypeId) {
		// TODO Auto-generated method stub
		return classModuleMapper.queryModuleByClasstypeId(classTypeId);
	}

	@Override
	public List<ClassModule> queryModulesByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return classModuleMapper.queryModulesByCompanyId(companyId);
	}

	@Override
	public void insertClassModuleOnsale(List<ClassmoduleNoOnsaleVo> search) {
		for(ClassmoduleNoOnsaleVo cmos:search){
			ClassmoduleNoOnsaleVo c=classModuleMapper.queryModuleNoIsExist(cmos);
			if(c==null){
				classModuleMapper.insertClassModuleOnsale(cmos);
			}else{
				classModuleMapper.updateClassModuleOnsale(cmos);
			}
			
		}
	}
	
	@Override
	public Integer calculationHourByClassType(Integer classTypeId) {
		return classModuleMapper.calculationHourByClassType(classTypeId);
	}

	@Override
	public ClassModule findCompanyIdByClassNo(String roomId) {
		// TODO Auto-generated method stub
		return classModuleMapper.findCompanyIdByClassNo(roomId);
	}

	@Override
	public void updateModelByItem(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classModuleMapper.updateModelByItem(param);
	}
	
}
