package com.yuxin.wx.system.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysSchoolItemRelation;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:SysConfigItem
 * @author wang.zx
 * @date 2014-12-5
 */
public interface SysConfigItemMapper extends BaseMapper<SysConfigItem> {
	List<SysConfigItem> findByItemTwo(HashMap map);
	List<SysConfigItem> findByItemOne(@Param("companyId") String companyId,@Param("schoolId") String schoolId);
	
	List<SysConfigItem> selectItem(SysConfigItem search);
	List<SysConfigItem> findByParentCode(SysConfigItem item);
	/**
	 * 
	 * Class Name: SysConfigItemMapper.java
	 * @Description: 学校 id 查询 项目 总数
	 * @author 周文斌
	 * @date 2015-4-30 下午1:50:24
	 * @version 1.0
	 * @param map
	 * @return
	 */
	Integer findProjectBySchoolId(Integer schoolId);
	
	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据公司id查询 一级项目
	 * @author 周文斌
	 * @date 2015-5-4 下午1:02:47
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<SysConfigItem> findItemByCompanyId(@Param(value = "itemType") Integer itemType, @Param(value = "companyId") Integer companyId);

	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据一级项目id 查询二级项目
	 * @author 周文斌
	 * @date 2015-5-4 下午1:03:42
	 * @version 1.0
	 * @param oneItemId
	 * @return
	 */
	List<SysConfigItem> findTwoByOneId(Integer oneItemId);

	/**
	 * 
	 * Class Name: ISysConfigSchoolService.java
	 * @Description: 插入关联表
	 * @author 周文斌
	 * @date 2015-5-7 下午4:30:54
	 * @version 1.0
	 * @param param
	 */
	void insertRelation(Map<String,Object> param);
	

	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据学校id查询 唯一
	 * @author 周文斌
	 * @date 2015-5-7 下午4:48:13
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findUnquieItem(Map<String,Object> param);

	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据公司id查询 唯一
	 * @author 周文斌
	 * @date 2015-5-7 下午4:48:13
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findUnquieItemByUpdate(Map<String,Object> param);
	
	List<SysConfigItem> findOneItemByCompanyId(Integer companyId);

	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据parentid 修改 状态
	 * @author 周文斌
	 * @date 2015-5-10 下午2:21:24
	 * @version 1.0
	 * @param sci
	 */
	void updateTwoByOne(SysConfigItem sci);

	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 检查 关系是否存在
	 * @author 周文斌
	 * @date 2015-5-12 下午7:32:27
	 * @version 1.0
	 * @param params
	 * @return
	 */
	SysSchoolItemRelation findExist(Map<String,Object> params);

	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 修改关联关系状态
	 * @author 周文斌
	 * @date 2015-5-12 下午7:39:33
	 * @version 1.0
	 * @param params
	 */
	void updateRelation(Map<String,Object> params);

	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据公司id 和 学校id 查询项目
	 * @author 周文斌
	 * @date 2015-5-14 下午4:49:28
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<SysConfigItem> findItemBySchoolCompanyId(Map<String ,Object> param);
	int selectCount(Integer companyId);

	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 查询状态
	 * @author 周文斌
	 * @date 2015-5-23 下午2:25:18
	 * @version 1.0
	 * @param item
	 * @return
	 */
	List<SysConfigItem> findStatus(SysConfigItem item);

	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 根据itemid 查询 当前学校没有使用的项目
	 * @author 周文斌
	 * @date 2015-5-23 下午6:19:14
	 * @version 1.0
	 * @param list
	 * @return
	 */
	List<SysConfigItem> findNotInByItemId(Map<String,Object> param);
	
	List<SysConfigItem> selectSecondItem(SysConfigItem item);

	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 查询整个项目是否存在名字
	 * @author 周文斌
	 * @date 2015-8-14 下午12:50:18
	 * @version 1.0
	 * @param item
	 * @return
	 */
	SysConfigItem findDelNullByName(SysConfigItem item);

	/**
	 * 
	 * Class Name: ISysConfigItemService.java
	 * @Description: 查询是否还有其他分校使用
	 * @author 周文斌
	 * @date 2015-8-14 下午3:20:22
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<SysSchoolItemRelation> findUseByItemId(Map<String, Object> param);
	List<SysConfigItem> findItemByIds(List<Integer> list);

}