package com.yuxin.wx.api.fee;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.course.CourseRemote;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.vo.course.CourseRemoteVo;
import com.yuxin.wx.vo.fee.RemoteFeeVo;
import com.yuxin.wx.vo.fee.StagingVo;

public interface IStageService {
    /**
     * 
    * @Title: IStageService.java
    * @Description: 新增CourseRemote
    * @return void    返回类型 
    * @throws 
    * @exception 
    * @date 2014-12-5
    * @user by wangzx
     */
    void insert(StudentFeeStage studentFeeStage);
    
    /**
     * 
    * @Title: IStageService.java 
    * @Description: 批量新增CourseRemote
    * @return void    返回类型 
    * @throws 
    * @exception 
    * @date 2014-12-5
    * @user by wangzx
     */
    void batchInsert(List<StudentFeeStage> studentFeeStage);
    
    /**
     * 
    * @Title: IStageService.java 
    * @Description: 编辑CourseRemote
    * @return void    返回类型 
    * @throws 
    * @exception 
    * @date 2014-12-5
    * @user by wangzx
     */
    void update(StudentFeeStage studentFeeStage);
    
    
    /**
     * 
     * Class Name: IStageService.java
     * @Description: 根据远程教育id获取远程教育修改信息
     * @author Kevin
     * @date 2015年1月4日
     * @version 1.0
     * @param id
     * @return
     */
    StudentFeeStage findByOneId(Integer id);
    
    /**
     * 
     * Class Name: IStageService.java
     * @Description: 根据查询条件获取列表
     * @author chopin
     * @date 2015年1月4日
     * @version 1.0
     * @param id
     * @return
     */
    public PageFinder<StagingVo> queryList(StagingVo search);
    public PageFinder<StagingVo> queryList2(StagingVo search);
    /**
     * 
     * Class Name: IStageService.java
     * @Description: 查询未缴费的数据
     * @author chopin
     * @date 2015年1月4日
     * @version 1.0
     * @param id
     * @return
     */
    PageFinder<StagingVo> queryListByStatus(StagingVo search);
    
    /**
     * 
     * Class Name: IStageService.java
     * @Description: 分期缴费
     * @author Chopin
     * @date 2015年1月23日
     * @version 1.0
     * @param studentFeeStage
     * @param userId
     * @return
     */
    String fixStage(StudentFeeStage studentFeeStage,Users user);
    
    /**
     * 
     * Class Name: IStageService.java
     * @Description: 修改下次有效分期的金额
     * @author Chopin
     * @date 2015年1月23日
     * @version 1.0
     * @param studentFeeStage
     * @return
     */
    String modifyNextStage(StudentFeeStage studentFeeStage,Integer userId);
    
    /**
     * 
     * Class Name: IStageService.java
     * @Description: 远程结费
     * @author zhang.zx
     * @date 2015年6月23日 下午2:24:56
     * @modifier
     * @modify-date 2015年6月23日 下午2:24:56
     * @version 1.0
     * @param search
     * @return
     */
    PageFinder<RemoteFeeVo> queryRemoteList(RemoteFeeVo search);
    
    /**
     * 
     * Class Name: IStageService.java
     * @Description: 查询单个远程结费信息
     * @author zhang.zx
     * @date 2015年6月23日 下午5:51:49
     * @modifier
     * @modify-date 2015年6月23日 下午5:51:49
     * @version 1.0
     * @param search
     * @return
     */
    RemoteFeeVo queryRemoteById(RemoteFeeVo search);
    
    /**
     * 
     * Class Name: IStageService.java
     * @Description: 添加远程节费
     * @author zhang.zx
     * @date 2015年6月23日 下午9:52:01
     * @modifier
     * @modify-date 2015年6月23日 下午9:52:01
     * @version 1.0
     * @param search
     */
    void insertFee(RemoteFeeVo search);
    
    void insertRemoteFee(RemoteFeeVo search);
    
    void updateRemoteFee(RemoteFeeVo search);
    /**
     * 
     * Class Name: IStageService.java
     * @Description: 查询结费列表
     * @author zhang.zx
     * @date 2015年6月23日 下午9:52:55
     * @modifier
     * @modify-date 2015年6月23日 下午9:52:55
     * @version 1.0
     * @param search
     * @return
     */
    List<RemoteFeeVo> queryFee(RemoteFeeVo search);
}
