package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.company.CompanyMemberLevelConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelConfigVo;
import com.yuxin.wx.model.company.CompanyMemberLevelDetail;
/**
 * Service Interface:CompanyMemberLevelConfig
 * @author chopin
 * @date 2016-5-17
 */
public interface ICompanyMemberLevelConfigService  {
	/**
	 * 
	* @Title: saveCompanyMemberLevelConfig
	* @Description: 新增CompanyMemberLevelConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void insert(CompanyMemberLevelConfig entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyMemberLevelConfig 
	* @Description: 批量新增CompanyMemberLevelConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void batchInsert(List<CompanyMemberLevelConfig> list);
	
	/**
	 * 
	* @Title: updateCompanyMemberLevelConfig 
	* @Description: 编辑CompanyMemberLevelConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void update(CompanyMemberLevelConfig entity);
	
	/**
	 * 
	* @Title: deleteCompanyMemberLevelConfigById 
	* @Description: 根据id删除CompanyMemberLevelConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void deleteCompanyMemberLevelConfigById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyMemberLevelConfigByIds 
	* @Description: 根据id批量删除CompanyMemberLevelConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void deleteCompanyMemberLevelConfigByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyMemberLevelConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	CompanyMemberLevelConfig findCompanyMemberLevelConfigById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyMemberLevelConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMemberLevelConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	List<CompanyMemberLevelConfig> findCompanyMemberLevelConfigByPage(CompanyMemberLevelConfig search);
	
	/**
	 * 
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * @Description: 查询会员等级列表
	 * @author zhang.zx
	 * @date 2016年5月20日 下午2:34:41
	 * @modifier
	 * @modify-date 2016年5月20日 下午2:34:41
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<CompanyMemberLevelConfig> queryMemberLevelList(CompanyMemberLevelConfig search);
	/**
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * 
	 * @Description: 查询所有的公司会员等级
	 * @author xukaiqiang
	 * @date 2016年5月22日 下午4:20:44
	 * @modifier
	 * @modify-date 2016年5月22日 下午4:20:44
	 * @version 1.0
	 * @param entity
	 * @return
	 */
	List<CompanyMemberLevelConfigVo> findCompanyMemberLevelConfigByCompanyId(CompanyMemberLevelConfig search);

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
	List<CompanyMemberLevelDetail> findCompanyMemberLevelConfigDetailById(CompanyMemberLevelConfigVo search);
	/**
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * @Description: 查询大于当前会员等级的所有等级名称
	 * @author xukaiqiang
	 * @date 2016年5月27日 下午4:12:13
	 * @modifier
	 * @modify-date 2016年5月27日 下午4:12:13
	 * @version 1.0
	 * @param map
	 * @return
	 */
	List<CompanyMemberLevelConfig> queryAllCompanyMemberLevelName(Map map);
	/***
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * @Description: 查询会员等级下面的所有有效期,大于当前会员等级下面的
	 * @author xukaiqiang
	 * @date 2016年5月27日 下午6:37:25
	 * @modifier
	 * @modify-date 2016年5月27日 下午6:37:25
	 * @version 1.0
	 * @param map
	 * @return
	 */
	List<CompanyMemberLevelDetail> queryMemberLevelDetails(Map<String, Integer> map);
	/**
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * @Description: 查询会员等级下面的所有有效期
	 * @author xukaiqiang
	 * @date 2016年5月29日 下午4:17:51
	 * @modifier
	 * @modify-date 2016年5月29日 下午4:17:51
	 * @version 1.0
	 * @param map
	 * @return
	 */
	List<CompanyMemberLevelDetail> queryMemberLevelAllDetails(Map<String, Integer> map);
	/**
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * @Description: 查询当前会员有效期价格
	 * @author xukaiqiang
	 * @date 2016年5月29日 下午5:14:14
	 * @modifier
	 * @modify-date 2016年5月29日 下午5:14:14
	 * @version 1.0
	 * @param map
	 */
	Double queryMemberLevelValidPrice(Map<String, Integer> map);
	/**
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * @Description: 会员等级名称是否重复
	 * @author xukaiqiang
	 * @date 2016年6月2日 下午2:19:36
	 * @modifier
	 * @modify-date 2016年6月2日 下午2:19:36
	 * @version 1.0
	 * @param companyMemberLevelConfigVo
	 * @return
	 */
	boolean checkMemberLevelName(CompanyMemberLevelConfigVo companyMemberLevelConfigVo);
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
	Integer findMaxSequence(Map<String, Object> map);
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
	Integer findHighLevel(Integer companyId);

	List<CompanyMemberLevelConfig> queryAllCompanyMemberLevelNameNoSelf(Map<String, Integer> map);

	boolean checkMemberLevelExist(Integer companyId);
}