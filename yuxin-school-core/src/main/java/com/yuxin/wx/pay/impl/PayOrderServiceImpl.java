package com.yuxin.wx.pay.impl;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.pay.IPayOrderService;
import com.yuxin.wx.commodity.mapper.CommodityMapper;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.pay.PayOrder;
import com.yuxin.wx.model.pay.PayOrderHistory;
import com.yuxin.wx.pay.mapper.PayOrderMapper;
import com.yuxin.wx.student.mapper.StudentPayMasterMapper;
import com.yuxin.wx.util.DateUtil;
import com.yuxin.wx.vo.course.OrderManage;
import com.yuxin.wx.vo.student.PayOrderVo;
import com.yuxin.wx.vo.system.PayOrderIntegralVo;
import com.yuxin.wx.vo.system.PayOrderVipVo;

/**
 * Service Implementation:PayOrder
 * 
 * @author chopin
 * @date 2015-3-12
 */
@Service
@Transactional
public class PayOrderServiceImpl extends BaseServiceImpl implements IPayOrderService {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private StudentPayMasterMapper studentPayMasterMapper;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    Log log_pay = LogFactory.getLog("pay");

    /**
     * 
     * @Title: savePayOrder
     * @Description: 根据商品信息新增PayOrder 和 payOrderDetail
     * @return void 返回类型
     * @throws @exception
     * @date 2015-3-12
     * @user by zoull
     */
    @Override
    public void insert(PayOrder entity) {
        payOrderMapper.insert(entity);
    };

    /**
     * 
     * @Title: batchSavePayOrder
     * @Description: 批量新增PayOrder
     * @return void 返回类型
     * @throws @exception
     * @date 2015-3-12
     * @user by chopin
     */
    @Override
    public void batchInsert(List<PayOrder> entity) {
        payOrderMapper.batchInsert(entity);
    };

    /**
     * 
     * @Title: updatePayOrder
     * @Description: 编辑PayOrder
     * @return void 返回类型
     * @throws @exception
     * @date 2015-3-12
     * @user by chopin
     */
    @Override
    public void update(PayOrder entity) {
        payOrderMapper.update(entity);
    };

    /**
     * 
     * @Title: deletePayOrderById
     * @Description: 根据id删除PayOrder
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-3-12
     * @user by chopin
     */
    @Override
    public void deletePayOrderById(Integer id) {
        payOrderMapper.deleteById(id);
    };

    /**
     * 
     * @Title: deletePayOrderByIds
     * @Description: 根据id批量删除PayOrder
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-3-12
     * @user by chopin
     */
    @Override
    public void deletePayOrderByIds(Integer[] ids) {
        payOrderMapper.deleteByIds(ids);
    };

    /**
     * 
     * @Title: findPayOrderById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-3-12
     * @user by chopin
     */
    @Override
    public PayOrder findPayOrderById(Integer id) {
        return payOrderMapper.findById(id);
    };

    /**
     * 
     * @Title: findPayOrderByPage
     * @Description: 分页查询
     * @return
     * @return List<PayOrder> 返回类型
     * @throws @exception
     * @date 2015-3-12
     * @user by chopin
     */
    @Override
    public List<PayOrder> findPayOrderByPage(PayOrder search) {
        return payOrderMapper.page(search);
    }

    @Override
    public List<PayOrderVo> findPayOrderByUserId(PayOrder order) {
        return payOrderMapper.findPayOrderByUserId(order);
    }

    @Override
    public Integer findCountByCommodityId(Integer id) {
        return payOrderMapper.findCountByCommodityId(id);
    }

    @Override
    public PayOrder findPayOrderByOrderNum(String orderNum) {
        return payOrderMapper.findPayOrderByOrderNum(orderNum);
    }

    @Override
    public List<PayOrder> findPayOrderByParams(Map<String, Object> params) {
        // 将String类型，转换为Date类型
        if (params.get("inpstart") != null && params.get("inpstart") != "") {
            String startDay = (String) params.get("inpstart");
            Date startDate = null;
            try {
                startDate = format.parse(startDay);
            } catch (ParseException e) {
                log_pay.error("格式化订单查询开始日期格式出错", e);
                e.printStackTrace();
            }
            params.put("inpstart", startDate);
        }
        if (params.get("inpend") != null && params.get("inpend") != "") {
            String endDay = (String) params.get("inpend");
            Date endDate = null;
            try {
                endDate = format.parse(endDay);
                endDate = DateUtil.addDate(endDate, 1);
            } catch (ParseException e) {
                e.printStackTrace();
                log_pay.error("格式化订单查询结束日期格式出错");
            }
            params.put("inpend", endDate);
        }
        return payOrderMapper.findPayOrderByParams(params);
    }

    @Override
    public Integer findCountByParams(Map<String, Object> params) {
        return payOrderMapper.findCountByParams(params);
    }

    @Override
    public void insertOrderHistory(PayOrderHistory history) {
        payOrderMapper.insertOrderHistory(history);

    }

    @Override
    public PayOrderVo findPayOrderVoById(Integer id) {
        // TODO Auto-generated method stub
        return payOrderMapper.findPayOrderVoById(id);
    }

    @Override
    public Map findPayOrderTotalMoney(OrderManage search) {
        Double totalMoney = 0.00;
        Double payMoney = 0.00;
        Double notPayMoney = 0.00;
        List<OrderManage> data = studentPayMasterMapper.queryClassPackageTotalOrder(search);
        for (OrderManage order : data) {
            if (null != order) {
                if (null != order.getPayPrice()) {
                    totalMoney += order.getPayPrice();
                }
                if (null != order.getPayPrice()) {
                    payMoney += order.getPayPrice();
                }
            }
        }
        notPayMoney = totalMoney - payMoney;
        DecimalFormat df = new DecimalFormat("######0.00");
        Map map = new HashMap();
        map.put("totalMoney", df.format(totalMoney));
        map.put("payMoney", df.format(payMoney));
        map.put("notPayMoney", df.format(notPayMoney));
        return map;
    }

    @Override
    public PageFinder<PayOrderIntegralVo> queryIntegralOrder(PayOrderIntegralVo search) {
        List<PayOrderIntegralVo> data = payOrderMapper.findIntegralPayOrderList(search);
        Integer count = payOrderMapper.findIntegralPayOrderListCount(search);
        PageFinder<PayOrderIntegralVo> pageFinder = new PageFinder<PayOrderIntegralVo>(search.getPage(), search.getPageSize(), count, data);
        return pageFinder;
    }

    @Override
    public PageFinder<PayOrderVipVo> queryVipOrder(PayOrderVipVo search) {
        List<PayOrderVipVo> data = payOrderMapper.findVipPayOrderList(search);
        for (PayOrderVipVo payOrderVipVo : data) {
            if (null != payOrderVipVo.getOrderTime() && null != payOrderVipVo.getMemberLength() && !"".equals(payOrderVipVo.getMemberLength())) {
                if (payOrderVipVo.getMemberLength() == -1) {
                    payOrderVipVo.setMemberEndTime(null);
                } else {
                    Date d = payOrderVipVo.getOrderTime();
                    Calendar ca = Calendar.getInstance();
                    ca.setTime(payOrderVipVo.getOrderTime());
                    ca.add(Calendar.DATE, payOrderVipVo.getMemberLength() * 30);
                    d = ca.getTime();
                    payOrderVipVo.setMemberEndTime(d);
                }
            }
        }
        Integer count = payOrderMapper.findVipPayOrderListCount(search);
        PageFinder<PayOrderVipVo> pageFinder = new PageFinder<PayOrderVipVo>(search.getPage(), search.getPageSize(), count, data);
        return pageFinder;
    }

    @Override
    public List<PayOrderVipVo> findVipPayOrderListForExport(PayOrderVipVo search) {
        return payOrderMapper.findVipPayOrderListForExport(search);
    }

    @Override
    public int findOrderFinishCountByUserId(Integer userId, Integer companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("companyId", companyId);
        return payOrderMapper.findOrderFinishCountByUserId(map);
    }

    @Override
    public Integer queryOrderBuyClassCountByUserId(Map<String, Object> map) {
        return payOrderMapper.queryOrderBuyClassCountByUserId(map);
    }

    @Override
    public List<PayOrder> findSchoolMoneyByCondition(Map<String, Object> map) {
        return payOrderMapper.findSchoolMoneyByCondition(map);
    }

}
