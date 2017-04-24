package com.yuxin.wx.company.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyCouponsPatchService;
import com.yuxin.wx.commodity.mapper.CommodityMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.company.mapper.CompanyCouponsLibMapper;
import com.yuxin.wx.company.mapper.CompanyCouponsPatchMapper;
import com.yuxin.wx.company.mapper.CompanyStudentMessageMapper;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.company.CompanyCouponsLib;
import com.yuxin.wx.model.company.CompanyCouponsPatch;
import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.user.UserMessage;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.model.user.UsersFrontMyCoupons;
import com.yuxin.wx.student.mapper.StudentMapper;
import com.yuxin.wx.system.mapper.SysConfigItemMapper;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import com.yuxin.wx.user.mapper.UsersFrontMyCouponsMapper;
import com.yuxin.wx.util.ClassPackageUtil;
import com.yuxin.wx.util.JedisUtil;
import com.yuxin.wx.util.RandomCodesUtil;
import com.yuxin.wx.vo.company.CompanyCouponsPatchVo;

/**
 * Service Implementation:CompanyCouponsPatch
 * @author chopin
 * @date 2016-6-20
 */
@Service
@Transactional
public class CompanyCouponsPatchServiceImpl extends BaseServiceImpl implements ICompanyCouponsPatchService {
	private static Log log=LogFactory.getLog("log");
	@Autowired
	private CompanyCouponsPatchMapper companyCouponsPatchMapper;
	@Autowired
	private CompanyCouponsLibMapper companyCouponsLibMapper;
	@Autowired
	private UsersFrontMapper usersFrontMapper;
	@Autowired
	private UsersFrontMyCouponsMapper usersFrontMyCouponsMapper;
	@Autowired
	private CompanyStudentMessageMapper companyStudentMessageMapper;
	@Autowired
	private CommodityMapper commodityMapper;
	@Autowired
	private SysConfigItemMapper sysConfigItemMapper;
	@Autowired
	private StudentMapper studentMapper;
	
	private static final Long BASE_NUM=11000000L;
	private static final int ADD_NUM=1000000;
	private static StringBuffer bufs=new StringBuffer();
	/**
	 * 
	* @Title: saveCompanyCouponsPatch
	* @Description: 新增CompanyCouponsPatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void insert(CompanyCouponsPatch entity){
		companyCouponsPatchMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyCouponsPatch 
	* @Description: 批量新增CompanyCouponsPatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyCouponsPatch> entity){
		companyCouponsPatchMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyCouponsPatch 
	* @Description: 编辑CompanyCouponsPatch
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void update(CompanyCouponsPatch entity){
		companyCouponsPatchMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyCouponsPatchById 
	* @Description: 根据id删除CompanyCouponsPatch
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyCouponsPatchById(Integer id){
		companyCouponsPatchMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyCouponsPatchByIds 
	* @Description: 根据id批量删除CompanyCouponsPatch
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public void deleteCompanyCouponsPatchByIds(Integer[] ids){
		companyCouponsPatchMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyCouponsPatchById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public CompanyCouponsPatch findCompanyCouponsPatchById(Integer id){
		return companyCouponsPatchMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyCouponsPatchByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyCouponsPatch>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-6-20
	* @user by chopin
	 */
	@Override
	public List<CompanyCouponsPatch> findCompanyCouponsPatchByPage(CompanyCouponsPatch search){
		return companyCouponsPatchMapper.page(search);
	}

	@Override
	public List<CompanyCouponsPatch> queryCouponsByCondition(
			CompanyCouponsPatch search) {
		// TODO Auto-generated method stub
		return companyCouponsPatchMapper.queryCouponsByCondition(search);
	}
	
	@Override
	public List<Integer> findCouponsByDate(Date cleantime) {
		// TODO Auto-generated method stub
		return companyCouponsPatchMapper.findCouponsByDate(cleantime);
	}

	@Override
	public List<String> findCodeByPatchId(Integer id) {
		// TODO Auto-generated method stub
		return companyCouponsPatchMapper.findCodeByPatchId(id);
	}

	@Override
	public void delUserByCode(List<String> codes) {
		// TODO Auto-generated method stub
		companyCouponsPatchMapper.delUserByCode(codes);
	}

	@Override
	public void delLibByPatch(Integer id) {
		// TODO Auto-generated method stub
		companyCouponsPatchMapper.delLibByPatch(id);
	}
	
	private static Long getYu(Long i){
		 if(i>36){
			 if(i%36<36){
				 bufs.append(i%36+",");
			 }
			 return getYu(i/36);
		 }else{
			 if(i<36){
				 bufs.append(i+",");
			 }
			 return i;
		 }
	 }
	 private static String productCode(Long baseNum){
		 StringBuffer buf=new StringBuffer();
		 getYu(baseNum);
		 log.error("创建优惠码，baseNum：" + baseNum + "转36进制 arrays：" + bufs);
		 final char[] digits = {'0', '1', '2', '3', '4','5','6', '7', '8', '9', 'A', 'B', 'C', 'D','E', 'F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		 String[] str=reverseSort(bufs.toString()).split(",");
        for(int i=0;i<str.length;i++){
       	 if(null!=str[i]){
       		 buf.append(digits[Integer.parseInt(str[i])]);
       	 }
        }
        bufs.setLength(0);
        return buf.toString();
	}
	 
	 private static String reverseSort(String str){
		 StringBuffer b=new StringBuffer();
		 String[] s=str.split(",");
		 for(int i=s.length-1;i>=0;i--){
			 if(null!=s[i]){
				 b.append(s[i]+",");
			 }
		 }
		 return b.toString();
	}

	@Override
	public String manageCouponsPatch(CompanyCouponsPatch coupons,String type,Integer userId) {
		//发放优惠券
		CompanyCouponsLib condtion=new CompanyCouponsLib();
		condtion.setPatchId(coupons.getId());
		List<CompanyCouponsLib> data=companyCouponsLibMapper.queryLibsListByPatchId(condtion);
		if(null!=data && data.size()>0){
			return "success";
		}
		try {
			if("saveAndSend".equals(type) || (null!=coupons.getIssueWay() && coupons.getIssueWay()==1)){
				coupons=companyCouponsPatchMapper.findById(coupons.getId());
				if(null!=coupons && null!=coupons.getStatus() && "0".equals(coupons.getStatus())){
					addConpusLibs(coupons,userId);
				}
			}
		} catch (Exception e) {
			log.error("添加优惠码库失败",e);
			e.printStackTrace();
			return "发送优惠码失败";
			
		}
		
		return "success";
	};
	
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchServiceImpl.java
	 * @Description: 生成优惠券
	 * @author zhang.zx
	 * @date 2016年6月21日 下午3:42:14
	 * @modifier
	 * @modify-date 2016年6月21日 下午3:42:14
	 * @version 1.0
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	private List<String> genreateCode(Integer len,long baseIn){
		List<String> listcode=new ArrayList<String>();
		Random rd=new Random();
		for(int i=0;i<len;i++){
			Integer a=rd.nextInt(1000)+1;
			listcode.add(productCode(baseIn+a));
			baseIn+=a;
		}
		return listcode;
	}
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchServiceImpl.java
	 * @Description: 生成优惠码
	 * @author zzq
	 * @date 2016年6月28日
	 * @modifier
	 * @modify-date 
	 * @version 1.0
	 * @param i
	 * @param shift
	 * @return
	 */
	 private static String toProductString(int i, int shift) {
			final char[] digits = {'0', '1', '2', '3', '4','5','6', '7', '8', '9', 'A', 'B', 'C', 'D','E', 'F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
			char[] buf = new char[36];
			int charPos = 36;
			int radix = 1 << shift;
			int mask = radix +15;
			do {
			    buf[--charPos] = digits[i & mask];
			    i >>>= shift;
			} while (i != 0);

			return new String(buf, charPos, (36 - charPos));
     }
	
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchServiceImpl.java
	 * @Description: 添加优惠券库
	 * @author zhang.zx
	 * @date 2016年6月21日 下午3:46:11
	 * @modifier
	 * @modify-date 2016年6月21日 下午3:46:11
	 * @version 1.0
	 * @param coupons
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	private String addConpusLibs(CompanyCouponsPatch coupons,Integer userId) throws NoSuchAlgorithmException{
		
		List<CompanyCouponsLib> arr=new ArrayList<CompanyCouponsLib>();
		//线上
		if(null!=coupons && coupons.getIssueWay()==0){
			List<UsersFront> uList=new ArrayList<UsersFront>();
			//优惠对象
			UsersFront u=new UsersFront();
			u.setCompanyId(coupons.getCompanyId());
			if("COUPON_OBJECT_ALL".equals(coupons.getForCrowd())){
				//所有注册用户
				u.setStatus(1);
				uList=usersFrontMapper.findConponsUsersByCondition(u);
			}else if("COUPON_OBJECT_STUDENT".equals(coupons.getForCrowd())){
				//购买过课程的学员
				u.setVipFlag(1);
				u.setStatus(1);
				uList=usersFrontMapper.findConponsUsersByCondition(u);
			}else if("COUPON_OBJECT_MEMBER".equals(coupons.getForCrowd())){
				//会员
				u.setStatus(1);
				u.setMemberLevel(coupons.getMemberList());
				uList=usersFrontMapper.findConponsUsersByCondition(u);
			}else{
				//指定用户
				String[] str=coupons.getUserList().split(",");
				if(null!=str && str.length>0){
					for(int i=0;i<str.length;i++){
						if(null!=str[i]){
							UsersFront us=new UsersFront();
							us.setId(Integer.parseInt(str[i]));
							uList.add(us);
						}
					}
				}
			}
			for(UsersFront user:uList){
				if(null!=user && null!=user.getId()){
					CompanyCouponsLib lib=new CompanyCouponsLib();
					log.info("生成优惠券开始");
					//lib.setCode(coupons.getPromoCodePrefix()+genreateCode());
					lib.setTimeLimitFrom(coupons.getTimeLimitFrom());
					lib.setTimeLimitTo(coupons.getTimeLimitTo());
					lib.setStatus(0);
					lib.setPatchId(coupons.getId());
					lib.setCreater(userId);
//					lib.setUserId(user.getId());
					//优惠方式
					lib.setUseWay(coupons.getUseWay()+"");
					if(null!=coupons.getUseWay()){
						if(coupons.getUseWay()==0){
							lib.setInsCashNum(coupons.getInsCashNum());
						}else if(coupons.getUseWay()==1){
							lib.setOverNum(coupons.getOverNum());
							lib.setOverCutNum(coupons.getOverCutNum());
						}else if(coupons.getUseWay()==2){
							lib.setDiscountNum(coupons.getDiscountNum());
						}
					}
					lib.setDescription(coupons.getDescription());
					lib.setCommodityType(coupons.getCommodityType());
					//使用范围
					lib.setUseRange(coupons.getUseRange());
					if(null!=coupons.getUseRange()){
						if(coupons.getUseRange()==1){
							switch(coupons.getCommodityType()){
							case 0:
								lib.setRangeItemOne(coupons.getRangeItemOne());
								if(null!=coupons.getRangeItemSecond()){
									lib.setRangeItemSecond(coupons.getRangeItemSecond());
								}
								break;
							case 1:
								lib.setRangeItemPackageCategory(coupons.getRangeItemPackageCategory());
								break;
							}
						}else if(coupons.getUseRange()==2){
							switch(coupons.getCommodityType()){
							case 0:
								lib.setRangeItemOne(coupons.getRangeItemOne());
								if(null!=coupons.getRangeItemSecond()){
									lib.setRangeItemSecond(coupons.getRangeItemSecond());
								}
								if(null!=coupons.getRangeItemCourse()){
									Commodity ct=commodityMapper.findById(coupons.getRangeItemCourse());
									if(null!=ct){
										lib.setRangeItemOne(ct.getItemOneId());
										lib.setRangeItemSecond(ct.getItemSecondId());
										lib.setRangeItemCourse(ct.getId());
									}
								}
								break;
							case 1:
								lib.setRangeItemPackageCategory(coupons.getRangeItemPackageCategory());
								if(null!=coupons.getRangeItemCourse()){
									Commodity ct=commodityMapper.findById(coupons.getRangeItemCourse());
									if(null!=ct){
										lib.setRangeItemCourse(ct.getId());
									}
								}
								break;
							}
						}
					}
					lib.setDelFlag(0);
					arr.add(lib);
					//companyCouponsLibMapper.insert(lib);
				}
			}
			if(null!=arr && arr.size()>0){
				List<String> codeArr=RandomCodesUtil.getCodes(RandomCodesUtil.CODETYPE_COUPON, arr.size());
				if(null!=codeArr){
					for(int i=0;i<codeArr.size();i++){
						arr.get(i).setCode(coupons.getPromoCodePrefix()+codeArr.get(i));
						companyCouponsLibMapper.insert(arr.get(i));
					}
				}
			}
		}else{
			for(int i=0;i<coupons.getTotalNum();i++){
				CompanyCouponsLib lib=new CompanyCouponsLib();
				log.info("生成优惠券开始");
//				lib.setCode(coupons.getPromoCodePrefix()+genreateCode());
//				log.info("优惠券code："+genreateCode()+"-=========");
				lib.setTimeLimitFrom(coupons.getTimeLimitFrom());
				lib.setTimeLimitTo(coupons.getTimeLimitTo());
				lib.setStatus(0);
				lib.setPatchId(coupons.getId());
				//lib.setUserId(userId);
				lib.setCreater(userId);
				//优惠方式
				lib.setUseWay(coupons.getUseWay()+"");
				if(null!=coupons.getUseWay()){
					if(coupons.getUseWay()==0){
						lib.setInsCashNum(coupons.getInsCashNum());
					}else if(coupons.getUseWay()==1){
						lib.setOverNum(coupons.getOverNum());
						lib.setOverCutNum(coupons.getOverCutNum());
					}else if(coupons.getUseWay()==2){
						lib.setDiscountNum(coupons.getDiscountNum());
					}
				}
				lib.setDescription(coupons.getDescription());
				lib.setCommodityType(coupons.getCommodityType());
				//使用范围
				lib.setUseRange(coupons.getUseRange());
				if(null!=coupons.getUseRange()){
					if(coupons.getUseRange()==1){
						switch(coupons.getCommodityType()){
						case 0:
							lib.setRangeItemOne(coupons.getRangeItemOne());
							if(null!=coupons.getRangeItemSecond()){
								lib.setRangeItemSecond(coupons.getRangeItemSecond());
							}
							break;
						case 1:
							lib.setRangeItemPackageCategory(coupons.getRangeItemPackageCategory());
							break;
						}
					}else if(coupons.getUseRange()==2){
						switch(coupons.getCommodityType()){
						case 0:
							lib.setRangeItemOne(coupons.getRangeItemOne());
							if(null!=coupons.getRangeItemSecond()){
								lib.setRangeItemSecond(coupons.getRangeItemSecond());
							}
							if(null!=coupons.getRangeItemCourse()){
								Commodity ct=commodityMapper.findById(coupons.getRangeItemCourse());
								if(null!=ct){
									lib.setRangeItemOne(ct.getItemOneId());
									lib.setRangeItemSecond(ct.getItemSecondId());
									lib.setRangeItemCourse(ct.getId());
								}
							}
							break;
						case 1:
							lib.setRangeItemPackageCategory(coupons.getRangeItemPackageCategory());
							if(null!=coupons.getRangeItemCourse()){
								Commodity ct=commodityMapper.findById(coupons.getRangeItemCourse());
								if(null!=ct){
									lib.setRangeItemCourse(ct.getId());
								}
							}
							break;
						}
					}
				}
				lib.setDelFlag(0);
				arr.add(lib);
				//companyCouponsLibMapper.insert(lib);
			}
			if(null!=arr && arr.size()>0){
				List<String> codeArr=RandomCodesUtil.getCodes(RandomCodesUtil.CODETYPE_COUPON, coupons.getTotalNum());
				if(null!=codeArr){
					for(int i=0;i<codeArr.size();i++){
						arr.get(i).setCode(coupons.getPromoCodePrefix()+codeArr.get(i));
						companyCouponsLibMapper.insert(arr.get(i));
					}
				}
			}
			coupons.setStatus(1+"");
			coupons.setSendDate(new Date());
			companyCouponsPatchMapper.update(coupons);
		}
		return "success";
	}
	
	@Override
	public PageFinder<CompanyCouponsPatchVo> queryCouponsPatchListByCompanyId(CompanyCouponsPatchVo search) {
		List<CompanyCouponsPatchVo> data = companyCouponsPatchMapper.queryCouponsPatchListByCompanyId(search);
		int rowCount=companyCouponsPatchMapper.queryCouponsPatchListCountByCompanyId(search);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		for (CompanyCouponsPatchVo companyCouponsPatchVo : data) {
			if(null!=companyCouponsPatchVo.getTimeLimitTo()){
				String today=sdf.format(new Date());
				String temp=sdf.format(companyCouponsPatchVo.getTimeLimitTo());
				try {
					Date todayDate = sdf.parse(today);
					Date tempDate = sdf.parse(temp);
					if(todayDate.after(tempDate) && "0".equals(companyCouponsPatchVo.getStatus())){
						companyCouponsPatchVo.setStatus("2");
						
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(companyCouponsPatchVo.getCommodityType()!=null && companyCouponsPatchVo.getCommodityType() == 1 ){
				if(companyCouponsPatchVo.getUseRange() == 1){
					companyCouponsPatchVo.setRangeItemPackageCategory(ClassPackageUtil.getClassPackageUrl(companyCouponsPatchVo.getCompanyId(), companyCouponsPatchVo.getRangeItemPackageCategory()));
				}else if(companyCouponsPatchVo.getUseRange() == 2){
					companyCouponsPatchVo.setRangeItemPackageCategory(ClassPackageUtil.getClassPackageUrl(companyCouponsPatchVo.getCompanyId(), companyCouponsPatchVo.getRangeItemPackageCategory())+"-" +companyCouponsPatchVo.getRangeItemCourseName());
				}
			}
		}
		PageFinder<CompanyCouponsPatchVo> pageFinder=new PageFinder<CompanyCouponsPatchVo>(search.getPage(),search.getPageSize(), rowCount, data);
		return pageFinder;
	}

	@Override
	public CompanyCouponsPatchVo queryCouponsPatchById(Integer id) {
		CompanyCouponsPatchVo data = companyCouponsPatchMapper.queryCouponsPatchById(id);
		if(data.getCommodityType()!=null && data.getCommodityType() == 1 ){
			if(data.getUseRange() == 1){
				data.setRangeItemPackageCategory(ClassPackageUtil.getClassPackageUrl(data.getCompanyId(), data.getRangeItemPackageCategory()));
			}else if(data.getUseRange() == 2){
				data.setRangeItemPackageCategory(ClassPackageUtil.getClassPackageUrl(data.getCompanyId(), data.getRangeItemPackageCategory())+"-" +data.getRangeItemCourseName());
			}
		}
		return data;
	}

	@Override
	public List<CompanyCouponsPatch> queryCouponseBycondtion(
			CompanyCouponsPatch search) {
		// TODO Auto-generated method stub
		return companyCouponsPatchMapper.queryCouponsByCondition(search);
	}

	@Override
	public boolean sendCouponsPatch(CompanyCouponsPatch ccp, List<CompanyCouponsLib> cclList,List<String> userslist) {
		
		int sendCount=0;
//		int count_all=ccp.getTotalNum();
//		if(count_all<userslist.size()){
//			return false;
//		}
		for (int i=0;i<userslist.size();i++) {
			UsersFrontMyCoupons usersFrontMyCoupons=new UsersFrontMyCoupons();
			usersFrontMyCoupons.setCouponsCode(cclList.get(i).getCode());
			usersFrontMyCoupons.setStatus(0);
			usersFrontMyCoupons.setUserId(Integer.parseInt(userslist.get(i)));
			usersFrontMyCouponsMapper.insert(usersFrontMyCoupons);
			//更新发送人
			cclList.get(i).setUserId(Integer.parseInt(userslist.get(i)));
			companyCouponsLibMapper.update(cclList.get(i));
			
			sendCount++;
		}
		ccp.setTotalNum(cclList.size());
		ccp.setUsedNum(sendCount);
		ccp.setRemainNum(ccp.getTotalNum()-sendCount);
		ccp.setStatus("1");
		ccp.setSendDate(new Date());
		companyCouponsPatchMapper.update(ccp);
		
		return true;
	}

	@Override
	public boolean sendCouponsMessage(CompanyCouponsPatch ccp, String[] userIds,Integer createrId,Integer schoolId,Integer companyId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (String userId : userIds) {
			UsersFront uf=usersFrontMapper.findById(Integer.parseInt(userId));
			CompanyStudentMessage companyStudentMessage=new CompanyStudentMessage();
			companyStudentMessage.setTitle("赠送您一张优惠券");
			try {
				Student stu = studentMapper.findByUserId(Integer.parseInt(userId));
				String stuName=(null!=stu && null!=stu.getName() && !"".equals(stu.getName()))?stu.getName():(null!=uf.getUsername() && !"".equals(uf.getUsername())?uf.getUsername():uf.getMobile());
				//couponinfo
				String useWay="";
				if(ccp.getUseWay()==0){
					useWay="抵现"+ccp.getInsCashNum()+"元";
				}
				if(ccp.getUseWay()==1){
					useWay="满"+ccp.getOverNum()+"元减"+ccp.getOverCutNum()+"元";
				}
				if(ccp.getUseWay()==2){
					useWay="打"+ccp.getDiscountNum()+"折";
				}
				// 0-全部课程  1-学科 2-课程
				if(ccp.getCommodityType()!=null && ccp.getCommodityType()==1){
					if(ccp.getUseRange()==0){
						companyStudentMessage.setContent(changeContentsCode(URLDecoder.decode(ccp.getNoticMsgContents(), "UTF-8")).replace("【username】",stuName).replace("【coursename】","全部课程包").replace("【startdate】",sdf.format(ccp.getTimeLimitFrom())).replace("【enddate】",sdf.format(ccp.getTimeLimitTo())).replace("【couponinfo】", useWay));
					}else if(ccp.getUseRange()==1){
						companyStudentMessage.setContent(changeContentsCode(URLDecoder.decode(ccp.getNoticMsgContents(), "UTF-8")).replace("【username】",stuName).replace("【coursename】",ClassPackageUtil.getClassPackageUrl(companyId, ccp.getRangeItemPackageCategory())+"课程包").replace("【startdate】",sdf.format(ccp.getTimeLimitFrom())).replace("【enddate】",sdf.format(ccp.getTimeLimitTo())).replace("【couponinfo】", useWay));
					}else if(ccp.getUseRange()==2){
						companyStudentMessage.setContent(changeContentsCode(URLDecoder.decode(ccp.getNoticMsgContents(), "UTF-8")).replace("【username】",stuName).replace("【coursename】",ClassPackageUtil.getClassPackageUrl(companyId, ccp.getRangeItemPackageCategory())+"-"+ClassPackageUtil.getClassPackageName(ccp.getRangeItemCourse(), ClassPackageUtil.IDTYPE_COMMODITYID)+"课程包").replace("【startdate】",sdf.format(ccp.getTimeLimitFrom())).replace("【enddate】",sdf.format(ccp.getTimeLimitTo())).replace("【couponinfo】", useWay));
					}
				}else{
					if(ccp.getUseRange()==0){
						companyStudentMessage.setContent(changeContentsCode(URLDecoder.decode(ccp.getNoticMsgContents(), "UTF-8")).replace("【username】",stuName).replace("【coursename】","全部课程").replace("【startdate】",sdf.format(ccp.getTimeLimitFrom())).replace("【enddate】",sdf.format(ccp.getTimeLimitTo())).replace("【couponinfo】", useWay));
					}else if(ccp.getUseRange()==1){
						SysConfigItem sysOne=sysConfigItemMapper.findById(ccp.getRangeItemOne());
						SysConfigItem sysTwo=(null!=ccp.getRangeItemSecond()?sysConfigItemMapper.findById(ccp.getRangeItemSecond()):null);
						String itemOne=(null!=sysOne)?sysOne.getItemName():"";
						String itemSecond=(null!=sysTwo)?"-"+sysTwo.getItemName():"";
						
						companyStudentMessage.setContent(changeContentsCode(URLDecoder.decode(ccp.getNoticMsgContents(), "UTF-8")).replace("【username】",stuName).replace("【coursename】",itemOne+itemSecond+"课程").replace("【startdate】",sdf.format(ccp.getTimeLimitFrom())).replace("【enddate】",sdf.format(ccp.getTimeLimitTo())).replace("【couponinfo】", useWay));
					}else if(ccp.getUseRange()==2){
						SysConfigItem sysOne=sysConfigItemMapper.findById(ccp.getRangeItemOne());
						SysConfigItem sysTwo=(null!=ccp.getRangeItemSecond()?sysConfigItemMapper.findById(ccp.getRangeItemSecond()):null);
						String itemOne=(null!=sysOne)?sysOne.getItemName():"";
						String itemSecond=(null!=sysTwo)?"-"+sysTwo.getItemName():"";
						
						String commodityName=commodityMapper.findById(ccp.getRangeItemCourse()).getName();
						companyStudentMessage.setContent(changeContentsCode(URLDecoder.decode(ccp.getNoticMsgContents(), "UTF-8")).replace("【username】",stuName).replace("【coursename】",itemOne+itemSecond+"-"+ commodityName+"课程").replace("【startdate】",sdf.format(ccp.getTimeLimitFrom())).replace("【enddate】",sdf.format(ccp.getTimeLimitTo())).replace("【couponinfo】", useWay));
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			companyStudentMessage.setMessageType("STUDENT_MESSAGE_OTHER");
			companyStudentMessage.setMessageMethod("STUDENT_MESSAGE_WEB");
			companyStudentMessage.setCreator(createrId);
			companyStudentMessage.setCreateTime(new Date());
			companyStudentMessage.setSendNum(1);
			companyStudentMessage.setMessageStatus("STUDENT_MESSAGE_FINISH");
			companyStudentMessage.setSchoolId(schoolId);
			companyStudentMessage.setCompanyId(companyId);
			companyStudentMessageMapper.insert(companyStudentMessage);
	
			
			UserMessage userMessage=new UserMessage();
			userMessage.setMessageId(companyStudentMessage.getId());
			userMessage.setUserId(Integer.parseInt(userId));
			userMessage.setReadFlag(0);
			companyStudentMessageMapper.insertUserMessage(userMessage);
		}
		return true;
	}

	@Override
	public List<UsersFront> queryAllUsers(Integer companyId) {
		return companyCouponsPatchMapper.queryAllUsers(companyId);
	}

	@Override
	public List<UsersFront> queryMemberUsers(Map<String, String> map) {
		return companyCouponsPatchMapper.queryMemberUsers(map);
	}

	@Override
	public List<UsersFront> queryAlreadyBuyClassUsers(Integer companyId) {
		return companyCouponsPatchMapper.queryAlreadyBuyClassUsers(companyId);
	}

	@Override
	public String sendCouponsMobileMessage(CompanyCouponsPatch ccp ,String userId, Integer createrId, Integer schoolId,
			Integer companyId) {
		UsersFront uf=usersFrontMapper.findById(Integer.parseInt(userId));
		CompanyStudentMessage companyStudentMessage=new CompanyStudentMessage();
		companyStudentMessage.setTitle("赠送您一张优惠券");
		Student stu = studentMapper.findByUserId(Integer.parseInt(userId));
		String stuName=(null!=stu && null!=stu.getName() && !"".equals(stu.getName()))?stu.getName():(null!=uf.getUsername() && !"".equals(uf.getUsername())?uf.getUsername():uf.getMobile());
		companyStudentMessage.setContent(ccp.getNoticSmsContents().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&quot;").replace("【username】",stuName)+ "【在线网校】");
		companyStudentMessage.setMessageType("STUDENT_MESSAGE_OTHER");
		companyStudentMessage.setMessageMethod("STUDENT_MESSAGE_MOBILE");
		companyStudentMessage.setCreator(createrId);
		companyStudentMessage.setCreateTime(new Date());
		companyStudentMessage.setSendNum(1);
		companyStudentMessage.setMessageStatus("STUDENT_MESSAGE_COMMIT");
		companyStudentMessage.setSchoolId(schoolId);
		companyStudentMessage.setCompanyId(companyId);
		companyStudentMessageMapper.insert(companyStudentMessage);
		return ccp.getNoticSmsContents().replace("【username】",stuName)+ "【在线网校】"+"#####"+companyStudentMessage.getId();
	}

	@Override
	public CompanyCouponsPatch findCompanyCouponsPatchBySearch(
			CompanyCouponsPatch search) {
		// TODO Auto-generated method stub
		return companyCouponsPatchMapper.findCompanyCouponsPatchBySearch(search);
	}
	
	
	/**
	 * 
	 * Class Name: CompanyCouponsPatchServiceImpl.java
	 * @Description: 变更，修复乱码
	 * @author dongshuai
	 * @date 2016年8月12日 下午4:06:20
	 * @modifier
	 * @modify-date 2016年8月12日 下午4:06:20
	 * @version 1.0
	 * @param contents
	 * @return
	 */
	public String changeContentsCode(String contents){
//		return contents.replaceAll("&bdquo;", "a")
//				.replaceAll("&mdash;", "e")
//				.replaceAll("&Dagger;", "h")
//				.replaceAll("&rarr;", "i")
//				.replaceAll("&zwj;", "m")
//				.replaceAll("&sbquo;", "o")
//				.replaceAll("&ndash;", "p")
//				.replaceAll("&lsquo;", "r")
//				.replaceAll("&dagger;", "t")
//				.replaceAll("&larr;", "w");
//      2016-08-18 富文本版本已还原,则不用更改
		return contents;
	}
}
