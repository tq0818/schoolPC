<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>示例</title>
<script type="text/javascript" src="<%=rootPath %>/plugins/ccUpload/upload_files/jquery-1.11.2.min.js"></script>
</head>
<body style=" overflow-x:hidden; margin:0; width:100%;">
	<img alt="" src="<%=rootPath %>${src}" style="width:1920px;" id="img">
</body>
<script>
	$("#img").css("margin-left",-(1920-$(window).outerWidth())/2)
</script>
</html>