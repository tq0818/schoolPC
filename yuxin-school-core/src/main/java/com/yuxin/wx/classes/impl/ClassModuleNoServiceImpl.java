package com.yuxin.wx.classes.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.classes.mapper.ClassModuleNoMapper;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.vo.classes.ClassModuleNoListVo;
import com.yuxin.wx.vo.classes.ClassModuleNoVo;
import com.yuxin.wx.vo.classes.ClassVo;

/**
 * Service Implementation:ClassModuleNo
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class ClassModuleNoServiceImpl extends BaseServiceImpl implements IClassModuleNoService {

	@Autowired
	private ClassModuleNoMapper classModuleNoMapper;
	
	/**
	 * 
	* @Title: saveClassModuleNo
	* @Description: 新增ClassModuleNo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(ClassModuleNo classModuleNo){
		classModuleNoMapper.insert(classModuleNo);
	}
	
	/**
	 * 
	* @Title: batchSaveClassModuleNo 
	* @Description: 批量新增ClassModuleNo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<ClassModuleNo> classModuleNos){
		if(classModuleNos != null && !classModuleNos.isEmpty()){
			classModuleNoMapper.batchInsert(classModuleNos);
		}
	}
	
	/**
	 * 
	* @Title: updateClassModuleNo 
	* @Description: 编辑ClassModuleNo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(ClassModuleNo classModuleNo){
		classModuleNoMapper.update(classModuleNo);
	}
	
	/**
	 * 
	* @Title: deleteClassModuleNoById 
	* @Description: 根据id删除ClassModuleNo
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassModuleNoById(Integer id){
		classModuleNoMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: findListByModule
	* @Description: 根据moduleid 查询 moduleNo 列表
	* @param moduleId
	* @return ClassModuleNo    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	@Override
	public List<ClassModuleNoVo> findListByModule(ClassModuleNo search) {
		return classModuleNoMapper.findListByModule(search);
	}
	
	/**
	 * 
	* @Title: findListByModuleId 
	* @Description: 根据moduleid 查询 moduleNo 列表
	* @param moduleId
	* @return ClassModuleNo    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	@Override
	public List<ClassModuleNoVo> findListByModuleId(Integer moduleId) {
		return classModuleNoMapper.findListModuleId(moduleId);
	}
	
	/**
	 * 
	* @Title: deleteClassModuleNoByIds 
	* @Description: 根据id批量删除ClassModuleNo
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassModuleNoByIds(Integer[] ids){
		classModuleNoMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findClassModuleNoById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public ClassModuleNo findClassModuleNoById(Integer id){
		return classModuleNoMapper.findById(id);
	}
	
	/**
	 * 
	* @Title: findClassModuleNoByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassModuleNo>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<ClassModuleNoListVo> findClassModuleNoListVoByPage(ClassModuleNo search){
		Integer totalRecords = classModuleNoMapper.modulePageCount(search);
		search.setTotalRecords(totalRecords);
		return classModuleNoMapper.pageList(search);
	}
	
	
	@Override
	public List<ClassModuleNo> findClassModuleNoListByCampusId(Integer campusId){
		return classModuleNoMapper.findClassModuleNoListByCampusId(campusId);
	}
	
	@Override
	public ClassModuleNoListVo findModuleNoListVoById(Integer id){
		return classModuleNoMapper.findModuleNoListVoById(id);
	}

	/**
	* @Description: 分页查询
	* @return
	* @return List<ClassModuleNo>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public List<ClassModuleNo> findClassModuleNoByPage(ClassModuleNo search) {
		Integer totalRecords = classModuleNoMapper.pageCount(search);
		search.setTotalRecords(totalRecords);
		return classModuleNoMapper.page(search);
	}
	
	public int findCountByKeys(ClassModuleNo search){
		Integer totalRecords = classModuleNoMapper.pageCount(search);
		return totalRecords;
	}

	/**
	 * 
	* @Title: getCampus_name
	* @Description: 由id获得校区名称
	* @return String    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-28
	* @user by ycl
	 */
	@Override
	public String getCampus_nameById(Integer id) {
		// TODO Auto-generated method stub
		return classModuleNoMapper.getCampus_nameById(id);
	}

	@Override
	public List<String> findClassNoByCompanyId(Map<String,Object> param) {
		// TODO Auto-generated method stub
		return classModuleNoMapper.findClassNoByCompanyId(param);
	}

	@Override
	public List<ClassModuleNo> findByCmId(Integer classModuleId,Integer classTypeId) {
		Map map=new HashMap();
		map.put("classModuleId",classModuleId);
		map.put("classTypeId", classTypeId);
		return classModuleNoMapper.findByCmId(map);
	}

	@Override
	public List<ClassModuleNo> findBySearch(StudentPaySlave paySlave) {
		// TODO Auto-generated method stub
		return classModuleNoMapper.findBySearch(paySlave);
	}

	@Override
	public List<ClassModuleNoListVo> findVoByCmId(Integer id) {
		return classModuleNoMapper.findVoByCmId(id);
	}

	@Override
	public List<ClassModuleNoListVo> findModuleNoListByClassType(
			Integer moduleId) {
		return classModuleNoMapper.queryModuleNoListByClassType(moduleId);
	}

	@Override
	public List<ClassVo> findClassInfoByMap(ClassVo classVo) {
		// TODO Auto-generated method stub
		return classModuleNoMapper.findClassInfoByMap(classVo);
	}

	@Override
	public Integer findCountByMap(ClassVo classVo) {
		// TODO Auto-generated method stub
		return classModuleNoMapper.findCountByMap(classVo);
	}

	@Override
	public List<ClassModuleNo> findByItem(ClassModuleNo classModuleNo) {
		// TODO Auto-generated method stub
		return classModuleNoMapper.findByItem(classModuleNo);
	}

	@Override
	public List<ClassModuleNo> queryModuleNoByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return classModuleNoMapper.querymoduleNosByCompanyId(companyId);
	}

	@Override
	public List<ClassModuleNo> queryClassModuleNoById(Integer id) {
		// TODO Auto-generated method stub
		return classModuleNoMapper.queryClassModuleNoById(id);
	}
	
	
	@Override
	public List<ClassModuleNo> findModuleNoOnSaleByModuleId(Integer id) {
		return classModuleNoMapper.findModuleNoOnSaleByModuleId(id);
	}

	@Override
	public List<Integer> findClassModuleNoIdsByModuleId(Integer id) {
		return classModuleNoMapper.findClassModuleNoIdsByModuleId(id);
	}
}
