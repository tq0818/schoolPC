<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/decorators/import.jsp" %>
<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/question/edit.js?_=(new Date())"></script>
<input type="hidden" value="${sub.categoryId }" id="categoryId"/>
<input type="hidden" value="${sub.id }" id="subId">
<input type="hidden" value="${topicVo.point.chapterId }" id="chapterId">
<input type="hidden" value="${topicVo.point.sectionId }" id="sectionId">
<input type="hidden" value="${topicVo.point.pointId }" id="pointId">
<input type="hidden" value="${parentId }" id="parentId">
<input type="hidden" value="${types }" id="topicType">
<input type="hidden" value="${btn }" id="btn">
<input type="hidden" value="${status }" id="status">
	<!-- 单选题 -->
	<c:if test="${types == 'TOPIC_TYPE_RADIO' }">
		<jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicRadio.jsp"/>
	</c:if>
	<!-- 多选题 -->
	<c:if test="${types == 'TOPIC_TYPE_MULTIPLE' }">
		<jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicMultiple.jsp"/>
	</c:if>
	<!-- 不定项 -->
	<c:if test="${types == 'TOPIC_TYPE_UNDEFINED' }">
		<jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicUndefined.jsp"/>
	</c:if>
	<!-- 判断题 -->
	<c:if test="${types == 'TOPIC_TYPE_TRUE_FALSE' }">
		<jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicTrueFalse.jsp"/>
	</c:if>
	<!-- 简答题 -->
	<c:if test="${types == 'TOPIC_TYPE_ANSWER' }">
		<jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicAnswer.jsp"/>
	</c:if>
	<!-- 填空题 -->
	<c:if test="${types == 'TOPIC_TYPE_FILLING' }">
		<jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicFilling.jsp"/>
	</c:if>
	<!-- 材料题 -->
	<c:if test="${types == 'TOPIC_TYPE_CASE' }">
		<jsp:include page="/WEB-INF/jsp/tiku/question/questionType/topicCase.jsp"/>
	</c:if>
<div class="add-topicname-ckeditor" style="z-index: 98; display: block;" data-select="">
	<textarea rows="" cols="" id="createQues"></textarea>
	<p class="c text-center">
		<a href="javascript:;" class="btn btn-primary btn-topicNameOk">确定</a>
		<a href="javascript:;" class="btn btn-default btn-topicNameCancel">取消</a>
	</p>
</div>
<script type="text/javascript">
    var rec = CKEDITOR.replace('createQues');
    rec.config.width="521px";
    rec.config.toolbar = [
                    [ 'mode', 'document', 'doctools' ],
                    [ 'Source', ],
                    [ 'basicstyles', 'cleanup' ],
                    [ 'Bold', 'Italic', 'Underline', 'Strike' ],
                    [ 'NumberedList', 'BulletedList','JustifyLeft', 'JustifyCenter', 'JustifyRight','JustifyBlock' ], [ 'Link', 'Unlink' ],
                    [ 'Image', 'Table' ],
                    [ 'Styles', 'Format', 'Font', 'FontSize' ], [ '-' ],
                    ['Maximize']
                    ];
    rec.config.baseFloatZIndex = 10100;
    rec.config.customConfig = 'config.js';
 </script>