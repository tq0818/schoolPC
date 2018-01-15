<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>

<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登录</title>
<script type="text/javascript">
		var rootPath='<%=rootPath%>';
</script>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css" />
<c:if test="${register_flag}">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/register.css" />
</c:if>
<c:if test="${register_flag==null || register_flag==false}">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/login.css" />
</c:if>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.cookie.js"></script>
</head>
<body>
	<!-- header start -->
		<div class="header">

		</div>
	<!-- header end -->
	<div class="main">
		<div class="content">
			<div class="caption" id="caption-login">
				<div class="image"></div>
			</div>
			<div class="box">
				<div class="panel login-panel">
				<div class="login-title">
					<h2 class="h6">机构登录</h2>
				</div>
				<form class="login-form" action="<%=rootPath%>/login" method="post">
					<div class="login-form1">
						<span class="tips">${info}</span>
						<div class="c" style="margin-top:10px;">
							<input type="text" id="username" name="username" class="input-ctrl" placeholder="用户名" value="lijt">
						</div>
						<div class="c">
							<input type="password" id="password" name="password"  class="input-ctrl" placeholder="密码" value="111111">
						</div>
						<div class="c none" id="codes">
								<input type="text" id="checkcode" name="checkcode" value="" placeholder="图像验证码" class="code input-sm"> 
								<span class="codeimg"></span><img class="iconfont rf" src="<%=rootPath%>/images/refresh.png"/>
						</div>
						<div class="s remeber">
								<span><input type="checkbox" id="rememberMe" name="rememberMe" value="1" checked=checked/> 记住我</span>
								<a href="<%=rootPath%>/users/findPwd" style="color: red; margin-left: 50px;">忘记密码?</a>
						</div>
						<div class="c">
							<button type="submit" class="btn btn-primary btn-login"> 登录</button>
						</div>
					</div>
				</form>
				</div>
			</div>
		</div>
		<div class="bot">
		
		</div>
	</div>
	<!-- footer start -->

	<!-- 测试提交 -->
	<!-- footer end -->
	<script type="text/javascript" src="<%=rootPath %>/javascripts/jquery.placeholder.min.js"></script>
	<script  type="text/javascript" src="<%=rootPath%>/javascripts/login.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/jquery-validation/jquery.validate.js"></script>
</body>
</html>