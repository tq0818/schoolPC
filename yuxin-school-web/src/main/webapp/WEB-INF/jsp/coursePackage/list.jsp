<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程包</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes-add.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
</head>
<body style="position:relative;">
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">    
     
        <div class="m-search clear">
            <span class="fr" style="margin-top:8px;">
               <i class="tips" id="tip" style="display: none;">没有查到相关信息!</i>
                <input type="text" id="coursePackageName" placeholder="请输入课程包名称">
                <input type="button" onclick="Form.queryCommodityByName();" class="btn btn-default btn-sm" placeholder="输入课程包名称" value="搜索">
            </span>
        </div>
    </div>
</div>
<div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="classes-type">
            <p class="c">
                <span class="t-title">分类</span>
                <span class="t-content" id="firstTypeList">
                 
                </span>
            </p>
            <p class="c">
                <span class="t-title">二级分类</span>
                <span class="t-content" id="secondeTypeList">
                	
                </span>
            </p>
             <p class="c" id="labeSets">
                <span class="t-title">三级分类</span>
                <span class="t-content" id="thirdTypeList">
                	
                </span>
            </p>
        </div>
    </div>
</div>
<div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div id="packageLists">
        
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/cousePackage/list.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
 <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>