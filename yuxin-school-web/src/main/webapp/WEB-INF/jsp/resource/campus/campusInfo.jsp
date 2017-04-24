<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
    <title>校区</title>
    <%@include file="/decorators/import.jsp"%>
    <link href="<%=rootPath%>/stylesheets/manage.css" rel="stylesheet" type="text/css"/>
    <link href="<%=rootPath%>/stylesheets/resource.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/resource.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/resource/campus/campus.js"></script>
</head>
<body>
<!-- header start -->
<!-- header end -->
<!-- 二级导航 -->
<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
<div class="u-wrap resource">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">校区</h2>
            <div class="search">
                <a href="javascript:;" class="btn btn-mini btn-primary btn-add-school"><em class="iconfont">&#xe606;</em> 添加校区</a>
            </div>
            <span class="line"></span>
        </div>    
        <div class="sc-list-sc">
            <div class="sc-select">
                <span class="sc-select-title">校区状态</span>
                <a href="javascript:;" class="btn btn-mini btn-success tType" camStatus="">全部</a>
                <a href="javascript:;" class="btn btn-mini btn-default tType" camStatus="1">启用</a>
                <a href="javascript:;" class="btn btn-mini btn-default tType" camStatus="0">停用</a>
                <input type="hidden" value="" id="camStatus"/>
            </div>
            <div class="sc-list-sc-list clear" id="ajaxInfo" style="min-height: 350px;position: relative;">
              
            </div>
            <!-- ajax加载中div开始 -->
			<div class="loading lp-units-loading" style="display:none">
			       <p><i></i>加载中,请稍后...</p>
			</div>
		    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
			<!--  ajax加载中div结束 -->
        </div>
    </div>
</div>
<!-- footer start -->
<!-- footer end -->
<!-- 停用蒙板 -->
<div class="add-subs-layer stop-subs" style="height: 188px;">
    <p class="c">
        <span class="l-description">
        你要停用的：<span id="campusName"></span>
        <em class="alert-tips">还有以下班号在上课，请结课后才能停用。</em>
        </span>
    </p>
    	<table id="stopInfo" style="margin-left: 22px;">
		</table>
    <p class="c text-center">
        <a href="javascript:;" class="btn btn-sm btn-default btn-cancel">返回</a>
    </p>
</div>
<!-- 添加 -->
<div class="add-subs-layer add-school" style="margin: -11%">
<form id="addCampus">
    <p class="c">
        <span class="c-title">校区名称</span>
        <span class="c-title">
            <input type="text" name="campusName">
        </span>
    </p>
    <p class="c">
        <span class="c-title">编号</span>
        <span class="c-title">
            <input type="text" name="campusNo">
        </span>
    </p>
    <p class="c msg">
        校区编号为简单标识，例如A、B、C，在生成班号时作为<br>
        校区代码使用。例如：会计基础-20150506-A
    </p>
    <p class="c text-center">
        <a href="javascript:;" class="btn btn-sm btn-primary saveCampus">保存</a>
        <a href="javascript:;" class="btn btn-sm btn-default btn-cancel">返回</a>
    </p>
</form>
</div>
<div class="add-subs-layer-bg"></div>
<!-- 编辑 -->
<div class="add-subs-layer edit-school" style="height: 130px; width: 330px; margin: -11%;">
<form id="updateCampus">
    <p class="c">
        <span class="c-title">校区名称</span>
        <span class="c-title">
            <input type="text" name="campusName" class="UCampusName">
        </span>
    </p>
    <input type="hidden" name="id" class="UCampusId"/>
    <p class="c">
        <span class="c-title">编号</span>
        <span class="c-title">
            <input type="text" name="campusNo" class="UCampusNo">
        </span>
    </p>
    <p class="c text-center">
        <a href="javascript:;" class="btn btn-sm btn-primary updateCampus">保存</a>
        <a href="javascript:;" class="btn btn-sm btn-default btn-cancel">返回</a>
    </p>
</form>
</div>
<div class="add-subs-layer-bg"></div>
<script>
	$(function(){
		$selectSubMenu('resource_campus');
	});
</script>
</body>
</html>