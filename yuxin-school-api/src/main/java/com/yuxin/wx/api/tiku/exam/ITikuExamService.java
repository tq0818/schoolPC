package com.yuxin.wx.api.tiku.exam;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.exam.TikuExam;
/**
 * Service Interface:TikuExam
 * @author wang.zx
 * @date 2016-2-17
 */
public interface ITikuExamService  {
	/**
	 * 
	* @Title: saveTikuExam
	* @Description: 新增TikuExam
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	void insert(TikuExam entity);
	
	/**
	 * 
	* @Title: batchSaveTikuExam 
	* @Description: 批量新增TikuExam
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	void batchInsert(List<TikuExam> list);
	
	/**
	 * 
	* @Title: updateTikuExam 
	* @Description: 编辑TikuExam
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	void update(TikuExam entity);
	
	void updateById(TikuExam entity);
	/**
	 * 
	* @Title: deleteTikuExamById 
	* @Description: 根据id删除TikuExam
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	void deleteTikuExamById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuExamByIds 
	* @Description: 根据id批量删除TikuExam
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	void deleteTikuExamByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuExamById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	TikuExam findTikuExamById(Integer id);
	
	/**
	 * 
	* @Title: findTikuExamByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuExam>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	List<TikuExam> findTikuExamByPage(TikuExam search);
	/**
	 * 
	 * Class Name: ITikuExamService.java
	 * @Description: 查询考试列表方法(分页)
	 * @author yuchanglong
	 * @date 2016年2月17日 下午4:33:53
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<TikuExam> findTikuExamsByPage(TikuExam search);
}