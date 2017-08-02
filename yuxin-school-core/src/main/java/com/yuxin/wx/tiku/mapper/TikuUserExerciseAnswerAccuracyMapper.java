package com.yuxin.wx.tiku.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswerAccuracy;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswerStatistics;

import java.util.List;

/**
 * Service Interface:Users
 *
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuUserExerciseAnswerAccuracyMapper extends BaseMapper<TikuUserExerciseAnswerAccuracy> {
    List<Integer> queryAllData();
}