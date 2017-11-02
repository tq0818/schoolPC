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
					<h2 class="h5">直播情况</h2>
					<span class="line"></span>
				</div>
				<div>
					<%--<input type="text" id="stuMobile" name="mobile" placeholder="手机号" maxlength="11"/>--%>
					<%--<input type="text" id="stuusername" name="username" placeholder="用户名"/>--%>
					<input type="text" id="startDate" name="startDate" value="${startDate}" placeholder="开始时间"/>
					<input type="text" id="endDate" name="endDate" value="${endDate}" placeholder="结束时间"/>
					<input type="button" id="search" value="查询" onclick="queryChartData();"/>
				</div>

				<div style="text-align: center;font-size: 16px;width: 46%;display: none;">总计观看人数<span id="watchIndex" style="color:red"></span></div>
				<div class="e-charst" id="watch_info_index"></div>
				<div style="text-align: center;font-size: 16px;width: 46%;display: none;">总计观看人次<span id="watchAll" style="color:red"></span></div>
				<div class="e-charst" id="watch_info_all"></div>
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
		<script type="text/javascript" src="<%=rootPath %>/javascripts/common/DateUtils.js"></script>
		<script type="text/javascript" src="<%=rootPath %>/javascripts/home-page.js"></script>
		<script type="text/javascript" src="<%=rootPath %>/javascripts/query/statistical_watchInfo.js"></script>
		<script type="text/javascript">

            function queryChartData(){
                $.ajax({
                    url:rootPath+'/query/statistics/watchInfoTotal',
                    data:{'startDate':$("#startDate").val(),'endDate':$("#endDate").val()},
                    dataType:'json',
                    type:'post',
                    success:function(result){
                        if(result!=null && result.all!=null){
                            $("#watchAll").html(result.all).parent().css({"display":'inline-block'});
                        }
                        if(result!=null && result.index!=null){
                            $("#watchIndex").html(result.index).parent().css({"display":'inline-block'});
                        }
                    }
                });



                $(document).statistical({}).queryPerfect();
                $(document).statistical({}).queryPerfect2();
			}

            queryChartData();
		</script>
</body>
</html>