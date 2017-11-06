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
				<div>
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
				<div style="margin-top: 10px;">
					<span>日期</span>
					<span><input type="text" name="startTime" class="date-picker from" value="${startTime}"/><em>到</em><input type="text" name="endTime" class="date-picker to" value="${endTime}"/></span>
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
						<th width="6%" class="btn-sort" fieldName="study_rate" sort="">播放率</th>
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
<script type="text/javascript" src="<%=rootPath%>/javascripts/selectStudentGroup.js"></script>
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
</script>
</body>
</html>