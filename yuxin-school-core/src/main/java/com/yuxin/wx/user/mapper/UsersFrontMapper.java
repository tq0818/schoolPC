package com.yuxin.wx.user.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.vo.student.SelectStudentOrUsersfrontVo;
import com.yuxin.wx.vo.user.UsersFrontIntegralVo;
import com.yuxin.wx.vo.user.UsersFrontVo;
import com.yuxin.wx.vo.user.UsersStudentInfo;

/**
 * Service Interface:Users
 * 
 * @author chopin
 * @date 2015-3-12
 */
public interface UsersFrontMapper extends BaseMapper<UsersFront> {

    UsersFront findUsersFrontByEmail(UsersFront usersFront);

    UsersFront findUsersFrontByMobile(UsersFront usersFront);

    UsersFront findUsersFrontByuserName(String userName);

    Integer insert2(UsersFront front);

    List<UsersFrontVo> findUserList(UsersFrontVo search);

    List<UsersFrontVo> findUserVipList(UsersFrontVo search);

    List<UsersFrontVo> findUserVipsList(UsersFrontVo search);

    List<Map> countUserByDate(UsersFrontVo search);

    int countList(UsersFrontVo search);

    List<UsersFrontVo> countOffLine(UsersFrontVo search);

    List<UsersFrontIntegralVo> queryStudentIntegral(UsersFrontIntegralVo search);

    Integer queryStudentIntegralCount(UsersFrontIntegralVo search);

    Integer findUserVipListCount(UsersFrontVo search);

    List<UsersFrontVo> findUserVipListForExportExcel(UsersFrontVo search);

    List<UsersFront> findUsersFrontByMemberId(Integer id);

    /**
     * 
     * Class Name: IUsersFrontService.java
     * 
     * @Description: 查询有会员的用户
     * @author 周文斌
     * @date 2016-6-13 下午5:35:26
     * @version 1.0
     * @param param
     * @return
     */
    List<UsersStudentInfo> findUserMemberByCompanyId(Map<String, Object> param);

    void updateNull(UsersFront usersFront);

    List<UsersFront> findConponsUsersByCondition(UsersFront u);
    List<UsersFront> findConponsUsersByConditionIn(UsersFront u);

    UsersFront findUsersFrontByUsername(UsersFront uf);

    Integer findUsersFrontByMobileAndUsername(UsersFront uf);

    UsersFrontVo findVoById(Integer id);

    UsersFrontVo findUserVoByInvitCode(Map<String, Object> map);

    UsersFront findUserFrontByStudentId(Integer id);
    
    Integer findUsersfrontCountByMobileOrUsername(SelectStudentOrUsersfrontVo search);
    
    UsersFront findUsersfrontByMobileOrUsername(SelectStudentOrUsersfrontVo search);
    
    UsersFront findUsersFrontOnlyByMobile(SelectStudentOrUsersfrontVo search);
    
    UsersFront findUsersFrontOnlyByUsername(SelectStudentOrUsersfrontVo search);

    List<UsersFrontVo> findUserFrontAndStudent(Student student);

    String findNickNameByUserFrontId(String userid);
}