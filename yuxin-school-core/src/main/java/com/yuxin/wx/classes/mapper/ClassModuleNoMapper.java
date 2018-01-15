package com.yuxin.wx.classes.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassModuleNo;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.vo.classes.ClassModuleNoListVo;
import com.yuxin.wx.vo.classes.ClassModuleNoVo;
import com.yuxin.wx.vo.classes.ClassVo;
/**
 * Service Interface:ClassModuleNo
 * @author wang.zx
 * @date 2014-12-5
 */
public interface ClassModuleNoMapper extends BaseMapper<ClassModuleNo> {
	
	List<ClassModuleNo> page(ClassModuleNo classModuleNo);
	
	List<ClassModuleNoVo> findListModuleId(Integer moduleId); 
	
	/**
	 * @Description:(查询班号list)
	 * @author wang.zx 
	 * @date 2015-1-9 下午7:12:13
	 * @version 1.0
	 * @param vo
	 * @return
	 */
	List<ClassModuleNoListVo> pageList(ClassModuleNo vo);
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
	 * @Description:(根据ID查询班号VO)
	 * @author wang.zx 
	 * @date 2015-2-1 下午6:13:40
	 * @version 1.0
	 * @param id
	 * @return
	 */
	ClassModuleNoListVo findModuleNoListVoById(Integer id);
	
	/**
	 * @Description:(查询班号总数)
	 * @author wang.zx 
	 * @date 2015-1-9 下午10:40:23
	 * @version 1.0
	 * @param vo
	 * @return
	 */
	public Integer modulePageCount(ClassModuleNo vo);
	
	
	List<ClassModuleNo> findClassModuleNoListByCampusId(Integer campusId);

	/**
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
	List<ClassModuleNoVo> findListByModule(ClassModuleNo search);

	List<ClassModuleNo> findByCmId(Map map);

	List<ClassModuleNo> findBySearch(StudentPaySlave paySlave);

	List<ClassModuleNoListVo> findVoByCmId(Integer id);
	
	List<ClassModuleNoListVo> queryModuleNoListByClassType(Integer moduleId);

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
	
	List<ClassModuleNo> querymoduleNosByCompanyId(Integer companyId);
	
	List<ClassModuleNo> queryClassModuleNoById(Integer id);

	List<ClassModuleNo> findModuleNoOnSaleByModuleId(Integer id);
	
	List<Integer> findClassModuleNoIdsByModuleId(Integer id);
	
	ClassModuleNo findByModuleId(Integer moduleId);
}