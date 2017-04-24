package com.yuxin.wx.tiku.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuConfig;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuConfigMapper extends BaseMapper<TikuConfig> {

	/**
	 * 
	 * Class Name: ITikuConfigService.java
	 * @Description: 查询存不存在
	 * @author 周文斌
	 * @date 2016-3-2 下午5:54:20
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	List<TikuConfig> findList(Map<String, Object> param);

	/**
	 * 
	 * Class Name: ITikuConfigService.java
	 * @Description: 根据值查询
	 * @author 周文斌
	 * @date 2016-3-2 下午5:59:27
	 * @version 1.0
	 * @param param
	 * @return
	 */
	TikuConfig findByCodeValue(Map<String, Object> param);
}