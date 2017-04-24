<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:forEach items="${itemTwos }" var="itemTwo">
	<a href="javascript:;" fType="itemTwo" fValue="${itemTwo.id }" onmousedown="toExcute('itemTwo',${itemTwo.id });" class="btn btn-link btn-default">${itemTwo.itemName }</a>
</c:forEach>