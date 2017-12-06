package com.yuxin.wx.controller.branchschool;


import com.yuxin.wx.api.pay.IPayOrderService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.pay.PayOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orderQuery")
public class OrderQuery {
    @Autowired
    private IPayOrderService payOrderServiceImpl;

    @RequestMapping(value = "/getOrderList")
    public String getOrderList(Model model,
                               Integer page,
                               String payType,
                               String payStatus,
                               String startDate,
                               String endDate,
                               String mobile,
                               String payTime,
                               Integer companyId) {
//        Integer companyId = WebUtils.getCurrentCompanyId();

        Map<String, Object> map = new HashMap<String, Object>();
        int timeLen = -1;
        if ("today".equals(payTime)) {
            timeLen = 0;
        } else if ("yesday".equals(payTime)) {
            timeLen = 1;
        } else if ("sevnday".equals(payTime)) {
            timeLen = 7;
        } else if ("thirty".equals(payTime)) {
            timeLen = 30;
        } else if ("thirmonth".equals(payTime)) {
            timeLen = 90;
        }
        if (!"nos".equals(payTime)) {
            startDate = "";
            endDate = "";
        }
        map.put("payType", payType);
        map.put("payTime", payTime);
        map.put("timeLen", timeLen);
        map.put("payStatus", payStatus);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("mobile", mobile);
        map.put("companyId", companyId);
        map.put("page", (page - 1) * 5);
        map.put("pageSize", 5);

        System.out.println("payTime:" + payTime);

        // 查询 订单 集合
        List<PayOrder> cpoList = this.payOrderServiceImpl.findPayOrderByParams(map);
        // 总数
        Integer count = this.payOrderServiceImpl.findCountByParams(map);
        // 分页
        PageFinder<PayOrder> payPage = new PageFinder<PayOrder>(page, 5, count, cpoList);

        //数据存放在payPage中的data属性中
        model.addAttribute("payPage", payPage);
        return "system/orderDetail";

    }
}
