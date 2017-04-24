<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="zh-cn">
<head>
<title>设置支付信息</title>
<%@include file="/decorators/import.jsp"%>
<link href="<%=rootPath%>/stylesheets/manage.css" rel="stylesheet"
	type="text/css" />
<link href="<%=rootPath%>/stylesheets/company.css" rel="stylesheet"
	type="text/css" />
<link href="<%=rootPath%>/stylesheets/minitip.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
.ewm{
  width: 150px;
  height: 150px;
  text-align: center;
  line-height: 150px;
  z-index: 99;
}
.pcH{
  margin-left: 80%;
  width: 100px;
  margin-top: -20px;
  position: absolute;
  z-index: 1;
}
</style>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/plus/jquery.units.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/buy-member.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/company/buyAliPay.js"></script>
</head>
<body>
	<!-- header start -->
	<!-- header end -->
	<!-- 二级导航 -->

	<!-- 用户信息开始 -->
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<!-- 用户信息结束 -->
	<!-- 购买主体内容 -->
	<div class="u-wrap company">
		<div class="mainbackground u-content clear">
			<div class="full-wrap buy-box">
				<div class="buy-title">
					<div style="height:48px">
						<i class="iconfont c3 active">&#xe640;</i>
						<i class="brand-text">支付</i>
					</div>
				</div>
				<!-- title end -->
				<div class="buy-main">
					<div class="b-title">
						<h2 class="h6">
							设置公司支付宝信息 
								<select class="btn btn-sm btn-default btn-code howPay" style="margin-left: 73%;">
									<option value="-1">如何开通支付？</option>
									<option value="1">企业即时到账</option>
									<option value="2">个人担保交易</option>
								</select>
						</h2>
					</div>
					<div class="cont">
						<input type="hidden" id="companyId" value="${company.id}" />
						<input type="hidden" id="payType" value="${cpc.payType}" />
						<p class="c clear">
							<span class="p-name">可选择的支付方式</span>
							<select id="seType" onchange="javascript:tChange()">
								<option value="">全部</option>
								<option value="PAY_TYPE_ZFB">企业即时到账</option>
								<option value="PAY_TYPE_GRDB">个人担保交易</option>
								<option value="PAY_TYPE_ZFB_ZZ">个人支付宝</option>
								<option value="PAY_TYPE_WX_PERSON">个人微信</option>
								<option value="PAY_TYPE_REMIT">银行汇款</option>
								<option value="PAY_TYPE_WX_GZH">微信公众号</option>
							</select>
						</p>
						<p class="c clear seTypeArea" style="height: 25px;">
							<span class="p-name">已启用的支付方式</span>
						</p>
						<p class="c clear">
							<span class="p-name">公司名称</span> <label>${company.companyName}</label>
						</p>
						<div id="zfbRel" class="none">
						<p class="c clear">
							<span class="p-name">支付宝账号</span> <input type="text"
								class="input-ctrol" value="${cpc.zfbAccount}" id="zfbAccount">
							<span class="tips">支付宝账号必须是上面通过认证的公司申请的企业账号</span>
						</p>
						<p class="c clear">
							<span class="p-name">合作者身份PID</span> <input type="text"
								class="input-ctrol" value="${cpc.zfbPartnerId}"
								id="zfbPartnerId"> <span class="tips">16位纯数字</span>
						</p>
						<p class="c clear">
							<span class="p-name">安全校验码KEY</span> <input type="text"
								class="input-ctrol" value="${cpc.zfbKey}" id="zfbKey">
						</p>
						</div>
						
						<div id="bank" class="none">
						<p class="c clear">
							<span class="p-name">开户名称</span> <input type="text"
								class="input-ctrol" value="${cpc.bankCompanyName}" id="bankCompanyName">
						</p>
						<p class="c clear">
							<span class="p-name">银行账号</span> <input type="text"
								class="input-ctrol" value="${cpc.bankAccountNumber}"
								id="bankAccountNumber">
						</p>
						<p class="c clear">
							<span class="p-name">开户银行</span> <input type="text"
								class="input-ctrol" value="${cpc.bankAccountName}" id="bankAccountName">
						</p>
						</div>
						
						<div id="wxgzh" class="none">
						<p class="c clear">
							<span class="p-name">AppID(应用ID)</span> <input type="text"
								class="input-ctrol" value="${cpc.gzhWxAppID}" id="appId">
						</p>
						<p class="c clear">
							<span class="p-name">AppSecret(应用密钥)</span> <input type="text"
								class="input-ctrol" value="${cpc.gzhWxAppSecret}" id="appSecret">
						</p>
						<p class="c clear">
							<span class="p-name">微信支付商户号</span> <input type="text"
								class="input-ctrol" value="${cpc.gzhWxMchID}" id="paySaleId">
						</p>
						<p class="c clear">
							<span class="p-name">微信支付商户号KEY</span> <input type="text"
								class="input-ctrol" value="${cpc.gzhWxKey}" id="paySaleKey">
							<span class="tips">自己生成的32位的MD5的加密key</span>
						</p>
						</div>
						
						<div id="zfbPer" class="none">
						<p class="c clear">
							<span class="p-name">账户姓名</span> <input type="text"
								class="input-ctrol" value="${cpc.personName}" id="personName">
							<span class="tips">您在支付宝中的填写的姓名</span>
						</p>
						<p class="c clear">
							<span class="p-name">支付宝账号</span> <input type="text"
								class="input-ctrol" value="${cpc.personZfbAccount}"
								id="personZfbAccount">
						</p>
						<p class="c clear">
							<span class="p-name">支付宝二维码</span> 
							<div class="ewm" style="margin-left: 120px;margin-top: -30px;" id="upZfb">上传支付宝二维码</div>
							<input type="file" class="pcH none" name="imgDataZfb" id="imgDataZfb" onchange="savePic('imgDataZfb')">
							<c:if test="${!empty cpc.personZfbUrl}">
								<img src="http://${imageServeUrl}/${cpc.personZfbUrl}" class="ewm" style="margin-top: -150px;margin-left: 120px;" id="zfbImg">
							</c:if>
							<c:if test="${empty cpc.personZfbUrl}">
								<img class="ewm" style="margin-top: -150px;margin-left: 120px;" id="zfbImg">
							</c:if>
							<input type="hidden" id="personZfbUrl" value="${cpc.personZfbUrl}">
						</p>
							
						</div>
						
						<div id="perWx" class="none">
							<p class="c clear">
								<span class="p-name">用户姓名</span> <input type="text"
									class="input-ctrol" value="${cpc.personWxAccount}"
									id="personWxAccount">
							</p>
							<p class="c clear">
								<span class="p-name">微信二维码</span> 
								<div class="ewm" id="upWx" style="margin-left: 120px;margin-top: -30px;">上传微信二维码</div>
								<input type="file" class="pcH none" name="imgDataWx" id="imgDataWx"  onchange="savePic('imgDataWx')">
								<c:if test="${!empty cpc.personWxUrl}">
									<img src="http://${imageServeUrl}/${cpc.personWxUrl}" class="ewm" style="margin-top: -150px;margin-left: 120px;" id="wxImg">
								</c:if>
								<c:if test="${empty cpc.personWxUrl}">
									<img class="ewm" style="margin-top: -150px;margin-left: 120px;" id="wxImg">
								</c:if>
								<input type="hidden" id="personWxUrl" value="${cpc.personWxUrl}">
							</p>
						</div>
						<c:if test="${!empty cpc.companyId}">
						<div id="cheMobile" style="margin-top: 10px;">
							<p class="c clear">
								<span class="p-name">图形验证码</span> <input type="text"
									class="input-ctrol input-code" id="picCode"
									style="width: 147px;"> <span class="c-title"
									style="margin-left: 17px"><img
									src="<%=rootPath%>/captcha" id="captcha" height="29"
									width="100" alt="点击刷新验证码"> </span>
							</p>
							<p class="c clear">
								<span class="p-name">手机验证码</span> <input type="text"
									class="input-ctrol" id="mobileCode"> <input
									type="button" class="btn btn-sm btn-default btn-code"
									id="getCode" value="获取验证码"> <span class="tips">短信发送到注册手机号${user.mobile}


								
							</p>
							<input type="hidden" value="${user.mobile}" id="mobile" />
						</div>
						</c:if>

						<p class="c clear">
							<span class="p-name">&nbsp;</span> <input type="button"
								value="保存支付信息" class="btn btn-big btn-primary" style="margin-top: 10px;" id="save">
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="payConfigCompanyId" value="${cpc.companyId}" />
	<input type="hidden" id="saveType" />
	<input type="hidden" id="imgUrl" value="${imageServeUrl}"/>
	<!-- 购买主体结束 -->
	<!-- footer start -->
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->
	<!-- footer end -->
	<script type="text/javascript">
		$(function() {
			$selectSubMenu('org_service');
		})
	</script>
</body>
</html>