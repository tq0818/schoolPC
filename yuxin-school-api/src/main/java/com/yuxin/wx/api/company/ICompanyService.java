package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.NewCompanyVo;
import com.yuxin.wx.vo.company.CompanyOrgMessageReadVo;
import com.yuxin.wx.vo.company.CompanyOrgMessageVo;
import com.yuxin.wx.vo.company.CompanyPicsVo;
import com.yuxin.wx.vo.company.CompanyTotalVo;
import com.yuxin.wx.vo.company.CompanyVo;
import com.yuxin.wx.vo.company.companySpecialDomain;
import com.yuxin.wx.vo.query.RegisterInfoVo;

/**
 * Service Interface:Company
 * @author chopin
 * @date 2015-4-23
 */
public interface ICompanyService  {
	/**
	 * 
	* @Title: saveCompany
	* @Description: 新增Company
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void insert(Company entity);
	
	/**
	 * 
	* @Title: batchSaveCompany 
	* @Description: 批量新增Company
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void batchInsert(List<Company> list);
	
	/**
	 * 
	* @Title: updateCompany 
	* @Description: 编辑Company
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void update(Company entity);
	
	/**
	 * 
	* @Title: deleteCompanyById 
	* @Description: 根据id删除Company
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void deleteCompanyById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyByIds 
	* @Description: 根据id批量删除Company
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	void deleteCompanyByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	Company findCompanyById(Integer id);
	
	
	/**
	 * 
	* @Title: findCompanyByPage 
	* @Description: 分页查询
	* @return
	* @return List<Company>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-4-23
	* @user by wangzx
	 */
	List<Company> findCompanyByPage(Company search);
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 根据公司ID查询所享受服务级别
	 * @author ycl
	 * @date 2015-4-29 上午11:11:15
	 * @modifier
	 * @modify-date 2015-4-29 上午11:11:15
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	Integer findCompanyLevel(Integer companyId);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询公司图片库
	 * @author zhang.zx
	 * @date 2015-4-29 上午11:11:15
	 * @modifier
	 * @modify-date 2015-4-29 上午11:11:15
	 * @version 1.0
	 * @param companyPicsVo
	 * @return
	 */
	PageFinder<CompanyPicsVo> findCompanyPic(CompanyPicsVo companyPicsVo);

	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 分页查询Vo
	 * @author ycl
	 * @date 2015-5-12 下午6:14:12
	 * @modifier
	 * @modify-date 2015-5-12 下午6:14:12
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<CompanyVo> findCompanyVoByPage(Company search);
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询分页用的总数量
	 * @author ycl
	 * @date 2015-5-12 下午6:42:49
	 * @modifier
	 * @modify-date 2015-5-12 下午6:42:49
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Integer findTotalCount(Company search);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询公司私有图片库
	 * @author zhang.zx
	 * @date 2015-4-29 上午11:11:15
	 * @modifier
	 * @modify-date 2015-4-29 上午11:11:15
	 * @version 1.0
	 * @param companyPicsVo
	 * @return
	 */
	PageFinder<CompanyPicsVo> findCompanyPrivatePic(CompanyPicsVo companyPicsVo);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询公司图片库一级条件
	 * @author zhang.zx
	 * @date 2015-4-29 上午11:11:15
	 * @modifier
	 * @modify-date 2015-4-29 上午11:11:15
	 * @version 1.0
	 * @param companyPicsVo
	 * @return
	 */
	List<CompanyPicsVo> findOneCondtion();
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 条件查询公司
	 * @author zhang.zx
	 * @date 2015-5-17
	 * @modifier
	 * @modify-date 2015-5-17
	 * @version 1.0
	 * @param Company
	 * @return
	 */
	Company queryCompanyByCopanyCondition(Company company);
	

	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 添加
	 * @author zhang.zx
	 * @date 2015-5-18
	 * @modifier
	 * @modify-date 2015-5-18
	 * @version 1.0
	 * @param Company
	 * @return
	 */
	void insertCompany(Company company);
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询公司的审核认证状态
	 * @author 权飞虎
	 * @date 2015年5月19日 下午6:48:50
	 * @modifier
	 * @modify-date 2015年5月19日 下午6:48:50
	 * @version 1.0
	 * @param currentCompanyId
	 * @return
	 */
	String findStatus(Integer currentCompanyId);

	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 定时任务 获得所有公司的id
	 * @author 周文斌
	 * @date 2015-5-21 下午12:59:19
	 * @version 1.0
	 * @return
	 */
	List<Integer> findCompanyId(String types);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 定时任务 查询所有收费 公司 id 和等级
	 * @author 周文斌
	 * @date 2015-5-21 下午2:15:18
	 * @version 1.0
	 * @return
	 */
	List<Company> findCompanyIdAndLevel();

	
	public List<Company> findAll();
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 会员升级的操作
	 * @author ycl
	 * @date 2015-6-2 上午10:35:19
	 * @modifier
	 * @modify-date 2015-6-2 上午10:35:19
	 * @version 1.0
	 * @param CEntity
	 * @param CMSEntity
	 */
	void memberLevelUp(Company CEntity,CompanyMemberService CMSEntity);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 验证域名
	 * @author zhang.zx
	 * @date 2015年6月8日 下午8:24:27
	 * @modifier
	 * @modify-date 2015年6月8日 下午8:24:27
	 * @version 1.0
	 * @param domainName
	 * @return
	 */
	List<companySpecialDomain> findSpecialName(String domainName);
	
	/**
	 * 
	* @Title: findCompanyServiceStaticByPage 
	* @Description: 达到最大招生人数
	* @return
	* @return List<CompanyServiceStatic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-23
	* @user by chopin
	 */
	public Boolean reachMaxStudentNum(Integer companyId);
	
	/**
	 * 
	* @Title: findCompanyServiceStaticByPage 
	* @Description: 达到服务期限
	* @return
	* @return List<CompanyServiceStatic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-23
	* @user by chopin
	 */
	public Boolean reachMaxStudentDate(Integer companyId);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询消息列表
	 * @author zhang.zx
	 * @date 2015年7月13日 下午7:16:16
	 * @modifier
	 * @modify-date 2015年7月13日 下午7:16:16
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<CompanyOrgMessageVo> findMessageList(CompanyOrgMessageVo search);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 根据id查询消息
	 * @author zhang.zx
	 * @date 2015年7月13日 下午8:03:53
	 * @modifier
	 * @modify-date 2015年7月13日 下午8:03:53
	 * @version 1.0
	 * @param id
	 * @return
	 */
	CompanyOrgMessageVo queryMessageById(Integer id);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 添加消息
	 * @author zhang.zx
	 * @date 2015年7月13日 下午8:19:42
	 * @modifier
	 * @modify-date 2015年7月13日 下午8:19:42
	 * @version 1.0
	 * @param c
	 */
	void insertMsg(CompanyOrgMessageVo c);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 修改消息
	 * @author zhang.zx
	 * @date 2015年7月13日 下午8:19:51
	 * @modifier
	 * @modify-date 2015年7月13日 下午8:19:51
	 * @version 1.0
	 * @param c
	 */
	void updateMsg(CompanyOrgMessageVo c);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 删除消息
	 * @author zhang.zx
	 * @date 2015年7月13日 下午8:19:51
	 * @modifier
	 * @modify-date 2015年7月13日 下午8:19:51
	 * @version 1.0
	 * @param id
	 */
	void deleteMsg(Integer id);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询公司
	 * @author zhang.zx
	 * @date 2015年7月14日 上午9:51:39
	 * @modifier
	 * @modify-date 2015年7月14日 上午9:51:39
	 * @version 1.0
	 * @return
	 */
	List<Company> queryAllCompany();
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 消息中心列表
	 * @author zhang.zx
	 * @date 2015年7月14日 下午12:14:34
	 * @modifier
	 * @modify-date 2015年7月14日 下午12:14:34
	 * @version 1.0
	 * @param messageType
	 * @return
	 */
	PageFinder<CompanyOrgMessageReadVo> findMessageCenterList(CompanyOrgMessageReadVo c);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 添加公司阅读记录
	 * @author zhang
	 * @date 2015年7月14日 下午12:51:25
	 * @modifier
	 * @modify-date 2015年7月14日 下午12:51:25
	 * @version 1.0
	 * @param c
	 */
	void insertCompanyReadDetail(CompanyOrgMessageReadVo c);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description:更改阅读状态
	 * @author zhang.zx
	 * @date 2015年7月14日 下午12:52:15
	 * @modifier
	 * @modify-date 2015年7月14日 下午12:52:15
	 * @version 1.0
	 * @param c
	 */
	void updateMsgReadFlag(CompanyOrgMessageReadVo c);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询机构消息记录
	 * @author zhang.zx
	 * @date 2015年7月14日 下午2:58:40
	 * @modifier
	 * @modify-date 2015年7月14日 下午2:58:40
	 * @version 1.0
	 * @param id
	 * @return
	 */
	CompanyOrgMessageReadVo querymessageCenterById(Map<String, Integer> map);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询公司未读数量
	 * @author zhang.zx
	 * @date 2015年7月17日 下午6:28:27
	 * @modifier
	 * @modify-date 2015年7月17日 下午6:28:27
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	Integer queryCompanyNotReadNum(CompanyOrgMessageReadVo c);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 公司来源统计
	 * @author zhang.zx
	 * @date 2015年7月27日 下午5:36:05
	 * @modifier
	 * @modify-date 2015年7月27日 下午5:36:05
	 * @version 1.0
	 * @param c
	 * @return
	 */
	List<CompanyTotalVo> querySourceList(CompanyTotalVo c);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 统计公司注册人数
	 * @author zhang.zx
	 * @date 2015年7月29日 下午2:08:37
	 * @modifier
	 * @modify-date 2015年7月29日 下午2:08:37
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<CompanyTotalVo> queryCompanyRegistNum(CompanyTotalVo search);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询公司图表数据
	 * @author zhang.zx
	 * @date 2015年7月29日 下午2:17:34
	 * @modifier
	 * @modify-date 2015年7月29日 下午2:17:34
	 * @version 1.0
	 * @param c
	 * @return
	 */
	List<CompanyTotalVo> queryRegistNumChar(CompanyTotalVo c);
	
	List<Company> queryCompanyBuyFlag(Company search);
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询某时间段内公司注册信息
	 * @author zhang.zx
	 * @date 2015年7月29日 下午2:17:34
	 * @modifier
	 * @modify-date 2015年7月29日 下午2:17:34
	 * @version 1.0
	 * @param c
	 * @return
	 */
	public List<Company> queryRegisterCompany(Map map);
	
	/**
	 * 
	* @Title: findCompanyServiceStaticByPage 
	* @Description: 检查公司域名是否被注册
	* @return
	* @return List<CompanyServiceStatic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-23
	* @user by chopin
	 */
	public Boolean checkCompanyDomain(String search);
	/**
	 * 
	* @Title: findCompanyServiceStaticByPage 
	* @Description: 检查公司域名是否被注册
	* @return
	* @return List<CompanyServiceStatic>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-23
	* @user by chopin
	 */
	public Boolean checkCompanyName(String search);
	/**
	 * Class Name: ICompanyService.java
	 * @Description:查询私有云图片
	 * @author xukaiqiang
	 * @date 2016年6月8日 上午11:22:35
	 * @modifier
	 * @modify-date 2016年6月8日 上午11:22:35
	 * @version 1.0
	 * @param picVo
	 * @return
	 */
	PageFinder<CompanyPicsVo> queryPrivatePic(CompanyPicsVo picVo);
	/**
	 * Class Name: ICompanyService.java
	 * @Description:查询公有云图片
	 * @author xukaiqiang
	 * @date 2016年6月8日 上午11:22:35
	 * @modifier
	 * @modify-date 2016年6月8日 上午11:22:35
	 * @version 1.0
	 * @param picVo
	 * @return
	 */
	PageFinder<CompanyPicsVo> queryPublicPic(CompanyPicsVo picVo);
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询域名
	 * @author 周文斌
	 * @date 2016-7-26 下午6:32:27
	 * @version 1.0
	 * @param id
	 * @return
	 */
	String findDomainById(Integer id);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询公司注册信息（url,手机号,注册时间）
	 * @author dongshuai
	 * @date 2016年8月11日 下午5:30:04
	 * @modifier
	 * @modify-date 2016年8月11日 下午5:30:04
	 * @version 1.0
	 * @return
	 */
	PageFinder<RegisterInfoVo> queryRegisterInfo(RegisterInfoVo riv);
	
	/**
	 * 
	 * Class Name: ICompanyService.java
	 * @Description: 查询公司注册信息（url,手机号,注册时间）
	 * @author dongshuai
	 * @date 2016年8月11日 下午6:41:55
	 * @modifier
	 * @modify-date 2016年8月11日 下午6:41:55
	 * @version 1.0
	 * @param riv
	 * @return
	 */
	List<RegisterInfoVo> queryRegisterInfoExport(RegisterInfoVo riv);

	List<CompanyOrgMessageVo> queryMessageServiceList(CompanyOrgMessageVo search);
	
	Integer queryServiceOpenFlag(Map<String, Object> map);
	
	/**
	 * 
	 * @author jishangyang 2017年12月7日 下午6:46:51
	 * @Method: findCompanyVoById 
	 * @Description: 根据ID查询分校
	 * @param id
	 * @return 
	 * @throws
	 */
	NewCompanyVo findCompanyVoById(Integer id);
	CompanyLiveConfig findCompanyLiveConfigById(Integer id);

}
