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
						<input type="text" id="stuName" name="name" placeholder="姓名"/>
						<input type="hidden" id="sfzh" name="identityId" placeholder="证件号码"/>
						<input type="hidden" id="isStu" name="isStu" value="1"/>
						<input type="hidden" id="sourceFromStatic" value='1'/>		
						<select name="eduArea" id="eduArea">
							<option value="">请选择区域</option>
							<c:forEach items="${areas}" var="area" >
								<option value="${area.itemCode}" data-id="${area.id}" ${student.eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
							</c:forEach>
						</select>

						<select name="eduSchool" id="eduSchool" data-id="${student.eduSchool}">
							<option value="">请选择学校</option>
						</select>
					</div>
					<div style="margin-top: 10px;">
						<span>创建时间</span>
						<span><input type="text" name="startTime" class="date-picker from"/><em>到</em><input type="text" name="endTime" class="date-picker to"/></span>
						<c:if test="${address==1}">
						<span style="padding:0 15px;" id="caddress">
							<select id="prov" name="province"></select>
							<select id="city" name="city"></select>
							<select id="dist" name="county"></select>
						</span>
						</c:if>
						<span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
					</div>
				</form>
				<div class="user-list">
					<table class="table table-center" id="tableList">
						<tr data-buy="true">
							<%--<th width="3%"><input type="checkbox" class="checkboxAll"></th>--%>
							<%--<th width="8%">手机号</th>--%>
							<%--<th width="8%">用户名</th>--%>
							<th width="8%">姓名</th>
							<th width="5%">身份</th>
							<th width="8%">区域</th>
							<th width="12%">学校</th>
							<th width="10%">所在班级</th>
							<c:if test="${userorg_roleopenflag==1 }">
								<shiro:hasAnyRoles name="机构管理员,代理机构">
									<th width="10%">所属代理机构</th>
								</shiro:hasAnyRoles>
							</c:if>
							<th width="8%">创建时间</th>
							<th width="6%">前台登录账号</th>
							<th width="6%">前台账号状态</th>
							<th width="5%">报名状态</th>
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
<script type="text/javascript" src="<%=rootPath %>/javascripts/query/query_student.js"></script>
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
	$selectSubMenu('statistics_org_detail');
</script>
</body>
</html>