package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.system.ISysLogStudentOperationService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysLogStudentOperation;
import com.yuxin.wx.system.mapper.SysLogStudentOperationMapper;
import com.yuxin.wx.vo.system.SysLogStudentOperationVo;

/**
 * Service Implementation:SysLogStudentOperation
 * @author wang.zx
 * @date 2017-2-14
 */
@Service
@Transactional
public class SysLogStudentOperationServiceImpl extends BaseServiceImpl implements ISysLogStudentOperationService {

	@Autowired
	private SysLogStudentOperationMapper sysLogStudentOperationMapper;
	
	/**
	 * 
	* @Title: saveSysLogStudentOperation
	* @Description: 新增SysLogStudentOperation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by chopin
	 */
	@Override
	public void insert(SysLogStudentOperation entity){
		sysLogStudentOperationMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysLogStudentOperation 
	* @Description: 批量新增SysLogStudentOperation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysLogStudentOperation> T){
		sysLogStudentOperationMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateSysLogStudentOperation 
	* @Description: 编辑SysLogStudentOperation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by chopin
	 */
	@Override
	public void update(SysLogStudentOperation T){
		sysLogStudentOperationMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteSysLogStudentOperationById 
	* @Description: 根据id删除SysLogStudentOperation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by chopin
	 */
	 @Override
	public void deleteSysLogStudentOperationById(Integer id){
		sysLogStudentOperationMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysLogStudentOperationByIds 
	* @Description: 根据id批量删除SysLogStudentOperation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by chopin
	 */
	@Override
	public void deleteSysLogStudentOperationByIds(Integer[] ids){
		sysLogStudentOperationMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysLogStudentOperationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by chopin
	 */
	@Override
	public SysLogStudentOperation findSysLogStudentOperationById(Integer id){
		return sysLogStudentOperationMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysLogStudentOperationByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysLogStudentOperation>    返回类型 
	* @throws 
	* @exception 
	* @date 2017-2-14
	* @user by chopin
	 */
	@Override
	public List<SysLogStudentOperation> findSysLogStudentOperationByPage(SysLogStudentOperation search){
		return sysLogStudentOperationMapper.page(search);
	}

	@Override
	public SysLogStudentOperation selectOne(SysLogStudentOperation slso,
			Integer sort) {
		// TODO Auto-generated method stub
		if(sort == null || sort.equals(0)){
			return sysLogStudentOperationMapper.selectOneAsc(slso);
		}else{
			return sysLogStudentOperationMapper.selectOneDesc(slso);
		}
	}

	@Override
	public PageFinder<SysLogStudentOperation> selectList(
			SysLogStudentOperation slso, Integer sort) {
		// TODO Auto-generated method stub
		Integer count = sysLogStudentOperationMapper.selectListCount(slso);
		if(sort == null || sort.equals(0)){
			List<SysLogStudentOperation> list = sysLogStudentOperationMapper
					.selectListAsc(slso);
			PageFinder<SysLogStudentOperation> slsoPage 
				= new PageFinder<SysLogStudentOperation>(slso.getPage(), slso.getPageSize(), count, list);
			return slsoPage;
		}else{
			List<SysLogStudentOperation> list = sysLogStudentOperationMapper
					.selectListDesc(slso);
			PageFinder<SysLogStudentOperation> slsoPage 
				= new PageFinder<SysLogStudentOperation>(slso.getPage(), slso.getPageSize(), count, list);
			return slsoPage;
		}
	}

	@Override
	public PageFinder<SysLogStudentOperation> selectListByDate(
			SysLogStudentOperationVo slsoVo, Integer sort) {
		// TODO Auto-generated method stub
		Integer count = sysLogStudentOperationMapper.selectListByDateCount(slsoVo);
		if(sort == null || sort.equals(0)){
			List<SysLogStudentOperation> list = sysLogStudentOperationMapper
					.selectListByDateAsc(slsoVo);
			PageFinder<SysLogStudentOperation> slsoPage 
				= new PageFinder<SysLogStudentOperation>(slsoVo.getPage(), slsoVo.getPageSize(), count, list);
			return slsoPage;
		}else{
			List<SysLogStudentOperation> list = sysLogStudentOperationMapper
					.selectListByDateDesc(slsoVo);
			PageFinder<SysLogStudentOperation> slsoPage 
				= new PageFinder<SysLogStudentOperation>(slsoVo.getPage(), slsoVo.getPageSize(), count, list);
			return slsoPage;
		}
	};
	
	
	@Override
	public List<SysLogStudentOperationVo> findBySearch(SysLogStudentOperationVo search) {
		return sysLogStudentOperationMapper.findBySearch(search);
	};
}
