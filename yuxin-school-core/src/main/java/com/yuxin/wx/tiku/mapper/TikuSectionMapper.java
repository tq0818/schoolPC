package com.yuxin.wx.tiku.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuSection;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuSectionMapper extends BaseMapper<TikuSection> {
	List<TikuSection> findTikuSection(TikuSection search);
	Integer secCount(Integer secId);
	/**
	 * 
	 * Class Name: ITikuSectionService.java
	 * @Description: 查询节
	 * @author 周文斌
	 * @date 2015-7-10 下午2:13:06
	 * @version 1.0
	 * @param chapterId
	 * @return
	 */
	List<TikuSection> findSectionByChapterId(Integer chapterId);
	
	
	
	Integer getMaxSectionOrder(Integer chapterId);
	
	void deleteBySubId(Integer subId);
	/**
	 * 
	 * Class Name: TikuSectionMapper.java
	 * @Description: 根据题库类别id删除
	 * @author yuchanglong
	 * @date 2015年9月10日 下午12:37:27
	 * @version 1.0
	 * @param tikuId
	 */
	void deleteByTikuId(Integer tikuId);
}