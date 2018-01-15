<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程-增加视频</title>
   <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
     <style type="text/css">
    	.up-input{position:relative;width:160px;height:32px;z-index:1}
		.upfile{position:relative;display:inline-block}
		.upfile .btn-up{position:absolute;top:0;left:0;}
    </style>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		$chooseMenu("videoCode");
    	});
    </script>
</head>
<body>
<div class="q-box">
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonTitle.jsp"></jsp:include>
<div class="u-wrap company overflow">
	<jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonClass.jsp"></jsp:include>
    <div class="right-side">
       <div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="teacher-content">
            <div class="teacher-btns">
            	<input type="hidden" id="moduleId" value="${moduleId}" />
            	<input type="hidden" id="itemOneId" value="${ct.itemOneId}" />
            	<input type="hidden" id="companyId" value="${ct.companyId }"/>
            	<input type="hidden" id="itemSecondId" value="${ct.itemSecondId}" />
            	<input type="hidden" id="classTypeId" value="${ct.id}" />
            	<input type="hidden" id="classTypeName" value="${ct.name }"/>
                <!-- <a href="javascript:;" class="btn btn-mini btn-default">隐藏默认章节序号</a> -->
            </div>
            <div class="t-c clear" >
                <div class="t-c-l" style="height:600px;">
                    <ul class="sortable base-sort" >
                        
                    </ul>
                </div>
                <div class="t-c-r right" >
                </div>
            </div>
        </div>   
    </div>
</div>
    </div>
</div>


<div class="add-layer add-class-layer none">
    <i class="iconfont close">&#xe610;</i>
    <div class="layer-content">
        <p class="c">
            <span class="c-title">节的名称</span>
            <span class="c-content">
                <input type="text" placeholder="输入名称">
            </span>
        </p>
        <p class="c">
            <span class="c-title">顺序</span>
            <span class="c-content"><input type="text"></span>
        </p>
        <p class="c">
            <span class="c-title">公开视频</span>
            <span class="c-content">
                <input type="checkbox" name="freeflag" class="feeFlag"> 免费视频
                <input type="checkbox" name="freeflag" class="feeFlag"> 试听
            </span>
        </p>
        <p class="c">
            <span class="c-title">视频文件</span>
            <span class="c-content">
                <input type="text">
                <input type="button" class="btn btn-sm btn-primary" value="从库中选择">
            </span>
        </p>
    </div>
</div>
<div class="add-layer-bg none" style="z-index:1000;"></div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!-- 弹层信息 -->
</div>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/classes.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/class/branchschool/videoAddBase.js"></script>
</body>
</body>
</html>
