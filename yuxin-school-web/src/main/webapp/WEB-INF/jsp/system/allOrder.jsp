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
							<label for="orderNum">订单编号：</label>
							<input id="orderNum" type="text">
						</li>
						<li  class="allOrderHeaderInput">
							<label for="inpstart">订单时间：</label>
							<input type="text" style="margin-right: 10px" id="inpstart" readonly>至
							<input type="text" id="inpend" readonly>
						</li>
					</ul>
				<ul style="display: inline-block;">
					<li>
						<label for="payMethod">支付方式：</label>
						<select id="payMethod" >
							<option value="">全部</option>
							<option value="">微信</option>
							<option value="">支付宝</option>
						</select>
					</li>
					<li class="allOrderHeaderInput">
						<label for="payPrice01">订单金额：</label>
						<input type="text" id="payPrice01" style="margin-right: 10px">至
						<input type="text" id="payPrice02">
					</li>
				</ul>
				<button class="btn btn-primary">查询</button>
				<button class="btn btn-primary">导出</button>
			</div>
			<div class="allArderState">
				<button class="btn btn-primary">全部订单</button>
				<button class="btn btn-primary">未付款</button>
				<button class="btn btn-primary">已完成</button>
				<button class="btn btn-primary">已取消</button>
			</div>
			<div class="user-list allOrderTable" id="orderList">

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
			console.log(0);
			queryOrders(1);
		});

		function queryOrders(pageNo){

			var orderNum = $("#orderNum").val();
			var inpstart = $("#inpstart").val();
			var inpend = $("#inpend").val();
			var payMethod = $("#payMethod").val();
			var firstPrice = $("#firstPrice").val();
			var secondPrice = $("#secondPrice").val();
			console.log(11);
			$.ajax({
				url : "/payOrder/queryAllOrder",
				type:"post",
				data:{"page":pageNo,"pageSize":5,"orderNum":orderNum, "inpstart":inpstart, "inpend":inpend, "payMethod":payMethod, "firstPrice":firstPrice, "secondPrice":secondPrice},
				dataType:"html",
				beforeSend:function(XMLHttpRequest){
					$(".loading").show();
					$(".loading-bg").show();
				},
				success:function(data){
					console.log(22);
					$("#orderList").html("").html(data);
				},
				complete:function(XMLHttpRequest,textStatus){
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		}

		//筛选按钮
		$('.allArderState').children('button').click(function(){
		    console.log($(this).index());

		    if($(this).index()==0){
		        $('button').addClass('btn-primary');
			}else {
		        if($(this).hasClass('btn-default')){
                    $(this).addClass('btn-primary');
                    $(this).siblings('button').addClass('btn-default').removeClass('btn-primary');
				}else {
                    $(this).addClass('btn-default').removeClass('btn-primary');
				}
            }
		});
	</script>
</body>
</html>
