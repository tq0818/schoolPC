package com.yuxin.wx.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.user.IUserInviteRewardsRecordService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.user.UserInviteRewardsRecord;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.user.mapper.UserInviteRewardsRecordMapper;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import com.yuxin.wx.vo.user.UserInviteRewardsRecordVo;
import com.yuxin.wx.vo.user.UsersFrontVo;

/**
 * Service Implementation:UserInviteRewardsRecord
 * 
 * @author chopin
 * @date 2016-7-29
 */
@Service
@Transactional
public class UserInviteRewardsRecordServiceImpl extends BaseServiceImpl implements IUserInviteRewardsRecordService {

    @Autowired
    private UserInviteRewardsRecordMapper userInviteRewardsRecordMapper;
    @Autowired
    private UsersFrontMapper usersFrontServiceMapper;

    /**
     * 
     * @Title: saveUserInviteRewardsRecord
     * @Description: 新增UserInviteRewardsRecord
     * @return void 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by chopin
     */
    @Override
    public void insert(UserInviteRewardsRecord entity) {
        userInviteRewardsRecordMapper.insert(entity);
    };

    /**
     * 
     * @Title: batchSaveUserInviteRewardsRecord
     * @Description: 批量新增UserInviteRewardsRecord
     * @return void 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by chopin
     */
    @Override
    public void batchInsert(List<UserInviteRewardsRecord> entity) {
        userInviteRewardsRecordMapper.batchInsert(entity);
    };

    /**
     * 
     * @Title: updateUserInviteRewardsRecord
     * @Description: 编辑UserInviteRewardsRecord
     * @return void 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by chopin
     */
    @Override
    public void update(UserInviteRewardsRecord entity) {
        userInviteRewardsRecordMapper.update(entity);
    };

    /**
     * 
     * @Title: deleteUserInviteRewardsRecordById
     * @Description: 根据id删除UserInviteRewardsRecord
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by chopin
     */
    @Override
    public void deleteUserInviteRewardsRecordById(Integer id) {
        userInviteRewardsRecordMapper.deleteById(id);
    };

    /**
     * 
     * @Title: deleteUserInviteRewardsRecordByIds
     * @Description: 根据id批量删除UserInviteRewardsRecord
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by chopin
     */
    @Override
    public void deleteUserInviteRewardsRecordByIds(Integer[] ids) {
        userInviteRewardsRecordMapper.deleteByIds(ids);
    };

    /**
     * 
     * @Title: findUserInviteRewardsRecordById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by chopin
     */
    @Override
    public UserInviteRewardsRecord findUserInviteRewardsRecordById(Integer id) {
        return userInviteRewardsRecordMapper.findById(id);
    };

    /**
     * 
     * @Title: findUserInviteRewardsRecordByPage
     * @Description: 分页查询
     * @return
     * @return List<UserInviteRewardsRecord> 返回类型
     * @throws @exception
     * @date 2016-7-29
     * @user by chopin
     */
    @Override
    public List<UserInviteRewardsRecord> findUserInviteRewardsRecordByPage(UserInviteRewardsRecord search) {
        return userInviteRewardsRecordMapper.page(search);
    }

    @Override
    public PageFinder<UserInviteRewardsRecord> findUserRewardRecordByWhere(UserInviteRewardsRecord search) {
        List<UserInviteRewardsRecord> data = userInviteRewardsRecordMapper.queryUserWardRecord(search);
        if (null != data && data.size() > 0) {
            for (UserInviteRewardsRecord record : data) {
                if (null != record && null != record.getUserId()) {
                    UsersFront user = usersFrontServiceMapper.findById(record.getUserId());
                    if (null != user) {
                        if (null != user.getUsername()) {
                            record.setBe_userName(user.getUsername());
                        }
                        if (null != user.getMobile()) {
                            record.setBe_mobile(user.getMobile());
                        }
                    }
                }
                if (null != record && null != record.getParentId()) {
                    UsersFront user = usersFrontServiceMapper.findById(record.getParentId());
                    if (null != user) {
                        if (null != user.getUsername()) {
                            record.setUserName(user.getUsername());
                        }
                        if (null != user.getMobile()) {
                            record.setMobile(user.getMobile());
                        }
                    }
                }
            }
        }
        Integer count = userInviteRewardsRecordMapper.queryUserWardRecordCount(search);
        PageFinder<UserInviteRewardsRecord> pageFinder = new PageFinder<UserInviteRewardsRecord>(search.getPage(), search.getPageSize(), count, data);
        return pageFinder;
    }

    @Override
    public UserInviteRewardsRecordVo queryUserWardTotal(UserInviteRewardsRecord search) {
        // TODO Auto-generated method stub
        return userInviteRewardsRecordMapper.queryUserWardTotal(search);
    }

    /**
     * 根据被邀请人的id查询出上一级邀请人的信息
     * 
     * @author licong
     * @date 2016年8月5日 上午11:00:36
     * @param
     * @param search
     * @return
     */
    @Override
    public UsersFrontVo findRewardRecordBySearch(UserInviteRewardsRecord search) {
        search = userInviteRewardsRecordMapper.findRewardRecordBySearch(search);
        if (search == null) {
            return null;
        }
        return usersFrontServiceMapper.findVoById(search.getParentId());
    };

    @Override
    public double getTotalRewardsMoney(Integer companyId) {
        // TODO Auto-generated method stub
        return userInviteRewardsRecordMapper.getTotalRewardsMoney(companyId);
    }

    @Override
    public int getTotalRewardsIntegral(Integer companyId) {
        return userInviteRewardsRecordMapper.getTotalRewardsIntegral(companyId);
    }

    @Override
    public int getTotalRegNumber(Integer companyId) {
        return userInviteRewardsRecordMapper.getTotalRegNumber(companyId);
    }

    @Override
    public int getTotalConNumber(Integer companyId) {
        return userInviteRewardsRecordMapper.getTotalConNumber(companyId);
    };

    @Override
    public int getTotalNumberForPage(UserInviteRewardsRecord search) {
        return userInviteRewardsRecordMapper.pageCount(search);
    }

    @Override
    public List<UserInviteRewardsRecord> queryForAll(UserInviteRewardsRecord search) {
        return userInviteRewardsRecordMapper.queryForAll(search);
    }

    @Override
    public List<UserInviteRewardsRecord> findDetailRewardsRecordByParentId(UserInviteRewardsRecord search) {
        return userInviteRewardsRecordMapper.findDetailRewardsRecordByParentId(search);
    }

    @Override
    public int findDetailRewardsRecordCountByParentId(UserInviteRewardsRecord search) {
        return userInviteRewardsRecordMapper.findDetailRewardsRecordCountByParentId(search);
    }

    @Override
    public List<UserInviteRewardsRecord> findByPage(UserInviteRewardsRecord search) {
        // TODO Auto-generated method stub
        return userInviteRewardsRecordMapper.findByPage(search);
    }

    @Override
    public List<UserInviteRewardsRecord> exprotWithoutPage(UserInviteRewardsRecord search) {
        // TODO Auto-generated method stub
        return userInviteRewardsRecordMapper.exprotWithoutPage(search);
    }

    @Override
    public Map<String, Object> findInvitedTypeAndId(Map<String, Object> condition) {
        return userInviteRewardsRecordMapper.findInvitedTypeAndId(condition);
    }

}
