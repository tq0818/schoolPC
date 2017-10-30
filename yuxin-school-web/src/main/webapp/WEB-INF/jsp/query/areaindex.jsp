<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>区域统计总览</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/homepage.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/query/statistical-style.css">
	<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_statistics_area.jsp"%>
<div class="statistical-box">
	<span class="scount fl">· 成都数字学校<i class="c_red">${areaDict.itemValue}注册人数总计</i>：<b>${allStuNum}</b>人</span>
	<span class="scount fr">· 成都数字学校<i class="c_red">${areaDict.itemValue}教育信息完善人数总计</i>：<b>${completeStuNum}</b>人</span>
</div>
<div class="q-box charts-box">
	<div class="gg-biaoge clear stuAndOrder">

		<div class="biaoge-coment biaoge-left fl wit50">
			<div class="biaoge-cont">
				<div class="block-title">
					<h3><i class="c_red">${areaDict.itemValue}</i>学员趋势图</h3>
					<span class="more"><a href="<%=rootPath %>/query/areastatistics/studentList" style="color: #00b7ee; text-decoration:none;">更多</a></span>
			</div>
				<div class="e-charst" id="stu-qushi"></div>
			</div>

		</div>
		<div class="biaoge-coment biaoge-left fr wit50">
			<div class="biaoge-cont">
				<div class="block-title">
					<h3>区（市）县、市直属机构完善教育信息的情况</h3>
					<span class="more"><a href="<%=rootPath %>/query/areastatistics/queryOrg" style="color: #00b7ee; text-decoration:none;">更多</a></span>
				</div>
				<div class="e-charst" id="perfect-qushi"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src='<%=rootPath %>/javascripts/plus/echarts/echarts.common.min.js'></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/home-page.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/query/statistical.js"></script>
<script type="text/javascript">
		$(document).statistical({}).queryPerfect();
</script>
</body>
</html>

