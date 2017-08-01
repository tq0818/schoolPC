package com.yuxin.wx.tiku.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuTopicOption;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuTopicOptionMapper extends BaseMapper<TikuTopicOption> {

	/**
	 * 
	 * Class Name: ITikuTopicOptionService.java
	 * @Description: 查询试题选项
	 * @author 周文斌
	 * @date 2015-7-10 上午10:24:45
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<TikuTopicOption> findOptionByTopicId(Integer topicId);

	/**
	 * 
	 * Class Name: ITikuTopicOptionService.java
	 * @Description: 删除试题 选项相关
	 * @author 周文斌
	 * @date 2015-7-17 下午2:26:45
	 * @version 1.0
	 * @param topicId
	 */
	void deleteByTopicId(List<Integer> list);
	
	/**
	 * 批量查询试题选项
	 * @param list
	 * @return
	 */
	List<TikuTopicOption> findOptionByListTopicId(List<Integer> list);
}