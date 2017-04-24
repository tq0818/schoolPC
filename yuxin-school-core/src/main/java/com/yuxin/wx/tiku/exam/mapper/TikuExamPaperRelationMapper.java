package com.yuxin.wx.tiku.exam.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.exam.TikuExamPaperRelation;
import com.yuxin.wx.vo.tiku.exam.TikuExamPaperRelationVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuExamPaperRelationMapper extends BaseMapper<TikuExamPaperRelation> {
	List<TikuExamPaperRelationVo> byPage(TikuExamPaperRelation paperRelation);

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
	 * Class Name: TikuExamPaperRelationMapper.java
	 * @Description: 统计列表的考试通过率等
	 * @author yuchanglong
	 * @date 2016年2月18日 上午10:03:51
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
	
	Integer examPaperCount(Integer examId);
}