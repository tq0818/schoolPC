package com.yuxin.wx.classes.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.classes.IClassModuleNoOnsaleService;
import com.yuxin.wx.api.classes.IClassModuleNoService;
import com.yuxin.wx.api.classes.IClassModuleService;
import com.yuxin.wx.api.classes.IClassTypeModuleRelationService;
import com.yuxin.wx.api.classes.IClassTypeOfBranchSchoolService;
import com.yuxin.wx.api.classes.IClassTypeResourceService;
import com.yuxin.wx.api.classes.IClassTypeResourceTypeService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.api.course.ICourseExerciseService;
import com.yuxin.wx.api.tiku.ITikuCategoryService;
import com.yuxin.wx.api.tiku.ITikuPaperService;
import com.yuxin.wx.api.tiku.ITikuPaperTopicService;
import com.yuxin.wx.api.tiku.ITikuSubjectService;
import com.yuxin.wx.api.tiku.ITikuTopicService;
import com.yuxin.wx.classes.mapper.ClassTypeMapper;
import com.yuxin.wx.classes.mapper.ClassTypeOfBranchSchoolMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassModuleNoOnsale;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeModuleRelation;
import com.yuxin.wx.model.classes.ClassTypeResource;
import com.yuxin.wx.model.classes.ClassTypeResourceType;
import com.yuxin.wx.model.classes.SchoolShareClassType;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.course.CourseExercise;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.model.tiku.TikuCategory;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.TikuPaperTopic;
import com.yuxin.wx.model.tiku.TikuSubject;
import com.yuxin.wx.model.tiku.TikuTopic;
import com.yuxin.wx.system.mapper.SysConfigDictMapper;
import com.yuxin.wx.vo.classes.ClassTypeResourceVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.course.CourseExerciseVo;

@Service
@Transactional
public class ClassTypeOfBranchSchoolService extends BaseServiceImpl implements
		IClassTypeOfBranchSchoolService {
	Log log=LogFactory.getLog("log");
	
	@Autowired
	private SysConfigDictMapper sysConfigDictMapper;
	
	@Autowired
	private ClassTypeOfBranchSchoolMapper classTypeOfBranchSchoolMapper;

    @Autowired
    private IClassTypeResourceTypeService classTypeResourceTypeServiceImpl;
	
	@Autowired
	private ClassTypeMapper classTypeMapper;
	
	@Autowired
	private ICommodityProductRealtionService commodityProductRealtionServiceImpl;

	@Autowired
	private IClassModuleService classModuleServiceImpl;
	
	@Autowired
	private IClassModuleNoService classModuleNoService ;

	@Autowired
	private IClassModuleNoOnsaleService classModuleNoOnsaleServiceImpl;
	
	@Autowired
	private IClassModuleLessonService classModuleLessonService;
	
	@Autowired
	private IClassTypeResourceService classTypeResourceService ;

	@Autowired
	private IClassTypeModuleRelationService classTypeModuleRelationServiceImpl;

	@Autowired
	private ICourseExerciseService courseExerciseService;
	
	@Autowired
	private ITikuCategoryService tikuCategoryService;
	
	@Autowired
	private ITikuSubjectService tikuSubjectService;
	
	@Autowired
	private ITikuPaperService tikuPaperService;
	
	@Autowired
	private ITikuTopicService tikuTopicService;
		
	@Override
	public List<SysConfigDict> findByParentId(String parentId) {
		// TODO Auto-generated method stub
		return sysConfigDictMapper.findByParentId(parentId);
	}
	
	@Override
	public List<SysConfigDict> findByDicCode(String code) {
		return sysConfigDictMapper.findByDicCode(code);
	}

	@Override
	public List<ClassTypeVo> queryClassTypeOfBranchSchool(Map<String, Object> param) {
		return classTypeOfBranchSchoolMapper.queryClassTypeOfBranchSchool(param);
	}

	@Override
	public int queryCountClassTypeOfBranchSchool(Map<String, Object> param) {
		return classTypeOfBranchSchoolMapper.queryCountClassTypeOfBranchSchool(param);
	}


	@Override
	public List<SysConfigItemRelation> findItemFront(
			Map<String, Object> param) {
		return classTypeOfBranchSchoolMapper.findItemFront(param);
	}

	@Override
	public void battchUpOrDown(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classTypeOfBranchSchoolMapper.battchUpOrDown(param);
	}

	public ClassTypeVo findClassTypeDetail(Map<String,String> map) {
		ClassTypeVo classvo=classTypeOfBranchSchoolMapper.findDetailById(map);
		List<ClassModule> classModules=classTypeOfBranchSchoolMapper.findListByClassId(map);
		List<CourseVideoChapter> chapters=classTypeOfBranchSchoolMapper.findByClassId(map);
		Integer totalClass=0;
		if(null!=classModules&&classModules.size()>0){
			for(ClassModule classModule: classModules){
				if(classModule.getTotalClassHour()!=null){
					totalClass+=classModule.getTotalClassHour();
				}
			}
		}
		classvo.setModules(classModules);
		classvo.setVideos(chapters);
		//classvo.setRemotes(classTypeOfBranchSchoolMapper.findRemotesByClassTypeId(map));
		classvo.setTotalClass(totalClass);
		return classvo;
	}
	
	public ClassTypeVo findClassTypeDetail1(Map<String,String> map) {
		ClassTypeVo classvo=classTypeOfBranchSchoolMapper.findDetailById1(map);
		List<ClassModule> classModules=classTypeOfBranchSchoolMapper.findListByClassId(map);
		List<CourseVideoChapter> chapters=classTypeOfBranchSchoolMapper.findByClassId(map);
		Integer totalClass=0;
		if(null!=classModules&&classModules.size()>0){
			for(ClassModule classModule: classModules){
				if(classModule.getTotalClassHour()!=null){
					totalClass+=classModule.getTotalClassHour();
				}
			}
		}
		classvo.setModules(classModules);
		classvo.setVideos(chapters);
		//classvo.setRemotes(classTypeOfBranchSchoolMapper.findRemotesByClassTypeId(map));
		classvo.setTotalClass(totalClass);
		return classvo;
	}

	@Override
	public void setCddsRecommendFlag(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classTypeOfBranchSchoolMapper.setCddsRecommendFlag(param);
	}

	@Override
	public void setSaleOrNoSale(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classTypeOfBranchSchoolMapper.setSaleOrNoSale(param);
	}

	@Override
	public List<ClassTypeResourceVo> findResBy(ClassTypeResource res) {
		// TODO Auto-generated method stub
		return classTypeOfBranchSchoolMapper.findResBy(res);
	}

	@Override
	public Integer findResCountBy(ClassTypeResource res) {
		// TODO Auto-generated method stub
		return classTypeOfBranchSchoolMapper.findResCountBy(res);
	}

	@Override
	public String copyClassTypeToTargetCompany(Map<String, String> param) throws Exception {
		String result="";
		Integer sourceClassTypeId=Integer.parseInt(param.get("sourceClassTypeId"));
		Integer targetCompanyId=Integer.parseInt(param.get("targetCompanyId"));
		Integer targetSchoolId=Integer.parseInt(param.get("targetSchoolId"));
		Map<Integer, Integer> lessonIdMap=new HashMap<Integer, Integer>();
		//1.查询课程信息
		ClassType sourceCt=classTypeMapper.findById(sourceClassTypeId);
		ClassType targetCt=this.getTargetClassType(sourceCt);
		targetCt.setCreateTime(new Date());
		targetCt.setUpdateTime(new Date());
		targetCt.setSourceCompanyId(sourceCt.getCompanyId());
		targetCt.setSourceClassTypeId(sourceCt.getId());
		targetCt.setIsOutSource(1);
		targetCt.setCompanyId(targetCompanyId);
		targetCt.setCreateSchoolId(targetSchoolId);
		classTypeOfBranchSchoolMapper.insertClassType(targetCt);
		//插入日志
		SchoolShareClassType log=new SchoolShareClassType();
		log.setSourceClassTypeId(sourceCt.getId());
		log.setSourceCompanyId(sourceCt.getCompanyId());
		log.setTargetClassTypeId(targetCt.getId());
		log.setTargetCompanyId(targetCt.getCompanyId());
		log.setIsUsed(1);
		classTypeOfBranchSchoolMapper.insertSchoolShareClassType(log);
		
		//2.commodity商品表
		Commodity sourceC =classTypeOfBranchSchoolMapper.findCommodityByClassTypeId(sourceCt.getId());
		Commodity targetC =this.getTargetCommodity(sourceC);
		targetC.setCompanyId(targetCompanyId);
		targetC.setSchoolId(targetSchoolId);
		targetC.setCerateTime(new Date());
		targetC.setUpdateTime(new Date());
		targetC.setOriginType(0);
		targetC.setCddsStatus(0);
		targetC.setCddsRecommendFlag(0);
		classTypeOfBranchSchoolMapper.insertCommodity(targetC);
		//3.commodity_product_realtion商品课程关系表
		CommodityProductRealtion commodityProductRealtion = new CommodityProductRealtion();
		commodityProductRealtion.setProductId(targetCt.getId());
		commodityProductRealtion.setProductType("1");
		commodityProductRealtion.setComId(targetC.getId());
		commodityProductRealtionServiceImpl.insert(commodityProductRealtion);
		List<ClassModule> moduleList=classTypeOfBranchSchoolMapper.findListByClassId(param);
		if(null!=moduleList&&moduleList.size()>0){
			for(ClassModule sourceM:moduleList){
				//4.class_module课程单元
				ClassModule targetM=this.getTargetClassModule(sourceM);
				targetM.setSchoolId(targetSchoolId);
				targetM.setCompanyId(targetCompanyId);
				targetM.setCreateTime(new Date());
				targetM.setUpdateTime(new Date());
				classModuleServiceImpl.insert(targetM);
				//5.class_type_module_relation模块班型关系表
				ClassTypeModuleRelation ctmr=new ClassTypeModuleRelation();
		    	ctmr.setModuleId(targetM.getId());
		    	ctmr.setClassTypeId(targetC.getId());
		    	classTypeModuleRelationServiceImpl.insert(ctmr);
				//6.class_module_no模块号
		    	ClassModuleNo sourceCmn=classModuleNoService.findClassModuleNoByModuleId(sourceM.getId());
		    	if(null!=sourceCmn){
		    		ClassModuleNo targetCmn=this.getTargetClassModuleNo(sourceCmn);
		    		targetCmn.setModuleId(targetM.getId());
		    		targetCmn.setCreateTime(new Date());
		    		targetCmn.setUpdateTime(new Date());
		    		targetCmn.setCompanyId(targetCompanyId);
		    		classModuleNoService.insert(targetCmn);
		    		//7.class_module_no_onsale在售模块号
		    		ClassModuleNoOnsale entity=new ClassModuleNoOnsale();
		    		entity.setClasstypeId(targetC.getId());
		    		entity.setDefaultFlag(1);
		    		entity.setModuleId(targetM.getId());
		    		entity.setModuleNoId(targetCmn.getId());
		    		classModuleNoOnsaleServiceImpl.insert(entity);
		    		//8.class_module_lesson
		    		List<ClassModuleLesson> sourceCmlList=classModuleLessonService.findClassModuleLessonByModuleNoId1(sourceCmn.getId());
		    		if(null!=sourceCmlList&&sourceCmlList.size()>0){
		    			for(ClassModuleLesson sourceCml:sourceCmlList){
		    				ClassModuleLesson targetCml=this.getTargetClassModuleLesson(sourceCml);
		    				targetCml.setModuleNoId(targetCmn.getId());
		    				targetCml.setCreateTime(new Date());
		    				targetCml.setIsOutSource(1);
		    				classModuleLessonService.insert(targetCml);
		    				lessonIdMap.put(sourceCml.getId(), targetCml.getId());
		    				// 9.课后练习(试卷)
		    				param.put("resourceId", String.valueOf(sourceCml.getId()));
		    				this.copyTiku(param, targetCompanyId, targetCt.getId(), targetCml.getId());
		    			}
		    		}
		    	}
			}
		}
		//10.课程资料
		List<ClassTypeResource>  resourceList=classTypeOfBranchSchoolMapper.queryResourcesByClassTypeId(sourceClassTypeId);
		if(null!=resourceList&&resourceList.size()>0){
			for (ClassTypeResource sourceResource : resourceList) {
				if(null==sourceResource.getFileId()) continue;
				ResourceList targetRl=new ResourceList();
				targetRl.setId(sourceResource.getFileId());
				targetRl.setCompanyId(targetCompanyId);
				targetRl.setSchoolId(targetSchoolId);
				classTypeOfBranchSchoolMapper.insertResourceList(targetRl);
				ClassTypeResource targetR=new ClassTypeResource();
				targetR.setFileId(targetRl.getId());
				targetR.setClassTypeId(targetCt.getId());
				targetR.setCompanyId(targetCompanyId);
				targetR.setId(sourceResource.getId());
				classTypeOfBranchSchoolMapper.insertClassTypeResource(targetR);
				if(null!=lessonIdMap&&lessonIdMap.size()>0){
					for(Integer sourceLessonId:lessonIdMap.keySet()){
						classTypeOfBranchSchoolMapper.updateClassTypeResource(lessonIdMap.get(sourceLessonId),sourceLessonId,  targetCt.getId());
					}
				}
				ClassTypeResourceType targetRt=new ClassTypeResourceType();
				targetRt.setCompanyId(targetCompanyId);
				targetRt.setResourceType(sourceResource.getResourceType());
	            ClassTypeResourceType restype = this.classTypeResourceTypeServiceImpl.findResourceTypeBy(targetRt);
	            if (restype == null) {
	                this.classTypeResourceTypeServiceImpl.insert(targetRt);
	            }
			}
		}
		result=JsonMsg.SUCCESS;
		return result;
	};
	
	/**
	 * 复制课后练习
	 */
	public void copyTiku(Map<String, String> param,Integer targetCompanyId,Integer targetClassTypeId,Integer targetlessonId) throws Exception{
		//练习
		CourseExerciseVo sourceCe=classTypeOfBranchSchoolMapper.findCourseExercise(param);
		if(null!=sourceCe&&null!=sourceCe.getPaperId()) {
			CourseExercise targetCe=this.getTargetCourseExercise(sourceCe);
			targetCe.setClassTypeId(targetClassTypeId);
			targetCe.setResourceId(targetlessonId);
			targetCe.setCompanyId(targetCompanyId);
			//题库分类
			TikuCategory targetTc=new TikuCategory();
			targetTc.setCompanyId(targetCompanyId);
			targetTc.setTikuName("外校题库");
			targetTc.setTikuDesc("外校题库");
			targetTc.setIconUrl("icons/zhibo-0.png");
			targetTc.setIconBackUrl("icons/zhibo-0.png");
			targetTc.setDelFlag(0);
			Integer tcId=classTypeOfBranchSchoolMapper.findTikuByComIdAndTName(targetTc);
			if(null==tcId){
				tikuCategoryService.insert(targetTc);;
			}else{
				targetTc.setId(tcId);
			}
			//题库科目
			TikuSubject targetTs=new TikuSubject();
			if(StringUtils.isNotEmpty(sourceCe.getSubjectName())){
				targetTs.setCategoryId(targetTc.getId());
				targetTs.setSubjectName(sourceCe.getSubjectName());
				targetTs.setDelFlag(0);
				Integer tsId=classTypeOfBranchSchoolMapper.findTikuSubjectByCIdAndTName(targetTs);
				if(null==tsId){
					tikuSubjectService.insert(targetTs);
				}else{
					targetTs.setId(tsId);
				}
			}
			//试卷
			TikuPaper sourceTp=tikuPaperService.findTikuPaperById(sourceCe.getPaperId());
			TikuPaper targetTp=this.getTargetTikuPaper(sourceTp);
			targetTp.setCreateTime(new Date());
			targetTp.setUpdateTime(new Date());
			targetTp.setAuditTime(new Date());
			targetTp.setCompanyId(targetCompanyId);
			targetTp.setTikuCategoryId(targetTc.getId());
			targetTp.setTkuSubjectId(targetTs.getId());
			tikuPaperService.insert(targetTp);
			Map<String, String> tparam=new HashMap<String, String>();
			tparam.put("paperId", String.valueOf(targetTp.getId()));
			tparam.put("sourcePaperId", String.valueOf(sourceCe.getPaperId()));
			//题库试卷各试题类型分数
			classTypeOfBranchSchoolMapper.insertTikuPaperTopicType(tparam);
			//试题
			List<TikuTopic> sourceTopics=classTypeOfBranchSchoolMapper.findTopicsByPaperId(sourceCe.getPaperId());
			if(null!=sourceTopics&&sourceTopics.size()>0){
				for(TikuTopic sourceTop:sourceTopics){
					TikuTopic targetTop=this.getTargetTikuTopic(sourceTop);
					targetTop.setTikuSubjectId(targetTs.getId());
					targetTop.setTikuCategoryId(targetTc.getId());
					targetTop.setCompanyId(targetCompanyId);
					targetTop.setCreateTime(new Date());
					targetTop.setUpdateTime(new Date());
					targetTop.setAuditTime(new Date());
					tikuTopicService.insert(targetTop);
 
					Map<String, String> topparam=new HashMap<String, String>();
					topparam.put("topicId", String.valueOf(targetTop.getId()));
					topparam.put("sourceTopicId", String.valueOf(sourceTop.getId()));
					topparam.put("parperId", String.valueOf(targetTp.getId()));
					topparam.put("sourceParperId", String.valueOf(sourceTp.getId()));
					//试卷和试题关系表
					classTypeOfBranchSchoolMapper.insertTikuPaperTopic(topparam);
					//试题选项表
					classTypeOfBranchSchoolMapper.insertTikuTopicOption(topparam);
				}
			}
			targetCe.setTikuCategoryId(targetTc.getId());
			targetCe.setTikuSubjectId(targetTs.getId());
			targetCe.setPaperId(targetTp.getId());
			courseExerciseService.insert(targetCe);
		}
	}
	
 	public ClassType getTargetClassType(ClassType ct){
		ClassType targetCt=new ClassType();
		if(null==ct) return targetCt;
		targetCt.setCreator(ct.getCreator());
		targetCt.setUpdator(ct.getUpdator());
		targetCt.setName(ct.getName());
		targetCt.setTypeCode(ct.getTypeCode());
		targetCt.setOriginalPrice(ct.getOriginalPrice());
		targetCt.setRealPrice(ct.getRealPrice());
		targetCt.setDescription(ct.getDescription());
		targetCt.setPublishStatus("CLASS_UNPUBLISHED");
		targetCt.setPublishTime(ct.getPublishTime());
		targetCt.setIsSale(0);
		targetCt.setCover(ct.getCover());
		targetCt.setSubTitle(ct.getSubTitle());
		targetCt.setDetailDesc(ct.getDetailDesc());
		targetCt.setDelFlag(ct.getDelFlag());
		targetCt.setBaseNum(ct.getBaseNum());
		targetCt.setLableType(ct.getLableType());
		targetCt.setTeacherId(ct.getTeacherId());
		targetCt.setFaceFlag(ct.getFaceFlag());
		targetCt.setLiveFlag(ct.getLiveFlag());
		targetCt.setVideoFlag(ct.getVideoFlag());
		targetCt.setRemoteFlag(ct.getRemoteFlag());
		targetCt.setRecommendFlag(ct.getRecommendFlag());
		targetCt.setValidityDay(ct.getValidityDay());
		targetCt.setValidityDate(ct.getValidityDate());
		targetCt.setIntegralFlag(ct.getIntegralFlag());
		targetCt.setMemberFlag(ct.getMemberFlag());
		targetCt.setBuyNumMax(ct.getBuyNumMax());
		targetCt.setSubjectClassOrder(null);
		targetCt.setIconLable(ct.getIconLable());
//		targetCt.setItemOneCode(ct.getItemOneCode());
//		targetCt.setItemSecondCode(ct.getItemSecondCode());
//		targetCt.setItemThirdCode(ct.getItemThirdCode());
		targetCt.setItemFourthCode(ct.getItemFourthCode());
		targetCt.setIsMicroClass(ct.getIsMicroClass());
		targetCt.setPaperDescription(ct.getPaperDescription());
		targetCt.setIsPublic(ct.getIsPublic());
		targetCt.setPublicPrice(ct.getPublicPrice());
		return targetCt;
	}
	
	public Commodity getTargetCommodity(Commodity c){
		Commodity targetC=new Commodity();
		if(null==c) return targetC;
		targetC.setName(c.getName());
		targetC.setCoverUrl(c.getCoverUrl());
		targetC.setOverview(c.getOverview());
		targetC.setOriginalPrice(c.getOriginalPrice());
		targetC.setRealPrice(c.getRealPrice());
		targetC.setType(c.getType());
		targetC.setCreator(c.getCreator());
		targetC.setUpdator(c.getUpdator());
		targetC.setStatus("0");
		targetC.setLableType(c.getLableType());
		targetC.setBaseNum(c.getBaseNum());
		targetC.setFaceFlag(c.getFaceFlag());
		targetC.setLiveFlag(c.getLiveFlag());
		targetC.setVideoFlag(c.getVideoFlag());
		targetC.setRemoteFlag(c.getRemoteFlag());
		targetC.setRecommendFlag(c.getRecommendFlag());
		targetC.setBuyNum(c.getBuyNum());
		targetC.setIntegralFlag(c.getIntegralFlag());
		targetC.setMemberFlag(c.getMemberFlag());
//		targetC.setItemOneCode(c.getItemOneCode());
//		targetC.setItemSecondCode(c.getItemSecondCode());
//		targetC.setItemThirdCode(c.getItemThirdCode());
		targetC.setItemFourthCode(c.getItemFourthCode());
		targetC.setIsMicroClass(c.getIsMicroClass());

		return targetC;
	}
	
	public ClassModule getTargetClassModule(ClassModule m){
		ClassModule targetM=new ClassModule();
		targetM.setName(m.getName());
		targetM.setTotalClassHour(m.getTotalClassHour());
		targetM.setTeachMethod(m.getTeachMethod());
		targetM.setModuleType(m.getModuleType());
		targetM.setModuleDesc(m.getModuleDesc());
		targetM.setPublishStatus(m.getPublishStatus());
		targetM.setPublishTime(m.getPublishTime());
		targetM.setDelFlag(m.getDelFlag());
		targetM.setCreator(m.getCreator());
		targetM.setUpdator(m.getUpdator());
		targetM.setSort(m.getSort());
		targetM.setChapterFlag(m.getChapterFlag());
		targetM.setItemOneCode(m.getItemOneCode());
		targetM.setItemSecondCode(m.getItemSecondCode());
		targetM.setItemThirdCode(m.getItemThirdCode());
		targetM.setItemFourthCode(m.getItemFourthCode());
		return targetM;
	}
	
	public ClassModuleNo getTargetClassModuleNo(ClassModuleNo n){
		ClassModuleNo targetCMN=new ClassModuleNo();
		targetCMN.setName(n.getName());
		targetCMN.setExamTerm(n.getExamTerm());
		targetCMN.setStartDate(n.getStartDate());
		targetCMN.setlessonType(n.getlessonType());
		targetCMN.setlessonDay(n.getlessonDay());
		targetCMN.setlessonScope(n.getlessonScope());
		targetCMN.setStartTime(n.getStartTime());
		targetCMN.setEndTime(n.getEndTime());
		targetCMN.setLessonHour(n.getLessonHour());
		targetCMN.setTeachers(n.getTeachers());
		targetCMN.setMasters(n.getMasters());
		targetCMN.setAssistants(n.getAssistants());
		targetCMN.setCampusId(n.getCampusId());
		targetCMN.setClassroom(n.getClassroom());
		targetCMN.setLiveRoom(n.getLiveRoom());
		targetCMN.setDevice(n.getDevice());
		targetCMN.setPlanEnrollStudents(n.getPlanEnrollStudents());
		targetCMN.setEnrollYetStudents(n.getEnrollYetStudents());
		targetCMN.setPublishStatus(n.getPublishStatus());
		targetCMN.setTotalHours(n.getTotalHours());
		targetCMN.setClassTeachType(n.getClassTeachType());
		targetCMN.setDescription(n.getDescription());
		targetCMN.setCreator(n.getCreator());
		targetCMN.setUpdator(n.getUpdator());
		targetCMN.setDelFlag(n.getDelFlag());
		targetCMN.setLessonTotal(n.getLessonTotal());
		targetCMN.setLessonPlan(n.getLessonPlan());

		return targetCMN;
	}
	
	public ClassModuleLesson getTargetClassModuleLesson(ClassModuleLesson l){
		ClassModuleLesson targetCml=new ClassModuleLesson();
		targetCml.setLessonName(l.getLessonName());
		targetCml.setLessonDate(l.getLessonDate());
		targetCml.setScope(l.getScope());
		targetCml.setWeekType(l.getWeekType());
		targetCml.setLessonTimeStart(l.getLessonTimeStart());
		targetCml.setLessonTimeEnd(l.getLessonTimeEnd());
		targetCml.setLessonHour(l.getLessonHour());
		targetCml.setTeachers(l.getTeachers());
		targetCml.setAssistants(l.getAssistants());
		targetCml.setMasters(l.getMasters());
		targetCml.setTeachersName(l.getTeachersName());
		targetCml.setAssistantsName(l.getAssistantsName());
		targetCml.setMastersName(l.getMastersName());
		targetCml.setClassroomId(l.getClassroomId());
		targetCml.setClassroom(l.getClassroom());
		targetCml.setLiveRoom(l.getLiveRoom());
		targetCml.setRemark(l.getRemark());
		targetCml.setLiveCompanyType(l.getLiveCompanyType());
		targetCml.setLiveroomIdGh(l.getLiveroomIdGh());
		targetCml.setTeacherUrlGh(l.getTeacherUrlGh());
		targetCml.setStudentUrlGh(l.getStudentUrlGh());
		targetCml.setAssistantUrlGh(l.getAssistantUrlGh());
		targetCml.setReplayUrlGh(l.getReplayUrlGh());
		targetCml.setDelFlag(l.getDelFlag());
		targetCml.setSort(l.getSort());
		targetCml.setChapterFlag(l.getChapterFlag());
		targetCml.setSupportMobile(l.getSupportMobile());
		targetCml.setLiveClassType(l.getLiveClassType());;
		targetCml.setCreator(l.getCreator());
		targetCml.setBarrage(l.getBarrage());
		targetCml.setModetype(l.getModetype());
		
		return targetCml;
	}
	
	public CourseExercise getTargetCourseExercise(CourseExerciseVo ce){
		CourseExercise targetCe=new CourseExercise();
		targetCe.setResourceType(ce.getResourceType());
		targetCe.setExerciseType(ce.getExerciseType());
		targetCe.setTopicNum(ce.getTopicNum());
		targetCe.setTikuResourceType(ce.getTikuResourceType());
		return targetCe;		
	}

	public TikuPaper getTargetTikuPaper(TikuPaper tp){
		TikuPaper targetTp=new TikuPaper();
		targetTp.setPaperName(tp.getPaperName());
		targetTp.setPaperType(tp.getPaperType());
		targetTp.setExamTime(tp.getExamTime());
		targetTp.setTotalScore(tp.getTotalScore());
		targetTp.setRegion(tp.getRegion());
		targetTp.setContainTopicType(tp.getContainTopicType());
		targetTp.setPaperStatus(tp.getPaperStatus());
		targetTp.setTotalTopicNum(tp.getTotalTopicNum());
		targetTp.setCreator(tp.getCreator());
		targetTp.setUpdator(tp.getUpdator());
		targetTp.setAuditor(tp.getAuditor());
		targetTp.setTeacherId(tp.getTeacherId());		
		return targetTp;
	}

	public TikuTopic getTargetTikuTopic(TikuTopic t){
		TikuTopic targetTop=new TikuTopic();
		targetTop.setTopicName(t.getTopicName());
		targetTop.setTopicNameBlank(t.getTopicNameBlank());
		targetTop.setTopicType(t.getTopicType());
		targetTop.setScore(t.getScore());
		targetTop.setAnswer(t.getAnswer());
		targetTop.setDifficulty(t.getDifficulty());
		targetTop.setAnalyseWord(t.getAnalyseWord());
		targetTop.setAnalyseVideoUrl(t.getAnalyseVideoUrl());
		targetTop.setAnalyseAudioUrl(t.getAnalyseAudioUrl());
		targetTop.setStatus(t.getStatus());
		targetTop.setParentId(t.getParentId());
		targetTop.setCreator(t.getCreator());
		targetTop.setUpdator(t.getUpdator());
		targetTop.setAuditor(t.getAuditor());
		targetTop.setPaperFlag(t.getPaperFlag());
		targetTop.setChildFlag(t.getChildFlag());

		return targetTop;
	}

	@Override
	public Integer findSchoolShareClassType(Map<String, String> param) {
		// TODO Auto-generated method stub
		return classTypeOfBranchSchoolMapper.findSchoolShareClassType(param);
	}

	@Override
	public List<ClassTypeVo> queryClassTypeOfOtherSchool(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return classTypeOfBranchSchoolMapper.queryClassTypeOfOtherSchool(param);
	}

	@Override
	public int queryCountClassTypeOfOtherSchool(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return classTypeOfBranchSchoolMapper.queryCountClassTypeOfOtherSchool(param);
	}

	@Override
	public void battchSaleOrStopSale(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classTypeOfBranchSchoolMapper.battchSaleOrNoOfCommodity(param);
		classTypeOfBranchSchoolMapper.battchSaleOrNoOfClassType(param);
	}

	@Override
	public String validateOnSale(Map<String, Object> param) {
		String result=new String();
		try {
			List<ClassType> ctlist=classTypeOfBranchSchoolMapper.validateOnSaleOfModuleNoOnSale(param);
			if(null!=ctlist&&ctlist.size()>0){
				result="课程("+ctlist.get(0).getName()+")没有在售班号，暂时不能上架";
			}else{
				List<ClassType> ctlist1 =classTypeOfBranchSchoolMapper.validateOnSaleOfSubject(param);
				if(null!=ctlist1&&ctlist1.size()>0){
					result="课程("+ctlist1.get(0).getName()+")的学科为空，暂时不能上架";
				}
			}
		} catch (Exception e) {
			result="课程上架校验失败";
			log.error(e.getMessage());
		}
		return result;
	}
}
