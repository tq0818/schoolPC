<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath=request.getContextPath(); %>

<!doctype html>
<html lang="zh-cn">
<head>
    <title>忘记密码</title>
	<script type="text/javascript">
		var rootPath='<%=rootPath %>';
	</script>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/register.css">
       <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
     <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
     <script type="text/javascript" src="<%=rootPath %>/javascripts/findPwd.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/company/jquery.cityselect.js"></script>
    
</head>
<body>
<!-- header start -->
<header class="full-wrap navbar minibar reversal">
    <div class="header">
        <a href="/" title="" class="navbar-brand"></a>
        <ul class="nav nav-left navspace">
           
        </ul>
        <ul class="nav nav-right">
            <li><a href="<%=rootPath %>/login" class="active">机构登录</a></li>
        </ul>
    </div>
</header>
<!-- header end -->
<div class="u-wrap register paddingtop">
    <div class="nopadding">
        <div class="steps">
            <ul class="clear">
                <li class="step2 s1 hover">
                    <i>01</i>
                    <em>填写账户信息</em>
                </li>
                <li class="step2 s2">
                    <i>02</i>
                    <em>重置密码</em>
                </li>
                <li class="step2 s3">
                    <i>03</i>
                    <em>完成</em>
                </li>
            </ul>
        </div>
    </div>
</div>
<!-- 第一步 -->
<div class="u-wrap register" id="registerStepOne">
<form id="registerForm">
    <div class="registermain">
        <ul>
            <li>
                <span class="r-title">公司域名</span>
                <span class="r-content"><input type="text" class="input_ctrl" id="domain" name="domain" placeholder="例如：my.yunduoketang.com"></span>
                <span class="tips t" style="display: none;"></span>
            </li>
            <li>
                <span class="r-title">用户名</span>
                <span class="r-content"><input type="text" class="input_ctrl" id="userName" name="userName" placeholder="输入您的用户名" maxlength="16"></span>
                <span class="tips" style="display: none;"></span>
            </li>
            <li id="mList">
                <span class="r-title">手机号</span>
                <span class="r-content"><input type="text" class="input_ctrl" id="mobile" name="mobile" value="" placeholder="输入您的手机号码"></span>
                <span class="tips" style="display: none;"></span>
            </li>
            <li>
                <span class="r-title">验证码</span>
                <span class="r-content n-code"><input type="text" id="checkcode" name="checkcode" value="" placeholder="图像验证码"></span>
                <span class="r-img"></span>
            </li>
            <li class="msg-code">
                <span class="r-title">短信验证</span>
                <span class="r-content t-code"><input type="text" name="sms" id="register_sms2" placeholder="短信验证码"></span>
                <span class="r-btns"><input type="button" class="btn btn-mini btn-primary btn-gray" onclick="Form.sendSmsCode(this)" id="btn-sms" disabled="disabled" value="获得验证码"/></span>
                <span class="tips"></span>
            </li>
 
            <li>
                <p class="text-center">
                    <a href="javascript:Form.registerCompany();" id="subBtn"  class="btn btn-mini btn-primary">下一步</a>
                </p>
            </li>
        </ul>
    </div>
   </form>
</div>
<!-- 第二步 -->
<div class="u-wrap register" id="registerStepTwo" style="display: none;">
   <form id="registerFormTwo">
    <div class="registermain">
        <ul>
            <li>
                <span class="r-title">新密码</span>
                <span class="r-content"><input type="password" name="password" id="password" class="input_ctrl" value="" placeholder="请输入密码"></span>
            </li>
            <li>
                <span class="r-title">确认密码</span>
                <span class="r-content"><input type="password" name="confirmPassword" id="confirmPassword" class="input_ctrl" value="" placeholder="请输入密码"></span>
            </li>
            <li>
                <p class="text-center">
                    <a href="javascript:Form.registerCompanyContent()" class="btn btn-mini btn-primary">下一步</a>
                </p>
            </li>
        </ul>
    </div>
   </form>
</div>
<!-- 第三步 -->
<div class="u-wrap register" id="registerStepThree" style="display: none;">
    <div class="registermain">
        <div class="msg">
        	<!--  
            <p>恭喜你完成了注册，请邮箱验证邮件已经发送到<em id="sendEmailContent"></em>
请在24小时内点击链接激活邮箱，此邮箱会作为与贵公司联系的主要途径。</p>-->
			<p style="color:red;margin: 15%;">恭喜您完成了密码重置，请登陆后继续使用网校服务。</p>
            <p class="text-center">
                <a href="../login.jsp" class="btn btn-mini btn-primary">去登陆</a>
            </p>
        </div>
    </div>
</div>
<!-- footer start -->

<!-- footer end -->
</body>
</html>