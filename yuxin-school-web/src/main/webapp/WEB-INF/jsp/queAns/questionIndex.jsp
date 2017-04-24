<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程问答</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/queAns/questionIndex.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
    
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/resource.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/utils.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/allAswer.css" />
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<style type="text/css">
.add-classes {
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	width: 760px;
	height: 535px;
	margin-left: -400px;
	margin-top: -280px;
	padding: 10px 20px;
	background-color: #fafafa;
	z-index: 99
}

.add-classes .close {
	position: absolute;
	top: 5px;
	right: 0;
	font-size: 16px;
	font-size: 1.6rem;
	cursor: pointer
}

.add-classes-bg {
	display: none;
	position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	background-color: rgba(0, 0, 0, 0.4);
	z-index: 98
}

i.close {
	font-size: 12px;
	color: #aaa;
	margin-top: -6px;
	margin-left: 14px;
}

.cke {
	visibility: hidden;
}
</style>
</head>
<body style="position:relative;">
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<div class="u-wrap classes allAnswer">
    <div class="mainbackground nopadding">
        <div class="classes-type">
        	<p class="c clear">
                <span class="t-title fl">类型：</span>
                <span class="t-content fr" id="itemOneList">
					<a href="<%=rootPath %>/Question/comQuestionIndex" class="btn btn-mini btn-default">社区问答</a>
                	<c:if test="${!empty courseQuestionFunctionSet}">
                		<a href="<%=rootPath %>/Question/questionIndex" class="btn btn-mini btn-default btn-success">课程问答</a>
                	</c:if>
                </span>
            </p>
            <p class="c clear">
                <span class="t-title fl">学科：</span>
                <span class="t-content fr" id="itemOneList">
                	<a href="javascript:Form.queryItemSecond('');" class="btn btn-mini btn-default btn-success">全部</a>
                    <c:forEach items="${firstItems }" var="itemOne" varStatus="status">
	                <a href="javascript:Form.queryItemSecond(${itemOne.id });" ids="${itemOne.id }" class="btn btn-mini btn-default">${itemOne.itemName }</a>
                    </c:forEach>
                </span>
            </p>
            <p class="c clear itemSec">
                <span class="t-title fl">学科小类：</span>
                <span class="t-content fr" id="itemSecondList">
                	
                </span>
            </p>
            <p class="c clear">
                <span class="t-title fl">状态：</span>
                <span class="t-content fr">
                	<a href="javascript:void(0)" class="btn jz btn-mini btn-default btn-success" jz="">全部</a>
                	<a href="javascript:void(0)" class="btn jz btn-mini btn-default" jz="1">置顶</a>
                	<a href="javascript:void(0)" class="btn jz btn-mini btn-default" jz="2">精华</a>
                </span>
            </p>
        </div>
    </div>
</div>
<div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div id="questionList">
	        
        </div>
    </div>
</div>

<div class="add-classes">
	<div style="width: 100%;float: right;"><i class="iconfont close">&#xe610;</i></div>
	<div class="shimg" style="overflow: auto;width:100%;height:100%;text-align: center;">
		
	</div>
</div>
<div class="add-classes-bg"></div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript" src="<%=rootPath %>/javascripts/queAns/questionIndex.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script>
$(function(){
	$selectSubMenu('community_qa');
});
</script>
</body>
</html>