<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/decorators/import.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<p>redis缓存总占内存大小:&nbsp;&nbsp;&nbsp;&nbsp;${totalMem }字节</p>
	<table border="1px" cellspacing="0px">
	<tr><td>键名称<td><td>所用字节大小<td></tr>
	<c:forEach items="${list }" var="item">
	<tr><td>${item.key }<td><td>${item.valueSize }<td></tr>
	</c:forEach>
	</table>
	</center>
</body>
</html>