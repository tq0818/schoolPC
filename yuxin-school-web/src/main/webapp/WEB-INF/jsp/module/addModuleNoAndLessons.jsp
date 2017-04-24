<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课次列表</title>
    <%-- <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/jquery.datetimepicker.css" /> --%>
    
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css">
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
    <%-- <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.datetimepicker.js"></script> --%>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/modules/addModuleNoAndLessons.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/modules/operateModuleNoLession.js"></script> 
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
		<div class="u-wrap operate" style="margin-top: -10px;">
			<div class="mainbackground">
			<form action="<%=rootPath %>/classModuleNo/editModuleNoAndAddLesson" method="post" id="submitForm">
			        <div class="heading">
			            <h2 class="h5">排课</h2>
			            <span class="search">${moduleNoName }</span>
			            <span class="line"></span>
			        </div>
			        <div class="select-option clear">
			            <ul>
			            	<input name="moduleNo.startDate" type="hidden" value="${startDate }" id="startDate"/>
			            	<input name="moduleNo.totalHours" type="hidden" value="${totalHours }" id="totalHours"/>
			            	<input name="moduleNo.moduleName" type="hidden" value="${moduleName }" id="moduleName"/>
			            	<input name="classTeachType" type="hidden" value="${classTeachType }" id="classTeachType"/>
			            	<input name="moduleNo.id" type="hidden" value="${id }" id="id" />
			            	
			            	<!-- 排课列表是否展示 -->
			            	<input type="hidden" value="${isHide }" id="isHide" />
			                <li>
			                    <span class="s-title">班别</span>
			                    <span class="s-list class-name">
			                        <a class="btn btn-sm btn-default active" href="javascript:;" lessionType="LESSON_OFF_JOB">脱产班</a>
			                        <a class="btn btn-sm btn-default" href="javascript:;" lessionType="LESSON_WEEKEND">周末班</a>
			                        <a class="btn btn-sm btn-default" href="javascript:;" lessionType="LESSON_NIGHT">晚班</a>
			                        <a class="btn btn-sm btn-default" href="javascript:;" lessionType="LESSON_SATURDAY">周六班</a>
			                        <a class="btn btn-sm btn-default" href="javascript:;" lessionType="LESSON_SUNDAY">周日班</a>
			                    </span>
			                    <input type="hidden" value="LESSON_WEEKEND" id="lessonType" name="moduleNo.lessonType"/>
			                </li>
			                <li>
			                    <span class="s-title">排课天</span>
			                    <span class="s-list weekDiv">
			                    	<label><input type="checkbox" name="moduleNo.lessonDay" <c:if test="${currentDate == 1 }">checked</c:if> value="1">周一</label>
			                        <label><input type="checkbox" name="moduleNo.lessonDay" <c:if test="${currentDate == 2 }">checked</c:if> value="2">周二</label>
			                        <label><input type="checkbox" name="moduleNo.lessonDay" <c:if test="${currentDate == 3 }">checked</c:if> value="3">周三</label>
			                        <label><input type="checkbox" name="moduleNo.lessonDay" <c:if test="${currentDate == 4 }">checked</c:if> value="4">周四</label>
			                        <label><input type="checkbox" name="moduleNo.lessonDay" <c:if test="${currentDate == 5 }">checked</c:if> value="5">周五</label>
			                        <label><input type="checkbox" name="moduleNo.lessonDay" <c:if test="${currentDate == 6 }">checked</c:if> value="6">周六</label>
			                        <label><input type="checkbox" name="moduleNo.lessonDay" <c:if test="${currentDate == 7 }">checked</c:if> value="0">周日</label>
			                    </span>
			                </li>
			                <li>
			                    <span class="s-title ">上课时段</span>
			                    <span class="s-list lesson-time">
			                        <a class="btn btn-sm btn-default active" href="javascript:;" lessonScope="LESSON_TIME_MORNING">上午</a>
			                        <a class="btn btn-sm btn-default" href="javascript:;" lessonScope="LESSON_TIME_AFTERNOON">下午</a>
			                        <a class="btn btn-sm btn-default" href="javascript:;" lessonScope="LESSON_TIME_WHOLE_DAY">全天</a>
			                        <a class="btn btn-sm btn-default" href="javascript:;" lessonScope="LESSON_TIME_NIGHT">晚上</a>
			                    </span>
			                    <input name="moduleNo.lessonScope" value="LESSON_TIME_MORNING" id="lessonScope" type="hidden"/>
			                </li>
			                
			                <li style=" overflow: hidden;">
			                 	<span class="s-title">课次开始时间</span>
			                    <span class="s-list boot-time">
			                        <div class="input-group date form_time col-md-1" data-date="" data-date-format="hh:ii" data-link-field="lessonTimeStart" data-link-format="hh:ii">
					                    <input class="form-control" size="16" dataType="startTime" id="startTime" type="text" value="09:00" readonly style="height: 30px;width:60px;">
										<span class="input-group-addon" style="min-height: 14px; display: table-cell;"><span class="glyphicon glyphicon-time" style="min-height: 14px"></span></span>
					                </div>
									<input type="hidden" id="lessonTimeStart" value="09:00" name="moduleNo.startTime" style="width:60px"/>
			                    </span>
			                </li>
			                <li style=" overflow: hidden;">
			                 	<span class="s-title">课次结束时间</span>
			                    <span class="s-list boot-time">
			                        <div class="input-group date form_time col-md-1" data-date="" data-date-format="hh:ii" data-link-field="lessonTimeEnd" data-link-format="hh:ii">
					                    <input class="form-control" size="16" dataType="endTime" id="endTime" type="text" value="15:00" readonly style="height: 30px;width:60px;">
										<span class="input-group-addon" style="min-height: 14px; display: table-cell;"><span class="glyphicon glyphicon-time" style="min-height: 14px"></span></span>
					                </div>
									<input type="hidden" id="lessonTimeEnd" value="15:00" name="moduleNo.endTime" style="width:60px"/>
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
			                        <input type="text" id="lessonHour" value="1" name="moduleNo.lessonHour" maxlength="4" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" class="lessonHour" style="width:35px;"/>
			                    </span>
			                </li>
			                <c:if test="${liveProvider ne 'ht' and liveProvider ne 'cc' and classTeachType eq 'TEACH_METHOD_LIVE'}">
				                <li>
				                    <span class="s-title">是否支持移动端</span>
				                    <span class="s-list">
				                        <label><input name="moduleNo.supportMobile" type="radio" value="是" >是</label>
				                        <label><input name="moduleNo.supportMobile" type="radio" value="否" checked="checked" >否</label>
				                        <em style="color:#f00;">* 排课后不可修改, 需要使用直播助手和桌面共享的方式进行上课。</em>
				                    </span>
				                </li>
			                </c:if>
			                <c:if test="${liveProvider eq 'ht' && classTeachType eq 'TEACH_METHOD_LIVE'}">
				                <li>
				                    <span class="s-title">是否支持弹幕</span>
				                    <span class="s-list">
				                        <label><input name="moduleNo.barrage" type="radio" value="1" >是</label>
				                        <label><input name="moduleNo.barrage" type="radio" value="0" checked="checked" >否</label>
				                        <em style="color:#f00;">* 排课后不可修改</em>
				                    </span>
				                </li>
				                <li>
				                    <span class="s-title">模式</span>
				                    <span class="s-list">
				                    <select id="modetype">
				                    	<option value="3">大班课</option>
				                    	<option value="1">语音互动课堂</option>
				                    </select>
				                        <em style="color:#f00;">* 排课后不可修改</em>
				                    </span>
				                </li>
				                <li>
				                	<span style="color:#999;"><b>大班课:</b>适用于以宣讲为主的授课方式。如果您的课堂不太会出现频繁的老师与学员之间的互动。建议选用这种方式。<br/>
                        			<b>语音互动课堂:</b>适用于包括讲授为主，并辅以文字、语音互动沟通的授课方式。如果您的课堂需要适当的互动过程，建议选用这种方式。</span>
				                </li>
			                </c:if>
			                <c:if test="${liveProvider eq 'cc' && classTeachType eq 'TEACH_METHOD_LIVE'}">
				                <li>
				                    <span class="s-title">是否支持弹幕</span>
				                    <span class="s-list">
				                        <label><input name="moduleNo.barrage" type="radio" value="1" >是</label>
				                        <label><input name="moduleNo.barrage" type="radio" value="0" checked="checked" >否</label>
				                        <em style="color:#f00;">* 排课后不可修改</em>
				                    </span>
				                </li>
				                <li>
				                    <span class="s-title">直播间模板</span>
				                    <span class="s-list">
				                    <select id="modetype">
				                    	<option value="1">模板1</option>
				                    	<option value="2">模板2</option>
				                    	<option value="3">模板3</option>
				                    	<option value="4">模板4</option>
				                    	<option value="5">模板5</option>
				                    	<option value="6">模板6</option>
				                    </select>
				                        <em style="color:#f00;">* 排课后不可修改</em>
				                    </span>
				                </li>
				                <li>
				                	<span style="color:#999;">
				                        <b>不同直接模板内包含不同功能，模板对应功能如下：</b></br>
				                        <b>&nbsp;</b>模板一：视频直播；</br>
				                        <b>&nbsp;</b>模板二：视频直播+聊天互动+直播问答；</br>
				                        <b>&nbsp;</b>模板三：视频直播+聊天互动；</br>
				                        <b>&nbsp;</b>模板四：视频直播+聊天互动+直播文档；</br>
				                        <b>&nbsp;</b>模板五：视频直播+聊天互动+直播文档+直播问答；</br>
				                        <b>&nbsp;</b>模板六：视频直播+直播问答；</span>
				                </li>
			                </c:if>
			                <li>
			                	<span class="s-title">预排课</span>
			                	<span class="s-list ">
			                        <a class="btn btn-sm btn-default active" href="javascript:;" id="preInitClass">预排课</a>
			                    </span>
			                </li>
			            </ul>
			        </div>
			        <div class="operate_list class_time_list lessions_class">
			        	<div class="select-option clear">
			            	<ul>
			                    <li>
			                        <span class="s-list">
			                            <a class="btn btn-sm btn-default active" id="addLessonForModuleNo" href="javascript:;">新增课</a>
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
				                    	<c:when test="${liveProvider eq 'cc' }">
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
			            </table>
			            <div class="subbtns">
			                <a class="btn btn-success back_step" href="javascript:;">上一步</a>
			                <!-- <a class="btn btn-big btn-success save_step" >保存</a> -->
			                <a class="btn btn-success save_next_step"  href="javascript:;">保存并继续</a>
			                <a class="btn btn-default back_list saveRoom" >取消</a>
			            </div>
			        </div>
		        </form>
		    </div>
		</div>
	<!--  内容结束 -->
	 <script type="text/javascript" src="<%=rootPath %>/javascripts/modules/returnClass.js"></script> 
	 <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap.min.js"></script>
	 <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	 <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>

	<script type="text/javascript">
    	$(function(){
    		$selectSubMenu('course_class_type');
    		var date1 = new Date();
    		date1 = date1.getFullYear() +"-"+(date1.getMonth() +1) + "-" + date1.getDate();
    		$('.form_time').datetimepicker({
    	        language:  'zh-CN',
    			autoclose: 1,
    			startDate: date1,
    			todayHighlight: 1,
    			startView: 1,
    			minView: 0,
    			maxView: 1,
    			forceParse: 0
    	    }).on("changeDate", function(){
    	    	var dataType = $(this).find("input").attr("dataType");
    	    	var currentTime = $(this).find("input").val().replace(":","");
    	    	if(dataType == "startTime"){
    	    		//查找endTime的值
    	    		var lessonTimeEnd = $("#endTime").val().replace(":","");
    	    		if(parseInt(lessonTimeEnd) < currentTime){
    	    			alert("结束时间不能小于开始时间");
    	    		}
    	    		if(parseInt(lessonTimeEnd) == currentTime){
    	    			alert("结束时间不能等于开始时间");
    	    		}
    	    	}else if(dataType == "endTime"){
    	    		//查找startTime的值
    	    		var lessonTimeStart = $("#startTime").val().replace(":","");
    	    		if(parseInt(lessonTimeStart) > currentTime){
    	    			alert("开始时间不能大于结束时间");
    	    		}
    	    		if(parseInt(lessonTimeStart) == currentTime){
    	    			alert("结束时间不能等于开始时间");
    	    		}
    	    	}
    	    });
    	});
    </script>
    <!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
		<p><i></i>排课中,请稍后...</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
	<!--  ajax加载中div结束 -->
	
</body>
</html>