package com.yuxin.wx.homework.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.homework.IHomeworkPaperDetailService;
import com.yuxin.wx.homework.mapper.HomeworkPaperDetailMapper;
import com.yuxin.wx.model.homework.HomeworkPaperDetail;

/**
 * Service Implementation:HomeworkPaperDetail
 *
 * @author chopin
 * @date 2016-12-15
 */
@Service
@Transactional
public class HomeworkPaperDetailServiceImpl extends BaseServiceImpl implements IHomeworkPaperDetailService {

    @Autowired
    private HomeworkPaperDetailMapper homeworkPaperDetailMapper;

    /**
     *
     * @Title: saveHomeworkPaperDetail
     * @Description: 新增HomeworkPaperDetail
     * @return void 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by chopin
     */
    @Override
    public void insert(HomeworkPaperDetail entity) {
        this.homeworkPaperDetailMapper.insert(entity);
    };

    /**
     *
     * @Title: batchSaveHomeworkPaperDetail
     * @Description: 批量新增HomeworkPaperDetail
     * @return void 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by chopin
     */
    @Override
    public void batchInsert(List<HomeworkPaperDetail> entity) {
        this.homeworkPaperDetailMapper.batchInsert(entity);
    };

    /**
     *
     * @Title: updateHomeworkPaperDetail
     * @Description: 编辑HomeworkPaperDetail
     * @return void 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by chopin
     */
    @Override
    public void update(HomeworkPaperDetail entity) {
        this.homeworkPaperDetailMapper.update(entity);
    };

    /**
     *
     * @Title: deleteHomeworkPaperDetailById
     * @Description: 根据id删除HomeworkPaperDetail
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by chopin
     */
    @Override
    public void deleteHomeworkPaperDetailById(Integer id) {
        this.homeworkPaperDetailMapper.deleteById(id);
    };

    /**
     *
     * @Title: deleteHomeworkPaperDetailByIds
     * @Description: 根据id批量删除HomeworkPaperDetail
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by chopin
     */
    @Override
    public void deleteHomeworkPaperDetailByIds(Integer[] ids) {
        this.homeworkPaperDetailMapper.deleteByIds(ids);
    };

    /**
     *
     * @Title: findHomeworkPaperDetailById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by chopin
     */
    @Override
    public HomeworkPaperDetail findHomeworkPaperDetailById(Integer id) {
        return this.homeworkPaperDetailMapper.findById(id);
    };

    /**
     *
     * @Title: findHomeworkPaperDetailByPage
     * @Description: 分页查询
     * @return
     * @return List<HomeworkPaperDetail> 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by chopin
     */
    @Override
    public List<HomeworkPaperDetail> findHomeworkPaperDetailByPage(HomeworkPaperDetail search) {
        return this.homeworkPaperDetailMapper.page(search);
    };

    @Override
    public Double findReadScoreBySid(Integer sid) {
        return this.homeworkPaperDetailMapper.findReadScoreBySid(sid);
    }
}
