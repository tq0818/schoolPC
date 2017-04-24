<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/student.css" />
<script type="text/javascript" src="<%=rootPath%>/javascripts/student.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/student/index.js"></script>
<title>线下报名</title>
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="../menu/menu_student.jsp"></jsp:include>
<script type="text/javascript">
	$selectSubMenu('student_manage');
</script>
<form id="gonext" action="gonext" >

</form>
<div class="main">
<div class="u-wrap student main-fixed">
    <div class="mainbackground">
        <div class="center-search">
            <p class="c">
                <input id="mobile" type="text" class="input-control" value="" placeholder="输入学员手机号">
                <a  id="search" href="javascript:void(0)" class="btn btn-sm btn-default">搜索</a>
            </p>
            <p class="c tips"><span class="ps"></span></p>
           
        </div>
         <p class="tip"><span>如果学员不是通过在线购买的，可以通过此功能为学员报名一个课程，报名成功后学员可以登录网校学习该课程。</span></p>
    </div>
</div>
</div>
</body>
</html>