package com.yuxin.wx.user.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.classes.mapper.ClassTypeMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SimplePage;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import com.yuxin.wx.vo.classes.ClassLessonVO;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.company.CompanySchoolVO;
import com.yuxin.wx.vo.redis.ClassLectureVO;
import com.yuxin.wx.vo.student.SelectStudentOrUsersfrontVo;
import com.yuxin.wx.vo.student.StudentListVo;
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

    
    @Autowired
    private ClassTypeMapper classTypeMapper;
    
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

    @Override
    public List<UsersFrontVo> getUserLearningList(StudentListVo search) {
        return usersFrontMapper.getUserLearningList(search);
    }

    @Override
    public Integer getUserLearningListCount(StudentListVo search) {
        return usersFrontMapper.getUserLearningListCount(search);
    }

    @Override
    public List<ClassTypeVo> getClassTimeList() {
        return usersFrontMapper.getClassTimeList();
    }

    @Override
    public List<UsersFrontVo> getStuList(StudentListVo search) {
        return usersFrontMapper.getStuList(search);
    }

    @Override
    public Integer getStuListCount(StudentListVo search) {
        return usersFrontMapper.getStuListCount(search);
    }

	@Override
	public SimplePage getUserClassStudyAsSchoolResponse(StudentListVo search, Users loginUser  ) {
		search.setUserId(loginUser.getId());
		 //获取年级或班级下的所有学生列表
        List<UsersFrontVo> stuList = usersFrontMapper.getStuList(search);
        if(null == stuList || stuList.size() == 0){
        	//如果获取学生信息失败，直接返回，不再进行下一步查询
        	return getFaild();
        }
        int stuCount = usersFrontMapper.getStuListCount(search);
		
        //组装年级信息，用于查询课程列表
        ClassType classType = getClassTypeByEduStepYear(search.getEduStep(), search.getEduYear());
        if(null == classType){
        	SimplePage pg = new SimplePage();
        	pg.setData(getStudentList(stuList));
        	pg.setCount(stuCount);
            pg.setPage(search.getPage() / search.getPageSize() );
            pg.setSize(search.getPageSize());
        	return pg;
        }
        classType.setEduStep(search.getEduStep());
        classType.setEduYear(search.getEduYear());
        if(null != search.getEduClass() && !"".equals(search.getEduClass())){
            classType.setEduClass(search.getEduClass());
        }
        classType.setSubject(search.getSubject());
        classType.setLiveFlag(Integer.parseInt(search.getLiveFlag()));
        classType.setCompanyId(search.getCompanyId());
        //获取课程列表并放入map
      //  List<ClassLectureVO>  classList = classTypeServiceImpl.getClassTypeListVideo(classType);
        
        
       
        
        
        //录播课程
        List<ClassLectureVO>  classList = null;
        if ("0".equals(search.getLiveFlag())){
        	classList = classTypeMapper.getClassTypeListVideo(classType);
        	if(null != classList){
        		for(int i = 0;i<classList.size();i++){
            		classList.get(i).initVedioLen();//调用方法转换视频格式得到视频长度
            	}
        	}
        	System.out.println("课程数量为:"+classList.size());
        	if(classList.size() == 0){
        		SimplePage pg = new SimplePage();
        		pg.setData(getStudentList(stuList));
        		pg.setCount(stuCount);
                pg.setPage(search.getPage() / search.getPageSize() );
                pg.setSize(search.getPageSize());
        		return pg;
        	}
        	
        }else{
        	//直播课程
        	classList = classTypeMapper.getClassTypeListLive(classType);
        	if(null == classList || classList.size() == 0){
        		SimplePage pg = new SimplePage();
        		pg.setData(getStudentList(stuList));
        		pg.setCount(stuCount);
                pg.setPage(search.getPage() / search.getPageSize() );
                pg.setSize(search.getPageSize());
        		return pg;
        	}
        	
        	for(int i = 0;i<classList.size();i++){
        		classList.get(i).setVideoLen(classList.get(i).getLiveLessonTime());
        	}
        	
        }
        
        Map<Integer,ClassLectureVO> map = new HashMap<>();
        for(ClassLectureVO temp : classList){
        	if(temp.getId() != null){
        		map.put(Integer.valueOf(temp.getId()),temp);
        	}
        }
        
       
        
      //组装学生id
        List<Integer> stuIdsList = new ArrayList<>();
        for(UsersFrontVo fu : stuList){
        	stuIdsList.add(Integer.valueOf(fu.getUserId()));
        }
        //组装课程id
        List<Integer> lessonIdsList = new ArrayList<>();
        for (Map.Entry<Integer, ClassLectureVO> entry : map.entrySet()) {
        	lessonIdsList.add(entry.getKey());
        }
        
        List<ClassLessonVO>  lessonVOList  = null;
		if ("0".equals(search.getLiveFlag())) {
			// 录播
			Map<String, List<Integer>> pmap = new HashMap<>();
			pmap.put("stuIdsList", stuIdsList);
			pmap.put("lessonIdsList", lessonIdsList);
			lessonVOList = classTypeMapper.getClassLessonLogList(pmap);
			// classTypeServiceImpl.getClassLessonLogList(stuIdsList,lessonIdsList);
			if (null == lessonVOList) {
				return getFaild();
			}
			
		} else {
			// 直播
//			/
			Map<String, List<Integer>> pmap = new HashMap<>();
			pmap.put("stuIdsList", stuIdsList);
			pmap.put("lessonIdsList", lessonIdsList);
			lessonVOList = classTypeMapper.getClassLessonLiveList(pmap);
			if (null == lessonVOList) {
				return getFaild();
			}
			
			for(int j = 0;j< lessonVOList.size();j++){
				lessonVOList.get(j).setLen(lessonVOList.get(j).getLiveWatchTime() / 1000);
			}
			
		}
        
        //组装数据
        Map<String,Map<String,Integer> > resultMap = new HashMap<>(); 
        Map<String,Map<String,Integer> > studyTimeMap = new HashMap<>(); 
        Map<String,Integer> initMap = null;
        Map<String,Integer> initTimeMap = null;
        for(int i=0;i<stuList.size();i++){
        	initMap =  new HashMap<String,Integer>();
        	initTimeMap =  new HashMap<String,Integer>();
        	for(Integer ids : map.keySet()){
    			initMap.put(""+ids, 0);
    			initTimeMap.put(""+ids, 0);
    		}
        	resultMap.put(""+stuList.get(i).getUserId(), initMap);
        	studyTimeMap.put(""+stuList.get(i).getUserId(),initTimeMap);
        }
        
        
        
       
        //处理录播观看记录
        Map<String,Integer> temp = null;
        Map<String,Integer> studyTemp = null;
        
       
        for(ClassLessonVO lessonVO : lessonVOList){
        	System.out.println("循环课程查询结果,userId = "+lessonVO.getUserId());
        	temp = resultMap.get(""+lessonVO.getUserId());
        	studyTemp = studyTimeMap.get(""+lessonVO.getUserId());
        	if(null != temp){
        		//Integer flag = 0;
        		ClassLectureVO maplesson = map.get(Integer.valueOf(lessonVO.getLectureId()));
        		if(1.0f*lessonVO.getLen()/maplesson.getVideoLen() >= 0.7){
        			temp.put(""+maplesson.getId(), 1);
        		}
        		studyTemp.put(""+maplesson.getId(), lessonVO.getLen());
        	}else{
        		return getFaild();
        	}
        }
        
        //组装返回web json
        //组装课程列表
        JSONObject obj = null;
        
       // PageFinder<UsersFrontVo> pageFinder = new PageFinder(page,pageSize,count,lessonArr);

        JSONObject pageFinder = new JSONObject();
        pageFinder.put("page", 1);
        pageFinder.put("size", 10);
        pageFinder.put("count", stuCount);
        
        JSONArray lessonArr = null;
        JSONArray arr = new JSONArray();
        for(UsersFrontVo vo : stuList){
        	Map<String,Integer> flagMap = resultMap.get(""+vo.getUserId());
        	obj = new JSONObject();
        	
        	lessonArr = new JSONArray();
        	int countClass = 0;
        	int classTime = 0;
        	for(ClassLectureVO classTemp : classList){
        		lessonArr.add(flagMap.get(""+classTemp.getId()));
        	}
        	
        	Map<String,Integer> studyTempMap = studyTimeMap.get(""+vo.getUserId());
        	if(null != studyTempMap){
        		for (Map.Entry< String,Integer> entry : studyTempMap.entrySet()) {  
               	  //classList.add(entry.getValue());
        			if(entry.getValue() != 0){
        				countClass ++;
        				classTime += entry.getValue();
        			}
              	} 
        	}
        	vo.setCountClass(String.valueOf(countClass));
        	vo.setStudyTime(String.valueOf(classTime/60));
        	obj.put("info", vo);
        	
        	obj.put("list", lessonArr);
        	arr.add(obj);
        }

        pageFinder.put("data", arr);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("classList",classList);
        jsonObject.put("pageFinder", pageFinder);
        SimplePage pg = new SimplePage();
        pg.setCount(stuCount);
        pg.setData(jsonObject);
        pg.setPage(search.getPage() / search.getPageSize() );
        pg.setSize(search.getPageSize());
		return pg;
	}

	
	private SimplePage getFaild(){
		SimplePage pg = new SimplePage();
		JSONObject obj = new JSONObject();
		obj.put("classList", new JSONArray());
		JSONObject pageFinder = new JSONObject();
		pageFinder.put("data", new JSONArray());
		pageFinder.put("count", 0);
		obj.put("pageFinder", pageFinder);
		pg.setData(obj);
		return pg;
	}
	
	private JSONObject getStudentList( List<UsersFrontVo> stuList){
		/* pageFinder.put("data", arr);
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("classList",classList);
	        jsonObject.put("pageFinder", pageFinder);*/
	        
	      JSONObject json = new JSONObject();
	      if(null == stuList){
	    	  return json;
	      }
		
	      JSONArray arr = new JSONArray();
	      JSONObject obj = null;
	      for(UsersFrontVo vo : stuList){
	    	  obj = new JSONObject();
	    	  obj.put("info", vo);
	    	  arr.add(obj);
	      }
	      JSONObject json2 = new JSONObject();
	      json2.put("data", arr);
	      json.put("pageFinder", json2);
	      json.put("classList", new JSONArray());
		return json;
	}
	
	@Override
	public ClassType getClassTypeByEduStepYear(String step, String eduYear) {
		try {
			ClassType classType = new ClassType();
			// 获取当前时间
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;

			if ("STEP_01".equals(step)) {
				classType.setItemName("小%");
				if (month >= 9) {
					if (year - Integer.parseInt(eduYear) + 1 <= 3) {
						classType.setItemSecondName("TYPE_LOW");
					} else if (year - Integer.parseInt(eduYear) + 1 == 4) {
						classType.setItemSecondName("GRADE_FOUR");
					} else if (year - Integer.parseInt(eduYear) + 1 == 5) {
						classType.setItemSecondName("GRADE_FIVE");
					} else {
						classType.setItemSecondName("GRADE_SIX");
					}
				} else {
					if (year - Integer.parseInt(eduYear) <= 3) {
						classType.setItemSecondName("TYPE_LOW");
					} else if (year - Integer.parseInt(eduYear) == 4) {
						classType.setItemSecondName("GRADE_FOUR");
					} else if (year - Integer.parseInt(eduYear) == 5) {
						classType.setItemSecondName("GRADE_FIVE");
					} else {
						classType.setItemSecondName("GRADE_SIX");
					}
				}
			} else if ("STEP_02".equals(step)) {
				classType.setItemName("初%");
				classType.setItemSecondName("MID_ONE");
			} else if ("STEP_03".equals(step)) {
				classType.setItemName("高%");
				classType.setItemSecondName("HIHER_ONE");
			} else {
				return null;
			}
			return classType;
		} catch (Exception e) {
			return null;
		}

	}
	
	

	
	
	
}
