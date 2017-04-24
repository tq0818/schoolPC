package com.yuxin.wx.classes.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.classes.IClassPackageService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.classes.mapper.ClassPackageMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassPackage;
import com.yuxin.wx.model.pay.PayOrder;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.pay.mapper.PayOrderMapper;
import com.yuxin.wx.student.mapper.StudentMapper;
import com.yuxin.wx.student.mapper.StudentPayMasterMapper;
import com.yuxin.wx.system.mapper.SysConfigDictMapper;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import com.yuxin.wx.vo.classes.ClasspackageVo;
import com.yuxin.wx.vo.course.OrderManage;
import com.yuxin.wx.vo.student.StuPayMasterVo;

/**
 * Service Implementation:ClassPackage
 * @author chopin
 * @date 2016-3-21
 */
@Service
@Transactional
public class ClassPackageServiceImpl extends BaseServiceImpl implements IClassPackageService {

	@Autowired
	private ClassPackageMapper classPackageMapper;
	@Autowired
	private StudentPayMasterMapper studentPayMasterMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private UsersFrontMapper usersFrontMapper;
	@Autowired
	private PayOrderMapper payOrderMapper;
	@Autowired
	private SysConfigDictMapper sysConfigDictMapper;
	
	/**
	 * 
	* @Title: saveClassPackage
	* @Description: 新增ClassPackage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void insert(ClassPackage entity){
		classPackageMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveClassPackage 
	* @Description: 批量新增ClassPackage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<ClassPackage> entity){
		classPackageMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateClassPackage 
	* @Description: 编辑ClassPackage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void update(ClassPackage entity){
		classPackageMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteClassPackageById 
	* @Description: 根据id删除ClassPackage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	 @Override
	public void deleteClassPackageById(Integer id){
		classPackageMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteClassPackageByIds 
	* @Description: 根据id批量删除ClassPackage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public void deleteClassPackageByIds(Integer[] ids){
		classPackageMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findClassPackageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public ClassPackage findClassPackageById(Integer id){
		return classPackageMapper.findById(id);
	};
	/**
	 * 
	* @Title: findClassPackageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public ClasspackageVo findClassPackageVoById(Integer id){
		return classPackageMapper.findVoById(id);
	}
	
	/**
	 * 
	* @Title: findClassPackageByPage 
	* @Description: 分页查询
	* @return
	* @return List<ClassPackage>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-3-21
	* @user by chopin
	 */
	@Override
	public List<ClassPackage> findClassPackageByPage(ClassPackage search){
		return classPackageMapper.page(search);
	}

	@Override
	public PageFinder<ClassPackage> findClassPackageLists(ClassPackage search) {
		List<ClassPackage> data=classPackageMapper.queryClassPackageByCondition(search);
		Integer count=classPackageMapper.queryClassPackageCount(search);
		PageFinder<ClassPackage> pageFinder=new PageFinder<ClassPackage>(search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public List<ClassPackage> queryClassPackageByWhere(ClassPackage search) {
		// TODO Auto-generated method stub
		return classPackageMapper.queryClassPackageByWhere(search);
	}

	@Override
	public PageFinder<OrderManage> findClassPackageOrderList(
			OrderManage search) {
		List<OrderManage> data=studentPayMasterMapper.queryClassPackageOrder(search);
		for(OrderManage pay:data){
			if(null!=pay){
				if(null!=pay.getPayType()){
					SysConfigDict dict=new SysConfigDict();
					dict.setItemCode(pay.getPayType());
					SysConfigDict dic=sysConfigDictMapper.findByCode(dict);
					if(null!=dic){
						pay.setPayType(dic.getItemValue());
					}
				}
				if(null!=pay.getUserId()){
					UsersFront u=usersFrontMapper.findById(pay.getUserId());
					if(null!=u && null!=u.getUsername()){
						pay.setUsername(u.getUsername());
					}
				}
			}
		}
		Integer count=studentPayMasterMapper.queryClassPackageOrderCount(search);
		PageFinder<OrderManage> pageFinder=new PageFinder<OrderManage>(search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	};
	
	public ClasspackageVo queryDetail(Map search){
		return classPackageMapper.queryByCustomWhere(search);
	}
	/**
	 * 查询课程包里所有的版型id
	 */
	@Override
	public List<Integer> findClassPackageAllClasses(Integer classPackageId){
		return classPackageMapper.findClassPackageAllClasses(classPackageId);
	}

	@Override
	public PageFinder<ClassPackage> queryClassPackageHasCountsByCondition(ClassPackage search) {
		List<ClassPackage> data=classPackageMapper.queryClassPackageHasCountsByCondition(search);
		Integer count=classPackageMapper.queryClassPackageHasCountsCountByCondition(search);
		PageFinder<ClassPackage> pageFinder=new PageFinder<ClassPackage>(search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	}
	
	@Override
    public PageFinder<ClasspackageVo> findByCondition(ClasspackageVo search) {
        List<ClasspackageVo> data = this.classPackageMapper.findByCondition(search);
        Integer count = this.classPackageMapper.findByConditionCount(search);
        PageFinder<ClasspackageVo> pageFind = new PageFinder<ClasspackageVo>(search.getPage(), search.getPageSize(), count, data);
        return pageFind;
    }

	@Override
	public List<ClassPackage> queryCommodityByClassPackage(ClassPackage search) {
		return classPackageMapper.queryCommodityByClassPackage(search);
	}

	@Override
	public ClassPackage queryClassPackageByComId(Integer id) {
		return classPackageMapper.queryClassPackageByComId(id);
	}
}
