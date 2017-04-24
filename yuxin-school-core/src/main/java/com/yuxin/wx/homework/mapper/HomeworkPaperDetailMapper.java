package com.yuxin.wx.homework.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.homework.HomeworkPaperDetail;

/**
 * Service Interface:Users
 * 
 * @author chopin
 * @date 2015-3-12
 */
public interface HomeworkPaperDetailMapper extends BaseMapper<HomeworkPaperDetail> {

    Double findReadScoreBySid(Integer sid);

}