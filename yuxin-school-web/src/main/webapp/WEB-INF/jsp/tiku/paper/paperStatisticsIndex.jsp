<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
<title>考试统计</title>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/classes.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/classedit.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/newresource.css" />
<script type="text/javascript"> var rootPath = "<%=rootPath%>"</script>
</head>
<style>
	.L-exam-line div{text-align:center; line-height:84px; margin-top:0;}
	.L-exam-line .td1{width:25%;}
	.L-exam-line .td2{width:32%;}
	.L-exam-line .td2{width:10%;}
	.L-exam-line .td3 p{_line-height:40px; margin-top:14px;}
	.L-exam-line .td6 a{line-height:84px; display:inline-block; width:136px; height:26px; background:#07bbee; color:#fff;}

</style>

<body>
	<jsp:include page="/WEB-INF/jsp/tiku/tikuHeader.jsp"></jsp:include>
	<div class="u-wrap company clear">
		<div class="mainbackground nopadding">
			<div class="heading">
				<h2 class="h5">考试统计</h2>
				<span class="line"></span>
				<div class="new-set" onclick="location.href='<%=rootPath%>/tikuPaper/toTikuPaper/${tikuId}'">返回</div>
			</div>
			<div class="r-list L-r-list">
				<div class="sc-select searchBook clear"">
					<dl class="bookTitle">
	                    <dt>试卷名称：<span>${statistics.paperName}</span></dt>
	                    <dd>试卷作答人数：<span>${statistics.total}人</span></dd>
	                    <dd>最高分：<span>${statistics.max}分</span></dd>
	                    <dd>最低分：<em>${statistics.min}分</em></dd>
	                    <dd>平均分：<em>${statistics.avg}分</em></dd>
	                </dl>
	                <div class="bookBut">
	                    <a id="exportExcle" href="javascript:void(0);" class="btn btn-mini btn-primary">导出数据</a>
	                </div>
				</div>
				<div class="r-list L-r-list-table" id="teacherContent" style="min-height: 350px;position: relative;">
					<table id="tableList" class="table table-hover table-center table-list L-table">
						<colgroup>
						  <col width="20%">
						  <col width="20%">
						  <col width="20%">
						  <col width="20%">
						  <col width="20%">
						</colgroup>
						<tr class="top-tr">
							<td>学员名称</td>
							<td>用户名</td>
							<td>手机号</td>
							<td>当前试卷分数</td>
							<td>考试时间</td>
						</tr>
						<tr><td colspan="5">暂无数据</td></tr>
					</table>
				</div>
				<div class="pages pagination"></div>
			</div>
		</div>
	</div>
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->
	<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script src="<%=rootPath%>/javascripts/splitmarket.js"></script>
	<script src="<%=rootPath%>/javascripts/classedit.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/laydate/laydate.js"></script>
	<script src="<%=rootPath%>/javascripts/tiku/paper/paperStatisticsIndex.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<input type="hidden" value="${examId }" id="examId" />
	<script type="text/javascript">
	$(function(){
		$(".tiHeader .navspace li>a:eq(1)").addClass("active");
		$selectMenu('tiku_header');
	})
	</script>
	
	<input type="hidden" id ="paperId" value="${paperId}">
	<input type="hidden" value="${tikuId}" id="tikuId"/>
	<form id="exportForm"  method="post">
		<input type="hidden" name="page" id="page"/>
		<input type="hidden" name="tikuExamId" id="tikuExamId"/>
		<input type="hidden" name="startTime" id="startTime" value="2000-1-1"/>
		<input type="hidden" name="endTime" id="endTime"  value="2099-1-1"/>
		<input type="hidden" name="userMobile" id="userMobile"/>
		<input type="hidden" name="exerciseId" id="tikuPaperId"/>
		<input type="hidden" name="status" id="status"/>
	</form>
</body>
</html>