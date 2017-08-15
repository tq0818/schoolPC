package com.yuxin.wx.tiku.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.TikuUserExercise;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswerStatistics;
import com.yuxin.wx.vo.tiku.TikuUserExerVo;
import com.yuxin.wx.vo.tiku.TikuUserExerciseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Service Interface:Users
 *
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuUserExerciseAnswerStatisticsMapper extends BaseMapper<TikuUserExerciseAnswerStatistics> {
     List<Integer> queryAllData();
}