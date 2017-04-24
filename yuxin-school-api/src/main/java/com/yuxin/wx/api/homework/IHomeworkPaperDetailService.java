package com.yuxin.wx.api.homework;

import java.util.List;

import com.yuxin.wx.model.homework.HomeworkPaperDetail;

/**
 * Service Interface:HomeworkPaperDetail
 *
 * @author chopin
 * @date 2016-12-15
 */
public interface IHomeworkPaperDetailService {
    /**
     *
     * @Title: saveHomeworkPaperDetail
     * @Description: 新增HomeworkPaperDetail
     * @return void 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by wangzx
     */
    void insert(HomeworkPaperDetail entity);

    /**
     *
     * @Title: batchSaveHomeworkPaperDetail
     * @Description: 批量新增HomeworkPaperDetail
     * @return void 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by wangzx
     */
    void batchInsert(List<HomeworkPaperDetail> list);

    /**
     *
     * @Title: updateHomeworkPaperDetail
     * @Description: 编辑HomeworkPaperDetail
     * @return void 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by wangzx
     */
    void update(HomeworkPaperDetail entity);

    /**
     *
     * @Title: deleteHomeworkPaperDetailById
     * @Description: 根据id删除HomeworkPaperDetail
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by wangzx
     */
    void deleteHomeworkPaperDetailById(Integer id);

    /**
     *
     * @Title: deleteHomeworkPaperDetailByIds
     * @Description: 根据id批量删除HomeworkPaperDetail
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by wangzx
     */
    void deleteHomeworkPaperDetailByIds(Integer[] ids);

    /**
     *
     * @Title: findHomeworkPaperDetailById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by wangzx
     */
    HomeworkPaperDetail findHomeworkPaperDetailById(Integer id);

    /**
     *
     * @Title: findHomeworkPaperDetailByPage
     * @Description: 分页查询
     * @return
     * @return List<HomeworkPaperDetail> 返回类型
     * @throws @exception
     * @date 2016-12-15
     * @user by wangzx
     */
    List<HomeworkPaperDetail> findHomeworkPaperDetailByPage(HomeworkPaperDetail search);

    Double findReadScoreBySid(Integer sid);
}