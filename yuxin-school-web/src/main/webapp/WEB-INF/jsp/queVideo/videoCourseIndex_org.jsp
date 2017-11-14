<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>点播统计-概况（校级管理员）</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query/statistics.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_org.jsp"></jsp:include>
<div class="u-wrap query overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query_org.jsp"></jsp:include>
	<div class="right-side set-system gg-biaoge">
		<div class="mainbackground nopadding">
			<div class="heading">
				<h2 class="h5">点播情况</h2>
				<span class="line"></span>
			</div>
		</div>
		<div class="content-right">
			<p class="screen-info">
				<a href="<%=rootPath%>/query/orgstatistics/videoCourseIndex" class="btn active" >概况</a>
				<a href="<%=rootPath%>/query/orgstatistics/userVideoList" class="btn">详情</a>
				<span class="date">
					<i class="text">日期</i>
					<span><input type="text" name="startTime" class="date-picker from" value="${startTime}"/><em>到</em><input type="text" name="endTime" class="date-picker to" value="${endTime}"/></span>

					<input type="hidden" id="eduArea" name="eduArea" value="${area.itemCode}">
					<input type="hidden" id="eduSchool" name="eduSchool" value="${org.itemCode}">
				</span>
				<button class="btns-default searchContents">查询</button>
			</p>
			<ul class="playcount-info">
				<li>
					<img class="pull-left" src="<%=rootPath%>/images/query/ico-play1.png" alt="">
					<span class="pull-right tit">总计观看点播人数</span>
					<span class="pull-right msg" id="userNum"></span>
				</li>
				<li>
					<img class="pull-left" src="<%=rootPath%>/images/query/ico-play2.png" alt="">
					<span class="pull-right tit">观看总时长</span>
                    <span class="pull-right msg" id="totleStudyLength"></span>
				</li>
				<li>
					<img class="pull-left" src="<%=rootPath%>/images/query/ico-play3.png" alt="">
					<span class="pull-right tit">观看总播放量</span>
                    <span class="pull-right msg" id="personNum"></span>
				</li>
			</ul>
			<div class="user-list">
				<table class="table table-center" id="tableList">
					<tr data-buy="true">
						<th width="16%">课程学段</th>
						<th width="16%">学科</th>
						<th width="16%" class="btn-sort" fieldName="totle_study" sort="">总播放量</th>
						<th width="16%" class="btn-sort" fieldName="totle_study_length" sort="">总播放时长</th>
						<th width="16%" class="btn-sort" fieldName="study_rate" sort="">播完率</th>
						<th width="16%" class="btn-sort" fieldName="view_num" sort="">观看次数</th>
					</tr>
					<c:choose>
						<c:when test="${userorg_roleopenflag==1 && proxyOrgRole ==1 }">
							<tr><td colspan="7">暂无数据</td></tr>
						</c:when>
						<c:otherwise>
							<tr><td colspan="6">暂无数据</td></tr>
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
<script type="text/javascript" src="<%=rootPath %>/javascripts/query/video/video_course_index_org.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts-all.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/byecharts.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript">
	$selectThirdMenu('videoList');

	$.tableSort($(".btn-sort"),{
		callback:function(data){
			console.log(data);
			student.search(1,data);
		}
	});
</script>
</body>
</html>