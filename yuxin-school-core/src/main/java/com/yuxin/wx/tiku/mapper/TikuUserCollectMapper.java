package com.yuxin.wx.tiku.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuUserCollect;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuUserCollectMapper extends BaseMapper<TikuUserCollect> {
	
	Integer findCollectCountByUserIdAndSubjectId(@Param("userId") Integer userId, @Param("subjectId") Integer subjectId);

	/**
	 * 
	 * Class Name: ITikuUserCollectService.java
	 * @Description: 查询是否收藏
	 * @author 周文斌
	 * @date 2015-8-26 下午6:59:35
	 * @version 1.0
	 * @param collect
	 * @return
	 */
	Integer findCollectByMore(TikuUserCollect collect);

	/**
	 * 
	 * Class Name: ITikuUserCollectService.java
	 * @Description: 查询用户收藏
	 * @author 周文斌
	 * @date 2015-8-27 下午4:41:11
	 * @version 1.0
	 * @param collect
	 * @return
	 */
	List<TikuUserCollect> findTopicByCollect(TikuUserCollect collect);
}