package com.yuxin.wx.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import com.yuxin.wx.vo.student.SelectStudentOrUsersfrontVo;
import com.yuxin.wx.vo.user.UsersFrontIntegralVo;
import com.yuxin.wx.vo.user.UsersFrontVo;
import com.yuxin.wx.vo.user.UsersStudentInfo;

/**
 * Service Implementation:UsersFront
 * 
 * @author chopin
 * @date 2015-5-9
 */
@Service
@Transactional
public class UsersFrontServiceImpl extends BaseServiceImpl implements IUsersFrontService {

    @Autowired
    private UsersFrontMapper usersFrontMapper;

    /**
     * 
     * @Title: saveUsersFront
     * @Description: 新增UsersFront
     * @return void 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by chopin
     */
    @Override
    public void insert(UsersFront entity) {
        this.usersFrontMapper.insert(entity);
    };

    /**
     * 
     * @Title: batchSaveUsersFront
     * @Description: 批量新增UsersFront
     * @return void 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by chopin
     */
    @Override
    public void batchInsert(List<UsersFront> entity) {
        this.usersFrontMapper.batchInsert(entity);
    };

    /**
     * 
     * @Title: updateUsersFront
     * @Description: 编辑UsersFront
     * @return void 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by chopin
     */
    @Override
    public void update(UsersFront entity) {
        this.usersFrontMapper.update(entity);
    };
 

    /**
     * 
     * @Title: deleteUsersFrontById
     * @Description: 根据id删除UsersFront
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by chopin
     */
    @Override
    public void deleteUsersFrontById(Integer id) {
        this.usersFrontMapper.deleteById(id);
    };

    /**
     * 
     * @Title: deleteUsersFrontByIds
     * @Description: 根据id批量删除UsersFront
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by chopin
     */
    @Override
    public void deleteUsersFrontByIds(Integer[] ids) {
        this.usersFrontMapper.deleteByIds(ids);
    };

    /**
     * 
     * @Title: findUsersFrontById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by chopin
     */
    @Override
    public UsersFront findUsersFrontById(Integer id) {
        return this.usersFrontMapper.findById(id);
    };

    /**
     * 
     * @Title: findUsersFrontByPage
     * @Description: 分页查询
     * @return
     * @return List<UsersFront> 返回类型
     * @throws @exception
     * @date 2015-5-9
     * @user by chopin
     */
    @Override
    public List<UsersFront> findUsersFrontByPage(UsersFront search) {
        return this.usersFrontMapper.page(search);
    };

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
    @Override
    public UsersFront findUsersFrontByEmail(UsersFront usersFront) {
        return this.usersFrontMapper.findUsersFrontByEmail(usersFront);
    }

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
    @Override
    public UsersFront findUsersFrontByMobile(UsersFront usersFront) {
        return this.usersFrontMapper.findUsersFrontByMobile(usersFront);
    }

    @Override
    public Integer insert2(UsersFront front) {
        return this.usersFrontMapper.insert2(front);
    };

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
    @Override
    public PageFinder<UsersFrontVo> findUserList(UsersFrontVo search) {
        List<UsersFrontVo> list = this.usersFrontMapper.findUserList(search);
        int rowCount = this.usersFrontMapper.countList(search);
        PageFinder<UsersFrontVo> pf = new PageFinder<UsersFrontVo>(search.getPage(), search.getPageSize(), rowCount, list);
        return pf;
    }

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
    @Override
    public Map countOffLine(UsersFrontVo search) {
        int online_num = 0;
        int offline_num = 0;
        int mobile_num = 0;
        List<UsersFrontVo> list = this.usersFrontMapper.countOffLine(search);
        for (UsersFrontVo v : list) {
            if (v.getRegistType() != null && v.getRegistType() == 1) {
                online_num += 1;
            } else if (v.getRegistType() != null && v.getRegistType() == 2) {
                offline_num += 1;
            } else if (v.getRegistType() != null && v.getRegistType() == 3) {
                mobile_num += 1;
            }
        }
        Map map = new HashMap();
        map.put("mobile_num", mobile_num);
        map.put("online_num", online_num);
        map.put("offline_num", offline_num);
        map.put("total_num", list.size());
        return map;
    }

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
    @Override
    public List<Map> countUserByDate(UsersFrontVo search) {
        return this.usersFrontMapper.countUserByDate(search);
    }

    @Override
    public PageFinder<UsersFrontIntegralVo> queryStudentIntegralTotal(UsersFrontIntegralVo search) {
        List<UsersFrontIntegralVo> data = this.usersFrontMapper.queryStudentIntegral(search);
        Integer count = this.usersFrontMapper.queryStudentIntegralCount(search);
        PageFinder<UsersFrontIntegralVo> pageFinder = new PageFinder<UsersFrontIntegralVo>(search.getPage(), search.getPageSize(), count, data);
        return pageFinder;
    }

    @Override
    public PageFinder<UsersFrontVo> findUserVipList(UsersFrontVo search) {
        List<UsersFrontVo> list = this.usersFrontMapper.findUserVipList(search);
        int rowCount = this.usersFrontMapper.findUserVipListCount(search);
        PageFinder<UsersFrontVo> pf = new PageFinder<UsersFrontVo>(search.getPage(), search.getPageSize(), rowCount, list);
        return pf;
    }

    @Override
    public List<UsersFrontVo> findUserVipsList(UsersFrontVo search) {
        return this.usersFrontMapper.findUserVipsList(search);
    }

    @Override
    public Integer findUserVipListCount(UsersFrontVo search) {
        return this.usersFrontMapper.findUserVipListCount(search);
    }

    @Override
    public List<UsersFrontVo> findUserVipListForExportExcel(UsersFrontVo search) {
        return this.usersFrontMapper.findUserVipListForExportExcel(search);
    }

    @Override
    public List<UsersFront> findUsersFrontByMemberId(Integer id) {
        return this.usersFrontMapper.findUsersFrontByMemberId(id);
    }

    @Override
    public List<UsersStudentInfo> findUserMemberByCompanyId(Map<String, Object> param) {
        return this.usersFrontMapper.findUserMemberByCompanyId(param);
    }

    @Override
    public void updateNull(UsersFront usersFront) {
        this.usersFrontMapper.updateNull(usersFront);
    }

    @Override
    public List<UsersFront> findConponsUsersByCondition(UsersFront u) {
        // TODO Auto-generated method stub
        return this.usersFrontMapper.findConponsUsersByCondition(u);
    }
    @Override
    public List<UsersFront> findConponsUsersByConditionIn(UsersFront u) {
        // TODO Auto-generated method stub
        return this.usersFrontMapper.findConponsUsersByConditionIn(u);
    }
    @Override
    public UsersFront findUsersFrontByUsername(UsersFront uf) {
        return this.usersFrontMapper.findUsersFrontByUsername(uf);
    }

    @Override
    public Integer findUsersFrontByMobileAndUsername(UsersFront uf) {
        return this.usersFrontMapper.findUsersFrontByMobileAndUsername(uf);
    }

    @Override
    public UsersFrontVo findUserIdByInvitCode(String code, Integer companyId) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", code);
        map.put("companyId", companyId);
        return this.usersFrontMapper.findUserVoByInvitCode(map);
    }

    @Override
    public List<UsersFrontVo> findUserFrontAndStudent(Student student) {
        return usersFrontMapper.findUserFrontAndStudent(student);
    }

    @Override
    public UsersFrontVo findUsersFrontVoById(Integer id) {
        // TODO Auto-generated method stub
        return this.usersFrontMapper.findVoById(id);
    }

    @Override
    public UsersFront findUserFrontByStudentId(Integer id) {
        return this.usersFrontMapper.findUserFrontByStudentId(id);
    }

	@Override
	public Integer findUsersfrontCountByMobileOrUsername(SelectStudentOrUsersfrontVo search) {
		return usersFrontMapper.findUsersfrontCountByMobileOrUsername(search);
	}

    @Override
    public List<UsersFront> queryAll() {
        return usersFrontMapper.queryAll();
    }

}
