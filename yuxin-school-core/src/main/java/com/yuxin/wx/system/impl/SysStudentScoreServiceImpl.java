package com.yuxin.wx.system.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.system.ISysStudentScoreService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysStudentScore;
import com.yuxin.wx.system.mapper.SysStudentScoreMapper;

/**
 * Service Implementation:SysStudentScore
 * @author chopin
 * @date 2016-11-16
 */
@Service
@Transactional
public class SysStudentScoreServiceImpl extends BaseServiceImpl implements ISysStudentScoreService {
	
	Log log = LogFactory.getLog("log");
	
	@Autowired
	private SysStudentScoreMapper sysStudentScoreMapper;
	
	/**
	 * 
	* @Title: saveSysStudentScore
	* @Description: 新增SysStudentScore
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by chopin
	 */
	@Override
	public void insert(SysStudentScore entity){
		sysStudentScoreMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveSysStudentScore 
	* @Description: 批量新增SysStudentScore
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<SysStudentScore> entity){
		sysStudentScoreMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateSysStudentScore 
	* @Description: 编辑SysStudentScore
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by chopin
	 */
	@Override
	public void update(SysStudentScore entity){
		sysStudentScoreMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteSysStudentScoreById 
	* @Description: 根据id删除SysStudentScore
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by chopin
	 */
	 @Override
	public void deleteSysStudentScoreById(Integer id){
		sysStudentScoreMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteSysStudentScoreByIds 
	* @Description: 根据id批量删除SysStudentScore
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by chopin
	 */
	@Override
	public void deleteSysStudentScoreByIds(Integer[] ids){
		sysStudentScoreMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findSysStudentScoreById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by chopin
	 */
	@Override
	public SysStudentScore findSysStudentScoreById(Integer id){
		return sysStudentScoreMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findSysStudentScoreByPage 
	* @Description: 分页查询
	* @return
	* @return List<SysStudentScore>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-11-16
	* @user by chopin
	 */
	@Override
	public List<SysStudentScore> findSysStudentScoreByPage(SysStudentScore search){
		return sysStudentScoreMapper.page(search);
	}

	@SuppressWarnings("finally")
	@Override
	public boolean importCompetitionScore(List<List<String>> list,Integer companyId) {
		boolean result = true;
		
		try {
			for (int i = 1; i < list.size(); i++) {
				List<String> csList = list.get(i);
				SysStudentScore sss = new SysStudentScore();
				sss.setName(csList.get(0));
				sss.setCode(csList.get(1));
				sss.setIdCode(csList.get(2));
				sss.setGrade(csList.get(3));
				sss.setScore(csList.get(4));
				sss.setAwards(csList.get(5));
				sss.setLink(csList.get(6));
				sss.setCompanyId(companyId);
				sysStudentScoreMapper.insert(sss);
			}
		} catch (Exception e) {
			result = false;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
			log.error("竞赛成绩导入数据出错",e);
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	@Override
	public PageFinder<SysStudentScore> querySysStudentScoreData(SysStudentScore search) {
		List<SysStudentScore> data = sysStudentScoreMapper.querySysStudentScoreList(search);
		int count = sysStudentScoreMapper.querySysStudentScoreListCount(search);
		PageFinder<SysStudentScore> pageFinder = new PageFinder<SysStudentScore>(search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	};
	
	
}
