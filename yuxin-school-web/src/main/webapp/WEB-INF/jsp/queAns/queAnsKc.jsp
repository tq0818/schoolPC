<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>问答设置</title>
    <%@include file="/decorators/import.jsp"%>
</head>
<body style="overflow: hidden;">
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<img class="kcIns" src="<%=rootPath%>/images/kcfl.png" style="margin-left: 4%"/>

<script type="text/javascript">
$(function(){
	var bodyWidth = $("body").width();
	console.log(bodyWidth)
	$("body").attr("style","overflow: hidden;")
	$(".kcIns").width(bodyWidth);
	$selectSubMenu('org_service');
})
</script>
</body>
</html>