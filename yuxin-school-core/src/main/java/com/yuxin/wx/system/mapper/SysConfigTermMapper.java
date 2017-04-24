package com.yuxin.wx.system.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysConfigTerm;
import com.yuxin.wx.vo.system.ConfigTermVo;
/**
 * Service Interface:SysConfigTerm
 * @author wang.zx
 * @date 2014-12-5
 */
public interface SysConfigTermMapper extends BaseMapper<SysConfigTerm> {
	List<SysConfigTerm> findList(Integer itemOneId);
	
	/**
	 * 
	 * Class Name: SysConfigTermMapper.java
	 * @Description: 根据条件查询考期
	 * @author liuxindong
	 * @date 2014-12-14 下午8:47:24
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigTerm> queryTerm(SysConfigTerm search);
	
	/**
	 * 
	 * Class Name: SysConfigTermMapper.java
	 * @Description: 根据条件分页查询考期列表
	 * @author liuxindong
	 * @date 2014-12-14 下午8:47:38
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<ConfigTermVo> page(SysConfigTerm param);
	
	/**
	 * 
	 * Class Name: SysConfigTermMapper.java
	 * @Description: 根据条件分页查询考期总数
	 * @author liuxindong
	 * @date 2014-12-14 下午8:47:43
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer pageCount(SysConfigTerm param);
	
	/**
	 * 
	 * Class Name: SysConfigTermMapper.java
	 * @Description: 查询列表
	 * @author Chopin
	 * @date 2014年12月21日
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<SysConfigTerm> findList(SysConfigTerm search);
	
	/**
	 * @Description:(根据一级项目获取对应的目标考期, 注意不包含校区)
	 * @author wang.zx 
	 * @date 2014-12-26 上午11:58:56
	 * @version 1.0
	 * @param itemOneId
	 * @return
	 */
	List<SysConfigTerm> getTermByItemOne(Map map);
	
	//ycl-start
	List<ConfigTermVo> findByCompandyId(SysConfigTerm configTerm);
	//ycl-start

	/**
	 * 
	 * Class Name: ISysConfigTermService.java
	 * @Description: 根据一级项目 查询 考期
	 * @author 周文斌
	 * @date 2015-5-15 下午12:27:07
	 * @version 1.0
	 * @param oneItemId
	 * @return
	 */
	List<SysConfigTerm> findTermByOneItemId(Integer oneItemId);
	
	/**
	 * 根据考期名称查询考期
	 * @param configTerm
	 * @return
	 */
	List<SysConfigTerm> findByName(SysConfigTerm configTerm);
}