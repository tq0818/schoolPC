package com.yuxin.wx.api.user;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.user.UserInviteRewardsRecord;
import com.yuxin.wx.vo.user.UserInviteRewardsRecordVo;
import com.yuxin.wx.vo.user.UsersFrontVo;

/**
 * Service Interface:UserInviteRewardsRecord
 * 
 * @author chopin
 * @date 2016-7-29
 */
public interface IUserInviteRewardsRecordService {
    /**
     * 
     * @Title: saveUserInviteRewardsRecord
     * @Description: 新增UserInviteRewardsRecord
     * @return void 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by wangzx
     */
    void insert(UserInviteRewardsRecord entity);

    /**
     * 
     * @Title: batchSaveUserInviteRewardsRecord
     * @Description: 批量新增UserInviteRewardsRecord
     * @return void 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by wangzx
     */
    void batchInsert(List<UserInviteRewardsRecord> list);

    /**
     * 
     * @Title: updateUserInviteRewardsRecord
     * @Description: 编辑UserInviteRewardsRecord
     * @return void 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by wangzx
     */
    void update(UserInviteRewardsRecord entity);

    /**
     * 
     * @Title: deleteUserInviteRewardsRecordById
     * @Description: 根据id删除UserInviteRewardsRecord
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by wangzx
     */
    void deleteUserInviteRewardsRecordById(Integer id);

    /**
     * 
     * @Title: deleteUserInviteRewardsRecordByIds
     * @Description: 根据id批量删除UserInviteRewardsRecord
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by wangzx
     */
    void deleteUserInviteRewardsRecordByIds(Integer[] ids);

    /**
     * 
     * @Title: findUserInviteRewardsRecordById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by wangzx
     */
    UserInviteRewardsRecord findUserInviteRewardsRecordById(Integer id);

    /**
     * 
     * @Title: findUserInviteRewardsRecordByPage
     * @Description: 分页查询
     * @return
     * @return List<UserInviteRewardsRecord> 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by wangzx
     */
    List<UserInviteRewardsRecord> findUserInviteRewardsRecordByPage(UserInviteRewardsRecord search);

    /**
     * 
     * Class Name: IUserInviteRewardsRecordService.java
     * 
     * @Description: 分页查询用户奖励记录
     * @author zhang.zx
     * @date 2016年8月1日 下午2:14:46
     * @modifier
     * @modify-date 2016年8月1日 下午2:14:46
     * @version 1.0
     * @param search
     * @return
     */
    PageFinder<UserInviteRewardsRecord> findUserRewardRecordByWhere(UserInviteRewardsRecord search);

    /**
     * 
     * Class Name: IUserInviteRewardsRecordService.java
     * 
     * @Description: 查询个人奖励统计
     * @author zhang.zx
     * @date 2016年8月1日 下午4:32:53
     * @modifier
     * @modify-date 2016年8月1日 下午4:32:53
     * @version 1.0
     * @param search
     * @return
     */
    UserInviteRewardsRecordVo queryUserWardTotal(UserInviteRewardsRecord search);

    /**
     * 根据被邀请人的id查询出上一级邀请人的信息
     * 
     * @author licong
     * @date 2016年8月5日 上午11:00:36
     * @param
     * @param search
     * @return
     */
    UsersFrontVo findRewardRecordBySearch(UserInviteRewardsRecord search);

    // 得到该机构发放的代金券总金额
    public double getTotalRewardsMoney(Integer companyId);

    // 得到该机构发放的总积分
    public int getTotalRewardsIntegral(Integer companyId);

    // 得到该机构下被邀请注册的总人数
    public int getTotalRegNumber(Integer companyId);

    // 得到该机构下被邀请消费的总人数
    public int getTotalConNumber(Integer companyId);

    // 分页查询总条数
    public int getTotalNumberForPage(UserInviteRewardsRecord search);

    // 查询所有
    public List<UserInviteRewardsRecord> queryForAll(UserInviteRewardsRecord search);

    public List<UserInviteRewardsRecord> findDetailRewardsRecordByParentId(UserInviteRewardsRecord search);

    public int findDetailRewardsRecordCountByParentId(UserInviteRewardsRecord search);

    List<UserInviteRewardsRecord> findByPage(UserInviteRewardsRecord search);

    List<UserInviteRewardsRecord> exprotWithoutPage(UserInviteRewardsRecord search);

    Map<String, Object> findInvitedTypeAndId(Map<String, Object> condition);
}