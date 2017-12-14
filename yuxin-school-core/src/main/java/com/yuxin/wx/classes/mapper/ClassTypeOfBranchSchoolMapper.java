package com.yuxin.wx.classes.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeResource;
import com.yuxin.wx.model.classes.ClassTypeResourceType;
import com.yuxin.wx.model.classes.SchoolShareClassType;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.course.CourseExercise;
import com.yuxin.wx.model.course.CourseRemote;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.model.resource.ResourceList;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.model.tiku.TikuCategory;
import com.yuxin.wx.model.tiku.TikuSubject;
import com.yuxin.wx.model.tiku.TikuTopic;
import com.yuxin.wx.vo.classes.ClassTypeResourceVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.course.CourseExerciseVo;
import com.yuxin.wx.common.BaseMapper;

/**
 * 分校课程
 */
public interface ClassTypeOfBranchSchoolMapper extends BaseMapper<ClassType> {
	
	/**
	 * 查询分校课程(分校课程)
	 */
	List<ClassTypeVo> queryClassTypeOfBranchSchool(Map<String, Object> param);

	/**
	 * 查询分校课程(分校课程)
	 */
	int queryCountClassTypeOfOtherSchool(Map<String, Object> param);
	

	/**
	 * 查询分校课程(分校课程)
	 */
	List<ClassTypeVo> queryClassTypeOfOtherSchool(Map<String, Object> param);

	/**
	 * 查询分校课程(分校课程)
	 */
	int queryCountClassTypeOfBranchSchool(Map<String, Object> param);
	
    /**
     * 查询分类/学段/学科
     */
    List<SysConfigItemRelation> findItemFront(Map<String, Object> param);
    
    /**
     * 批量上架/下架
     * @param param
     */
    void battchUpOrDown(Map<String, Object> param);
    /**
     * 查询课型
     */
    ClassTypeVo findDetailById(Map<String, String> param);

    ClassTypeVo findDetailById1(Map<String, String> param);
    
    /**
     * 查询课程单元
     */
	List<ClassModule> findListByClassId(Map<String,String> map);
	
	List<CourseVideoChapter> findByClassId(Map<String, String> map);
	
	List<CourseRemote> findRemotesByClassTypeId(Map<String, String> map);

    void setCddsRecommendFlag(Map<String, Object> param);
    
    void setSaleOrNoSale(Map<String, Object> param);
	
    List<ClassTypeResourceVo> findResBy(ClassTypeResource res);
	
	Integer findResCountBy(ClassTypeResource res);
	
	Integer findSchoolShareClassType(Map<String, String> param);
	
	void insertClassType(ClassType classType);
	
	Commodity findCommodityByClassTypeId(Integer classTypeId);

	void insertCommodity(Commodity commodity);
	
	List<ClassTypeResource> queryResourcesByClassTypeId(Integer classTypeId);
	
	void insertResourceList(ResourceList rl);
	
	void insertClassTypeResource(ClassTypeResource res);
	
	CourseExerciseVo findCourseExercise(Map<String, String> param);

	Integer findTikuByComIdAndTName(TikuCategory category);
	
	Integer findTikuSubjectByCIdAndTName(TikuSubject subject);

	void insertTikuPaperTopicType(Map<String, String> param);
	
	List<TikuTopic> findTopicsByPaperId(Integer paperId);
	
	void insertTikuTopicOption(Map<String, String> param);

	void insertTikuPaperTopic(Map<String, String> param);
	
	void insertSchoolShareClassType(SchoolShareClassType log);
	
	void updateClassTypeResource(Integer targetLessonId,Integer sourceLessonId,Integer classTypeId);
	
	void battchSaleOrNoOfCommodity(Map<String, Object> param);
	void battchSaleOrNoOfClassType(Map<String, Object> param);
	
}
