package com.yuxin.wx.tiku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuUserExerciseAnswerService;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswer;
import com.yuxin.wx.tiku.mapper.TikuUserExerciseAnswerMapper;

/**
 * Service Implementation:TikuUserExerciseAnswer
 *
 * @author wang.zx
 * @date 2015-8-24
 */
@Service
@Transactional
public class TikuUserExerciseAnswerServiceImpl extends BaseServiceImpl implements ITikuUserExerciseAnswerService {

    @Autowired
    private TikuUserExerciseAnswerMapper tikuUserExerciseAnswerMapper;

    /**
     *
     * @Title: saveTikuUserExerciseAnswer
     * @Description: 新增TikuUserExerciseAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public void insert(TikuUserExerciseAnswer entity) {
        this.tikuUserExerciseAnswerMapper.insert(entity);
    };

    /**
     *
     * @Title: batchSaveTikuUserExerciseAnswer
     * @Description: 批量新增TikuUserExerciseAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public void batchInsert(List<TikuUserExerciseAnswer> entity) {
        this.tikuUserExerciseAnswerMapper.batchInsert(entity);
    };

    /**
     *
     * @Title: updateTikuUserExerciseAnswer
     * @Description: 编辑TikuUserExerciseAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public void update(TikuUserExerciseAnswer entity) {
        this.tikuUserExerciseAnswerMapper.update(entity);
    };

    /**
     *
     * @Title: deleteTikuUserExerciseAnswerById
     * @Description: 根据id删除TikuUserExerciseAnswer
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public void deleteTikuUserExerciseAnswerById(Integer id) {
        this.tikuUserExerciseAnswerMapper.deleteById(id);
    };

    /**
     *
     * @Title: deleteTikuUserExerciseAnswerByIds
     * @Description: 根据id批量删除TikuUserExerciseAnswer
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public void deleteTikuUserExerciseAnswerByIds(Integer[] ids) {
        this.tikuUserExerciseAnswerMapper.deleteByIds(ids);
    };

    /**
     *
     * @Title: findTikuUserExerciseAnswerById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public TikuUserExerciseAnswer findTikuUserExerciseAnswerById(Integer id) {
        return this.tikuUserExerciseAnswerMapper.findById(id);
    };

    /**
     *
     * @Title: findTikuUserExerciseAnswerByPage
     * @Description: 分页查询
     * @return
     * @return List<TikuUserExerciseAnswer> 返回类型
     * @throws @exception
     * @date 2015-8-24
     * @user by chopin
     */
    @Override
    public List<TikuUserExerciseAnswer> findTikuUserExerciseAnswerByPage(TikuUserExerciseAnswer search) {
        return this.tikuUserExerciseAnswerMapper.page(search);
    }

    @Override
    public Integer findExerciseAnswerCountByUserIdAndSubjectId(Integer userId, Integer subjectId) {
        return this.tikuUserExerciseAnswerMapper.findExerciseAnswerCountByUserIdAndSubjectId(userId, subjectId);
    }

    @Override
    public TikuUserExerciseAnswer findAnswerByMoreId(TikuUserExerciseAnswer ue) {
        // TODO Auto-generated method stub
        return this.tikuUserExerciseAnswerMapper.findAnswerByMoreId(ue);
    }

    @Override
    public void insertUserAns(TikuUserExerciseAnswer entity) {
        // TODO Auto-generated method stub
        TikuUserExerciseAnswer answer = this.tikuUserExerciseAnswerMapper.findAnswerByMoreId(entity);
        if (answer == null) {
            entity.setId(null);
            this.tikuUserExerciseAnswerMapper.insert(entity);
        } else {
            this.tikuUserExerciseAnswerMapper.update(entity);
        }
    }

    @Override
    public List<TikuUserExerciseAnswer> findTikuUserExerciseAnswerByTopIds(List<TikuUserExerciseAnswer> childIds) {
        // TODO Auto-generated method stub
        return this.tikuUserExerciseAnswerMapper.findTikuUserExerciseAnswerByTopIds(childIds);
    }

    @Override
    public List<TikuUserExerciseAnswer> findbyUserExerciseId(Integer exerciseId) {
        // TODO Auto-generated method stub
        return this.tikuUserExerciseAnswerMapper.findbyUserExerciseId(exerciseId);
    }

    @Override
    public Integer findTrueCountByExeId(Integer exerciseId) {
        // TODO Auto-generated method stub
        return this.tikuUserExerciseAnswerMapper.findTrueCountByExeId(exerciseId);
    }

    @Override
    public List<TikuUserExerciseAnswer> findByExerId(Integer exerciseId) {
        // TODO Auto-generated method stub
        return this.tikuUserExerciseAnswerMapper.findByExerId(exerciseId);
    };

    @Override
    public Integer findCategoryCountByUserId(Integer userId) {
        return this.tikuUserExerciseAnswerMapper.findCategoryCountByUserId(userId);
    };

    @Override
    public Double findExerciseScore(Integer exerciseId) {
        return this.tikuUserExerciseAnswerMapper.findExerciseScore(exerciseId);
    }
}
