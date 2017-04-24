<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp"%>
	<title>学员登录设置</title>
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
	.button {
		margin-top: 60px;
	}
	.ask {
	    margin-left: 3px;
 		color: #1cbdef;
	}
	.login-set .set-cont input {
		 width: 300px;
	}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<div class="u-wrap company overflow">
		<jsp:include page="/WEB-INF/jsp/menu/menu_studentconfig.jsp"></jsp:include>
		<div class="screen-right">
			<div class="screen-right-cont">
				<div class="screen-right-title">
					<h3>
						<i>PC网页端微信登录设置<i class="iconfont ask wx" title="如何申请使用微信帐号登录">&#xe60f;</i></i>
					</h3>
				</div>
				<div class="login-set clear">
					<div class="set-title fl">微信.开放平台&nbsp;&nbsp;AppID</div>
					<div class="set-cont fl">
						<input type="text" id="wxId" value="${clc.wechatAppid }" maxlength="32"/>
					</div>
				</div>
				<div class="login-set clear">
					<div class="set-title fl">微信.开放平台&nbsp;&nbsp;AppSecret</div>
					<div class="set-cont fl">
						<input type="text" id="wxKey" value="${clc.wechatKey }" maxlength="32"/>
					</div>
				</div>
				
				<div class="screen-right-title" style="margin-top:50px">
					<h3>
						<i>微信APP端网页登录设置<i class="iconfont ask openWeichat" title="如何申请使用微信帐号登录">&#xe60f;</i></i>
					</h3>
				</div>
				<div class="login-set clear">
					<div class="set-title fl">微信.公众平台&nbsp;&nbsp;AppID</div>
					<div class="set-cont fl">
						<input type="text" id="wxOpenId" value="${clc.wechatOpenAppid }" maxlength="32"/>
					</div>
				</div>
				<div class="login-set clear">
					<div class="set-title fl">微信.公众平台&nbsp;&nbsp;AppSecret</div>
					<div class="set-cont fl">
						<input type="text" id="wxOpenKey" value="${clc.wechatOpenSecret }" maxlength="32"/>
					</div>
				</div>
				
				<div class="btn-box button">
					<button class="manage-button manage-btn-success">
						保存<span class="manage-button-mask"></span>
					</button>
					<button class="manage-button manage-btn-cancel" onclick="window.location.href='javascript:history.go(-1)'">
						取消<span class="manage-button-mask"></span>
					</button>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="saveId" value="${kind }">
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/login-third-list.js"></script>
</body>
</html>