package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.SysNewsType;
import com.yuxin.wx.vo.system.SysNewsTypeVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface SysNewsTypeMapper extends BaseMapper<SysNewsType> {

	SysNewsType findByName(SysNewsType sysNewsType);
	/**
	 * 
	 * @fileName : SysNewsTypeMapper.java
	 * @date : 2015年12月7日 下午2:34:20
	 * @author :　杨延博
	 * @description :查询类型列表
	 */
	List<SysNewsTypeVo> queryNewsType(SysNewsType sysNewsType);
	
	
}