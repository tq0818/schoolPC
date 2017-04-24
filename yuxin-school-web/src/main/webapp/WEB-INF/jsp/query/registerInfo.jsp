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
<title>留言</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet"
	type="text/css" />
	<style type="text/css">
		.pages li.disabled{padding:0px;}
	</style>
 <link rel="stylesheet" href="<%=rootPath%>/stylesheets/popupwin.css"/>
</head>
<body>
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_admin.jsp"></jsp:include>
	<div class="u-wrap query">
	
		<div class="mainbackground nopadding">
			<div class="main-content nospace">
				<div class="classes-type">
				<form method="post" id="searchForm">
				<p class="c">
                    <span class="t-title">日期</span>
                    <span class="t-content" id="dateList">
	                <a href="javascript:;" mark="today" class="btn btn-sm btn-default" >今天</a>
	                <a href="javascript:;" mark="yesday" class="btn btn-sm btn-default" >昨天</a>
	                <a href="javascript:;" mark="sevnday" class="btn btn-sm btn-default" >最近7天</a>
	                <a href="javascript:;" mark="thirty" class="btn btn-sm btn-default" >最近30天</a>
                	<a href="javascript:;" mark="nos" class="btn btn-sm btn-default" >指定时间</a>
                        <em class="daterangs none">从</em> 
						<input class="date-picker from daterangs none" name="startTime" type="text"> 
						<em class="daterangs none">到</em>
						<input class="date-picker to daterangs none" name="endTime" type="text"> 
                        <input type="button" id="searchContent" class="btn btn-sm btn-primary" value="搜索">
                    </span>
                </p>
                </form>
				</div>
			</div>
		</div>
	</div>
	<div class="u-wrap query">
		<div class="mainbackground">
			<div class="title" style="margin-left:84px;">
				<input type="button" id="export" class="btn btn-sm btn-primary exportexcle" value="导出注册信息">
			</div>
			<div class="c-list">
				<div class="panel" style="border:0;">
					<div class="chars none" style="width:1060px;height:500px;"></div>
					<div class="tables" style="padding:0;">
						<table class="table table-center" style="margin:0;">
								<tr>
									<th width="50%">url</th>
									<th width="20%">手机</th>
									<th width="30%">注册时间</th>
								</tr>
								<tr><td colspan="3">暂无数据</td></tr>
						</table>
						<div class="pages pagination">
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
	<script type="text/javascript" src="<%=rootPath%>/javascripts/query/registerInfo.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
</body>
</html>