package com.yuxin.wx.classes.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.classes.ClassTypeMemberDiscount;
import com.yuxin.wx.vo.classes.ClassTypeMemberDiscountVo;
import com.yuxin.wx.vo.company.MemberLevelAndClassTypeVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface ClassTypeMemberDiscountMapper extends BaseMapper<ClassTypeMemberDiscount> {
	
	List<ClassTypeMemberDiscount> findClassLevelList(ClassTypeMemberDiscount search);
	List<MemberLevelAndClassTypeVo> queryMemberLevelAndClassTypeVoListByMemberId(ClassTypeMemberDiscountVo search);
	List<MemberLevelAndClassTypeVo> queryMemberLevelAndClassTypeVoListByMemberIdPage(ClassTypeMemberDiscount search);
	int queryMemberLevelAndClassTypeVoListByMemberIdPageCount(ClassTypeMemberDiscount search);
	MemberLevelAndClassTypeVo queryMemberLevelAndClassTypeVoByClassId(ClassTypeMemberDiscount ctmd);
	ClassTypeMemberDiscount findDiscountByMemberIdAndClassTypeId(ClassTypeMemberDiscount ctmd);
	void deleteClassTypeMemberDiscountByMemberId(Integer id);
	
	int selectClassTypeCount(Integer id);
}