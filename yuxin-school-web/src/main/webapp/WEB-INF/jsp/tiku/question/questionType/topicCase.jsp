<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/decorators/import.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="u-wrap tiku">
	<input type="hidden" value="${topic.id }" id="topicId"/>
	
    <div class="mainbackground nopadding">
        <div class="heading paperhead">
            <h2 class="h5">材料题</h2>
            <span class="line"></span>
            <div class="h-type">
              	  科目 <span class="c">${sub.subjectName }</span>
            </div>
        </div>
        <!-- 判断当前材料，是新增还是修改 -->
        <c:if test="${empty topic || flag == 'true'}">
			<%-- <jsp:include page="/WEB-INF/jsp/tiku/question/editCase1.jsp"/> --%>
			<span class="qution-title case-content" style="float: left;padding-left: 100px;">材料内容</span>
		     <div class="qution-content">
		         <div class="qution-control" style="padding-left:200px;">
		             <span class="qution-input">
		                 <textarea name="" id="quesContent" style="width:85%;height: 400px;">${topic.topicName }</textarea>
		             </span>
		         </div>
		         <div class="qution-control">
		             <p class="text-center">
		                 <jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicSubmit.jsp"/>
		             </p>
		         </div>
		     </div>
		     <script type="text/javascript" src="/plugins/ckeditor/ckeditor.js"></script>
        </c:if>
        
        <c:if test="${not empty topic && flag == 'false'}">
        	<jsp:include page="/WEB-INF/jsp/tiku/question/editCase2.jsp"/>
        </c:if>
        
        <!-- 如果是编辑材料，则加载当前材料下所有的试题 -->
   		<!-- 存放试题列表 -->
   		<div class="qustion-list">
   			<!-- 已经存在的试题列表 -->
   			<div id="existQuestion">
   			</div>
   			<!-- 新增试题 -->
   			<div id="addQuestionEdit">
   			
   			</div>
   		</div>
   		<c:if test="${not empty topic && flag == 'false'}">
   			<div class="qution-control">
	             <p class="text-center">
	                 <jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicSubmit.jsp"/>
	             </p>
	        </div>
   		</c:if>
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