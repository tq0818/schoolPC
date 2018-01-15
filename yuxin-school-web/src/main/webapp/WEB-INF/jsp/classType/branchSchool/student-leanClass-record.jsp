<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>课程-学员管理</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/popupwin.css">
    
    <script type="text/javascript">
    	var rootPath = "<%=rootPath %>";
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonTitle.jsp"></jsp:include>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<div class="u-wrap company overflow">
    <jsp:include page="/WEB-INF/jsp/classType/branchSchool/commonClass.jsp"></jsp:include>
<input id="stuId" type="hidden" value="${stuId}">
<input id="classTypeId" type="hidden" value="${classTypeId}">
<input id="companyId" type="hidden" value="${ct.companyId}">
    <div class="right-side">
        <div class="u-wrap classes">
            <div class="mainbackground nopadding">
                <div class="heading">
                    <h2 class="h5">${leanRec.name}-上课情况</h2>
                    <span class="line"></span>
                </div>
                <div class="table-box t-table-box">
                    <table>
                        <tr class="table-title">
                            <td>学习进度</td>
                            <td>注册时间</td>
                            <td>报名时间</td>
                            <td>提问次数</td>
                            <td>评论次数</td>
                        </tr>
                        <tr>
                            <td>${leanRec.precent}%</td>
                            <td>${leanRec.registTime}</td>
                            <td>${leanRec.applyTime}</td>
                            <td>${leanRec.questionNum==null?0:leanRec.questionNum}次</td>
                            <td>${leanRec.evaluateNum==null?0:leanRec.evaluateNum}次</td>
                        </tr>
                    </table>
                    <div class="pages-box"></div>
                </div>
                <div class="tab-box">
                    <div class="tab-name tab1 active">学习详情</div>
                    <div class="tab-name tab2">做题记录</div>
                </div>
                <div class="table-box m-table-box">
                    <table id="leanRecordTable">
                       
                    </table>
                    <div class="pages-box">
                    	<div class="pages"><ul class="pagination" id="studentLeanPage"></ul></div>
                    </div>
                </div>
                <div class="table-box b-table-box">
                    <div class="table-box-input">
                        <select name="" id="stu_tiKu" style="width: 180px;">
                        	<option value="0">科目名称</option>
                        </select>
                        <select name="" id="stu_subject" style="width: 180px;">
                        	<option value="0">科目名称</option>
                        </select>
                        <select name="" id="stu_exerciseType" style="width: 180px;">
                            <option value="0">类型名称</option>
                            <option value="EXERCISE_TYPE_15">快速做题</option>
                            <option value="EXERCISE_TYPE_CHAPTER">章节练习</option>
                            <option value="EXERCISE_TYPE_PAPER">模拟题/真题</option>
                            <option value="EXERCISE_AFTER_CLASS">课后练习</option>
                            <option value="EXERCISE_TYPE_EXAM">证书考试</option>
                            <option value="EXERCISE_TYPE_HOMEWORK">课后作业</option>
                        </select>
                        <input class="form-control datetime-picker date-picker" id="stu_startTime" value="做题时间" style="width: 150px;height:16px;display:inline-block"/>
                        <input id="search" type="button" value="搜索" style="width: 90px;position:relative;top:2px;margin-left:3px;color:#fff;background-color: #237fd5;border-color:#237fd5;border-radius: 3px;">
                    </div>
                    <table id="studentTiKuRecordTable"></table>
                    <div class="pages-box">
	                    <div class="pages">
	                    	<ul class="pagination" id="studentTiKuPage"></ul>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="pop-fixed">
    <div class="select-board cum-detail">
        <div class="board-title">
            <span>累计上课详情</span>
            <em class="iconfont pop-close-btn">&#xe610;</em>
        </div>
        <div class="select-content">
            <div style="margin-top: 20px;">
                <table style="width: 100%" id="lecOrlessTable">
                    
                </table>
            </div>
        </div>
        <div class="page-btn-box">
        	<div class="pages">
               <ul class="pagination" id="lecOrlessPage">
			</ul>
           </div> 
        </div>
    </div>
</div>
<input type="hidden" value="${userId}" id="stu_userId"/>
<script type="text/javascript">
	$(document).ready(function(){
		$chooseMenu("studentsCode");
		$selectMenu('course_class_type');
	});
</script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/student/splitmarket.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/student/classedit.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/class/branchschool/student-leanClass-record.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/popupwin.js"></script>
</body>
</html>