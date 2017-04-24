package com.yuxin.wx.api.classes;

import java.util.List;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassTypeMemberDiscount;
import com.yuxin.wx.vo.classes.ClassTypeMemberDiscountVo;
import com.yuxin.wx.vo.company.MemberLevelAndClassTypeVo;
/**
 * Service Interface:ClassTypeMemberDiscount
 * @author chopin
 * @date 2016-5-17
 */
public interface IClassTypeMemberDiscountService  {
	/**
	 * 
	* @Title: saveClassTypeMemberDiscount
	* @Description: 新增ClassTypeMemberDiscount
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void insert(ClassTypeMemberDiscount entity);
	
	/**
	 * 
	* @Title: batchSaveClassTypeMemberDiscount 
	* @Description: 批量新增ClassTypeMemberDiscount
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void batchInsert(List<ClassTypeMemberDiscount> list);
	
	/**
	 * 
	* @Title: updateClassTypeMemberDiscount 
	* @Description: 编辑ClassTypeMemberDiscount
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void update(ClassTypeMemberDiscount entity);
	
	/**
	 * 
	* @Title: deleteClassTypeMemberDiscountById 
	* @Description: 根据id删除ClassTypeMemberDiscount
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void deleteClassTypeMemberDiscountById(Integer id);
	
	/**
	 * 
	* @Title: deleteClassTypeMemberDiscountByIds 
	* @Description: 根据id批量删除ClassTypeMemberDiscount
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	void deleteClassTypeMemberDiscountByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findClassTypeMemberDiscountById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	ClassTypeMemberDiscount findClassTypeMemberDiscountById(Integer id);
	
	/**
	 * 
	* @Title: findClassTypeMemberDiscountByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassTypeMemberDiscount>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by wangzx
	 */
	List<ClassTypeMemberDiscount> findClassTypeMemberDiscountByPage(ClassTypeMemberDiscount search);
	
	/**
	 * 课程会员等级列表
	 * @param search
	 * @return
	 */
	List<ClassTypeMemberDiscount> findClassLevelList(ClassTypeMemberDiscount search);
	
	/**
	 * 更新课程会员信息
	 * @param list
	 * @return
	 */
	String updateClassTypeMember(Integer companyId,Integer cId,List<ClassTypeMemberDiscount> list);
	
	/**
	 * 
	 * @Description:根据会员等级id查询课程
	 * @author: dongshuai
	 * @date: 2016年5月26日
	 * @param search
	 * @return
	 *
	 */
	List<MemberLevelAndClassTypeVo> queryMemberLevelAndClassTypeVoListByMemberId(ClassTypeMemberDiscountVo search);
	
	
	/**
	 * 
	 * @Description:保存会员课程
	 * @author: dongshuai
	 * @date: 2016年5月26日
	 * @param memberId
	 * @param ids
	 * @param companyId
	 * @return
	 *
	 */
	boolean saveClassType(String memberId,String ids,int companyId);
	
	PageFinder<MemberLevelAndClassTypeVo> queryMemberLevelAndClassTypeVoListByMemberIdPage(
			ClassTypeMemberDiscount search);
	
	/**
	 * 
	 * @Description:根据课程id和会员id查询课程信息
	 * @author: dongshuai
	 * @date: 2016年5月29日
	 * @param classTypeId
	 * @return
	 *
	 */
	MemberLevelAndClassTypeVo queryMemberLevelAndClassTypeVoByClassId(ClassTypeMemberDiscount ctmd);
	
	ClassTypeMemberDiscount findDiscountByMemberIdAndClassTypeId(ClassTypeMemberDiscount ctmd);

	void deleteClassTypeMemberDiscountByMemberId(Integer id);
	
	int selectClassTypeCount(Integer id);
}