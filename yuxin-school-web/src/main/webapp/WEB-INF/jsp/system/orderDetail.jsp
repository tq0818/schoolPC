<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
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
		<th width="15%">操作</th>
	</tr>
	<c:forEach var="order" items="${payPage.data}">
		<tr>
			<td>${order.orderNum }</td>
			<td>${order.commodityName }</td>
			<td>${order.payPrice }</td>
			<td>${order.stuName }</td>
			<td>${order.discountNo}</td>
			<td>${order.orderTime}</td>
			<td>${order.payTime}</td>
			<c:choose>
				<c:when test="${order.payStatus=='PAY_SUCCESS'}">
					<td>已完成</td>
				</c:when>
				<c:otherwise>
					<td>未支付</td>
				</c:otherwise>
			</c:choose>

			<td><a href="##?orderId=${order.payTime}">查看详情</a></td>
		</tr>
	</c:forEach>

</table>
<script>
	//	分页
	$(".pagination").pagination('',
			{
				next_text: "下一页",
				prev_text: "上一页",
				current_page: '',
				link_to: "javascript:void(0)",
				num_display_entries: 8,
				items_per_page: 1,
				num_edge_entries: '',
				callback: function (page, jq) {
					var pageNo = page + 1;

				}
			}
	);
</script>