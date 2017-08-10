package com.yuxin.wx.tiku.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuPaperTopicService;
import com.yuxin.wx.model.tiku.TikuPaperTopic;
import com.yuxin.wx.model.tiku.TikuTopic;
import com.yuxin.wx.tiku.mapper.TikuPaperTopicMapper;

/**
 * Service Implementation:TikuPaperTopic
 *
 * @author wang.zx
 * @date 2015-7-8
 */
@Service
@Transactional
public class TikuPaperTopicServiceImpl extends BaseServiceImpl implements ITikuPaperTopicService {

    @Autowired
    private TikuPaperTopicMapper tikuPaperTopicMapper;

    /**
     *
     * @Title: saveTikuPaperTopic
     * @Description: 新增TikuPaperTopic
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void insert(TikuPaperTopic entity) {
        this.tikuPaperTopicMapper.insert(entity);
    };

    /**
     *
     * @Title: batchSaveTikuPaperTopic
     * @Description: 批量新增TikuPaperTopic
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void batchInsert(List<TikuPaperTopic> entity) {
        this.tikuPaperTopicMapper.batchInsert(entity);
    };

    /**
     *
     * @Title: updateTikuPaperTopic
     * @Description: 编辑TikuPaperTopic
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void update(TikuPaperTopic entity) {
        this.tikuPaperTopicMapper.update(entity);
    };

    /**
     *
     * @Title: deleteTikuPaperTopicById
     * @Description: 根据id删除TikuPaperTopic
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void deleteTikuPaperTopicById(Integer id) {
        this.tikuPaperTopicMapper.deleteById(id);
    };

    /**
     *
     * @Title: deleteTikuPaperTopicByIds
     * @Description: 根据id批量删除TikuPaperTopic
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void deleteTikuPaperTopicByIds(Integer[] ids) {
        this.tikuPaperTopicMapper.deleteByIds(ids);
    };

    /**
     *
     * @Title: findTikuPaperTopicById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public TikuPaperTopic findTikuPaperTopicById(Integer id) {
        return this.tikuPaperTopicMapper.findById(id);
    };

    /**
     *
     * @Title: findTikuPaperTopicByPage
     * @Description: 分页查询
     * @return
     * @return List<TikuPaperTopic> 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public List<TikuPaperTopic> findTikuPaperTopicByPage(TikuPaperTopic search) {
        return this.tikuPaperTopicMapper.page(search);
    }

    @Override
    public TikuPaperTopic findTopicPaperById(Map<String, Object> param) {
        return this.tikuPaperTopicMapper.findTopicPaperById(param);
    }

    @Override
    public List<TikuTopic> findTopicPaperByParentIdAndPaperId(Integer parentId, Integer paperId) {
        return this.tikuPaperTopicMapper.findTopicPaperByParentIdAndPaperId(parentId, paperId);
    }

    @Override
    public void delPaperTopic(Map<String, Object> param) {
        this.tikuPaperTopicMapper.delPaperTopic(param);
    }

    @Override
    public Integer findPaperCountById(Integer paperId) {
        return this.tikuPaperTopicMapper.findPaperCountById(paperId);
    }

    @Override
    public List<TikuPaperTopic> findListByTopicId(Integer topicId) {
        return this.tikuPaperTopicMapper.findListByTopicId(topicId);
    }

    @Override
    public List<TikuPaperTopic> findOtherByPaperTopic(Map<String, Object> param) {
        return this.tikuPaperTopicMapper.findOtherByPaperTopic(param);
    }

    @Override
    public List<TikuPaperTopic> findTopicByPaperId(Integer paperId) {
        return this.tikuPaperTopicMapper.findTopicByPaperId(paperId);
    }

    @Override
    public Float findTopicScore(Map<String, Object> param) {
        return this.tikuPaperTopicMapper.findTopicScore(param);
    }

    @Override
    public List<Integer> findIdByType(Map<String, Object> param) {
        return this.tikuPaperTopicMapper.findIdByType(param);
    }

    @Override
    public void updateByIds(Map<String, Object> param) {
        this.tikuPaperTopicMapper.updateByIds(param);
    }

    @Override
    public Integer findCount(Map<String, Object> param) {
        return this.tikuPaperTopicMapper.findCount(param);
    }

    @Override
    public Double findSumScore(Map<String, Object> param) {
        return this.tikuPaperTopicMapper.findSumScore(param);
    }

    @Override
    public List<TikuPaperTopic> findTikuPaperByType(Map<String, Object> param) {
        return this.tikuPaperTopicMapper.findTikuPaperByType(param);
    }
}
