<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
<title>机构首页</title>
<%@include file="/decorators/import.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/minitip.css"/>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/Acinf.css"/>
<link href="<%=rootPath%>/stylesheets/resource.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/resource.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/plus/miniTip.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/company/accountInformation.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/company.js"></script>
		<script type="text/javascript" src="<%=rootPath %>/plugins/qrcode/jquery.qrcode-0.12.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/canvasjs.min.js"></script> 
	<script>
		$(function(){
			var saveFile = function(data, filename){
			       var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
			       save_link.href = data;
			       save_link.download = filename;

			       var event = document.createEvent('MouseEvents');
			       event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
			       save_link.dispatchEvent(event);
			   };
			   
			function product_invite_codeEwm(){
				$(".AcinfBox div").qrcode({
					text  : $("#qrcode").attr("mark"),
					size:150
				});
				var canvas=$(".AcinfBox div").find("canvas")[0];
				var img=convertCanvasToImage(canvas);
				$(".erqcode").html(img);
			}

			function convertCanvasToImage(canvas) {
				var image = new Image();
				image.src = canvas.toDataURL("image/png");
				return image;
			}
			$(document).ready(function(){
				product_invite_codeEwm();
				
				$('.saveLocal').on('click',function(){
					var src = $(".erqcode").find('img').attr('src');
					src = src.replace('image/png','image/octet-stream;Content-Disposition:attachment;filename=code.png');
					window.location.href=src;
					saveFile(src,"教师二维码.png");
				})
			})
			
		})
	</script>
</head>
<body>
	<!-- header start -->
	<!-- header end -->
	<!-- 二级导航 -->
	<div class="u-wrap company overflow">
	<div class="left-side">
		<div class="left-side-title">
			<em>用户信息配置</em>
		</div>
		<ul>
			<li class="subentry" onclick="javascript:location.href='<%=rootPath%>/companyMemberService/toUpdatePwd'">修改密码</li>
			<li class="subentry active">账户信息</li>
		</ul>
	</div>
	<div class="right-side">
		<div class="mainbackground">
		<div class="buy-title" style="border-bottom: solid 1px #ccc;">
		 	<div class="heading">
		        <i class="brand-text" style="font-style: initial;">账户信息</i>
		    </div>
		</div>
			<div class="main">
				<input type="hidden" value="${user.companyId }" id="companyId" />
				<p class="c">
					<span class="m-title">登录名</span> <input type="text"
						class="readonly" name="name" value="${user.username }" readonly
						tabindex="-1">
				</p>
				<p class="c">
					<span class="m-title">姓名</span> <input type="hidden"
						value="${user.realName }" id="HRealName" /> <input type="text"
						name="realName" class="input-edit" value="${user.realName }"
						tabindex="-1"> <input type="button" value="修改"
						class="btn btn-sm btn-default" id="URealName">
				</p>
				<%-- <p class="c">
					<span class="m-title">注册邮箱</span> <input type="hidden"
						value="${user.email }" id="HEmail" /> <input type="text"
						class="readonly" name="email" value="${user.email }" readonly
						tabindex="-1"> <input type="button" value="更换邮箱"
						class="btn btn-sm btn-default" id="UEmail">
				</p> --%>
				<p class="c">
					<span class="m-title">手机号码</span> <input type="hidden"
						value="${user.mobile }" id="HMobile" /> <input type="text"
						class="readonly" name="mobile" value="${user.mobile }" readonly
						tabindex="-1"> <input type="button" value="更换手机号"
						class="btn btn-sm btn-default" id="UMobile">
				</p>
			</div>
			<div class="buy-title" style="border-bottom: solid 1px #ccc;">
			 	<div class="heading">
			        <i class="brand-text" style="font-style: initial;">紧急联系人</i>
			    </div>
			</div>
			<div class="main">
				<p class="c">
					<span class="m-title">姓名</span> <input type="hidden"
						value="${user.emergencyContactName }" id="HEmergencyContactName" />
					<input type="text" name="emergencyContactName" class="input-edit"
						value="${user.emergencyContactName }" tabindex="-1"> <input
						type="button" value="修改" class="btn btn-sm btn-default"
						id="UEmergencyContactName">
				</p>
				<p class="c">
					<span class="m-title">电话号码</span> <input type="hidden"
						value="${user.emergencyContactMobile }"
						id="HEmergencyContactMobile" /> <input type="text"
						name="emergencyContactMobile" class="input-edit"
						value="${user.emergencyContactMobile }" tabindex="-1"> <input
						type="button" value="修改" class="btn btn-sm btn-default"
						id="UEmergencyContactMobile">
				</p>
				<p class="c">
					<span class="m-title">QQ</span> <input type="hidden"
						value="${user.qqNo }" id="HQqNo" /> <input type="text" name="qqNo"
						class="input-edit" value="${user.qqNo }" tabindex="-1"> <input
						type="button" value="修改" class="btn btn-sm btn-default" id="UQqNo">
				</p>
			</div>
			<div>
			<c:if test='${isTeacher eq "true" and isInviteOpen eq "true"}'>
			<div class="buy-title" style="border-bottom: solid 1px #ccc;">
		 	<div class="heading">
		        <i class="brand-text" style="font-style: initial;">我的邀请码</i>
		    </div>
			</div>
			<div class="main">
            <div id="qrcode" mark="${inviteCodeStr}"></div>
            <div class="AcinfBox">
                <div class="erqcode">
                </div>
                <!-- <button class="saveLocal">保存到本地</button> -->
            </div>
        </div>
        </c:if>
        </div>
		</div>
	</div>
	</div>
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->
	<!-- 修改邮箱-->
	<div class="add-subs-layer replace-email" style="height: 200px; width: 400px">
		<p class="c">
			<span class="MailErrorInfo" style=""></span> 
		</p>
		<p class="c">
			<span class="c-title">新邮箱地址</span> <span class="c-title"> <input
				type="text" class="newMaile">
			</span>
		</p>
		<p class="c">
			<span class="c-title">图片验证码</span> <span class="c-title"> <input
				type="text" class="mailePicCode">
			</span>
			<span class="c-title" style="margin-left: 120px"><img
				src="<%=rootPath%>/captcha" id="maileCaptcha"
				height="29" width="100" alt="点击刷新验证码">
			</span>
		</p>
		<p class="c">
			<span class="c-title">验证码</span> <span class="c-title"> 
			<input type="text" class="maileCode">
			</span>
			<span class="c-title" style="margin-left: 120px; position: absolute;"><input type="button" value="发送验证码" class="btn btn-sm btn-primary sendMaileCode"/> </span>
		</p>
		<p class="c text-center">
			<a href="javascript:;" class="btn btn-sm btn-primary saveMail">保存</a> <a
				href="javascript:;" class="btn btn-sm btn-default btn-cancel">返回</a>
		</p>
	</div>
	<div class="add-subs-layer-bg"></div>
	<!-- 修改手机 -->
	<div class="add-subs-layer replace-mobile"
		style="height: 200px; width: 400px">
		<p class="c">
			<span class="mobileErrorInfo" style=""></span> 
		</p>
		<p class="c">
			<span class="c-title">新的手机号</span> <span class="c-title"> <input
				type="text" class="phoneNum">
			</span>
		</p>
		<p class="c">
			<span class="c-title">图片验证码</span> <span class="c-title"> <input
				type="text" class="picCode">
			</span>
			<span class="c-title" style="margin-left: 120px"><img
				src="<%=rootPath%>/captcha" id="captcha"
				height="29" width="100" alt="点击刷新验证码">
			</span>
		</p>
		<p class="c">
			<span class="c-title">验证码</span> <span class="c-title"> <input
				type="text" class="mobileCode">
			</span>
			<span class="c-title" style="margin-left: 120px; position: absolute;"><input value="发送验证码" type="button" class="btn btn-sm btn-primary sendMobileCode"/></span>
		</p>
		<p class="c text-center">
			<a class="btn btn-sm btn-primary saveNewMobile">保存</a> <a
				href="javascript:;" class="btn btn-sm btn-default btn-cancel">返回</a>
		</p>
	</div>
	<div class="add-subs-layer-bg"></div>
	<!-- footer start -->
	<!-- footer end -->
</body>
</html>