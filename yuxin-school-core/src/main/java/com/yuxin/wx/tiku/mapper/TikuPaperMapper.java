package com.yuxin.wx.tiku.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.vo.tiku.TikuExamPaperVo;
import com.yuxin.wx.vo.tiku.TikuPaperVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuPaperMapper extends BaseMapper<TikuPaper> {
	List<TikuPaper> findTikuUserByPage(TikuPaper search);
	/**
	 * 
	 * Class Name: TikuPaperMapper.java
	 * @Description: 试卷首页查询分页数量方法
	 * @author yuchanglong
	 * @date 2015年7月9日 下午3:51:29
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Integer TUCount(TikuPaper search);
	
	List<TikuPaperVo> pagevo(TikuPaper search);
	

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
	List<TikuPaperVo> containhw(TikuPaper search);
	Integer containhwCount(TikuPaper search);
}