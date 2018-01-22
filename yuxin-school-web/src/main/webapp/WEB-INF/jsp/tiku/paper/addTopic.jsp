<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加试题</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/paper/addTopic.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
    <style type="text/css">
    	.mintitle{float:right;margin-right:40px;margin-top:-14px}
    	.mintitle-t{margin-top:-20px;margin-right:-10px;}
    	.btn-paper{margin-right:5px}
    	.main-content{height: 100%;min-height: 350px; margin: 0 13px;}
    	.main-content {height:100%;min-height: 350px;}
		.steps ul li.step3 {width: 50%;text-align: center}
		.steps ul li.step3.s1 {left: 0;z-index: 9}
		.steps ul li.step3.s2 {left: 50%;z-index: 8}
		.steps ul li.step3.s2:after {background-image: none;background-color: #ddd}
		.steps ul li.step3.s2.active:after, .steps ul li.step3.s2.hover:after {background-image: none;background-color: #04a2ca}
		.steps ul li:after {content: "\20";position: absolute;top: 0;right: -30px;width: 30px;height: 30px;background-image: url(../images/step-after.png);background-repeat: no-repeat;background-position: 0 -64px;z-index: 9}
		.steps ul li.active:after {content: "\20";position: absolute;top: 0;right: -30px;idth: 30px;height: 30px;background-image: url(../images/step-after.png);background-repeat: no-repeat;background-position: 0 -32px;z-index: 9}
		.steps ul li.hover:after {content: "\20";position: absolute;top: 0;right: -30px;width: 30px;height: 30px;background-color: #ddd;background-image: url(../images/step-after.png);background-repeat: no-repeat;background-position: 0 0;z-index: 9}
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/tiku/tikuHeader.jsp"></jsp:include>
<div class="u-wrap classes">
    <div class="nopadding">
        <div class="steps">
            <div class="line"></div>
            <ul class="clear">
                <li class="step3 s1 active">
                    <i>01</i>
                    <em>试卷基本信息</em>
                </li>
                <li class="step3 s2 hover">
                    <i>02</i>
                    <em>添加试题</em>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="u-wrap tiku">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">添加试题</h2>
            <span class="line"></span>
            <a href="javascript:;" class="btn btn-mini btn-default btn-cancel mintitle mintitle-t" >返回</a>
            <c:if test="${set.paperAuditYes == 1 && set.paperAuditNo == 0}">
            	<c:if test="${btn == 'edit' || btn == 'add'}">
           			<a href="javascript:;" class="btn btn-mini btn-primary btn-paper mintitle mintitle-t" data-commit="commit">提交审核</a>
            	</c:if>
            	<c:if test="${btn == 'audite' }">
           			<a href="javascript:;" class="btn btn-mini btn-primary btn-paper mintitle mintitle-t" data-commit="no">审核不通过</a>
           			<a href="javascript:;" class="btn btn-mini btn-primary btn-paper mintitle mintitle-t" data-commit="ok">审核通过</a>
            	</c:if>
            </c:if>
            <c:if test="${set.paperAuditYes == 0 && set.paperAuditNo == 1}">
           		<a href="javascript:;" class="btn btn-mini btn-primary btn-paper mintitle mintitle-t" data-commit="publish">发布</a>
            </c:if>
            <span class="mintitle">编辑：${userName }</span>
            <span class="mintitle">${sub.subjectName }</span>
            <span class="mintitle" style="margin-right: 100px;">${cate.tikuName }</span>
        </div>
		<input type="hidden" value="${paper.tikuCategoryId }" id="tikuId"/>
        <input type="hidden" value="${paper.id }" id="paperId"/>
        <input type="hidden" value="${paper.tikuCategoryId }" id="cateId">
        <input type="hidden" value="${paper.tkuSubjectId }" id="subId">
        <p>
        	<c:forEach var="t" items="${topicType }">
        		<c:if test="${t == 'TOPIC_TYPE_RADIO' }">
            		<a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_RADIO">单选题</a>
        		</c:if>
        		<c:if test="${t == 'TOPIC_TYPE_MULTIPLE' }">
            		<a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_MULTIPLE">多选题</a>
        		</c:if>
        		<c:if test="${t == 'TOPIC_TYPE_TRUE_FALSE' }">
            		<a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_TRUE_FALSE">判断题</a>
        		</c:if>
        		<c:if test="${t == 'TOPIC_TYPE_ANSWER' }">
            		<a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_ANSWER">简答题</a>
        		</c:if>
        		<c:if test="${t == 'TOPIC_TYPE_UNDEFINED' }">
            		<a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_UNDEFINED">不定项</a>
        		</c:if>
        		<c:if test="${t == 'TOPIC_TYPE_FILLING' }">
            		<a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_FILLING">填空题</a>
        		</c:if>
        		<c:if test="${t == 'TOPIC_TYPE_CASE' }">
            		<a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_CASE">材料题</a>
        		</c:if>
        	</c:forEach>
        </p>
        <div class="table-list">
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
		var tikuId = $("#cateId").val();
		$(".btn-cancel").attr("href",rootPath+"/tikuPaper/toTikuPaper/"+tikuId);
	});
</script>
</body>
</html>