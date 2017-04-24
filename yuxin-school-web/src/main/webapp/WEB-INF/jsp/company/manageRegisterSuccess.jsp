<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>

<% String rootPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />   
	<meta name="apple-mobile-web-app-capable" content="yes" />  
	<meta name="format-detection" content="telephone=no" /> 
    <script type="text/javascript">
        var rootPath = '<%=rootPath %>';
    </script>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/head.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/main.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/school-register.css"/>
    <title></title>
</head>
<body>
<div class="school-register">
    <header>
        <div class="head-title">
            <a id="goBack" href="Javascript:history.go(-2);void(0);" class="back"> <i class="iconfont">&#xe650;</i>返回</a>
            <p>网校注册成功</p>
        </div>
    </header>
    <main>
        <div class="school-register-success">
            <h1>恭喜您注册成功</h1>
            <p>您现在可以通过网页体验所有的网校功能</p>
            <p>体验网址：manage.yunduoketang.com</p>
        </div>
    </main>
</div>
<script type="text/javascript">
$(function(){
	var url = '${url}';
	//后退
	$("#goBack").bind('click',function(){
		if(window.history){
			window.history.go(-2);
		}else{
			window.location = url;
		}
	});
});
</script>
</body>
</html>
