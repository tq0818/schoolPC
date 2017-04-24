package com.yuxin.wx.company.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyMemberLevelConfigService;
import com.yuxin.wx.company.mapper.CompanyMemberLevelConfigMapper;
import com.yuxin.wx.model.company.CompanyMemberLevelConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelConfigVo;
import com.yuxin.wx.model.company.CompanyMemberLevelDetail;

/**
 * Service Implementation:CompanyMemberLevelConfig
 * 
 * @author chopin
 * @date 2016-5-17
 */
@Service
@Transactional
public class CompanyMemberLevelConfigServiceImpl extends BaseServiceImpl implements ICompanyMemberLevelConfigService {

	@Autowired
	private CompanyMemberLevelConfigMapper companyMemberLevelConfigMapper;

	/**
	 * 
	 * @Title: saveCompanyMemberLevelConfig
	 * @Description: 新增CompanyMemberLevelConfig
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2016-5-17
	 * @user by chopin
	 */
	@Override
	public void insert(CompanyMemberLevelConfig entity) {
		companyMemberLevelConfigMapper.insert(entity);
	};

	/**
	 * 
	 * @Title: batchSaveCompanyMemberLevelConfig
	 * @Description: 批量新增CompanyMemberLevelConfig
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2016-5-17
	 * @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyMemberLevelConfig> entity) {
		companyMemberLevelConfigMapper.batchInsert(entity);
	};

	/**
	 * 
	 * @Title: updateCompanyMemberLevelConfig
	 * @Description: 编辑CompanyMemberLevelConfig
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2016-5-17
	 * @user by chopin
	 */
	@Override
	public void update(CompanyMemberLevelConfig entity) {
		companyMemberLevelConfigMapper.update(entity);
	};

	
	/**
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * @Description: 查询最高等级
	 * @author xukaiqiang
	 * @date 2016年6月13日 上午12:05:22
	 * @modifier
	 * @modify-date 2016年6月13日 上午12:05:22
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	public Integer findHighLevel(Integer companyId){
		return companyMemberLevelConfigMapper.findHighLevel(companyId);
	}
	/**
	 * 
	 * @Title: deleteCompanyMemberLevelConfigById
	 * @Description: 根据id删除CompanyMemberLevelConfig
	 * @param id
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2016-5-17
	 * @user by chopin
	 */
	@Override
	public void deleteCompanyMemberLevelConfigById(Integer id) {
		companyMemberLevelConfigMapper.deleteById(id);
	};

	/**
	 * 
	 * @Title: deleteCompanyMemberLevelConfigByIds
	 * @Description: 根据id批量删除CompanyMemberLevelConfig
	 * @param ids
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2016-5-17
	 * @user by chopin
	 */
	@Override
	public void deleteCompanyMemberLevelConfigByIds(Integer[] ids) {
		companyMemberLevelConfigMapper.deleteByIds(ids);
	};

	/**
	 * 
	 * @Title: findCompanyMemberLevelConfigById
	 * @Description: 根据id查询
	 * @param id
	 * @return void 返回类型
	 * @throws @exception
	 * @date 2016-5-17
	 * @user by chopin
	 */
	@Override
	public CompanyMemberLevelConfig findCompanyMemberLevelConfigById(Integer id) {
		return companyMemberLevelConfigMapper.findById(id);
	};

	/**
	 * 
	 * @Title: findCompanyMemberLevelConfigByPage
	 * @Description: 分页查询
	 * @return
	 * @return List<CompanyMemberLevelConfig> 返回类型
	 * @throws @exception
	 * @date 2016-5-17
	 * @user by chopin
	 */
	@Override
	public List<CompanyMemberLevelConfig> findCompanyMemberLevelConfigByPage(CompanyMemberLevelConfig search) {
		return companyMemberLevelConfigMapper.page(search);
	}

	@Override
	public List<CompanyMemberLevelConfig> queryMemberLevelList(CompanyMemberLevelConfig search) {
		// TODO Auto-generated method stub
		return companyMemberLevelConfigMapper.queryMemberLevelList(search);
	};

	@Override
	public List<CompanyMemberLevelConfigVo> findCompanyMemberLevelConfigByCompanyId(CompanyMemberLevelConfig search) {
		return companyMemberLevelConfigMapper.findCompanyMemberLevelConfigByCompanyId(search);
	}

	/**
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * 
	 * @Description: 查询所有的会员等级的详情
	 * @author xukaiqiang
	 * @date 2016年5月22日 下午5:09:30
	 * @modifier
	 * @modify-date 2016年5月22日 下午5:09:30
	 * @version 1.0
	 * @param search
	 * @return
	 */
	public List<CompanyMemberLevelDetail> findCompanyMemberLevelConfigDetailById(CompanyMemberLevelConfigVo search) {
		return companyMemberLevelConfigMapper.findCompanyMemberLevelConfigDetailById(search);
	}

	/**
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * 
	 * @Description: 查询大于当前会员等级的所有等级名称
	 * @author xukaiqiang
	 * @date 2016年5月27日 下午4:12:13
	 * @modifier
	 * @modify-date 2016年5月27日 下午4:12:13
	 * @version 1.0
	 * @param map
	 * @return
	 */
	@Override
	public List<CompanyMemberLevelConfig> queryAllCompanyMemberLevelName(Map map) {
		return companyMemberLevelConfigMapper.queryAllCompanyMemberLevelName(map);
	}

	/***
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * 
	 * @Description: 查询会员等级下面的所有有效期,大于当前会员等级下面的
	 * @author xukaiqiang
	 * @date 2016年5月27日 下午6:37:25
	 * @modifier
	 * @modify-date 2016年5月27日 下午6:37:25
	 * @version 1.0
	 * @param memberLevelId
	 * @return
	 */
	@Override
	public List<CompanyMemberLevelDetail> queryMemberLevelDetails(Map<String, Integer> map) {
		return companyMemberLevelConfigMapper.queryMemberLevelDetails(map);
	}

	/**
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * 
	 * @Description: 查询会员等级下面的所有有效期
	 * @author xukaiqiang
	 * @date 2016年5月29日 下午4:17:51
	 * @modifier
	 * @modify-date 2016年5月29日 下午4:17:51
	 * @version 1.0
	 * @param map
	 * @return
	 */
	public  List<CompanyMemberLevelDetail> queryMemberLevelAllDetails(Map<String, Integer> map){
		return companyMemberLevelConfigMapper.queryMemberLevelAllDetails(map);
	}
	/**
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * @Description: 查询会员有效期价格
	 * @author xukaiqiang
	 * @date 2016年5月29日 下午5:14:14
	 * @modifier
	 * @modify-date 2016年5月29日 下午5:14:14
	 * @version 1.0
	 * @param map
	 */
	public Double queryMemberLevelValidPrice(Map<String, Integer> map){
		//查询当前有效期的价格
		return companyMemberLevelConfigMapper.queryMemberLevelValidPrice(map);
	}

	@Override
	public boolean checkMemberLevelName(CompanyMemberLevelConfigVo companyMemberLevelConfigVo) { 
		List<CompanyMemberLevelConfigVo> list=companyMemberLevelConfigMapper.checkMemberLevelName(companyMemberLevelConfigVo);
		
		if(companyMemberLevelConfigVo.getMethod().equals("add")){
			//重复了
			if(list.size()>0){
				return true;
			}
		}
		if(companyMemberLevelConfigVo.getMethod().equals("update")){
			//重复了
			if (list.size() > 0 && !list.get(0).getId().equals(companyMemberLevelConfigVo.getId())) {
				return  true;
			}
		}
		return false;	
	}

	/**
	 * 
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * @Description: 查询最大的sequence
	 * @author xukaiqiang
	 * @date 2016年6月2日 下午6:45:36
	 * @modifier
	 * @modify-date 2016年6月2日 下午6:45:36
	 * @version 1.0
	 * @return
	 */
	@Override
	public Integer findMaxSequence(Map<String, Object> map) {
		return companyMemberLevelConfigMapper.findMaxSequence(map);
	}
	public List<CompanyMemberLevelConfig> queryAllCompanyMemberLevelNameNoSelf(Map<String, Integer> map){
		return companyMemberLevelConfigMapper.queryAllCompanyMemberLevelNameNoSelf(map);
	}
	public boolean checkMemberLevelExist(Integer companyId){
		int count=companyMemberLevelConfigMapper.checkMemberLevelExist(companyId);
		if(count>0){
			return  true;
		}else{
			return false;
		}
	}
}
