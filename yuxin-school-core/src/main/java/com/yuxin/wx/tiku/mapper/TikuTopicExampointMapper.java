package com.yuxin.wx.tiku.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuTopicExampoint;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuTopicExampointMapper extends BaseMapper<TikuTopicExampoint> {

	/**
	 * 
	 * Class Name: ITikuTopicExampointService.java
	 * @Description: 查询试题章节信息
	 * @author 周文斌
	 * @date 2015-7-10 下午12:41:07
	 * @version 1.0
	 * @param topicId
	 * @return
	 */
	TikuTopicExampoint findPointByTopicId(Integer topicId);

	/**
	 * 
	 * Class Name: ITikuTopicExampointService.java
	 * @Description: 查询关系id试题与 章节考点
	 * @author 周文斌
	 * @date 2015-7-16 下午12:32:13
	 * @version 1.0
	 * @param topicId
	 * @return
	 */
	Integer findIdByTopicId(Integer topicId);

	/**
	 * 
	 * Class Name: ITikuTopicExampointService.java
	 * @Description: 删除试题相关
	 * @author 周文斌
	 * @date 2015-7-17 下午2:23:38
	 * @version 1.0
	 * @param topicId
	 */
	void deleteByTopicId(List<Integer> list);
}