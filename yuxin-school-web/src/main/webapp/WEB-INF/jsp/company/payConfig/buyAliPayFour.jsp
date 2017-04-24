<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>设置支付信息</title>
    <%@include file="/decorators/import.jsp"%>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/minitip.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <%@include file="/WEB-INF/jsp/company/payConfig/commonLeft.jsp"%>
    <div class="right-side">
    <!-- 购买主体内容 -->
	<div class="u-wrap company">
		<div class="mainbackground u-content clear">
			<div class="full-wrap buy-box">
				<!-- title end -->
				<div class="buy-main">
					<div class="cont">
						<p class="c clear">
							<span class="p-name">公司名称：</span> <label>${company.companyName}</label>
						</p>
						
						<p class="c clear">
							<span class="p-name">用户姓名：</span> <input type="text"
								class="input-ctrol" value="${cpc.personWxAccount}" id="personWxAccount">
							<span class="tips">您在微信中的填写的姓名</span>
						</p>
						<p class="c clear">
							<span class="p-name">微信二维码：</span> 
							<div class="ewm" style="margin-left: 120px;margin-top: -30px;" id="upWx">上传微信二维码</div>
							<input type="file" class="pcH none" name="imgDataWx" id="imgDataWx" accept=".jpg,.png,gif" onchange="savePic('imgDataWx')">
							<c:if test="${!empty cpc.personWxUrl}">
								<img src="http://${imageServeUrl}/${cpc.personWxUrl}" class="ewm" style="margin-top: -30px;margin-left: -202px;background-color: inherit;" id="wxImg">
							</c:if>
							<c:if test="${empty cpc.personWxUrl}">
								<img class="ewm" style="margin-top: -30px;margin-left: -202px;background-color: inherit;" id="wxImg">
							</c:if>
							<input type="hidden" id="personWxUrl" value="${cpc.personWxUrl}">
						</p>
						
						<c:if test="${!empty cpc.companyId}">
						<div id="cheMobile" style="margin-top: 10px;">
							<p class="c clear" style="padding-top: 10px;">
								<span class="p-name">图形验证码：</span> <input type="text"
									class="input-ctrol input-code" id="picCode"
									style="width: 147px;"> <span class="c-title"
									style="margin-left: 17px"><img
									src="<%=rootPath%>/captcha" id="captcha" height="29"
									width="100" alt="点击刷新验证码"> </span>
							</p>
							<p class="c clear">
								<span class="p-name">手机验证码：</span> <input type="text"
									class="input-ctrol" id="mobileCode"> <input
									type="button" class="btn btn-sm btn-default btn-code"
									id="getCode" value="获取验证码"> <span class="tips">短信发送到注册手机号${user.mobile}


								
							</p>
							<input type="hidden" value="${user.mobile}" id="mobile" />
						</div>
						</c:if>

						<div class="sive-manage" id="save">保存支付信息</div>
               			<div class="cancel-manage" onclick="location.href='<%=rootPath%>/payConfig/showWxChoice'">取消</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    </div>
</div>
<input type="hidden" id="payType" value="${cpc.payType }">
<input type="hidden" id="saveType" value="PAY_TYPE_WX_PERSON">
<input type="hidden" id="payConfigCompanyId" value="${cpc.companyId}">
<input type="hidden" id="companyId" value="${company.id}" />
<input type="hidden" id="imgUrl" value="${imageServeUrl}"/>
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/buy-member.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.units.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/company/payConfig/buyAliPay.js"></script>
<script type="text/javascript">
	$(function() {
		$selectSubMenu('org_service');
		$(".wx").addClass("active");
	})
</script>
</body>
</html>