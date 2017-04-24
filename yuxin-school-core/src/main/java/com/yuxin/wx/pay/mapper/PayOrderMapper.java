package com.yuxin.wx.pay.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.pay.PayOrder;
import com.yuxin.wx.model.pay.PayOrderHistory;
import com.yuxin.wx.vo.student.PayOrderVo;
import com.yuxin.wx.vo.system.PayOrderIntegralVo;
import com.yuxin.wx.vo.system.PayOrderVipVo;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface PayOrderMapper extends BaseMapper<PayOrder> {
	
	List<PayOrderVo> findPayOrderByUserId(PayOrder order);

	Integer findCountByCommodityId(Integer id);
	
	PayOrder findPayOrderByOrderNum(String orderNum);
	
	List<PayOrder> findPayOrderByParams(Map<String, Object> params);
	
	Integer findCountByParams(Map<String, Object> params);
	
	void insertOrderHistory(PayOrderHistory history);
	
	PayOrderVo findPayOrderVoById(Integer id);
	
	List<PayOrder> findPayOrderByWhere(PayOrder search);
	
	Integer findCountByWhere(PayOrder seach);
	
	List<PayOrder> findPayOrderTotalMoney(PayOrder search);
	
	List<PayOrderIntegralVo> findIntegralPayOrderList(PayOrderIntegralVo search);
	
	Integer findIntegralPayOrderListCount(PayOrderIntegralVo search);
	
	List<PayOrderVipVo> findVipPayOrderList(PayOrderVipVo search);
	
	Integer findVipPayOrderListCount(PayOrderVipVo search);
	
	List<PayOrderVipVo> findVipPayOrderListForExport(PayOrderVipVo search);
	
	int findOrderFinishCountByUserId(Map<String,Object> map);
	
	Integer queryOrderBuyClassCountByUserId(Map<String,Object> map);
}