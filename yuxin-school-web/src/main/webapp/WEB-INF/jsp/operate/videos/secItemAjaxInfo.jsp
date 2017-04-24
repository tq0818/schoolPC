<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<a class="btn btn-sm btn-default secItem active" href="javascript:;" itemId="">全部</a>
<c:forEach items="${secItemList}" var="sec">
	<a class="btn btn-sm btn-default secItem" href="javascript:;" itemId="${sec.id}">${sec.itemName}</a>
</c:forEach>