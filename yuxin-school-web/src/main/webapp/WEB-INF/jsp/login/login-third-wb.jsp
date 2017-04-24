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
						<i>微博登录设置<i class="iconfont ask wb" title="如何申请使用微博帐号登录">&#xe60f;</i></i>
					</h3>
				</div>
				<div class="login-set clear">
					<div class="set-title fl">APP Key</div>
					<div class="set-cont fl">
						<input type="text" id="wbId" value="${clc.weiboKey }" maxlength="32"/>
					</div>
				</div>
				<div class="login-set clear">
					<div class="set-title fl">APP Secret</div>
					<div class="set-cont fl">
						<input type="text" id="wbKey" value="${clc.weiboSercet }" maxlength="32"/>
					</div>
				</div>
				<div class="login-set clear">
					<div class="set-title fl">登录接口验证代码</div>
					<div class="set-cont fl">
						<textarea name="" id="weiboValidateCode" cols="30" rows="10" maxlength="100">${clc.weiboValidateCode }</textarea>
						<p class="prompt-font">说明：在申请第三方登录接口时，会要求验证您的网站域名。请把相关验证代码粘到此处，然后提交保存。<br>
							示例：“ &lt;meta property="wb:webmaster" content="e1e38124adb9218d" &gt; ”
						</p>
						<%-- <a href=" http://open.weibo.com/authentication" class="login-port">申请微博登录接口接入帮助 http://open.weibo.com/authentication/</a> --%>
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
				<div class="btn-box button none">
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