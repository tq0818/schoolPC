<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <title>新手导航</title>
    <%@include file="/decorators/import.jsp"%>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
   	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
   	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/help.css">
	<script type="text/javascript" src="/manage/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript">
	var second=3;
	var timer;
	function change()
	{
	  second--;
	 
	 if(second>-1)
	 {
	  document.getElementById("second").innerHTML=second;
	  timer = setTimeout('change()',1000);//调用自身实现
	 }
	 else
	 {
		 window.location.href=rootPath+"/companyMemberService/showFace";
	   clearTimeout(timer);
	 }
	}
	timer = setTimeout('change()',1000);
	</script>
    
</head>
<body>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">    
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe621;</i>帮助</a>
        <ul class="nav nav-left navspace">
            <li><a href="javascript:;" class="active">新手任务</a></li>
        </ul>
    </div>
</div>

<div class="u-wrap help">
    <div class="mainbackground nopadding" >
        <div class="help-list" style="height:420px" >
        	<div style="height:130px"></div>
        	<div>
        	<h5 align="center" style="color:gray">您当前使用的是试用版，试用有效期已过，请购买适合您的版本。</h5>
        	<br/>
        	<div align="center">
				<h6 style="color:gray">页面
				<span style="color:red" id="second">3</span>秒后跳转到开通服务</h6>
        	</div>
        	</div>
        </div>
    </div>
</div>

</body>
</html>