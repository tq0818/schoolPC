<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课表查看</title> 
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/minitip.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_newsystem.jsp"></jsp:include>
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">课表查看权限</span>
                </div>
            </div>
            <p class="prompt-font">说明：当直播课，面授课排课后，允许有权限学员对其进行查看。</p>
            <div class="timetable-box-padd coursewacthlist">
                <div class="timetable-box buy_courses" ids="" type="USER_BUY_THIS" tent="购买本课程学员">
                    <span class="iconfont">&#xe676;</span>
                    <span class="timetable-btn-tit">购买本课程学员</span>
                </div>
                <div class="timetable-box buy_anycourses" ids="" type="USER_BUY_OTHER" tent="所有付费学员">
                    <span class="iconfont">&#xe678;</span>
                    <span class="timetable-btn-tit">所有付费学员</span>
                </div>
                <div class="timetable-box login_users" ids="" type="USER_LOGIN" tent="所有登录用户">
                    <span class="iconfont">&#xe679;</span>
                    <span class="timetable-btn-tit">所有登录用户</span>
                </div>
                <div class="timetable-box all_users" ids="" type="USER_ALL" tent="所有用户">
                    <span class="iconfont">&#xe675;</span>
                    <span class="timetable-btn-tit">所有用户</span>
                </div>
            </div>
            <div class="clear"></div>
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
    	$(function(){
    		$selectSubMenu('org_service');
    		$selectSubMenus("course_list");
    	})
    </script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/class/manageCourseList.js"></script>
</body>

</html>