<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<input type="hidden" value="${payPage.rowCount }" id="rowCount"/>
<input type="hidden" value="${payPage.pageNo }" id="pageNo"/>
<input type="hidden" value="${payPage.pageSize }" id="pageSize"/>
<c:choose>
	<c:when test="${isArea eq 0}">
		<table class="table table-center allOrderList" >
			<tr>
				<th width="3%">序号</th>
				<th width="10%">分校名称</th>
				<th width="10%">所属区域</th>
				<th width="10%" class="btn-sort" onclick="querySchoolMoney(1,'totalSort');">分校总收入（元）</th>
				<th width="10%" class="btn-sort" onclick="querySchoolMoney(1,'fetchSort');">应收费用（元）</th>
			</tr>
			<c:forEach var="order" items="${payPage.data}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${order.schoolName}</td>
					<td>${order.aeraName}</td>
					<td>${order.totalMoney}</td>
					<td>${order.fetchMoney}</td>
				</tr>
			</c:forEach>

		</table>
	</c:when>
	<c:otherwise>
		<table class="table table-center allOrderList" >
			<tr>
				<th width="3%">序号</th>
				<th width="10%">时间</th>
				<th width="10%" class="btn-sort" onclick="querySchoolMoney(1,'totalSort');">总收入（元）</th>
				<th width="10%" class="btn-sort" onclick="querySchoolMoney(1,'handInSort');">应缴费用（元）</th>
				<th width="10%" class="btn-sort" onclick="querySchoolMoney(1,'fetchSort');">实际收入（元）</th>
			</tr>
			<c:forEach var="order" items="${payPage.data}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td><fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd"/></td>
					<td>${order.totalMoney}</td>
					<td>${order.fetchMoney - order.fetchMoney}</td>
					<td>${order.fetchMoney}</td>
				</tr>
			</c:forEach>

		</table>
	</c:otherwise>
</c:choose>


<script type="text/javascript">
	$(document).ready(function(){
		$("#totalMoneyId").empty();
		$("#totalMoneyId").html("${totalMoneyAdd}")
		$(".pagination").empty();
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
				querySchoolMoney(pageNo);
			}
		});
	});
</script>