<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:forEach items="${secItemList}" var="sec">
	<option value="${sec.id}">${sec.itemName}</option>
</c:forEach>