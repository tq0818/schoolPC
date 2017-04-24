package com.yuxin.wx.tiku.exam.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.exam.TikuExamUserRelation;
import com.yuxin.wx.vo.tiku.exam.TikuExamUserRelationVo;

/**
 * Service Interface:Users
 *
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuExamUserRelationMapper extends BaseMapper<TikuExamUserRelation> {
    List<TikuExamUserRelationVo> byPage(TikuExamUserRelationVo relation);

    TikuExamUserRelation findByExerciseId(Integer exerciseId);

    Integer findExamUserCount(TikuExamUserRelationVo relationVo);

    Map<String, Object> statisticRspdByPaper(TikuPaper paper);
}