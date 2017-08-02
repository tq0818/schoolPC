package com.yuxin.wx.api.tiku;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.tiku.TikuUserExerciseAnswer;

/**
 * Service Interface:TikuUserExerciseAnswer
 *
 * @author wang.zx
 * @date 2015-8-24
 */
public interface ITikuUserExerciseAnswerService {
    /**
     *
     * @Title: saveTikuUserExerciseAnswer
     * @Description: 新增TikuUserExerciseAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void insert(TikuUserExerciseAnswer entity);

    /**
     *
     * @Title: batchSaveTikuUserExerciseAnswer
     * @Description: 批量新增TikuUserExerciseAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void batchInsert(List<TikuUserExerciseAnswer> list);

    /**
     *
     * @Title: updateTikuUserExerciseAnswer
     * @Description: 编辑TikuUserExerciseAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void update(TikuUserExerciseAnswer entity);

    /**
     *
     * Class Name: ITikuUserExerciseAnswerService.java
     *
     * @Description: 做题下一题操作
     * @author yuchanglong
     * @date 2015年8月27日 下午4:47:06
     * @version 1.0
     * @param entity
     */
    void insertUserAns(TikuUserExerciseAnswer entity);

    /**
     *
     * @Title: deleteTikuUserExerciseAnswerById
     * @Description: 根据id删除TikuUserExerciseAnswer
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void deleteTikuUserExerciseAnswerById(Integer id);

    /**
     *
     * @Title: deleteTikuUserExerciseAnswerByIds
     * @Description: 根据id批量删除TikuUserExerciseAnswer
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    void deleteTikuUserExerciseAnswerByIds(Integer[] ids);

    /**
     *
     * @Title: findTikuUserExerciseAnswerById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    TikuUserExerciseAnswer findTikuUserExerciseAnswerById(Integer id);

    /**
     *
     * @Title: findTikuUserExerciseAnswerByPage
     * @Description: 分页查询
     * @return
     * @return List<TikuUserExerciseAnswer> 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by wangzx
     */
    List<TikuUserExerciseAnswer> findTikuUserExerciseAnswerByPage(TikuUserExerciseAnswer search);

    /**
     * @Description: 根据用户Id以及科目Id查询用户做过的题
     * @author zx
     * @date 2015-8-24 下午9:06:50
     * @version 1.0
     * @param userId
     * @param subjectId
     * @return
     */
    Integer findExerciseAnswerCountByUserIdAndSubjectId(Integer userId, Integer subjectId);

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

    /**
     *
     * Class Name: ITikuUserExerciseAnswerService.java
     *
     * @Description: 根据子题ids查询用户答案信息
     * @author yuchanglong
     * @date 2015年8月28日 下午12:14:57
     * @version 1.0
     * @param childIds
     * @return
     */
    List<TikuUserExerciseAnswer> findTikuUserExerciseAnswerByTopIds(List<TikuUserExerciseAnswer> childIds);

    /**
     *
     * Class Name: ITikuUserExerciseAnswerService.java
     *
     * @Description: 根据exercise表id查询错题
     * @author yuchanglong
     * @date 2015年8月28日 下午4:23:06
     * @version 1.0
     * @param exerciseId
     * @return
     */
    List<TikuUserExerciseAnswer> findbyUserExerciseId(Integer exerciseId);

    /**
     *
     * Class Name: ITikuUserExerciseAnswerService.java
     *
     * @Description: 根据exercise表id查询正确数量
     * @author yuchanglong
     * @date 2015年8月28日 下午4:23:06
     * @version 1.0
     * @param exerciseId
     * @return
     */
    Integer findTrueCountByExeId(Integer exerciseId);

    /**
     *
     * Class Name: ITikuUserExerciseAnswerService.java
     *
     * @Description: 根据exercise表id查询所有
     * @author yuchanglong
     * @date 2015年9月1日 上午10:21:01
     * @version 1.0
     * @param exerciseId
     * @return
     */
    List<TikuUserExerciseAnswer> findByExerId(Integer exerciseId);
    /**
     * @Description: 根据userID获取该用户一共做过几个题库
     * @author zx
     * @date 2015-9-15 下午7:07:11
     * @version 1.0
     * @param userId
     * @return
     */
    Integer findCategoryCountByUserId(Integer userId);

    Double findExerciseScore(Integer exerciseId);

    List<TikuUserExerciseAnswer> findTikuUserExerciseAnswer(Map<String, Object> papam);
}