package com.yuxin.wx.company.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyNewStepService;
import com.yuxin.wx.company.mapper.CompanyMapper;
import com.yuxin.wx.company.mapper.CompanyNewStepMapper;
import com.yuxin.wx.model.company.CompanyNewStep;
import com.yuxin.wx.system.mapper.SysConfigItemMapper;
import com.yuxin.wx.user.mapper.UsersMapper;

/**
 * Service Implementation:CompanyNewStep
 * @author chopin
 * @date 2015-5-20
 */
@Service
@Transactional
public class CompanyNewStepServiceImpl extends BaseServiceImpl implements ICompanyNewStepService {

	@Autowired
	private CompanyNewStepMapper companyNewStepMapper;
	@Autowired
	private CompanyMapper companyMapper;
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private SysConfigItemMapper sysConfigItemMapper;
	
	/**
	 * 
	* @Title: saveCompanyNewStep
	* @Description: 新增CompanyNewStep
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by chopin
	 */
	@Override
	public void insert(CompanyNewStep entity){
		companyNewStepMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyNewStep 
	* @Description: 批量新增CompanyNewStep
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyNewStep> entity){
		companyNewStepMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyNewStep 
	* @Description: 编辑CompanyNewStep
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by chopin
	 */
	@Override
	public void update(CompanyNewStep entity){
		companyNewStepMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyNewStepById 
	* @Description: 根据id删除CompanyNewStep
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyNewStepById(Integer id){
		companyNewStepMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyNewStepByIds 
	* @Description: 根据id批量删除CompanyNewStep
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by chopin
	 */
	@Override
	public void deleteCompanyNewStepByIds(Integer[] ids){
		companyNewStepMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyNewStepById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by chopin
	 */
	@Override
	public CompanyNewStep findCompanyNewStepById(Integer id){
		return companyNewStepMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyNewStepByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyNewStep>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-5-20
	* @user by chopin
	 */
	@Override
	public List<CompanyNewStep> findCompanyNewStepByPage(CompanyNewStep search){
		return companyNewStepMapper.page(search);
	}

	@Override
	public int selectCount(Integer companyId) {
		return companyNewStepMapper.selectCount(companyId);
	}

	@Override
	public List<CompanyNewStep> findCompanyNewStepByCompany(
			Integer companyId) {
		return companyNewStepMapper.findCompanyNewStepByCompany(companyId);
	};
	public Boolean isPass(Integer companyId){
		Boolean isPass=false;
		List<CompanyNewStep> list = companyNewStepMapper.findCompanyNewStepByCompany(companyId);
		if(list.size()>0){
			int newStepCount = companyNewStepMapper.selectCount(companyId);
			if(newStepCount>0){
				isPass=true;
			}else{
				String status = companyMapper.findStatus(companyId);
				int userCount = usersMapper.selectCount(companyId);
				int itemCount = sysConfigItemMapper.selectCount(companyId);
				CompanyNewStep entity=new CompanyNewStep();
				entity.setId(list.get(0).getId());
				entity.setCompanyId(companyId);
				if("3".equals(status)){
					entity.setCompanyAuthority(1);
					
				}
				//查询该公司是否有其他用户
				if(userCount>1){
					entity.setUserCreate(1);
					
				}
				if(itemCount>0){
					entity.setItemAll(1);
				}
				if("3".equals(status)||userCount>1||itemCount>0){
					companyNewStepMapper.update(entity);
				}
				
				int count = companyNewStepMapper.selectCount(companyId);
				if(count>0){
					isPass=true;
				}
			}
			
		}else{
			CompanyNewStep entity=new CompanyNewStep();
			entity.setCompanyId(companyId);
			String status = companyMapper.findStatus(companyId);
			int userCount = usersMapper.selectCount(companyId);
			int itemCount = sysConfigItemMapper.selectCount(companyId);
			if("3".equals(status)){
				entity.setCompanyAuthority(1);
				//companyNewStepMapper.update(entity);
			}
			//查询该公司是否有其他用户
			if(userCount>1){
				entity.setUserCreate(1);
				
			}
			if(itemCount>0){
				entity.setItemAll(1);
			}
			companyNewStepMapper.insert(entity);
		}
		
		
			return isPass;
	}
	
}
