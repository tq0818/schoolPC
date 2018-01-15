package com.yuxin.wx.api.user;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.vo.student.SelectStudentOrUsersfrontVo;
import com.yuxin.wx.vo.user.UsersFrontIntegralVo;
import com.yuxin.wx.vo.user.UsersFrontVo;
import com.yuxin.wx.vo.user.UsersStudentInfo;

import java.util.List;
import java.util.Map;

/**
 * Service Interface:UsersFront
 *
 * @author chopin
 * @date 2015-5-9
 */
public interface IUsersFrontService {
    /**
     *
     * @Title: saveUsersFront
     * @Description: 新增UsersFront
     * @return void 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by wangzx
     */
    void insert(UsersFront entity);

    /**
     *
     * @Title: batchSaveUsersFront
     * @Description: 批量新增UsersFront
     * @return void 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by wangzx
     */
    void batchInsert(List<UsersFront> list);

    /**
     *
     * @Title: updateUsersFront
     * @Description: 编辑UsersFront
     * @return void 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by wangzx
     */
    void update(UsersFront entity);

    /**
     *
     * @Title: deleteUsersFrontById
     * @Description: 根据id删除UsersFront
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by wangzx
     */
    void deleteUsersFrontById(Integer id);

    /**
     *
     * @Title: deleteUsersFrontByIds
     * @Description: 根据id批量删除UsersFront
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by wangzx
     */
    void deleteUsersFrontByIds(Integer[] ids);

    /**
     *
     * @Title: findUsersFrontById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by wangzx
     */
    UsersFront findUsersFrontById(Integer id);

    /**
     *
     * @Title: findUsersFrontByPage
     * @Description: 分页查询
     * @return
     * @return List<UsersFront> 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by wangzx
     */
    List<UsersFront> findUsersFrontByPage(UsersFront search);

    /**
     *
     * @Title: findUsersFrontByEmail
     * @Description: 根据邮箱查询用户
     * @return
     * @return List<UsersFront> 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by chopin
     */
    public UsersFront findUsersFrontByEmail(UsersFront usersFront);

    /**
     *
     * @Title: findUsersFrontByEmail
     * @Description: 根据手机查询用户
     * @return
     * @return List<UsersFront> 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by chopin
     */
    public UsersFront findUsersFrontByMobile(UsersFront usersFront);

    /**
     *
     * Class Name: IUsersFrontService.java
     *
     * @Description: 返回主键
     * @author 权飞虎
     * @date 2015年5月18日 下午6:28:56
     * @modifier
     * @modify-date 2015年5月18日 下午6:28:56
     * @version 1.0
     * @param front
     * @return
     */
    Integer insert2(UsersFront front);

    /**
     *
     * @Title: findUsersFrontByEmail
     * @Description: 自定义条件查询用户列表
     * @return
     * @return List<UsersFront> 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by chopin
     */
    public PageFinder<UsersFrontVo> findUserList(UsersFrontVo search);

    public PageFinder<UsersFrontVo> findUserVipList(UsersFrontVo search);

    public List<UsersFrontVo> findUserVipsList(UsersFrontVo search);

    /**
     *
     * @Title: findUsersFrontByEmail
     * @Description: 根据时间统计用户
     * @return
     * @return List<UsersFront> 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by chopin
     */
    public List<Map> countUserByDate(UsersFrontVo search);

    /**
     *
     * @Title: findUsersFrontByEmail
     * @Description: 自定义条件查询用户列表
     * @return
     * @return List<UsersFront> 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by chopin
     */
    public Map countOffLine(UsersFrontVo search);

    /**
     *
     * Class Name: IUsersFrontService.java
     *
     * @Description: 查询积分统计
     * @author zhang.zx
     * @date 2016年5月19日 上午10:59:13
     * @modifier
     * @modify-date 2016年5月19日 上午10:59:13
     * @version 1.0
     * @param search
     * @return
     */
    PageFinder<UsersFrontIntegralVo> queryStudentIntegralTotal(UsersFrontIntegralVo search);

    /**
     *
     * @Description: 查询会员数量
     * @author: dongshuai
     * @date: 2016年5月23日
     * @param search
     * @return
     *
     */
    Integer findUserVipListCount(UsersFrontVo usersFrontVo);

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

    /**
     *
     * Class Name: IUsersFrontService.java
     *
     * @Description: 条件查询用户信息
     * @author zhang.zx
     * @date 2016年6月21日 下午4:11:03
     * @modifier
     * @modify-date 2016年6月21日 下午4:11:03
     * @version 1.0
     * @param u
     * @return
     */
    List<UsersFront> findConponsUsersByCondition(UsersFront u);
    List<UsersFront> findConponsUsersByConditionIn(UsersFront u);

    UsersFront findUsersFrontByUsername(UsersFront uf);

    Integer findUsersFrontByMobileAndUsername(UsersFront uf);

    UsersFrontVo findUsersFrontVoById(Integer id);

    UsersFrontVo findUserIdByInvitCode(String code, Integer companyId);

    List<UsersFrontVo> findUserFrontAndStudent(Student student);

    UsersFront findUserFrontByStudentId(Integer id);
    
    Integer findUsersfrontCountByMobileOrUsername(SelectStudentOrUsersfrontVo search);

    List<UsersFront> queryAll();
    
}