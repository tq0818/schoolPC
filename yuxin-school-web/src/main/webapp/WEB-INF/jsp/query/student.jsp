<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>学员查询趋势图</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
	<style type="text/css">
		.pages li.disabled{padding:0px;}
	</style>
</head>
<body>
<input type="hidden" id="schoolId" value='${schoolId}'/>
		<input type="hidden" id="schoolName" value='${schoolName}'/>
		<input type="hidden" id="isAdmin" value='${isAdmin}'/>
		<input type="hidden" id="isSubAdmin" value='${isSubAdmin}'/>	
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
	<div class="u-wrap query overflow">
	 	<jsp:include page="/WEB-INF/jsp/menu/menu_query.jsp"></jsp:include>
	<div class="right-side">
		<div class="mainbackground nopadding">
			<div class="main-content nospace">
				<div class="classes-type">
					<p class="c">
						<span class="t-title">分校</span> <span class="t-content"> </span>
					</p>
					<p class="c">
						<span class="t-title">来源</span> <span class="t-content"> </span>
					</p>
					<p class="c">
						<span class="t-title">用户类型</span> <span class="t-content">
						</span>
					</p>
					<p class="c">
						<span class="t-title">注册时间</span> <span class="t-content" id="dateList">
					<a href="javascript:;" mark="today" class="btn btn-sm btn-default" >今天</a>
	                <a href="javascript:;" mark="yesday" class="btn btn-sm btn-default" >昨天</a>
	                <a href="javascript:;" mark="sevnday" class="btn btn-sm btn-default" >7天</a>
	                <a href="javascript:;" mark="thirty" class="btn btn-sm btn-default" >当月</a>
	                <a href="javascript:;" mark="thirmonth" class="btn btn-sm btn-default" >三个月</a>
                	<a href="javascript:;" mark="nos" class="btn btn-sm btn-default" >指定时间</a>
                       <em class="daterangs none" style="color: black;background-color: #f6f6f6;border: none;">从</em> 
						<input class="date-picker from daterangs none" type="text"> 
						<em class="daterangs none" style="color: black;background-color: #f6f6f6;border: none;">到</em>
						<input class="date-picker to daterangs none" type="text">
							<input type="button" class="btn btn-sm btn-primary btn-searh" value="搜索">
						</span>
					</p>
				</div>
			</div>
			<div class="mainbackground" style="padding: 50px 0px 10px;">
			<div class="title">
				<a href="javascript:;" class="btn btn-sm btn-success btn-detail">明细</a> <a
					href="javascript:;" class="btn btn-sm btn-default btn-chart">趋势图</a>
			</div>
			<div class="c-list">
				<div class="tabs">
				    <a href="javascript:;" class="btn btn-sm btn-default">手机端:<b>0</b>人</a>
					<a href="javascript:;" class="btn btn-sm btn-default">线下录入:<b>0</b>人</a> 
					<a href="javascript:;" class="btn btn-sm btn-default">官网注册:<b>0</b>人</a> 
					<a href="javascript:;" class="btn btn-sm btn-default">合计新增学员:<b>0</b>人
					</a>
				</div>
				<div class="panel">
					<div class="chars none" style="width:1060px;height:500px;"></div>
					<div class="tables">
						<table class="table table-center">
								<tr>
									<th width="12%">所属分校</th>
									<th width="12%">姓名</th>
									<th width="12%">用户名</th>
									<th width="12%">手机</th>
									<th width="12%">邮箱</th>
									<th width="15%">注册时间</th>
									<th width="10%">用户类型</th>
									<th width="15%">来源</th>
								</tr>
								<tr><td colspan="8">暂无数据</td></tr>
						</table>
						<div class="pages pagination">
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
		</div>
	</div>

	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/query/student.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
</body>
</html>