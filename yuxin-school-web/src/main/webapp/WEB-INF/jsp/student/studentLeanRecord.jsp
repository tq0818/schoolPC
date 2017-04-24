<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>学习记录</title>
     <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <%-- <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/add-title.css"/> --%>
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
   <div class="u-wrap classes" style="min-height:390px;margin-bottom:20px;">
       <div class="mainbackground nopadding">
           <div class="heading">
               <h2 class="h5">学习进度</h2>
               <span class="line"></span>
           </div>
           <div class="table-box t-table-box">
               <table>
                   <tr class="table-title">
                   	   <td>课程名称</td>
                       <td>报名时间</td>
                       <td>学习进度</td>
                   </tr>
                   <c:forEach items="${list}" var="leanRec">
                   <tr>
                       <td>${leanRec.name}</td>
                       <td>${leanRec.applyTime}</td>
                       <td>${leanRec.precent}%</td>
                   </tr>
                   </c:forEach>
               </table>
               <div class="pages-box"></div>
           </div>
           <div class="heading">
               <h2 class="h5">学习记录</h2>
               <span class="line"></span>
           </div>
           <div class="table-box m-table-box">
           <div style="margin-top: 30px;">
           	<form action="" method="post" id="exprotForm">
				<input type="hidden" name="stuId" value="${stu.id}">
           		<select id="classTypeId" name="commodityId">
           			<option value="">全部课程</option>
           			<c:forEach items="${classes}" var="classType">
           				<option value="${classType.commdityId}">${classType.classTypeName}</option>                          <!-- 测试发版bug -->
           			</c:forEach>
           		</select>
           		<div class="out_list">导出列表</div>
           	</form>
           </div>
               <table id="leanRecordTable">
                   
                 </table>
               <div class="pages-box">
               	<div class="pages"><ul class="pagination" id="studentLeanPage"></ul></div>
               </div>
           </div>
           
        </div>
    </div>
</div>
<form action="" method="post" style="display:none" id="exprotForm">
<input type="text" name="stuId" value="${stu.id}"> 
<!-- 测试发版bug -->                

</form>


<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>

<input type="hidden" value="${stuId }" id="stuId">


<!-- popupwin 修改密码界面结束 -->
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/student/studentLeanRecord.js"></script>
</body>
</html>