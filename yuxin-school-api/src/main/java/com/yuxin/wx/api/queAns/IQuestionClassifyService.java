package com.yuxin.wx.api.queAns;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.queAns.QuestionAnswer;
import com.yuxin.wx.model.queAns.QuestionClassify;
/**
 * Service Interface:QuestionClassifyVo
 * @author wang.zx
 * @date 2015-12-9
 */
public interface IQuestionClassifyService  {
	/**
	 * 
	* @Title: saveQuestionClassify
	* @Description: 新增QuestionClassify
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void insert(QuestionClassify entity);
	
	/**
	 * 
	* @Title: batchSaveQuestionClassify 
	* @Description: 批量新增QuestionClassify
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void batchInsert(List<QuestionClassify> list);
	
	/**
	 * 
	* @Title: updateQuestionClassify 
	* @Description: 编辑QuestionClassify
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void update(QuestionClassify entity);
	
	/**
	 * 
	* @Title: deleteQuestionClassifyById 
	* @Description: 根据id删除QuestionClassify
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void deleteQuestionClassifyById(Integer id);
	
	/**
	 * 
	* @Title: deleteQuestionClassifyByIds 
	* @Description: 根据id批量删除QuestionClassify
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	void deleteQuestionClassifyByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findQuestionClassifyById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	QuestionClassify findQuestionClassifyById(Integer id);
	
	/**
	 * 
	* @Title: findQuestionClassifyByPage 
	* @Description: 分页查询
	* @return
	* @return List<QuestionClassifyVo>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by wangzx
	 */
	List<QuestionClassify> findQuestionClassifyByPage(QuestionClassify search);
	
	/**
	 * 
	 * Class Name: IQuestionClassifyService.java
	 * @Description: 不分页查询
	 * @author yuchanglong
	 * @date 2015年12月10日 下午4:44:10
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<QuestionClassify> findQuestionClassify(QuestionClassify search);
	
	/**
	 * 
	 * Class Name: IQuestionClassifyService.java
	 * @Description: 课程问答设置下面课程标签
	 * @author yuchanglong
	 * @date 2015年12月25日 下午1:14:51
	 * @version 1.0
	 * @param search
	 * @return
	 */
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