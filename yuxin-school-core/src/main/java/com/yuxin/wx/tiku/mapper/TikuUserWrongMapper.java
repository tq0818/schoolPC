package com.yuxin.wx.tiku.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuUserWrong;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuUserWrongMapper extends BaseMapper<TikuUserWrong> {
	
	Integer findWrongByUserIdAndSubjectId(@Param("userId") Integer userId, @Param("subjectId") Integer subjectId);
	
	void deleteTikuUserWrongById(Integer userId, Integer topicId);
	
	List<TikuUserWrong> findTikuUserWrongByUserIdAndSubjectId(@Param("userId") Integer userId, @Param("subjectId") Integer subjectId);

	/**
	 * 
	 * Class Name: ITikuUserWrongService.java
	 * @Description: 查询科目下 错题
	 * @author 周文斌
	 * @date 2015-8-28 下午7:03:46
	 * @version 1.0
	 * @param wrong
	 * @return
	 */
	List<TikuUserWrong> findTopicByWrong(TikuUserWrong wrong);
	
	Integer isOnly(TikuUserWrong wrong);
}