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
<title>查询统计</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
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
					<h2 class="h5">学员列表</h2>
					<span class="line"></span>
				</div>
				<form method="post" id="searchForm">
					<div>
						<%--<input type="text" id="stuMobile" name="mobile" placeholder="手机号" maxlength="11"/>--%>
						<%--<input type="text" id="stuusername" name="username" placeholder="用户名"/>--%>
						<input type="hidden" id="isStu" name="isStu" value="1"/>
						<p>区域</p>
						<select name="eduArea" id="eduArea">
							<c:forEach items="${areas}" var="area" >
								<option value="${area.itemCode}" data-id="${area.id}" ${student.eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
							</c:forEach>
						</select>
						<p>学校</p>
							<select name="eduSchool" id="eduSchool" data-id="${student.eduSchool}">
							<option value="">请选择学校</option>
						</select>
						<p>学段</p>
							<select name="eduStep" id="eduStep">
								<option value="">请选择学段</option>
								<c:forEach items="${steps}" var="steps" >
									<option value="${steps.itemCode}" data-id="${steps.id}" >${steps.itemValue}</option>
								</c:forEach>
							</select>
						<p>入学年份</p>
							<select name="eduYear" id="eduYear">
								<option value="">请选择入学年份</option>
							</select>
						<p>班级</p>
							<select name="eduClass" id="eduClass">
								<option value="">请选择班级</option>
								<option value="1">1班</option>
								<option value="2">2班</option>
								<option value="3">3班</option>
								<option value="4">4班</option>
								<option value="5">5班</option>
								<option value="6">6班</option>
								<option value="7">7班</option>
								<option value="8">8班</option>
								<option value="9">9班</option>
								<option value="10">10班</option>
								<option value="11">11班</option>
								<option value="12">12班</option>
								<option value="13">13班</option>
								<option value="14">14班</option>
								<option value="15">15班</option>
								<option value="16">16班</option>
								<option value="17">17班</option>
								<option value="18">18班</option>
								<option value="19">19班</option>
								<option value="20">20班</option>
								<option value="21">21班</option>
								<option value="22">22班</option>
								<option value="23">23班</option>
								<option value="24">24班</option>
								<option value="25">25班</option>
								<option value="26">26班</option>
								<option value="27">27班</option>
								<option value="28">28班</option>
								<option value="29">29班</option>
								<option value="30">30班</option>
							</select>
					</div>
					<div style="margin-top: 10px;">
						<span>学科</span>
						<select name="subject" id="subject">
							<c:forEach items="${subject}" var="subject" >
								<option value="${subject.itemCode}" data-id="${subject.id}" >${subject.itemName}</option>
							</c:forEach>
						</select>
						<span>课程模块</span>
						<select name="class" id="class">

						</select>
						<span>课次</span>
						<select name="lesson" id="lesson">

						</select>
						<span>观看时间</span>
						<span><input type="text" name="startTime" id="startTime" class="date-picker from" value="${startTime}"/><em>到</em><input type="text" name="endTime" id="endTime" class="date-picker to" value="${endTime}"/></span>
						<input name="userOrMobile" placeholder="学员手机号/用户名查询" />

					</div>
					<div style="...">
						<span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
						<span><a href="javascript:;" class="btn btn-primary searchContents">导出</a></span>

					</div>
				</form>
				<div class="user-list">
					<table class="table table-center" id="tableList">
						<tr data-buy="true">
							<%--<th width="3%"><input type="checkbox" class="checkboxAll"></th>--%>
							<%--<th width="8%">手机号</th>--%>
							<%--<th width="8%">用户名</th>--%>
							<th width="8%">课程名称</th>
							<th width="5%">课次名称</th>
							<th width="8%">用户名</th>
							<th width="12%">学员名称</th>
							<th width="10%">所在班级</th>
							<th width="8%">创建时间</th>
							<th width="6%">观看累计次数</th>
							<th width="6%">观看累计时长</th>
							<%--<th width="10%">操作</th>--%>
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
<script type="text/javascript" src="<%=rootPath %>/javascripts/query/query_student_watchList.js"></script>
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
<script type="text/javascript" src="<%=rootPath%>/javascripts/selectStudentGroup.js"></script>
<script type="text/javascript">
    init();
	$selectSubMenu('statistics_org_detail');
</script>
</body>
</html>