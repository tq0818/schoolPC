package com.yuxin.wx.api.classes;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.vo.classes.ClassModuleNoListVo;
import com.yuxin.wx.vo.classes.ClassModuleNoVo;
import com.yuxin.wx.vo.classes.ClassVo;
/**
 * Service Interface:ClassModuleNo
 * @author wang.zx
 * @date 2014-12-5
 */
public interface IClassModuleNoService  {
	/**
	 * 
	* @Title: saveClassModuleNo
	* @Description: 新增ClassModuleNo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void insert(ClassModuleNo classModuleNo);
	
	/**
	 * 
	* @Title: getCampus_name
	* @Description: 由id获得校区名称
	* @return String    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-28
	* @user by ycl
	 */
	String getCampus_nameById(Integer id);
	
	/**
	 * 
	* @Title: batchSaveClassModuleNo 
	* @Description: 批量新增ClassModuleNo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void batchInsert(List<ClassModuleNo> classModuleNo);
	
	/**
	 * 
	* @Title: updateClassModuleNo 
	* @Description: 编辑ClassModuleNo
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void update(ClassModuleNo classModuleNo);
	
	/**
	 * 
	* @Title: deleteClassModuleNoById 
	* @Description: 根据id删除ClassModuleNo
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteClassModuleNoById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassModuleNoByIds 
	* @Description: 根据id批量删除ClassModuleNo
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	void deleteClassModuleNoByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassModuleNoById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	ClassModuleNo findClassModuleNoById(Integer id);
	
	
	/**
	 * 
	* @Title: findClassModuleNoById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	List<ClassModuleNoVo> findListByModuleId(Integer moduleId);
	
	/**
	 * 
	* @Title: findClassModuleNoByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassModuleNo>    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by wangzx
	 */
	List<ClassModuleNo> findClassModuleNoByPage(ClassModuleNo search);
	
	/**
	 * @Description:(查询班号list)
	 * @author wang.zx 
	 * @date 2015-1-9 下午7:12:13
	 * @version 1.0
	 * @param vo
	 * @return
	 */
	List<ClassModuleNoListVo> findClassModuleNoListVoByPage(ClassModuleNo search);
	
	/**
	 * @Description:根据校区ID查询该校区下是否还有正在使用的课程
	 * @author wang.zx 
	 * @date 2015-2-9 下午7:12:13
	 * @version 1.0
	 * @param vo
	 * @return
	 */
	List<ClassModuleNo> findClassModuleNoListByCampusId(Integer compusId);
	
	/**
	 * @Description:(根据班号ID查询对应的VO)
	 * @author wang.zx 
	 * @date 2015-2-1 下午6:11:17
	 * @version 1.0
	 * @param id
	 * @return
	 */
	ClassModuleNoListVo findModuleNoListVoById(Integer id);
	
	int findCountByKeys(ClassModuleNo search);
	
	/**T
	 * 
	 * Class Name: IClassModuleNoService.java
	 * @Description: 根据moduelid 查询 班号
	 * @author 周文斌
	 * @date 2015-5-4 下午6:13:21
	 * @version 1.0
	 * @param <T>
	 * @param moduleIds
	 * @return
	 */
	List<String> findClassNoByCompanyId(Map<String,Object> param);
	
	
	/**
	 * 
	* @Title: findListByModule
	* @Description: 根据moduleid 查询 moduleNo 列表
	* @param moduleId
	* @return ClassModuleNo    返回类型 
	* @throws 
	* @exception 
	* @date 2014-12-5
	* @user by chopin
	 */
	public List<ClassModuleNoVo> findListByModule(ClassModuleNo search);
	/**
	 * 
	 * Class Name: IClassModuleNoService.java
	 * @Description: 根据模块查询对应班号
	 * @author 权飞虎
	 * @date 2015年5月8日 下午3:22:48
	 * @modifier
	 * @modify-date 2015年5月8日 下午3:22:48
	 * @version 1.0
	 * @param classModuleId
	 * @return
	 */
	List<ClassModuleNo> findByCmId(Integer classModuleId,Integer classTypeId);
	/**
	 * 
	 * Class Name: IClassModuleNoService.java
	 * @Description: 根据子订单查询所有模块号
	 * @author 权飞虎
	 * @date 2015年5月9日 上午10:41:42
	 * @modifier
	 * @modify-date 2015年5月9日 上午10:41:42
	 * @version 1.0
	 * @param paySlave
	 * @return
	 */
	List<ClassModuleNo> findBySearch(StudentPaySlave paySlave);

	List<ClassModuleNoListVo> findVoByCmId(Integer id);
	
	/**
	 * 
	 * Class Name: IClassModuleNoService.java
	 * @Description: 查询班号详细信息
	 * @author zhang.zx
	 * @date 2015年5月11日 上午11:35:11
	 * @modifier
	 * @modify-date 2015年5月11日 上午11:35:11
	 * @version 1.0
	 * @param classModule
	 * @return
	 */
	List<ClassModuleNoListVo> findModuleNoListByClassType(Integer moduleId);
	
	/**
	 * 
	 * Class Name: IClassModuleNoService.java
	 * @Description: 多条件查询排课
	 * @author 周文斌
	 * @date 2015-5-14 下午8:30:36
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<ClassVo> findClassInfoByMap(ClassVo classVo);
	
	/**
	 * 
	 * Class Name: IClassModuleNoService.java
	 * @Description: 查询多条件 排课 总数
	 * @author 周文斌
	 * @date 2015-5-14 下午9:01:45
	 * @version 1.0
	 * @param classVo
	 * @return
	 */
	Integer findCountByMap(ClassVo classVo);
	
	/**
	 * 
	 * Class Name: IClassModuleNoService.java
	 * @Description: 根据item id 查询 班号
	 * @author 周文斌
	 * @date 2015-6-5 下午12:11:47
	 * @version 1.0
	 * @param classModuleNo
	 * @return
	 */
	List<ClassModuleNo> findByItem(ClassModuleNo classModuleNo);
	
	/**
	 * 
	 * Class Name: IClassModuleNoService.java
	 * @Description: 根据公司id查询班号
	 * @author zhang.zx
	 * @date 2015年8月11日 下午5:14:02
	 * @modifier
	 * @modify-date 2015年8月11日 下午5:14:02
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<ClassModuleNo> queryModuleNoByCompanyId(Integer companyId);
	
	/**
	 * 
	 * Class Name: IClassModuleNoService.java
	 * @Description: 根据模块号查询班号
	 * @author zhang.zx
	 * @date 2015年8月18日 下午2:32:09
	 * @modifier
	 * @modify-date 2015年8月18日 下午2:32:09
	 * @version 1.0
	 * @param id
	 * @return
	 */
	List<ClassModuleNo> queryClassModuleNoById(Integer id);

	List<ClassModuleNo> findModuleNoOnSaleByModuleId(Integer id);
	
	List<Integer> findClassModuleNoIdsByModuleId(Integer id);
}