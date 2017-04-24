package com.yuxin.wx.tiku.exam.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.exam.ITikuExamUserRelationService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.exam.TikuExamUserRelation;
import com.yuxin.wx.tiku.exam.mapper.TikuExamUserRelationMapper;
import com.yuxin.wx.vo.tiku.exam.TikuExamUserRelationVo;

/**
 * Service Implementation:TikuExamUserRelation
 *
 * @author wang.zx
 * @date 2016-2-17
 */
@Service
@Transactional
public class TikuExamUserRelationServiceImpl extends BaseServiceImpl implements ITikuExamUserRelationService {

    @Autowired
    private TikuExamUserRelationMapper tikuExamUserRelationMapper;

    /**
     *
     * @Title: saveTikuExamUserRelation
     * @Description: 新增TikuExamUserRelation
     * @return void 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by chopin
     */
    @Override
    public void insert(TikuExamUserRelation entity) {
        this.tikuExamUserRelationMapper.insert(entity);
    };

    /**
     *
     * @Title: batchSaveTikuExamUserRelation
     * @Description: 批量新增TikuExamUserRelation
     * @return void 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by chopin
     */
    @Override
    public void batchInsert(List<TikuExamUserRelation> entity) {
        this.tikuExamUserRelationMapper.batchInsert(entity);
    };

    /**
     *
     * @Title: updateTikuExamUserRelation
     * @Description: 编辑TikuExamUserRelation
     * @return void 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by chopin
     */
    @Override
    public void update(TikuExamUserRelation entity) {
        this.tikuExamUserRelationMapper.update(entity);
    };

    /**
     *
     * @Title: deleteTikuExamUserRelationById
     * @Description: 根据id删除TikuExamUserRelation
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by chopin
     */
    @Override
    public void deleteTikuExamUserRelationById(Integer id) {
        this.tikuExamUserRelationMapper.deleteById(id);
    };

    /**
     *
     * @Title: deleteTikuExamUserRelationByIds
     * @Description: 根据id批量删除TikuExamUserRelation
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by chopin
     */
    @Override
    public void deleteTikuExamUserRelationByIds(Integer[] ids) {
        this.tikuExamUserRelationMapper.deleteByIds(ids);
    };

    /**
     *
     * @Title: findTikuExamUserRelationById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by chopin
     */
    @Override
    public TikuExamUserRelation findTikuExamUserRelationById(Integer id) {
        return this.tikuExamUserRelationMapper.findById(id);
    };

    /**
     *
     * @Title: findTikuExamUserRelationByPage
     * @Description: 分页查询
     * @return
     * @return List<TikuExamUserRelation> 返回类型
     * @throws @exception
     * @date 2016-2-17
     * @user by chopin
     */
    @Override
    public List<TikuExamUserRelation> findTikuExamUserRelationByPage(TikuExamUserRelation search) {
        return this.tikuExamUserRelationMapper.page(search);
    }

    @Override
    public Integer findExamUserCount(TikuExamUserRelationVo search) {
        return this.tikuExamUserRelationMapper.findExamUserCount(search);
    }

    @Override
    public PageFinder<TikuExamUserRelationVo> findByPage(TikuExamUserRelationVo search) {
        List<TikuExamUserRelationVo> list = this.tikuExamUserRelationMapper.byPage(search);
        Integer count = this.tikuExamUserRelationMapper.pageCount(search);
        PageFinder<TikuExamUserRelationVo> pageFinder = new PageFinder<TikuExamUserRelationVo>(search.getPage(), search.getPageSize(), count, list);
        return pageFinder;
    }

    @Override
    public TikuExamUserRelation findByExerciseId(Integer exerciseId) {
        return this.tikuExamUserRelationMapper.findByExerciseId(exerciseId);
    }

    @Override
    public Map<String, Object> statisticRspdByPaper(TikuPaper paper) {
        return this.tikuExamUserRelationMapper.statisticRspdByPaper(paper);
    }
}
