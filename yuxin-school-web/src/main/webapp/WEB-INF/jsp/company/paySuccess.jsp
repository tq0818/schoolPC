<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
    <title>支付成功</title>
    <link href="<%=rootPath%>/stylesheets/manage.css" rel="stylesheet"
	type="text/css" />
	<link href="<%=rootPath%>/stylesheets/company.css" rel="stylesheet"
	type="text/css" />
	<script src="<%=rootPath%>/javascripts/company.js" ></script>
	<link href="<%=rootPath%>/stylesheets/minitip.css" rel="stylesheet"
	type="text/css" />
	<script type="text/javascript"
	src="<%=rootPath%>/javascripts/plus/miniTip.js"></script>
	<script type="text/javascript">
	function countdown(secs,surl){
		$("#time").html(secs);//<span>中显示的内容值
		if(--secs>0){
		       setTimeout("countdown("+secs+",'"+surl+"')",1000);//设定超时时间
		       }
		else{
		       location.href=surl;//跳转页面
		       } 
		}
	var url = rootPath+"/company/companyService";
	countdown(7,url);
	</script>
</head>
<body>
<!-- header start -->
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>

<div class="u-wrap company main-fixed">
    <div class="mainbackground nopadding">
        <p class="t big-title t-tips"><i class="icon i1"></i>您已经支付成功，请去<a href="<%=rootPath%>/company/companyService">查看服务</a>是否开通。</p>
        <p class="text-center">
            <a href="<%=rootPath%>/company/companyService" class="btn btn-success">查看服务</a>
        </p>
        <p class="text-center">
            <label><span id="time" style="color: red;"></span>秒后跳转到开通页面</label>
        </p>
    </div>
</div>
<!-- footer start -->
<!-- footer end -->
<script type="text/javascript">
$(function(){
	$selectSubMenu('org_service');
})
</script>
</body>
</html>