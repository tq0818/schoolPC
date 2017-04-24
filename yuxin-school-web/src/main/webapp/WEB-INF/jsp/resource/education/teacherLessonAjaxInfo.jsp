<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:if test="${!empty lesson}">
	<p class="c">
	        <span>该教师还有以下课次未结束：</span>
	</p>
<c:forEach items="${lesson}" var="l">
    
    <p class="c">
        <span class="c-title">${l.lessonName}</span>
    </p>
</c:forEach>
<p class="c">
	        <span>是否继续删除？</span>
</p>
</c:if>
<c:if test="${empty lesson}">
<p class="c">
	        <span>该教师没有日后的课次安排</span>
</p>
<p class="c">
	        <span>点击继续执行删除操作</span>
</p>
</c:if>

