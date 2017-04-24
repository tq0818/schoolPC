package com.yuxin.wx.queAns.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.queAns.QuestionAnswer;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface QuestionAnswerMapper extends BaseMapper<QuestionAnswer> {
	
	/**
	 * 
	 * Class Name: IQuestionAnswerService.java
	 * @Description: 查询回复，根据问题id，等级
	 * @author 周文斌
	 * @date 2015-12-9 下午12:44:53
	 * @version 1.0
	 * @param ans
	 * @return
	 */
	List<QuestionAnswer> findAnsByQueId(QuestionAnswer ans);

	/**
	 * 
	 * Class Name: IQuestionAnswerService.java
	 * @Description: 根据回复id 查询二级回复list
	 * @author 周文斌
	 * @date 2015-12-10 下午12:21:34
	 * @version 1.0
	 * @param answerId
	 * @return
	 */
	List<Integer> findTwoAns(Integer answerId);

	/**
	 * 
	 * Class Name: IQuestionAnswerService.java
	 * @Description: 批量假删除  回复
	 * @author 周文斌
	 * @date 2015-12-10 下午12:27:12
	 * @version 1.0
	 * @param list
	 */
	void updateList(List<Integer> list);

	/**
	 * 
	 * Class Name: IQuestionAnswerService.java
	 * @Description: 根据回复id 查询二级回复list
	 * @author 周文斌
	 * @date 2015-12-10 下午5:34:59
	 * @version 1.0
	 * @param answerId
	 * @return
	 */
	List<QuestionAnswer> findEntityTwoAns(Integer answerId);
	/**
	 * 
	 * Class Name: IQuestionAnswerService.java
	 * @Description: 查询总个数
	 * @author 周文斌
	 * @date 2015-12-10 下午7:24:57
	 * @version 1.0
	 * @param ans
	 * @return
	 */
	Integer findAnsCountByQueId(QuestionAnswer ans);
}