package com.yuxin.wx.classes.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.vo.classes.ClassPackageConditionVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;
import com.yuxin.wx.vo.company.MemberLevelAndClassTypeVo;
import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.common.PageFinder;
/**
 * Service Interface:ClassType
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ClassTypeMapper extends BaseMapper<ClassType> {
	List<ClassType> findListByItem(ClassType classType);
	List<ClassType> findListByItem2(ClassType classType);
	ClassTypeVo findDetailById(Map map);
	//分页查询
	List<ClassTypeVo> queryClassTypeByKeysForPage(ClassType classType);
	ClassType findClassTypeByStuId(Map<String, Object> map);
	List<ClassType> findByItem(Map<String, Integer> map);
	
	List<ClassTypeVo> queryClassTypesByPage(ClassType search);
	int queryCounts(ClassType search);
	List<ClassType> findByRemote(Integer id);
	List<ClassType> findByModule(Integer id);
	ClassType queryClassTypeByName(String name);
	List<ClassType> queryClassBycondition(ClassType classType);

	/**
	 * 
	 * Class Name: ICommodityService.java
	 * @Description: 查询是否 还有出售中的商品 根据学校id 和 公司id
	 * @author 周文斌
	 * @date 2015-5-13 下午6:36:07
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<String> findNameById(Map<String,Object> param);
	List<ClassType> findClassTypeList3(Integer companyId);
	List<ClassType> findByItemOne(Map<String, Integer> map);
	
	void changClassTypeCollectStatus(ClassType classType);
	
	List<ClassType> queryClassTypeExits(ClassType classType);

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
	List<ClassType> findClassByItem(Map<String, Integer> map);
	
	//根据公司id查询班型数据
	List<ClassType> findClassTypeBycompanyId(Integer companyId);
	
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
	
	List<ClassTypeVo> queryClassTypesByClassPackage(ClassPackageConditionVo search);
	List<ClassTypeVo> queryIsExistByClassPackage(ClassPackageConditionVo search);
	
	/**
	 * 
	 * @Description: 查询允许使用会员的课程
	 * @author: dongshuai
	 * @date: 2016年5月26日
	 * @param param
	 * @return
	 *
	 */
	List<ClassTypeVo> queryClassTypeForMember(MemberLevelAndClassTypeVo mlactVo);
	
	int queryClassTypeForMemberCount(MemberLevelAndClassTypeVo mlactVo);
	List<ClassType> queryClassTypeByOneAndTwoItem(ClassType search);
	List<ClassType> findClassTypeList4(Integer companyId);
	ClassTypeVo findClassTypeVoByClassTypeId(Integer classTypeId);
	List<ClassType> findClassTypeVoByClassTypeId(ClassType cp);
	List<ClassType> findAllclassType(ClassType cp);
}