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
	<link rel="stylesheet" href="<%=rootPath %>/stylesheets/query/statistics.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
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
					<h2 class="h5">直播并发</h2>
					<span class="line"></span>
				</div>
				<div class="content-right">
					<form method="post" id="searchForm" class="screen-info">
						<div>
						<%--<input type="text" id="stuMobile" name="mobile" placeholder="手机号" maxlength="11"/>--%>
						<%--<input type="text" id="stuusername" name="username" placeholder="用户名"/>--%>
							<span>时间</span>
							<span><input type="text" name="startTime" id="startTime" class="date-picker from" value="${startTime}"/><em>到</em><input type="text" name="endTime" id="endTime" class="date-picker to" value="${endTime}"/></span>
							<span class="dayList">
								<button type="button" class="daysTab" value="-7">7天</button>
								<button type="button" class="daysTab" value="-14">14天</button>
								<button type="button" class="daysTab" value="-31">31天</button>
							</span>
							
						</div>
						<div class="margin10">
							<span><i class="iconfont ico" style="color: red;"></i>学段</span>
							<select name="secondItemCode" id="secondItemCode">
								<option value="">请选择学段</option>
								<c:forEach items="${secondItem}" var="secondItem" >
									<option value="${secondItem.itemCode}"  >${secondItem.itemName}</option>
								</c:forEach>
							</select>
							<span><i class="iconfont ico" style="color: red;"></i>学科</span>
							<select name="itemThirdCode" id="itemThirdCode">
								<option value="">请选择学科</option>
							</select>
							<span><i class="iconfont ico" style="color: red;"></i>课程模块</span>
							<select name="comId" id="class">
								<option value="">请选择课程模块</option>
							</select>
							<span><i class="iconfont ico" style="color: red;"></i>课次</span>
							<select name="lesson" id="lesson">
								<option value="">全部</option>
							</select>
						</div>
						<div class="btn-center margin10">
							<button class="btns-default" id="search" onclick="searchbtn();" type="button">查询</button>
							<button class="btns-default exportExcleStudent" id="search" type="button">导出数据</button>

						<%--	<span><a  class="btn btn-primary searchContents" onclick="searchbtn()">搜索</a></span>
							<span><a href="javascript:;" class="btn btn-primary exportExcleStudent">导出</a></span>--%>

						</div>
					</form>
					<div class="user-list">
						<table class="table table-center" id="tableList">
							<tr data-buy="true">
								<th width="10%">课程名称</th>
								<th width="10%">课次名称</th>
								<th width="8%" class="btn-sort" fieldName="times" sort="">观看人次</th>
								<th width="8%" class="btn-sort" fieldName="max_concurrent" sort="">最大并发</th>
								<th width="8%">用户学习方式详情</th>
							</tr>
							<%--<tr>
								<td>name</td>
								<td>name123</td>
								<td>52</td>
								<td class="max-imum">66</td>
								<td class="learning-style">detail</td>
							</tr>--%>
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
	</div>
	<!-- popupwin 最大并发 开始    -->
	<div class="popupwin-box max-imumbox1 clear" style="display:none">
		<div class="popupwin max-imumbox" style="width:1000px; height: auto;top:10px;" data-pupwin="modal">
			<div class="popupwin-title">
					<h2 class="h5">最大并发</h2>
					<i class="close iconfont canclekuang"></i>
				</div>
			<div class="popupwin-main">
				<div class="demand-count viewsCount" id="maxImum" style="width:960px;height: 380px;"></div>
			</div>
		</div>
	</div>
	<!-- popupwin 最大并发结束 -->
<!-- popupwin 学习方式详细 开始    -->
<div class="popupwin-box learning-stylebox1 clear" style="display:none">
	<div class="popupwin learning-stylebox" style="width:400px; height: auto;top:10px;" data-pupwin="modal">
		<div class="popupwin-title">
			<h2 class="h5">用户学习方式详情</h2>
			<i class="close iconfont canclekuang"></i>
		</div>
		<div class="popupwin-main">
			<div class="demand-count viewsCount" id="learningStyle" style="width:360px;height: 160px;"></div>
		</div>
	</div>
</div>
<!-- popupwin 学习方式详细结束 -->
<input type="hidden" id="selectCounts" value="10">
<script type="text/javascript" src="<%=rootPath %>/javascripts/query/query_student_watchCrrentCount.js"></script>
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
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts-all.js"></script>
<script type="text/javascript">
    init();

	$selectThirdMenu('watchInfoList');
	$selectThirdMenu('watchInfoCurrentCoun');
	function searchbtn(){
		//清除之前字段排序的值
		$(".table .btn-sort").attr("sort","");
		$(".table .sortTarget").removeClass("sortTarget");
       search(1);
	}
    $.tableSort($(".btn-sort"),{
        callback:function(data){
            search(1,data);
        }
    });
</script>
</body>
</html>