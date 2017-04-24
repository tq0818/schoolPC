<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>代报考</title>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/student.css"/>
    <style type="text/css">
    	.student .mainbackground .select-option li span.s-list a.active{
    		color:white;
    	}
    </style>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student/dbkApply.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>

<div class="u-wrap student">
    <div class="mainbackground">
        <div class="heading">
            <h2 class="h5">代报考</h2>
            <div class="search">
                <i class="tips" id="tip">没有查到相关信息!</i>
                <input type="text" id="mobile" placeholder="请输入学员手机号/用户名" class="input-ctrl">
                <input type="button" onclick="Form.queryStuListByMobile(1)" class="btn btn-sm" value="搜索">
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
                    <span class="s-title">考试时间</span>
                    <span class="s-list" id="termList">
                    </span>
                </li>
                <li>
                    <span class="s-title">报考资料</span>
                    <span class="s-list" id="completeList">
                    	<a href="javascript:Form.queryStudentList(1,'',null,null,null);" class="btn btn-mini btn-default btn-success">全部</a>
                        <a href="javascript:Form.queryStudentList(1,'',null,null,1);" ids="1" class="btn btn-mini btn-default">齐全</a>
                        <a href="javascript:Form.queryStudentList(1,'',null,null,0);" ids="0" class="btn btn-mini btn-default">不齐全</a>
                    </span>
                </li>
            </ul>
        </div>
        <div class="student-list">
            <div class="list-title">
                <h2 class="h6"><i class="iconfont">&#xe614;</i>学员列表</h2>
                <div class="list-btns">
                    <a href="javascript:Form.exportExcel();" class="btn btn-mini btn-default"><i class="iconfont">&#xe615;</i>学员导出</a>
                    <a href="javascript:Form.apply();" class="btn btn-mini btn-default"><i class="iconfont">&#xe616;</i>报考完成</a>
                </div>
            </div>
            <div class="list-content" id="studentList">
               
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
</body>
</html>