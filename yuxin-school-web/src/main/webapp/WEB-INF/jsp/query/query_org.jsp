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
				<div class="biaoge-coment biaoge-left wit50" id="student-qushi">
					<div class="biaoge-cont">
						<div class="e-charst" id="perfect-qushi"></div>
					</div>
				</div>
				<div class="user-list">
					<table class="table table-center" id="tableList">
						<tr data-buy="true">
							<th width="8%">区域</th>
							<th width="8%">注册总人数</th>
							<th width="8%">小学人数</th>
							<th width="6%">初中人数</th>
							<th width="6%">高中人数</th>
						</tr>
						<tr><td colspan="14">暂无数据</td></tr>

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

			if((eduArea==null || eduArea=='') && (eduStep==null || eduStep=='')){
				queryPerfect1();
				return;
			}else if(eduArea==null || eduArea=='' || eduStep==null || eduStep==''){
				alert("请选择区域和学段！");
				return;
			}

			$(".user-list").hide();
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
			$("#student-qushi").show();
			$(document).statistical({}).queryPerfect2(eduArea, eduStep);
		}

		function queryPerfect1(){
			var eduarea = $('#eduarea').val();
			var edustep = $('#edustep').val();


			$("#studentTotal").parent().hide();
			$("#student-qushi").hide();
			$(".user-list").find("table").find("tr:gt(0)").remove();
			$(".user-list").show();
			//代理机构权限
			$.ajax({
				url: rootPath + "/query/statistics/areaTotalStatistics",
				data: {eduArea:eduarea,eduStep:edustep},
				type: 'post',
				beforeSend: function (XMLHttpRequest) {
					$(".loading").show();
					$(".loading-bg").show();
				},
				success: function (jsonData) {
					if (jsonData.data.length == 0) {
						$(".user-list")
								.find("table")
								.append('<tr><td colspan="5">没有查找到数据</td></tr>');
					}else{
						var areaList = new Array();
						$.each(jsonData.data,function (i, area) {
							var areaData = {};
							//转化学段
							switch(area.eduStep) {
								case "STEP_01":
									areaData.STEP_01 = area.registerNum;
									break;
								case "STEP_02":
									areaData.STEP_02 = area.registerNum;
									break;
								case "STEP_03":
									areaData.STEP_03 = area.registerNum;
									break;
							}
							if(areaList.length>0 && areaList[areaList.length-1].eduArea == area.eduArea){
								$.extend(areaList[areaList.length-1], areaData);
							}else{
								areaData.eduArea = area.eduArea;
								switch(area.eduArea) {
									case "510102": areaData.areaName = "高新区";break;
									case "510104": areaData.areaName = "锦江区";break;
									case "510105": areaData.areaName = "青羊区";break;
									case "510106": areaData.areaName = "金牛区";break;
									case "510107": areaData.areaName = "武侯区";break;
									case "510108": areaData.areaName = "成华区";break;
									case "510112": areaData.areaName = "龙泉驿区";break;
									case "510113": areaData.areaName = "青白江区";break;
									case "510114": areaData.areaName = "新都区";break;
									case "510115": areaData.areaName = "温江区";break;
									case "510116": areaData.areaName = "双流区";break;
									case "510117": areaData.areaName = "郫都区";break;
									case "510121": areaData.areaName = "金堂县";break;
									case "510129": areaData.areaName = "大邑县";break;
									case "510131": areaData.areaName = "蒲江县";break;
									case "510132": areaData.areaName = "新津县";break;
									case "510156": areaData.areaName = "天府新区";break;
									case "510181": areaData.areaName = "都江堰市";break;
									case "510182": areaData.areaName = "彭州市";break;
									case "510183": areaData.areaName = "邛崃市";break;
									case "510184": areaData.areaName = "崇州市";break;
									case "510185": areaData.areaName = "简阳市";break;
								}
								areaList.push(areaData);
							}
						});

						$.each(areaList,function (i, stu) {
							$(".user-list")
									.find("table")
									.append(
											'<tr>'
											+ '<td>'
											+ (stu.areaName ? stu.areaName
													: "")
											+ '</td>'
											+ '<td>'
											+ (Number(stu.STEP_01)+Number(stu.STEP_02)+Number(stu.STEP_03))
											+ '</td>'
											+ '<td>'
											+ (stu.STEP_01 ?stu.STEP_01
													: "")
											+ '</td>'
											+ '<td>'
											+ (stu.STEP_02 ? stu.STEP_02
													: "")
											+ '</td>'
											+ '<td>'
											+ (stu.STEP_03 ? stu.STEP_03
													: "")
											+ '</td>'
											+ '</tr>');
						});
					}
				},
				complete: function (XMLHttpRequest, textStatus) {
					$(".loading").hide();
					$(".loading-bg").hide();
				}
			});
		}
	</script>
</body>
</html>