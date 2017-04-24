<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教室管理</title>
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
		                <li class="step s3 active">
		                    <i>3</i>
		                    <em>安排老师</em>
		                </li>
		                <li class="step s4 hover">
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
		            <h2 class="h5">预定教室</h2>
		            <span class="search">${moduleNoName }</span>
		            <span class="line"></span>
		        </div>
		        <table class="table table-center table-list">
		            <col width="10%">
		            <col width="10%">
		            <col width="10%">
		            <col width="10%">
		            <col width="10%">
		            <col width="5%">
		            <col width="10%">
		            <col width="25%">
		            <col width="10%">
		            <tr>
		                <th>日期</th>
		                <th>星期</th>
		                <th>时段</th>
		                <th>时间</th>
		                <th>课次</th>
		                <th>课时</th>
		                <th>老师教务</th>
		                <th>教室地址</th>
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
			                <td>${item.teachersName } ${item.assistantsName }</td>
			                <c:choose>
			                	<c:when test="${item.classroomId == null}">
			                		<td><em>未安排</em></td>
				                	<td>
					                    <a href="javascript:;" lesson_id="${item.id }" class="btn btn-mini btn-warning editClassRoom">预定教室</a>
					                </td>
			                	</c:when>
			                	<c:otherwise>
			                		<td>${item.classroom }</td>
			                		 <td>
						                   <a href="javascript:;" lesson_id="${item.id }" class="btn btn-mini btn-primary editClassRoom">更改教室</a>
						              </td>
			                	</c:otherwise>
			                </c:choose>
			            </tr>
		            </c:forEach>
		        </table>
		        <div class="subbtns">
		            <a class="btn btn-success" href="<%=rootPath %>/classModuleNo/editLessonTeacher/${moduleNoId}">上一步</a>
		            <a class="btn btn-success saveRoom" href="javascript:;">完成排课</a>
		            <a class="btn btn-default saveRoom" href="javascript:;">取消</a>
		        </div>
		    </div>
		</div>
	<!--  内容结束 -->
	
	<!-- 弹出教师信息开始 -->
	<div class="add-classes none">
	    <div class="heading">
	   		<input type="hidden" value="${moduleNoId }" id="moduleNoId"/>
           	<input type="hidden" id="lessonId"/>
	            	
	        <h2 class="h5">教室</h2>
	        <span class="line"></span>
	        <span class="add-btn"><a href="javascript:void(0)" class="addClassRoom">添加</a></span>
	        <i class="iconfont close">&#xe610;</i>
	    </div>
	    <div class="place" id="campusList">
	        <!-- 此处存放所有的校区 -->
	    </div>
	    <div class="place-list">
	    	<div class="addRoomArea" style="display: none;">
	    		<input type="text" placeholder="请输入教室名称" id="classRoomName"/>
	    		<input type="text" placeholder="请输入教室地址" id="classRoomAddress"/>
	    		<a href="javascript:;" class="btn btn-mini btn-primary insertRoom">保存</a>
	    	</div>
	        <ul id="classRoomList">
	        	<!-- 此处存放所有的教室 -->
	        </ul>
	    </div>
	    <div class="btns text-center">
	        <p class="text-center blue font-size">
	            <input type="checkbox" id="allSetClassRoom">本期课程全部安排此教室
	        </p>
	        <p class="text-center">
	            <a href="javascript:;" class="btn btn-success">确定</a>
	            <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
	        </p>
	    </div>
	</div>
	<div class="add-classes-bg none"></div>
	<!-- 弹出教师信息结束 -->

	<script type="text/javascript" src="<%=rootPath %>/javascripts/modules/moduleAlert.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/modules/selectClassRoom.js"></script> 
	<script type="text/javascript" src="<%=rootPath %>/javascripts/modules/returnClass.js"></script> 
	<script type="text/javascript">
    	$(function(){
    		$selectSubMenu('course_class_type');
    	});
    </script>
    <input type="hidden" id="campusId"/>
</body>
</html>