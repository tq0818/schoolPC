<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>知识树</title>
	<link rel="stylesheet" href="<%=rootPath%>/stylesheets/system/knowledgeIndex.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/message.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/system/knowledgeIndex.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_resource.jsp"%>
<input type="hidden" id="selectCounts" value="10">
<div class="u-wrap resource">
	<div class="mainbackground nopadding">
		<!--form表单-->
		<form name="search" id="search">
			<label>选择年份
				<select name="eduYear" id="eduYear">
					<c:forEach begin="2017" end="2019" varStatus="index">
						<option value="${index.index}">${index.index}年</option>
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
				<select name="eduStep" id="eduStep" class="tbxSelect">
					<c:forEach items="${eduSteps}" var="step">
						<option value="${step.itemCode}">${step.itemValue}</option>
					</c:forEach>
				</select>
				<select name="itemSecondCode" id="itemSecondCode" class="tbxSelect">
				</select>
			</label>

			<label>选择学科
				<select name="itemThreeCode" id="itemThreeCode">
				</select>
			</label>
			<div class="btnArray">
				<div class="btnArray_top">
					<button class="btn_defalut btn_blue searchContents" type="button">查询</button>
					<button class="btn_defalut removeKnowledge" type="button">清空知识树</button>
				</div>
				<div class="btnArray_top">
					<button class="btn_defalut btn_blue viewKnowledge" type="button">预览</button>
					<button class="btn_defalut buildKnowledge" type="button">生成知识树</button>
				</div>
			</div>

		</form>

		<div class="tree_setting">
			知识树设置
		</div>
		<div class="course-list">
				<table class="table table-center" id="tableList">
					<tr data-buy="true">
						<th width="3%"><input type="checkbox" class="checkboxAll"></th>
						<th width="18%">课程名称</th>
						<th width="3%">操作</th>
					</tr>
					<tr><td colspan="3">暂无数据</td></tr>
				</table>
				<div class="pages pagination"></div>
			</div>
	</div>
	<!-- popupwin 界面 开始-->
	<div class="popupwin knowledgePopup" style="width:600px; height:auto;top:10px;display: none;" data-pupwin="modal">
		<div class="popupwin-title">
			<h2 class="h5">预览知识树</h2>
			<i class="close iconfont"></i>
		</div>
		<div class="main form-horizontal" id="lsOne">
			<div class="knowledge-list">
				<table class="table table-center">
					<tr data-buy="true">
						<th width="3%">编号</th>
						<th width="10%">课程名称</th>
						<th width="10%">课次名称</th>
					</tr>
					<tr><td colspan="3">暂无数据</td></tr>
				</table>
			</div>
		</div>
	</div>
	<!-- popupwin 界面结束 -->
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script type="text/javascript">
	$selectSubMenu('resource_tree');
</script>
</body>
</html>