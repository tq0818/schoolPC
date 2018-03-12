package com.yuxin.wx.classes.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.classes.mapper.ClassModuleLessonMapper;
import com.yuxin.wx.classes.mapper.ClassModuleMapper;
import com.yuxin.wx.classes.mapper.ClassModuleNoMapper;
import com.yuxin.wx.classes.mapper.ClassTypeMapper;
import com.yuxin.wx.classes.mapper.ClassTypeModuleRelationMapper;
import com.yuxin.wx.classes.mapper.ClassTypeRemoteRelationMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.course.mapper.CourseRemoteMapper;
import com.yuxin.wx.course.mapper.CourseVideoChapterMapper;
import com.yuxin.wx.course.mapper.CourseVideoMapper;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeModuleRelation;
import com.yuxin.wx.model.classes.ClassTypeRemoteRelation;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.system.mapper.SysConfigItemTagMapper;
import com.yuxin.wx.system.mapper.SysConfigTeacherMapper;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import com.yuxin.wx.vo.classes.ClassLessonVO;
import com.yuxin.wx.vo.classes.ClassPackageConditionVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.company.MemberLevelAndClassTypeVo;
import com.yuxin.wx.vo.redis.ClassLectureVO;
import com.yuxin.wx.vo.student.StudentListVo;
import com.yuxin.wx.vo.user.UsersFrontVo;

/**
 * Service Implementation:ClassType
 * @author wang.zx
 * @date 2014-12-5
 */
@Service
@Transactional
public class ClassTypeServiceImpl extends BaseServiceImpl implements IClassTypeService {

	@Autowired
	private ClassTypeMapper classTypeMapper;
	
	@Autowired
	private CourseVideoMapper courseVideoMapper;
	
	@Autowired
	private CourseRemoteMapper courseRemoteMapper;
	
	@Autowired
	private ClassModuleMapper classModuleMapper;
	@Autowired
	private ClassTypeModuleRelationMapper classTypeModuleRelationMapper;
	@Autowired
	private ClassTypeRemoteRelationMapper classTypeRemoteRelationMapper;
	@Autowired
	private CourseVideoChapterMapper courseVideoChapterMapper;
	@Autowired
	private SysConfigItemTagMapper sysConfigItemTagMapper;
	@Autowired
	private ClassModuleLessonMapper moduleLessonMapper;
	@Autowired
	private ClassModuleNoMapper moduleNoMapper;
	@Autowired
	private ClassTypeModuleRelationMapper relatiomMapper;
	@Autowired
	private SysConfigTeacherMapper teacherMapper;

	@Autowired
	private UsersFrontMapper usersFrontMapper;

	/**
	 * 
	* @Title: saveClassType
	* @Description: 新增ClassType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void insert(ClassType classType){
		classTypeMapper.insert(classType);
	}
	
	/**
	 * 
	* @Title: batchSaveClassType 
	* @Description: 批量新增ClassType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void batchInsert(List<ClassType> classTypes){
		if(classTypes != null && !classTypes.isEmpty()){
			classTypeMapper.batchInsert(classTypes);
		}
	}
	
	/**
	 * 
	* @Title: updateClassType 
	* @Description: 编辑ClassType
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void update(ClassType classType){
		classTypeMapper.update(classType);
	}
	
	/**
	 * 
	* @Title: deleteClassTypeById 
	* @Description: 根据id删除ClassType
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassTypeById(Integer id){
		classTypeMapper.deleteById(id);
	}
	
	/**
	 * 
	* @Title: deleteClassTypeByIds 
	* @Description: 根据id批量删除ClassType
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public void deleteClassTypeByIds(Integer[] ids){
		classTypeMapper.deleteByIds(ids);
	}
	
	/**
	 * 
	* @Title: findClassTypeById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	@Override
	public ClassType findClassTypeById(Integer id){
		return classTypeMapper.findById(id);
	}

	/**
	 * 
	* @Title: findClassTypeById 
	* @Description: 查询班型列表
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by Chopin
	 */
	@Override
	public List<ClassType> findClassTypeList(ClassType classType) {
		
		return classTypeMapper.findListByItem(classType);
	}
	
	/**
	 * 
	* @Title: findClassTypeById 
	* @Description: 查询班型列表
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by Chopin
	 */
	@Override
	public List<ClassType> findClassTypeList2(ClassType classType) {
		
		return classTypeMapper.findListByItem2(classType);
	}
	
	/**
	 * 
	* @Title: findClassTypeByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassType>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by zhangbo
	 */
	@Override
	public PageFinder<ClassTypeVo> queryClassTypeByKeysForPage(ClassType search){
		Integer rowCount = classTypeMapper.pageCount(search);
		List<ClassTypeVo> data=classTypeMapper.queryClassTypeByKeysForPage(search);
		PageFinder<ClassTypeVo> pageFinder=new PageFinder<ClassTypeVo>(search.getPage(), search.getPageSize(), rowCount, data);
		return pageFinder;
	}
	
	/**
	 * 
	* @Title: findClassTypeDetail 
	* @Description: 根据id查询班级详细信息
	* @return
	* @return ClassTypeVo 返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	public ClassTypeVo findClassTypeDetail(Map<String,String> map) {
		ClassTypeVo classvo=classTypeMapper.findDetailById(map);
		List<ClassModule> classModules=classModuleMapper.findListByClassId(map);
		List<CourseVideoChapter> chapters=courseVideoChapterMapper.findByClassId(map);
		Integer totalClass=0;
		for(ClassModule classModule: classModules){
			if(classModule.getTotalClassHour()!=null){
				totalClass+=classModule.getTotalClassHour();
			}
		}
		
		classvo.setModules(classModules);
		classvo.setVideos(chapters);
		Integer id=map.get("classId")!=null?Integer.parseInt(map.get("classId")):null;
		classvo.setRemotes(courseRemoteMapper.findRemotesByClassTypeId(id));
		//classvo.setVideos(videos);
		classvo.setTotalClass(totalClass);
		return classvo;
	};
	
	@Override
	public void updateClassTypeRalation(ClassType classType,String ralationsIdVal){
		String type=classType.getTypeCode();
		int index=ralationsIdVal.indexOf(",");
		if(index>0){
			if(type.equals("CLASS_TYPE_NOMAL")){
				List<ClassTypeModuleRelation> listCmrs=new ArrayList<ClassTypeModuleRelation>();
				String [] moduleIds=ralationsIdVal.split(",");
				for(int i=0;i<moduleIds.length;i++){
					ClassTypeModuleRelation cmr=new ClassTypeModuleRelation();
					cmr.setClassTypeId(classType.getId());
					cmr.setModuleId(Integer.parseInt(moduleIds[i]));
					listCmrs.add(cmr);
				}
				classTypeModuleRelationMapper.batchInsert(listCmrs);
			}else if(type.equals("CLASS_TYPE_REMOTE")){
				List<ClassTypeRemoteRelation> listCRrs=new ArrayList<ClassTypeRemoteRelation>();
				String [] rids=ralationsIdVal.split(",");
				for(int i=0;i<rids.length;i++){
					ClassTypeRemoteRelation crr=new ClassTypeRemoteRelation();
					crr.setClassTypeId(classType.getId());
					crr.setRemoteId(Integer.parseInt(rids[i]));
					listCRrs.add(crr);
				}
				classTypeRemoteRelationMapper.batchInsert(listCRrs);
			}
		}else{
			if(type.equals("CLASS_TYPE_NOMAL")){
				ClassTypeModuleRelation cmr=new ClassTypeModuleRelation();
				cmr.setClassTypeId(classType.getId());
				cmr.setModuleId(Integer.parseInt(ralationsIdVal));
				classTypeModuleRelationMapper.insert(cmr);
			}else if(type.equals("CLASS_TYPE_REMOTE")){
				ClassTypeRemoteRelation crr=new ClassTypeRemoteRelation();
				crr.setClassTypeId(classType.getId());
				crr.setRemoteId(Integer.parseInt(ralationsIdVal));
				classTypeRemoteRelationMapper.insert(crr);
			}
		}
	}

	@Override
	public ClassType findClassTypeByStuId(Integer id, Integer companyId,Integer payMasterId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("companyId", companyId);
		map.put("payMasterId", payMasterId);
		return classTypeMapper.findClassTypeByStuId(map);
	}

	@Override
	public List<ClassType> findByItem(Integer conpanyId,Integer schoolId, Integer itemOneId,Integer itemTwoId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("companyId", conpanyId);
		map.put("itemOneId", itemOneId);
		map.put("itemTwoId", itemTwoId);
		map.put("createSchoolId", schoolId);
		return classTypeMapper.findByItem(map);
	}

	@Override
	public PageFinder<ClassTypeVo> findClassTypesByPage(ClassType search) {
		System.out.println(search.getPage());
		String []codes = new String [1];
		List<ClassTypeVo> data = new ArrayList<ClassTypeVo>();
		if (search.getItemFourthCode()!=null && search.getItemFourthCode().length()>0){
			codes = search.getItemFourthCode().split(",");
		}
		Map<String,Object> map  = new HashMap<>();
		map.put("classType",search);
		map.put("codes",codes);
		data=classTypeMapper.queryClassTypesByPage(map);
		
	
			for(ClassTypeVo comm:data)
			{
			Integer videoFlag=comm.getVideoFlag();
			Integer liveFlag=comm.getLiveFlag();
			Integer faceFlag=comm.getFaceFlag();
			Integer remoteFlag=comm.getRemoteFlag();
			if(videoFlag==null){
				comm.setVideoFlag(0);
			}
			if(liveFlag==null){
				comm.setLiveFlag(0);
			}
			if(faceFlag==null){
				comm.setFaceFlag(0);
			}
			if(remoteFlag==null){
				comm.setRemoteFlag(0);
			}
		}
		int rowCount=classTypeMapper.queryCounts(map);
		PageFinder<ClassTypeVo> pageFinder=new PageFinder<ClassTypeVo>(search.getPage(), search.getPageSize(), rowCount, data);
		return pageFinder;
		
		
	}

	@Override
	public List<ClassType> findByRemote(Integer id) {
		return classTypeMapper.findByRemote( id);
	}
	@Override
	public ClassType findClassTypeByName(String name) {
		return classTypeMapper.queryClassTypeByName(name);
	}

	@Override
	public List<ClassType> findByModule(Integer id) {
		return classTypeMapper.findByModule(id);
	}


	
	@Override
	public List<String> findNameById(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return classTypeMapper.findNameById(param);
	}
	
	@Override
	public List<ClassType> findClassTypeList3(Integer companyId) {
		return classTypeMapper.findClassTypeList3(companyId);
	}
	@Override
	public List<ClassType> findClassTypeList4(Integer companyId) {
		return classTypeMapper.findClassTypeList4(companyId);
	}

	@Override
	public List<ClassType> findByItemOne(Integer itemOneId, Integer companyId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("itemOneId", itemOneId);
		map.put("companyId", companyId);
		return classTypeMapper.findByItemOne(map);
	}

	@Override
	public void changClassTypeCollectStatus(ClassType classType) {
		classTypeMapper.changClassTypeCollectStatus(classType);
	}

	@Override
	public List<ClassType> findClassTypeExits(ClassType classType) {
		// TODO Auto-generated method stub
		return classTypeMapper.queryClassTypeExits(classType);
	}

	@Override
	public List<ClassType> findClassTypeByCondition(ClassType search) {
		return classTypeMapper.queryClassBycondition(search);
	}

	@Override
	public List<ClassType> findClassByItem(Integer conpanyId, Integer schoolId,
			Integer itemOneId, Integer itemTwoId) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("companyId", conpanyId);
		map.put("itemOneId", itemOneId);
		map.put("itemTwoId", itemTwoId);
		map.put("createSchoolId", schoolId);
		return classTypeMapper.findClassByItem(map);
	}
	@Override
	public List<ClassType> findClassByItemRelation(Integer conpanyId, Integer schoolId,
												   String itemOneCode, String itemSecondCode, String itemThridCode, Integer liveFlag, Integer videoFlag) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", conpanyId);
		map.put("itemOneCode", itemOneCode);
		map.put("itemSecondCode", itemSecondCode);
		map.put("itemThirdCode", itemThridCode);
		map.put("createSchoolId", schoolId);
		map.put("liveFlag", liveFlag);
		map.put("videoFlag", videoFlag);
		return classTypeMapper.findClassByItemRelation(map);
	}



	@Override
	public List<ClassType> findClassTypeBycompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return classTypeMapper.findClassTypeBycompanyId(companyId);
	}

	@Override
	public ClassType findClassTypeByCommodity(Integer id) {
		return classTypeMapper.findClassTypeByCommodity(id);
	}

	@Override
	public List<Integer> findClassTypeByClass(ClassType ct) {
		// TODO Auto-generated method stub
		return classTypeMapper.findClassTypeByClass(ct);
	}

	@Override
	public void updateDeflag(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classTypeMapper.updateDeflag(param);
	}

	@Override
	public List<ClassTypeVo> queryClassTypesByClassPackage(
			ClassPackageConditionVo search) {
		// TODO Auto-generated method stub
		return classTypeMapper.queryClassTypesByClassPackage(search);
	}

	@Override
	public List<ClassTypeVo> queryIsExistByClassPackage(
			ClassPackageConditionVo search) {
		// TODO Auto-generated method stub
		return classTypeMapper.queryIsExistByClassPackage(search);
	}

	@Override
	public PageFinder<ClassTypeVo> queryClassTypeForMember(MemberLevelAndClassTypeVo mlactVo) {
		List<ClassTypeVo> classTypeVoList=classTypeMapper.queryClassTypeForMember(mlactVo);
		int count=classTypeMapper.queryClassTypeForMemberCount(mlactVo);
		PageFinder<ClassTypeVo> pageFinder=new PageFinder<ClassTypeVo>(mlactVo.getPage(), mlactVo.getPageSize(), count, classTypeVoList);
		return pageFinder;
	}

	@Override
	public List<ClassType> queryCourseByOneAndTwoItem(ClassType search) {
		return classTypeMapper.queryClassTypeByOneAndTwoItem(search);
	}
	
	@Override
	public ClassTypeVo findClassTypeVoByClassTypeId(Integer classTypeId){
		return classTypeMapper.findClassTypeVoByClassTypeId(classTypeId);
	}

	@Override
	public List<ClassType> findAllclassType(ClassType cp) {
		return classTypeMapper.findAllclassType(cp);
	}

	@Override
	public int updateSubjectClassOrder(ClassType cp) {
		int row = classTypeMapper.updateSubjectClassOrder(cp);
		return row;
	}

	@Override
	public int countSubjectClassOrder(String itemOneCode) {
		int count = classTypeMapper.countSubjectClassOrder(itemOneCode);
		return count;
	}

	@Override
	public List<ClassTypeVo> queryLiveClassOfBranchSchool(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return classTypeMapper.queryLiveClassOfBranchSchool(param);
	}

	@Override
	public int queryCountLiveClassOfBranchSchool(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return classTypeMapper.queryCountLiveClassOfBranchSchool(param);
	}
	

	@Override
	public List<ClassTypeVo> queryClassOfBranchSchool(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return classTypeMapper.queryClassOfBranchSchool(param);
	}

	@Override
	public int queryCountClassOfBranchSchool(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return classTypeMapper.queryCountClassOfBranchSchool(param);
	}

	@Override
	public List<ClassTypeVo> queryLiveClassOfOtherSchool(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return classTypeMapper.queryLiveClassOfOtherSchool(param);
	}

	@Override
	public int queryCountLiveClassOfOtherSchool(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return classTypeMapper.queryCountLiveClassOfOtherSchool(param);
	}

	@Override
	public List<ClassLectureVO> getClassTypeListVideo(ClassType search) {
		return classTypeMapper.getClassTypeListVideo(search);
	}

	@Override
	public List<ClassLectureVO> getClassTypeListLive(ClassType search) {
		return classTypeMapper.getClassTypeListLive(search);
	}

	@Override
	public List<ClassType> getClassTimeList(ClassType search) {
		return classTypeMapper.getClassTimeList(search);
	}

	@Override
	public List<ClassLessonVO> getClassLessonLogList(List<Integer> stuIdsList, List<Integer> lessonIdsList) {
		Map<String,List<Integer>> map = new HashMap<>();
		map.put("stuIdsList", stuIdsList);
		map.put("lessonIdsList", lessonIdsList);
		return classTypeMapper.getClassLessonLogList( map);
	}

	@Override
	public JSONObject getListDatas(StudentListVo search,ClassType classType) {
		JSONObject jsonObject = new JSONObject();
		List<ClassLectureVO> classList =classTypeMapper.getClassTypeListVideo(classType);
		Map<Integer,ClassLectureVO> map = new HashMap<>();
		for(ClassLectureVO tempClassLessonVO : classList){
			map.put(tempClassLessonVO.getId(), tempClassLessonVO);
		}
		/*//获取Redis缓存课程列表
		Map<Integer,ClassLectureVO> map = RedisHelper.getInstance().getClassLectureMap(Long.valueOf(search.getUserId()),Long.parseLong(String.valueOf(search.getCompanyId())),classType.getItemSecondCode(),search.getEduStep(),classType.getSubject());
		if(null == map){
			//Redis中不存在，查询
			if(search.getLiveFlag().equals("0")){
				//录播课程列表
				classList = classTypeMapper.getClassTypeListVideo(classType);
				map = new HashMap<>();
				for(ClassLectureVO temp : classList){
					temp.initVedioLen();//调用方法转换视频格式得到视频长度
					map.put(Integer.valueOf(temp.getId()),temp);
				}
				RedisHelper.getInstance().putClassLectureMap(Long.valueOf(search.getUserId()),Long.parseLong(String.valueOf(search.getCompanyId())),classType.getItemSecondCode(),search.getEduStep(),classType.getSubject(),map);
			}else{
				//直播课程列表
				// classList = classTypeServiceImpl.getClassTypeListLive(classType);
			}
		}else{
			classList = new ArrayList<>();
			//List<ClassLectureVO>
			for (Map.Entry< Integer,ClassLectureVO> entry : map.entrySet()) {
				classList.add(entry.getValue());
			}
		}*/

		//获取年级或班级下的所有学生列表
		List<UsersFrontVo> stuList = usersFrontMapper.getStuList(search);
		int stuCount = usersFrontMapper.getStuListCount(search);

		//根据学生列表和课程列表查询观看记录
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
		Map<String,List<Integer>> maps = new HashMap<>();
		maps.put("stuIdsList", stuIdsList);
		maps.put("lessonIdsList", lessonIdsList);
		//获取录播观看记录
		List<ClassLessonVO>  lectureVOList = classTypeMapper.getClassLessonLogList(maps);

		if(null == lectureVOList){
			//TODO  没有获取到记录
			return jsonObject;
		}else{
			System.out.println("lectureVOList size = "+lectureVOList.size());
			for(ClassLessonVO v : lectureVOList){
				System.out.println(v);
			}
		}

		for(ClassLessonVO vo :lectureVOList ){
			System.out.println("课程观看记录"+vo);
		}

		//组装数据
		//List<UsersFrontVo> stuList
		//初始化二维关系
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

		for(ClassLessonVO lessonVO : lectureVOList){
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
				System.out.println("map中没有找到对应学生的观看记录");
			}
		}

		//组装返回web json
		//组装课程列表
		JSONObject obj = null;


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
		jsonObject.put("classList",classList);
		jsonObject.put("pageFinder", pageFinder);
		return jsonObject;
	}

}
