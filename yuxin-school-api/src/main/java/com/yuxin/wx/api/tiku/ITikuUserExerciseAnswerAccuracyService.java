package com.yuxin.wx.api.tiku;

import com.yuxin.wx.model.tiku.TikuUserExerciseAnswerAccuracy;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswerStatistics;

import java.util.List;

/**
 * Service Interface:TikuUserExerciseAnswerStatistics
 *
 * @author wang.zx
 * @date 2015-8-24
 */
public interface ITikuUserExerciseAnswerAccuracyService {
    /**
     *
     * @Title: saveTikuUserExerciseAnswerStatistics
     * @Description: 新增TikuUserExerciseAnswerStatistics
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void insert(TikuUserExerciseAnswerAccuracy entity);

    /**
     *
     * @Title: batchSaveTikuUserExerciseAnswer
     * @Description: 批量新增TikuUserExerciseAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void batchInsert(List<TikuUserExerciseAnswerAccuracy> list);

    /**
     *
     * @Title: updateTikuUserExerciseAnswer
     * @Description: 编辑TikuUserExerciseAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void update(TikuUserExerciseAnswerAccuracy entity);

    void deleteByIds(Integer[] ids);

    List<Integer> queryAllData();
}