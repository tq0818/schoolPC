package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysStudentScore;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysStudentScoreMapper extends BaseMapper<SysStudentScore> {
	
	/**
	 * 
	 * Class Name: SysStudentScoreMapper.java
	 * @Description: 竞赛成绩列表list
	 * @author dongshuai
	 * @date 2016年11月17日 下午8:18:48
	 * @modifier
	 * @modify-date 2016年11月17日 下午8:18:48
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysStudentScore> querySysStudentScoreList(SysStudentScore search);
	
	/**
	 * 
	 * Class Name: SysStudentScoreMapper.java
	 * @Description: 竞赛成绩列表个数
	 * @author dongshuai
	 * @date 2016年11月17日 下午8:19:07
	 * @modifier
	 * @modify-date 2016年11月17日 下午8:19:07
	 * @version 1.0
	 * @param search
	 * @return
	 */
	int querySysStudentScoreListCount(SysStudentScore search);
}