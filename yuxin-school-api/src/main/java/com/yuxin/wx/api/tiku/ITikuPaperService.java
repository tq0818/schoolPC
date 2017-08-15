package com.yuxin.wx.api.tiku;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.PageFinder2;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.vo.tiku.TikuExamPaperVo;
import com.yuxin.wx.vo.tiku.TikuPaperVo;
/**
 * Service Interface:TikuPaper
 * @author wang.zx
 * @date 2015-7-8
 */
public interface ITikuPaperService  {
	/**
	 * 
	* @Title: saveTikuPaper
	* @Description: 新增TikuPaper
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void insert(TikuPaper entity);
	
	/**
	 * 
	* @Title: batchSaveTikuPaper 
	* @Description: 批量新增TikuPaper
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void batchInsert(List<TikuPaper> list);
	
	/**
	 * 
	* @Title: updateTikuPaper 
	* @Description: 编辑TikuPaper
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void update(TikuPaper entity);
	
	/**
	 * 
	* @Title: deleteTikuPaperById 
	* @Description: 根据id删除TikuPaper
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuPaperById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuPaperByIds 
	* @Description: 根据id批量删除TikuPaper
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuPaperByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuPaperById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	TikuPaper findTikuPaperById(Integer id);
	
	/**
	 * 
	* @Title: findTikuPaperByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuPaper>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	List<TikuPaper> findTikuPaperByPage(TikuPaper search);
	/**
	 * 
	 * Class Name: ITikuPaperService.java
	 * @Description: 试卷首页查询方法，多查询一个审核者名称
	 * @author yuchanglong
	 * @date 2015年7月9日 下午3:41:43
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<TikuPaper> findTikuUserByPage(TikuPaper search);
	
	/**
	 * 
	 * Class Name: ITikuPaperService.java
	 * @Description: 试卷首页查询方法，多查询一个审核者名称
	 * @author yuchanglong
	 * @date 2015年7月9日 下午3:41:43
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<TikuPaperVo> findTikuPaperVoByPage(TikuPaper search);
	
	/**
	 * 
	 * Class Name: ITikuPaperService.java
	 * @Description: 查询考试下的试卷
	 * @author 周文斌
	 * @date 2016-2-17 下午8:21:57
	 * @version 1.0
	 * @param paper
	 * @return
	 */
	List<TikuExamPaperVo> findPaperByExam(TikuPaper paper);
	
	/**
	 * 
	 * Class Name: ITikuPaperService.java
	 * @Description: 查询考试下的试卷总数
	 * @author 周文斌
	 * @date 2016-2-17 下午8:21:57
	 * @version 1.0
	 * @param paper
	 * @return
	 */
	Integer findPaperByExamCount(TikuPaper paper);

	PageFinder2<TikuPaperVo> containhw(TikuPaper search);

	List<TikuPaper> queryAll();

}