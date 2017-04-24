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
				Forms.loadAjaxInfo(pageNo);
			}
		});

	});
</script>
<c:forEach items="${pageFinder.data}" var="ex" varStatus="status">
	<div class="exam-line">
		<div class="td1 middle-font">
			<span>${ex.examName }</span>
			<c:if test="${ex.status == 2 }">
				<div class="blue">已发布</div>
			</c:if>
			<c:if test="${ex.status != 2 }">
				<div>未发布</div>
			</c:if>
		</div>
		<div class="td21">
			<div class="td21-top">考试简介：${ex.introductionStr }</div>
			<div class="td21-bottom">
				<div>考试类型：证书考试</div>
				<div>通过分数：${ex.passScore }</div>
				<c:if test="${ex.examCount == 0}">
					<div>考试次数：不限制</div>
				</c:if>
				<c:if test="${ex.examCount != 0}">
					<div>考试次数：${ex.examCount }次</div>
				</c:if>
				<div>创建时间：<fmt:formatDate value="${ex.createTime }"/></div>
				<div>创建人：${ex.creatorName }</div>
			</div>
		</div>
		<div class="td31">
			<div class="clear">
				<div class="right-btns delete" ids="${ex.id }">删除考试</div>
				<c:if test="${ex.status == 2 }">
				<div class="right-btns cancleFB" status="1" ids="${ex.id }">取消发布</div>
				</c:if>
				<c:if test="${ex.status != 2 }">
				<div class="right-btns FB" status="2" ids="${ex.id }">发布</div>
				</c:if>
			</div>
			<div class="clear">
				<div class="right-btns TJ" ids="${ex.id }">统计</div>
				<div class="right-btns" onclick="location.href='<%=rootPath%>/tikuExam/updateExam/${ex.id }'">编辑</div>
			</div>
		</div>
	</div>
</c:forEach>