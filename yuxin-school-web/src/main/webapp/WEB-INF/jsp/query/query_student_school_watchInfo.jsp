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
<title>直播情况</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
<link rel="stylesheet" href="<%=rootPath %>/stylesheets/query/statistics.css">
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
	 	<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query_org.jsp"></jsp:include>
		<div class="right-side set-system">
			<div class="mainbackground nopadding">
				<div class="heading">
					<h2 class="h5">直播情况</h2>
					<span class="line"></span>
				</div>
				<div class="content-right">
					<p class="screen-info" style="margin-bottom: 20px;">
						<a href="/query/statistics/watchInfoList" class="btn active">概况</a>
						<a href="/query/statistics/studentWatchInfoList" class="btn">详情</a>
						<span class="date">
							<i class="text">日期</i>
							<span><input type="text" id="startDate" name="startTime" class="date-picker from" value="${startDate}" placeholder="开始时间"><em>到</em>
								<input type="text" id="endDate" name="endTime" class="date-picker to" value="${endDate}" placeholder="结束时间"></span>
						</span>
						<button class="btns-default" id="search" onclick="queryChartData();">查询</button>
					</p>
					<ul class="playcount-info">
						<li>
							<img class="pull-left" src="<%=rootPath %>/images/query/ico-play1.png" alt="">
							<span class="pull-right tit">总计观看直播人数</span>
							<span class="pull-right msg" id="watchNum">500</span>
						</li>
						<li>
							<img class="pull-left" src="<%=rootPath %>/images/query/ico-play2.png" alt="">
							<span class="pull-right tit">总计观看直播时长</span>
							<span class="pull-right msg" id="totalTime">1034小时32分10秒</span>
						</li>
						<li>
							<img class="pull-left" src="<%=rootPath %>/images/query/ico-play3.png" alt="">
							<span class="pull-right tit">总计观看直播人次</span>
							<span class="pull-right msg" id="watchAll">1210</span>
						</li>
					</ul>
					<div class="statistics-con">
						<div class="demand-count" id="demandCount" style="width:100%;height: 380px;"></div>
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
		<script type="text/javascript" src='<%=rootPath %>/javascripts/plus/echarts/echarts.common.min.js'></script>
		<script type="text/javascript" src="<%=rootPath %>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="<%=rootPath %>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
		<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
		<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/byecharts.js"></script>
		<script type="text/javascript">

            $selectThirdMenu('watchSchoolInfoList');
            function queryChartData() {
                $.ajax({
                    url: rootPath + '/query/statistics/watchSchoolInfoIndex',
                    data: {
                        'startDate': $("#startDate").val(),
                        'endDate': $("#endDate").val()
                    },
                    dataType: 'json',
                    type: 'post',
                    success: function (result) {

						if(result!=null){

                            var  year =[];
                            var  newResult=[];
                            var  newTotal =[];
                            $.each(result.year,function(i,n){
                                year[i] = (n.edu_year+"级");
							});
                            $.each(result.result,function(m,l){
                                    newResult[m]=(l.times);
                            });
                            $.each(result.total,function(x,y){
                                    newTotal[x]=(y.times);
                            });

                            $("#watchNum").html(result.watchNum);
                            $("#totalTime").html(result.totalTime);
                            $("#watchAll").html(result.watchAll);


						}
                        var chartOpiton = {
                            "id": document.getElementById("demandCount"),
                            "legend": {
                                show: true,
                                selectedMode: false,
                                y: 20,
                                data: ["报名人数", "实际观看人数"]
                            },
                            "series": [
                                {
                                    name: '实际观看人数',
                                    type: 'bar',
                                    itemStyle: {
                                        normal: {
                                            color: "#5b9bd5"
                                        }
                                    },
                                    data: newResult
                                }, {
                                    name: "报名人数",
                                    type: 'bar',
                                    itemStyle: {
                                        normal: {
                                            color: "#ed7d31",
                                        }
                                    },
                                    data: newTotal
                                }
                            ],
                            "seriesName": "观看点播前五",
                            "yAxisData": year

                        };

                        $(document).statistical().setCharts(chartOpiton);





                    }
                });
            }

                // $(document).statistical({}).queryPerfect();
                //  $(document).statistical({}).queryPerfect2();
//			}

                 queryChartData();
                var chartOpiton = {
                    "id": document.getElementById("demandCount"),
                    "legend": {
                        show: true,
                        selectedMode: false,
                        y: 20,
                        data: ["报名人数", "实际观看人数"]
                    },
                    "series": [
                        {
                            name: '实际观看人数',
                            type: 'bar',
                            itemStyle: {
                                normal: {
                                    color: "#5b9bd5"
                                }
                            },
                            data: [160, 172, 160, 98, 102, 30]
                        }, {
                            name: "报名人数",
                            type: 'bar',
                            itemStyle: {
                                normal: {
                                    color: "#ed7d31",
                                }
                            },
                            data: [200, 200, 200, 200, 200, 200]
                        }
                    ],
                    "seriesName": "观看点播前五",
                    "yAxisData": ['2012级', '2013级', '2014级', '2015级', '2016级', '2017级']

                };

                $(document).statistical().setCharts(chartOpiton);

		</script>
</body>
</html>