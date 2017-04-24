<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:forEach items="${classTypeList }" var="classType">
	<a href="javascript:;" fType="classType" fValue="${classType.id }" onmousedown="toExcute('classType',${classType.id });" class="btn btn-link btn-default">${classType.name }</a>
</c:forEach>