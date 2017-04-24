package com.yuxin.wx.api.homework;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.homework.HomeworkStudentComplete;
import com.yuxin.wx.vo.homework.StudentHomeWorkVo;
/**
 * Service Interface:HomeworkStudentComplete
 * @author chopin
 * @date 2016-12-15
 */
import com.yuxin.wx.vo.homework.StudentHomeworkAttachmentVo;

public interface IHomeworkStudentCompleteService  {
	/**
	 * 
	* @Title: saveHomeworkStudentComplete
	* @Description: 新增HomeworkStudentComplete
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void insert(HomeworkStudentComplete entity);
	
	/**
	 * 
	* @Title: batchSaveHomeworkStudentComplete 
	* @Description: 批量新增HomeworkStudentComplete
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void batchInsert(List<HomeworkStudentComplete> list);
	
	/**
	 * 
	* @Title: updateHomeworkStudentComplete 
	* @Description: 编辑HomeworkStudentComplete
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void update(HomeworkStudentComplete entity);
	
	/**
	 * 
	* @Title: deleteHomeworkStudentCompleteById 
	* @Description: 根据id删除HomeworkStudentComplete
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void deleteHomeworkStudentCompleteById(Integer id);
	
	/**
	 * 
	* @Title: deleteHomeworkStudentCompleteByIds 
	* @Description: 根据id批量删除HomeworkStudentComplete
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	void deleteHomeworkStudentCompleteByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findHomeworkStudentCompleteById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	HomeworkStudentComplete findHomeworkStudentCompleteById(Integer id);
	
	/**
	 * 
	* @Title: findHomeworkStudentCompleteByPage 
	* @Description: 分页查询
	* @return
	* @return List<HomeworkStudentComplete>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by wangzx
	 */
	List<HomeworkStudentComplete> findHomeworkStudentCompleteByPage(HomeworkStudentComplete search);
	
	
	/**
	 * 
	 * Class Name: IHomeworkStudentCompleteService.java
	 * @Description: 分页学员作业
	 * @author dongshuai
	 * @date 2016年12月16日 上午10:47:07
	 * @modifier
	 * @modify-date 2016年12月16日 上午10:47:07
	 * @version 1.0
	 * @param shv
	 * @return
	 */
	PageFinder<StudentHomeWorkVo> queryStudentHomeworks(StudentHomeWorkVo shv);
	
	/**
	 * 
	 * Class Name: IHomeworkStudentCompleteService.java
	 * @Description: 统计
	 * @author dongshuai
	 * @date 2016年12月16日 上午11:07:01
	 * @modifier
	 * @modify-date 2016年12月16日 上午11:07:01
	 * @version 1.0
	 * @param shv
	 * @return
	 */
	StudentHomeWorkVo queryAllStudentHomework(StudentHomeWorkVo shv);
	
	/**
	 * 
	 * Class Name: IHomeworkStudentCompleteService.java
	 * @Description: 查询作业回答与批阅
	 * @author dongshuai
	 * @date 2016年12月16日 下午6:06:52
	 * @modifier
	 * @modify-date 2016年12月16日 下午6:06:52
	 * @version 1.0
	 * @param shav
	 * @return
	 */
	List<StudentHomeworkAttachmentVo> queryStudentHomeworkAndReadList(StudentHomeworkAttachmentVo shav);

	List<HomeworkStudentComplete> findByHomeworkId(StudentHomeWorkVo homeworkId);
}