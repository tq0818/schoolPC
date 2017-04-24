package com.yuxin.wx.classes.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.classes.IClassTypeMemberDiscountService;
import com.yuxin.wx.classes.mapper.ClassTypeMapper;
import com.yuxin.wx.classes.mapper.ClassTypeMemberDiscountMapper;
import com.yuxin.wx.commodity.mapper.CommodityMapper;
import com.yuxin.wx.commodity.mapper.CommodityProductRealtionMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyMemberLevelConfigMapper;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeMemberDiscount;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.company.CompanyMemberLevelConfig;
import com.yuxin.wx.vo.classes.ClassTypeMemberDiscountVo;
import com.yuxin.wx.vo.company.MemberLevelAndClassTypeVo;
import com.yuxin.wx.vo.privilege.UserRolesListVo;


/**
 * Service Implementation:ClassTypeMemberDiscount
 * @author chopin
 * @date 2016-5-17
 */
@Service
@Transactional
public class ClassTypeMemberDiscountServiceImpl extends BaseServiceImpl implements IClassTypeMemberDiscountService {

	@Autowired
	private ClassTypeMemberDiscountMapper classTypeMemberDiscountMapper;
	@Autowired
	private CompanyMemberLevelConfigMapper companyMemberLevelConfigMapper;
	@Autowired
	private ClassTypeMapper classTypeMapper;
	@Autowired
	private CommodityProductRealtionMapper commodityProductRealtionMapper;
	@Autowired
	private CommodityMapper commodityMapper;
	/**
	 * 
	* @Title: saveClassTypeMemberDiscount
	* @Description: 新增ClassTypeMemberDiscount
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void insert(ClassTypeMemberDiscount entity){
		classTypeMemberDiscountMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveClassTypeMemberDiscount 
	* @Description: 批量新增ClassTypeMemberDiscount
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<ClassTypeMemberDiscount> entity){
		classTypeMemberDiscountMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateClassTypeMemberDiscount 
	* @Description: 编辑ClassTypeMemberDiscount
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void update(ClassTypeMemberDiscount entity){
		classTypeMemberDiscountMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteClassTypeMemberDiscountById 
	* @Description: 根据id删除ClassTypeMemberDiscount
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	 @Override
	public void deleteClassTypeMemberDiscountById(Integer id){
		classTypeMemberDiscountMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteClassTypeMemberDiscountByIds 
	* @Description: 根据id批量删除ClassTypeMemberDiscount
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void deleteClassTypeMemberDiscountByIds(Integer[] ids){
		classTypeMemberDiscountMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findClassTypeMemberDiscountById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public ClassTypeMemberDiscount findClassTypeMemberDiscountById(Integer id){
		return classTypeMemberDiscountMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findClassTypeMemberDiscountByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassTypeMemberDiscount>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public List<ClassTypeMemberDiscount> findClassTypeMemberDiscountByPage(ClassTypeMemberDiscount search){
		return classTypeMemberDiscountMapper.page(search);
	}

	@Override
	public List<ClassTypeMemberDiscount> findClassLevelList(
			ClassTypeMemberDiscount search) {
		// TODO Auto-generated method stub
		return classTypeMemberDiscountMapper.findClassLevelList(search);
	}

	@Override
	public String updateClassTypeMember(Integer companyId,Integer cId,List<ClassTypeMemberDiscount> list) {
		//查询课程会员级别
		ClassTypeMemberDiscount con=new ClassTypeMemberDiscount();
		con.setCompanyId(companyId);
		con.setClassTypeId(cId);
		List<ClassTypeMemberDiscount> arr=classTypeMemberDiscountMapper.findClassLevelList(con);
		if(null!=arr && arr.size()>0){
			for(ClassTypeMemberDiscount arr1:arr){
				if(null!=arr1){
					classTypeMemberDiscountMapper.deleteById(arr1.getId());
				}
			}
			
		}
		if(null!=list && list.size()>0){
			for(ClassTypeMemberDiscount member:list){
				member.setCompanyId(companyId);
			    classTypeMemberDiscountMapper.insert(member);
			}
		}
		return "success";
	}

	@Override
	public List<MemberLevelAndClassTypeVo> queryMemberLevelAndClassTypeVoListByMemberId(
			ClassTypeMemberDiscountVo search) {
		return classTypeMemberDiscountMapper.queryMemberLevelAndClassTypeVoListByMemberId(search);
	}
	
	@Override
	public PageFinder<MemberLevelAndClassTypeVo> queryMemberLevelAndClassTypeVoListByMemberIdPage(
			ClassTypeMemberDiscount search) {
		List<MemberLevelAndClassTypeVo> data = classTypeMemberDiscountMapper.queryMemberLevelAndClassTypeVoListByMemberIdPage(search);
		int rowCount=classTypeMemberDiscountMapper.queryMemberLevelAndClassTypeVoListByMemberIdPageCount(search);
		PageFinder<MemberLevelAndClassTypeVo> pageFinder=new PageFinder<MemberLevelAndClassTypeVo>(search.getPage(), search.getPageSize(), rowCount, data);
		return  pageFinder;
	}

	@Override
	public boolean saveClassType(String memberId, String ids, int companyId) {
		String[] idsstr=ids.split(",");
		CompanyMemberLevelConfig cmlc = companyMemberLevelConfigMapper.findById(Integer.parseInt(memberId));
		if(cmlc!=null){
			for (String id : idsstr) {
				if(""!=id&&!"".equals(id)){
					ClassTypeMemberDiscount ctmd=new ClassTypeMemberDiscount();
					ctmd.setMemberLevel(cmlc.getName());
					ctmd.setMemberDiscount(cmlc.getDiscount());
					ctmd.setMemberId(Integer.parseInt(memberId));
					ctmd.setClassTypeId(Integer.parseInt(id));
					ctmd.setCompanyId(companyId);
					ctmd.setDiscountType(1);
					classTypeMemberDiscountMapper.insert(ctmd);
					
					//维护commodity
					CommodityProductRealtion cpr=commodityProductRealtionMapper.findByClassTypeId(id);
					if(null!=cpr && null!=cpr.getComId()){
						Commodity commodity=commodityMapper.findById(cpr.getComId());
						if(null!=commodity){
							commodity.setMemberFlag(1);
							commodityMapper.update(commodity);
						}
					}
					//将课程加入会员课程
					ClassType  classType=classTypeMapper.findById(Integer.parseInt(id));
					if(null!=classType){
						classType.setMemberFlag(1);
						classTypeMapper.update(classType);
					}
				}
			}
		}else{
			return false;
		}
		return true;
	}

	@Override
	public MemberLevelAndClassTypeVo queryMemberLevelAndClassTypeVoByClassId(
			ClassTypeMemberDiscount ctmd) {
		return classTypeMemberDiscountMapper.queryMemberLevelAndClassTypeVoByClassId(ctmd);
	}

	@Override
	public ClassTypeMemberDiscount findDiscountByMemberIdAndClassTypeId(
			ClassTypeMemberDiscount ctmd) {
		return classTypeMemberDiscountMapper.findDiscountByMemberIdAndClassTypeId(ctmd);
	};
	public void deleteClassTypeMemberDiscountByMemberId(Integer id){
		 classTypeMemberDiscountMapper.deleteClassTypeMemberDiscountByMemberId(id);
	}

	@Override
	public int selectClassTypeCount(Integer id) {
		return classTypeMemberDiscountMapper.selectClassTypeCount(id);
	}
}
