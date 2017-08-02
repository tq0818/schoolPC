package com.yuxin.wx.api.tiku;

import com.yuxin.wx.model.tiku.TikuUserExerciseAnswerStatistics;

import java.util.List;

/**
 * Service Interface:TikuUserExerciseAnswerStatistics
 *
 * @author wang.zx
 * @date 2015-8-24
 */
public interface ITikuUserExerciseAnswerStatisticsService {
    /**
     *
     * @Title: saveTikuUserExerciseAnswerStatistics
     * @Description: 新增TikuUserExerciseAnswerStatistics
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void insert(TikuUserExerciseAnswerStatistics entity);

    /**
     *
     * @Title: batchSaveTikuUserExerciseAnswer
     * @Description: 批量新增TikuUserExerciseAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void batchInsert(List<TikuUserExerciseAnswerStatistics> list);

    /**
     *
     * @Title: updateTikuUserExerciseAnswer
     * @Description: 编辑TikuUserExerciseAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void update(TikuUserExerciseAnswerStatistics entity);

    void deleteByIds(Integer[] ids);

    List<Integer> queryAllData();
}