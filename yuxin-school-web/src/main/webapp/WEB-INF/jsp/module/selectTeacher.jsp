<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师管理</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css"/>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
</head>
<body>
	<!-- 二级导航开始 -->
		<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
	<!-- 二级导航结束 -->
	
	<!--  内容开始 -->
		<div class="u-wrap operate">
		    <div class="nopadding">
		        <div class="steps">
		            <ul class="clear">
		                <li class="step s1 active">
		                    <i>1</i>
		                    <em>基本信息</em>
		                </li>
		                <li class="step s2 active">
		                    <i>2</i>
		                    <em>确定上课时间</em>
		                </li>
		                <li class="step s3 hover">
		                    <i>3</i>
		                    <em>安排老师</em>
		                </li>
		                <li class="step s4">
		                    <i>4</i>
		                    <em>预定教室</em>
		                </li>
		            </ul>
		        </div>
		    </div>
		</div>
		
		<div class="u-wrap operate">
		    <div class="mainbackground">
		        <div class="heading">
		            <h2 class="h5">安排老师</h2>
		            <span class="search">${moduleNoName }</span>
		            <span class="line"></span>
		        </div>
		        <input type="hidden" value="${classTeachType }" id="classTeachType"/>
		        <table class="table table-center table-list">
		            <col width="10%">
		            <col width="10%">
		            <col width="10%">
		            <col width="10%">
		            <col width="20%">
		            <col width="10%">
		            <col width="20%">
		            <col width="10%">
		            <tr>
		                <th>日期</th>
		                <th>星期</th>
		                <th>时段</th>
		                <th>时间</th>
		                <th>课次</th>
		                <th>课时</th>
		                <th>老师教务</th>
		                <th>操作</th>
		            </tr>
		            
		            <c:forEach items="${lessons }" var="item">
		            	<tr>
			                <td><fmt:formatDate value="${item.lessonDate }" pattern="yyyy-MM-dd"/></td>
			                <td>${item.weekType }</td>
			                <td>${item.scope }</td>
			                <td>${item.lessonTimeStart }-${item.lessonTimeEnd }</td>
			                <td>${item.lessonName }</td>
			                <td>${item.lessonHour }</td>
			                <c:choose>
			                	<c:when test="${item.teachers == null && item.assistants == null}">
			                		<td><em>未安排</em></td>
				                	<td>
					                    <a href="javascript:;" lesson_id="${item.id }" class="btn btn-mini btn-warning editTeacher">安排老师</a>
					                </td>
			                	</c:when>
			                	<c:otherwise>
			                		<td>${item.teachers == null ? '未安排' : item.teachersName } ${item.assistants == null ? '未安排' : item.assistantsName }</td>
			                		<td>
					                    <a href="javascript:;" lesson_id="${item.id }" lesson_teacher="${item.teachersName }" lesson_educational ="${item.assistantsName }" class="btn btn-mini btn-primary editTeacher">更改老师</a>
					                </td>
			                	</c:otherwise>
			                </c:choose>
			            </tr>
		            </c:forEach>
		            
		        </table>
		        <div class="subbtns">
		            <a class="btn  btn-success prevLesson" href="javascript:;">上一步</a>
		            <a class="btn  btn-success nextClassRoom" href="javascript:;">下一步，安排教室</a>
		            <a class="btn  btn-default saveRoom" href="javascript:;">取消</a>
		        </div>
		    </div>
		</div>
		
	<!--  内容结束 -->
	
	<!-- 弹出教师信息开始 -->
	<div class="add-classes none">
	    <div class="teacher clear">
	        <div class="half fl">
	            <div class="heading">
	            	<input type="hidden" value="${moduleNoId }" id="moduleNoId"/>
	            	<input type="hidden" value="${moduleId }" id="moduleId"/>
	            	<input type="hidden" id="lessonId"/>
	                <h2 class="h5">老师
	                	<!-- <small><i class="iconfont all"></i>显示全部老师</small> -->
	                </h2>
	                <span class="line"></span>
	               <span class="add-btn"><a href="javascript:;" class="addTL">添加</a></span>
	            </div>
	            <div class="place-list teach">
	            	<div class="insertArea" style="display: none;">
	            		<input type="text" id="userName" placeholder="登陆账号"/>
	            		<input type="password" id="pwd" placeholder="密码"/>
	            		<input type="text" id="teacherName" placeholder="老师姓名"/>
	            		<input type="text" id="teacherMobile" placeholder="手机号"/>
	            		<a href="javascript:;" class="btn btn-mini btn-primary insertTeacher">保存</a>
	            	</div>
	                <ul id="teacherList">
	                </ul>
	            </div>
	        </div>
	        <div class="half fr">
	            <div class="heading">
	                <h2 class="h5">教务人员</h2>
	                <span class="line"></span>
	                 <span class="add-btn"><a href="javascript:;" class="addT">添加</a></span>
	            </div>
	            <div class="place-list teach">
	            	<div class="insertTArea" style="display: none;">
	            		<input type="text" id="userNameA" placeholder="登陆账号"/>
	            		<input type="password" id="pwdA" placeholder="密码"/>
	            		<input type="text" id="teacherTName" placeholder="教务人员姓名"/>
	            		<input type="text" id="teacherTMobile" placeholder="手机号"/>
	            		<a href="javascript:;" class="btn btn-mini btn-primary insertTTeacher">保存</a>
	            	</div>
	                <ul id="assistantsList">
	                </ul>
	            </div>
	        </div>
	    </div>
	    <div class="btns text-center">
	        <p class="text-center blue font-size">
	            <input type="checkbox" id="allSetTeacher">本期课程全部安排此老师
	        </p>
	        <p class="text-center">
	            <a href="javascript:;" class="btn btn-success">确定</a>
	            <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
	        </p>
	    </div>
	</div>
	<div class="add-classes-bg none"></div>
	<!-- 弹出教师信息结束 -->

	<script type="text/javascript" src="<%=rootPath %>/javascripts/modules/selectTeacher.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/modules/returnClass.js"></script>
	<script type="text/javascript">
    	$(function(){
    		$selectSubMenu('course_class_type');
    	});
    </script>
</body>
</html>