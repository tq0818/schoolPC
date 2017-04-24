package com.yuxin.wx.tiku.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuUserExerciseService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.TikuUserExercise;
import com.yuxin.wx.model.tiku.TikuUserWrong;
import com.yuxin.wx.tiku.mapper.TikuUserExerciseMapper;
import com.yuxin.wx.tiku.mapper.TikuUserWrongMapper;
import com.yuxin.wx.vo.tiku.TikuUserExerVo;
import com.yuxin.wx.vo.tiku.TikuUserExerciseVo;

/**
 * Service Implementation:TikuUserExercise
 *
 * @author wang.zx
 * @date 2015-8-24
 */
@Service
@Transactional
public class TikuUserExerciseServiceImpl extends BaseServiceImpl implements ITikuUserExerciseService {

    @Autowired
    private TikuUserExerciseMapper tikuUserExerciseMapper;

    @Autowired
    private TikuUserWrongMapper tikuUserWrongMapper;

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
    public void insert(TikuUserExercise entity) {
        this.tikuUserExerciseMapper.insert(entity);
    };

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
    public void batchInsert(List<TikuUserExercise> entity) {
        this.tikuUserExerciseMapper.batchInsert(entity);
    };

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
    public void update(TikuUserExercise entity) {
        this.tikuUserExerciseMapper.update(entity);
    };

    /**
     *
     * @Title: deleteTikuUserExerciseById
     * @Description: 根据id删除TikuUserExercise
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public void deleteTikuUserExerciseById(Integer id) {
        this.tikuUserExerciseMapper.deleteById(id);
    };

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
    public void deleteTikuUserExerciseByIds(Integer[] ids) {
        this.tikuUserExerciseMapper.deleteByIds(ids);
    };

    /**
     *
     * @Title: findTikuUserExerciseById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public TikuUserExercise findTikuUserExerciseById(Integer id) {
        return this.tikuUserExerciseMapper.findById(id);
    };

    /**
     *
     * @Title: findTikuUserExerciseByPage
     * @Description: 分页查询
     * @return
     * @return List<TikuUserExercise> 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public List<TikuUserExercise> findTikuUserExerciseByPage(TikuUserExercise search) {
        return this.tikuUserExerciseMapper.page(search);
    }

    @Override
    public TikuUserExerciseVo findTIkuUserExerciseByUserAndStatus(TikuUserExercise exercise) {
        return this.tikuUserExerciseMapper.findTIkuUserExerciseByUserAndStatus(exercise);
    }

    @Override
    public List<TikuUserExercise> findTIkuUserExerciseByUserId(Integer userId) {
        return this.tikuUserExerciseMapper.findTIkuUserExerciseByUserId(userId);
    }

    @Override
    public void topicSuccess(TikuUserExercise exercise, List<TikuUserWrong> wrongs) {
        this.tikuUserExerciseMapper.update(exercise);
        if (wrongs != null && wrongs.size() > 0) {
            this.tikuUserWrongMapper.batchInsert(wrongs);
        }
    }

    @Override
    public List<TikuUserExerVo> findRecords(TikuUserExercise exer) {
        return this.tikuUserExerciseMapper.findRecords(exer);
    }

    @Override
    public Integer findRecordsCount(TikuUserExercise exer) {
        return this.tikuUserExerciseMapper.findRecordsCount(exer);
    }

    @Override
    public Integer findTopicCountByChapterId(Integer chapterId) {
        return this.tikuUserExerciseMapper.findTopicCountByChapterId(chapterId);
    }

    @Override
    public TikuUserExercise findSpeedIng(TikuUserExercise tue) {
        return this.tikuUserExerciseMapper.findSpeedIng(tue);
    }

    @Override
    public TikuUserExercise findAfterClassIng(TikuUserExercise tue) {
        return this.tikuUserExerciseMapper.findAfterClassIng(tue);
    }

    @Override
    public List<TikuUserExercise> findRecordByUserId(TikuUserExercise tue) {
        return this.tikuUserExerciseMapper.findRecordByUserId(tue);
    }

    @Override
    public Map<String, Object> statisticRspdByPaper(TikuPaper paper) {
        return this.tikuUserExerciseMapper.statisticRspdByPaper(paper);
    }

    @Override
    public PageFinder<Map<String, Object>> findPaperRspdInfo(TikuUserExercise exercise) {
        List<Map<String, Object>> list = this.tikuUserExerciseMapper.findPaperRspdInfo(exercise);
        Integer count = this.tikuUserExerciseMapper.findPaperRspdInfoCount(exercise);
        PageFinder<Map<String, Object>> pageFinder = new PageFinder<Map<String, Object>>(exercise.getPage(), exercise.getPageSize(), count, list);
        return pageFinder;
    }

    @Override
    public PageFinder<TikuUserExerciseVo> findAllPaperRspdInfo(TikuUserExercise exercise) {
        List<TikuUserExerciseVo> list = this.tikuUserExerciseMapper.findAllPaperRspdInfo(exercise);
        Integer count = this.tikuUserExerciseMapper.findPaperRspdInfoCount(exercise);
        PageFinder<TikuUserExerciseVo> pageFinder = new PageFinder<TikuUserExerciseVo>(exercise.getPage(), exercise.getPageSize(), count, list);
        return pageFinder;
    }

}
