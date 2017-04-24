<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>远程结费</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>

<div class="u-wrap student">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">远程结费</h2>
            <div class="search">
                <input type="text" class="input-ctrl" id="remoteName">
                <input type="button" onclick="Form.searchByName(1)" class="btn btn-sm" value="搜索">
            </div>
            <span class="line"></span>
        </div>
        <div class="select-option clear">
            <ul>
                <li>
                    <span class="s-title">学科</span>
                    <span class="s-list" id="itemOneList">
                    <c:forEach items="${firstItems }" var="itemOne" varStatus="status">
                        <a href="javascript:Form.queryItemSecond(${itemOne.id });" ids="${itemOne.id }" class="btn btn-mini btn-default ${status.count==1?'btn-success':'' }">${itemOne.itemName }</a>
                     </c:forEach>
                    </span>
                </li>
                <li>
                    <span class="s-title">学科小类</span>
                    <span class="s-list" id="itemSecondList">
                    </span>
                </li>
                <li>
                    <span class="s-title">考期</span>
                     <span class="s-list" id="termList">
                    </span>
                </li>
                 <li>
                    <span class="s-title">结费状态</span>
                    <span class="s-list" id="pStatus">
                        <a href="javascript:Form.queryStuLongList(1);" ids="" class="btn btn-mini btn-default">未结费</a>
                        <a href="javascript:Form.queryStuLongList(1);" ids="0" class="btn btn-mini btn-default">部分结费</a>
                        <a href="javascript:Form.queryStuLongList(1);" ids="1" class="btn btn-mini btn-default">已结费</a>
                    </span>
                </li>
            </ul>
        </div>
        <div class="student-list">
            <div class="list-title">
                <h2 class="h6"><i class="iconfont">&#xe614;</i>远程缴费通知</h2>
            </div>
            <div class="list-content" id="remoteListMsg">
               
            </div>
        </div>
    </div>
</div>
<!-- 弹层信息 -->
<div class="checkStudentTc">
<div class="check_student_hd">远程结费详情<i class="close iconfont">&#xe610;</i></div>
   <div id="lsOne">
   		<table class="table table-center">
		      <tr>
			     <th>课程</th>
		         <th>考期</th>
		         <th>结费日期</th>
		         <th>结费金额</th>
		         <th>操作人</th>
		      </tr>
	     </table>
   </div>
</div>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student/studentLong.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student/showTc.js"></script>
</body>
</html>