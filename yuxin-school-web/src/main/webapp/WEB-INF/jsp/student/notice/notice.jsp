<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>学员通知</title>
    <%@include file="/decorators/import.jsp" %>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/student.css"/> 
     <script type="text/javascript" src="<%=rootPath%>/javascripts/student.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student/notice/notice.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<input type="hidden" value="5" id="pageSize"/>
	<div class="u-wrap student">
    <div class="mainbackground">
        <div class="main-content nospace">
            <div class="classes-type">
                <p class="c">
                    <span class="t-title">通知类型</span>
                    <span class="t-content">
                    	<a href="javascript:;" class="btn btn-mini btn-notice btn-success" data-type="all">全部</a>
                        <a href="javascript:;" class="btn btn-mini btn-notice btn-link" data-type="STUDENT_MESSAGE_CLASSTYPE">课程通知</a>
                        <c:if test="${classMoreStatus == 1 }">
                        	<a href="javascript:;" class="btn btn-mini btn-notice btn-link" data-type="STUDENT_MESSAGE_MODULENO">班号通知</a>
                        </c:if>
                        <a href="javascript:;" class="btn btn-mini btn-notice btn-link" data-type="STUDENT_MESSAGE_SPECIAL">指定通知</a>
                        <a href="javascript:;" class="btn btn-mini btn-notice btn-link" data-type="STUDENT_MESSAGE_GROUP">分组通知</a>
                    </span>
                </p>
                <p class="c">
                    <span class="t-title">学科</span>
                    <span class="t-content">
                    	<a href="javascript:;" class="btn btn-mini btn-one btn-link" data-id="0">全部</a>
                    	<c:forEach var="o" items="${oneItem }">
                        	<a href="javascript:;" class="btn btn-mini btn-one btn-link" data-id="${o.id }">${o.itemName }</a>
                    	</c:forEach>
                    </span>
                </p>
                <p class="c">
                    <span class="t-title">学科小类</span>
                    <span class="t-content" id="two">
                    </span>
                </p>
                <p class="c">
                    <span class="t-title">通知方式</span>
                    <span class="t-content">
                        <a href="javascript:;" class="btn btn-mini btn-method btn-success" data-type="all">全部</a>
                        <a href="javascript:;" class="btn btn-mini btn-method btn-link" data-type="STUDENT_MESSAGE_MOBILE">短信</a>
                        <a href="javascript:;" class="btn btn-mini btn-method btn-link" data-type="STUDENT_MESSAGE_WEB">站内信</a>
                        <a href="javascript:;" class="btn btn-mini btn-method btn-link" data-type="STUDENT_MESSAGE_EMAIL">邮件</a>
                    </span>
                </p>
            </div>
        </div>
    </div>
</div>
<div class="u-wrap student">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">学员通知</h2>
            <div class="user-infos">
                <a href="<%=rootPath %>/student/createNotice" class="btn btn-mini btn-priamry" style="background-color: #237fd5;color: #fff;"><em class="iconfont">&#xe606;</em>新建学员通知</a>
            </div>
            <span class="line"></span>
        </div>
        <div class="main-content">
            <div class="notice-list">
                
            </div>
            <div class="pages">
                <ul class="pagination">
					
				</ul>
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
</body>
</html>