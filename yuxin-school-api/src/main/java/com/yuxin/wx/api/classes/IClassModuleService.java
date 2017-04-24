package com.yuxin.wx.api.classes;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleNo;
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
public interface IClassModuleService  {
	/**
	 * 
	* @Title: saveClassModule
	* @Description: 新增ClassModule
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(ClassModule classModule);
	
	/**
	 * 
	* @Title: batchSaveClassModule 
	* @Description: 批量新增ClassModule
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<ClassModule> classModule);
	
	/**
	 * 
	* @Title: updateClassModule 
	* @Description: 编辑ClassModule
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(ClassModule classModule);
	
	/**
	 * 
	* @Title: deleteClassModuleById 
	* @Description: 根据id删除ClassModule
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteClassModuleById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassModuleByIds 
	* @Description: 根据id批量删除ClassModule
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteClassModuleByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassModuleById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	ClassModule findClassModuleById(Integer id);
	
	/**
	 * 
	* @Title: findClassModuleByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassModule>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<ClassModule> findClassModuleByPage(ClassModule search);
	
	
	/**
	 * 
	* @Title: queryClassModuleByKeys 
	* @Description: 根据条件查询模块信息
	* @return
	* @return List<ClassModule>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-6
	* @user by zhangbo
	 */
	public List<ClassModuleVo> queryClassModuleByKeys(ClassModule classModule);
	
	/**
	 * 
	* @Title: queryClassModuleByKeysCount 
	* @Description: 根据条件查询模块总数
	* @return
	* @return List<ClassModule>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-6
	* @user by zhangbo
	 */
	public int queryClassModuleByKeysCount(ClassModule classModule);

	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 根据id查询模块详细信息
	 * @author Kevin
	 * @date 2014-12-19 上午10:09:31
	 * @version 1.0
	 * @param moduleId
	 * @return
	 */
	public ClassModuleVo queryOneVoById(Integer moduleId);
	
	/**
	 * @Description:(根据二级项目查询对应的模块)
	 * @author wang.zx 
	 * @date 2014-12-27 下午7:20:53
	 * @version 1.0
	 * @param secondItemId
	 * @return
	 */
	public List<ClassModule> findModulesBySecondItemId(Integer secondItemId);
	
	/**
	 * @Description: 根据条件查询模块列表，不分页
	 * @author wzx
	 * @date 2015-5-28 下午4:52:55
	 * @version 1.0
	 * @return
	 */
	public List<ClassModule> findModulesByModule(ClassModule module);
	
	
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 根据班级模块关系获取当前班级下面所有模块
	 * @author Kevin
	 * @date 2014年12月31日
	 * @version 1.0
	 * @param secondItemId
	 * @return
	 */
	
	public List<ClassModule> findModulesByClassTypeId(Integer classTypeId);
	
	/**
	 * 根据模块id查询是否被班型所用
	 * Class Name: IClassModuleService.java
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author Keyn
	 * @date 2015-2-6 下午12:45:42
	 * @version 1.0
	 * @param moduleId
	 * @return
	 */
	Integer checkUpdate(Integer moduleId);
	
	/**
	 * 根据模块id查询是否能停用
	 * Class Name: IClassModuleService.java
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author Keyn
	 * @date 2015-2-6 下午12:45:42
	 * @version 1.0
	 * @param moduleId
	 * @return
	 */
	Integer checkStop(Integer moduleId);
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 根据班型查询所对应的模块号
	 * @author 权飞虎
	 * @date 2015年4月27日 上午11:34:37
	 * @modifier
	 * @modify-date 2015年4月27日 上午11:34:37
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<ClassModule> findByClassTypeId(Integer id);
	
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 条件查询可用模块信息
	 * @author zhang.zx
	 * @date 2015-5-6
	 * @modifier
	 * @modify-date 2015-5-6
	 * @version 1.0
	 * @param classModule
	 * @return
	 */
	List<ClassModuleVo> findAllClassModule(ClassModule classModule);
	
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 根据班型查询模块
	 * @author zhang.zx
	 * @date 2015-5-6
	 * @modifier
	 * @modify-date 2015-5-6
	 * @version 1.0
	 * @param 
	 * @return
	 */
	List<ClassModuleVo> queryAllModules(String classTypeId);

	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 根据主订单查询模块
	 * @author 权飞虎
	 * @date 2015年5月8日 下午9:08:20
	 * @modifier
	 * @modify-date 2015年5月8日 下午9:08:20
	 * @version 1.0
	 * @param paySlave
	 * @return
	 */
	List<ClassModule> findByPayMasterId(StudentPaySlave paySlave);
	
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
	
	/**
	 * @Description: 根据模块id批量查询模块
	 * @author wzx
	 * @date 2015-5-11 上午10:09:23
	 * @version 1.0
	 * @param ids
	 * @return
	 */
	List<ClassModule> findModuleByIds(List<String> ids);
	

	PageFinder<ClassModuleVo> querySearchPage(ClassModule module);
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 根据id做假删除
	 * @author 权飞虎
	 * @date 2015年5月12日 下午5:26:27
	 * @modifier
	 * @modify-date 2015年5月12日 下午5:26:27
	 * @version 1.0
	 * @param module
	 */
	void changeStatus(ClassModule module);
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 根据名字查询模块(不包括已经删除的)
	 * @author 权飞虎
	 * @date 2015年5月12日 下午9:05:56
	 * @modifier
	 * @modify-date 2015年5月12日 下午9:05:56
	 * @version 1.0
	 * @param name
	 * @param companyId 
	 * @return
	 */
	List<ClassModule> findByName(String name, Integer companyId);
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 根据ID查询
	 * @author 权飞虎
	 * @date 2015年5月13日 下午3:12:38
	 * @modifier
	 * @modify-date 2015年5月13日 下午3:12:38
	 * @version 1.0
	 * @param id
	 * @return
	 */
	ClassModuleVo findClassModuleVoById(Integer id);
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 根据条件查模块号
	 * @author 权飞虎
	 * @date 2015年5月17日 下午7:57:32
	 * @modifier
	 * @modify-date 2015年5月17日 下午7:57:32
	 * @version 1.0
	 * @param moduleNo
	 * @return
	 */
	List<ClassModule> findClassModules(ClassModuleNo moduleNo);
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 判断当前模块是否正在在售模块中使用
	 * @author 权飞虎
	 * @date 2015年5月18日 下午4:05:17
	 * @modifier
	 * @modify-date 2015年5月18日 下午4:05:17
	 * @version 1.0
	 * @param id
	 * @param currentCompanyId
	 * @return
	 */
	Integer isUse(Integer id, Integer currentCompanyId);
	
	/**
	 * 根据模块名称查询模块信息
	 * zhang.zx
	 * @param name
	 * @return
	 */
	ClassModule queryModuleByName(ClassModule c);
	
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 根据班型id查询模块
	 * @author zhang.zx
	 * @date 2015年7月14日 下午5:35:36
	 * @modifier
	 * @modify-date 2015年7月14日 下午5:35:36
	 * @version 1.0
	 * @param classTypeId
	 * @return
	 */
	ClassModule queryModuleByClasstypeId(Integer classTypeId);
	
	/**
	 * 添加在售模块班号
	 * zhang.zx
	 * @param search
	 */
	void insertClassModuleOnsale(ClassmoduleNoOnsaleVo search);
	
	/**
	 * 根据模块号查询班号
	 * zhang.zx
	 * @param moduleId
	 * @return
	 */
	List<ClassmoduleNoOnsaleVo> queryModuleNoByModuleId(Integer moduleId);
	
	/**
	 * 删除在售班号
	 * zhang.zx
	 * @param id
	 */
	void deleteClassModuleOnsaleById(ClassmoduleNoOnsaleVo c);
	
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description:查询视频模块信息
	 * @author zhang.zx
	 * @date 2015年7月7日 上午9:32:14
	 * @modifier
	 * @modify-date 2015年7月7日 上午9:32:14
	 * @version 1.0
	 * @param vc
	 * @return
	 */
	List<ClassVideoModuleVo> queryVideoModule(ClassVideoModuleVo vc);
	
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 根据公司id查询模块信息
	 * @author zhang.zx
	 * @date 2015年8月11日 下午4:26:36
	 * @modifier
	 * @modify-date 2015年8月11日 下午4:26:36
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<ClassModule> queryModulesByCompanyId(Integer companyId);
	
	/**
	 * 
	 * Class Name: IClassModuleService.java
	 * @Description: 添加班号
	 * @author zhang.zx
	 * @date 2015年8月12日 下午6:56:14
	 * @modifier
	 * @modify-date 2015年8月12日 下午6:56:14
	 * @version 1.0
	 * @param search
	 */
	void insertClassModuleOnsale(List<ClassmoduleNoOnsaleVo> search);
	
	/**
	 * @Description: 根据版型ID查询版型所对应的总课时
	 * @author wzx
	 * @date 2015-5-12 下午7:25:57
	 * @version 1.0
	 * @param classTypeId
	 * @return
	 */
	Integer calculationHourByClassType(Integer classTypeId);
	
    List<ClassmoduleNoOnsaleVo> queryModuleNoByClasstypeId(Integer classTypeId);
    
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