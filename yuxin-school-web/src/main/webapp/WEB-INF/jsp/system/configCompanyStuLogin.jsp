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
						<i class="t">学员登录设置</i>
					</h3>
				</div>
				<h2 class="l-title">
					<i>同一账号多处同时登录</i>
					<c:choose>
						<c:when test="${'0' eq clc.multiOnline}">
							<em class="iconfont normal close">&#xe604;</em>
							<span id="" class="i close" kind="multiOnline">已禁用</span>
						</c:when>
						<c:otherwise>
							<em class="iconfont normal open">&#xe603;</em>
							<span id="" class="i open" kind="multiOnline">已启用</span>
						</c:otherwise>
					</c:choose>

				</h2>


				<div class="screen-right-title">
					<h3>
						<i class="t">学员登录设置</i>
					</h3>
				</div>

				<div class="third-login">
					<h2 class="l-title">
						<i>第三方登录</i>
						<c:choose>
							<c:when test="${'0' eq clc.thirdLoginFlag}">
								<em class="iconfont normal close ">&#xe604;</em>
								<span id="" class="i close" kind="thirdLoginFlag">已禁用</span>
							</c:when>
							<c:otherwise>
								<em class="iconfont normal open ">&#xe603;</em>
								<span id="" class="i open" kind="thirdLoginFlag">已启用</span>
							</c:otherwise>
						</c:choose>
					</h2>
					<p class="prompt-font">说明：开启后进行相应设置，学员可以通过QQ、微信、微博账号登录网校。</p>
					<div class="pay-box paddres thirdLoginFlagDiv none">
						<div class="pay-btn c mr-40">
							<div class="login-type">
								<i class="iconfont qq">&#xe690;</i>
								<p>QQ登录</p>
							</div>
							<div class="yes-btn start mt-30 QQ" startName="qq">启用</div>
							<div class="ban-box mt-35">
								<div class="ban-btn" delName="qq">禁用</div>
								<div class="set-btn" onclick="window.location.href='<%=rootPath%>/companyFunctionSet/setThirdLogin/qq'">设置</div>
							</div>
							<img src="<%=rootPath%>/images/choosed-1_03.png" alt="" class="choose-img">
						</div>
						<div class="pay-btn c mr-40">
							<div class="login-type">
								<i class="iconfont wx">&#xe691;</i>
								<p>微信登录</p>
							</div>
							<div class="yes-btn start mt-30 WX" startName="wx">启用</div>
							<div class="ban-box mt-35">
								<div class="ban-btn" delName="wx">禁用</div>
								<div class="set-btn" onclick="window.location.href='<%=rootPath%>/companyFunctionSet/setThirdLogin/wx'">设置</div>
							</div>
							<img src="<%=rootPath%>/images/choosed-1_03.png" alt="" class="choose-img">
						</div>
						<div class="pay-btn c mr-40">
							<div class="login-type">
								<i class="iconfont wb">&#xe692;</i>
								<p>微博登录</p>
							</div>
							<div class="yes-btn start mt-30 WB" startName="wb">启用</div>
							<div class="ban-box mt-35">
								<div class="ban-btn" delName="wb">禁用</div>
								<div class="set-btn" onclick="window.location.href='<%=rootPath%>/companyFunctionSet/setThirdLogin/wb'">设置</div>
							</div>
							<img src="<%=rootPath%>/images/choosed-1_03.png" alt="" class="choose-img">
						</div>
					</div>
					<div class="login-mobile thirdLoginFlagDiv none">
						<h2 class="l-title login-mobile-message">
							<i>第三方登录绑定手机号</i>
						</h2>
						<p class="prompt-font login-mobile-message">说明：此功能是设置，当学员通过第三方登录成功后，是否必须绑定手机号。</p>
						<div class="clear"></div>
						<div class="pay-box paddres">
							<div class="pay-btn c mr-40 must" kind="1">
								<div class="bind-mobile">
									<p>登录后必须绑定手机号</p>
								</div>
							</div>
							<div class="pay-btn c mr-40 notmust" kind="0">
								<div class="bind-mobile">
									<p>登录后可不绑定手机号</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="third-login">
					<h2 class="l-title">
						<i>独立登录、注册页面</i>
						<c:choose>
						<c:when test="${clc.useLoginPage == 1}">
							<em class="iconfont normal open">&#xe603;</em>
							<span id="" class="i open" kind="useLoginPage">已启用</span>
						</c:when>
						<c:otherwise>
							<em class="iconfont normal close">&#xe604;</em>
							<span id="" class="i close" kind="useLoginPage">已禁用</span>
						</c:otherwise>
					</c:choose>
					</h2>
					<p class="prompt-font">说明：开启此功能后，在网校的页头点击 “登录”、“注册” 会进入独立的登录、注册页面。</p>
					<p style="padding-left: 30px;"><input type="checkbox" id="global"/>全部使用独立登录、注册页面</p>
				</div>
				
			</div>
		</div>
	</div>
	<%--同时登陆--%>
	<input type="hidden" id="multiOnline" value="${clc.multiOnline}" />
	<%--第三方登陆--%>
	<input type="hidden" id="thirdLoginFlag" value="${clc.thirdLoginFlag}" />
	
	<input type="hidden" id="useLoginPage" value="${clc.useLoginPage}" />
	<%--QQ--%>
	<input type="hidden" id="qqLogin" value="${clc.qqLogin==null?0:clc.qqLogin }" />
	<%--WB--%>
	<input type="hidden" id="wbLogin" value="${clc.weiboLogin==null?0:clc.weiboLogin }" />
	<%--WX--%>
	<input type="hidden" id="wxLogin" value="${clc.wechatLogin==null?0:clc.wechatLogin }" />
	<%--绑定手机--%>
	<input type="hidden" id="mobileKind" value="${clc.bingMobile }" />

	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/login-third.js"></script>
</body>
</html>