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
    	.col-md-1 {width:10% ;padding: 0px;}
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
    <script type="text/javascript" src="<%=rootPath %>/javascripts/modules/addGenseeModuleNoAndLessons.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/modules/operateModuleNoLession.js"></script> 
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
			                    	<label><input type="checkbox" name="moduleNo.lessonDay" checked value="1">周一</label>
			                        <label><input type="checkbox" name="moduleNo.lessonDay" value="2">周二</label>
			                        <label><input type="checkbox" name="moduleNo.lessonDay" value="3">周三</label>
			                        <label><input type="checkbox" name="moduleNo.lessonDay" value="4">周四</label>
			                        <label><input type="checkbox" name="moduleNo.lessonDay" value="5">周五</label>
			                        <label><input type="checkbox" name="moduleNo.lessonDay" value="6">周六</label>
			                        <label><input type="checkbox" name="moduleNo.lessonDay" value="0">周日</label>
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
			                <%-- <li class="dis_inb">
			                    <span class="s-title">课次开始时间</span>
			                    <span class="s-list">
			                        <input type="text" id="lessonTimeStart" value="09:00" name="moduleNo.startTime" readonly class="laydate-icon date-picker input_width_40" />
			                        
			                    </span>
			                </li>
			                <li class="dis_inb">
			                    <span class="s-title">结束时间</span>
			                    <span class="s-list">
			                        <input type="text" id="lessonTimeEnd" value="15:00" name="moduleNo.endTime" readonly class="laydate-icon date-picker input_width_40" />
			                    </span>
			                </li> --%>
			                
			                <li style=" overflow: hidden;">
			                 	<span class="s-title">课次开始时间</span>
			                    <span class="s-list boot-time">
			                        <div class="input-group date form_time col-md-1" data-date="" data-date-format="hh:ii" data-link-field="lessonTimeStart" data-link-format="hh:ii">
					                    <input class="form-control" size="16" dataType="startTime" id="startTime" type="text" value="09:00" readonly style="height: 30px;width:60px;">
										<span class="input-group-addon" style="min-height: 14px; display: table-cell;"><span class="glyphicon glyphicon-time" style="min-height: 14px"></span></span>
					                </div>
									<input type="hidden" id="lessonTimeStart" value="09:00" name="moduleNo.startTime"/>
			                    </span>
			                </li>
			                <li style=" overflow: hidden;">
			                 	<span class="s-title">课次结束时间</span>
			                    <span class="s-list boot-time">
			                        <div class="input-group date form_time col-md-1" data-date="" data-date-format="hh:ii" data-link-field="lessonTimeEnd" data-link-format="hh:ii">
					                    <input class="form-control" size="16" dataType="endTime" id="endTime" type="text" value="11:30" readonly style="height: 30px;width:60px;">
										<span class="input-group-addon" style="min-height: 14px; display: table-cell;"><span class="glyphicon glyphicon-time" style="min-height: 14px"></span></span>
					                </div>
									<input type="hidden" id="lessonTimeEnd" value="11:30" name="moduleNo.endTime"/>
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
			                <li>
			                    <span class="s-title">直播类型</span>
			                    <span class="s-list">
			                        <label><input name="moduleNo.liveClassType" type="radio" value="small" >小班课</label>
										<i class="iconfont ask" title="适用于包括讲授为主，并辅以文字、语音互动沟通的授课方式。如果您的课堂需要适当的互动过程。建议选用这种方式。小班课并没有限制进入人数，建议不要超过100人" style="margin-right: 20px;margin-left: -10px;">&#xe60f;</i>
			                        <label><input name="moduleNo.liveClassType" type="radio" value="big" checked="checked" >大讲堂</label>
			                        	<i class="iconfont ask" title="适用于以宣讲为主的授课方式。如果您的课堂不太会出现频繁的老师与学员之间的互动。建议选用这种方式。大讲堂对学员人数无限制，您甚至可以开一场万人级别的课堂" style="margin-right: 20px;margin-left: -10px;">&#xe60f;</i>
			                        <em style="color:#f00;">* 排课后不可修改</em>
			                    </span>
			                </li>
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
			                    <th>直播类型</th>
			                    <th>操作</th>
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
    	    			return false;
    	    		}
    	    		if(parseInt(lessonTimeEnd) == currentTime){
    	    			alert("结束时间不能等于开始时间");
    	    			return false;
    	    		}
    	    	}else if(dataType == "endTime"){
    	    		//查找startTime的值
    	    		var lessonTimeStart = $("#startTime").val().replace(":","");
    	    		if(parseInt(lessonTimeStart) > currentTime){
    	    			alert("开始时间不能大于结束时间");
    	    			return false;
    	    		}
    	    		if(parseInt(lessonTimeStart) == currentTime){
    	    			alert("结束时间不能等于开始时间");
    	    			return false;
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