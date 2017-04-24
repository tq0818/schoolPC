package com.yuxin.wx.company.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.company.CompanyStudyCard;
import com.yuxin.wx.vo.company.CompanyStudyCardsVo;

/**
 * Service Interface:Users
 * 
 * @author chopin
 * @date 2015-3-12
 */
public interface CompanyStudyCardMapper extends BaseMapper<CompanyStudyCard> {

	List<CompanyStudyCardsVo> queryStudyCards(CompanyStudyCardsVo search);

	int queryStudyCardsCount(CompanyStudyCardsVo search);
	
	List<CompanyStudyCardsVo> queryStudyCardsList(CompanyStudyCardsVo search);
	
	int queryCountByNameOrPrefix(Map<String, Object> map);
}