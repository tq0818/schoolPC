<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>点播统计-概况（区县管理员）</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query/statistics.css">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_area.jsp"></jsp:include>
<div class="u-wrap query overflow">
	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query_area.jsp"></jsp:include>
	<div class="right-side set-system gg-biaoge">
		<div class="mainbackground nopadding">
			<div class="heading">
				<h2 class="h5">点播情况</h2>
				<span class="line"></span>
			</div>
		</div>
		<div class="content-right">
			<div class="survey-detail" id="statisticsCon">
				<div class="survey-con">
					<form id="searchForm">
					<p class="screen-info">
						<a href="<%=rootPath%>/query/areastatistics/videoCourseIndex" class="btn active" >概况</a>
						<a href="<%=rootPath%>/query/areastatistics/userVideoList" class="btn">详情</a>
						<span class="date">
							<i class="text">日期</i>
							<span><input type="text" name="startTime" class="date-picker from" value="${startTime}"/><em>至</em><input type="text" name="endTime" class="date-picker to" value="${endTime}"/></span>
							<input type="hidden" id="eduArea" name="eduArea" value="${area.itemCode}">
						</span>
						<button type="button" class="btns-default" id="searchData">查询</button>
						<button type="button" class="btns-default" id="exportData">导出数据</button>
					</p>
					</form>
					<div class="statistics-con">
						<div class="school-demand" id="demandCount2" style="width:49%;height: 240px;float: left;"></div>
						<div class="school-demand" id="demandCount3" style="width:49%;height: 240px;float: left;"></div>
						<div class="demandCount-search screen-info">
							<select name="eduSchoolStep" id="eduSchoolStep">
								<c:forEach items="${stepNews}" var="step" >
									<option value="${step.itemCode}" data-id="${step.id}" >${step.itemValue}</option>
								</c:forEach>
							</select>
							<span class="date" style="margin-left: 0;">
								<i class="text">日期</i>
								<span><input type="text" name="startTime2" class="date-picker from2" value="${startTime}"/><em>至</em><input type="text" name="endTime2" class="date-picker to2" value="${endTime}"/></span>
							</span>
							<button class="btns-default" id="searchData2">查询</button>

							<div class="demand-count" id="demandCount" style="width:100%;height: 380px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts-all.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/byecharts.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript">
	//	$selectSubMenu('statistics_org_detail');
	$selectThirdMenu('videoList');


	$("#searchData").click(function(){
		if ($(".to").val() != "") {
			if ($(".to").val() < $(".from").val()) {
				$.msg("时间范围不正确");
				return;
			}
		}

		searchTotleVideoCourse2($(".from").val(), $(".to").val());//按照学校性质赛选
		searchTotleVideoCourse3($(".from").val(), $(".to").val());//观看点播前五学校
		searchTotleVideoCourse4($(".from").val(), $(".to").val());//观看学科前五学校
	});
	$("#searchData2").click(function(){
		if ($(".to2").val() != "") {
			if ($(".to2").val() < $(".from2").val()) {
				$.msg("时间范围不正确");
				return;
			}
		}

		searchTotleVideoCourse($(".from2").val(), $(".to2").val());//查询所有的学生
	});
	//导出
	$("#exportData").click(function(){
		if ($(".to").val() != "") {
			if ($(".to").val() < $(".from").val()) {
				$.msg("时间范围不正确");
				return;
			}
		}

		$("#searchForm").attr("action",
				rootPath + "/query/exportVideoCourseIndexExcle_area")
				.submit();
	});

	function searchTotleVideoCourse(startTime, endTime){
		var dataKey = new Array(),dataValue = new Array();
		var totleNum = 0;
		$.ajax({
			url: rootPath + "/query/statistics/queryTotleVideoCourseForSchool",
			data:{startTime:startTime, endTime:endTime, eduArea:$("#eduArea").val(), eduSchoolStep:$("#eduSchoolStep").val()},
			success:function(result){
				result = result.schoolVideoList ? result.schoolVideoList:null;
				if(result!=null && result.length>0){
					for(var i=0; i<result.length; i++){
						dataKey.push(result[i].schoolName);
						dataValue.push(result[i].userNum);

						totleNum += result[i].userNum;
					}
				}
				var chart1 = {
					"id":document.getElementById("demandCount"),
					"titleText":'总计观看点播人数' + totleNum,
					"seriesData":dataValue,
					"seriesName":"观看点播人数",
					"barWidth":5,
					"yAxisData":dataKey,
					"grid":{
						x:250
					}
				};

				$(document).statistical().setCharts(chart1);
			}
		});
	}

	function searchTotleVideoCourse2(startTime, endTime){
		var dataCode = ["EDU_STEP_NEW_01","EDU_STEP_NEW_02","EDU_STEP_NEW_03","EDU_STEP_NEW_04","EDU_STEP_NEW_05","EDU_STEP_NEW_06"];
		var dataName = ["小学","完全中学","十二年制","九年制","高级中学","初级中学"];
		var dataKey = new Array(),dataValue = new Array();
		$.ajax({
			url: rootPath + "/query/statistics/queryTotleSchoolStep",
			data:{startTime:startTime, endTime:endTime, eduArea:$("#eduArea").val()},
			success:function(result){
				result = result.schoolStepList ? result.schoolStepList:null;
				if(result!=null && result.length>0){
					for(var i=0; i<dataCode.length; i++){
						var num = 0;
						for(var j=0; j<result.length; j++){
							if(result[j].schoolStepCode!=null && result[j].schoolStepCode!="" && dataCode[i] == result[j].schoolStepCode){
								dataKey.push(dataName[i]);
								dataValue.push(result[j].userNum);

								result.splice(j, 1);
								j--;
								num++;
							}
						}
						if(num == 0){
							dataKey.push(dataName[i]);
							dataValue.push(0);
						}
					}
				}

				var chart2 = {
					"id":document.getElementById("demandCount2"),
					"titleText":'观看点播人数',
					"seriesData":dataValue,
					"seriesName":"观看点播人数",
					"seriesFormatter":'{c}人'

				};
				chart2.legend ={
					show:true,
					data:['观看点播人数'],
					x:'center',
					y:'bottom'
				};
				chart2.xAxis = [
					{
						type : 'category',
						data : dataKey,
						axisLabel:{ interval:0}
					}
				];
				chart2.yAxis = [{type : 'value'}];

				$(document).statistical().setCharts(chart2);
			}
		});
	}

	function searchTotleVideoCourse3(startTime, endTime){
		var dataKey = new Array(),dataValue = new Array();
		$.ajax({
			url: rootPath + "/query/statistics/queryTopSchoolView",
			data:{startTime:startTime, endTime:endTime, eduArea:$("#eduArea").val()},
			success:function(result){
				result = result.schoolViewList ? result.schoolViewList:null;
				if(result!=null && result.length>0){
					for(var i=0; i<result.length; i++){
						if(result[i].schoolCode!=null && result[i].schoolCode!=""){
							dataKey.push(result[i].schoolName);
							dataValue.push(result[i].viewNum);
						}
					}
				}
				var chart3 = {
					"id":document.getElementById("demandCount3"),
					"titleText":'观看点播前五',
					"seriesData":dataValue,
					"seriesName":"观看点播人数",
					"seriesFormatter":'{c}人',
					"grid":{
						x:50,
						x2:30,
						y:50,
						y2:80
					}
				};
				chart3.legend ={
					show:true,
					data:['观看点播人数'],
					x:'center',
					y:'bottom'
				};
				chart3.xAxis = [
					{
						type : 'category',
						data : dataKey,
						axisLabel:{ interval:0,rotate: 15}
					}
				];
				chart3.yAxis = [{type : 'value'}];

				$(document).statistical().setCharts(chart3);
			}
		});
	}

	function setSubjectinfo(id,shcoolList,dataList,yAxisData){
		var chartOpiton = {
			"id":id,
			"grid":{
					x:80,
					x2:50,
					y:5,
					y2:30
			},
			"series":[{
				name: shcoolList[0],
				type: 'bar',
				itemStyle: {
					normal: {
						label : {show: true,formatter: '{a} {c}人',position:'insideRight'},
						color: "#5b9bd5"
					}
				},
				data: [dataList[0]]
			},
				{
					name: shcoolList[1],
					type: 'bar',
					itemStyle: {
						normal: {
							label : {show: true,formatter: '{a} {c}人',position:'insideRight'},
							color: "#ffc000"
						}
					},
					data: [dataList[1]]
				},
				{
					name: shcoolList[2],
					type: 'bar',
					itemStyle: {
						normal: {
							label : {show: true,formatter: '{a} {c}人',position:'insideRight'},
							color: "#a5a5a5"
						}
					},
					data: [dataList[2]]
				},
				{
					name: shcoolList[3],
					type: 'bar',
					itemStyle: {
						normal: {
							label : {show: true,formatter: '{a} {c}人',position:'insideRight'},
							color: "#ed7d31"
						}
					},
					data: [dataList[3]]
				},
				{
					name: shcoolList[4],
					type: 'bar',
					itemStyle: {
						normal: {
							label : {show: true,formatter: '{a} {c}人',position:'insideRight'},
							color: "#4472c4"
						}
					},
					data:  [dataList[4]]
				}
			],
			"seriesName":"观看点播人数",
			"yAxisData":[yAxisData]

		};
		return chartOpiton
	}

	function searchTotleVideoCourse4(startTime, endTime){
		$.ajax({
			url: rootPath + "/query/statistics/queryTopSubjectView",
			data:{startTime:startTime, endTime:endTime,eduArea:$("#eduArea").val()},
			success:function(result){
				result = result.subjectTotleList ? result.subjectTotleList:null;
				if(result!=null && result.length>0){
					$(".statistics-con").children().remove(".subjectinfo");
					for(var j=0; j<result.length; j++){
						for(var k in result[j]){
							var dataKey = new Array(),dataValue = new Array();
							for(var i=0; i<result[j][k].length; i++){
								dataKey.push(result[j][k][i].schoolName);
								dataValue.push(result[j][k][i].viewNum);
							}
							//$(".statistics-con").append('<div class="demand-count subjectinfo" style="width:100%;height: 120px;"></div>');
							$('<div class="demand-count subjectinfo" style="width:100%;height: 160px;"></div>').insertBefore($(".demandCount-search"));

							var chart4 = setSubjectinfo($(".statistics-con").children(":last").prev()[0],dataKey,dataValue,k);
							$(document).statistical().setCharts(chart4);
						}
					}
				}
			}
		});
	}
	searchTotleVideoCourse($(".from").val(), $(".to").val());//查询所有的学生
	searchTotleVideoCourse2($(".from").val(), $(".to").val());//按照学校性质赛选
	searchTotleVideoCourse3($(".from").val(), $(".to").val());//观看点播前五学校
	searchTotleVideoCourse4($(".from").val(), $(".to").val());//观看学科前五学校


</script>
</body>
</html>