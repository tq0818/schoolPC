<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程包分类</title>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/class-set.css"/>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<input type="hidden" id="showLable" value="show"/>
<div class="u-wrap company overflow">
    <div class="left-side">
        <div class="left-side-title">
            <em>课程包</em>
            <span class="iconfont return-pic hcancle">&#xe650;</span>
        </div>
        <ul id="menu_list">
            <li class="subentry" mark="/classPackageCategory/SetClassPackageCategory">课程包设置</li>
            <li class="subentry active" mark="/classPackageCategory/addClassPackageCategory">添加分类</li>
        </ul>
    </div>
    <div class="right-side">
        <div class="title-box">
            <div class="tit-font">
                <span class="t">课程包</span>
                <em class="add-classify-btn addparentCategory" mark="one"><i class="iconfont">&#xe61c;</i>添加分类</em>
            </div>
        </div>
        <div class="add-classify-content" id="classPackageCategoryLists">
            
            
        </div>
    </div>
</div>
<div class="add-grade-pop-box">
    <div class="add-grade-pop">
        <div class="add-grade-title">添加一级菜单</div>
        <div class="add-grade-content">
            <p>
                <span>分类名称：</span>
                <input type="text" maxlength="10" id="categoryName"/>
            </p>
            <p class="care">注：最多可输入10个字</p>
            <input type="hidden" value="" id="cateGoryId"/>
            <div class="addpop-btn-box">
                <span class="add-yes addCategorys" type="" parentCode="" mark="">确定</span>
                <span class="add-cancel">取消</span>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
		$(function(){
			$selectMenu('org_service');
		})
	</script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/class/cousePackage/setClassPackageCategory.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/splitmarket.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/company/commonHeader.js" id="seajsnode"></script>
</body>
</html>