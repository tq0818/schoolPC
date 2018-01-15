<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<input type="hidden" value="${payPage.rowCount }" id="rowCount"/>
<input type="hidden" value="${payPage.pageNo }" id="pageNo"/>
<input type="hidden" value="${payPage.pageSize }" id="pageSize"/>
<table class="table table-center allOrderList" >
	<tr>
		<th width="3%">序号</th>
		<th width="10%">老师</th>
		<th width="10%">性别</th>
		<th width="10%">教师级别</th>
		<c:if test="${isArea eq 0}">
		<th width="10%">所属学校</th>
		</c:if>
		<th width="10%" class="btn-sort">总收入</th>
		<th width="10%"><a href="##">操作</a></th>
	</tr>
<%--	<tr>
		<td>1</td>
		<td>1</td>
		<td>1</td>
		<td>1</td>
		<td>1</td>
		<td>1</td>
		<td><a href="##">详情</a></td>--%>

	<c:forEach var="order" items="${payPage.data}" varStatus="vs">
		<tr>
			<td>${vs.count}</td>
			<td>${order.teacherName}</td>
			<td>${order.sex}</td>
			<td>${order.teacherLevel}</td>
			<c:if test="${isArea eq 0}">
			<td>${order.schoolName}</td>
			</c:if>
			<td>${order.fetchMoney}</td>
			<td><a href="##">详情</a></td>
		</tr>
	</c:forEach>
	<%--</tr>--%>
</table>

<script type="text/javascript">
	$(document).ready(function(){
		$(".pagination").pagination('${payPage.rowCount}', {
			next_text : "下一页",
			prev_text : "上一页",
			current_page :${payPage.pageNo}-1,
			link_to : "javascript:void(0)",
			num_display_entries : 8,
			items_per_page :  ${payPage.pageSize},
			num_edge_entries : 1,
			callback:function(page,jq){
				var pageNo = page + 1;
				queryTeacherMoney(pageNo);
			}
		});
	});
</script>