<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>分校</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/resource.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/resource/school/school.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
<div class="u-wrap resource">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">分校</h2>
            <c:if test="${(cms.faceStudentAll - css.faceStudent) <= 100 }">
	            <div class="infos">
	                累积服务学员：<span><i class="last">${css.faceStudent }</i> / <i class="sum">${cms.faceStudentAll }</i></span>
	                <a href="javascript:;" class="tips" id="openFace">升级获得更多学员</a>
	            </div>
            </c:if>
            <span class="line"></span>
        </div>
		<div class="r-list">
            <ul class="clear">
            	<li>
            	<input type="hidden" value="${scs.id }"/>
                    <p class="r-title">${scs.schoolName }</p>
                    <p class="r-infos">
                        <span class="h">
                            <span class="r-r-title">建校时间:</span>
                            <span class="r-r-content"><fmt:formatDate value="${scs.createTime }" pattern="yyyy-MM-dd"/></span>
                        </span>
                        <span class="h">
                            <span class="r-r-title">教务人员:</span>
                            <span class="r-r-content"></span>人
                        </span>
                        <span class="h">
                            <span class="r-r-title">学科数:</span>
                            <span class="r-r-content"></span>个
                        </span>
                        <span class="h">
                            <span class="r-r-title">校区:</span>
                            <span class="r-r-content"></span>个
                        </span>
                        <span class="h">
                            <span class="r-r-title">老师数:</span>
                            <span class="r-r-content"></span>人
                        </span>
                        <span class="h">
                            <span class="r-r-title">线下教室:</span>
                            <span class="r-r-content"></span>间
                        </span>
                    </p>
                </li>
            </ul>
        </div>
     </div>
</div>

<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script>
	$(function(){
		$selectSubMenu('resource_school');
	});
</script>
</body>
</html>