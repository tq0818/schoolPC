package com.yuxin.wx.api.classes;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.vo.classes.ClassLessonVO;
import com.yuxin.wx.vo.classes.ClassPackageConditionVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.company.MemberLevelAndClassTypeVo;
import com.yuxin.wx.vo.redis.ClassLectureVO;
import com.yuxin.wx.vo.student.StudentListVo;

/**
 * Service Interface:ClassType
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IClassTypeService  {
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
	void insert(ClassType classType);
	
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
	void batchInsert(List<ClassType> classType);
	
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
	void update(ClassType classType);
	
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
	void deleteClassTypeById(Integer id);
	
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
	void deleteClassTypeByIds(Integer[] ids);
	
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
	ClassType findClassTypeById(Integer id);
	
	/**
	 * 
	* @Title: findClassTypeList
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by Chopin
	 */
	List<ClassType> findClassTypeList(ClassType classType);
	
	/**
	 * 
	* @Title: findClassTypeList
	* @Description: 根据id查询远程班型
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by Chopin
	 */
	List<ClassType> findClassTypeList2(ClassType classType);
	
	/**
	 * 
	* @Title: queryClassTypeByKeysForPage 
	* @Description: 分页查询
	* @return
	* @return PageFinder<ClassTypeVo>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-1-1
	* @user by zhangbo
	 */
	public PageFinder<ClassTypeVo> queryClassTypeByKeysForPage(ClassType search);
	
	/**
	 * 
	* @Title: findClassTypeDetail 
	* @Description: 获取班型详细信息
	* @return
	* @return List<ClassType>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	ClassTypeVo findClassTypeDetail(Map<String,String> map);
	
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 添加班型相关联的模块信息或者远程教育信息
	 * @author Keyn
	 * @date 2015-1-5 下午3:46:11
	 * @version 1.0
	 * @param classType
	 * @param ralationsIdVal
	 */
	public void updateClassTypeRalation(ClassType classType,String ralationsIdVal);
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 查询所报班型
	 * @author 权飞虎
	 * @date 2015年4月25日 下午6:22:35
	 * @modifier
	 * @modify-date 2015年4月25日 下午6:22:35
	 * @version 1.0
	 * @param id	学生id
	 * @param companyId		公司id	
	 * @param payMasterId 	订单id
	 * @return
	 */
	ClassType findClassTypeByStuId(Integer id, Integer companyId, Integer payMasterId);
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Descript 权飞虎
	 * @date 2015年4月29日 下午4:03:32
	 * @modifier
	 * @modify-date 2015年4月29日 下午4:03:32
	 * @version 1.0
	 * @param id
	 * @param object
	 * @return
	 */
	List<ClassType> findByItem(Integer conpanyId,Integer schoolId,Integer itemOneId,Integer itemTwoId);
	
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 查询班型详情
	 * @author zhang.zx
	 * @date 2015年5月5日 上午10:43:43
	 * @modifier
	 * @modify-date 2015年5月5日 上午10:43:43
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<ClassTypeVo> findClassTypesByPage(ClassType search);
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description:根据远程信息查询班型
	 * @author 权飞虎
	 * @date 2015年5月13日 下午6:01:58
	 * @modifier
	 * @modify-date 2015年5月13日 下午6:01:58
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<ClassType> findByRemote(Integer id);
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 根君班号查询班型
	 * @author 权飞虎
	 * @date 2015年5月13日 下午6:13:53
	 * @modifier
	 * @modify-date 2015年5月13日 下午6:13:53
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<ClassType> findByModule(Integer id);
	
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 根据班型名称查询班
	 * @author zhang.zx
	 * @date 2015年5月5日 上午10:43:43
	 * @modifier
	 * @modify-date 2015年5月5日 上午10:43:43
	 * @version 1.0
	 * @param search
	 * @return
	 */
	ClassType findClassTypeByName(String name);

	/**
	 * 
	 * Class Name: ICommodityService.java
	 * @Description: 查询是否 还有出售中的班型 根据学校id 和 公司id
	 * @author 周文斌
	 * @date 2015-5-13 下午6:36:07
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<String> findNameById(Map<String,Object> param);
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 查询该公司所有未删除班型
	 * @author 权飞虎
	 * @date 2015年5月15日 上午10:38:58
	 * @modifier
	 * @modify-date 2015年5月15日 上午10:38:58
	 * @version 1.0
	 * @param classType
	 * @return
	 */
	List<ClassType> findClassTypeList3(Integer companyId);
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 根据一级项目查询版型
	 * @author admin
	 * @date 2015年5月15日 下午2:45:21
	 * @modifier
	 * @modify-date 2015年5月15日 下午2:45:21
	 * @version 1.0
	 * @param itemOneId
	 * @param companyId
	 * @return
	 */
	List<ClassType> findByItemOne(Integer itemOneId, Integer companyId);
	
	/**
	 * 
	 * Class Name: ICommodityService.java
	 * @Description:改变班型收藏状态
	 * @author zhang.zx
	 * @date 2015-5-13 下午6:36:07
	 * @version 1.0
	 * @param param
	 * @return
	 */
	void changClassTypeCollectStatus(ClassType classType);
	
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 查询班型是否存在
	 * @author zhang.zx
	 * @date 2015年5月26日 下午4:12:18
	 * @modifier
	 * @modify-date 2015年5月26日 下午4:12:18
	 * @version 1.0
	 * @param classType
	 * @return
	 */
	List<ClassType> findClassTypeExits(ClassType classType);
	
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 条件查询班型信息
	 * @author zhang.zx
	 * @date 2015年6月3日 下午7:01:38
	 * @modifier
	 * @modify-date 2015年6月3日 下午7:01:38
	 * @version 1.0
	 * @param classType
	 * @return
	 */
	List<ClassType> findClassTypeByCondition(ClassType classType);
	
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 查询班型id name
	 * @author 周文斌
	 * @date 2015-7-16 下午2:38:19
	 * @version 1.0
	 * @param conpanyId
	 * @param schoolId
	 * @param itemOneId
	 * @param itemTwoId
	 * @return
	 */
	List<ClassType> findClassByItem(Integer conpanyId,Integer schoolId,Integer itemOneId,Integer itemTwoId);

    List<ClassType> findClassByItemRelation(Integer conpanyId, Integer schoolId,
                                            String itemOneCode, String itemSecondCode, String itemThridCode, Integer liveFlag, Integer videoFlag);

    /**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 根据公司id查询班型信息
	 * @author zhang.zx
	 * @date 2015年8月11日 下午4:21:18
	 * @modifier
	 * @modify-date 2015年8月11日 下午4:21:18
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<ClassType> findClassTypeBycompanyId(Integer companyId);
	
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 购买课程,根据商品id查询所购买的课程
	 * @author 权飞虎
	 * @date 2015年4月13日 下午4:36:50
	 * @modifier
	 * @modify-date 2015年4月13日 下午4:36:50
	 * @version 1.0
	 * @param id
	 * @return
	 */
	ClassType findClassTypeByCommodity(Integer id);
	
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 查询学科下 课程列表
	 * @author 周文斌
	 * @date 2015-12-22 下午3:22:41
	 * @version 1.0
	 * @param commodity
	 * @return
	 */
	List<Integer> findClassTypeByClass(ClassType ct);
	
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 课程是否可见
	 * @author 周文斌
	 * @date 2015-12-22 下午3:33:34
	 * @version 1.0
	 * @param param
	 */
	void updateDeflag(Map<String, Object> param);
	
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 查询课程包下的课程
	 * @author zhang.zx
	 * @date 2016年3月23日 下午4:27:09
	 * @modifier
	 * @modify-date 2016年3月23日 下午4:27:09
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<ClassTypeVo> queryClassTypesByClassPackage(ClassPackageConditionVo search);
	
	/**
	 * 
	 * Class Name: IClassTypeService.java
	 * @Description: 查询课程包下是否存在某个课
	 * @author zhang.zx
	 * @date 2016年3月23日 下午5:59:55
	 * @modifier
	 * @modify-date 2016年3月23日 下午5:59:55
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<ClassTypeVo> queryIsExistByClassPackage(ClassPackageConditionVo search);
	
	/**
	 * 
	 * @Description:查询允许使用会员的课程
	 * @author: dongshuai
	 * @date: 2016年5月26日
	 * @param param
	 * @return
	 *
	 */
	PageFinder<ClassTypeVo> queryClassTypeForMember(MemberLevelAndClassTypeVo mlactVo);
	
	List<ClassType> queryCourseByOneAndTwoItem(ClassType search);

	List<ClassType> findClassTypeList4(Integer companyId);

	ClassTypeVo findClassTypeVoByClassTypeId(Integer classTypeId);

	List<ClassType> findAllclassType(ClassType cp);
	
	/**
	 * 修改学科课程排序
	 * @param cp
	 * @return
	 */
	int updateSubjectClassOrder(ClassType cp);
	
	/**
	 * 获取当前学科课程排序总数
	 * @return
	 */
	int countSubjectClassOrder(String itemOenCode);
	
	/**
	 * 查询分校公开的直播课程
	 */
	List<ClassTypeVo> queryLiveClassOfBranchSchool(Map<String, Object> param);

	/**
	 * 查询分校公开的直播课程
	 */
	int queryCountLiveClassOfBranchSchool(Map<String, Object> param);
	
	/**
	 * 查询分校课程(分校课程查询)
	 */
	List<ClassTypeVo> queryClassOfBranchSchool(Map<String, Object> param);

	/**
	 * 查询分校课程(分校课程查询)
	 */
	int queryCountClassOfBranchSchool(Map<String, Object> param);
	
	
	/**
	 * 查询其他分校的直播课程
	 */
	List<ClassTypeVo> queryLiveClassOfOtherSchool(Map<String, Object> param);

	/**
	 * 查询其他分校的直播课程
	 */
	int queryCountLiveClassOfOtherSchool(Map<String, Object> param);

	/**
	 * 获取录播课程列表
	 * @param search
	 * @return
	 */
	List<ClassLectureVO> getClassTypeListVideo(ClassType search);

	List<ClassType> getClassTypeListLive(ClassType search);

	List<ClassType> getClassTimeList(ClassType search);
	
	List<ClassLessonVO> getClassLessonLogList(List<Integer> stuIdsList,List<Integer> lessonIdList);

	JSONObject getListDatas(StudentListVo search,ClassType classType);
	
}