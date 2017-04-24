<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>    
<c:forEach items="${terms }" var="term" varStatus="status">
	<a href="javascript:;"  fType="term" fValue="${term.id }" onmousedown="toExcute('term',${term.id });" class="btn btn-link btn-default" >${term.termName }</a>
</c:forEach>