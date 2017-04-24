<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	.cke_button__questionfilling_icon{ width : 60px;}
</style>
</head>
<body>
<div class="u-wrap tiku">
    <div class="mainbackground nopadding">
    	<input type="hidden" value="${topicVo.id }" id="topicId"/>
        <div class="heading">
            <h2 class="h5">填空题</h2>
            <span class="line"></span>
            <div class="h-type">
                科目 <span class="c">${sub.subjectName }</span>
            </div>
        </div>
        <div class="qution-content">
            <div class="qution-control">
		        <span class="qution-title">题目</span>
		        <div style="margin-left:100px">
		       		<textarea rows="" cols="" id="quesContent">${topicVo.topicNameBlank }</textarea>
		    	</div>
		    </div>
    
            <jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicAnalytical.jsp"/>
            <jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicSubmit.jsp"/>
        </div>
    </div>
</div>
<script type="text/javascript">
	var answer = CKEDITOR.replace('quesContent');
	answer.config.width="820px";
	answer.config.toolbar = [
			[ 'mode', 'document', 'doctools' ],
			[ 'Source', ],
			[ 'basicstyles', 'cleanup' ],
			[ 'Bold', 'Italic', 'Underline', 'Strike' ],
			[ 'NumberedList', 'BulletedList',
					'JustifyLeft', 'JustifyCenter', 'JustifyRight',
					'JustifyBlock' ], [ 'Link', 'Unlink' ],
			[ 'Image', 'Table' ],
			['Maximize'],
			['questionFilling']
			];
	answer.config.baseFloatZIndex = 10100;
	answer.config.customConfig = 'config.js';
	
</script>
</body>
</html>