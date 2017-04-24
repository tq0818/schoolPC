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
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/Acinf.css"/>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css"/>
<link href="<%=rootPath%>/stylesheets/resource.css" rel="stylesheet"
	type="text/css" />
	<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
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
	<jsp:include page="/header.jsp" />
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
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">账户信息</span>
                </div>
            </div>
            <div class="AcinfBox">
                <ul class="AcinfBoxList">
                    <li class="clear">
                        <span>登录名</span>
                        <b>${user.username }</b>
                    </li>
                    <li class="clear">
                        <span>姓名</span>
                        <b>${user.realName }</b>
                    </li>
                    <li class="clear">
                        <span>手机号</span>
                        <b>${user.mobile }</b>
                    </li>
                </ul>
            </div>
        </div>
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">紧急联系人</span>
                </div>
            </div>
            <div class="AcinfBox">
                <ul class="AcinfBoxList">
                    <li class="clear">
                        <span>登录名</span>
                        <b>${user.emergencyContactName }</b>
                    </li>
                    <li class="clear">
                        <span>手机号码</span>
                        <b>${user.emergencyContactMobile }</b>
                    </li>
                </ul>
            </div>
        </div>
        <c:if test="${isInviteOpen eq 'true' }">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">我的邀请码</span>
                </div>
            </div>
            <div id="qrcode" mark="${inviteCodeStr}"></div>
            <div class="AcinfBox">
                <div class="erqcode">
                </div>
               <!--  <button class="saveLocal">保存到本地</button> --> 
            </div>
        </div>
        </c:if>
    </div>
</div>

<jsp:include page="/footer.jsp" />
</body>
</html>