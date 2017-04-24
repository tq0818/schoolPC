<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>在线直播</title>
    <%@include file="/decorators/import.jsp" %>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/crypto.js"></script>
	<script src="//view.csslcloud.net/js/_fix.js"></script>
<script type="text/javascript">
function getHeight(){
	var h = document.documentElement.clientHeight;
	$("#divHeight").attr("style","height:"+h+"px");
	var url = decrypt($("#url").val());
	var p = $("#p").val();
	$("#divHeight").html("<iframe width='100%' height='100%' src='" + url + p +"'></iframe>");
}
</script>
</head>
<body onload="javascript:getHeight();" style="margin:0px;padding:0px;overflow:hidden">
	<input type="hidden" value="${url }" id="url"/>
	<input type="hidden" value="${p }" id="p"/>
	<div id="divHeight" style="height:0px;">
	</div>
</body>
</html>