<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加班号</title>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/jquery.datetimepicker.css" />
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.datetimepicker.js"></script>
</head>
<body>
	<!-- 二级导航开始 -->
		<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
	<!-- 二级导航结束 -->
	
	<!--  内容开始 -->
		<div class="u-wrap operate">
		    <div class="nopadding">
		        <div class="steps">
		            <div class="line"></div>
		            <ul class="clear">
		                <li class="step s1 hover">
		                    <i>1</i>
		                    <em>班号</em>
		                </li>
		                <li class="step s2">
		                    <i>2</i>
		                    <em>课次</em>
		                </li>
		                <li class="step s3">
		                    <i>3</i>
		                    <em>老师教务</em>
		                </li>
		                <li class="step s4">
		                    <i>4</i>
		                    <em>教室</em>
		                </li>
		            </ul>
		        </div>
		    </div>
		</div>
		<div class="u-wrap operate">
			<div class="mainbackground">
		        <div class="heading">
		            <h2 class="h5">课程号</h2>
		        </div>
		        <form id="moduleForm" action="<%=rootPath%>/classModuleNo/addModule" method="post">
		        <div class="operate_main">
		        	<div class="title">所属模块</div>
		            <div class="class_number_cell">
		            	<div class="w h">
		                	<span class="class_number_name">学科：</span>
		                    <select class="form-control input-medium" id="itemOneId" name="itemOneId" >
		                    	<c:forEach items="${firstItems }"  var="item">
		                    		<option value="${item.id }" <c:if test="${itemOneId eq item.id }">selected</c:if>>${item.itemName }</option>
		                    	</c:forEach>
		                    </select>
		                </div>
		                <div class="w h">
		                	<span class="class_number_name">学科小类：</span>
		                    <select class="form-control input-medium" id="itemSecondId" name="itemSecondId">
		                    	<c:forEach items="${secondItems }"  var="item">
		                    		<option value="${item.id }" <c:if test="${itemSecondId eq item.id }">selected</c:if>>${item.itemName }</option>
		                    	</c:forEach>
		                    </select>
		                </div>
		                <div class="w h">
		                	<span class="class_number_name">模块名称：</span>
		                    <select class="form-control input-medium" id="moduleId" name="moduleId">
			                </select>
		                </div>
		                <div class="w h">
		                	<span class="class_number_name">授课方式：</span>
		                    <span class="class_number_sp" id="teachMethod"></span>
		                    <input name="classTeachType" type="hidden" value="面授">
		                </div>
		                <div class="w h">
		                	<span class="class_number_name">总课时：</span>
		                    <span class="class_number_sp" id="totalClassHour"></span>
		                    <input name="totalHours" type="hidden" value="30">
		                </div>
		                <div class="w w_all">
		                	<span class="class_number_name">课程单元信息：</span>
		                    <span class="class_number_sp" id="moduleDesc"></span>
		                </div>
		            </div>
		        </div>
		        <div class="operate_main">    
		            <div class="title">班号信息</div>
		            <div class="class_number_cell">
		            	<div class="w w_all campus_div">
		                	<span class="class_number_name">上课校区：</span>
		                    <span class="s-list" id="campus" name="campus">
		                    </span>
		                    <!-- 校区编码隐藏域 -->
         					<input type="hidden" value="" id="campus_no" />
         					<input type="hidden" value="" id="campus_id" name="campusId"/>
		                </div>
		            	<div class="w h">
		                	<span class="class_number_name" style="width: 120px;margin-left: -19px;">第一次上课时间：</span>
		                    <input type="text" readonly="readonly" name="startDate" class="txt laydate-icon" id="startDate" />
		                </div>
		                <div class="w h">
		                	<span class="class_number_name">班号名称：</span>
		                   	<input type="text" class="txt" id="moduleNoName" name="name"/>
		                </div>
		                <div class="w h">
		                	<span class="class_number_name">最大人数：</span>
		                    <input type="text" class="txt planEnrollStudents" name="planEnrollStudents" id="planEnrollStudents"/>
		                </div>
		                <div class="w w_all">
		                	<span class="class_number_name">班号信息：</span>
		                    <textarea class="com_textarea" name="description"></textarea>
		                </div>
		                
		                <div class="w w_all">
		                	<span class="class_number_name">允许现在招生：</span>
		                    <i class="iconfont normal open allowIconfont"></i>
		                    <span class="i open" >已允许</span>
		                    <input type="hidden" value="MODULE_NO_ON_SALE" name="publishStatus" id="publishStatus"/>
		                </div>
		            </div>
		            <input type="hidden" value="" name="next" id="next"/>
		            <div class="subbtns">
		                <a class="btn btn-success" style="cursor:pointer" id="saveNotGo">保存，稍后再排上课信息</a>
		                <a class="btn btn-success" style="cursor:pointer" id="saveAndGo">保存并继续</a>
		                <a class="btn btn-default saveRoom" style="cursor:pointer" >取消</a>
		            </div>
		        </div>
		        </form>
		    </div>
		</div>
	<!--  内容结束 -->
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/modules/addClassModuleNo.js"></script> 
	<script type="text/javascript" src="<%=rootPath %>/javascripts/modules/returnClass.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/operate.js"></script>
	<script type="text/javascript">
    	$(function(){
    		$selectSubMenu('course_class_type');
    		 $("#itemOneId").bind("change",function(){
        		 $("#itemSecondId").html('').getSysItem($(this).val(),function(){
        			 $("#itemSecondId").removeAttr("disabled");
        		 });
        	 });
    	});
    </script>
</body>
</html>