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
<form action="<%=rootPath%>/payOrder/exportExcelAllOrder" method="post" id="searchForm"></form>
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
							<option value="PAY_TYPE_WX_PERSON">微信</option>
							<option value="PAY_TYPE_ZFB">支付宝</option>
						</select>
					</li>
					<li class="allOrderHeaderInput">
						<label for="payPrice01">订单金额：</label>
						<input type="text" id="payPrice01" onkeyup="checkNum($(this));" style="margin-right: 10px">至
						<input type="text" id="payPrice02" onkeyup="checkNum($(this));">
					</li>
				</ul>
				<button class="btn btn-primary" onclick="queryByCondition();">查询</button>
				<button class="btn btn-primary" onclick="exportData();">导出</button>
			</div>
			<div class="allArderState">
				<button class="btn btn-primary" code="">全部订单</button>
				<button class="btn btn-default" code="PAY_NON">未付款</button>
				<button class="btn btn-default" code="PAY_SUCCESS">已完成</button>
				<button class="btn btn-default" code="SUB_ORDER_DELTED">已取消</button>
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
    format: 'YYYY-MM-DD hh:mm',
//    isinitVal:true,
    onClose:false,
    maxDate: $.nowDate({DD:0}), //最大日期
    okfun: function(obj){
        end.minDate = obj.val; //开始日选好后，重置结束日的最小日期
//        endDates();
    }
};
var end = {
    format: 'YYYY-MM-DD hh:mm',
//	isinitVal:true,
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
			queryOrders(1);
		});

		function queryByCondition(){
			if($("#inpstart").val()){
				if(!$("#inpend").val()){
					alert("请选择查询时间")
					return;
				}
			}
			if($("#payPrice01").val()){
				if(!$("#payPrice02").val()){
					alert("请输入订单金额")
					return;
				}
			}
			queryOrders(1);
		}

		function checkNum(obj){
			var price = obj.val();
			var regu = "^[0-9]+\.?[0-9]*$";
			var re = new RegExp(regu);
			if(!re.test(price)){
				obj.val('');
				return;
			}
			if(obj.attr("id")=='payPrice02'){
				if(!$("#payPrice01").val()){obj.val(''); return;}
			}

		}


		function exportData(){
			if($("#inpstart").val()){
				if(!$("#inpend").val()){
					alert("请选择查询时间")
					return;
				}
			}
			if($("#payPrice01").val()){
				if(!$("#payPrice02").val()){
					alert("请输入订单金额")
					return;
				}
			}
			$("#searchForm").empty();
			var inputs = '<input type="hidden" name="page" value="1"/>' +
					'<input type="hidden" name="orderNum" value="'+$("#orderNum").val()+'"/>' +
					'<input type="hidden" name="inpstart" value="'+$("#inpstart").val()+'"/>'+
					'<input type="hidden" name="payMethod" value="'+$("#payMethod").val()+'"/>' +
					'<input type="hidden" name="firstPrice" value="'+$("#payPrice01").val()+'"/>'+
					'<input type="hidden" name="secondPrice" value="'+$("#payPrice02").val()+'"/>';

			$("#searchForm").append(inputs);
			$("#searchForm").submit();

		}

		function queryOrders(pageNo){

			var orderNum = $.trim($("#orderNum").val());
			var inpstart = $("#inpstart").val();
			var inpend = $("#inpend").val();
			var payMethod = $("#payMethod").val();
			var firstPrice = $("#payPrice01").val();
			var secondPrice = $("#payPrice02").val();
			$.ajax({
				url : "/payOrder/queryAllOrder",
				type:"post",
				data:{"page":pageNo,"pageSize":10,"orderNum":orderNum, "inpstart":inpstart, "inpend":inpend, "payMethod":payMethod, "firstPrice":firstPrice, "secondPrice":secondPrice},
				dataType:"html",
				beforeSend:function(XMLHttpRequest){
					$(".loading").show();
					$(".loading-bg").show();
				},
				success:function(data){
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

		        if($(this).hasClass('btn-default')){
                    $(this).addClass('btn-primary');
                    $(this).siblings('button').addClass('btn-default').removeClass('btn-primary');
				}else {
                    $(this).addClass('btn-default').removeClass('btn-primary');
				}

		});
	</script>
</body>
</html>
