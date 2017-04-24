<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/decorators/import.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/enjoyhint/jquery.enjoyhint.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
	<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/student.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/guide.css"/>
	<link rel="stylesheet" type="text/css"  href="<%=rootPath %>/stylesheets/sidebar/sidebar.css"/>
	<script type="text/javascript">var rootPath='<%=rootPath%>'</script>
    <script src="<%=rootPath%>/javascripts/plus/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=rootPath%>/plugins/enjoyhint/enjoyhint.js"></script>
    <script src="<%=rootPath%>/javascripts/system.js" type="text/javascript"></script>
    <script src="<%=rootPath%>/javascripts/index.js" type="text/javascript"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
	<div></div>
<div style="display: block;">
	<div class="u-wrap query">
		<div class="mainbackground">
			<div class="c-list">
				<div id="dateList">
					<a href="javascript:;" mark="sevnday" class="btn btn-sm btn-default btn-success" >7天</a>
	                <a href="javascript:;" mark="thirty" class="btn btn-sm btn-default" >当月</a>
	                <a href="javascript:;" mark="thirmonth" class="btn btn-sm btn-default" >三个月</a>
				</div>
				<div class="panel">
					<div class="chars charsStu" style="width:1060px;min-height: 200px;"></div>
				</div>
				<div id="tabs" style="float: right;">
				    <a href="javascript:;" class="btn btn-sm btn-default">手机端:<b>0</b>人</a>
					<a href="javascript:;" class="btn btn-sm btn-default">线下录入:<b>0</b>人
					</a> <a href="javascript:;" class="btn btn-sm btn-default">官网注册:<b>0</b>人
					</a> <a href="javascript:;" class="btn btn-sm btn-default">合计新增学员:<b>0</b>人
					</a>
				</div>
			</div>
			<!-- 订单统计 -->
			<div class="c-list" style="margin-top:20px;margin-bottom: 40px;">
				<div id="dateListOrder">
					<a href="javascript:;" mark="sevnday" class="btn btn-sm btn-default btn-success" >7天</a>
	                <a href="javascript:;" mark="thirty" class="btn btn-sm btn-default" >当月</a>
	                <a href="javascript:;" mark="thirmonth" class="btn btn-sm btn-default" >三个月</a>
				</div>
				<div class="panel">
					<div class="chars charsOrder" style="width:1060px;min-height: 200px;"></div>
				</div>
				 <div id="tabsOrder" style="float: right;">
	                <a href="javascript:;" class="btn btn-sm btn-default">报名人次：<b>0</b>人次</a>
	                <a href="javascript:;" class="btn btn-sm btn-default">合计应缴：￥<b>0</b></a>
	            </div>
			</div>
		</div>
	</div>
</div>

<div style="display: none;">
<form id="gonext" action="gonext" >

</form>
<div class="main">
<div class="u-wrap student main-fixed">
    <div class="mainbackground">
        <div class="center-search">
            <p class="c">
                <input id="mobile" type="text" class="input-control" value="" placeholder="输入学员手机号">
                <a  id="search" href="javascript:void(0)" class="btn btn-sm btn-default">搜索</a>
            </p>
            <p class="c tips"><span class="ps"></span></p>
        </div>
    </div>
</div>
</div>
</div>
<!-- footer start -->

<!-- footer end wqwqw-->
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/companytotal.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/companyorder.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/student.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/student/index.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/sidebar.js"></script>
</body>
</html>