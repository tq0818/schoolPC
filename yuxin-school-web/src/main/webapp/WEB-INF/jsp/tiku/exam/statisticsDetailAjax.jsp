<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".paginations").html("");

		$(".paginations").pagination('${pageFinder.rowCount}', {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : '${pageFinder.pageNo-1}',
			link_to : "javascript:void(0)",
			num_display_entries : 8,
			items_per_page : '${pageFinder.pageSize}',
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				var paperId = $("#paperName").val();
				var status = $("#passStatus").val();
				var mobile = $("#mobile").val();
				var examId = $("#examId").val();
				var start = $("#start").val();
				var end = $("#end").val();
				Forms.loadDetailAjaxInfo(pageNo, paperId, status, mobile,examId,start,end);
			}
		});

	});
</script>
<table id="tableList">
	<colgroup>
	  <col width="15%">
	  <col width="15%">
	  <col width="15%">
	  <col width="15%">
	  <col width="15%">
	  <col width="15%">
	  <col width="10%">
	</colgroup>
	<tr class="top-tr">
		<td>学员名称</td>
		<td>用户名</td>
		<td>手机号</td>
		<td>当前试卷分数</td>
		<td>考试时间</td>
		<td>考试情况</td>
		<td>试卷名称</td>
	</tr>
	<c:if test="${!empty pageFinder.data }">
	<c:forEach items="${pageFinder.data}" var="paper" varStatus="status">
		<tr>
			<td>${paper.userName }</td>
			<td>${paper.allowUserExam }</td>
			<td>${paper.userMobile }</td>
			<td>${paper.score }</td>
			<td><fmt:formatDate value="${paper.createTime }"/></td>
			<td>
				<c:if test="${paper.status  != 1}">
					未通过
				</c:if>
				<c:if test="${paper.status  == 1}">
					通过
				</c:if>
			</td>
			<td>${paper.tikuPaperName }</td>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${empty pageFinder.data }"><tr><td colspan="7">没有相关数据</td></tr></c:if>
</table>
<form id="exportForm"  method="post">
<input type="hidden" name="page" id="page"/>
<input type="hidden" name="tikuExamId" id="tikuExamId"/>
<input type="hidden" name="startTime" id="startTime" value="2000-1-1"/>
<input type="hidden" name="endTime" id="endTime"  value="2099-1-1"/>
<input type="hidden" name="userMobile" id="userMobile"/>
<input type="hidden" name="tikuPaperId" id="tikuPaperId"/>
<input type="hidden" name="status" id="status"/>
</form>