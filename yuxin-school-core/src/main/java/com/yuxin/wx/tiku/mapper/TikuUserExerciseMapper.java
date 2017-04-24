package com.yuxin.wx.tiku.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.TikuUserExercise;
import com.yuxin.wx.vo.tiku.TikuUserExerVo;
import com.yuxin.wx.vo.tiku.TikuUserExerciseVo;

/**
 * Service Interface:Users
 *
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuUserExerciseMapper extends BaseMapper<TikuUserExercise> {

    TikuUserExerciseVo findTIkuUserExerciseByUserAndStatus(TikuUserExercise exercise);

    List<TikuUserExercise> findTIkuUserExerciseByUserId(Integer userId);

    TikuUserExercise findTikuUserExerciseByExerId(Integer exerId, Integer userId);

    /**
     * 分页查询已做试题 Class Name: ITikuUserExerciseService.java
     *
     * @Description:
     * @author 周文斌
     * @date 2015-8-31 下午1:06:19
     * @version 1.0
     * @param exer
     * @return
     */
    List<TikuUserExerVo> findRecords(TikuUserExercise exer);

    /**
     *
     * Class Name: ITikuUserExerciseService.java
     *
     * @Description: 分页总数
     * @author 周文斌
     * @date 2015-8-31 下午1:08:01
     * @version 1.0
     * @param exer
     * @return
     */
    Integer findRecordsCount(TikuUserExercise exer);

    Integer findExerciseCountByParams(@Param("userId") Integer userId, @Param("subjectId") Integer subjectId);

    Integer findTopicCountByChapterId(Integer chapterId);

    /**
     *
     * Class Name: ITikuUserExerciseService.java
     *
     * @Description: 查询是否有未做完的15道
     * @author 周文斌
     * @date 2015-9-16 上午11:03:18
     * @version 1.0
     * @param tue
     * @return
     */
    TikuUserExercise findSpeedIng(TikuUserExercise tue);

    TikuUserExercise findAfterClassIng(TikuUserExercise tue);

    /**
     *
     * Class Name: ITikuUserExerciseService.java
     *
     * @Description: 按条件分页查询做题记录
     * @author 周文斌
     * @date 2016-4-26 下午5:45:25
     * @version 1.0
     * @param tue
     * @return
     */
    List<TikuUserExercise> findRecordByUserId(TikuUserExercise tue);

    /**
     *
     * Class Name: ITikuUserExerciseService.java
     *
     * @Description: 根据试卷统计
     * @author xiongbao
     * @date 2016-4-26 下午5:45:25
     * @version 1.0
     * @param tue
     * @return
     */
    Map<String, Object> statisticRspdByPaper(TikuPaper paper);

    /**
     *
     * Class Name: ITikuUserExerciseService.java
     *
     * @Description: 根据试卷统计做题记录
     * @author xiongbao
     * @date 2016-4-26 下午5:45:25
     * @version 1.0
     * @param tue
     * @return
     */
    List<Map<String, Object>> findPaperRspdInfo(TikuUserExercise exercise);

    /**
     *
     * Class Name: ITikuUserExerciseService.java
     *
     * @Description: 根据试卷统计做题记录计数
     * @author xiongbao
     * @date 2016-4-26 下午5:45:25
     * @version 1.0
     * @param tue
     * @return
     */
    Integer findPaperRspdInfoCount(TikuUserExercise exercise);

    /**
     *
     * Class Name: ITikuUserExerciseService.java
     *
     * @Description: 查询试卷全部做题记录
     * @author xiongbao
     * @date 2016-4-26 下午5:45:25
     * @version 1.0
     * @param tue
     * @return
     */
    List<TikuUserExerciseVo> findAllPaperRspdInfo(TikuUserExercise exercise);
}