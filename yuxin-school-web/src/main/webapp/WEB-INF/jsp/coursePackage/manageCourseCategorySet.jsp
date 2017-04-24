<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程包分类设置</title>
       <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/class-set.css"/>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <div class="left-side">
        <div class="left-side-title">
            <em>课程包</em>
            <span class="iconfont return-pic hcancle">&#xe650;</span>
        </div>
        <ul id="menu_list">
            <li class="subentry active" mark="/classPackageCategory/SetClassPackageCategory">课程包设置</li>
            <li class="subentry" mark="/classPackageCategory/addClassPackageCategory">添加分类</li>
        </ul>
    </div>
    <div class="right-side">
        <div class="title-box">
            <div class="tit-font">
                <span class="t">设置</span>
            </div>
        </div>
        <div class="sec-title" id="classPackageSetId" ids="" mark="CLASS_PACKAGE_SET">课程包改名</div>
        <p class="prompt-font">说明：可对课程包及阶段的名称进行自定义，自定义的名称将在网校前台展示。</p>
        <div class="padd85 set-content">
            <em class="set-title">课程包</em>
            <input type="text" id="printClassPackageName" maxlength="5"  disabled="disabled" value=""/>
            <span class="savebtn_list" mark="course">编辑</span>
            <a class="btn btn-default cancle-chapterandlec" id="showClassPackageName" style="cursor:pointer; display: none;">取消</a>
        </div>
        <div class="padd85 set-content">
            <em class="set-title">阶段</em>
            <input type="text" id="printClassStageName" maxlength="5"  disabled="disabled" value=""/>
            <span class="savebtn_list" mark="stage">编辑</span>
            <a class="btn btn-default cancle-chapterandlec" id="showClassStageName" style="cursor:pointer;display: none;">取消</a>
        </div>
    </div>
</div>
<script type="text/javascript">
	$(function(){
		$selectMenu('org_service');
	})
</script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/class/cousePackage/setClassPackageName.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/splitmarket.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/class-set.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script src="<%=request.getContextPath()%>/javascripts/company/commonHeader.js" id="seajsnode"></script>
</body>
</html>