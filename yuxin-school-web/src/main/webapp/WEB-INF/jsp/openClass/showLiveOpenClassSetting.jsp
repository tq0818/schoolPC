<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp"%>
	<title>公开课设置</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/minitip.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/system.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/splitscreen.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage-screen.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/login-third.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/footer.css" />
	<style>
	.open {
		color: #0099ff;
	}
	.close {
		color: #666;
	}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<div class="u-wrap company overflow">
		<jsp:include page="/WEB-INF/jsp/menu/menu_openclass.jsp"></jsp:include>
		<div class="screen-right">
			<div class="screen-right-cont">
				<div class="screen-right-title">
					<h3>
						<i class="t">公开课设置</i>
					</h3>
				</div>
				<h2 class="l-title needLogin" >
					<i>观看公开课需要登录</i>
					<c:choose>
						<c:when test="${'0' eq cfs.status}">
							<em class="iconfont normal close">&#xe604;</em>
							<span id="" class="i close" data-kind="needLogin" data-status="1">已禁用</span>
						</c:when>
						<c:otherwise>
							<em class="iconfont normal open">&#xe603;</em>
							<span id="" class="i open" data-kind="needLogin" data-status="0">已启用</span>
						</c:otherwise>
					</c:choose>

				</h2>
				<p class="prompt-font">说明：开启后,观看公开课需要登录。</p>
				
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/openClass/openClassSetting.js"></script>
</body>
</html>