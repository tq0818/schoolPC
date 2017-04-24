package com.yuxin.wx.api.tiku.exam;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.exam.TikuExamUserRelation;
import com.yuxin.wx.vo.tiku.exam.TikuExamUserRelationVo;

/**
 * Service Interface:TikuExamUserRelation
 *
 * @author wang.zx
 * @date 2016-2-17
 */
public interface ITikuExamUserRelationService {
    /**
     *
     * @Title: saveTikuExamUserRelation
     * @Description: 新增TikuExamUserRelation
     * @return void 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by wangzx
     */
    void insert(TikuExamUserRelation entity);

    /**
     *
     * @Title: batchSaveTikuExamUserRelation
     * @Description: 批量新增TikuExamUserRelation
     * @return void 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by wangzx
     */
    void batchInsert(List<TikuExamUserRelation> list);

    /**
     *
     * @Title: updateTikuExamUserRelation
     * @Description: 编辑TikuExamUserRelation
     * @return void 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by wangzx
     */
    void update(TikuExamUserRelation entity);

    /**
     *
     * @Title: deleteTikuExamUserRelationById
     * @Description: 根据id删除TikuExamUserRelation
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by wangzx
     */
    void deleteTikuExamUserRelationById(Integer id);

    /**
     *
     * @Title: deleteTikuExamUserRelationByIds
     * @Description: 根据id批量删除TikuExamUserRelation
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by wangzx
     */
    void deleteTikuExamUserRelationByIds(Integer[] ids);

    /**
     *
     * @Title: findTikuExamUserRelationById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by wangzx
     */
    TikuExamUserRelation findTikuExamUserRelationById(Integer id);

    /**
     *
     * Class Name: ITikuExamUserRelationService.java
     *
     * @Description: findByExerciseId
     * @author yuchanglong
     * @date 2016年2月23日 下午3:34:16
     * @version 1.0
     * @return
     */
    TikuExamUserRelation findByExerciseId(Integer exerciseId);

    /**
     *
     * @Title: findTikuExamUserRelationByPage
     * @Description: 分页查询
     * @return
     * @return List<TikuExamUserRelation> 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by wangzx
     */
    List<TikuExamUserRelation> findTikuExamUserRelationByPage(TikuExamUserRelation search);

    /**
     *
     * Class Name: ITikuExamUserRelationService.java
     *
     * @Description: 查询该考试多少人参加了
     * @author yuchanglong
     * @date 2016年2月17日 下午5:58:25
     * @version 1.0
     * @param search
     * @return
     */
    Integer findExamUserCount(TikuExamUserRelationVo search);

    /**
     *
     * Class Name: ITikuExamUserRelationService.java
     *
     * @Description: 统计查看详情时分页查询
     * @author yuchanglong
     * @date 2016年2月18日 下午2:42:45
     * @version 1.0
     * @param search
     * @return
     */
    PageFinder<TikuExamUserRelationVo> findByPage(TikuExamUserRelationVo search);

    /**
     * 查询试卷做题详细情况
     *
     * @param paper
     * @return
     */
    Map<String, Object> statisticRspdByPaper(TikuPaper paper);
}