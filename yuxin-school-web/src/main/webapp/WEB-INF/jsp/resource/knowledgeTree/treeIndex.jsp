<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>知识树</title>
	<link rel="stylesheet" href="<%=rootPath%>/plugins/jquery-pagination/css/pagination.css">
	<link rel="stylesheet" href="<%=rootPath%>/stylesheets/knowledgetree/index.css">
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/knowledgetree/index.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
<div class="u-wrap resource">
	<div class="mainbackground nopadding">
		<div class="cont">
			<!--form表单-->
			<form name="search" id="search">
				<label>选择年份
					<select name="eduYear" id="eduYear">
						<c:forEach begin="2017" end="2019" varStatus="index">
							<option value="${index}">${index}年</option>
						</c:forEach>
					</select>
				</label>
				<label>选择期数
					<select name="eduSeason" id="eduSeason">
						<option value="spring">春季</option>
						<option value="summer">暑假</option>
						<option value="autumn">秋季</option>
						<option value="winter">寒假</option>
					</select>
				</label>
				<label>选择学段
					<select name="eduStep" id="eduStep">
						<c:forEach items="${eduSteps}" var="step">
							<option value="${step.itemCode}">${step.itemValue}</option>
						</c:forEach>
					</select>
					<select name="itemSecondCode" id="itemSecondCode">
					</select>
				</label>

				<label>选择学科
					<select name="itemThreeCode" id="itemThreeCode">
					</select>
				</label>
				<button class="btn_defalut" type="button">生成知识树</button>
				<button class="btn_defalut" type="button">导入</button>
				<button class="btn_defalut" type="button">导出</button>
			</form>
			<div class="course-list">
				<table class="table table-center" id="tableList">
					<tr data-buy="true">
						<th width="3%"><input type="checkbox" class="checkboxAll"></th>
						<th width="8%">课程名称</th>
						<th width="8%">操作</th>
					</tr>
					<tr><td colspan="3">暂无数据</td></tr>


				</table>
				<div class="pages pagination"></div>
			</div>
		</div>
	</div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript" src="<%=rootPath %>/javascripts/knowledgeTree/index.js"></script>
<script type="text/javascript">
	$selectSubMenu('resource_tree');
</script>
</body>
</html>