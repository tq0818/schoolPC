<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>收入查询</title>
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
				<label for="">请选择查询日期：</label>
				<input type="text" style="margin-right: 10px" id="inpstart" readonly>至
				<input type="text" id="inpend" readonly>
				<button class="btn btn-primary">查询</button>
				<button class="btn btn-primary">导出数据</button>
			</div>
			<div class="user-list allOrderTable">
				<table class="table table-center allOrderList" >
					<tr>
						<th width="3%">序号</th>
						<th width="10%">时间</th>
						<th width="10%">
							总收入(元)
							<i class="icon iconfont unsort sorting">&#xe612;</i>
						</th>
						<th width="10%">应缴费用（元）</th>
						<th width="10%">实际收入</th>
					</tr>
					<tr>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
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
    maxDate: $.nowDate({DD:0}), //最大日期
    okfun: function(obj){
        end.minDate = obj.val; //开始日选好后，重置结束日的最小日期
//        endDates();
    }
};
var end = {
    format: 'YYYY年MM月DD日 hh:mm:ss',
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
			$selectSubMenus('incomeQuery');
		});
	</script>
</body>
</html>