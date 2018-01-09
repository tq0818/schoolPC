package com.yuxin.wx.system.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.api.system.ISysConfigTeacherLessonService;
import com.yuxin.wx.api.system.ISysConfigTeacherService;
import com.yuxin.wx.auth.mapper.AuthRoleMapper;
import com.yuxin.wx.auth.mapper.AuthUserRoleMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.auth.AuthRole;
import com.yuxin.wx.model.auth.AuthUserRole;
import com.yuxin.wx.model.system.SysConfigTeacher;
import com.yuxin.wx.model.system.SysConfigTeacherLesson;
import com.yuxin.wx.model.teacher.UsersComanyRelation;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.system.mapper.SysConfigTeacherLessonMapper;
import com.yuxin.wx.system.mapper.SysConfigTeacherMapper;
import com.yuxin.wx.user.mapper.UsersMapper;
import com.yuxin.wx.vo.system.SysConfigTeachersVo;
import com.yuxin.wx.vo.system.TeachersVo;

/**
 * Service Implementation:SysConfigTeacher
 * 
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class SysConfigTeacherServiceImpl extends BaseServiceImpl implements ISysConfigTeacherService {

    @Autowired
    private SysConfigTeacherMapper sysConfigTeacherMapper;

    @Autowired
    private SysConfigTeacherLessonMapper sysConfigTeacherLessonMapper;

    @Autowired
    private UsersMapper usersMapper;
    
    @Autowired
    private ISysConfigTeacherLessonService sysConfigTeacherLessonServiceImpl;

    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;
    
    @Autowired
    private AuthRoleMapper authRoleMapper ;

    /**
     * 
     * @Title: saveSysConfigTeacher
     * @Description: 新增SysConfigTeacher
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public void insert(SysConfigTeacher sysConfigTeacher) {
        sysConfigTeacherMapper.insert(sysConfigTeacher);
    }

    /**
     * 
     * @Title: batchSaveSysConfigTeacher
     * @Description: 批量新增SysConfigTeacher
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public void batchInsert(List<SysConfigTeacher> sysConfigTeachers) {
        if (sysConfigTeachers != null && !sysConfigTeachers.isEmpty()) {
            sysConfigTeacherMapper.batchInsert(sysConfigTeachers);
        }
    }

    /**
     * 
     * @Title: updateSysConfigTeacher
     * @Description: 编辑SysConfigTeacher
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public void update(SysConfigTeacher sysConfigTeacher) {
        sysConfigTeacherMapper.update(sysConfigTeacher);
    }

    /**
     * 
     * @Title: deleteSysConfigTeacherById
     * @Description: 根据id删除SysConfigTeacher
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public void deleteSysConfigTeacherById(Integer id) {
        sysConfigTeacherMapper.deleteById(id);
    }

    /**
     * 
     * @Title: deleteSysConfigTeacherByIds
     * @Description: 根据id批量删除SysConfigTeacher
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public void deleteSysConfigTeacherByIds(Integer[] ids) {
        sysConfigTeacherMapper.deleteByIds(ids);
    }

    /**
     * 
     * @Title: findSysConfigTeacherById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public SysConfigTeacher findSysConfigTeacherById(Integer id) {
        return sysConfigTeacherMapper.findById(id);
    }

    /**
     * 
     * @Title: findSysConfigTeacherByPage
     * @Description: 分页查询
     * @return
     * @return List<SysConfigTeacher> 返回类型
     * @throws @exception
     * @date 2014-12-5
     * @user by wangzx
     */
    @Override
    public List<SysConfigTeacher> findSysConfigTeacherByPage(SysConfigTeacher search) {
        Integer totalRecords = sysConfigTeacherMapper.pageCount(search);
        search.setTotalRecords(totalRecords);
        return sysConfigTeacherMapper.page(search);
    }

    @Override
    public List<SysConfigTeacher> findSysConfigTeacherByParam(SysConfigTeacher search) {
        List<SysConfigTeacher> teachers = sysConfigTeacherMapper.findSysConfigTeacherByParam(search);
        return teachers;
    }

    @Override
    public List<SysConfigTeacher> findTeacherBySchoolAndModuleId(Integer schoolId, Integer moduleId, String teacherType) {
        List<SysConfigTeacher> teachers = sysConfigTeacherMapper.findTeacherBySchoolAndModuleId(schoolId, moduleId, teacherType);
        return teachers;
    }

    @Override
    public List<SysConfigTeacher> findNotTeacherBySchoolAndModuleId(Integer schoolId, Integer moduleId) {
        List<SysConfigTeacher> teachers = sysConfigTeacherMapper.findNotTeacherBySchoolAndModuleId(schoolId, moduleId);
        return teachers;
    }

    @Override
    public List<SysConfigTeacher> findTeacherBySchoolAndModuleIdAndLesson(Integer schoolId, Integer moduleId, String teacherType) {
        List<SysConfigTeacher> teachers = sysConfigTeacherMapper.findTeacherBySchoolAndModuleIdAndLesson(schoolId, moduleId, teacherType);
        return teachers;
    }

    @Override
    public PageFinder<SysConfigTeacher> queryTeachersByKeys(SysConfigTeacher search) {
        List<SysConfigTeacher> teachers = sysConfigTeacherMapper.queryTeachersByKeys(search);
        int rowCount = sysConfigTeacherMapper.queryTeachersByKeysCount(search);
        PageFinder<SysConfigTeacher> pageFinder = new PageFinder<SysConfigTeacher>(search.getPage(), search.getPageSize(), rowCount, teachers);
        return pageFinder;
    }

    @Override
    public List<SysConfigTeacher> queryForCheck(SysConfigTeacher search) {
        return sysConfigTeacherMapper.queryForCheck(search);
    }

    @Override
    public List<SysConfigTeacher> queryForClassType(Map<String, String> teacherMap) {
        return sysConfigTeacherMapper.queryForClassType(teacherMap);
    }

    @Override
    public Integer findTeacherBySchoolId(Integer schoolId) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findTeacherBySchoolId(schoolId);
    }

    @Override
    public Integer findSenateBySchoolId(Integer schoolId) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findSenateBySchoolId(schoolId);
    }

    // ------------------ 如果以上的方法没用的话，会直接删除 2015-05-04. 如果有新增加的方法，则写到该注释的下方
    // -------------------//

    @Override
    public List<SysConfigTeacher> queryAllTeachersWithItemOneId(SysConfigTeacher teacher) {
        return sysConfigTeacherMapper.queryAllTeachersWithItemOneId(teacher);
    }

    @Override
    public List<SysConfigTeacher> findByCompany(SysConfigTeacher configTeacher) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findByCompany(configTeacher);
    }

    @Override
    public SysConfigTeacher findTeacherIdByMobile(SysConfigTeacher search) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findTeacherIdByMobile(search);
    }

    @Override
    public List<SysConfigTeacher> findTeachers(Map<String, String> teacherMap) {
        List<SysConfigTeacher> data = sysConfigTeacherMapper.queryTeachers(teacherMap);
        return data;
    }

    @Override
    public List<SysConfigTeacher> findAssistantBySchool(Integer schoolId) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findAssistantBySchool(schoolId);
    }

    @Override
    public List<SysConfigTeachersVo> findSysConfigTeachersByName(SysConfigTeachersVo sysConfigTeacher) {
        return sysConfigTeacherMapper.queryTeacherByName(sysConfigTeacher);
    }

    @Override
    public SysConfigTeacher findByUserId(Integer userId) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findByUserId(userId);
    }

    @Override
    public void insertTeacherAndTecherLesson(SysConfigTeacher sysConfigTeacher, SysConfigTeacherLesson sysConfigTeacherLesson) {
        // TODO Auto-generated method stub
        Users users = new Users();
        users.setUsername(sysConfigTeacher.getUserName());
        users.setPassword(sysConfigTeacher.getPwd());
        users.setCompanyId(sysConfigTeacher.getCompanyId());
        users.setSchoolId(sysConfigTeacher.getSchoolId());
        users.setStatus(1);
        users.setRealName(sysConfigTeacher.getName());
        users.setMobile(sysConfigTeacher.getMobile());
        usersMapper.insert(users);
        sysConfigTeacher.setUserId(users.getId());
        sysConfigTeacherMapper.insert(sysConfigTeacher);
        
        List<AuthRole> roles=authRoleMapper.findByCompanyId(users.getCompanyId());
        String teaRoleId=new String();  
        String aduRoleId=new String();
        if(null!=roles&&roles.size()>0){
        	for(AuthRole ar:roles){
        		if("直播老师".equals(ar.getRoleName())&&null!=ar.getId()){
        			teaRoleId=String.valueOf(ar.getId());
        		}else if("助教".equals(ar.getRoleName())&&null!=ar.getId()){
        			aduRoleId=String.valueOf(ar.getId());
        		}
        	}
        }
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(users.getId());
        String ttype = sysConfigTeacher.getTeaOrAdu();
        if ("tea".equals(ttype)) {
            authUserRole.setRoleUid(teaRoleId);
        }
        if ("adu".equals(ttype)) {
            authUserRole.setRoleUid(aduRoleId);
        }
        authUserRole.setCreateTime(sysConfigTeacher.getCreateTime());
        authUserRole.setCreator(sysConfigTeacher.getCreator().toString());
        authUserRoleMapper.insert(authUserRole);
        //添加教师学校关系表
        UsersComanyRelation ucr=new UsersComanyRelation();
        ucr.setUserId(users.getId());
        ucr.setCompanyId(users.getCompanyId());
        ucr.setIsUsed(1);
        usersMapper.insertUsersComanyRelation(ucr);
        sysConfigTeacherLesson.setTeacherId(sysConfigTeacher.getId());
        sysConfigTeacherLessonMapper.insert(sysConfigTeacherLesson);
    }

    @Override
    public void isnertTeaAndUse(SysConfigTeacher sysConfigTeacher) {
        Users users = new Users();
        users.setUsername(sysConfigTeacher.getUserName());
        users.setPassword(sysConfigTeacher.getPwd());
        users.setCompanyId(sysConfigTeacher.getCompanyId());
        users.setSchoolId(sysConfigTeacher.getSchoolId());
        users.setStatus(1);
        users.setRealName(sysConfigTeacher.getName());
        users.setMobile(sysConfigTeacher.getMobile());
        usersMapper.insert(users);
        //添加教师学校关系表
        UsersComanyRelation ucr=new UsersComanyRelation();
        ucr.setUserId(users.getId());
        ucr.setCompanyId(users.getCompanyId());
        ucr.setIsUsed(1);
        usersMapper.insertUsersComanyRelation(ucr);
        List<AuthRole> roles=authRoleMapper.findByCompanyId(users.getCompanyId());
        String teaRoleId=new String();  
        String aduRoleId=new String();
        if(null!=roles&&roles.size()>0){
        	for(AuthRole ar:roles){
        		if("直播老师".equals(ar.getRoleName())&&null!=ar.getId()){
        			teaRoleId=String.valueOf(ar.getId());
        		}else if("助教".equals(ar.getRoleName())&&null!=ar.getId()){
        			aduRoleId=String.valueOf(ar.getId());
        		}
        	}
        }
        sysConfigTeacher.setUserId(users.getId());
        sysConfigTeacherMapper.insert(sysConfigTeacher);
        
        SysConfigTeacherLesson lesson = new SysConfigTeacherLesson();
        Integer teaId = sysConfigTeacher.getId();
        Integer itemOneId = sysConfigTeacher.getItemOneId();
        lesson.setItemOneId(itemOneId);
        lesson.setTeacherId(teaId);
        sysConfigTeacherLessonServiceImpl.insert(lesson);
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(users.getId());
        String ttype = sysConfigTeacher.getTeaOrAdu();
        if ("tea".equals(ttype)) {
            authUserRole.setRoleUid(teaRoleId);
        }else if ("adu".equals(ttype)) {
            authUserRole.setRoleUid(aduRoleId);
        }
        authUserRole.setCreateTime(sysConfigTeacher.getCreateTime());
        authUserRole.setCreator(sysConfigTeacher.getCreator().toString());
        authUserRoleMapper.insert(authUserRole);

    }

    @Override
    public Integer addClassTypeTeacher(Users user) {
        // 添加用户信息
        usersMapper.insert(user);
        // 添加用户角色权限信息
        List<AuthRole> roles=authRoleMapper.findByCompanyId(user.getCompanyId());
        String teaRoleId=new String();  
        if(null!=roles&&roles.size()>0){
        	for(AuthRole ar:roles){
        		if("直播老师".equals(ar.getRoleName())&&null!=ar.getId()){
        			teaRoleId=String.valueOf(ar.getId());
        		}
        	}
        }
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setRoleUid(teaRoleId);
        authUserRole.setUserId(user.getId());
        authUserRole.setUpdateTime(new Date());
        authUserRole.setUpdator(user.getId() + "");
        authUserRole.setCreateTime(new Date());
        authUserRole.setCreator(user.getId() + "");
        authUserRoleMapper.insert(authUserRole);
        // 添加教师信息
        SysConfigTeacher teacher = new SysConfigTeacher();
        teacher.setName(user.getRealName());
        teacher.setTeacherType("PERSON_TEACHER");
        teacher.setMobile(user.getMobile());
        teacher.setCompanyId(user.getCompanyId());
        teacher.setCreateTime(new Date());
        teacher.setCreator(user.getId());
        teacher.setDelFlag(0);
        teacher.setUserId(user.getId());
        teacher.setStatusCode("TEACHER_USERD");
        teacher.setSchoolId(user.getSchoolId());
        sysConfigTeacherMapper.insert(teacher);
        //添加教师学校关系表
        UsersComanyRelation ucr=new UsersComanyRelation();
        ucr.setUserId(user.getId());
        ucr.setCompanyId(user.getCompanyId());
        ucr.setIsUsed(1);
        usersMapper.insertUsersComanyRelation(ucr);
        return teacher.getId();
    }

    @Override
    public List<SysConfigTeacher> findTeachersByUserId(Integer userId) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findteachersByUserId(userId);
    }

    @Override
    public PageFinder<SysConfigTeacher> findByCompanyPage(SysConfigTeacher search) {
        // TODO Auto-generated method stub
        List<SysConfigTeacher> teachers = sysConfigTeacherMapper.findByCompany(search);
        int rowCount = sysConfigTeacherMapper.findByCompanyCount(search);
        PageFinder<SysConfigTeacher> pageFinder = new PageFinder<SysConfigTeacher>(search.getPage(), search.getPageSize(), rowCount, teachers);
        return pageFinder;
    }
    @Override
    public PageFinder<SysConfigTeacher> findNewTeacherPage(SysConfigTeacher search) {
        // TODO Auto-generated method stub
        PageFinder<SysConfigTeacher> pageFinder = null;
        Integer itemOneId = search.getItemOneId();
        if (itemOneId == 0) {
            List<SysConfigTeacher> teachers = sysConfigTeacherMapper.findByNewCompany(search);
            int rowCount = sysConfigTeacherMapper.findByCompanyCount(search);
            pageFinder = new PageFinder<SysConfigTeacher>(search.getPage(), search.getPageSize(), rowCount, teachers);
        } else if (itemOneId > 0) {
            List<SysConfigTeacher> teachers = sysConfigTeacherMapper.queryAllNewTeachersWithItemOneId(search);
            int rowCount = sysConfigTeacherMapper.queryAllTeachersCountWithItemOneId(search);
            pageFinder = new PageFinder<SysConfigTeacher>(search.getPage(), search.getPageSize(), rowCount, teachers);
        }
        return pageFinder;
    }

    @Override
    public PageFinder<SysConfigTeacher> findTeacherPage(SysConfigTeacher search) {
        // TODO Auto-generated method stub
        PageFinder<SysConfigTeacher> pageFinder = null;
        Integer itemOneId = search.getItemOneId();
        if (itemOneId == 0) {
            List<SysConfigTeacher> teachers = sysConfigTeacherMapper.findByCompany(search);
            int rowCount = sysConfigTeacherMapper.findByCompanyCount(search);
            pageFinder = new PageFinder<SysConfigTeacher>(search.getPage(), search.getPageSize(), rowCount, teachers);
        } else if (itemOneId > 0) {
            List<SysConfigTeacher> teachers = sysConfigTeacherMapper.queryAllTeachersWithItemOneId(search);
            int rowCount = sysConfigTeacherMapper.queryAllTeachersCountWithItemOneId(search);
            pageFinder = new PageFinder<SysConfigTeacher>(search.getPage(), search.getPageSize(), rowCount, teachers);
        }
        return pageFinder;
    }

    @Override
    public List<SysConfigTeacher> queryTeachersBycondition(Map<String, String> teacherMap) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.queryTeachersBycondition(teacherMap);
    }

    @Override
    public List<SysConfigTeacher> findSysConfigTeacherByCompany(SysConfigTeacher sysConfigTeahcer) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findSysConfigTeacherByCompany(sysConfigTeahcer);
    }

    @Override
    public List<SysConfigTeacher> findByIds(String[] ids) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findByIds(ids);
    }

    @Override
    public List<SysConfigTeacher> findSysConfigTeacherByName(Map<String, String> map) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findSysConfigTeacherByName(map);
    }

    @Override
    public SysConfigTeacher findTeacherAndUserById(Integer id) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findTeacherAndUserById(id);
    }

    @Override
    public void updateTeaAndUse(SysConfigTeacher sysConfigTeacher) {

        Integer teacherID = sysConfigTeacher.getId();
        Integer delFlag = sysConfigTeacher.getDelFlag();
        

        SysConfigTeacher findT = sysConfigTeacherMapper.findById(teacherID);
        sysConfigTeacherLessonMapper.deleteByTeacherId(teacherID);

        if (delFlag != null && delFlag.equals(1)) {
            SysConfigTeacher delTeacher = new SysConfigTeacher();
            delTeacher.setId(teacherID);
            delTeacher.setDelFlag(1);
            sysConfigTeacher.setUpdateTime(new Date());
            sysConfigTeacherMapper.update(sysConfigTeacher);

            Integer findUid = findT.getUserId();
            Users updateU = new Users();
            updateU.setId(findUid);
            updateU.setStatus(0);
            usersMapper.update(updateU);
        } else {
            String pwd = sysConfigTeacher.getPwd();
            if (pwd != null && pwd != "") {
                Users users = new Users();
                Users user = usersMapper.findById(sysConfigTeacher.getUserId());
                if (user != null) {
                    users.setId(sysConfigTeacher.getUserId());
                    users.setPassword(pwd);
                    users.setPhone(sysConfigTeacher.getMobile());
                    users.setMobile(sysConfigTeacher.getMobile());
                    usersMapper.update(users);

                    // 存在用户则不更新
                    sysConfigTeacher.setCreator(null);
                    sysConfigTeacher.setCompanyId(null);
                    sysConfigTeacher.setSchoolId(null);
                } else {
                    users.setUsername(sysConfigTeacher.getUserName());
                    users.setPassword(sysConfigTeacher.getPwd());
                    users.setCompanyId(sysConfigTeacher.getCompanyId());
                    users.setSchoolId(sysConfigTeacher.getSchoolId());
                    users.setStatus(1);
                    users.setRealName(sysConfigTeacher.getName());
                    users.setMobile(sysConfigTeacher.getMobile());
                    usersMapper.insert(users);
                    //添加教师学校关系表
                    UsersComanyRelation ucr=new UsersComanyRelation();
                    ucr.setUserId(users.getId());
                    ucr.setCompanyId(users.getCompanyId());
                    ucr.setIsUsed(1);
                    usersMapper.insertUsersComanyRelation(ucr);
                    
                    List<AuthRole> roles=authRoleMapper.findByCompanyId(users.getCompanyId());
                    String teaRoleId=new String();  
                    String aduRoleId=new String();
                    if(null!=roles&&roles.size()>0){
                    	for(AuthRole ar:roles){
                    		if("直播老师".equals(ar.getRoleName())&&null!=ar.getId()){
                    			teaRoleId=String.valueOf(ar.getId());
                    		}else if("助教".equals(ar.getRoleName())&&null!=ar.getId()){
                    			aduRoleId=String.valueOf(ar.getId());
                    		}
                    	}
                    }
                    AuthUserRole authUserRole = new AuthUserRole();
                    authUserRole.setUserId(users.getId());
                    String ttype = sysConfigTeacher.getTeaOrAdu();
                    if ("tea".equals(ttype)) {
                        authUserRole.setRoleUid(teaRoleId);
                    }else if ("adu".equals(ttype)) {
                        authUserRole.setRoleUid(aduRoleId);
                    }
                    authUserRole.setCreateTime(new Date());
                    authUserRole.setCreator(sysConfigTeacher.getCreator().toString());
                    authUserRoleMapper.insert(authUserRole);
                }

                sysConfigTeacher.setUserId(users.getId());
            }

            // 去除为用户User提供的信息
            sysConfigTeacher.setCreator(null);
            sysConfigTeacher.setCompanyId(null);
            sysConfigTeacher.setSchoolId(null);

            SysConfigTeacherLesson lesson = new SysConfigTeacherLesson();
            Integer teaId = sysConfigTeacher.getId();
            Integer itemOneId = sysConfigTeacher.getItemOneId();
            String strTwoId = sysConfigTeacher.getItemSecondId();
            if (strTwoId == null || strTwoId == "") {
                strTwoId = "0";
            }
            Integer itemTwoId = Integer.parseInt(strTwoId);
            lesson.setItemOneId(itemOneId);
            lesson.setItemSecondId(itemTwoId);
            lesson.setTeacherId(teaId);
            sysConfigTeacherLessonMapper.insert(lesson);

            sysConfigTeacherMapper.update(sysConfigTeacher);
        }
    }

    @Override
    public List<SysConfigTeacher> findAssistants(Map<String, String> teacherMap) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findAssistants(teacherMap);
    }

    @Override
    public void updateauthTeacher(SysConfigTeacher teacher) {
        // TODO Auto-generated method stub
        sysConfigTeacherMapper.updateauthTeacher(teacher);
    }

    @Override
    public Integer findMobileCount(SysConfigTeacher sct) {
        return sysConfigTeacherMapper.findMobileCount(sct);
    }

    @Override
    public List<SysConfigTeacher> findTeacherByUserId(SysConfigTeacher search) {
        // TODO Auto-generated method stub
        return sysConfigTeacherMapper.findTeacherByUserId(search);
    }

    @Override
    public SysConfigTeacher findByInviteCode(SysConfigTeacher search) {
        return sysConfigTeacherMapper.findByInviteCode(search);
    }
    
    
    @Override
	public PageFinder<TeachersVo> findTeachersBycondition(TeachersVo teacher) {
		List<TeachersVo> data=sysConfigTeacherMapper.queryTeachersData(teacher);
		Integer count=sysConfigTeacherMapper.queryTeachersDataCount(teacher);
		if(null!=teacher && null!=teacher.getCusorder()){
			if(teacher.getCusorder().equals("hot")){
				Collections.sort(data, new Comparator<TeachersVo>(){
					@Override
					public int compare(TeachersVo o1, TeachersVo o2) {
						if(null==o1.getStuNum()||"".equals(o1.getStuNum())){
							o1.setStuNum(0);
						}
						if(null==o2.getStuNum()||"".equals(o2.getStuNum())){
							o2.setStuNum(0);
						}
						return o2.getStuNum().compareTo(o1.getStuNum());
					}
		    	});
			}else if(teacher.getCusorder().equals("good")){
				Collections.sort(data, new Comparator<TeachersVo>(){
					@Override
					public int compare(TeachersVo o1, TeachersVo o2) {
						if(null==o1.getScore()||"".equals(o1.getScore())){
							o1.setScore(0);
						}
						if(null==o2.getScore()||"".equals(o2.getScore())){
							o2.setScore(0);
						}
						return o2.getScore().compareTo(o1.getScore());
					}
		    	});
			}
//			else{
//				Collections.sort(data, new Comparator<TeachersVo>(){
//					@Override
//					public int compare(TeachersVo o1, TeachersVo o2) {
//						return o2.getId().compareTo(o1.getId());
//					}
//		    	});
//			}
		}
		PageFinder<TeachersVo> pageFinder=new PageFinder<TeachersVo>(teacher.getPage(), teacher.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public int updateSortId(SysConfigTeacher search) {
		// TODO Auto-generated method stub
		return sysConfigTeacherMapper.updateSortId( search);
	}

	@Override
	public int checkSortCount() {
		// TODO Auto-generated method stub
		return sysConfigTeacherMapper.checkSortCount();
	}

	@Override
	public List<SysConfigTeacher> findTeacherBySubject(Map<String, Object> map) {
		List<SysConfigTeacher> list = sysConfigTeacherMapper.findTeacherBySubject(map);
		return list;
	}

}
