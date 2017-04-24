<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title></title>
<%@include file="/decorators/import.jsp" %>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
<style type="text/css">
	footer{
		margin-top: 38%;
	}
</style>
</head>
<body">
	<c:choose>
		<c:when test="${firstName eq 'student' }">
			<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
		</c:when>
		<c:when test="${firstName eq 'course' }">
			<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"/>
		</c:when>
		<c:when test="${firstName eq 'teaching' }">
			<jsp:include page="/WEB-INF/jsp/menu/menu_teach.jsp"/>
		</c:when>
		<c:when test="${firstName eq 'operate' }">
			<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"/>
		</c:when>
		<c:when test="${firstName eq 'tiku' }">
			<jsp:include page="/WEB-INF/jsp/menu/menu_tiku.jsp"/>
		</c:when>
		<c:when test="${firstName eq 'resource' }">
			<jsp:include page="/WEB-INF/jsp/menu/menu_resource.jsp"/>
		</c:when>
		<c:when test="${firstName eq 'system' }">
			<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"/>
		</c:when>
	</c:choose>
	<script type="text/javascript">
		$(function(){
			var code = $('.smbar ul li:eq(0)').attr('code');
			$selectSubMenu(code);
			var url = $('.smbar ul li:eq(0) a').attr('href');
			window.location.href = url; 
		});
	</script>
</body>
</html>