<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
<title>开通支付</title>
<%@include file="/decorators/import.jsp"%>
<link href="<%=rootPath%>/stylesheets/manage.css" rel="stylesheet"
	type="text/css" />
<link href="<%=rootPath%>/stylesheets/company.css" rel="stylesheet"
	type="text/css" />
<script src="<%=rootPath%>/javascripts/system.js"></script>
<script src="<%=rootPath%>/javascripts/company/companyDomain.js"></script>
<style type="text/css">
	.imgWidth{width: 800px;}
</style>
</head>
<body>
	<!-- header start -->
	<!-- header end -->
	<!-- 二级导航 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<div class="u-wrap company">
		<div class="heading">
			<h2 class="h5">如何开通企业即时到账</h2>
		</div>
	</div>
	<div class="u-wrap company">
		<div class="mainbackground u-content clear">
			<div class="full-wrap domain">
				<div class="domain-tips">

					<div class="p">
						<h2 class="h6">
							<em>STEP01</em> 注册一个支付宝企业账号：
						</h2>
						<p>- 注册地址：<a href="https://www.alipay.com" target="_blank">https://www.alipay.com</a></p>
						<p>- 打开页面后，选择“立即注册”，选择企业账户注册，然后按照注册的流程进行注册。</p>
						<p>
							<img src="<%=rootPath%>/images/ali1.jpg" class="imgWidth">
						</p>
						<p>
							<img src="<%=rootPath%>/images/ali2.png" class="imgWidth">
						</p>
					</div>
					<div class="p">
						<h2 class="h6">
							<em>STEP02</em> 在线申请即时到账收款服务
						</h2>
						<p>申请地址：<a href="https://b.alipay.com/order/productDetail.htm?productId=2012111200373124" target="_blank">https://b.alipay.com/order/productDetail.htm?productId=2012111200373124</a>
							进入填写信息的页面后，需要接入的网站地址处填写您的网校地址，如：my.xxxx.com</p>
						<p style="color: red;">注意：在您申请支付宝即时到账收款服务前，先确保有上架的课程，也就是说通过访问您的网校地址（例如my.xxxx.com）时，能够看到售卖的课程，有售卖的课程时支付宝才会审核通过。</p>
						<p>
							<img src="<%=rootPath%>/images/ali3.png" class="imgWidth">
						</p>
						<p>
							<img src="<%=rootPath%>/images/ali4.png" class="imgWidth">
						</p>
					</div>
					<div class="p">
						<h2 class="h6">
							<em>STEP03</em> 查看合作者身份PID和安全校验码KEY
						</h2>
						<p>即时到账收款开通后，用申请的支付宝账号登录
							打开地址：
							<a href="https://b.alipay.com/order/signManage.htm?channel=ent" target="_blank">https://b.alipay.com/order/signManage.htm?channel=ent</a>
							点击“查看PID |
							KEY”按钮，在新打开的页面里即可看到自己的合作者身份（PID），输入支付密码，点确认即可查看自己的安全校验码KEY。</p>
						<p style="color: red;">注意：PID是16位的数字，KEY是32位的字母加数字。</p>
						<p>
							<img src="<%=rootPath%>/images/ali6.png" class="imgWidth">
						</p>
						<p>
							<img src="<%=rootPath%>/images/ali7.png" class="imgWidth">
						</p>
						<p>
							<img src="<%=rootPath%>/images/ali8.png" class="imgWidth">
						</p>
					</div>
					<div class="p">
						<h2 class="h6">
							<em>STEP04</em> 把PID和KEY值添加到系统中
						</h2>
						<p>把第3步中获取到的PID和KEY值添加到系统中。
							在“机构-开通服务-设置支付信息”中把PID和KEY保存到系统中。</p>
						<p class="text-left">
							<input type="button" onclick='location.href="<%=rootPath%>/payConfig/showZFB"' class="btn btn-sm btn-primary" value="返回" style="margin-left: 50%">
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<input type="hidden" value="${company.id}" id="companyId" />
	<!-- footer start -->
	<!-- footer end -->
	<script type="text/javascript">
		$(function() {
			$selectSubMenu('org_service');
		})
	</script>
</body>
</html>