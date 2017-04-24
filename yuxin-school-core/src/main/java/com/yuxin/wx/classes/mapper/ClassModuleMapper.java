package com.yuxin.wx.classes.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.vo.classes.ClassModuleVo;
import com.yuxin.wx.vo.classes.ClassVideoModuleVo;
import com.yuxin.wx.vo.classes.ClassmoduleNoOnsaleVo;
import com.yuxin.wx.vo.classes.ModuleLessonVo;
/**
 * Service Interface:ClassModule
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ClassModuleMapper extends BaseMapper<ClassModule> {
	//根据条件查询模块集合
	public List<ClassModuleVo> queryClassModuleByKeys(ClassModule classModule);
	//根据条件查询模块列表
	public List<ClassModule> findModulesByModule(ClassModule classModule);
	//根据班型查询模块集合
	public List<ClassModule> findListByClassId(Map<String,String> map);
	//根据条件查询模块集合总数
	public int queryClassModuleByKeysCount(ClassModule classModule);
	//根据id查询模块信息
	public ClassModule queryOneById(Integer moduleId);
	//根据id查询模块信息
	public ClassModuleVo queryOneVoById(Integer moduleId);
	//根据二级项目查询对应的模块
	public List<ClassModule> findModulesBySecondItemId(Integer secondItemId);
	//根据班级模块关系获取当前班级下面所有模块
	public List<ClassModule> findModulesByClassTypeId(Integer secondItemId);
	
	Integer checkUpdate(Integer moduleId); 
	Integer checkStop(Integer moduleId);
	public List<ClassModule> findByClassTypeId(Integer id);
	
	public List<ClassModule> findByPayMasterId(StudentPaySlave paySlave); 

	List<ClassModuleVo> queryClassModules(ClassModule classModule);
	List<ClassModuleVo> queryAllModules(String classTypeId);

	

	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 通过schoolid 关联查询
	 * @author 周文斌
	 * @date 2015-5-8 下午8:31:53
	 * @version 1.0
	 * @param schoolId
	 * @return
	 */
	List<ModuleLessonVo> findBySchoolId(ModuleLessonVo mlv);
	

	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 查询总条数
	 * @author 周文斌
	 * @date 2015-5-11 下午6:56:53
	 * @version 1.0
	 * @param mlv
	 * @return
	 */
	Integer findCountBySchoolId(ModuleLessonVo mlv);

	
	List<ClassModule> findModuleByIds(List<Integer> ids);

	/**
	 * 
	 * Class Name: ClassModuleMapper.java
	 * @Description: 根据条件查询模块
	 * @author 权飞虎
	 * @date 2015年5月11日 下午6:52:50
	 * @modifier
	 * @modify-date 2015年5月11日 下午6:52:50
	 * @version 1.0
	 * @param module
	 * @return
	 */
	public List<ClassModuleVo> queryList(ClassModule module);
	/**
	 * 
	 * Class Name: ClassModuleMapper.java
	 * @Description: 根据条件查询数量
	 * @author 权飞虎
	 * @date 2015年5月11日 下午7:21:50
	 * @modifier
	 * @modify-date 2015年5月11日 下午7:21:50
	 * @version 1.0
	 * @param module
	 * @return
	 */
	public Integer queryCount(ClassModule module);
	public void changeStatus(ClassModule module);
	public List<ClassModule> findByName(ClassModule module);
	public ClassModuleVo findClassModuleVoById(Integer id);
	public List<ClassType> findByModule(Integer id);
	public List<ClassModule> findClassModules(ClassModuleNo moduleNo);
	public Integer isUse(Map<String, Integer> map);
	
	ClassModule queryModuleByName(ClassModule c);
	void insertClassModuleOnsale(ClassmoduleNoOnsaleVo search);
	List<ClassmoduleNoOnsaleVo> queryModuleNoByModuleId(Integer moduleId);
	List<ClassmoduleNoOnsaleVo> queryModuleNoByClassTypeId(Integer classTypeId);
	void deleteClassModuleOnsaleById(ClassmoduleNoOnsaleVo c);
	List<ClassVideoModuleVo> queryVideoModule(ClassVideoModuleVo vc);
	ClassModule queryModuleByClasstypeId(Integer classTypeId);
	
	List<ClassModule> queryModulesByCompanyId(Integer companyId);
	
	ClassmoduleNoOnsaleVo queryModuleNoIsExist(ClassmoduleNoOnsaleVo c);
	void updateClassModuleOnsale(ClassmoduleNoOnsaleVo c);
	Integer calculationHourByClassType(Integer classTypeId);
	

    /**
     * 
     * Class Name: IClassModuleService.java
     * @Description: 根据roomId查询公司id 分校id
     * @author 周文斌
     * @date 2015-12-11 下午3:29:40
     * @version 1.0
     * @param roomId
     * @return
     */
    ClassModule findCompanyIdByClassNo(String roomId);

    /**
     * 
     * Class Name: IClassModuleService.java
     * @Description: 修改状态
     * @author 周文斌
     * @date 2015-12-31 下午4:05:21
     * @version 1.0
     * @param param
     */
    void updateModelByItem(Map<String, Object> param);
}