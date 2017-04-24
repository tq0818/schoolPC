package com.yuxin.wx.queAns.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.queAns.IQuestionAnswerService;
import com.yuxin.wx.model.queAns.QuestionAnswer;
import com.yuxin.wx.queAns.mapper.QuestionAnswerMapper;

/**
 * Service Implementation:QuestionAnswer
 * 
 * @author wang.zx
 * @date 2015-12-9
 */
@Service
@Transactional
public class QuestionAnswerServiceImpl extends BaseServiceImpl implements IQuestionAnswerService {

    @Autowired
    private QuestionAnswerMapper QuestionAnswerMapper;

    /**
     * 
     * @Title: saveQuestionAnswer
     * @Description: 新增QuestionAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public void insert(QuestionAnswer entity) {
        QuestionAnswerMapper.insert(entity);
    };

    /**
     * 
     * @Title: batchSaveQuestionAnswer
     * @Description: 批量新增QuestionAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public void batchInsert(List<QuestionAnswer> T) {
        QuestionAnswerMapper.batchInsert(T);
    };

    /**
     * 
     * @Title: updateQuestionAnswer
     * @Description: 编辑QuestionAnswer
     * @return void 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public void update(QuestionAnswer T) {
        QuestionAnswerMapper.update(T);
    };

    /**
     * 
     * @Title: deleteQuestionAnswerById
     * @Description: 根据id删除QuestionAnswer
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public void deleteQuestionAnswerById(Integer id) {
        QuestionAnswerMapper.deleteById(id);
    };

    /**
     * 
     * @Title: deleteQuestionAnswerByIds
     * @Description: 根据id批量删除QuestionAnswer
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public void deleteQuestionAnswerByIds(Integer[] ids) {
        QuestionAnswerMapper.deleteByIds(ids);
    };

    /**
     * 
     * @Title: findQuestionAnswerById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public QuestionAnswer findQuestionAnswerById(Integer id) {
        return QuestionAnswerMapper.findById(id);
    };

    /**
     * 
     * @Title: findQuestionAnswerByPage
     * @Description: 分页查询
     * @return
     * @return List<QuestionAnswer> 返回类型
     * @throws @exception
     * @date 2015-12-9
     * @user by chopin
     */
    @Override
    public List<QuestionAnswer> findQuestionAnswerByPage(QuestionAnswer search) {
        return QuestionAnswerMapper.page(search);
    }

    @Override
    public List<QuestionAnswer> findAnsByQueId(QuestionAnswer ans) {
        return QuestionAnswerMapper.findAnsByQueId(ans);
    }

    @Override
    public List<Integer> findTwoAns(Integer answerId) {
        return QuestionAnswerMapper.findTwoAns(answerId);
    }

    @Override
    public void updateList(List<Integer> list) {
        QuestionAnswerMapper.updateList(list);
    }

    @Override
    public List<QuestionAnswer> findEntityTwoAns(Integer answerId) {
        return QuestionAnswerMapper.findEntityTwoAns(answerId);
    }

    @Override
    public Integer findAnsCountByQueId(QuestionAnswer ans) {
        // TODO Auto-generated method stub
        return QuestionAnswerMapper.findAnsCountByQueId(ans);
    };
}
