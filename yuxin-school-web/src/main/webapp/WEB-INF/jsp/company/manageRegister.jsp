<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>

<% String rootPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />   
	<meta name="apple-mobile-web-app-capable" content="yes" />  
	<meta name="format-detection" content="telephone=no" /> 
    <script type="text/javascript">
        var rootPath = '<%=rootPath %>';
    </script>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/head.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/main.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/school-register.css"/>
    <title></title>
</head>
<body>
<div class="school-register">
    <header>
        <div class="head-title">
            <a href="Javascript:history.go(-1);void(0);" class="back" id="goBack"> <i class="iconfont">&#xe650;</i>返回</a>
            <p>网校注册</p>
        </div>
    </header>
    <main class="register-panel">
    	<form id="registerForm" >
    	<input type="hidden" id="utm_source" name="utm_source" value="${utm_source}"/>
		<input type="hidden" id="utm_medium" name="utm_medium" value="${utm_medium}"/>
		<input type="hidden" id="utm_term" name="utm_term" value="${utm_term}"/>
		<input type="hidden" id="utm_content" name="utm_content" value="${utm_content}"/>
		<input type="hidden" id="utm_campaign" name="utm_campaign" value="${utm_campaign}"/>
		<input type="hidden" id="utm_url" name="utm_url" value="${utm_url}"/>
        <ul>
            <li class="pl70">
                <span>用户名</span>
                <input type="text" placeholder="请输入用户名" id="username" name="username" maxlength="16"/>
            </li>
            <li class="pl70">
                <span>密&nbsp;码</span>
                <input type="password" placeholder="请输入密码" id="password" name="password" maxlength="16"/>
            </li>
            <li class="pl70">
                <span>手机号</span>
                <div class="phone-num">
                    <input type="text" placeholder="请输入手机号"  id="mobile" name="mobile" maxlength="11"/>
                    <span class="icon-close"><i class="iconfont">&#xe607;</i></span>
                </div>
            </li>
            <li class="pl100 pr100 pic-code">
                <span>图形验证码</span>
                <input type="text" placeholder="输入验证码" id="checkcode" name="checkcode" maxlength="6"/>
                <img  class="codeimg" src="" height="30px" width="90px" alt="点击刷新验证码" onclick="Form.refreshCaptcha()"/>
            </li>
            <li class="pl100 pr120 phone-code">
                <span>手机验证码</span>
                <input type="text" placeholder="手机验证码" id="smscode" name="smscode" maxlength="6"/>
                <div class="achieve" id="btn-sms" disabled="disabled">获取验证码</div>
            </li>
        </ul>
        </form>
        <div class="btn">
            <button id="register_submit">注册</button>
        </div>
    </main>
</div>
</body>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/manageRegister.js"></script>
</html>
