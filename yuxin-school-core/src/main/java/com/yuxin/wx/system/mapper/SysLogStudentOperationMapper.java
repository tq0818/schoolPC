package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysLogStudentOperation;
import com.yuxin.wx.vo.system.SysLogStudentOperationVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysLogStudentOperationMapper extends BaseMapper<SysLogStudentOperation> {

	/**
	 * 
	 * Class Name: ISysLogStudentOperationService.java
	 * @Description: 根据条件查询一个 出来 
	 * @author 周文斌
	 * @date 2017-2-14 下午3:35:19
	 * @modify	2017-2-14 下午3:35:19
	 * @version 
	 * @param slso
	 * @param sort
	 * @return
	 */
	SysLogStudentOperation selectOneAsc(SysLogStudentOperation slso);
	/**
	 * 
	 * Class Name: ISysLogStudentOperationService.java
	 * @Description: 根据条件查询一个 出来 
	 * @author 周文斌
	 * @date 2017-2-14 下午3:35:19
	 * @modify	2017-2-14 下午3:35:19
	 * @version 
	 * @param slso
	 * @param sort
	 * @return
	 */
	SysLogStudentOperation selectOneDesc(SysLogStudentOperation slso);
	
	List<SysLogStudentOperation> selectListAsc(SysLogStudentOperation slso);
	List<SysLogStudentOperation> selectListDesc(SysLogStudentOperation slso);
	Integer selectListCount(SysLogStudentOperation slso);
	
	List<SysLogStudentOperation> selectListByDateAsc(SysLogStudentOperationVo slsoVo);
	List<SysLogStudentOperation> selectListByDateDesc(SysLogStudentOperationVo slsoVo);
	Integer selectListByDateCount(SysLogStudentOperationVo slsoVo);
	List<SysLogStudentOperationVo> findBySearch(SysLogStudentOperationVo search);

}