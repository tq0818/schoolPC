package com.yuxin.wx.tiku.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuExampoint;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuExampointMapper extends BaseMapper<TikuExampoint> {

	List<TikuExampoint> findTikuExampoint(TikuExampoint search);
	Integer getCPointCount(Integer pointId);
	void delByPid(Integer pid);

	/**
	 * 
	 * Class Name: ITikuExampointService.java
	 * @Description: 查询点
	 * @author 周文斌
	 * @date 2015-7-10 下午2:18:31
	 * @version 1.0
	 * @param sectionId
	 * @return
	 */
	List<TikuExampoint> findPointBySectionId(Integer sectionId);

	void deleteBySubId(Integer subId);
	/**
	 * 
	 * Class Name: TikuExampointMapper.java
	 * @Description: 根据题库类别id删除
	 * @author yuchanglong
	 * @date 2015年9月10日 下午12:34:05
	 * @version 1.0
	 * @param tikuId
	 */
	void deleteByTikuId(Integer tikuId);
}