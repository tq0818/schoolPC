<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>会员课程</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/minitip.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/custom-page.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/vip.css"/>
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<div class="u-wrap company overflow">
    <div class="left-side">
        <!--<div class="left-side-title">返回</div>-->
        <div class="left-side-title">
            <em>会员管理</em>
<!--             <span class="iconfont return-pic">&#xe650;</span> -->
        </div>
        <ul>
            <li class="subentry active member_ClassType">会员课程</li>
<!--             <li class="subentry">兑换码</li> -->
        </ul>
    </div>
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">会员课程</span>
                </div>
            </div>
            <p class="prompt-font">说明：选择需要添加会员课程的会员，点击进入操作</p>
            <div class="show-vip-class">
             
                <div class="vip-class">
                    <p>未添加会员等级</p>
                    <p></p>
                </div>
              
            </div>
        </div>
    </div>
</div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
<script src="<%=rootPath %>/javascripts/splitmarket.js"></script>
<script src="<%=rootPath %>/javascripts/custom-page.js"></script>
<script src="<%=rootPath %>/javascripts/class/myMemberClass.js"></script>
</body></html>