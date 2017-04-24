<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>自定义页面</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/custom-page.css"/>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<div class="u-wrap company overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_systemconfig.jsp"></jsp:include>
     <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">自定义页面管理</span>
                    <em class="add-page addCustom_page"><i class="iconfont">&#xe652;</i>新增页面</em>
                </div>
            </div>
            <div class="search-box" style="background-color:#f6f6f6;">
                <select name="" id="templete_choose" onchange="loadGroupList();" style="width: 100px;">

                </select>
                <select name="" id="group_choose" style="width: 100px;">
                    <option value="0">全部</option>
                </select>
                <input type="text" placeholder="标题名称" id="custom_title" maxlength="12"/>
                <em class="search-btn" id="searchContent">搜索</em>
            </div>
            <div class="tables">
                <div class="tr title">
                    <span class="coll-1">标题</span>
                    <span class="coll-11">模板组</span>
                    <span class="coll-2" style="margin-left: -5px;">最后编辑时间</span>
                    <span class="coll-3" style="margin-left: -5px;">操作人</span>
                    <span class="coll-4" style="margin-left: -6px;">操作</span>
                </div>

            </div>
            <div class="pages">
				<ul class="pagination"></ul>
			 </div>
            <div class="clear"></div>
        </div>
    </div>
</div>

<!-- 弹窗 -->
 <div class="add-page-pop">
    <div class="add-page-board">
        <p class="page-pop-title"><span>新增页面</span><em class="iconfont">&#xe610;</em></p>
        <div class="custom-way">
            <div class="custom-way-btn">
                <div class="grey-bg">站内编辑</div>
                <p>新增站内富文本框编辑页面</p>
            </div>
            <div class="custom-way-btn">
                <div class="green-bg">HTML页面上传</div>
                <p>上传线下制作好的HTML页面</p>
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
<script type="text/javascript" src="<%=rootPath %>/javascripts/system/customPageList.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/custom-page.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
</body>
</html>