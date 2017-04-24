package com.yuxin.wx.tiku.mapper;

import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuPaperTopicType;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuPaperTopicTypeMapper extends BaseMapper<TikuPaperTopicType> {
	

	/**
	 * 
	 * Class Name: ITikuPaperTopicTypeService.java
	 * @Description: 查询当前试卷题型的分数
	 * @author 周文斌
	 * @date 2015-7-23 下午9:19:17
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Double findScoreByPaperid(Map<String, Object> param);

	/**
	 * 
	 * Class Name: ITikuPaperTopicTypeService.java
	 * @Description: 查询分数试卷关系对象
	 * @author 周文斌
	 * @date 2015-7-24 下午1:08:21
	 * @version 1.0
	 * @param param
	 * @return
	 */
	TikuPaperTopicType findScoreById(Map<String, Object> param);
}