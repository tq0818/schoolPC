package com.yuxin.wx.tiku.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswer;

/**
 * Service Interface:Users
 *
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuUserExerciseAnswerMapper extends BaseMapper<TikuUserExerciseAnswer> {

    Integer findExerciseAnswerCountByUserIdAndSubjectId(@Param("userId") Integer userId, @Param("subjectId") Integer subjectId);

    /**
     *
     * Class Name: ITikuUserExerciseAnswerService.java
     *
     * @Description: 根据试题id 练习id 查询答案
     * @author 周文斌
     * @date 2015-8-26 下午1:04:09
     * @version 1.0
     * @param ue
     * @return
     */
    TikuUserExerciseAnswer findAnswerByMoreId(TikuUserExerciseAnswer ue);

    List<TikuUserExerciseAnswer> findTikuUserExerciseAnswerByTopIds(List<TikuUserExerciseAnswer> childIds);

    List<TikuUserExerciseAnswer> findbyUserExerciseId(Integer exerciseId);

    Integer findTrueCountByExeId(Integer exerciseId);

    List<TikuUserExerciseAnswer> findByExerId(Integer exerciseId);

    Integer findCategoryCountByUserId(Integer userId);

    Double findExerciseScore(Integer exerciseId);
}