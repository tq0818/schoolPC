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
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
<style>
.module-info{display: block;margin-left: 105px;margin-top: -21px;width: 1000px;}
</style>
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
		        	<div class="title">课程单元</div>
		            <div class="class_number_cell">
		            	<!-- 根据业务不同，包含不同的页面 -->
		            	<c:if test="${classTypeId == 0 }">
			            	<jsp:include page="/WEB-INF/jsp/module/addModuleNoInclude.jsp"></jsp:include>
		            	</c:if>
		            	<c:if test="${classTypeId > 0 }">
			            	<jsp:include page="/WEB-INF/jsp/module/addModuleNoByClassTypeInclude.jsp"></jsp:include>
		            	</c:if>
		                <!-- 如果是通过排班号过来的话，则直接展示 -->
		                <c:choose>
		                	<c:when test="${module != null }">
		                		<div class="w h">
				                	<span class="class_number_name">单元名称：</span>
				                	<span class="class_number_sp" id="moduleName">${module.name }</span>
				                	<input name="moduleId" type="hidden" id="moduleId" value="${module.id }">
				                </div>
		                		<div class="w h">
				                	<span class="class_number_name">授课方式：</span>
				                    <span class="class_number_sp" id="teachMethod">${module.teachMethod }</span>
				                    <input name="classTeachType" type="hidden" value="${module.teachMethod }">
				                </div>
				                <div class="w h">
				                	<span class="class_number_name">总课时：</span>
				                    <span class="class_number_sp" id="totalClassHour">${module.totalClassHour }</span>
				                    <input name="totalHours" type="hidden" value="${module.totalClassHour }">
				                </div>
				                <div class="w h w_all">
				                	<span class="class_number_name">课程单元信息：</span>
				                    <span class="class_number_sp module-info" id="moduleDesc">${module.moduleDesc }</span>
				                </div>
		                	</c:when>
		                	<c:otherwise>
		                		<div class="w h">
				                	<span class="class_number_name">单元名称：<em style="color:#f00;">*</em></span>
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
		                	</c:otherwise>
		                </c:choose>
		                
		            </div>
		        </div>
		        <div class="operate_main">    
		            <div class="title">班号信息</div>
		            <div class="class_number_cell">
		            
		            	<c:choose>
		                	<c:when test="${module != null }">
		                		<c:if test="${module.teachMethod == '面授' }">
		                			<div class="w w_all campus_div">
					                	<span class="class_number_name">上课校区：</span>
					                    <span class="s-list" id="campus" name="campus">
					                    </span>
					                    <!-- 校区编码隐藏域 -->
			         					<input type="hidden" value="" id="campus_no" />
			         					<input type="hidden" value="" id="campus_id" name="campusId"/>
					                </div>
		                		</c:if>
				            	<input type="hidden" value="${methed }" name="methed" id="methed"/>
				        		<input type="hidden" value="${lable }" name="courselable" id="courselable"/>
		                	</c:when>
		                	<c:otherwise>
		                		<div class="w w_all campus_div">
				                	<span class="class_number_name">上课校区：</span>
				                    <span class="s-list" id="campus" name="campus">
				                    </span>
				                    <!-- 校区编码隐藏域 -->
		         					<input type="hidden" value="" id="campus_no" />
		         					<input type="hidden" value="" id="campus_id" name="campusId"/>
				                </div>
				                <input type="hidden" value="0" id="methed"/>
		                	</c:otherwise>
		                </c:choose>
		            	<div class="w h">
		                	<span class="class_number_name" style="width: 120px;margin-left: -19px;">第一次上课时间：<em style="color:#f00;">*</em></span>
		                    <input type="text" readonly="readonly" name="startDate" class="txt laydate-icon" id="startDate" />
		                </div>
		                <input type="hidden" value="${classTypeId }" name="classTypeId" id="classTypeId"/> 
		                <input type="hidden" value="${editModuleNo }" id="editModuleNo"/>
		                <div class="w h">
		                	<span class="class_number_name">班号名称：<em style="color:#f00;">*</em></span>
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
		                    
		                    <i class="iconfont allowIconfont normal open">&#xe604;</i>
		                    <span class="i open" style="position: relative; top: 2px;">已允许</span>
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
	<c:if test="${classTypeId == 0 }">
		<script type="text/javascript" src="<%=rootPath %>/javascripts/common/itemList.js"></script>
	</c:if>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/modules/addClassModuleNo.js"></script> 
	<script type="text/javascript" src="<%=rootPath %>/javascripts/modules/returnClass.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/operate.js"></script>
	<script type="text/javascript">
    	$(function(){
    		$selectSubMenu('course_class_type');
    	});
    </script>
</body>
</html>