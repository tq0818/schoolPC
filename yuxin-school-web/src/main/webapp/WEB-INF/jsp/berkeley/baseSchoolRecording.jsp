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
<title>基地校录播课</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
	<%--<link rel="stylesheet" href="<%=rootPath %>/stylesheets/query/statistics.css">--%>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
	<style type="text/css">
		.pages li.disabled{padding:0px;}
		.screen-info select{margin-right: 50px;width: 180px;margin-bottom: 10px;}
		.screen-info input{width: 163px;height: 16px;line-height: 16px;}
	</style>
	<%--tob--%>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
	<script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>
<input type="hidden" id="schoolId" value='${schoolId}'/>
<input type="hidden" id="schoolName" value='${schoolName}'/>
<input type="hidden" id="isAdmin" value='${isAdmin}'/>
<input type="hidden" id="isSubAdmin" value='${isSubAdmin}'/>
	<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_berkeley.jsp"></jsp:include>
			<div class="mainbackground nopadding"  style="background: #f6f6f6;margin-left: 20px;height: 700px;">
				<div class="heading" style="height: 30px;padding-top: 10px;">
					<h2 class="h5" style="display: inline-block;">基地校</h2>
					<a href="<%=rootPath%>/baseSchool/baseSchoolRecording"  class="btn btn-primary recordingBtn">录播课</a>
					<a href="<%=rootPath%>/baseSchool/baseSchoolLive" class="btn  btn-default liveBtn">直播课</a>
					<span class="line"></span>
				</div>
				<div class="content-right">
					<%--<p class="screen-info" style="margin-bottom: 20px;">--%>
						<%----%>
					<%--</p>--%>
					<form method="post" id="searchForm">
				<div class="screen-info">
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

					<span>入学年份：</span>
					<select name="eduYear" id="eduYear">
						<option value="">请选择年份</option>
						<c:forEach items="${years}" var="item" >
							<option value="${item}" >${item}</option>
						</c:forEach>
					</select>
				</div>
				<div class="screen-info margin10">
					<span>班级：</span>
					<select name="eduClass" id="eduClass">
						<option value="">请选择班级</option>
						<c:forEach begin="1" end="30" varStatus="index">
							<option value="${index.index}">${index.index}班</option>
						</c:forEach>
					</select>
					<span class="date" style="margin-left: 0;">
						<span class="text" style="margin-right: 10px;">日期:</span>
						<span><input type="text" name="startTime" class="date-picker from" value="${startTime}"/><em style="margin: 0 20px 0 20px">至</em><input type="text" name="endTime" class="date-picker to" value="${endTime}"/></span>
					</span>
					<input type="text" id="username" name="username" placeholder="学员手机号/用户名查询" style="margin-left: 113px;"/>
					<%--<div style="text-align: center;margin-top: 10px;">--%>
						<%----%>
					<%--</div>--%>
					<span><a href="javascript:;" class="btn btn-primary searchContents" style="margin-right: 20px;">查询</a></span>
					<span><a href="javascript:;" class="btn btn-primary exportexcle">导出数据</a></span>

				</div>
			</form>
			<div class="user-list">
				<table class="table table-center" id="tableList">
					<tr data-buy="true">
						<%--<th width="3%"><input type="checkbox" class="checkboxAll"></th>--%>
						<th width="11%">课程名称</th>
						<th width="6%">用户名</th>
						<th width="7%">姓名</th>
						<th width="11%">学校</th>
						<th width="6%">班级</th>
						<th width="7%" class="btn-sort" fieldName="totle_study_length" sort="">总播放时长</th>
						<th width="7%" class="btn-sort" fieldName="study_rate" sort="">播完率</th>
						<th width="7%" class="btn-sort" fieldName="view_num" sort="">观看次数</th>
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
<script type="text/javascript" src="<%=rootPath %>/javascripts/branchschool/recirdWatchList.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/company/jquery.cityselect.js"></script>
<script type="text/javascript">
//	$selectSubMenu('statistics_org_detail');
	$selectThirdMenu('videoList');
	$selectThirdMenu('userVideoList');
	$.tableSort($(".btn-sort"),{
		callback:function(data){
			console.log(data);
			student.search(1,data);
		}
	});
</script>
</body>
</html>