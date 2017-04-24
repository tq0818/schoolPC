package com.yuxin.wx.api.tiku.exam;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.tiku.exam.TikuExamPaperRelation;
import com.yuxin.wx.vo.tiku.exam.TikuExamPaperRelationVo;
/**
 * Service Interface:TikuExamPaperRelation
 * @author wang.zx
 * @date 2016-2-17
 */
public interface ITikuExamPaperRelationService  {
	/**
	 * 
	* @Title: saveTikuExamPaperRelation
	* @Description: 新增TikuExamPaperRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	void insert(TikuExamPaperRelation entity);
	
	/**
	 * 
	* @Title: batchSaveTikuExamPaperRelation 
	* @Description: 批量新增TikuExamPaperRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	void batchInsert(List<TikuExamPaperRelation> list);
	
	/**
	 * 
	* @Title: updateTikuExamPaperRelation 
	* @Description: 编辑TikuExamPaperRelation
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	void update(TikuExamPaperRelation entity);
	
	/**
	 * 
	* @Title: deleteTikuExamPaperRelationById 
	* @Description: 根据id删除TikuExamPaperRelation
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	void deleteTikuExamPaperRelationById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuExamPaperRelationByIds 
	* @Description: 根据id批量删除TikuExamPaperRelation
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	void deleteTikuExamPaperRelationByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuExamPaperRelationById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	TikuExamPaperRelation findTikuExamPaperRelationById(Integer id);
	
	/**
	 * 
	* @Title: findTikuExamPaperRelationByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuExamPaperRelation>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-2-17
	* @user by wangzx
	 */
	List<TikuExamPaperRelation> findTikuExamPaperRelationByPage(TikuExamPaperRelation search);
	
	/**
	 * 
	 * Class Name: ITikuExamPaperRelationService.java
	 * @Description: 查询当前考试使用的试卷
	 * @author 周文斌
	 * @date 2016-2-17 下午6:52:10
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<TikuExamPaperRelationVo> findPaperByExamId(Integer id);
	/**
	 * 
	 * Class Name: ITikuExamPaperRelationService.java
	 * @Description:查询该考试下存在多少试卷
	 * @author yuchanglong
	 * @date 2016年2月17日 下午6:07:25
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Integer findExamHavePaperCount(TikuExamPaperRelation search);
	/**
	 * 
	 * Class Name: ITikuExamPaperRelationService.java
	 * @Description: 统计列表分页查询
	 * @author yuchanglong
	 * @date 2016年2月17日 下午6:59:53
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<TikuExamPaperRelationVo> findByPage(TikuExamPaperRelation search);
	/**
	 * 
	 * Class Name: ITikuExamPaperRelationService.java
	 * @Description: 统计列表的考试通过率等
	 * @author yuchanglong
	 * @date 2016年2月18日 上午10:04:36
	 * @version 1.0
	 * @param examId
	 * @return
	 */
	TikuExamPaperRelationVo findRateByExamId(Integer examId);
	
	/**
	 * 
	 * Class Name: ITikuExamPaperRelationService.java
	 * @Description: 查询是否存在
	 * @author 周文斌
	 * @date 2016-2-19 下午3:14:35
	 * @version 1.0
	 * @param param
	 * @return
	 */
	TikuExamPaperRelation findExist(Map<String, Object> param);
	/**
	 * 
	 * Class Name: ITikuExamPaperRelationService.java
	 * @Description: 查询该考试下拥有多少套试卷
	 * @author yuchanglong
	 * @date 2016年2月24日 下午7:18:40
	 * @version 1.0
	 * @param examId
	 * @return
	 */
	Integer examPaperCount(Integer examId);
}