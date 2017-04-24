<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>订单管理</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
    
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/coursePackage/commonTitle.jsp" %>
<div class="u-wrap company overflow">
	<%@include file="/WEB-INF/jsp/coursePackage/commonClass.jsp" %>
    <div class="right-side" style="background-color: white;">
   
		    <div class="mainbackground nopadding" style="min-height: 546px;">
		        <div class="heading clear">
		            <h2 class="h5" style="float:left;">订单管理</h2>
		            <span class="line"></span>
		        </div>
		        <form method="post" id="searchForm">
		        <div>
		            <input type="hidden" id="classPackageId"  name="classPackageId" value="${classPackage.id }"/>
		        	<span style="margin-left:15px" id="timeMarking">
		        		<a href="javascript:void(0);" class="choose">全部</a>
		        		<a href="javascript:void(0);" mark="sevnday">最近七天</a>
		        	</span>
		        	<span style="margin-left: 10px;"><input type="text" name="startTime" style="width: 80px;" placeholder="开始时间" class="date-picker from"/><em>至</em><input type="text" style="width: 80px;" placeholder="截止时间" name="endTime" class="date-picker to"/></span>
		        	<select id="stageNames" name="stageName">
		        		
		        	</select>
		        	<input type="text" id="stuName" name="name" placeholder="学员姓名" style="width:100px;"/>
		        	<span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
		        </div>
		        </form>
		        <div style="margin-top: 10px;text-align:right;padding:0 10px;">
		        	<span>已付款额:&nbsp;&nbsp;<span id="payedOrder">0.00</span></span>
		        	<span style="margin-left:5px;">欠款:&nbsp;&nbsp;<span id="paying">0.00</span></span>
		        	<span style="margin-left:5px;">总金额:&nbsp;&nbsp;<span id="payTotal">0.00</span></span>
		        </div>
		        <div class="user-list" style="margin-top: 5px;">
		          	<table class="table table-center" id="tableList">
						<tr>
							<th width="10%">手机号</th>
							<th width="10%">姓名</th>
							<th width="10%">用户名</th>
							<th width="10%">订单时间</th>
							<th width="10%">支付时间</th>
							<th width="10%">来源</th>
							<th width="10%">支付方式</th>
							<th width="15%">总金额</th>
							<th width="15%">已付款额</th>
						</tr>
						<tr><td colspan="9">暂无数据</td></tr>
				</table>
					<div class="pages pagination"></div>
		        </div>
		    </div>
	 
    </div>
</div>


<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->

<script type="text/javascript">
$(document).ready(function(){
	$selectMenu("course_package");
	$chooseMenu("ordersCode");
});
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/cousePackage/orderManage.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
</html>