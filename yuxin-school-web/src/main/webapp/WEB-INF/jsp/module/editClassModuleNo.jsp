<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑班号</title>
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
		            	<input type="hidden" value="${moduleNoVo.id }" name="id" id="id"/>
		            	<input type="hidden" value="${moduleNoVo.classTeachType }" name="classTeachType" id="classTeachType"/>
		            	<div class="w h">
		                	<span class="class_number_name">学科：</span>
		                	<span class="class_number_sp" id="itemOneId">${moduleNoVo.itemName }</span>
		                </div>
		                <div class="w h">
		                	<span class="class_number_name">学科小类：</span>
		                    <span class="class_number_sp" id="itemSecondName">${moduleNoVo.itemSecondName }</span>
		                </div>
		                <div class="w h">
		                	<span class="class_number_name">单元名称：</span>
		                	<input type="hidden" id="moduleId" value="${moduleNoVo.moduleId }">
		                    <span class="class_number_sp" id="moduleName">${moduleNoVo.moduleName }</span>
		                </div>
		                <div class="w h">
		                	<span class="class_number_name">授课方式：</span>
		                    <span class="class_number_sp" id="teachMethod">${wx:dictCode2Name(moduleNoVo.classTeachType)}</span>
		                </div>
		                <div class="w h">
		                	<span class="class_number_name">总课时：</span>
		                    <span class="class_number_sp" id="totalClassHour">${moduleNoVo.totalHours }</span>
		                </div>
		                <div class="w h w_all">
		                	<span class="class_number_name">课程单元信息：</span>
		                    <span class="class_number_sp  module-info" id="moduleDesc">${moduleNoVo.moduleDesc }</span>
		                </div>
		            </div>
		        </div>
		        <div class="operate_main">
		            <div class="title">班号信息</div>
		            <div class="class_number_cell">
			            <c:if test="${moduleNoVo.classTeachType =='TEACH_METHOD_FACE' }">
			            	<div class="w w_all">
			                	<span class="class_number_name">上课校区：</span>
			                    <span class="s-list" >
			                    	<a class="btn btn-sm btn-default active" href="javascript:;">${moduleNoVo.campusName }</a>
			                    </span>
			                    <!-- 校区编码隐藏域 -->
	         					<input type="hidden" value="${moduleNoVo.campusNo }" id="campus_no" />
	         					<input type="hidden" vlaue="${moduleNoVo.campusId }" id="campus_id" name="campusId"/>
			                </div>
		               </c:if>
		            	<div class="w h">
		                	<span class="class_number_name" style="width: 120px;margin-left: -19px;">第一次上课时间：<em style="color:#f00;">*</em></span>
		                    <input type="text" readonly="readonly" id="startDate"  value="<fmt:formatDate value="${moduleNoVo.startDate }" pattern="yyyy-MM-dd"/>"  class="txt laydate-icon" />
		                </div>
		                <div class="w h">
		                	<span class="class_number_name">班号名称：<em style="color:#f00;">*</em></span>
		                   	<input type="text" class="txt" id="moduleNoName" value="${moduleNoVo.name }" name="name"/>
		                </div>
		                <div class="w h">
		                	<span class="class_number_name">最大人数：</span>
		                    <input type="text" class="txt" value="${moduleNoVo.planEnrollStudents }" name="planEnrollStudents" id="planEnrollStudents"/>
		                </div>
		                <div class="w w_all">
		                	<span class="class_number_name">班号信息：</span>
		                    <textarea class="com_textarea" name="description" value="${moduleNoVo.description }">${moduleNoVo.description }</textarea>
		                </div>
		                
		                <div class="w w_all">
		                	<span class="class_number_name class_number_cell">允许现在招生：</span>
		                	<c:choose>
			                	<c:when test="${moduleNoVo.publishStatus eq 'MODULE_NO_ON_SALE' }">
			                		<i class="iconfont normal allowIconfont open">&#xe603;</i>
			                   		<span class="i open" >已允许</span>
			                	</c:when>
			                	<c:otherwise>
			                		<i class="iconfont normal allowIconfont close">&#xe604;</i>
			                    	<span class="i close" >已禁止</span>
			                	</c:otherwise>
		                	</c:choose>
		                    
		                    <input type="hidden" value="" name="publishStatus" value="${moduleNoVo.publishStatus }" id="publishStatus"/>
		                </div>
		            </div>
		            <input type="hidden" value="" name="next" id="next"/>
		            <input type="hidden" value="${classTypeId }" id="classTypeId"/>
		            <input type="hidden" value="${methed }" id="methed"/>
		            <input type="hidden" value="${editModuleNo }" id="editModuleNo"/>
		            <input type="hidden" value="${lable }" id="courselable"/>
		            <div class="subbtns">
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
	<script type="text/javascript" src="<%=rootPath %>/javascripts/operate.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/modules/returnClass.js"></script> 
	<script>
		$(function(){
			$selectMenu("course_class_type");
			var classTeachType = $("#classTeachType").val();
			if(classTeachType){
				if(classTeachType != 'TEACH_METHOD_FACE'){
					 $(".clear").find("li").each(function(){
						$(this).removeClass("step").addClass("step2");
					});
				}
			}
			
	    	$('.operate .s-list').unbind("click");
		});
	</script>
</body>
</html>