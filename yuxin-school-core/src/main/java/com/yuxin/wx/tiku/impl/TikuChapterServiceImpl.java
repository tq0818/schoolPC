package com.yuxin.wx.tiku.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuChapterService;
import com.yuxin.wx.model.tiku.TikuChapter;
import com.yuxin.wx.tiku.mapper.TikuChapterMapper;

/**
 * Service Implementation:TikuChapter
 * 
 * @author wang.zx
 * @date 2015-7-8
 */
@Service
@Transactional
public class TikuChapterServiceImpl extends BaseServiceImpl implements ITikuChapterService {

    @Autowired
    private TikuChapterMapper tikuChapterMapper;

    /**
     * 
     * @Title: saveTikuChapter
     * @Description: 新增TikuChapter
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void insert(TikuChapter entity) {
        this.tikuChapterMapper.insert(entity);
    };

    /**
     * 
     * @Title: batchSaveTikuChapter
     * @Description: 批量新增TikuChapter
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void batchInsert(List<TikuChapter> entity) {
        this.tikuChapterMapper.batchInsert(entity);
    };

    /**
     * 
     * @Title: updateTikuChapter
     * @Description: 编辑TikuChapter
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void update(TikuChapter entity) {
        this.tikuChapterMapper.update(entity);
    };

    /**
     * 
     * @Title: deleteTikuChapterById
     * @Description: 根据id删除TikuChapter
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void deleteTikuChapterById(Integer id) {
        this.tikuChapterMapper.deleteById(id);
    };

    /**
     * 
     * @Title: deleteTikuChapterByIds
     * @Description: 根据id批量删除TikuChapter
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void deleteTikuChapterByIds(Integer[] ids) {
        this.tikuChapterMapper.deleteByIds(ids);
    };

    /**
     * 
     * @Title: findTikuChapterById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public TikuChapter findTikuChapterById(Integer id) {
        return this.tikuChapterMapper.findById(id);
    };

    /**
     * 
     * @Title: findTikuChapterByPage
     * @Description: 分页查询
     * @return
     * @return List<TikuChapter> 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public List<TikuChapter> findTikuChapterByPage(TikuChapter search) {
        return this.tikuChapterMapper.page(search);
    }

    @Override
    public List<TikuChapter> findTikuChapter(TikuChapter search) {
        return this.tikuChapterMapper.findTikuChapter(search);
    }

    @Override
    public Integer selectSecByChapter(Integer chapterId) {
        return this.tikuChapterMapper.selectSecByChapter(chapterId);
    };

    @Override
    public List<TikuChapter> findChapterByCond(Map<String, Object> param) {
        return this.tikuChapterMapper.findChapterByCond(param);
    }

    @Override
    public Integer findMaxChapterOrder(Integer subId) {
        return this.tikuChapterMapper.findMaxChapterOrder(subId);
    }

    @Override
    public Integer findInfoBySubId(Integer subId) {
        return this.tikuChapterMapper.findInfoBySubId(subId);
    }

    @Override
    public void deleteBySubId(Integer subId) {
        this.tikuChapterMapper.deleteBySubId(subId);
    };
}
