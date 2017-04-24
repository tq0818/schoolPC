package com.yuxin.wx.student.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.student.StudentPaySlave;
import com.yuxin.wx.vo.student.StudentPaySlaveVo;
import com.yuxin.wx.common.BaseMapper;
/**
 * Service Interface:StudentPaySlave
 * @author wang.zx
 * @date 2014-12-5
 */
public interface StudentPaySlaveMapper extends BaseMapper<StudentPaySlave> {
	
	List<StudentPaySlave> findByStuId(Integer stuId);
	
	List<StudentPaySlaveVo> findByPayMasterId(Integer mid);
	List<StudentPaySlaveVo> findByPayMasterId2(Integer mid);

	void update2(Map<String, String> map);

	List<StudentPaySlave> findByPayMasterId3(Integer mid);
	/**
	 * 
	 * Class Name: StudentPaySlaveMapper.java
	 * @Description: 批量插入数据
	 * @author 权飞虎
	 * @date 2015年5月22日 下午3:40:47
	 * @modifier
	 * @modify-date 2015年5月22日 下午3:40:47
	 * @version 1.0
	 * @param list
	 */
	//void insert2(List<StudentPaySlave> list);

	/**
	 * 
	 * Class Name: IStudentPaySlaveService.java
	 * @Description: 根据资源id查询人数
	 * @author 周文斌
	 * @date 2015-5-26 下午2:38:37
	 * @version 1.0
	 * @param resourceId
	 * @return
	 */
	Integer findCountByResourceId(Integer resourceId);
	/**
	 * 
	 * Class Name: StudentPaySlaveMapper.java
	 * @Description: 将子订单做假删除,根据主订单id
	 * @author 权飞虎
	 * @date 2015年5月29日 下午4:23:24
	 * @modifier
	 * @modify-date 2015年5月29日 下午4:23:24
	 * @version 1.0
	 * @param mid
	 */
	void deleteByMasterId(Integer payMasterId);
	
	List<StudentPaySlave> queryStuSlaveBywhere(StudentPaySlave search);
	
	void deleteStuPaySlave(Integer id);

	void updateStatus(Map map);
	
}