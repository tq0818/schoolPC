<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>专题模块</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/teacher.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    
    <script type="text/javascript">
    	$(function(){
    		$selectSubMenu('special_topic');
    		loadSpecialList(1);
    	});
    	
    	function loadSpecialList(pageNum){
    		var url = "<%=rootPath%>/commodity/findSpecialByapge";
    		var data = {"pageNum":pageNum,"pageSize":12}
    		$('#specialList').load(url,data)
    	}
    	
    
    </script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>

<div class="u-wrap set-system">
    <div class="Y_background">
        <div class="Y_head Y_clear">
            <h2 class="h5 fl">专题模块</h2>
            <span class="line"></span>
            <span class="rb fr">
                    <a href="<%=rootPath %>/commodity/toAddSpecialPage"  class="btn btn-mini btn-primary">添加专题</a>
            </span>
        </div>
        <div id="specialList">
        
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript" src="<%=rootPath %>/javascripts/system/newsManage.js"></script>
</body>
</html>