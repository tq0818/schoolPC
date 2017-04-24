package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyCouponsLibService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyCouponsLibMapper;
import com.yuxin.wx.model.company.CompanyCouponsLib;
import com.yuxin.wx.vo.company.CompanyCouponsLibOrderVo;
import com.yuxin.wx.vo.company.CompanyCouponsLibVo;


/**
 * Service Implementation:CompanyCouponsLib
 * @author chopin
 * @date 2016-6-20
 */
@Service
@Transactional
public class CompanyCouponsLibServiceImpl extends BaseServiceImpl implements ICompanyCouponsLibService {

	@Autowired
	private CompanyCouponsLibMapper companyCouponsLibMapper;
	
	/**
	 * 
	* @Title: saveCompanyCouponsLib
	* @Description: 新增CompanyCouponsLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void insert(CompanyCouponsLib entity){
		companyCouponsLibMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyCouponsLib 
	* @Description: 批量新增CompanyCouponsLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyCouponsLib> entity){
		companyCouponsLibMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyCouponsLib 
	* @Description: 编辑CompanyCouponsLib
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void update(CompanyCouponsLib entity){
		companyCouponsLibMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyCouponsLibById 
	* @Description: 根据id删除CompanyCouponsLib
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyCouponsLibById(Integer id){
		companyCouponsLibMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyCouponsLibByIds 
	* @Description: 根据id批量删除CompanyCouponsLib
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void deleteCompanyCouponsLibByIds(Integer[] ids){
		companyCouponsLibMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyCouponsLibById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public CompanyCouponsLib findCompanyCouponsLibById(Integer id){
		return companyCouponsLibMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyCouponsLibByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyCouponsLib>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public List<CompanyCouponsLib> findCompanyCouponsLibByPage(CompanyCouponsLib search){
		return companyCouponsLibMapper.page(search);
	}

	@Override
	public PageFinder<CompanyCouponsLib> queryLibsListByPatchId(CompanyCouponsLib search) {
		List<CompanyCouponsLib> data=companyCouponsLibMapper.queryLibsListByPatchId(search);
		int rowCount=companyCouponsLibMapper.queryLibsListCountByPatchId(search);
		PageFinder<CompanyCouponsLib> pageFinder=new PageFinder<CompanyCouponsLib>(search.getPage(),search.getPageSize(), rowCount, data);
		return pageFinder;
	}
	
	@Override
	public PageFinder<CompanyCouponsLibOrderVo> queryLibsForUseOrder(CompanyCouponsLibVo search) {
		List<CompanyCouponsLibOrderVo> data = companyCouponsLibMapper.queryLibsForUseOrder(search);
		int rowCount = companyCouponsLibMapper.queryLibsForUseOrderCount(search);
		PageFinder<CompanyCouponsLibOrderVo> pageFinder=new PageFinder<CompanyCouponsLibOrderVo>(search.getPage(),search.getPageSize(), rowCount, data);
		return pageFinder;
	}

	@Override
	public List<CompanyCouponsLib> queryLibsListByPatchIdExport(CompanyCouponsLib search) {
		return companyCouponsLibMapper.queryLibsListByPatchIdExport(search);
	}

	@Override
	public CompanyCouponsLib findOneByCode(String code) {
		// TODO Auto-generated method stub
		return companyCouponsLibMapper.findOneByCode(code);
	};
}
