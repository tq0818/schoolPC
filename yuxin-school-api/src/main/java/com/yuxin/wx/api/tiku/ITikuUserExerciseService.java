package com.yuxin.wx.api.tiku;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.TikuUserExercise;
import com.yuxin.wx.model.tiku.TikuUserWrong;
import com.yuxin.wx.vo.tiku.TikuUserExerVo;
import com.yuxin.wx.vo.tiku.TikuUserExerciseVo;

/**
 * Service Interface:TikuUserExercise
 *
 * @author wang.zx
 * @date 2015-8-24
 */
public interface ITikuUserExerciseService {
    /**
     *
     * @Title: saveTikuUserExercise
     * @Description: 新增TikuUserExercise
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void insert(TikuUserExercise entity);

    /**
     *
     * @Title: batchSaveTikuUserExercise
     * @Description: 批量新增TikuUserExercise
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void batchInsert(List<TikuUserExercise> list);

    /**
     *
     * @Title: updateTikuUserExercise
     * @Description: 编辑TikuUserExercise
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void update(TikuUserExercise entity);

    /**
     *
     * @Title: deleteTikuUserExerciseById
     * @Description: 根据id删除TikuUserExercise
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void deleteTikuUserExerciseById(Integer id);

    /**
     *
     * @Title: deleteTikuUserExerciseByIds
     * @Description: 根据id批量删除TikuUserExercise
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void deleteTikuUserExerciseByIds(Integer[] ids);

    /**
     *
     * @Title: findTikuUserExerciseById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    TikuUserExercise findTikuUserExerciseById(Integer id);

    /**
     *
     * @Title: findTikuUserExerciseByPage
     * @Description: 分页查询
     * @return
     * @return List<TikuUserExercise> 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    List<TikuUserExercise> findTikuUserExerciseByPage(TikuUserExercise search);

    /**
     * @Description: 根据用户以及状态查找用户最后一次做题的试卷情况
     * @author zx
     * @date 2015-8-26 下午12:55:03
     * @version 1.0
     * @param userId
     * @param status
     * @return
     */
    TikuUserExerciseVo findTIkuUserExerciseByUserAndStatus(TikuUserExercise exercise);

    /**
     * @Description: 根据用户Id查询该用户做过的所有的试卷
     * @author zx
     * @date 2015-8-26 下午5:24:07
     * @version 1.0
     * @param userId
     * @return
     */
    List<TikuUserExercise> findTIkuUserExerciseByUserId(Integer userId);

    /**
     *
     * Class Name: ITikuUserExerciseService.java
     *
     * @Description: 交卷处理的事件
     * @author yuchanglong
     * @date 2015年8月28日 下午6:28:03
     * @version 1.0
     * @param exercise
     * @param wrong
     */
    void topicSuccess(TikuUserExercise exercise, List<TikuUserWrong> wrongs);

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

    /**
     * @Description: 根据当前章判断用户是否做过该章的题，如果做过做了多少
     * @author zx
     * @date 2015-9-2 上午11:34:52
     * @version 1.0
     * @param chapterId
     * @return
     */
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

    /**
     *
     * Class Name: ITikuUserExerciseService.java
     *
     * @Description: 查询是否有未做完的课后练习
     * @author ycl
     * @date 2015-9-16 上午11:03:18
     * @version 1.0
     * @param tue
     * @return
     */
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
     * @Description: 查询试卷做题记录
     * @author xiongbao
     * @date 2016-4-26 下午5:45:25
     * @version 1.0
     * @param tue
     * @return
     */
    PageFinder<Map<String, Object>> findPaperRspdInfo(TikuUserExercise exercise);

    /**
     *
     * Class Name: ITikuUserExerciseService.java
     *
     * @Description: 查询全部试卷做题记录
     * @author xiongbao
     * @date 2016-4-26 下午5:45:25
     * @version 1.0
     * @param tue
     * @return
     */
    PageFinder<TikuUserExerciseVo> findAllPaperRspdInfo(TikuUserExercise exercise);
}