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
<title>基地校直播课</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
	<link rel="stylesheet" href="<%=rootPath %>/stylesheets/query/statistics.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
	<style type="text/css">
		.pages li.disabled{padding:0px;}
		.screen-info select{margin-right: 50px;width: 200px;margin-bottom: 10px;}
		.screen-info .text{font-style:normal;}
	</style>
	<%--tob--%>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
	<script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>
<input type="hidden" id="schoolId" value='${schoolId}'/>
<input type="hidden" id="schoolName" value='${schoolName}'/>
<input type="hidden" id="isAdmin" value='${isAdmin}'/>
<input type="hidden" id="isSubAdmin" value='${isSubAdmin}'/>
	<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_berkeley.jsp"></jsp:include>
			<div class="mainbackground nopadding u-wrap" style="background: #f6f6f6;height: 700px;">
				<div class="heading" style="height: 30px;padding-top: 10px;">
					<h2 class="h5" style="display: inline-block;">基地校</h2>
						<a href="<%=rootPath%>/baseSchool/baseSchoolRecording"  class="btn btn-default recordingBtn">录播课</a>
						<a href="<%=rootPath%>/baseSchool/baseSchoolLive" class="btn btn-primary liveBtn">直播课</a>
					<span class="line"></span>
				</div>
				<div class="content-right">

					<form method="post" id="searchForm" class="screen-info">
						<div>
							<input type="hidden" id="isStu" name="isStu" value="1"/>
								<input type="hidden" id="role" name="role" value="${role}"/>
								<c:if test="${role=='all'}">
									<span>区域</span>
									<select name="eduArea" id="eduArea">
										<c:forEach items="${areas}" var="area" >
											<option value="${area.itemCode}" data-id="${area.id}" ${student.eduArea==area.itemValue?"selected":""}>${area.itemValue}</option>
										</c:forEach>
									</select>
									<span>学校</span>
									<select name="eduSchool" id="eduSchool" data-id="${student.eduSchool}">
										<option value="">请选择学校</option>
									</select>
									<span style="margin-right: 23px;">学段</span>
									<select name="eduStep" id="eduStep">
										<option value="">请选择学段</option>
										<c:forEach items="${steps}" var="steps" >
											<option value="${steps.itemCode}" data-id="${steps.id}" >${steps.itemValue}</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${role=='area'}">
									<input type="hidden" name="eduArea" id="eduArea" data-id="${areaId}" value="${area}"/>
									<span>学校</span>
									<!--学校性质-->
									<select name="schoolType" id="schoolType">
										<c:forEach items="${schoolType}" var="schoolType" >
											<option value="${schoolType.itemCode}" data-id="${schoolType.id}" >${schoolType.itemValue}</option>
										</c:forEach>
									</select>
									<select name="eduSchool" id="eduSchool" data-id="${student.eduSchool}">
									</select>
								</c:if>
								<c:if test="${role =='school'}">
									<input type="hidden" name="eduArea" id="eduArea" value="${area}"/>
									<input type="hidden" name="eduSchool" id="eduSchool" value="${eduSchool}"/>
									<span>学段</span>
									<select name="eduStep" id="eduStep">
										<c:forEach items="${steps}" var="steps" >
											<option value="${steps.itemCode}" data-id="${steps.id}" >${steps.itemValue}</option>
										</c:forEach>
									</select>
								</c:if>
							<span>入学年份</span>
								<select name="eduYear" id="eduYear">
									<option value="">请选择入学年份</option>
								</select>
						</div>
						<div style="margin-top: 10px;">
							<span>班级</span>
							<select name="eduClass" id="eduClass">
								<option value="">请选择班级</option>
								<option value="1">1班</option>
								<option value="2">2班</option>
								<option value="3">3班</option>
								<option value="4">4班</option>
								<option value="5">5班</option>
								<option value="6">6班</option>
								<option value="7">7班</option>
								<option value="8">8班</option>
								<option value="9">9班</option>
								<option value="10">10班</option>
								<option value="11">11班</option>
								<option value="12">12班</option>
								<option value="13">13班</option>
								<option value="14">14班</option>
								<option value="15">15班</option>
								<option value="16">16班</option>
								<option value="17">17班</option>
								<option value="18">18班</option>
								<option value="19">19班</option>
								<option value="20">20班</option>
								<option value="21">21班</option>
								<option value="22">22班</option>
								<option value="23">23班</option>
								<option value="24">24班</option>
								<option value="25">25班</option>
								<option value="26">26班</option>
								<option value="27">27班</option>
								<option value="28">28班</option>
								<option value="29">29班</option>
								<option value="30">30班</option>
							</select>
							<span>学科</span>
							<select name="itemThirdCode" id="subject">
								<option value="">请选择学科</option>
								<c:forEach items="${subject}" var="subject" >
									<option value="${subject.itemCode}" data-id="${subject.id}" >${subject.itemName}</option>
								</c:forEach>
							</select>
							<span>课程模块</span>
							<select name="commodityId" id="class">

							</select>
							<span style="margin-right: 23px;">课次</span>
							<select name="lessonId" id="lesson">

							</select>
                            <span class="date marginL0">
								<i class="text">观看时间</i>
								<span><input type="text" name="startTime" id="startTime"  class="date-picker from" value="${startTime}" style="width: 110px"><em>至</em><input type="text" id="endTime" name="endTime" class="date-picker to" value="${endTime}" style="width: 110px"></span>
							</span>
                            <input id="userOrMobile" name="userNameOrMobile" placeholder="学员手机号/用户名查询" type="text" style="height: 20px;width: 160px;"/>

							<button class="btns-default" id="" onclick="searchbtn();" type="button" style="margin-left: 50px;">查询</button>
							<button class="btns-default exportExcleStudent" id="search" type="button">导出数据</button>
						</div>
						<div class="btn-center margin10">

						</div>
                        <div  class=" margin10" style="text-align: right">
                            <span>总报名人数</span><span id="total"></span><span>，实际观课人数</span><span id="watch"></span>
                        </div>
					</form>
					<div class="user-list">
						<table class="table table-center" id="tableList">
							<tr data-buy="true">
								<%--<th width="3%"><input type="checkbox" class="checkboxAll"></th>--%>
								<%--<th width="8%">手机号</th>--%>
								<%--<th width="8%">用户名</th>--%>
								<th width="10%">课程名称</th>
								<th width="10%">课次名称</th>
								<th width="8%">用户名</th>
								<th width="8%">姓名</th>
							 	<th width="8%">学校</th>
							 	<th width="8%">学段</th>
							  	<th width="8%">班级</th>
                                <th width="9%" class="btn-sort" fieldName="times"  sort="">观看累计次数</th>
								<th width="11%" class="btn-sort" fieldName="watch_time"  sort="">观看累计时长</th>
							</tr>
						</table>
						<div class="pages pagination"></div>
					</div>
				</div>
		<!-- ajax加载中div开始 -->
		<div class="loading lp-units-loading" style="display:none">
			<p><i></i>加载中,请稍后...</p>
		</div>
		<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
		<!--  ajax加载中div结束 -->
		<!-- popupwin 累积上课详细 开始    -->
		<div class="popupwin-box cumulativeClass1  clear" style="display:none">
			<div class="popupwin cumulativeClass" style="width:850px; height: auto;top:10px;" data-pupwin="modal">
				<div class="popupwin-title">
					<h2 class="h5">累积上课详情</h2>
					<i class="close iconfont canclekuang"></i>
				</div>
				<div class="popupwin-main">

						<table class="table table-center" id="cumulativeCount">
							<tr data-buy="true">
								<th width="15%">课次名称</th>
								<th width="10%">学员名称</th>
								<c:if test="${role!='school'}">
									<th width="15%">学校</th>
								</c:if>
								<th width="8%">学段</th>
								<th width="10%">班级</th>
								<th width="15%">进入学习时间</th>
								<th width="15%">结束学习时间</th>
								<th width="20%">学习时长</th>
							</tr>
							<tr class="listData"><td colspan="14">没有查找到数据</td></tr>
						</table>
				</div>
			</div>
		</div>
		<!-- popupwin 累积上课详细结束 -->
	</div>
<input type="hidden" id="selectCounts" value="10">
<script type="text/javascript" src="<%=rootPath %>/javascripts/branchschool/query_student_watchList.js"></script>
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
<script type="text/javascript">
    init();
    $selectThirdMenu('watchInfoList');
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

//        二级菜单加active
    $selectSubMenu('baseSchoolLive');

</script>
</body>
</html>