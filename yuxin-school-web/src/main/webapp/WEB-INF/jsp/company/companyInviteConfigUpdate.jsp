<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>学员邀请码设置</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/manage.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/system.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/fatstyle.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/splitscreen.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/footer.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/manage-screen.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/code.css">
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script>
  	var rootPath = '<%=rootPath%>';
	var inviteConfig = '${inviteConfig}';
	var oneBeinviteFlag = '${inviteConfig.oneBeinviteFlag}';
	var oneInviteRgstMoney = '${inviteConfig.oneInviteRgstMoney}'
	var oneInviteRgstIntegral = '${inviteConfig.oneInviteRgstIntegral}';
	var oneInviteCsptMoney = '${inviteConfig.oneInviteCsptMoney}'
	var oneInviteCsptIntegral = '${inviteConfig.oneInviteCsptIntegral}';
	var oneInviteCsptPercent = '${inviteConfig.oneInviteCsptPercent}';
	var twoInviteFlag = '${inviteConfig.twoInviteFlag}';
	var twoInviteRgstMoney = '${inviteConfig.twoInviteRgstMoney}'
	var twoInviteRgstIntegral = '${inviteConfig.twoInviteRgstIntegral}';
	var twoInviteCsptIntegral = '${inviteConfig.twoInviteCsptIntegral}';
	var twoInviteCsptPercent = '${inviteConfig.twoInviteCsptPercent}';
	var twoInviteCsptMoney = '${inviteConfig.twoInviteCsptMoney}'
	var openFlag = '${inviteConfig.openFlag}';
	$(
			function() {

				if (openFlag != null && openFlag == '0' || openFlag == 0) {
					$('#zongbutton').removeClass("open").addClass("close")
							.html("&#xe604;");
					$('#zongbuttonspan').removeClass("open").addClass("close")
							.text('已禁用');
					$('#q-cont').hide();

				}
				$('#zongbutton')
						.on(
								'click',
								function() {
									var zongbutton = $('#zongbutton').attr(
											'class');
									if (zongbutton == 'iconfont normal close') {
										$
												.ajax({
													type : 'POST',
													url : rootPath
															+ "/companyInvitConfig/directUpdate",
													data : {
														'openFlag' : 0
													},
													success : function(data) {
														//alert('23231414');
													}
												})
									} else {
										$
												.ajax({
													type : 'POST',
													url : rootPath
															+ "/companyInvitConfig/directUpdate",
													data : {
														'openFlag' : 1
													},
													success : function(data) {
														//alert('23231414');
													}
												})
									}
								})
				if (oneBeinviteFlag == '1' || oneBeinviteFlag == 1) {
					$("#yijiregbutton").show();
				} else {
					$("#embutton").removeClass("open").addClass("close").html(
							"&#xe604;");
					$("#jiangliyiji").removeClass("open").addClass("close")
							.text("已禁用");
					$("#yijiregbutton").hide();
				}

				if(oneInviteCsptMoney != null && oneInviteCsptMoney !=''){
					$('#erjicondaijinradio').attr('checked', 'checked');
					$('#erjicondaijin').removeAttr('disabled');
					$('#erjicondaijinradio').parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
					find("input[type='text']").removeAttr("disabled");
				}
				
				if(twoInviteRgstMoney!=null && twoInviteRgstMoney !=''){
					$('#sanjiregdaijinradio').attr('checked', 'checked');
					$('#sanjiregdaijin').removeAttr('disabled');
					$('#sanjiregdaijinradio').parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
					find("input[type='text']").removeAttr("disabled");
				}
				if(twoInviteCsptMoney!=null && twoInviteCsptMoney !=''){
					$('#sanjicondaijinradio').attr('checked', 'checked');
					$('#sanjicondaijin').removeAttr('disabled');
					$('#sanjicondaijinradio').parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
					find("input[type='text']").removeAttr("disabled");
				}
				if (twoInviteFlag == '0' || twoInviteFlag == 0) {
					$('#embutton2').removeClass("open").addClass("close").html(
							"&#xe604;");
					$('#erjiyaoqing').removeClass("open").addClass("close")
							.text('已禁用');
					$('#sanjibuttoncon').hide();
				} else {
					$('#embutton2').removeClass("close").addClass("open");
					$('#erjiyaoqing').removeClass("close").addClass("open")
							.text('已启用');
					$('#sanjibuttoncon').show();
				}

				if(oneInviteRgstMoney!=null && oneInviteRgstMoney != ''){
					$('#erjiregdaijinradio').attr('checked', 'checked');
					$('#erjiregjifenradio').attr('checked', false);
					$('#erjiregdaijin').removeAttr('disabled');
					$('#erjiregdaijinradio').parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
					find("input[type='text']").removeAttr("disabled");
				}
				
				if (oneInviteRgstIntegral != null
						&& oneInviteRgstIntegral != '') {
					$('#erjiregdaijinradio').attr('checked', false);
					$('#erjiregjifenradio').attr('checked', 'checked');
					$('#erjiregjifen').removeAttr('disabled');
					$('#erjiregdaijin').attr('disabled', 'disabled');
					$('#erjiregjifenradio').parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
					find("input[type='text']").removeAttr("disabled");
				}

				if (oneInviteCsptIntegral != null
						&& oneInviteCsptIntegral != '') {
					$('#erjicondaijinradio').attr('checked', false);
					$('#erjiconjifenradio').attr('checked', 'checked');
					$('#erjiconjifen').removeAttr('disabled');
					$('#erjicondaijin').attr('disabled', 'disabled');
					$('#erjiconjifenradio').parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
					find("input[type='text']").removeAttr("disabled");
				}

				if (oneInviteCsptPercent != null && oneInviteCsptPercent != '') {
					$('#erjicondaijinradio').attr('checked', false);
					$('#erjitichengradio').attr('checked', 'checked');
					$('#erjiticheng').removeAttr('disabled');
					$('#erjicondaijin').attr('disabled', 'disabled');
					$('#erjitichengradio').parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
					find("input[type='text']").removeAttr("disabled");
				}

				if (twoInviteRgstIntegral != null
						&& twoInviteRgstIntegral != '') {
					$('#sanjiregdaijinradio').attr('checked', false);
					$('#sanjiregjifenradio').attr('checked', 'checked');
					$('#sanjiregjifen').removeAttr('disabled');
					$('#sanjiregdaijin').attr('disabled', 'disabled');
					$('#sanjiregjifenradio').parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
					find("input[type='text']").removeAttr("disabled");
				}

				if (twoInviteCsptIntegral != null
						&& twoInviteCsptIntegral != '') {
					$('#sanjicondaijinradio').attr('checked', false);
					$('#sanjiconjifenradio').attr('checked', 'checked');
					$('#sanjiconjifen').removeAttr('disabled');
					$('#sanjicondaijin').attr('disabled', 'disabled');
					$('#sanjiconjifenradio').parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
					find("input[type='text']").removeAttr("disabled");
				}

				if (twoInviteCsptPercent != null && twoInviteCsptPercent != '') {
					$('#sanjicondaijinradio').attr('checked', false);
					$('#sanjicontichengradio').attr('checked', 'checked');
					$('#sanjiconticheng').removeAttr('disabled');
					$('#sanjicondaijin').attr('disabled', 'disabled');
					$('#sanjicontichengradio').parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
					find("input[type='text']").removeAttr("disabled");
				}
			$('#yaoqingshezhi').on('click',function(){
				$(this).siblings('li').removeClass('active');
				$(this).addClass('active');
				window.location.href=rootPath+"/companyInvitConfig/toCompanyInviteCofig";
			})
			$('#youhuiguanli').on('click',function(){
				$(this).siblings('li').removeClass('active');
				$(this).addClass('active');
				window.location.href=rootPath+"/company/setCouponService";
			})
			
			})
			
</script>
</head>


<body class="q_box">
	<jsp:include page="/header.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<form id='companyinvitconfigForm' action="">
		<input type="hidden" value="${companyId }" name="companyId" /> <input
			type="hidden" id="usePriv" value="${inviteConfig.usePriv }" /> <input
			type="hidden" id="openFlag" value="${inviteConfig.openFlag }" /> <input
			type="hidden" id="oneBeinviteFlag"
			value="${inviteConfig.oneBeinviteFlag }" /> <input type="hidden"
			id="oneBeinviteGetMoney" value="${inviteConfig.oneBeinviteGetMoney }" />
		<input type="hidden" id="oneBeinviteMoneyPeriod"
			value="${inviteConfig.oneBeinviteMoneyPeriod }" /> <input
			type="hidden" id="oneInviteRgstMoney"
			value="${inviteConfig.oneInviteRgstMoney }" /> <input type="hidden"
			id="oneInviteRgstIntegral"
			value="${inviteConfig.oneInviteRgstIntegral }" /> <input
			type="hidden" id="oneInviteCsptMoney"
			value="${inviteConfig.oneInviteCsptMoney }" /> <input type="hidden"
			id="oneInviteCsptIntegral"
			value="${inviteConfig.oneInviteCsptIntegral }" /> <input
			type="hidden" id="oneInviteCsptPercent"
			value="${inviteConfig.oneInviteCsptPercent }" /> <input type="hidden"
			id="twoInviteRgstMoney" value="${inviteConfig.twoInviteRgstMoney }" />
		<input type="hidden" id="twoInviteRgstIntegral"
			value="${inviteConfig.twoInviteRgstIntegral }" /> <input
			type="hidden" id="twoInviteCsptMoney"
			value="${inviteConfig.twoInviteCsptMoney }" /> <input type="hidden"
			id="twoInviteCsptIntegral"
			value="${inviteConfig.twoInviteCsptIntegral }" /> <input
			type="hidden" id="twoInviteCsptPercent"
			value="${inviteConfig.twoInviteCsptPercent }" /> <input type="hidden"
			id="twoInviteFlag" value="${inviteConfig.twoInviteFlag }" />
	</form>

	<!-- 二级导航 -->
	<div class="u-wrap  manage-screen clear" >
	<jsp:include page="/WEB-INF/jsp/menu/menu_newpromotion.jsp"></jsp:include>
	<script>
	$(function(){
		debugger;
		$selectSubMenus('invite');
	})
	</script>
		<div class="screen-right">
			<div class="screen-right-cont">
				<div class="screen-right-title">
					<h3 style="border-left-color: #fa6900;">
						<i style="color:#000;">学员邀请码</i> <span class="q-open"><em
							class="iconfont normal open" id='zongbutton'></em></span> <span
							class="i open " id="zongbuttonspan">已启用</span>
					</h3>
				</div>
				<p class="prompt-font" style="line-height: 52px;font-size: 14px;color:#999;padding-left: 20px;">说明：开启该功能后，进行相应的设置，学员进入“我的邀请码”时生成邀请码和邀请链接，邀请码可重复使用。</p>

				<div class="code-cont" id="q-cont">
					<div class="coupon-block clear">
						<div class="t-label fl">邀请码使用权限</div>
						<div class="fl p-relative">
							<div class="user-chose-wrap" id="">

								<c:if test="${inviteConfig.usePriv=='COUPON_OBJECT_ALL' }">
									<div class="user-choses-selected" value="ueser1" click="false">注册用户</div>
								</c:if>
								<c:if test="${inviteConfig.usePriv=='COUPON_OBJECT_STUDENT' }">
									<div class="user-choses-selected" value="ueser2" click="false">购买过课程的学员</div>
								</c:if>
								<c:if test="${inviteConfig.usePriv=='COUPON_OBJECT_MEMBER'}">
									<div class="user-choses-selected" mark="member" value="ueser0">会员</div>
								</c:if>
								<div class="user-chose-position" style="display: none;">
									<div value="ueser2">购买过课程的学员</div>
									<div mark="member" value="ueser0">会员</div>
									<div value="ueser1">注册用户</div>

								</div>
								<div class="select-sanjiao" click="false">
									<b class=""></b>
								</div>
							</div>
						</div>
						<p id="ptext" class="fl text">说明：设置使用权限后，会为每位拥有权限的人员发放一个固定的邀请码，可重复使用</p>
					</div>
					<div class="screen-right-title q-screen">
						<div class="btn-cro">
							<div>
								<i style="margin-left: 13px;"> 被邀请人获得奖励</i> <span
									class="q-open2"><em class="iconfont normal open"
									id='embutton'></em> </span> <span class="i open" id="jiangliyiji">已启用</span>
							</div>

							<div class="q-hide" id="yijiregbutton">
								<div class="btn-cont q-btn">
									<div class="text1">
										<div class="text-left fl">被邀请人注册成功后获得</div>
										<span class="qcheck"
											style="color: #616161; box-sizing: border-box; padding-left: 15px;">代金券</span>
										<span id="yijidaijinspan" class="text-right"
											style="color: #616161;"><input type="text"
											id="yijidaijin" value="${inviteConfig.oneBeinviteGetMoney }">元</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
											id='yijidaijinerror' style='color: red;'></span>
									</div>
								</div>
								<div class="btn-cont" style="margin-bottom: 0;">
									<div class="text1">
										<div class="text-left fl">代金券使用有效天数</div>
										<span class="text-right" style="color: #616161;"><input
											type="text" id="daijinPercent"
											value="${inviteConfig.oneBeinviteMoneyPeriod }">天</span><span
											class="text" id="daijinPercentSpan">说明：获取代金券后 ** 天内使用</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
											id='daijinPercenterror' style='color: red;'></span>
									</div>
								</div>
							</div>

						</div>
						<div class="btn-cro" style="margin-top: 45px;">
							<i> 邀请人奖励规则</i>
							<div class="btn-cont q-btn q-radio q_only">
								<div class="text1">
									<div class="text-left fl">被邀请人注册成功后邀请人获得</div>
									<span class="qcheck "><input type="checkbox"
										class="radio"  id='erjiregdaijinradio'><label for="erjiregdaijinradio">代金券</label></span> <span
										class="text-right q-active"><input type="text"
										class="q-input" id='erjiregdaijin'
										value="${inviteConfig.oneInviteRgstMoney }">元</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
										id='erjiregdaijinerror' style='color: red;'></span>
								</div>
								
								
								<c:if test="${IsIntegral }">
									<div class="text1">
										<div class="text-left fl"></div>
										<span class="qcheck"><input type="checkbox" class="checkbox"
											id='erjiregjifenradio'><label for="erjiregjifenradio">积分</label></span> <span class="text-right"><input
											type="text" id='erjiregjifen'
											value="${inviteConfig.oneInviteRgstIntegral }"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
											id='erjiregjifenerror' style='color: red;'></span>
									</div>
								</c:if>
							</div>
							<div class="btn-cont q-btn q-radio first">
								<div class="q_only">
									<div class="text1">
										<div class="text-left fl">被邀请人首次消费后邀请人获得</div>
										<span class="qcheck "><input type="checkbox"
											class="radio"  id='erjicondaijinradio'><label for="erjicondaijinradio">代金券</label></span> <span
											class="text-right q-active"><input type="text"
											class="q-input" id='erjicondaijin'
											value="${inviteConfig.oneInviteCsptMoney }">元</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
											id='erjicondaijinerror' style='color: red;'></span>
									</div>
									<div class="text1">
										<div class="text-left fl"></div>
										<span class="qcheck"><input type="checkbox" class="radio"
											id='erjitichengradio'><label for="erjitichengradio">提成</label></span> <span class="text-right"><input
											type="text" id='erjiticheng'
											value="${inviteConfig.oneInviteCsptPercent }">%</span><span
											class="text">说明：提成按实际支付金额计算，奖励以代金券的形式发放到个人中心</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
											id='erjitichengerror' style='color: red;'></span>
									</div>
									<c:if test="${IsIntegral }">
										<div class="text1">
											<div class="text-left fl"></div>
											<span class="qcheck"><input type="checkbox" class="radio"
												id='erjiconjifenradio'><label for="erjiconjifenradio">积分</label></span> <span class="text-right"><input
												type="text" id='erjiconjifen'
												value="${inviteConfig.oneInviteCsptIntegral }"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
												id='erjiconjifenerror' style='color: red;'></span>
										</div>
									</c:if>
								</div>



								<div class="second">
									<div class="text1">
										<i class="wd270">开启两级邀请</i> <em class="iconfont qnormal open"
											id="embutton2"></em><span class="qi open" id='erjiyaoqing'>已启用</span>
										<span class="text">开启此功能后，被邀请人如再邀请其他人注册成功或消费，邀请人也可获得奖励</span>
									</div>
									<div class="toggle" id='sanjibuttoncon'>
										<div class="q_only">
											<div class="text1">
												<div class="text-left fl">被邀请人注册成功后邀请人获得</div>
												<span class="qcheck "><input type="checkbox"
													class="radio"  id='sanjiregdaijinradio'><label for="sanjiregdaijinradio">代金券</label></span>
												<span class="text-right q-active"><input type="text"
													class="q-input" id='sanjiregdaijin'
													value="${inviteConfig.twoInviteRgstMoney }">元</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
													id='sanjiregdaijinerror' style='color: red;'></span>
											</div>
											<c:if test="${IsIntegral }">
												<div class="text1">
													<div class="text-left fl"></div>
													<span class="qcheck"><input type="checkbox"
														class="radio" id='sanjiregjifenradio'><label for="sanjiregjifenradio">积分</label></span> <span
														class="text-right"><input type="text"
														id='sanjiregjifen'
														value="${inviteConfig.twoInviteRgstIntegral }"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
														id='sanjiregjifenerror' style='color: red;'></span>
												</div>
											</c:if>
										</div>
										<div class="q_only">
											<div class="text1 mg10">
												<div class="text-left fl">被邀请人首次消费后邀请人获得</div>
												<span class="qcheck "><input type="checkbox"
													class="checkbox"  id='sanjicondaijinradio'><label for="sanjicondaijinradio">代金券</label></span>
												<span class="text-right q-active"><input type="text"
													class="q-input" id='sanjicondaijin'
													value="${inviteConfig.twoInviteCsptMoney }">元</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
													id='sanjicondaijinerror' style='color: red;'></span>
											</div>
											<div class="text1">
												<div class="text-left fl"></div>
												<span class="qcheck"><input type="checkbox"
													class="radio" id='sanjicontichengradio'><label for="sanjicontichengradio">提成</label></span> <span
													class="text-right"><input type="text"
													id='sanjiconticheng'
													value="${inviteConfig.twoInviteCsptPercent }">%</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
													id='sanjicontichengerror' style='color: red;'></span>
											</div>
											<c:if test="${IsIntegral }">
												<div class="text1">
													<div class="text-left fl"></div>
													<span class="qcheck"><input type="checkbox"
														class="radio" id='sanjiconjifenradio'><label for="sanjiconjifenradio">积分</label></span> <span
														class="text-right"><input type="text"
														id='sanjiconjifen'
														value="${inviteConfig.twoInviteCsptIntegral }"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span
														id='sanjiconjifenerror' style='color: red;'></span>
												</div>
											</c:if>
										</div>


									</div>

								</div>

							</div>
						</div>
					</div>
					<div class="foter">
						<span class="delate" id="cancelSpan">取消</span><span
							class="save">保存</span>
					</div>
				</div>
			</div>
			<div class="layer-tips-bg"></div>
			<div class="layer-tips confirm">
				<div class="layer-tips-title">
					提示<i class="close iconfont confirm_close"></i>
				</div>
				<div class="layer-tips-content">您的邀请奖励还未设置完成，请确认填写完整后保存</div>
				<div class="layer-tips-btns">
					<a href="javascript:;" class="btn btn-mini btn-success confirm_ok">确定</a><a
						href="javascript:;"
						class="btn btn-mini btn-default confirm_cancle">取消</a>
				</div>
			</div>
		</div>
	</div>

	<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
	<script src="<%=rootPath%>/javascripts/code.js"></script>
	<script src="<%=rootPath%>/javascripts/my-select.js"></script>
	<script src="<%=rootPath%>/javascripts/company/invitConfig.js"></script>
	<script src="<%=rootPath%>/javascripts/common/utils.js"></script>
</body>

</html>
