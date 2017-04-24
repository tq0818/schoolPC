<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
<title>直播统计</title>
<%@include file="/decorators/import.jsp"%>
<link href="<%=rootPath%>/stylesheets/manage.css" rel="stylesheet" type="text/css" />
<link href="<%=rootPath%>/stylesheets/minitip.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
<link href="<%=rootPath%>/stylesheets/company.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/plus/miniTip.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/plus/laydate/laydate.js"></script>


</head>
<body>
<script type="text/javascript">
		$(function() {
			$selectSubMenu('org_service');
			$(".zbTail").addClass("active");
		})
</script>
	
	<!-- header start -->
	<!-- header end -->
	<!-- 二级导航 -->
	<!-- 用户信息开始 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/company/commonDomain.jsp"></jsp:include>
	<!-- 用户信息结束 -->
	<!-- 购买主体内容 -->
	<div class="u-wrap company overflow">
	<%@include file="/WEB-INF/jsp/company/liveCommonLeft.jsp"%>
	<div class="right-side">
		<div class="mainbackground u-content clear">
			<div class="full-wrap buy-box">
				<div class="buy-title">
                	<div class="heading">
				        <i class="brand-text">直播明细</i>
				    </div>
	                <div class="title-infos">
	                		<span class="infos"><em style="font-size: 14px;">
	                		${!empty clc ? clc.concurrentMax : 0 }
	                	</em>可用同时在线人数<i
										class="iconfont ask" title="最大同时在线人数">&#xe60f;</i></span>
	                    <span class="infos"><em style="font-size: 14px;">
	                    ${!empty css.onlineStudent ? css.onlineStudent : 0 }</em>已用最大同时在线人数<i
										class="iconfont ask" title="当月最大同时在线人数">&#xe60f;</i></span>
	                </div>
				</div>
					<div class="w">
						<span class="class_number_name">起始时间：</span>
						<input type="text" class="laydate-icon" readonly="readonly" id="start" style="width:200px"><span>至 </span>
						<input type="text" readonly="readonly" class="laydate-icon" id="end" style="width:200px">
						<input type="button" value="搜索" class="btn btn-sm selectInfo">
					</div>
					<div id="tb" style="margin-top: 40px;">
						<div id="lineDomLiveDetail" style="height: 400px; width: 95%;"></div>
					</div>
			</div>
		</div>
		</div>
	</div>
	<!-- 购买主体结束 -->
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
		<p><i></i>加载中,请稍后...</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
	<!--  ajax加载中div结束 -->
	<!-- footer start -->
	<!-- footer end -->

<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts-all.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/company/liveStatistics.js"></script>
</body>
</html>