package com.yuxin.wx.api.queAns;

import java.util.List;

import com.yuxin.wx.model.queAns.QuestionAnswer;
/**
 * Service Interface:QuestionAnswer
 * @author wang.zx
 * @date 2015-12-9
 */
public interface IQuestionAnswerService  {
	/**
	 * 
	* @Title: saveQuestionAnswer
	* @Description: 新增QuestionAnswer
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void insert(QuestionAnswer entity);
	
	/**
	 * 
	* @Title: batchSaveQuestionAnswer 
	* @Description: 批量新增QuestionAnswer
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void batchInsert(List<QuestionAnswer> list);
	
	/**
	 * 
	* @Title: updateQuestionAnswer 
	* @Description: 编辑QuestionAnswer
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void update(QuestionAnswer entity);
	
	/**
	 * 
	* @Title: deleteQuestionAnswerById 
	* @Description: 根据id删除QuestionAnswer
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void deleteQuestionAnswerById(Integer id);
	
	/**
	 * 
	* @Title: deleteQuestionAnswerByIds 
	* @Description: 根据id批量删除QuestionAnswer
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void deleteQuestionAnswerByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findQuestionAnswerById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	QuestionAnswer findQuestionAnswerById(Integer id);
	
	/**
	 * 
	* @Title: findQuestionAnswerByPage 
	* @Description: 分页查询
	* @return
	* @return List<QuestionAnswer>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	List<QuestionAnswer> findQuestionAnswerByPage(QuestionAnswer search);
	
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