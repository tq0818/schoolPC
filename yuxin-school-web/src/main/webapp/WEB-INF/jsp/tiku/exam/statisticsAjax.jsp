<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<script type="text/javascript"
	src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".pagination").html("");

		$(".pagination").pagination('${pageFinder.rowCount}', {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : '${pageFinder.pageNo-1}',
			link_to : "javascript:void(0)",
			num_display_entries : 8,
			items_per_page : '${pageFinder.pageSize}',
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				var examId = $("#examId").val();
				Forms.loadAjaxInfo(pageNo,examId);
			}
		});

	});
</script>
<div class="exam-list">
	<div class="list th-title">
	    <ul class="clear">
	        <li class="w25">试卷名称</li>
	        <li class="w11">考试次数</li>
	        <li class="w11">通过人数</li>
	        <li class="w11">通过率</li>
	        <li class="w15">平均得分</li>
	        <li class="w11">未通过人数</li>
	        <li class="w16">操作</li>
	    </ul>
	</div>
	<c:forEach items="${pageFinder.data}" var="paper" varStatus="status">
		<div class="list tr-content">
	         <ul class="clear">
	             <li class="w25" title="${paper.paperName }">${paper.paperNameSub }</li>
	             <li class="w11">${paper.paperCount }次</li>
	             <li class="w11">${paper.passPaperPeople}人</li>
	             <li class="w11">${paper.paperPassRate }%</li>
	             <li class="w15">${paper.paperAvg }分</li>
	             <li class="w11">${paper.allPaperPeople-paper.passPaperPeople}人</li>
	             <li class="w16 button detail" paperId="${paper.tikuPaperId }">查看详情</li>
	         </ul>
	     </div>
	</c:forEach>
</div>
