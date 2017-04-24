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
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/resource.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/plus/miniTip.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/company/accountInformation.js"></script>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/company.js"></script>
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
			<li class="subentry active">修改密码</li>
			<c:if test="${isMaxRole == 1 }">
				<li class="subentry" onclick="javascript:location.href='<%=rootPath%>/users/accountInfo'">账户信息</li>
			</c:if>
			<c:if test="${isMaxRole == 2 }">
				<li class="subentry" onclick="javascript:location.href='<%=rootPath%>/users/teacherAccountInfo'">账户信息</li>
			</c:if>
		</ul>
	</div>
	<div class="right-side">
	<div class="title-box">
        <div class="tit-font">
            <span class="t">修改密码</span>
        </div>
    </div>
	<div class="add-teacher-room edit-pwd" style="width: 400px;height: 280px;margin: 5% 28%;background-color: white;position: inherit;display: block;">
   	<div class="add-teacher-room-content" >
        <div class="edit-password">
            <p class="cc">
                <span class="sc-c-title" style="width: 100px;">旧密码</span>
                <span class="sc-c-content">
                    <input type="password" placeholder="输入你的旧密码" id="oldPwd">
                    <i class="iconfontH">*</i>
                    
                </span>
                 <span class="sc-c-title">&nbsp;</span>
	             <span class="sc-c-content">
	             	<span class="tipsH old"></span>
	             </span>
                
                
            </p>
            <p class="cc">
                <span class="sc-c-title" style="width: 100px;">新密码</span>
                <span class="sc-c-content">
                    <input type="password" placeholder="输入新密码" id="newPwd">
                    <i class="iconfontH">*</i>
                    
                </span>
                <span class="sc-c-title">&nbsp;</span>
	             <span class="sc-c-content">
	             	<span class="tipsH new"></span>
	             </span>
                
            </p>
            <p class="cc">
                <span class="sc-c-title" style="width: 100px;">重复一遍</span>
                <span class="sc-c-content">
                    <input type="password" placeholder="再次输入新密码" id="nextPwd">
                    <i class="iconfontH">*</i>
                    
                </span>
                <span class="sc-c-title">&nbsp;</span>
	             <span class="sc-c-content">
	             	<span class="tipsH next"></span>
	             </span>
                
            </p>
            <p class="cc" >
             <span class="sc-c-title">&nbsp;</span>
             <span class="sc-c-content" style="margin-left: 60px;">
             	<a href="javascript:;" class="btn btn-sm btn-primary save">修改</a>
                <!-- <a href="javascript:;" class="btn btn-sm btn-primary cancle">取消</a> -->
             </span>
                
            </p>
        </div>
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
	<!-- footer start -->
	<!-- footer end -->
</body>
</html>