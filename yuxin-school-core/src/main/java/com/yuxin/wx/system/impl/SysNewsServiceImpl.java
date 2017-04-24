package com.yuxin.wx.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyFunctionSetService;
import com.yuxin.wx.api.system.ISysNewsService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.company.CompanyFunctionSet;
import com.yuxin.wx.model.system.SysNews;
import com.yuxin.wx.system.mapper.SysNewsMapper;
import com.yuxin.wx.vo.company.CompanyFunctionSetVo;
import com.yuxin.wx.vo.system.SysNewsVo;

/**
 * Service Implementation:SysNews
 * 
 * @author wang.zx
 * @date 2015-3-14
 */
@Service
@Transactional
public class SysNewsServiceImpl extends BaseServiceImpl implements ISysNewsService {

    @Autowired
    private SysNewsMapper sysNewsMapper;
    @Autowired
    private ICompanyFunctionSetService companyFunctionSetServiceImpl;

    /**
     * 
     * Class Name: SysNewsServiceImpl.java
     * 
     * @Description: 添加公告
     * @author zhang.zx
     * @date 2015年3月31日 下午4:58:56
     * @version 1.0
     * @param sysNews
     * @return void 返回类型
     */
    @Override
    public void insert(SysNews sysNews) {
        sysNewsMapper.insert(sysNews);
    }

    /**
     * 
     * Class Name: SysNewsServiceImpl.java
     * 
     * @Description: 根据公告Id修改
     * @author zhang.zx
     * @date 2015年3月31日 下午4:58:56
     * @version 1.0
     * @param sysNews
     * @return void 返回类型
     */
    @Override
    public void update(SysNews sysNews) {
        sysNewsMapper.update(sysNews);
    }

    /**
     * 
     * Class Name: SysNewsServiceImpl.java
     * 
     * @Description: 根据公告Id查询
     * @author zhang.zx
     * @date 2015年3月31日 下午4:58:56
     * @version 1.0
     * @param id
     * @return SysNews 返回类型
     */
    @Override
    public SysNews findSysNewsById(Integer id) {
        return sysNewsMapper.findById(id);
    }

    @Override
    public List<SysNews> findSysNewsByPage(SysNews search) {
        return sysNewsMapper.page(search);
    }

    @Override
    public void batchInsert(List<SysNews> sysNews) {
        // TODO Auto-generated method stub
        sysNewsMapper.batchInsert(sysNews);
    }

    @Override
    public void deleteSysNewsById(Integer id) {
        // TODO Auto-generated method stub
        sysNewsMapper.deleteById(id);
    }

    @Override
    public void deleteSysNewsByIds(Integer[] ids) {
        // TODO Auto-generated method stub
        sysNewsMapper.deleteByIds(ids);
    }

    /**
     * 
     * Class Name: SysNewsServiceImpl.java
     * 
     * @Description: 分页查询公告信息
     * @author zhang.zx
     * @date 2015年3月31日 下午4:58:56
     * @version 1.0
     * @param search
     * @return PageFinder<SysNews> 返回类型
     */
    @Override
    public PageFinder<SysNewsVo> findSysNewByPageByKeys(SysNewsVo search) {
        List<SysNewsVo> sysNewsList = sysNewsMapper.page2(search);
        int count = sysNewsMapper.pageCount2(search);
        PageFinder<SysNewsVo> pageFinder = new PageFinder<SysNewsVo>(search.getPage(), search.getPageSize(), count, sysNewsList);
        return pageFinder;
    }

    /**
     * 
     * Class Name: SysNewsServiceImpl.java
     * 
     * @Description: 改变公告状态信息
     * @author zhang.zx
     * @date 2015年3月31日 下午4:58:56
     * @version 1.0
     * @param sysNews
     * @return void 返回类型
     */
    @Override
    public void changNewsStatus(SysNewsVo sysNews) {
        sysNewsMapper.changNewsStatus(sysNews);
    }

    /**
     * 
     * Class Name: SysNewsServiceImpl.java
     * 
     * @Description: 添加公告
     * @author zhang.zx
     * @date 2015年3月31日 下午4:58:56
     * @version 1.0
     * @param sysNews
     * @return void 返回类型
     */
    @Override
    public void addNews(SysNewsVo sysNews) {
        sysNewsMapper.addNews(sysNews);
    }

    /**
     * 
     * Class Name: SysNewsServiceImpl.java
     * 
     * @Description: 添加公告
     * @author zhang.zx
     * @date 2015年3月31日 下午4:58:56
     * @version 1.0
     * @param sysNews
     * @return void 返回类型
     */
    @Override
    public List<SysNewsVo> loadData(SysNewsVo search) {
        List<SysNewsVo> vo = sysNewsMapper.findList(search);
        return vo;
    }

    @Override
    public List<SysNews> findNewsByCompanyId(Integer companyId) {
        // TODO Auto-generated method stub
        return sysNewsMapper.findNewsByCompanyId(companyId);
    }

    @Override
    public List<CompanyFunctionSetVo> queryCompanyFunctionById(Integer companyId) {
        // TODO Auto-generated method stub
        return sysNewsMapper.queryCompanyFunctionById(companyId);
    }

    @Override
    public void editCompanyFunctions(CompanyFunctionSetVo fun) {
        // 判断公司是否存在
        CompanyFunctionSet companyFunctionSet = new CompanyFunctionSet();
        companyFunctionSet.setCompanyId(fun.getCompanyId());
        companyFunctionSet.setFunctionCode(fun.getFunctionCode());
        List<CompanyFunctionSet> companyFunctionSetList = companyFunctionSetServiceImpl.findCompanyFunctionSetByPage(companyFunctionSet);
        CompanyFunctionSet cfs = companyFunctionSetList != null && companyFunctionSetList.size() > 0 ? companyFunctionSetList.get(0) : null;
        if (null != cfs && null != cfs.getId()) {
            fun.setId(cfs.getId());
            sysNewsMapper.updateCompanyFunction(fun);
        } else {
            sysNewsMapper.insertCompanyFunction(fun);
        }
        // if(null!=fun.getId()&&!"".equals(fun.getId())){
        // sysNewsMapper.updateCompanyFunction(fun);
        // }
    }

    @Override
    public void updateDelFlag(SysNews sysNews) {
        // TODO Auto-generated method stub
        sysNewsMapper.updateDelFlag(sysNews);
    }

    @Override
    public List<SysNewsVo> guanwangNewsList(SysNewsVo search) {
        return sysNewsMapper.guanwangNewsList(search);
    }

    @Override
    public int guanwangNewsListCount(SysNewsVo search) {
        return sysNewsMapper.guanwangNewsListCount(search);
    }

    @Override
    public List<SysNewsVo> guanwangNewsList2(SysNewsVo search) {
        return sysNewsMapper.guanwangNewsList2(search);
    }

    @Override
    public List<SysNews> findSysNewsList(SysNewsVo search) {
        return sysNewsMapper.queryShowNews(search);
    }

}
