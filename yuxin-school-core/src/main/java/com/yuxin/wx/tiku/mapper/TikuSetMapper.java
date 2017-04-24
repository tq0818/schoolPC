package com.yuxin.wx.tiku.mapper;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuSet;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuSetMapper extends BaseMapper<TikuSet> {

	/**
	 * 
	 * Class Name: ITikuSetService.java
	 * @Description: 查询题库设置
	 * @author 周文斌
	 * @date 2015-7-9 下午2:27:40
	 * @version 1.0
	 * @param tiku
	 * @return
	 */
	TikuSet findSetByCompanyIdAndCategoryId(TikuSet tiku);
	
	void delByCateId(Integer cateId);
	
}