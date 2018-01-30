<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<input type="hidden" id="tID" value="${teacherId}">
<h5 style="display: inline-block;">收入明细</h5>
<i class="icon iconfont closeIncome" style="float: right;margin-top: 10px;">&#xe610;</i>
<table>
	<col width="20%">
	<col width="40%">
	<col width="40%">
	<tr>
		<th>序号</th>
		<th>课程名称</th>
		<th class="btn-sort">收入（元）</th>
	</tr>
	<c:forEach var="order" items="${orderDetails.data}" varStatus="vs">
		<tr>
			<td>${vs.count}</td>
			<td>${order.commodityName}</td>
			<td><fmt:formatNumber type="number" value="${order.fetchMoney}"  groupingUsed="false" pattern="########.##" /></td>
		</tr>
	</c:forEach>

</table>
<%--<a href="##" class="btn btn-default">上一页</a>
<a href="##" class="btn btn-primary">下一页</a>--%>
<div class="pages">
	<ul class="paginationDetails">
	</ul>
</div>
<script>
	$(function(){
		//关闭弹窗
		$('.closeIncome').click(function(){
			$('.detailIncome').fadeOut();
			$('.opacityIncome').fadeOut();
		});

		$(".paginationDetails").empty();
		$(".paginationDetails").pagination('${orderDetails.rowCount}', {
			next_text : "下一页",
			prev_text : "上一页",
			current_page :${orderDetails.pageNo}-1,
			link_to : "javascript:void(0)",
			num_display_entries : 8,
			items_per_page :  ${orderDetails.pageSize},
			num_edge_entries : 1,
			callback:function(page,jq){
				var pageNo = page + 1;
				queryTeacherDetails(pageNo,$("#tID").val());
			}
		});
	});
</script>
