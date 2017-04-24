package com.yuxin.wx.tiku.exam.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.exam.TikuExam;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuExamMapper extends BaseMapper<TikuExam> {
	List<TikuExam> findTikuExamsByPage(TikuExam search);	
	
	void updateById(TikuExam entity);
}