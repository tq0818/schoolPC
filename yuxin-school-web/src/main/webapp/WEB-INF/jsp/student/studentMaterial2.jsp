<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>报考材料</title>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script>
    <script type="text/javascript">
    $(function(){
		$(".footer").addClass("footer-fixed"); 
    });
	</script>
    <style type="text/css">
    	.c-title{
    		color:#999;
    	}
    </style>
   
    
</head>
<body>
<!-- 二级导航 -->
    <form action="<%=request.getContextPath() %>/studentAgentMaterial/showStuMaterial" method="post" id="toPayMessage">
		<input type="hidden" id="studentPayMasterId" name="studentPayMasterId"/>
		<input type="hidden" id="mobile" name="mobile" value='<c:out value="${mobile}"></c:out>'>
	<div class="u-wrap student main-fixed" style="top:50px">
		<div class="mainbackground">
			<div class="search-list">
            <ul id="ulList">
            
            </ul>
        </div>
			
		</div>
	</div>
	</form>
	
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/studentMaterial2.js"></script>

</body>
 </html>