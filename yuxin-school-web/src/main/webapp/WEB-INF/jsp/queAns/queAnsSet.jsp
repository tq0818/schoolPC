<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>课程问答</title>
    <%@include file="/decorators/import.jsp"%>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/minitip.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
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
                    <span class="t">课程问答功能</span>
                    <em class="iconfont normal ${ansIsOpenStatus == 1?'open':'close' }" code="COURSE_QUESTION_FUNCTION">${ansIsOpenStatus == 1?'&#xe603;':'&#xe604;' }</em>
                    <span id="" class="i ${ansIsOpenStatus == 1?'open':'close' }">${ansIsOpenStatus == 1?'已启用':'已禁用' }</span>
                </div>
            </div>
            <p class="prompt-font">说明：开启该功能后学员可以在学习课程时进行提问，也可以在拥有一定权限的情况下查看他人的提问，并进行回复</p>
            <div id="ansShow" style="${ansIsOpenStatus == 1?'display:block;':'display:none;' }">
            	<div class="pad-box">
	                <div>问答查看权限</div>
	            </div>
	            <div class="timetable-box-padd marg-b">
	                <div class="timetable-box ${lookStatus == 1?'active':'' }" status="1">
	                    <span class="iconfont">&#xe676;</span>
	                    <span class="timetable-btn-tit">购买本课程学员</span>
	                </div>
	                <div class="timetable-box qs1 ${lookStatus == 2?'active':'' }" status="2">
	                    <span class="iconfont">&#xe678;</span>
	                    <span class="timetable-btn-tit">所有付费学员</span>
	                </div>
	                <div class="timetable-box qs2 ${lookStatus == 3?'active':'' }" status="3">
	                    <span class="iconfont">&#xe679;</span>
	                    <span class="timetable-btn-tit">所有登录用户</span>
	                </div>
	                <div class="timetable-box qs2 ${lookStatus == 4?'active':'' }" status="4">
	                    <span class="iconfont">&#xe675;</span>
	                    <span class="timetable-btn-tit">所有用户</span>
	                </div>
	            </div>
	            <div class="clear"></div>
	            <div class="pad-box">
	                <div>问答回复权限</div>
	            </div>
	            <div class="timetable-box-padd marg-b">
	                <div class="timetable-box qr qr0 ${ansStatus == 1?'active':'' }" status="1">
	                    <span class="iconfont">&#xe676;</span>
	                    <span class="timetable-btn-tit">购买本课程学员</span>
	                </div>
	                <div class="${lookStatus >= 2?'timetable-box':'timetables-box' } qr1 qr ${ansStatus == 2?'active':'' }" status="2">
	                    <span class="iconfont">&#xe678;</span>
	                    <span class="timetable-btn-tit">所有付费学员</span>
	                    <div class="disable-box iconfont" style="${lookStatus < 2?'display:block;':'display:none;' }">&#xe67a;</div>
	                </div>
	                <div class="${lookStatus >= 3?'timetable-box':'timetables-box' } qr2 qr ${ansStatus == 3?'active':'' }" status="3">
	                    <span class="iconfont">&#xe679;</span>
	                    <span class="timetable-btn-tit">所有登录用户</span>
	                    <div class="disable-box iconfont" style="${lookStatus < 3?'display:block;':'display:none;' }">&#xe67a;</div>
	                </div>
	            </div>
	            <div class="clear"></div>
            </div>
            <div class="pad-box">
	                <div class="pt20">
	                    <p class="tit-font">
	                        <span class="t">相关权限设置</span>
	                        <em class="iconfont auth normal ${authIsOpenStatus == 1?'open':'close' }" code="COURSE_QUESTION_AUTH">${authIsOpenStatus == 1?'&#xe603;':'&#xe604;' }</em>
	                        <span id="" class="i ${authIsOpenStatus == 1?'open':'close' }">${authIsOpenStatus == 1?'已启用':'已禁用' }</span>
	                    </p>
	                    <p class="prompts-font">说明：开启后，课程中的提问可以在问答社区展示</p>
	                </div>
            </div>
        </div>
    </div>
</div>

<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script src="<%=rootPath%>/javascripts/queAns/course-ques.js"></script>
<script type="text/javascript">
	$(function(){
		$selectSubMenu('org_service');
		$selectSubMenus("course_wd");
	})
</script>
</body>
</html>