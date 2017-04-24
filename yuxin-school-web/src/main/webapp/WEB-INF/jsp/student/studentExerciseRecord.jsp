<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>做题记录</title>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
     <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
    
    <style type="text/css">
   	.tips{
   		color:red;
   	}
   	.out_list{
   	    float:right;
   	    width:72px;
   	    height:29px;
   	    line-height:29px;
   	    text-align:center;
   	    background-color:#237fd5;
   	    color:#fff;
   	    font-size:12px;
   	    cursor:pointer;
   	    border-radius: 3px;
   	}
   	.block{
   	    background-color:#f6f6f6;
   	}
   	.block>div{
   	    float:left;
   	    font-size:14px;
   	    color:#666;
   	}
    </style>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<div class="u-wrap company" style="margin: 20px auto;">
    <div class="block clear" style="padding: 0 25px;height:60px;line-height:60px;">
        <div class="class-title" style="margin-right:60px;">
        	学员名称 ：<c:if test="${empty stu.name}">${stu.mobile}</c:if><c:if test="${!empty stu.name}">${stu.name}</c:if>
        </div>
        <div class="sum" style="margin-right:60px;">
        	手机号码：${stu.mobile}
        </div>
        <div class="price" style="margin-right:60px;">
        	创建时间：<fmt:formatDate value="${stu.createTime}" type="date"/>
        </div>
    </div>
</div>

<div>
   <div class="u-wrap classes">
       <div class="mainbackground nopadding" style="min-height:390px;margin-bottom:20px;">
           <div class="heading">
               <h2 class="h5">做题记录</h2>
               <span class="line"></span>
           </div>
           <div class="table-box b-table-box" style="display: block;">
               <div class="table-box-input">
               	<form action="" id="exprotForm" method="post">
               	<input type="hidden" value="${stu.id}" name="stuId" id="stuId">
                  <select id="stu_tiKu" name="tiKuName" style="width: 180px;">
                  	<option value="">题库名称</option>
                  </select>
                  <select id="stu_subject" name="subjectName" style="width: 180px;">
                  	<option value="">科目名称</option>
                  </select>
                  <select  id="stu_exerciseType" name="exerciseType" style="width: 180px;">
                      <option value="">类型名称</option>
                      <option value="EXERCISE_TYPE_15">快速做题</option>
                      <option value="EXERCISE_TYPE_CHAPTER">章节练习</option>
                      <option value="EXERCISE_TYPE_PAPER">模拟题/真题</option>
                      <option value="EXERCISE_AFTER_CLASS">课后练习</option>
                      <option value="EXERCISE_TYPE_EXAM">证书考试</option>
                      <option value="EXERCISE_TYPE_HOMEWORK">课后作业</option>
                  </select>
                  <input class="form-control datetime-picker date-picker" id="stu_startTime" value="做题时间" name="startTime" style="width: 150px;height:16px;display:inline-block"/>
                  <input id="search" type="button" value="搜索" style="width: 90px;position:relative;top:2px;margin-left:3px;color:#fff;background-color: #237fd5;border-color:#237fd5;border-radius: 3px;">
                  <div class="out_list">导出列表</div>
                  </form>
                 
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


<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<input type="hidden" value="${stu.userId}" id="stu_userId"/>


<!-- popupwin 修改密码界面结束 -->
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/stuExercise.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/student/splitmarket.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/student/classedit.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/popupwin.js"></script>
</body>
</html>