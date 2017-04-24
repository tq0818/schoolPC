<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>报考材料</title>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script>
    <style type="text/css">
    	.c-title{
    		color:#999;
    	}
    </style>
    <script type="text/javascript">
    	$(function(){
      		$selectSubMenu('student_agent_material');
        	$(".footer").addClass("footer-fixed"); 
    	});
    </script>
    
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
    <form action="<%=request.getContextPath() %>/studentAgentMaterial/showStuMaterial" method="post" id="toPayMessage">
		<input type="hidden" id="studentPayMasterId" name="studentPayMasterId"/>
	
	<div class="u-wrap student main-fixed">
		<div class="mainbackground">
			<div class="center-search">
				<p class="c">
					<input type="text" class="input-control" value="" id="mobile" name="mobile"
						placeholder="输入学员手机号"> <a href="javascript:void(0);"
						class="btn btn-sm btn-default" id="searchBtn" onclick="search1();">搜索</a>
				</p>
					<p class="c" id="tip" style="display:none">
					<span class="ps">你查询的学员手机号不存在。</span>
				</p>
			</div>
			<p class="tip">通过该功能为需要代报考的学员录入已提交的报考材料</p>
			<div class="search-list">
            <ul id="ulList">
            
            </ul>
        </div>
			
		</div>
	</div>
	</form>
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/studentMaterial.js"></script>
</body>
</html>