package com.yuxin.wx.queAns.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.queAns.QuestionAnswer;
import com.yuxin.wx.model.queAns.QuestionClassify;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface QuestionClassifyMapper extends BaseMapper<QuestionClassify> {
	List<QuestionClassify> findQuestionClassify(QuestionClassify search);
	List<QuestionClassify> findQuestionClassifyKc(QuestionClassify search);
	

	/**
	 * 
	 * Class Name: IQuestionAnswerService.java
	 * @Description: 查询标签
	 * @author 周文斌
	 * @date 2015-12-29 下午8:23:17
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<QuestionClassify> findClassifyByCompany(Map<String, Object> param);
}