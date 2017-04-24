package com.yuxin.wx.company.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.company.ICompanyMemberConfigService;
import com.yuxin.wx.company.mapper.CompanyCashFlowMapper;
import com.yuxin.wx.company.mapper.CompanyMemberConfigMapper;
import com.yuxin.wx.company.mapper.CompanyMemberLevelDetailMapper;
import com.yuxin.wx.model.company.CompanyCashFlow;
import com.yuxin.wx.model.company.CompanyMemberConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelDetail;
import com.yuxin.wx.model.pay.PayOrder;
import com.yuxin.wx.model.student.Student;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.pay.mapper.PayOrderMapper;
import com.yuxin.wx.student.mapper.StudentMapper;
import com.yuxin.wx.student.mapper.StudentPayMasterMapper;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import com.yuxin.wx.util.DateUtil;
import com.yuxin.wx.vo.company.MemberLevelVo;
import com.yuxin.wx.vo.company.VipDateVo;


/**
 * Service Implementation:CompanyMemberConfig
 * @author chopin
 * @date 2016-5-17
 */
@Service
@Transactional
public class CompanyMemberConfigServiceImpl extends BaseServiceImpl implements ICompanyMemberConfigService {

	@Autowired
	private CompanyMemberConfigMapper companyMemberConfigMapper;
	@Autowired
	private UsersFrontMapper usersFrontMapper;
	@Autowired
	private CompanyMemberLevelDetailMapper companyMemberLevelDetailMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private PayOrderMapper payOrderMapper;
	@Autowired
	private StudentPayMasterMapper studentPayMasterMapper;
	@Autowired
	private CompanyCashFlowMapper companyCashFlowMapper;
	/**
	 * 
	* @Title: saveCompanyMemberConfig
	* @Description: 新增CompanyMemberConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void insert(CompanyMemberConfig entity){
		companyMemberConfigMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveCompanyMemberConfig 
	* @Description: 批量新增CompanyMemberConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<CompanyMemberConfig> entity){
		companyMemberConfigMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateCompanyMemberConfig 
	* @Description: 编辑CompanyMemberConfig
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void update(CompanyMemberConfig entity){
		companyMemberConfigMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteCompanyMemberConfigById 
	* @Description: 根据id删除CompanyMemberConfig
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	 @Override
	public void deleteCompanyMemberConfigById(Integer id){
		companyMemberConfigMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteCompanyMemberConfigByIds 
	* @Description: 根据id批量删除CompanyMemberConfig
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public void deleteCompanyMemberConfigByIds(Integer[] ids){
		companyMemberConfigMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findCompanyMemberConfigById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public CompanyMemberConfig findCompanyMemberConfigById(Integer id){
		return companyMemberConfigMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findCompanyMemberConfigByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyMemberConfig>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-5-17
	* @user by chopin
	 */
	@Override
	public List<CompanyMemberConfig> findCompanyMemberConfigByPage(CompanyMemberConfig search){
		return companyMemberConfigMapper.page(search);
	}

	@Override
	public CompanyMemberConfig queryMemberSets(CompanyMemberConfig search) {
		// TODO Auto-generated method stub
		return companyMemberConfigMapper.queryMemberSets(search);
	};
	/**
	 * Class Name: ICompanyMemberConfigService.java
	 * @Description: 根据公司编号查询数据库中是否有该公司的会员设置
	 * @author xukaiqiang
	 * @date 2016年5月23日 下午7:09:49
	 * @modifier
	 * @modify-date 2016年5月23日 下午7:09:49
	 * @version 1.0
	 * @param companyId
	 * @return
	 */
	public CompanyMemberConfig findCompanyMemberConfigByCompanyId(Integer companyId){
		return companyMemberConfigMapper.findCompanyMemberConfigByCompanyId(companyId);
	}
	@Override
	public boolean saveMemberForVip(String username,Integer companyId,Integer schoolId,String stuMobile,String memberId,String memberLevel,
			String buyLength,String buyName,String pos,String cash,String cheque,String remit,String needPay,Integer userId,String detailId,String detailOpenWay) {
		try {
			if("0".equals(detailOpenWay)){
				Double realPayPrice=0.00;
				if(!"".equals(pos) && null!=pos && Double.parseDouble(pos)>=0){
					realPayPrice+=Double.parseDouble(pos);
				}
				if(!"".equals(cash) && null!=cash && Double.parseDouble(cash)>=0){
					realPayPrice+=Double.parseDouble(cash);
				}
				if(!"".equals(cheque) && null!=cheque && Double.parseDouble(cheque)>=0){
					realPayPrice+=Double.parseDouble(cheque);
				}
				if(!"".equals(remit) && null!=remit && Double.parseDouble(remit)>=0){
					realPayPrice+=Double.parseDouble(remit);
				}
			
				//添加会员
				UsersFront uf=new UsersFront();
				if(!"".equals(stuMobile) && null!=stuMobile){
					uf.setCompanyId(companyId);
					uf.setMobile(stuMobile);
					uf=usersFrontMapper.findUsersFrontByMobile(uf);
					if(uf==null && !"".equals(username)&& null!=username){
						uf=new UsersFront();
						uf.setCompanyId(companyId);
						uf.setUsername(username);
						uf=usersFrontMapper.findUsersFrontByUsername(uf);
					}
				}else if(!"".equals(username) && null!=username){
					uf.setCompanyId(companyId);
					uf.setUsername(username);
					uf=usersFrontMapper.findUsersFrontByUsername(uf);
				}
				uf.setMemberId(Integer.parseInt(memberId));
				uf.setMemberLevel(memberLevel);
				uf.setMemberBuyLength(Integer.parseInt(buyLength));
				uf.setMemberStatus(1);
				if(Integer.parseInt(buyLength)>0){
					uf.setMemberEndTime(DateUtil.addMonthsToDate(new Date(),Integer.parseInt(buyLength)));//* 需计算到期时间
				}
				usersFrontMapper.update(uf);
	
				//生成订单1.pay_order 
				PayOrder payOrder=new PayOrder();
				payOrder.setUserId(uf.getId());
				payOrder.setOrderNum(new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).append(RandomUtils.nextInt(1000)).toString());//*生成时间戳
				payOrder.setOrderTime(new Date());
				payOrder.setPayTime(new Date());
				payOrder.setCommodityName("购买会员");//*商品名称
				payOrder.setCommodityId(Integer.parseInt(memberId));//*商品id
				payOrder.setOriginalPrice(companyMemberLevelDetailMapper.findById(Integer.parseInt(detailId)).getPrice());//*原始价格
				payOrder.setPayPrice(realPayPrice);
				String payType="";
				if(Double.parseDouble(pos)>=0){
					payType+="PAY_METHOD_POS,";
				}
				if(Double.parseDouble(cash)>=0){
					payType+="PAY_METHOD_CASH,";
				}
				if(Double.parseDouble(cheque)>=0){
					payType+="PAY_METHOD_CHECK,";
				}
				if(Double.parseDouble(remit)>=0){
					payType+="PAY_METHOD_TRANSFE,";
				}
				if(payType.length()>=1){
					payOrder.setPayType(payType.substring(0, payType.length()-1));//*支付类型 **
				}
				payOrder.setPayStatus("PAY_SUCCESS");//*支付状态
				payOrder.setCompanyId(companyId);
				payOrder.setCollectionAmount(realPayPrice);//?*收款金额
				payOrder.setCollectionTime(new Date());//?*收款时间
				payOrder.setCommdityType(memberLevel);//*商品类型
				payOrder.setMemberId(Integer.parseInt(memberId));//*会员Id
				CompanyMemberLevelDetail cmld = companyMemberLevelDetailMapper.findById(Integer.parseInt(detailId));
				if(cmld!=null){
					if(!"".equals(cmld.getPrice()) && null!=cmld.getPrice()){
						payOrder.setOriginalPrice(cmld.getPrice());
					}
				}
				payOrder.setSchoolId(schoolId);
				payOrder.setCommdityType("MEMBER");
				payOrder.setMemberLength(Integer.parseInt(buyLength));
				payOrderMapper.insert(payOrder);
				
				//生成订单2.student_pay_master 
				Student stu=new Student();
				if(!"".equals(stuMobile) && null!=stuMobile){
					stu.setCompanyId(companyId);
					stu.setMobile(stuMobile);
					stu=studentMapper.queryByMobile(stu);
					if(null==stu && null!=username && !"".equals(username)){
						stu=new Student();
						stu.setCompanyId(companyId);
						stu.setUsername(username);
						stu=studentMapper.queryByUsername(stu);
					}
				}else{
					stu.setCompanyId(companyId);
					stu.setUsername(username);
					stu=studentMapper.queryByUsername(stu);
				}
			
				StudentPayMaster studentPayMaster=new StudentPayMaster();
				studentPayMaster.setApplyTime(new Date());
				studentPayMaster.setStuId(stu.getUserId());
				studentPayMaster.setPayStatusCode("ORDER_FINISHED");//*订单状态
				studentPayMaster.setBizStatusCode("ORDER_BIZ_BUY_MEMBER");//*订单业务类型
				studentPayMaster.setApplyChannelCode("CHANNEL_OFFLINE");
				studentPayMaster.setTotalAmount(realPayPrice);
				studentPayMaster.setCreateTime(new Date());
				studentPayMaster.setCreator(userId.toString());
				studentPayMaster.setDeleteFlag(0);
				studentPayMaster.setCompanyId(companyId);
				studentPayMaster.setCommodityType("MEMBER");//*商品类型
				studentPayMaster.setMemberId(Integer.parseInt(memberId));//*会员Id
				studentPayMaster.setSchoolId(schoolId);
				studentPayMaster.setPaymentTypeCode("PAY_TYPE_ALL");
				studentPayMaster.setPayOrderId(payOrder.getId());
				studentPayMaster.setTrainingFee(0.00);
				studentPayMaster.setMemberLength(Integer.parseInt(buyLength));
				studentPayMasterMapper.insert(studentPayMaster);
				
				//生成流水3.company_cash_flow
				CompanyCashFlow companyCashFlow=new CompanyCashFlow();
				companyCashFlow.setTradeReason("BUY_MEMBER");//购买会员
				companyCashFlow.setTradeSource("PAY_OFFLINE");//资金来源
				if(payType.length()>=1){
					companyCashFlow.setTradeWay(payType.substring(0, payType.length()-1));//交易渠道
				}
				companyCashFlow.setTradeAmount(realPayPrice);
				companyCashFlow.setTradeDate(new Date());
				companyCashFlow.setUserId(uf.getId());
				companyCashFlow.setCompanyId(companyId);
				companyCashFlow.setTradeType("TRADE_IN");
				companyCashFlow.setStuId(stu.getUserId());
				companyCashFlow.setSchoolId(schoolId);
				companyCashFlow.setPayMasterId(studentPayMaster.getId());
				companyCashFlowMapper.insert(companyCashFlow);
			}else if("1".equals(detailOpenWay)){
				//添加会员
				UsersFront uf=new UsersFront();
				if(!"".equals(stuMobile) && null!=stuMobile){
					uf.setCompanyId(companyId);
					uf.setMobile(stuMobile);
					uf=usersFrontMapper.findUsersFrontByMobile(uf);
					if(uf==null && !"".equals(username)&& null!=username){
						uf=new UsersFront();
						uf.setCompanyId(companyId);
						uf.setUsername(username);
						uf=usersFrontMapper.findUsersFrontByUsername(uf);
					}
				}else if(!"".equals(username) && null!=username){
					uf.setCompanyId(companyId);
					uf.setUsername(username);
					uf=usersFrontMapper.findUsersFrontByUsername(uf);
				}
				uf.setMemberId(Integer.parseInt(memberId));
				uf.setMemberLevel(memberLevel);
				uf.setMemberBuyLength(-1);
				uf.setMemberStatus(1);
				usersFrontMapper.update(uf);

				//生成订单1.pay_order 
				PayOrder payOrder=new PayOrder();
				payOrder.setUserId(uf.getId());
				payOrder.setOrderNum(new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).append(RandomUtils.nextInt(1000)).toString());//*生成时间戳
				payOrder.setOrderTime(new Date());
				payOrder.setPayTime(new Date());
				payOrder.setCommodityName("购买会员");//*商品名称
				payOrder.setCommodityId(Integer.parseInt(memberId));//*商品id
				payOrder.setOriginalPrice(0.00);//*原始价格
				payOrder.setPayPrice(0.00);
				String payType="";
				payOrder.setPayType(payType);//*支付类型 **
				payOrder.setPayStatus("PAY_SUCCESS");//*支付状态
				payOrder.setCompanyId(companyId);
				payOrder.setCollectionAmount(0.00);//?*收款金额
				payOrder.setCollectionTime(new Date());//?*收款时间
				payOrder.setCommdityType(memberLevel);//*商品类型
				payOrder.setMemberId(Integer.parseInt(memberId));//*会员Id
				payOrder.setOriginalPrice(0.00);
				payOrder.setSchoolId(schoolId);
				payOrder.setCommdityType("MEMBER");
				payOrder.setMemberLength(-1);
				payOrderMapper.insert(payOrder);
				
				//生成订单2.student_pay_master 
				Student stu=new Student();
				if(!"".equals(stuMobile) && null!=stuMobile){
					stu.setCompanyId(companyId);
					stu.setMobile(stuMobile);
					stu=studentMapper.queryByMobile(stu);
					if(null==stu && null!=username && !"".equals(username)){
						stu=new Student();
						stu.setCompanyId(companyId);
						stu.setUsername(username);
						stu=studentMapper.queryByUsername(stu);
					}
				}else{
					stu.setCompanyId(companyId);
					stu.setUsername(username);
					stu=studentMapper.queryByUsername(stu);
				}
				StudentPayMaster studentPayMaster=new StudentPayMaster();
				studentPayMaster.setApplyTime(new Date());
				studentPayMaster.setStuId(stu.getUserId());
				studentPayMaster.setPayStatusCode("ORDER_FINISHED");//*订单状态
				studentPayMaster.setBizStatusCode("ORDER_BIZ_BUY_MEMBER");//*订单业务类型
				studentPayMaster.setApplyChannelCode("CHANNEL_OFFLINE");
				studentPayMaster.setTotalAmount(0.00);
				studentPayMaster.setCreateTime(new Date());
				studentPayMaster.setCreator(userId.toString());
				studentPayMaster.setDeleteFlag(0);
				studentPayMaster.setCompanyId(companyId);
				studentPayMaster.setCommodityType("MEMBER");//*商品类型
				studentPayMaster.setMemberId(Integer.parseInt(memberId));//*会员Id
				studentPayMaster.setSchoolId(schoolId);
				studentPayMaster.setPaymentTypeCode("PAY_TYPE_ALL");
				studentPayMaster.setPayOrderId(payOrder.getId());
				studentPayMaster.setTrainingFee(0.00);
				studentPayMaster.setMemberLength(-1);
				studentPayMasterMapper.insert(studentPayMaster);
				
				//生成流水3.company_cash_flow
				CompanyCashFlow companyCashFlow=new CompanyCashFlow();
				companyCashFlow.setTradeReason("BUY_MEMBER");//购买会员
				companyCashFlow.setTradeSource("PAY_OFFLINE");//资金来源
				companyCashFlow.setTradeWay(payType);//交易渠道
				companyCashFlow.setTradeAmount(0.00);
				companyCashFlow.setTradeDate(new Date());
				companyCashFlow.setUserId(uf.getId());
				companyCashFlow.setCompanyId(companyId);
				companyCashFlow.setTradeType("TRADE_IN");
				companyCashFlow.setStuId(stu.getUserId());
				companyCashFlow.setSchoolId(schoolId);
				companyCashFlow.setPayMasterId(studentPayMaster.getId());
				companyCashFlowMapper.insert(companyCashFlow);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public boolean saveMemberForZeroVip(String username,Integer companyId,Integer schoolId,String stuMobile,String memberId,String memberLevel,
			String buyLength,String buyName,String pos,String cash,String cheque,String remit,String needPay,Integer userId,String detailId) {
		try {
			//添加会员
			UsersFront uf=new UsersFront();
			if(!"".equals(stuMobile) && null!=stuMobile){
				uf.setCompanyId(companyId);
				uf.setMobile(stuMobile);
				uf=usersFrontMapper.findUsersFrontByMobile(uf);
				if(uf==null && !"".equals(username)&& null!=username){
					uf=new UsersFront();
					uf.setCompanyId(companyId);
					uf.setUsername(username);
					uf=usersFrontMapper.findUsersFrontByUsername(uf);
				}
			}else if(!"".equals(username) && null!=username){
				uf.setCompanyId(companyId);
				uf.setUsername(username);
				uf=usersFrontMapper.findUsersFrontByUsername(uf);
			}
			uf.setMemberId(Integer.parseInt(memberId));
			uf.setMemberLevel(memberLevel);
			uf.setMemberBuyLength(-1);
			uf.setMemberStatus(1);
			usersFrontMapper.update(uf);

			//生成订单1.pay_order 
			PayOrder payOrder=new PayOrder();
			payOrder.setUserId(uf.getId());
			payOrder.setOrderNum(new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).append(RandomUtils.nextInt(1000)).toString());//*生成时间戳
			payOrder.setOrderTime(new Date());
			payOrder.setPayTime(new Date());
			payOrder.setCommodityName("购买会员");//*商品名称
			payOrder.setCommodityId(Integer.parseInt(memberId));//*商品id
			payOrder.setOriginalPrice(0.00);//*原始价格
			payOrder.setPayPrice(0.00);
			String payType="";
			payOrder.setPayType(payType);//*支付类型 **
			payOrder.setPayStatus("PAY_SUCCESS");//*支付状态
			payOrder.setCompanyId(companyId);
			payOrder.setCollectionAmount(0.00);//?*收款金额
			payOrder.setCollectionTime(new Date());//?*收款时间
			payOrder.setCommdityType(memberLevel);//*商品类型
			payOrder.setMemberId(Integer.parseInt(memberId));//*会员Id
			payOrder.setOriginalPrice(0.00);
			payOrder.setSchoolId(schoolId);
			payOrder.setCommdityType("MEMBER");
			payOrder.setMemberLength(-1);
			payOrderMapper.insert(payOrder);
			
			//生成订单2.student_pay_master 
			Student stu=new Student();
			if(!"".equals(stuMobile) && null!=stuMobile){
				stu.setCompanyId(companyId);
				stu.setMobile(stuMobile);
				stu=studentMapper.queryByMobile(stu);
				if(null==stu && null!=username && !"".equals(username)){
					stu=new Student();
					stu.setCompanyId(companyId);
					stu.setUsername(username);
					stu=studentMapper.queryByUsername(stu);
				}
			}else{
				stu.setCompanyId(companyId);
				stu.setUsername(username);
				stu=studentMapper.queryByUsername(stu);
			}
			StudentPayMaster studentPayMaster=new StudentPayMaster();
			studentPayMaster.setApplyTime(new Date());
			studentPayMaster.setStuId(stu.getUserId());
			studentPayMaster.setPayStatusCode("ORDER_FINISHED");//*订单状态
			studentPayMaster.setBizStatusCode("ORDER_BIZ_BUY_MEMBER");//*订单业务类型
			studentPayMaster.setApplyChannelCode("CHANNEL_OFFLINE");
			studentPayMaster.setTotalAmount(0.00);
			studentPayMaster.setCreateTime(new Date());
			studentPayMaster.setCreator(userId.toString());
			studentPayMaster.setDeleteFlag(0);
			studentPayMaster.setCompanyId(companyId);
			studentPayMaster.setCommodityType("MEMBER");//*商品类型
			studentPayMaster.setMemberId(Integer.parseInt(memberId));//*会员Id
			studentPayMaster.setSchoolId(schoolId);
			studentPayMaster.setPaymentTypeCode("PAY_TYPE_ALL");
			studentPayMaster.setPayOrderId(payOrder.getId());
			studentPayMaster.setTrainingFee(0.00);
			studentPayMaster.setMemberLength(-1);
			studentPayMasterMapper.insert(studentPayMaster);
			
			//生成流水3.company_cash_flow
			CompanyCashFlow companyCashFlow=new CompanyCashFlow();
			companyCashFlow.setTradeReason("BUY_MEMBER");//购买会员
			companyCashFlow.setTradeSource("PAY_OFFLINE");//资金来源
			companyCashFlow.setTradeWay(payType);//交易渠道
			companyCashFlow.setTradeAmount(0.00);
			companyCashFlow.setTradeDate(new Date());
			companyCashFlow.setUserId(uf.getId());
			companyCashFlow.setCompanyId(companyId);
			companyCashFlow.setTradeType("TRADE_IN");
			companyCashFlow.setStuId(stu.getUserId());
			companyCashFlow.setSchoolId(schoolId);
			companyCashFlow.setPayMasterId(studentPayMaster.getId());
			companyCashFlowMapper.insert(companyCashFlow);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public List<MemberLevelVo> findMemberLevelByCompanyId(
			Map<String, Object> param) {
		return companyMemberConfigMapper.findMemberLevelByCompanyId(param);
	}

	@Override
	public List<CompanyMemberConfig> findCompanyMemberConfigta() {
		// TODO Auto-generated method stub
		return companyMemberConfigMapper.findCompanyMemberConfigta();
	}
	
	@Override
	public List<VipDateVo> findVipDateByCompanyId(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return companyMemberConfigMapper.findVipDateByCompanyId(param);
	}
	
	@Override
	public CompanyMemberConfig findByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return companyMemberConfigMapper.findByCompanyId(companyId);
	}
}
