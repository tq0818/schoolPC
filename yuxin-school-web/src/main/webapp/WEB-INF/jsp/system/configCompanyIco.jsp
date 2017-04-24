<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>浏览器图标</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    
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
                    <span class="t">浏览器图标</span>
                </div>
            </div>
            <div class="upload-box">
                <span class="upload-tit">浏览器图标：</span>
                <div class="upload-pic-box iconfont">
                     &#xe677;
                	<img id="imgObject" width="45px" height="45px" ids="" src=""/>
                	<div class="trans" id="pic-up">上传</div>
                </div>
                <div style="position: relative"> 
                    <input type="file" accept="image/*" id="imgData" name="imgData" onchange="Forms.addCompanyIco()" style="opacity:0;width:68px;display: inline-block;position: absolute;left: 0;top:4px;margin-left: 15px;cursor: pointer;filter:alpha(opacity = 0);" />
                    <div class="upload-btn" id="c-up">上传</div>
                </div>
<!-- 		        <input type="file" accept="image/*" id="imgData" name="imgData" onchange="Forms.addCompanyIco()" style="display: none;"/> -->
<!--                 <div class="upload-btn" id="c-up">上传</div> -->
                <span class="exp">*说明:上传文件格式为ico类型,文件大小不能大于32×32。</span>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('org_service');
    		$selectSubMenus('system_ico');
    	});
    </script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/system/systemCompanyIco.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
   	<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
</body>
</html>