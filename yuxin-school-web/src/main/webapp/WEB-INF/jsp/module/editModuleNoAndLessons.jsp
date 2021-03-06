<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑课次</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
    <style>
    	.input_width_40{width:60%}
    	.input_width_60{width:85%}
    	.input_width_15{width:30%}
    	.col-md-1 {width:10%;padding: 0px;}
    	.boot-time{float: none;}
    	.operate .mainbackground .select-option li span.s-title {
		    width: 100px;
		}
    	.operate .mainbackground .select-option li {
		    padding-left: 125px;
		}
    </style>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
    <%-- <script type="text/javascript" src="<%=rootPath %>/javascripts/modules/moduleAlert.js"></script> --%>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/modules/addModuleNoAndLessons.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/modules/operateModuleNoLession.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/DateUtils.js"></script>
</head>
<body>
	<!-- 二级导航开始 -->
		<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
	<!-- 二级导航结束 -->
	
	<!--  内容开始 -->
		<div class="u-wrap operate">
		    <div class="nopadding provider" data-provider="${liveProvider}">
		        <div class="steps">
		            <div class="line"></div>
		            <ul class="clear">
		                <li class="step s1 active">
		                    <i>1</i>
		                    <em>班号</em>
		                </li>
		                <li class="step s2 hover">
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
			<form action="<%=rootPath %>/classModuleNo/editModuleNoAndAddLesson" method="post" id="submitForm">
			        <div class="heading">
			            <h2 class="h5">排课</h2>
			            <span class="search">${moduleNo.name }</span>
			            <span class="line"></span>
			        </div>
			        <div class="select-option clear">
			            <ul>
			            	<input name="moduleNo.startDate" type="hidden" value="${startDate }" id="startDate"/>
			            	<input name="moduleNo.totalHours" type="hidden" value="${moduleNo.totalHours }" id="totalHours"/>
			            	<input name="moduleNo.moduleName" type="hidden" value="${moduleName }" id="moduleName"/>
			            	<input name="moduleNo.id" type="hidden" value="${moduleNo.id }" id="moduleNoId" />
			            	<input name="classTeachType" type="hidden" value="${classTeachType }" id="classTeachType"/>
			            	
			            	<!-- 排课列表是否展示 -->
			            	<input type="hidden" value="${isHide }" id="isHide" />
			            	
			            	
			                 <!-- <li>
			                    <span class="s-title">班别</span>
			                    <span class="s-list class-name">
			                        <c:if test="${moduleNo.lessonType eq  'LESSON_OFF_JOB'}"><a class="btn btn-sm btn-default active" href="javascript:;" lessionType="LESSON_OFF_JOB">脱产班</a></c:if>
			                        <c:if test="${moduleNo.lessonType eq  'LESSON_WEEKEND'}"><a class="btn btn-sm btn-default active" href="javascript:;" lessionType="LESSON_WEEKEND">周末班</a></c:if>
			                        <c:if test="${moduleNo.lessonType eq  'LESSON_NIGHT'}"><a class="btn btn-sm btn-default active" href="javascript:;" lessionType="LESSON_NIGHT">晚班</a></c:if>
			                        <c:if test="${moduleNo.lessonType eq  'LESSON_SATURDAY'}"><a class="btn btn-sm btn-default active" href="javascript:;" lessionType="LESSON_SATURDAY">周六班</a></c:if>
			                        <c:if test="${moduleNo.lessonType eq  'LESSON_SUNDAY'}"><a class="btn btn-sm btn-default active" href="javascript:;" lessionType="LESSON_SUNDAY">周日班</a></c:if>
			                    </span>
			                    <input type="hidden" value="${moduleNo.lessonType }" id="lessonType" name="moduleNo.lessonType"/>
			                </li>
			                <li>
			                    <span class="s-title">排课天</span>
			                    <span class="s-list weekDiv">
				                    <c:forEach items="${listDay }" var="item">
			                    		<label>${item }</label>
				                    </c:forEach>
			                    </span>
			                </li>
			                <li>
			                    <span class="s-title ">上课时段</span>
			                    <span class="s-list lesson-time">
			                        <c:if test="${moduleNo.lessonScope eq  'LESSON_TIME_MORNING'}"><a class="btn btn-sm btn-default active" href="javascript:;"  lessonScope="LESSON_TIME_MORNING">上午</a></c:if>
			                        <c:if test="${moduleNo.lessonScope eq  'LESSON_TIME_AFTERNOON'}"><a class="btn btn-sm btn-default active" href="javascript:;" lessonScope="LESSON_TIME_AFTERNOON">下午</a></c:if>
			                        <c:if test="${moduleNo.lessonScope eq  'LESSON_TIME_WHOLE_DAY'}"><a class="btn btn-sm btn-default active" href="javascript:;" lessonScope="LESSON_TIME_WHOLE_DAY">全天</a></c:if>
			                        <c:if test="${moduleNo.lessonScope eq  'LESSON_TIME_NIGHT'}"><a class="btn btn-sm btn-default active" href="javascript:;" lessonScope="LESSON_TIME_NIGHT">晚上</a></c:if>
			                    </span>
			                    <input name="moduleNo.lessonScope" value="${moduleNo.lessonScope }" id="lessonScope" type="hidden"/>
			                </li>
			                <li class="dis_inb">
			                    <span class="s-title">课次开始时间</span>
			                    <span class="s-list">
			                        <input type="text" id="startTime" value="${moduleNo.startTime}"" name="startTime" readonly class="laydate-icon input_width_40" />
			                        
			                    </span>
			                </li>
			                <li class="dis_inb">
			                    <span class="s-title">结束时间</span>
			                    <span class="s-list">
			                        <input type="text" id="endTime" value="${moduleNo.endTime}" name="endTime" readonly class="laydate-icon input_width_40" />
			                    </span>
			                </li>
			                <li>
			                    <span class="s-title">总课时</span>
			                    <span class="s-list">
			                        ${totalHours }
			                    </span>
			                </li>
			                <li>
			                    <span class="s-title">每课次课时</span>
			                    <span class="s-list">
			                        <input type="text" id="lessonHour" value="${moduleNo.lessonHour }" readonly name="lessonHour" class="lessonHour" style="width:35px;"/>
			                    </span>
			                </li>
			               <li >
			                	<span class="s-title">预排课</span>
			                	<span class="s-list ">
			                        <a class="btn btn-sm btn-default active" href="javascript:;" id="preInitClass">预排课</a>
			                    </span>
			                </li> -->
			            </ul>
			        </div>
			        <div class="operate_list class_time_list lessions_class">
			        	<div class="select-option clear">
			            	<ul>
			                    <li>
			                        <span class="s-list">
			                            <a class="btn btn-sm btn-default active" id="addLessonForModuleNo" href="#cancel">新增课</a>
			                        </span>
			                    </li>
			                </ul>    
			            </div>
			            <table class="table table-hover table-center operate_table"  id="bodyHtml">
			            	<tr >
			                	<th>日期</th>
			                    <th>星期</th>
			                    <th>时段</th>
			                    <th>时间</th>
			                    <th>课次</th>
			                    <th>课时</th>
			                    <c:if test="${classTeachType eq 'TEACH_METHOD_LIVE'}">
				                    <c:choose>
				                    	<c:when test="${liveProvider eq 'ht' }">
				                    		<th>弹幕</th>
				                    		<th>模式</th>
				                    	</c:when>
				                    	<c:when test="${liveProvider eq 'cc'}">
				                    		<th>弹幕</th>
				                    		<th>模板</th>
				                    	</c:when>
				                    	<c:otherwise>
				                    		<th>支持移动端</th>
				                    	</c:otherwise>
				                    </c:choose>
			                    </c:if>
			                    <th width="20%">操作</th>
			                </tr>
			                
			                <c:forEach items="${lessons }" var="item" varStatus="index">
			                	<tr>
				                	<td>
				                		<input type="text" readonly name="lesson[${index.index }].lessonDate" value=" <fmt:formatDate value="${item.lessonDate }" pattern="yyyy-MM-dd"/>" class="readonly input_width_60 date-picker-24-${index.index }" >
				                	</td>
				                	<td>
				                		<input type="text" readonly name="lesson[${index.index }].weekType" value="${item.weekType }" class="readonly input_width_40 " >
				                	</td>
				                	<td>
				                		<input type="text" name="lesson[${index.index }].scope" value="${item.scope }" class="readonly input_width_40" >
				                	</td>
				                	<td>
				                		<input type="text" name="lesson[${index.index }].lessonTimeStart" readonly="readonly" value="${item.lessonTimeStart }" class="readonly input_width_15 date-picker-${index.index }" >
				                		-<input type="text" name="lesson[${index.index }].lessonTimeEnd" readonly="readonly" value="${item.lessonTimeEnd }" class="readonly input_width_15 date-picker-${index.index }" >
				                	</td>
				                	<td>
				                		<input type="text" name="lesson[${index.index }].lessonName" value="${item.lessonName }" class="readonly " >
				                	</td>
				                	<td>
				                		<input type="text" name="lesson[${index.index }].lessonHour" value="${item.lessonHour }" class="readonly input_width_40 lessonHour" maxlength="3" >
				                	</td>
				                	<c:if test="${classTeachType eq 'TEACH_METHOD_LIVE'}">
					                	<c:if test="${liveProvider ne 'ht' and liveProvider ne 'cc' }">
						                	<td>
						                		<c:choose>
						                			<c:when test="${item.supportMobile eq 1 }"><input type="text" value="是" class="readonly input_width_40 lessonHour" maxlength="3" ></c:when>
						                			<c:otherwise><input type="text" value="否" class="readonly input_width_40 lessonHour" maxlength="3" ></c:otherwise>
						                		</c:choose>
						                	</td>
					                	</c:if>
					                	<c:if test="${liveProvider eq 'ht' }">
						                	<td>
						                		<c:choose>
						                			<c:when test="${item.barrage eq 1 }"><div style="width:85px">是</div><select style="display:none;"><option value="1"></option></select></c:when>
						                			<c:otherwise><div style="width:85px">否</div><select style="display:none;"><option value="0"></option></select></c:otherwise>
						                		</c:choose>
						                	</td>
						                	<td>
						                		<c:choose>
						                			<c:when test="${item.modetype eq 1 }"><div style="width:85px">语音互动课堂</div><select style="display:none;"><option value="1"></option></select></c:when>
						                			<c:when test="${item.modetype eq 5 }"><div style="width:85px">小班课</div><select style="display:none;"><option value="5"></option></select></c:when>
						                			<c:otherwise><div style="width:85px">大班课</div><select style="display:none;"><option value="3"></option></select></c:otherwise>
						                		</c:choose>
						                	</td>
					                	</c:if>
						                <c:if test="${liveProvider eq 'cc'}">
						                	<td>
						                		<c:choose>
						                			<c:when test="${item.barrage eq 1 }"><div style="width:85px">是</div><select style="display:none;"><option value="1"></option></select></c:when>
						                			<c:otherwise><div style="width:85px">否</div><select style="display:none;"><option value="0"></option></select></c:otherwise>
						                		</c:choose>
						                	</td>
						                	<td>
						                		<c:choose>
						                			<c:when test="${item.modetype eq 1 }"><div style="width:85px">模板1</div><select style="display:none;"><option value="1"></option></select></c:when>
						                			<c:when test="${item.modetype eq 2 }"><div style="width:85px">模板2</div><select style="display:none;"><option value="1"></option></select></c:when>
						                			<c:when test="${item.modetype eq 3 }"><div style="width:85px">模板3</div><select style="display:none;"><option value="1"></option></select></c:when>
						                			<c:when test="${item.modetype eq 4 }"><div style="width:85px">模板4</div><select style="display:none;"><option value="1"></option></select></c:when>
						                			<c:when test="${item.modetype eq 5 }"><div style="width:85px">模板5</div><select style="display:none;"><option value="1"></option></select></c:when>
						                			<c:when test="${item.modetype eq 6 }"><div style="width:85px">模板6</div><select style="display:none;"><option value="1"></option></select></c:when>
						                		</c:choose>
						                	</td>
						                </c:if>
					                </c:if>
				                	<input type="hidden" class="idClass" value="${item.id }" >
				                	<td>
					                	<a class="btn btn-sm btn-success" inputid="${index.index }" data-lesson="${item}" href="javascript:;">修改</a>
					                	<a class="btn btn-sm btn-danger" href="javascript:;">删除</a>
				                	</td>
				                </tr>
			                </c:forEach>
			                
			            </table>
			            <div class="subbtns">
			                <a class="btn btn-success back_step" href="<%=rootPath %>/classModuleNo/editModule/${moduleNo.id }">上一步</a>
			                <!-- <a class="btn btn-big btn-success save_step" >保存</a> -->
			                <a class="btn btn-success "  href="<%=rootPath %>/classModuleNo/editLessonTeacher/${moduleNo.id }">下一步</a>
			                <a class="btn btn-default back_list saveRoom" id="cancel" href="javascript:;">取消</a>
			            </div>
			        </div>
		        </form>
		    </div>
		</div>
	<input type="hidden" value="${lessonsLength }" id="lessonsFlag">
	<script type="text/javascript" src="<%=rootPath %>/javascripts/modules/returnClass.js"></script> 
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap.min.js"></script>
	 <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	 <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<!--  内容结束 -->
	<script type="text/javascript">
    	$(function(){
    		$selectSubMenu('course_class_type');
    	});
    </script>
</body>
</html>