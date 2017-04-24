package com.yuxin.wx.tiku.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuCategory;
import com.yuxin.wx.vo.tiku.TikuCategoryVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuCategoryMapper extends BaseMapper<TikuCategory> {
	List<TikuCategoryVo> findTikuCategoryVo(TikuCategory search);
	Integer findTopCountById(Integer id);
	Integer findTikuByComIdAndTName(TikuCategory category);
	
	List<TikuCategory> findCateList(Integer companyId);
}