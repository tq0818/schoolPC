<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>学员通知</title>
    <%@include file="/decorators/import.jsp" %>
   
<script type="text/javascript">
var affiche = '${goAffiche}';

function test(){
	$("a.btn-success").removeClass("btn-success");
	$("#affiche").removeClass();
	$("#affiche").attr("class","btn btn-mini btn-notice btn-success");
}

</script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<input type="hidden" value="10" id="pageSize"/>
	<div class="u-wrap student">
    <div class="mainbackground">
        <div class="main-content nospace">
            <div class="classes-type">
                <p class="c">
                    <span class="t-title">通知类型</span>
                    <span class="t-content">
                    	<a href="javascript:;" class="btn btn-mini btn-notice btn-success" data-type="all">全部</a>
                        <a href="javascript:;" class="btn btn-mini btn-notice" data-type="STUDENT_MESSAGE_CLASSTYPE">课程通知</a>
                        <c:if test="${classMoreStatus == 1 and CURRENT_IS_AREA eq 0}">
                        	<a href="javascript:;" class="btn btn-mini btn-notice" data-type="STUDENT_MESSAGE_MODULENO">班号通知</a>
                        </c:if>
                        <c:if test="${CURRENT_IS_AREA eq 0}">
                        <a href="javascript:;" class="btn btn-mini btn-notice" data-type="STUDENT_MESSAGE_SPECIAL">指定通知</a>
                        <a href="javascript:;" class="btn btn-mini btn-notice" data-type="STUDENT_MESSAGE_GROUP">分组通知</a>
                        </c:if>
                        <a id="affiche" href="javascript:;" class="btn btn-mini btn-notice" data-type="STUDENT_MESSAGE_AFFICHE">公告</a>
                        <c:if test="${CURRENT_IS_AREA eq 0}">
                        <a href="javascript:;" class="btn btn-mini btn-notice" data-type="STUDENT_MESSAGE_WEIXIN">微信指定通知</a>
                        </c:if>
                    </span>
                </p>
                <p class="c courseFlag">
                    <span class="t-title">分类</span>
                    <span class="t-content" id="one">
                        <a href="javascript:;" class="btn btn-mini btn-one btn-success" data-id="">全部</a>
                    	<c:forEach var="o" items="${oneItem }">
                            <a href="javascript:;" class="btn btn-mini btn-one" data-id="${o.itemCode }">${o.itemName }</a>
                        </c:forEach>
                    </span>
                </p>
                <p class="c stepFlag">
                    <span class="t-title">学段</span>
                    <span class="t-content" id="two">
                    	<a href="javascript:;" class="btn btn-mini btn-two btn-success" data-id="">全部</a>
                    	<c:forEach var="o" items="${twoItem }">
                        	<a href="javascript:;" class="btn btn-mini btn-two btn-method " data-id="${o.itemCode }">${o.itemName }</a>
                    	</c:forEach>
                    </span>
                </p>
                <p class="c subjectFlag">
                    <span class="t-title">学科</span>
                    <span class="t-content" id="three">
                        <a href="javascript:;" class="btn btn-mini btn-three btn-success" data-id="">全部</a>
                    	<c:forEach var="o" items="${threeItem }">
                            <a href="javascript:;" class="btn btn-mini  btn-method " data-id="${o.itemCode }">${o.itemName }</a>
                        </c:forEach>
                    </span>
                </p>
                <p class="c typeFlag">
                    <span class="t-title">通知方式</span>
                    <span class="t-content">
                        <a href="javascript:;" class="btn btn-mini btn-method btn-success" data-type="all">全部</a>
                        <%--<c:if test="${CURRENT_IS_AREA eq 0}">--%>
                        	<a href="javascript:;" class="btn btn-mini btn-method" data-type="STUDENT_MESSAGE_MOBILE">短信</a>
                        <%--</c:if>--%>
                        <a href="javascript:;" class="btn btn-mini btn-method" data-type="STUDENT_MESSAGE_WEB">站内信</a>
                        <c:if test="${CURRENT_IS_AREA eq 0}">
                        	<a href="javascript:;" class="btn btn-mini btn-method" data-type="STUDENT_MESSAGE_EMAIL">邮件</a>
                        </c:if>
                    </span>
                </p>
            </div>
        </div>
    </div>
</div>
<div class="u-wrap student">
    <div class="mainbackground">
        <div class="heading">
            <h2 id="studentNotice" class="h5">学员通知</h2>
            <div class="user-infos">
                <a id="addStudentNotice" href="javascript:goAddPage()" class="btn btn-mini btn-priamry" style="background-color: #237fd5;color: #fff;"><em class="iconfont">&#xe606;</em>新建学员通知</a>
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
  <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/student.css"/> 
     <script type="text/javascript" src="<%=rootPath%>/javascripts/student.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student/notice/notice.js"></script>
</body>
</html>