package com.yuxin.wx.api.fee;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.course.CourseRemotePayoff;
import com.yuxin.wx.vo.fee.RemoteFeeVo;

public interface ICourseRemotePayoffService {

    PageFinder<RemoteFeeVo> queryList(RemoteFeeVo search);
    void addPayOff(CourseRemotePayoff pay,Integer userid);
    void modifyPayOff(CourseRemotePayoff pay,Integer userid);
}
