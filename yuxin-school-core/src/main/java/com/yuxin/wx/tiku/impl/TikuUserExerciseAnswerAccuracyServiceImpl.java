package com.yuxin.wx.tiku.impl;

import com.yuxin.wx.api.tiku.ITikuUserExerciseAnswerAccuracyService;
import com.yuxin.wx.api.tiku.ITikuUserExerciseAnswerStatisticsService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswerAccuracy;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswerStatistics;
import com.yuxin.wx.tiku.mapper.TikuUserExerciseAnswerAccuracyMapper;
import com.yuxin.wx.tiku.mapper.TikuUserExerciseAnswerStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation:TikuUserExerciseAnswerStatisticsService
 *
 * @author wang.zx
 * @date 2015-8-24
 */
@Service
@Transactional
public class TikuUserExerciseAnswerAccuracyServiceImpl extends BaseServiceImpl implements ITikuUserExerciseAnswerAccuracyService {

    @Autowired
    private TikuUserExerciseAnswerAccuracyMapper tikuUserExerciseAnswerAccuracyMapper;


    /**
     *
     * @Title: saveTikuUserExercise
     * @Description: 新增TikuUserExercise
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public void insert(TikuUserExerciseAnswerAccuracy entity) {
        this.tikuUserExerciseAnswerAccuracyMapper.insert(entity);
    }

    /**
     *
     * @Title: batchSaveTikuUserExercise
     * @Description: 批量新增TikuUserExercise
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public void batchInsert(List<TikuUserExerciseAnswerAccuracy> entity) {
        this.tikuUserExerciseAnswerAccuracyMapper.batchInsert(entity);
    }

    /**
     *
     * @Title: updateTikuUserExercise
     * @Description: 编辑TikuUserExercise
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public void update(TikuUserExerciseAnswerAccuracy entity) {
        this.tikuUserExerciseAnswerAccuracyMapper.update(entity);
    }

    /**
     *
     * @Title: deleteTikuUserExerciseByIds
     * @Description: 根据id批量删除TikuUserExercise
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public void deleteByIds(Integer[] ids) {
        this.tikuUserExerciseAnswerAccuracyMapper.deleteByIds(ids);
    }

    @Override
    public List<Integer> queryAllData() {
        return tikuUserExerciseAnswerAccuracyMapper.queryAllData();
    }

}
