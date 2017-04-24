package com.yuxin.wx.tiku.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuChapter;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuChapterMapper extends BaseMapper<TikuChapter> {
	List<TikuChapter> findTikuChapter(TikuChapter search);
	Integer selectSecByChapter(Integer chapterId);

	/**
	 * 
	 * Class Name: ITikuChapterService.java
	 * @Description: 查询章
	 * @author 周文斌
	 * @date 2015-7-10 下午1:58:13
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<TikuChapter> findChapterByCond(Map<String, Object> param);
	
	Integer findMaxChapterOrder(Integer subId);
	
	Integer findInfoBySubId(Integer subId);
	
	void deleteBySubId(Integer subId);
	/**
	 * 
	 * Class Name: TikuChapterMapper.java
	 * @Description:根据题库类别id删除
	 * @author yuchanglong
	 * @date 2015年9月10日 下午12:39:49
	 * @version 1.0
	 * @param tikuId
	 */
	void deleteByTikuId(Integer tikuId);
}