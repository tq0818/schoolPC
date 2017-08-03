<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				$(".pagination").html("");
				$(".pagination").pagination('${pageFinder.rowCount}',{
					next_text : "下一页",
					prev_text : "上一页",
					current_page : '${pageFinder.pageNo-1}',
					link_to : "javascript:void(0)",
					num_display_entries : 8,
					items_per_page : '${pageFinder.pageSize}',
					num_edge_entries : 1,
					callback : function(page, jq) {
						var pageNo = page + 1;
						var tikuId = $("#tikuId").val();
						var subId = $("#subId").val();
						var paperType = $("#paperType").val();
						var paperStatus = $("#paperStatus").val();
						var paperName = $("#paperName").val();
						Forms.loadAjaxInfo(pageNo, subId, paperType,paperStatus, paperName, tikuId);
					}
				});
			});
</script>
<c:forEach items="${pageFinder.data}" var="paper" varStatus="status">
	<li>
		<table class="table table-border-title">
			<tr>
				<td rowspan="2" width="15%">
					<c:if test="${paper.paperStatus == 'PAPER_STATUS_EDIT' }"><span class="status s1">正在编辑</span></c:if>
	    			<c:if test="${paper.paperStatus == 'PAPER_STATUS_WAIT_AUDIT' }"><span class="status s2">等待审核</span></c:if>
	    			<c:if test="${paper.paperStatus == 'PAPER_STATUS_PUBLISH' }">
	    				<span class="status s3">发布成功</span>
	    				<span class="status c9 mt4"><fmt:formatDate value="${paper.auditTime}"/></span>
	    			</c:if>
	    			<c:if test="${paper.paperStatus == 'PAPER_STATUS_AUDIT_FAIL' }"><span class="status s4">审核不通过</span></c:if>

				</td>

				<td rowspan="2" width="60%"><span class="l-title">${paper.paperName}</span>
				</td>
				<td width="10%"><span class="c">编辑者</span> <span class="p">${paper.creatorName}</span>
				</td>
				<td style="width:170px;" rowspan="2" class="btns">
					<a href="javascript:;" class="btn btn-mini btn-primary" paperId="${paper.id}" onclick="javascript:Forms.delPaper(this)" style="display:inline-block;">删除</a>
					<shiro:hasAnyRoles name="机构管理员,试卷审核员">
						<c:if test="${isAudit == 'yes' }">
							<c:if test="${paper.paperStatus == 'PAPER_STATUS_WAIT_AUDIT' }">
								<a href="javascript:;" class="btn btn-mini btn-primary" paperId="${paper.id}" btn="audite" onclick="javascript:Forms.editPaper(this)" style="display:inline-block;">审核</a>
							</c:if>
							<c:if test="${paper.paperStatus != 'PAPER_STATUS_WAIT_AUDIT'}">
								<a href="javascript:;" class="btn btn-mini btn-primary disabled" paperId="${paper.id}" btn="audite" onclick="javascript:void(0)" style="display:inline-block;">审核</a>
							</c:if>
						</c:if>
					</shiro:hasAnyRoles>
					<a href="javascript:;" class="btn btn-mini btn-primary" paperId="${paper.id}" btn="edit" onclick="javascript:Forms.editPaper(this)" style="display:inline-block;">编辑</a>
 					<a href="javascript:;" class="btn btn-mini btn-primary" paperId="${paper.id}" btn="edit" onclick="javascript:Forms.statistics(this)" style="display:inline-block;">统计</a>
				</td>
			</tr>
			<tr>
				<td><span class="c">审核者</span> <span class="p">${paper.auditorName}</span></td>
			</tr>
		</table>
	</li>
</c:forEach>