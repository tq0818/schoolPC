<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>教室</title>
    <%@include file="/decorators/import.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/resource.css"/>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/resource.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/resource/classroom/classroom.js"></script>
	
<style type="text/css">
	#busLine{width:500px;}
	.sc-c-btn{width:100%;text-align:center}
</style>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
<div class="u-wrap resource">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">租用教室</h2>
            <div class="search">
                <a id="create" href="javascript:;" class="btn btn-mini btn-primary btn-add-classroom"><em class="iconfont">&#xe606;</em> 添加教室</a>
            </div>
            <%-- btn-add-class --%>
            <span class="line"></span>
        </div>        
        <div class="sc-list">
            <div class="sc-select">
                <span class="sc-select-title">校区选择</span>
                <c:forEach var="s" items="${sccList }">
                	<a href="javascript:;" class="btn btn-mini btn-success" data-id="${s.id }">${s.campusName }</a>
                </c:forEach>
            </div>
            <div class="sc-lists" style="min-height: 350px;position: relative;">  
                
            </div>
        </div>
    </div>
</div>
<!-- 添加教室 -->
<div class="add-teacher-room add-teacher">
</div>
<div class="add-subs-layer stop-subs" id="stopPanel">
		<p class="c">
			<span class="l-description"> <em class="alert-tips">还有以下课程在占用该教室，是否继续停用?</em>
			</span>
		</p>
		<p class="c">
			<table id="classHint">
			</table>
		</p>
    <p class="c text-center">
    	<a id="changeOk" href="javascript:;" class="btn btn-sm btn-default btn-primary" data-id="" data-status="">停用</a>
        <a href="javascript:;" class="btn btn-sm btn-default btn-cancel">取消</a>
    </p>
</div>
<div class="add-teacher-room-bg"></div>

<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script>
	$(function(){
		$selectSubMenu('resource_classroom');
	});
</script>
</body>
</html>