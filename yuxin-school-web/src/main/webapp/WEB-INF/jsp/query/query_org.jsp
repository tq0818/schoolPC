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
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/homepage.css">
<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/query/statistical-style.css">
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
		<div class="right-side set-system gg-biaoge">
			<div class="mainbackground nopadding">
				<div class="heading">
					<h2 class="h5">学校教育信息完善情况</h2>
					<span class="line"></span>
				</div>
			</div>
			<div class="query-statistical">
				<div class="query-box">
					<select name="eduArea" id="eduArea">
						<option value="">请选择区域</option>
						<c:forEach items="${areas}" var="area" >
							<option value="${area.itemCode}" data-id="${area.id}" >${area.itemValue}</option>
						</c:forEach>
					</select>

					<select name="eduStep" id="eduStep">
						<option value="">请选择学段</option>
						<c:forEach items="${stepNews}" var="step" >
							<option value="${step.itemCode}" data-id="${step.id}" >${step.itemValue}</option>
						</c:forEach>
					</select>
					<span><a href="javascript:;" class="btn btn-primary" onclick="queryPerfect2()">搜索</a></span>

					<div style="text-align: center;font-size: 16px;width: 46%;display: none;">总计<span id="studentTotal" style="color:red"></span>人已完善教育信息</div>
				</div>
				<div class="biaoge-coment biaoge-left wit50">
					<div class="biaoge-cont">
						<div class="e-charst" id="perfect-qushi"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
	<script type="text/javascript" src='<%=rootPath %>/javascripts/plus/echarts/echarts.common.min.js'></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/query/statistical.js"></script>
	<script type="text/javascript">
/*		function $selectThirdMenu(code) {
			$(".system_managelist").find("li").removeClass("active");
			$(".system_managelist").find("li").each(function() {
				if ($(this).attr("code") == code) {
					$(this).addClass("active");
				}
			})
		}*/
		$selectThirdMenu('orgStuList');
		function queryPerfect2(){
			var eduArea = $('#eduArea').val();
			var eduStep = $('#eduStep').val();

			if(eduArea==null || eduArea=='' || eduStep==null || eduStep==''){
				alert("请选择区域和学段！");
				return;
			}

			$.ajax({
				url:rootPath+'/query/statistics/orgStudentTotalStatistics',
				data:{'eduArea':eduArea, 'eduStep':eduStep},
				dataType:'json',
				type:'post',
				success:function(result){
					if(result!=null && result.stuNum!=null){
						$("#studentTotal").html(result.stuNum).parent().css({"display":'inline-block'});
					}
				}
			});

			$(document).statistical({}).queryPerfect2(eduArea, eduStep);
		}
	</script>
</body>
</html>