<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<input type="hidden" value="${payPage.rowCount }" id="rowCount"/>
<input type="hidden" value="${payPage.pageNo }" id="pageNo"/>
<input type="hidden" value="${payPage.pageSize }" id="pageSize"/>
<table class="table table-center allOrderList" >
	<tr>
		<th width="3%">订单编号</th>
		<th width="10%">课程名</th>
		<th width="10%">金额（元）</th>
		<th width="10%">姓名</th>
		<th width="10%">电话	</th>
		<th width="10%">下单时间</th>
		<th width="10%">付款时间</th>
		<th width="10%">订单状态</th>
	<%--	<th width="15%">操作</th>--%>
	</tr>
	<c:forEach var="order" items="${payPage.data}">
		<tr>
			<td>${order.orderNum }</td>
			<td>${order.commodityName }</td>
			<td>${order.payPrice }</td>
			<c:choose>
				<c:when test="${empty order.stuName }">
					<td>${order.couponCode}</td>
				</c:when>
				<c:when test="${empty order.stuName and empty order.couponCode }">
					<td>${order.discountNo}</td>
				</c:when>
				<c:otherwise>
					<td>${order.stuName }</td>
				</c:otherwise>
			</c:choose>

			<td>${order.discountNo}</td>
			<c:choose>
				<c:when test="${empty order.orderTime}">
					<td>${order.orderTime}</td>
				</c:when>
				<c:otherwise>
					<td><fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd HH:mm"/></td>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${empty order.payTime}">
					<td>${order.payTime}</td>
				</c:when>
				<c:otherwise>
					<td><fmt:formatDate value="${order.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</c:otherwise>
			</c:choose>

			<td>${order.payStatus}</td>
			<%--<c:choose>
				<c:when test="${order.payStatus=='PAY_SUCCESS'}">
					<td>已完成</td>
				</c:when>
				<c:when test="${order.payStatus=='PAY_NON'}">
					<td>未付款</td>
				</c:when>
				&lt;%&ndash;<c:when test="${order.payStatus=='SUB_ORDER_DELTED'}">
					<td>已取消</td>
				</c:when>&ndash;%&gt;
				<c:when test="${order.payStatus=='PAY_FAIL'}">
					<td>失败</td>
				</c:when>
			</c:choose>--%>

			<%--<td><a href="##?orderId=${order.payTime}">查看详情</a></td>--%>
		</tr>
	</c:forEach>

<script type="text/javascript">
	$(document).ready(function(){
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
				queryOrders(pageNo);
			}
		});
	});
</script>