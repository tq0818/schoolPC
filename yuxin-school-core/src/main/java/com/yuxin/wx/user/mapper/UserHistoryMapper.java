package com.yuxin.wx.user.mapper;

import java.util.Date;
import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.user.SysPlayLogsVo;
import com.yuxin.wx.model.user.UserHistory;
import com.yuxin.wx.vo.user.UserHistoryAllVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface UserHistoryMapper extends BaseMapper<UserHistory> {
	
	int queryCountByClassTypeIdAndUserId(UserHistory search);

    void insertHistoryAll(UserHistoryAllVo userHistoryAllVo);

    void insertPlayLogs(UserHistoryAllVo uha);
    
    void updatePlayLogs(UserHistory updateUserHistory);
    
    void insertSysPlayLogs(SysPlayLogsVo sysPlayLogsVo);
    
    List<SysPlayLogsVo> querySysPlayLogsVosByDate(UserHistory search);
}