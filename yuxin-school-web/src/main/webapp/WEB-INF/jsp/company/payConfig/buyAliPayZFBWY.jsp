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
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">支付宝网银</span>
<%--                     <em class="iconfont help-font" onclick="javascript:window.open('<%=rootPath%>/payConfig/howPay')">&#xe621;</em> --%>
<!--                     <div class="help-pop">如何开通企业即时到账</div> -->
                </div>
            </div>
            <div class="payinput-box">
                <div class="payinput-bar">
                    <p class="label-name">公司名称：</p>
                    <p class="input-con">${company.companyName}</p>
                    <p></p>
                </div>
                <div class="payinput-bar">
                    <p class="label-name">支付宝账号：</p>
                    <p class="input-con">
                        <input type="text" value="${cpc.zfbAccount}" id="zfbAccount">
                    </p>
                    <p>支付宝账号必须是上面通过认证的公司申请的企业账号</p>
                </div>
                <div class="payinput-bar">
                    <p class="label-name">合作者身份PID：</p>
                    <p class="input-con">
                        <input type="text" value="${cpc.zfbPartnerId}" id="zfbPartnerId">
                    </p>
                    <p>16位纯数字</p>
                </div>
                <div class="payinput-bar">
                    <p class="label-name">安全校验证码KEY：</p>
                    <p class="input-con">
                        <input type="text" value="${cpc.zfbKey}" id="zfbKey">
                    </p>
                    <p></p>
                </div>
                <c:if test="${!empty cpc.companyId}">
                <div class="payinput-bar">
                    <p class="label-name">图形验证码：</p>
                    <p class="input-con">
                        <input type="text" id="picCode">
                    </p>
                    <p>
                        <div class="yz-pic">
                            <img src="<%=rootPath%>/captcha" id="captcha" style="width: 122px;height: 26px;" alt="点击刷新验证码">
                        </div>
                    </p>
                </div>
                <div class="payinput-bar">
                    <p class="label-name">手机验证码：</p>
                    <p class="input-con">
                        <input type="text" id="mobileCode">
                    </p>
                    <p>
                        <span class="yzm-btn" id="getCode">获取验证码</span>
                        <span>短信发送到注册手机号${user.mobile}</span>
                        <input type="hidden" value="${user.mobile}" id="mobile" />
                    </p>
                </div>
                </c:if>
                <div class="sive-manage" id="save">保存支付信息</div>
                <div class="cancel-manage" onclick="location.href='<%=rootPath%>/payConfig/showPayChoice'">取消</div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<input type="hidden" id="payType" value="${cpc.payType }">
<input type="hidden" id="saveType" value="PAY_TYPE_ZFBWY">
<input type="hidden" id="payConfigCompanyId" value="${cpc.companyId}">
<input type="hidden" id="companyId" value="${company.id}" />
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/company/payConfig/buyAliPay.js"></script>
<script type="text/javascript">
	$(function() {
		$selectSubMenu('org_service');
		$(".zfb").addClass("active");
	})
</script>
</body>
</html>