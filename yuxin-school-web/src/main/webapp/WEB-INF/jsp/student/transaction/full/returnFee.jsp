<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>退费</title>
<%@include file="/decorators/import.jsp"%>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>

</head>
<body>
	<input id="payMasterId" value="${payMaster.id }" type="hidden"/>
	<input type="hidden" value="${lable }" id="lableTypes"/>
	<c:choose>
		<c:when test="${lable=='simpleType' }">
			<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>
	<%-- <div class="u-wrap student">
		<div class="mainbackground">
			<div class="main-content nospace" id="topMessage">
				<div class="m-b-m">
					<div class="m-b-p clear">
						<div class="m-b-p-left">
							<p class="h c">
								<span class="p-title">姓名</span> <span class="p-content">${student.name }</span>
							</p>
							<p class="h c">
								<span class="p-title">学费</span> <span class="p-content">${payMaster.totalAmount }</span>
							</p>
							<p class="h c">
								<span class="p-title">报名时间</span> <span class="p-content"><fmt:formatDate
										value="${payMaster.applyTime }"
										timeStyle="yyyy-MM-dd hh:mm:ss" /></span>
							</p>
							<p class="h c">
								<span class="p-title">手机号码</span> <span class="p-content">${student.mobile}</span>
							</p>
							<p class="h c">
								<span class="p-title">已缴</span> <span class="p-content">${payMaster.hasPay }</span>
							</p>
							<p class="h c">
								<span class="p-title">来源</span> <span class="p-content">${wx:dictCode2Name(payMaster.applyChannelCode)}</span>
							</p>
							<p class="h c">
								<span class="p-title">班型</span> <span class="p-content">${payMaster.classTypeName }</span>
							</p>
							<p class="h c important">
								<span class="p-title">欠缴</span> <span class="p-content">${payMaster.totalAmount-payMaster.hasPay }</span>
							</p>
						</div>
						<div class="m-b-p-right">
							<div class="btns">
								<a href="javascript:;" id="1" class="m-btn m-active">退费</a> <a
									href="javascript:;" id="2" class="m-btn">转班</a> <a
									href="javascript:;" id="3" class="m-btn m-mb ">转人</a> <a
									href="javascript:;" id="4" class="m-btn m-mb">详情</a> <a
									href="javascript:;" id="5" class="m-btn m-mb change">更换班号</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> --%>
	<jsp:include page="top.jsp"></jsp:include>
	<div class="u-wrap student">
		<div class="mainbackground">
			<div class="heading">
				<h2 class="h5">退费</h2>
				<div class="user-infos">
					<span>${student.name }</span> <span>${student.mobile }</span> <span>${payMaster.classTypeName }</span>
				</div>
				<span class="line"></span>
			</div>
			<div class="main-content">
				<div class="m-title">
					<h2 class="h6">订单信息</h2>
				</div>
				<ul class="list-infos clear">
					<li>
						<p class='c'>
							<span class="c-title">学习课程</span> <span class="c-content">${payMaster.classTypeName }</span>
						</p>
					</li>
					<li>
						<p class='c'>
							<span class="c-title">代报考</span> <span class="c-content">
								<input type="checkbox"
								<c:if test="${payMaster.isAgent==1 }">checked</c:if> value="1"
								disabled="disabled"> 是否代报考 <input type="checkbox"
								<c:if test="${payMaster.isAgentMaterialComplete==1 }">checked</c:if>
								value="2" disabled="disabled"> 资料是否齐全
							</span>
						</p>
					</li>
					<li>
						<p class='c'>
							<span class="c-title">培训费用</span> <span class="c-content">${payMaster.trainingFee }</span>
						</p>
					</li>
					<li>
						<p class='c'>
							<span class="c-title">代报考费用</span> <span class="c-content">${payMaster.examAgentFee }</span>
						</p>
					</li>
					<li>
						<p class='c'>
							<span class="c-title">订单总价</span> <span class="c-content">${payMaster.totalAmount }</span>
						</p>
					</li>
					<li>
						<p class='c'>
							<span class="c-title">已交总额</span> <span class="c-content">${payMaster.hasPay }</span>
						</p>
					</li>
				</ul>
			</div>
			<div id="message"></div>
			<form action="" method="post" id="feeMessage">
			<input type="hidden" id="stuId" name="stuId" value="${student.id}"/>
			<input type="hidden" id="refundFee" name="refundFee" value=""/>
			<input type="hidden" id="stuName" name="stuName" value="${student.name}"/>
			<input type="hidden" id="stuIdentityId" name="stuIdentityId" value="${student.identityId}"/>
			<input type="hidden" id="mid" name="payMasterId" value="${payMaster.id}"/>
			<input type="hidden" id="changeType" name="changeType" value="ORDER_OPERTE_STOPED">
			<input type="hidden" id="createType" name="createType" value="ORDER_OPERTE_STOPED">
			<jsp:include page="reason.jsp"></jsp:include>
			<div class="main-content">
				<div class="m-title">
					<h2 class="h6">退费</h2>
					<div class="more blue">
						<strong id="canReturn">可退 0.00</strong>
					</div>
				</div>
				<div class="m-i">
					<p class="c">
						<span class="i-title"><em>已使用</em></span> <span class="i-input">
							<input type="text" id="hasUsed" name="hasUsed"  class="prices" onblur="changeCanReturn();" value="0.00">
						</span>
					</p>
					<p class="c">
						<span class="i-title">POS</span> <span class="number" id="pos">0.00</span>
						<span class="tips" id="a" style="display:none">退费金额</span> 
						<span class="i-input"> 
						<input type="text" id="posReturn" name="posRefund" class="money prices" style="display:none" value="">
						</span>
					</p>
					<p class="c">
						<span class="i-title">现金</span> <span class="number" id="cash">0.00</span>
						<span class="tips" id="b" style="display:none">退费金额</span> <span class="i-input"> 
						<input type="text" id="cashReturn" name="cashRefund" class="money prices"  style="display:none" value="">
						</span>
					</p>
					<p class="c">
						<span class="i-title">支票</span> <span class="number" id="check">0.00</span>
						<span class="tips" id="c" style="display:none">退费金额</span>
						<span class="i-input"> 
						<input type="text" id="checkReturn" name="checkRefund" class="money prices"  style="display:none" value="">
						</span>
					</p>
					<p class="c">
						<span class="i-title">转账</span> <span class="number" id="remit">0.00</span>
						<span class="tips" id="d" style="display:none">退费金额</span>
						<span class="i-input"> 
						<input type="text" id="remitReturn" name="remitRefund" class="money prices" style="display:none" value="">
						</span>
					</p>
					<p class="c">
						<span class="i-title"></span> <span class="i-input"> <a
							href="javascript:;" onclick="save();" class="btn btn-primary">保存</a> <a
							href="javascript:;" onclick="history.go(-1);" class="btn btn-default">取消</a>
						</span>
					</p>
				</div>
			</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/full/transaction/returnFee.js"></script>
<script>
	$(function(){
		$("#reason_type").getSysDict("RESPONSIBLE_DIVISION",null,"bycode");
        $("#reason_type").bind("change",function(){
       	 $("#reason_detail").html('').getSubDict($(this).find("option:selected").val(),"reason");
       	 $("#reason_depart").html('').getSubDict($(this).find("option:selected").val(),"depart");
   	 });
	})
</script>
</body>
</html>