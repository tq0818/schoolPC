package com.yuxin.wx.tiku.impl;

import com.yuxin.wx.api.tiku.ITikuUserExerciseAnswerStatisticsService;
import com.yuxin.wx.api.tiku.ITikuUserExerciseService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.TikuUserExercise;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswerStatistics;
import com.yuxin.wx.model.tiku.TikuUserWrong;
import com.yuxin.wx.tiku.mapper.TikuUserExerciseAnswerStatisticsMapper;
import com.yuxin.wx.tiku.mapper.TikuUserExerciseMapper;
import com.yuxin.wx.tiku.mapper.TikuUserWrongMapper;
import com.yuxin.wx.vo.tiku.TikuUserExerVo;
import com.yuxin.wx.vo.tiku.TikuUserExerciseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Service Implementation:TikuUserExerciseAnswerStatisticsService
 *
 * @author wang.zx
 * @date 2015-8-24
 */
@Service
@Transactional
public class TikuUserExerciseAnswerStatisticsServiceImpl extends BaseServiceImpl implements ITikuUserExerciseAnswerStatisticsService {

    @Autowired
    private TikuUserExerciseAnswerStatisticsMapper tikuUserExerciseAnswerStatisticsMapper;


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
    public void insert(TikuUserExerciseAnswerStatistics entity) {
        this.tikuUserExerciseAnswerStatisticsMapper.insert(entity);
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
    public void batchInsert(List<TikuUserExerciseAnswerStatistics> entity) {
        this.tikuUserExerciseAnswerStatisticsMapper.batchInsert(entity);
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
    public void update(TikuUserExerciseAnswerStatistics entity) {
        this.tikuUserExerciseAnswerStatisticsMapper.update(entity);
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
        this.tikuUserExerciseAnswerStatisticsMapper.deleteByIds(ids);
    }

    @Override
    public List<Integer> queryAllData() {
        return tikuUserExerciseAnswerStatisticsMapper.queryAllData();
    }

}
