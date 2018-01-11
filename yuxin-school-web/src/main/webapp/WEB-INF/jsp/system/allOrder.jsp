<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订单</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<%--<script type="text/javascript" src="<%=rootPath%>/javascripts/system/order.js"></script>--%>
<style type="text/css">
.head-div {
	position: relative;
	margin-top: 15px;
	padding: 3px 8px;
}

.font-size {
	font-size: 14px;
	margin-left: 10px;
	margin-right: 11px;
}
</style>

	<%--tob--%>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/jeUI/jedate.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
	<script  src="<%=rootPath%>/plugins/jeUI/jquery.jedate.min.js" ></script>
	<script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
	<div class="u-wrap admin overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_operaconfig.jsp"></jsp:include>
	<div class="right-side">
		<div class="mainbackground nopadding allOrderContent">
			<div class="allOrderHeader">
				<ul>
						<li>
							<label for="">订单编号：</label>
							<input type="text">
						</li>
						<li  class="allOrderHeaderInput">
							<label for="">订单时间：</label>
							<input type="text" style="margin-right: 10px" id="inpstart" readonly>至
							<input type="text" id="inpend" readonly>
						</li>
					</ul>
				<ul style="display: inline-block;">
					<li>
						<label for="">支付方式：</label>
						<select name="" >
							<option value="">全部</option>
							<option value="">微信</option>
							<option value="">支付宝</option>
						</select>
					</li>
					<li class="allOrderHeaderInput">
						<label for="">订单金额：</label>
						<input type="text" style="margin-right: 10px">至
						<input type="text">
					</li>
				</ul>
				<button class="btn btn-primary">查询</button>
				<button class="btn btn-primary">导出</button>
			</div>
			<div class="allArderState">
				<button class="btn btn-primary">全部订单</button>
				<button class="btn btn-default">未付款</button>
				<button class="btn btn-default">已完成</button>
				<button class="btn btn-default">已取消</button>
				<button class="btn btn-default">失败</button>
			</div>
			<div class="user-list allOrderTable">
				<table class="table table-center allOrderList" >
					<tr>
						<th width="3%">订单编号</th>
						<th width="10%">课程名</th>
						<th width="10%">金额（元）</th>
						<th width="10%">姓名</th>
						<th width="10%">电话	</th>
						<th width="10%">下单时间</th>
						<th width="10%">付款时间</th>
						<th width="10%">订单状态</th>
						<th width="15%">操作</th>
					</tr>
					<tr>
						<td>123456789</td>
						<td>如何学习英语</td>
						<td>0.00</td>
						<td>朱胤轮</td>
						<td>18623232323</td>
						<td>2017-11-30 24:00</td>
						<td>2017-11-30 24:00</td>
						<td>已完成</td>
						<td><a href="##">查看详情</a></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="pages">
			<ul class="pagination">
			</ul>
		</div>
	</div>
	</div>
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->


	<script>
//	分页
    $(".pagination").pagination('',
        {
            next_text: "下一页",
            prev_text: "上一页",
            current_page: '',
            link_to: "javascript:void(0)",
            num_display_entries: 8,
            items_per_page: 1,
            num_edge_entries: '',
            callback: function (page, jq) {
                var pageNo = page + 1;

            }
        }
    );

</script>
	<script>
//		日历插件
var start = {
    format: 'YYYY-MM-DD hh:mm:ss',
    isinitVal:true,
    onClose:false,
    maxDate: $.nowDate({DD:0}), //最大日期
    okfun: function(obj){
        end.minDate = obj.val; //开始日选好后，重置结束日的最小日期
//        endDates();
    }
};
var end = {
    format: 'YYYY年MM月DD日 hh:mm:ss',
    onClose:false,
    maxDate: '2099-06-16 23:59:59', //最大日期
    okfun: function(obj){
        start.maxDate = obj.val; //将结束日的初始值设定为开始日的最大日期
    }
};

//或者是
$.jeDate('#inpstart',start);
$.jeDate('#inpend',end);



	</script>
	<script type="text/javascript">
		$(function() {
			$selectSubMenu('financial');
			$selectSubMenus('order');
		});
	</script>
</body>
</html>