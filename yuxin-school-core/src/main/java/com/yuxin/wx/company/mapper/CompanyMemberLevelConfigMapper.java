package com.yuxin.wx.company.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyMemberLevelConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelConfigVo;
import com.yuxin.wx.model.company.CompanyMemberLevelDetail;

/**
 * Service Interface:Users
 * 
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyMemberLevelConfigMapper extends BaseMapper<CompanyMemberLevelConfig> {

	List<CompanyMemberLevelConfig> queryMemberLevelList(CompanyMemberLevelConfig search);

	List<CompanyMemberLevelConfigVo> findCompanyMemberLevelConfigByCompanyId(CompanyMemberLevelConfig search);

	List<CompanyMemberLevelDetail> findCompanyMemberLevelConfigDetailById(CompanyMemberLevelConfigVo search);

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
	List<CompanyMemberLevelConfig> queryAllCompanyMemberLevelName(Map map);

	/***
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * 
	 * @Description: 查询会员等级下面的所有有效期,大于当前等级的有效期
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
	List<CompanyMemberLevelDetail> queryMemberLevelAllDetails(Map<String, Integer> map);

	/**
	 * Class Name: ICompanyMemberLevelConfigService.java
	 * 
	 * @Description: 查询会员有效期价格
	 * @author xukaiqiang
	 * @date 2016年5月29日 下午5:14:14
	 * @modifier
	 * @modify-date 2016年5月29日 下午5:14:14
	 * @version 1.0
	 * @param map
	 */
	Double queryMemberLevelValidPrice(Map<String, Integer> map);
	/**
	 * Class Name: CompanyMemberLevelConfigMapper.java
	 * @Description: 会员等级名称是否重复
	 * @author xukaiqiang
	 * @date 2016年6月2日 下午6:48:03
	 * @modifier
	 * @modify-date 2016年6月2日 下午6:48:03
	 * @version 1.0
	 * @param companyMemberLevelConfigVo
	 * @return
	 */
	List<CompanyMemberLevelConfigVo> checkMemberLevelName(CompanyMemberLevelConfigVo companyMemberLevelConfigVo);
	/**
	 * Class Name: CompanyMemberLevelConfigMapper.java
	 * @Description: 查找最大的Sequence
	 * @author xukaiqiang
	 * @date 2016年6月2日 下午6:48:18
	 * @modifier
	 * @modify-date 2016年6月2日 下午6:48:18
	 * @version 1.0
	 * @param map
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

	int checkMemberLevelExist(Integer companyId);
}