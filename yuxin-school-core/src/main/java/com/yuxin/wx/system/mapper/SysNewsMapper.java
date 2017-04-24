package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.model.system.SysNews;
import com.yuxin.wx.vo.company.CompanyFunctionSetVo;
import com.yuxin.wx.vo.system.SysNewsVo;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:SysConfigTable
 * @author wang.zx
 * @date 2014-12-5
 */
public interface SysNewsMapper extends BaseMapper<SysNews> {
	void changNewsStatus(SysNewsVo sysNewsVo);
	void addNews(SysNewsVo sysNewsVo);
	List<SysNewsVo> findList(SysNewsVo sysNewsVo);
	List<SysNews> findNewsByCompanyId(Integer companyId);
	List<CompanyFunctionSetVo> queryCompanyFunctionById(Integer companyId);
	void insertCompanyFunction(CompanyFunctionSetVo fun);
	void updateCompanyFunction(CompanyFunctionSetVo fun);
	void updateDelFlag(SysNews sysNews);
	List<SysNewsVo> page2(SysNewsVo search);
	int pageCount2(SysNewsVo search);
	public List<SysNewsVo> guanwangNewsList(SysNewsVo search);
	public List<SysNewsVo> guanwangNewsList2(SysNewsVo search);
	public int guanwangNewsListCount(SysNewsVo search);
	List<SysNews> queryShowNews(SysNewsVo search);
}