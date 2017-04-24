<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>确认收款</title>
<%@include file="/decorators/import.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/jquery.datetimepicker.css" />
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.datetimepicker.js"></script>
</head>
<body>
	<!-- header start -->
	<!-- header end -->
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<form action="" id="form">
	<div class="u-wrap admin">
		<div class="mainbackground nopadding">
			<div class="pay-submit">
				<div class="heading">
					<h2 class="h5">确认收款</h2>
					<span class="line"></span>
				</div>
				<div class="s-title">
					<h2 class="h6">订单信息</h2>
				</div>
				<div class="o-table">
					<table class="table">
						<col width="33%">
						<col width="33%">
						<col width="33%">
						<tr>
							<td><span class="a-title">订单编号</span> <span
								class="a-content">${payOrder.orderNum}</span></td>
							<td>
							<c:if test="${payOrder.commdityType == 'COMMODITY_CLASS' || payOrder.commdityType == 'COMMODITY_PACKAGE' }">
								<span class="a-title">课程</span> 
							</c:if>
							<c:if test="${payOrder.commdityType == 'INTEGRAL' }">
								<span class="a-title">积分</span> 
							</c:if>
							<c:if test="${payOrder.commdityType == 'MEMBER' }">
								<span class="a-title">会员</span> 
							</c:if>
							<span class="a-content">${payOrder.commodityName}</span>
							</td>
							<td><span class="a-title">订单状态</span> <span
								class="a-content">${wx:dictCode2Name(payOrder.payStatus)}</span>
							</td>
						</tr>
						<tr>
							<td>
								<span class="a-title">订单金额</span> 
								<span class="a-content">${payOrder.payPrice}</span>
							</td>
							<td>
								<span class="a-title">订单时间</span> 
								<span class="a-content"><fmt:formatDate value="${payOrder.orderTime}" type="both"/></span>
							</td>
							<td><span class="a-title">支付时间</span> <span class="a-content"><fmt:formatDate value="${payOrder.payTime}" type="both"/></span></td>
						</tr>
						<tr>
							<td>
								<span class="a-title">用户名</span> 
								<span class="a-content">${payOrder.userName}</span>
							</td>
							<td>
								<span class="a-title">手机号</span> 
								<span class="a-content">${payOrder.userMobile}</span>
							</td>
							<td></td>
						</tr>
					</table>
				</div>
				
				<div class="s-title">
					<h2 class="h6">支付信息</h2>
				</div>
				<div class="o-table">
					<table class="table">
						<col width="33%">
						<col width="33%">
						<tr>
							<td><span class="a-title">汇款编号</span> <span
								class="a-content"> <input type="text" placeholder="汇款编号" name="remittanceNumber">
							</span></td>
							<td><span class="a-title">收款金额</span> <span
								class="a-content"> <input type="text" placeholder="收款金额" name="collectionAmount">
							</span></td>
							<td><span class="a-title">时间</span> <span class="a-content">
									<input id="datetimepicker" name="collectionTime" readonly="readonly" type="text" placeholder="收款时间">
							</span></td>
						</tr>
						<tr>
							<td colspan="2"><span class="a-title">备注</span> <span class="a-content">
								<textarea  rows="3" cols="100" name="remark" placeholder="备注"></textarea>
							</span></td>
							<td></td>
						</tr>
					</table>
					<input type="hidden" name="id" value="${payOrder.id}" id="payId"/>
					<input type="hidden" name="orderNum" value="${payOrder.orderNum}"/>
					<input type="hidden" name="payType" value="${payOrder.payType}"/>
					<input type="hidden" name="commodityId" value="${payOrder.commodityId}"/>
					<input type="hidden" name="userId" value="${payOrder.userId}"/>
					<input type="hidden" name="payPrice" value="${payOrder.payPrice}"/>
					<input type="hidden" name="commdityType" value="${payOrder.commdityType}"/>
				</div>
				<p class="text-center">
					<a href="javascript:void(0)" class="btn btn-success ok">确认收款</a>
				</p>
			</div>
		</div>
	</div>
	
	<!-- footer start -->
	<!-- footer end -->
</form>
<div class="loading lp-units-loading" style="display: none">
	<p>
		<i></i>加载中,请稍后...
	</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
<script>
$(function(){
	/* $selectSubMenu('org_manage_order'); */
		$selectSubMenu('financial');
	//加载时间控件
	$("#datetimepicker").datetimepicker({
		lang:'ch',
		timepicker:false,
		format:'Y-m-d'
	});
	
	$(".ok").on("click",function(){
		var payId = $("#payId").val();
		$.ajax({
			 type: "post", 
			  url: rootPath+"/payOrder/checkIntegral", 
			  data: {"orderId":payId},
			  success: function(result){
				  if(result.pass){
					  var collectionAmount = $("input[name='collectionAmount']").val();
						var payPrice = $("input[name='payPrice']").val();
						var collectionTime = $("input[name='collectionTime']").val();
						
						//处理收款金额小数点以后是0，则用户在输入为没有小数点的时候也判断相等
						var num1 = window.parseFloat(collectionAmount); // 返回数字
						
						if(!collectionAmount){
							alert("请输入收款金额");
							return false;
						}
						if(isNaN(collectionAmount)){
							alert("收款金额必须为数字");
							return false;
						}
						if(!collectionTime){
							alert("收款时间不能为空");
							return false;
						}
						if(collectionAmount == payPrice || num1 == payPrice){
							submitOrder();
						}else{
							$.confirm(("收款金额和实际金额不一致，是否继续收款？"),function(isConfirm){
								if(isConfirm){
									submitOrder();
								}else{
									return;
								}
							});
						}
				  }else{
					  alert(result.msg);
					  return;
				  }
			  }
		});
		
		 	
	});
});

function submitOrder(){
	$.ajax({ 
	  type: "post", 
	  url: rootPath+"/payOrder/submitOrder", 
	  data: $("form").serialize(),
	  beforeSend : function(XMLHttpRequest) {
			$(".loading").show();
			$(".loading-bg").show();
      },
	  success: function(result){
		  $(".ok").unbind();
		  window.location.href = rootPath+"/payOrder/toOrder";
	  },
	  complete : function(XMLHttpRequest, textStatus) {
			$(".loading").hide();
			$(".loading-bg").hide();
	  }
  });
};
</script>
</body>
</html>