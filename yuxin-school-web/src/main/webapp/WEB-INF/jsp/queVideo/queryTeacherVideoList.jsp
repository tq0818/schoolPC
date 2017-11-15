<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>教师授课统计</title>
	<link rel="stylesheet" type="text/css"
		  href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
	<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query/statistics.css">
	<style type="text/css">
		.pages li.disabled{padding:0px;}
	</style>
</head>
<body>
<input type="hidden" id="schoolId" value='${schoolId}'/>
<input type="hidden" id="schoolName" value='${schoolName}'/>
<input type="hidden" id="isAdmin" value='${isAdmin}'/>
<input type="hidden" id="isSubAdmin" value='${isSubAdmin}'/>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_statistics.jsp"></jsp:include>
<div class="u-wrap query overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query.jsp"></jsp:include>
	<div class="right-side set-system">
		<div class="mainbackground nopadding">
			<div class="heading">
				<h2 class="h5">点播情况</h2>
				<span class="line"></span>
			</div>
			<form method="post" id="searchForm">
				<div class="screen-info ">
					<span>区域：</span>
					<select name="eduArea" id="eduArea">
						<c:forEach items="${areas}" var="area" >
							<option value="${area.itemCode}" data-id="${area.id}" ${student.eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
						</c:forEach>
					</select>
					<span>学校：</span>
					<select name="eduSchoolStep" id="eduSchoolStep">
						<option value="">请选择学校性质</option>
						<c:forEach items="${stepNews}" var="step" >
							<option value="${step.itemCode}" data-id="${step.id}" >${step.itemValue}</option>
						</c:forEach>
					</select>

					<select name="eduSchool" id="eduSchool" data-id="${student.eduSchool}">
						<option value="">请选择所在学校</option>
					</select>

					<span>学科：</span>
					<select name="eduSubject" id="eduSubject">
						<option value="">请选择学科</option>
						<c:forEach items="${subjectItem}" var="item" >
							<option value="${item.itemCode}" data-id="${item.id}" >${item.itemName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="screen-info "style="margin-top: 10px;">
					<span class="date" style="margin-left:0">
						<i class="text">日期</i>
						<span><input type="text" name="startTime" class="date-picker from" value="${startTime}"/><em>至</em><input type="text" name="endTime" class="date-picker to" value="${endTime}"/></span>
					</span>
					<input type="text" id="teaName" name="teaName" placeholder="请输入教师姓名"/>
					<span><a href="javascript:;" class="btn btn-primary searchContents">查询</a></span>
					<span><a href="javascript:;" class="btn btn-primary exportexcle">导出数据</a></span>
				</div>
			</form>
			<div class="user-list">
				<table class="table table-center" id="tableList">
					<tr data-buy="true">
						<%--<th width="3%"><input type="checkbox" class="checkboxAll"></th>--%>
						<th width="8%">教师</th>
						<th width="12%">课程名</th>
						<th width="6%">区域</th>
						<th width="12%">学校</th>
						<th width="6%">学科</th>
						<th width="8%">课程学段</th>
						<th width="8%" class="btn-sort" fieldName="totle_study" sort="">总播放量</th>
						<th width="10%" class="btn-sort" fieldName="totle_study_length" sort="">总播放时长</th>
						<th width="6%" class="btn-sort" fieldName="study_rate" sort="">播完率</th>
						<th width="6%">操作</th>
					</tr>
					<c:choose>
						<c:when test="${userorg_roleopenflag==1 && proxyOrgRole ==1 }">
							<tr><td colspan="15">暂无数据</td></tr>
						</c:when>
						<c:otherwise>
							<tr><td colspan="14">暂无数据</td></tr>
						</c:otherwise>
					</c:choose>


				</table>
				<div class="pages pagination"></div>
			</div>
		</div>
	</div>
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
		<p><i></i>加载中,请稍后...</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
	<!--  ajax加载中div结束 -->

	<!-- popupwin 编辑学生界面 开始    -->
	<div class="popupwin-box queryVideoDailyInfo1 clear" style="display:none">
		<div class="popupwin queryVideoDailyInfo" style="width:1000px; height: auto;top:10px;" data-pupwin="modal">
			<div class="popupwin-title">
					<h2 class="h5">观看比例</h2>
					<i class="close iconfont canclekuang"></i>
				</div>
			<div class="popupwin-main">
				<div class="btn-grouplist"><a href="" class="btn active  pull-left">观看比例</a>
					<!-- ==================终端 选择pc和移动 新加=================-->
					<div class="btn_group2 pull-right">
						<span class="pull-left">终端：</span>
						<div class="pull-left" content="viewsScale">
							<a name="terminal" v="0" t="all" class="active" href="javascript:void(0);">全部</a>
							<a name="terminal" v="1" t="pc" href="javascript:void(0);">PC</a>
							<a name="terminal" v="2" t="mobile" href="javascript:void(0);">移动</a>
						</div>
					</div>
				</div>
				<div class="demand-count viewsCount" axisPointer="shadow" id="viewsScale" style="width:960px;height: 380px;"></div>
			</div>
		</div>
	</div>
	<!-- popupwin 编辑学生界面结束 -->
</div>
<input type="hidden" id="selectCounts" value="10">
<script type="text/javascript" src="<%=rootPath %>/javascripts/query/video/query_teacher_video_list.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/company/jquery.cityselect.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts-all.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/byecharts.js"></script>
<script type="text/javascript">
//	$selectSubMenu('statistics_org_detail');

	$selectThirdMenu('videoList');
	$selectThirdMenu('teacherVideoList');

	$.tableSort($(".btn-sort"),{
		callback:function(data){
			console.log(data);
			student.search(1,data);
		}
	});
$(document).statistical().changeType({
	callback:function(e){
		var terminal = $(e).attr("t"),//设备类型
				$viewId = document.getElementById($(e).parent().attr("content"));//容器
		if($(e).parent().attr("content") == "viewsCount"){//line

		}else{//bar
			if ($(".from").val()!="" && $(".to").val()!="") {
				if ($(".to").val() < $(".from").val()) {
					$.msg("时间范围不正确");
					return;
				}else if(new Date($(".to").val()) - new Date($(".from").val()) > 30*24*60*60*1000){
					$.msg("时间范围不能超过30天");
					return;
				}
			}else{
				$.msg("时间选项必填");
				return;
			}
			var dataKey = new Array(),dataValue = new Array();
			$.ajax({
				url: rootPath + "/query/statistics/queryVideoCourseDaily",
				data:{classId:$("#classType").val(),startTime:$(".from").val(), endTime:$(".to").val(),className:$("#className").val()},
				success:function(result){
					result = result.attentions ? result.attentions:null;
					for(var i=0; result && i<result.length; i++){
						dataKey.push(result[i].section);
						if(terminal == 'pc'){
							dataValue.push(result[i].pc);
						}else if(terminal == 'mobile'){
							dataValue.push(result[i].mobile);
						}else{
							dataValue.push(result[i].pc+result[i].mobile);
						}

					}
					//更新数据
					var option =$($viewId).data();
					if(JSON.stringify(option) != "{}") {
						option.id = $viewId;
						option.tooltip.axisPointer = $($viewId).attr("axisPointer");
						option.series[0].data = dataValue;
						$(document).statistical().setCharts(option);
					}
				}
			});
		}

	}
});

</script>
</body>
</html>