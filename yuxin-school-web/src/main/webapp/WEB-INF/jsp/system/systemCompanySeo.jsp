<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>SEO设置</title>
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
        <div class="title-box">
            <div class="tit-font">
                <span class="t">seo配置</span>
            </div>
        </div>
        <div class="wx-box">
            <div class="wx-name">网站标题</div>
            <span><textarea rows="5" cols="150" id="websiteTitle" style="resize:none;">${seo.title }</textarea></span>
            <div style="margin: 5px 0px 0px 110px;color:#0099ff;font-size:12px;">如果有多个关键字，请用英文输入法下逗号“,”或“|”隔开</div>
        </div>
        <div class="wx-box">
            <div class="wx-name">SEO关键字</div>
            <span><textarea rows="5" cols="150" id="websitKeyWords" style="resize:none;">${seo.keywords }</textarea></span>
            <div style="margin: 5px 0px 0px 110px;color:#0099ff;font-size:12px;">如果有多个关键字，请用英文输入法下逗号“,”或“|”隔开</div>
        </div>
        <div class="wx-box">
            <div class="wx-name">网站描述</div>
            <span><textarea rows="6" cols="150" id="websitedescription" style="resize:none;">${seo.description }</textarea></span>
            <div style="margin: 5px 0px 0px 110px;color:#0099ff;font-size:12px;">提示:描述内容不能超过500个字</div>
        </div>
         <div class="wx-box">
         	<div class="wx-name"></div>
         	<span style="text-align: right; width: 933px;display: inline-block;">
	           <input type="button" class="btn btn-success savebuttoms" value="保存 "/>
	           <input type="button" class="btn btn-default canclebtn" value="取消 "/>
           </span>
        </div>
        <div class="clear"></div>
    </div>
</div>
 <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('org_service');
    		$selectSubMenus('system_seo');
    	});
    </script>
<input type="hidden" id="titleContent" value="${seo.title }"/>
<input type="hidden" id="keyContent" value="${seo.keywords }"/>
<input type="hidden" id="desContent" value="${seo.description }"/>
<input type="hidden" id="typesContent" value="${type }"/>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/system/systemCompanySeo.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>