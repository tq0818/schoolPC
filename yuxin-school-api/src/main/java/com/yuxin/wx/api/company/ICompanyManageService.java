package com.yuxin.wx.api.company;

import java.util.List;

import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.model.classes.EduMasterClass;
import com.yuxin.wx.model.company.CompanyLiveConfig;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyVo;

public interface ICompanyManageService {
	/**
	 * 通过map查询条件查询分校值
	 * @return 分校集合
	 */
	public PageFinder2<CompanyVo> queryCompanyVoListByCondition(CompanyVo search);
	
	/**
	 * 通过brachCode查询对应的分校
	 * @param brachCode
	 * @return
	 */
	public CompanyVo queryCompanyVoByCondition(String brachCode);
	
	/**
	 * 
	 * @author jishangyang 2017年12月7日 上午9:40:38
	 * @Method: addBerkeley 
	 * @Description: 添加分校
	 * @param search
	 * @param cms
	 * @param clc
	 * @return 
	 * @throws
	 */
	public void addBerkeley(CompanyVo search,CompanyMemberService cms,CompanyLiveConfig clc,Integer userId);
	/**
	 * 
	 * @author jishangyang 2017年12月8日 上午12:11:19
	 * @Method: eidtBerkeley 
	 * @Description: 修改分校
	 * @param search
	 * @param cms
	 * @param clc 
	 * @throws
	 */
	public void eidtBerkeley(CompanyVo search,CompanyMemberService cms,CompanyLiveConfig clc);
	
	/**
	 * 
	 * @author jishangyang 2017年12月13日 下午2:31:18
	 * @Method: findClassByEduAreaSchool 
	 * @Description: 通过所在学校字典代码查询 学校下班级信息
	 * @param eduAreaSchool 
	 * @throws
	 */
	public List<EduMasterClass> findClassByEduAreaSchool(String  eduAreaSchool);
	/**
	 * 
	 * @author jishangyang 2017年12月14日 下午12:05:01
	 * @Method: findClassByEduAreaSchool 
	 * @Description: 查询可用班级
	 * @param eduAreaSchool
	 * @return 
	 * @throws
	 */
	public List<EduMasterClass> findIsUsedClassByEduAreaSchool(String  eduAreaSchool);
	/**
	 * 
	 * @author jishangyang 2017年12月13日 下午5:03:32
	 * @Method: addClass 
	 * @Description: 保存班级信息
	 * @param saveList 
	 * @throws
	 */
	public void addClass(List<EduMasterClass> saveList);
	/**
	 * 
	 * @author jishangyang 2017年12月14日 下午2:35:43
	 * @Method: editClass 
	 * @Description: 修改班级
	 * @param saveList
	 * @param updateList 
	 * @throws
	 */
	public void editClass(List<EduMasterClass> saveList,List<EduMasterClass> updateList);
	/**
	 * 
	 * @author jishangyang 2017年12月13日 下午5:19:26
	 * @Method: findEduAreaByeduAreaSchool 
	 * @Description:通过学校字典码获取 区信息
	 * @param eduAreaSchool
	 * @return 
	 * @throws
	 */
	public String findEduAreaByeduAreaSchool(String  eduAreaSchool);
}
