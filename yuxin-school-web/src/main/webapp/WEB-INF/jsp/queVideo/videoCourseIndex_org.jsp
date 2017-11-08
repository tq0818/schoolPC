<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>点播统计-概况（校级管理员）</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query/statistics.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_org.jsp"></jsp:include>
<div class="u-wrap query overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query_org.jsp"></jsp:include>
	<div class="right-side set-system gg-biaoge">
		<div class="mainbackground nopadding">
			<div class="heading">
				<h2 class="h5">点播情况</h2>
				<span class="line"></span>
			</div>
		</div>
		<div class="content-right">
			<p class="screen-info">
				<a href="<%=rootPath%>/query/orgstatistics/videoCourseIndex" class="btn active" >概况</a>
				<a href="<%=rootPath%>/query/orgstatistics/userVideoList" class="btn">详情</a>
				<span class="date">
					<i class="text">日期</i>
					<span><input type="text" name="startTime" class="date-picker from" value="${startTime}"/><em>到</em><input type="text" name="endTime" class="date-picker to" value="${endTime}"/></span>

					<input type="hidden" id="eduArea" name="eduArea" value="${area.itemCode}">
					<input type="hidden" id="eduSchool" name="eduSchool" value="${org.itemCode}">
				</span>
				<button class="btns-default">查询</button>
			</p>
			<ul class="playcount-info">
				<li>
					<img class="pull-left" src="<%=rootPath%>/images/query/ico-play1.png" alt="">
					<span class="pull-right tit">总计观看直播人数</span>
					<span class="pull-right msg">500</span>
				</li>
				<li>
					<img class="pull-left" src="<%=rootPath%>/images/query/ico-play2.png" alt="">
					<span class="pull-right tit">总计观看直播时长</span>
					<span class="pull-right msg">1034小时32分10秒</span>
				</li>
				<li>
					<img class="pull-left" src="<%=rootPath%>/images/query/ico-play3.png" alt="">
					<span class="pull-right tit">总计观看直播人次</span>
					<span class="pull-right msg">1210</span>
				</li>
			</ul>
			<div class="statistics-con">
				<div class="demand-count" id="demandCount" style="width:100%;height: 380px;"></div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts-all.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/byecharts.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$selectThirdMenu('videoList');
	$(document).statistical().init();

	var chartOpiton = {
		"id":document.getElementById("demandCount"),
		"legend":{
			show:true,
			selectedMode: false,
			y: 20,
			data:["报名人数","实际观看人数"]
		},
		"series":[
			{
				name: '实际观看人数',
				type: 'bar',
				itemStyle: {
					normal: {
						color: "#5b9bd5"
					}
				},
				data: [160,172,160,98,102,30]
			},{
				name: "报名人数",
				type: 'bar',
				itemStyle: {
					normal: {
						color: "#ed7d31",
					}
				},
				data: [200,200,200,200,200,200]
			}
		],
		"seriesName":"观看点播前五",
		"yAxisData":['2012级','2013级','2014级','2015级','2016级','2017级']

	};
	$(document).statistical().setCharts(chartOpiton);
</script>
</body>
</html>