<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户订单</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
<script src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/system/order.js"></script>
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
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
	<div class="u-wrap admin overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_operaconfig.jsp"></jsp:include>
	<div class="right-side">
		<div class="mainbackground nopadding">
			<div class="admin-type">
				<p class="c payStatus">
					<span class="t-title">订单状态</span>
						<span class="t-content">
							<a href="javascript:;" class="btn btn-sm btn-status btn-success" pay-status="0">全部</a>
							<a href="javascript:;" class="btn btn-sm btn-status btn-default" pay-status="1">未支付</a>
							<a href="javascript:;" class="btn btn-sm btn-status btn-default" pay-status="2">转账中</a>
							<a href="javascript:;" class="btn btn-sm btn-status btn-default" pay-status="3">已完成</a>
						</span>
				</p>
				<p class="c">
					<span class="t-title">付款方式</span>
					<span class="t-content">
						<a href="javascript:;" class="btn btn-sm btn-status btn-success" pay-type="0">全部</a> 
						<a href="javascript:;" class="btn btn-sm btn-status btn-default" pay-type="1">支付宝</a>
						<a href="javascript:;" class="btn btn-sm btn-status btn-default" pay-type="2">担保交易</a>
						<a href="javascript:;" class="btn btn-sm btn-status btn-default" pay-type="3">个人转账</a>
						<a href="javascript:;" class="btn btn-sm btn-status btn-default" pay-type="4">微信支付</a>
						<c:if test="${stydycardservice == 1 }">
						<a href="javascript:;" class="btn btn-sm btn-status btn-default" 
							pay-type="5">学习卡兑换</a>
						</c:if>
					</span>
				</p>
 				<p class="c">
                    <span class="t-title">日期</span>
                    <span class="t-content" id="dateList">
	                <a href="javascript:;" mark="today" class="btn btn-sm btn-default day-time" >今天</a>
	                <a href="javascript:;" mark="yesday" class="btn btn-sm btn-default day-time" >昨天</a>
	                <a href="javascript:;" mark="sevnday" class="btn btn-sm btn-default day-time" >7天</a>
	                <a href="javascript:;" mark="thirty" class="btn btn-sm btn-default day-time" >当月</a>
	                <a href="javascript:;" mark="thirmonth" class="btn btn-sm btn-default day-time" >三个月</a>
                	<a href="javascript:;" mark="nos" class="btn btn-sm btn-default day-time-point" >指定时间</a>
                        <em class="daterangs none">从</em> 
						<input type="text" readonly="readonly" id="startDate" class="txt laydate-icon none" style="width: 119px; text-align: center;"> 
						<em class="daterangs none">到</em>
						<input type="text" readonly="readonly" id="endDate" class="txt laydate-icon none" style="width: 120px; text-align: center;"> 
                    </span>
                </p>
				<!-- <p class="c">
					<span class="t-title">订单时间</span> <span class="t-content"> <input
						type="text" readonly="readonly" id="startDate"
						class="txt laydate-icon" style="width: 119px; text-align: center;" />
						- <input type="text" readonly="readonly" id="endDate"
						class="txt laydate-icon" style="width: 120px; text-align: center;" />
					</span>
				</p> -->
				<p class="c">
					<span class="t-title" style="width: 92px;">手机号/用户名</span>
					<span class="t-content">
						<input type="text" id="mobile" class="txt" onkeyup="this.value=this.value.replace(/[^u4e00-u9fa5w]/g,'')";/>
						<a href="javascript:;" class="btn btn-sm btn-primary search" pay-status="0">搜索</a>
						<a href="javascript:;" class="btn btn-sm btn-primary reset" pay-status="0">重置</a>
					</span>
				</p>
			</div>
		</div>
		<%--放入订单列表--%>
		<div class="mainbackground nopadding">
			<div class="a-list o-list">

			</div>
		</div>
		<div class="pages">
			<ul class="pagination">
			</ul>
		</div>
		</div>
	</div>
	<input type="hidden" value="5" id="pageSize">


	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->
	<script type="text/javascript">
		$(function() {
			$selectSubMenu('financial');
			$selectSubMenus('operate_fee_confirm');
		});
	</script>
</body>
</html>