package com.yuxin.wx.api.pay;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.pay.PayOrder;
import com.yuxin.wx.model.pay.PayOrderHistory;
import com.yuxin.wx.vo.course.OrderManage;
import com.yuxin.wx.vo.student.PayOrderVo;
import com.yuxin.wx.vo.system.PayOrderIntegralVo;
import com.yuxin.wx.vo.system.PayOrderVipVo;
/**
 * Service Interface:PayOrder
 * @author chopin
 * @date 2015-3-12
 */
public interface IPayOrderService  {
	/**
	 * 
	* @Title: savePayOrder
	* @Description: 新增PayOrder
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	void insert(PayOrder entity);
	
	/**
	 * @Description: 新增订单历史表
	 * @author zx
	 * @date 2015-8-14 下午7:01:45
	 * @version 1.0
	 * @param history
	 */
	void insertOrderHistory(PayOrderHistory history);
	
	/**
	 * 
	* @Title: batchSavePayOrder 
	* @Description: 批量新增PayOrder
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	void batchInsert(List<PayOrder> list);
	
	/**
	 * 
	* @Title: updatePayOrder 
	* @Description: 编辑PayOrder
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	void update(PayOrder entity);
	
	/**
	 * 
	* @Title: deletePayOrderById 
	* @Description: 根据id删除PayOrder
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	void deletePayOrderById(Integer id);
	
	/**
	 * 
	* @Title: deletePayOrderByIds 
	* @Description: 根据id批量删除PayOrder
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	void deletePayOrderByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findPayOrderById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	PayOrder findPayOrderById(Integer id);
	
	/**
	 * 
	* @Title: findPayOrderByPage 
	* @Description: 分页查询
	* @return
	* @return List<PayOrder>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-3-12
	* @user by wangzx
	 */
	List<PayOrder> findPayOrderByPage(PayOrder search);
	
	/**
	 * @Description: 根据条件分页查询
	 * @author zx
	 * @date 2015-8-12 下午7:04:14
	 * @version 1.0
	 * @param order
	 * @return
	 */
	List<PayOrder> findPayOrderByParams(Map<String, Object> params);
	
	/**
	 * @Description: 查询总条数
	 * @author zx
	 * @date 2015-8-11 下午8:49:13
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Integer findCountByParams(Map<String, Object> params); 
	
	/**
	 * @Description: 根据学生ID查询其对应的订单信息
	 * @author ycl
	 * @date 2015-4-7 
	 * @version 1.0
	 * @return
	 */
	List<PayOrderVo> findPayOrderByUserId(PayOrder order);
	/**
	 * 
	 * Class Name: IPayOrderService.java
	 * @Description: 获取当前商品的总订单数
	 * @author 权飞虎
	 * @date 2015年4月13日 下午6:14:33
	 * @modifier
	 * @modify-date 2015年4月13日 下午6:14:33
	 * @version 1.0
	 * @param id
	 * @return
	 */
	Integer findCountByCommodityId(Integer id);
	
	/**
	 * @Description: 根据orderNum查询对应的order信息
	 * @author wzx
	 * @date 2015-5-7 下午4:38:50
	 * @version 1.0
	 * @param orderNum
	 * @return
	 */
	PayOrder findPayOrderByOrderNum(String orderNum);
	
	/**
	 * 
	 * Class Name: IPayOrderService.java
	 * @Description: 根据id查询vo
	 * @author yuchanglong
	 * @date 2015年12月22日 上午10:58:42
	 * @version 1.0
	 * @param id
	 * @return
	 */
	PayOrderVo findPayOrderVoById(Integer id);
	
	/**
	 * 
	 * Class Name: IPayOrderService.java
	 * @Description: 统计总金额
	 * @author zhang.zx
	 * @date 2016年3月29日 下午5:18:39
	 * @modifier
	 * @modify-date 2016年3月29日 下午5:18:39
	 * @version 1.0
	 * @param search
	 * @return
	 */
	Map findPayOrderTotalMoney(OrderManage search);
	
	/**
	 * 
	 * Class Name: IPayOrderService.java
	 * @Description: 查询积分订单
	 * @author zhang.zx
	 * @date 2016年5月19日 下午6:51:31
	 * @modifier
	 * @modify-date 2016年5月19日 下午6:51:31
	 * @version 1.0
	 * @param search
	 * @return
	 */
	PageFinder<PayOrderIntegralVo> queryIntegralOrder(PayOrderIntegralVo search);
	
	/**
	 * 
	 * @Description: 查询会员订单
	 * @author: dongshuai
	 * @date: 2016年5月24日
	 * @param search
	 * @return
	 *
	 */
	PageFinder<PayOrderVipVo> queryVipOrder(PayOrderVipVo search);
	
	/**
	 * @Description: 导出会员订单
	 * @author: dongshuai
	 * @date: 2016年6月7日
	 * @param search
	 * @return
	 */
	List<PayOrderVipVo> findVipPayOrderListForExport(PayOrderVipVo search);
	
	int findOrderFinishCountByUserId(Integer userId, Integer companyId);
	
	/**
	 * 
	 * Class Name: IPayOrderService.java
	 * @Description: 查询用户成功购买课程的数量
	 * @author dongshuai
	 * @date 2016年8月8日 下午3:19:29
	 * @modifier
	 * @modify-date 2016年8月8日 下午3:19:29
	 * @version 1.0
	 * @param map
	 * @return
	 */
	Integer queryOrderBuyClassCountByUserId(Map<String,Object> map);
}