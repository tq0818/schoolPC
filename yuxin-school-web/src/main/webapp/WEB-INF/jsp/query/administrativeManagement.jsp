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
<title>学校行政管理</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
	<style type="text/css">
	.pages li.disabled{padding:0px;}
   	.tips{
   		color:red;
   	}
	.u-wrap .mainbackground{height: 800px;}
	table .editDisable{text-align: center;background: #f6f6f6 !important;border: none !important;-webkit-appearance:none;}
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
					<h2 class="h5">学校行政管理</h2>
					<span class="line"></span>
				</div>
				<form method="post" id="searchForm">
					<div>
						<input type="text" placeholder="分校名称/组织机构代码" style="margin-right: 5px;">
						<select name="eduArea" id="eduArea">
							<option value="">请选择区域</option>
							<c:forEach items="${areas}" var="area" >
								<option value="${area.itemCode}" data-id="${area.id}" ${student.eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
							</c:forEach>
						</select>

						<select >
							<option value="">请选择学校性质</option>
						</select>
						<a href="##" class="btn btn-primary" style="margin-left: 5px;margin-bottom: 10px;">搜索</a>
						<a href="##" class="btn btn-primary" style="margin-left: 5px;float: right;">新增学校</a>
					</div>
				</form>
				<div class="user-list">
					<table class="table table-center" id="tableList">
						<tr data-buy="true">
							<th width="10%">序号</th>
							<th width="30%">学校名称</th>
							<th width="15%">组织机构代码</th>
							<th width="20%">区域</th>
							<th width="10%">学校性质</th>
							<th width="30%">操作</th>
						</tr>
						<tr>
							<td>1</td>
							<td><input type="text" value="成都市三原外国语学校" disabled class="editDisable"></td>
							<td><input type="text" value="19920129" disabled class="editDisable"></td>
							<td>
								<select name="" id="" disabled class="editDisable">
									<option value="">新都区</option>
									<option value="">新都区</option>
									<option value="">新都区</option>
								</select>
							</td>
							<td>
								<select name="" id="" disabled class="editDisable">
									<option value="">完全中学</option>
									<option value="">完全中学</option>
									<option value="">完全中学</option>
								</select>
							</td>
							<td>
								<a href="##" class="modify">修改</a>/
								<a href="##">账号设置</a>
							</td>
						</tr>
						<%--<c:choose>--%>
							<%--<c:when test="${userorg_roleopenflag==1 && proxyOrgRole ==1 }">--%>
								<%--<tr><td colspan="15">暂无数据</td></tr>--%>
							<%--</c:when>--%>
							<%--<c:otherwise>--%>
								<%--<tr><td colspan="14">暂无数据</td></tr>--%>
							<%--</c:otherwise>--%>
						<%--</c:choose>--%>


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

		<%--新增学校弹窗--%>
		<div>
			<h2>添加分校</h2>
			<ul>
				<li>
					<label for="">组织结构代码：</label>
					<input type="text">
				</li>
				<li>
					<label for="">学校名称：</label>
					<input type="text">
				</li>
				<li>
					<label for="">区域：</label>
					<select name="" id="">
						<option value="">请选择区域</option>
						<option value="">1</option>
					</select>
				</li>
				<li>
					<label for="">性质：</label>
					<select name="" id="">
						<option value="">请选择学校性质</option>
						<option value="">2</option>
					</select>
				</li>
			</ul>
		</div>

	</div>


<%--<script type="text/javascript" src="<%=rootPath %>/javascripts/query/query_student.js"></script>--%>
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
	//点击修改
	$('.modify').click(function(){
	    $(this).parent('td').siblings('td').children().removeClass('editDisable').attr('disabled',false);
	});


	$selectSubMenu('AdministrativeManagement');
</script>
</body>
</html>