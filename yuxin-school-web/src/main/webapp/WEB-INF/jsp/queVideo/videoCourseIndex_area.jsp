<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>点播统计-概况（区县管理员）</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query/statistics.css">
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/echarts/echarts-all.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/byecharts.js"></script>
</head>
<body>
<div class="content-right">
	<p class="screen-info">
		<a href="" class="btn btn-survey">概况</a>
		<a href="" class="btn btn-detail">详情</a>
		<span class="date">
			<i class="text">日期</i>
			<input type="text" class="i-data" value="2017-09-21">
			<i class="t">至</i>
			<input type="text" class="i-data" value="2017-10-21">
		</span>
		<button class="btns-default">查询</button>
		<button class="btns-default">导出数据</button>
	</p>
	<div class="statistics-con">
		<div class="demand-count" id="demandCount" style="width:100%;height: 380px;"></div>
		<div class="school-demand" id="demandCount2" style="width:49%;height: 240px;float: left;"></div>
		<div class="school-demand" id="demandCount3" style="width:49%;height: 240px;float: right;"></div>
		<p class="screen-info">
			<select class="select-box" id="">
				<option value="">请选择学校性质</option>
			</select>
			<span class="date" style="margin-left: 0;">
				<i class="text">日期</i>
				<input type="text" class="i-data" value="2017-09-21">
				<i class="t">至</i>
				<input type="text" class="i-data" value="2017-10-21">
			</span>
			<button class="btns-default">查询</button>
		</p>
		<div class="demand-count" id="demandCount4" style="width:100%;height: 340px;"></div>
	</div>
</div>
	
</body>
</html>
<script type="text/javascript">
	var chart1 = {
		"id":document.getElementById("demandCount4"),
		"titleText":'总计观看点播人数10000',
		"seriesData":["12","10","5","30","15","67","88","92","18","42","67","72","85","40","39","26","5","30","55","16","72","14"],
		"seriesName":"观看点播人数",
		"barWidth":5,
		"yAxisData":["高新区","锦江区","青羊区","金牛区","武侯区","成华区","龙泉驿区","青白江区","新都区","温江区","双流区","郫都区","金堂县","大邑县","蒲江县","新津县","天府新区","都江堰市","彭州市","邛崃市","崇州市","简阳市"]

	};			
	
	var chart2 = {
		"id":document.getElementById("demandCount"),
		"titleText":'总计观看点播人数(学校性质)',
		"seriesData":[12,15,5,24,41,31],
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
            data : ['小学', '民营小学', '十二年制', '九年制', '高级中学', '初级中学'],
            axisLabel:{ interval:0},
        }
    ],
    chart2.yAxis = [{type : 'value'}];
var chart3 = {
		"id":document.getElementById("demandCount2"),
		"titleText":'总计观看点播人数',
		"seriesData":[25,28,76,55,30],
		"seriesName":"观看点播人数",
		"seriesFormatter":'{c}人'
		
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
            data : ['成都七中', '石室中学', '西式中学', '普通中学', '棕北中学'],
            axisLabel:{ interval:0},
        }
    ],
    chart3.yAxis = [{type : 'value'}]; 
$(document).statistical().setCharts(chart1);
$(document).statistical().setCharts(chart2);
$(document).statistical().setCharts(chart3);
var chart4 = {
		"id":document.getElementById("demandCount3"),
		"titleText":'学科统计观看点播人数前五',
		"seriesList":[{
			            name: '盐道街中学1',
			            type: 'bar',
			            itemStyle: { 
								normal: { 
									label : {show: true,formatter: '{a} {c}人'},
									color: "#5b9bd5",

								}
						},
			            data: [12, 18, 42]
			        },
			        {
			            name: '盐道街中学2',
			            type: 'bar',
			            itemStyle: { 
								normal: { 
									label : {show: true,formatter: '{a} {c}人'},
									color: "#ffc000"
								}
						},
			            data: [33, 60, 19]
			        },
			        {
			            name: '盐道街中学3',
			            type: 'bar',
			            itemStyle: { 
								normal: { 
									label : {show: true,formatter: '{a} {c}人'},
									color: "#a5a5a5"
								}
						},
			            data: [66, 55, 39]
			        },
			        {
			            name: '盐道街中学4',
			            type: 'bar',
			            itemStyle: { 
								normal: { 
									label : {show: true,formatter: '{a} {c}人'},
									color: "#ed7d31"
								}
						},
			            data: [51, 71, 65]
			        }, {
			            name: '盐道街中学5',
			            type: 'bar',
			            itemStyle: { 
								normal: { 
									label : {show: true,formatter: '{a} {c}人'},
									color: "#4472c4"
								}
						},
			            data: [82, 76, 35]
			        }
			        ],
		"seriesName":"观看点播人数",
		"yAxisData":["语文","数学","英语"]

	};	
$(document).statistical().setCharts(chart4);



</script>