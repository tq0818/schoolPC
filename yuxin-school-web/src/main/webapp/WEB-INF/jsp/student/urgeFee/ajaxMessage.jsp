<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<table class="table table-center">
		<tr>
	     <th>姓名</th>
	     <th>用户名</th>
	     <th>手机号</th>
	     <th>课程</th>
	     <th>报名日期</th>
	     <th>总费用</th>
	     <th>分期日期</th>
	     <th>费用</th>
	     <th>结费状态</th>
	     <th>操作</th>
	 </tr>
     <c:forEach items="${pf.data }" var="stagingVo">
	     <tr>
		     <th>${stagingVo.stuName }</th>
		     <th>${stagingVo.username }</th>
		     <th>${stagingVo.mobile }</th>
		     <th>${stagingVo.classTypeName }</th>
		     <th><fmt:formatDate value="${stagingVo.applyTime}" timeStyle="yyyy-MM-dd"/></th>
		     <th>${stagingVo.totalAmount }</th>
		     <th><fmt:formatDate value="${stagingVo.stageDate }" timeStyle="yyyy-MM-dd"/></th>
		     <th>${stagingVo.stageFee }</th>
		     <th>
		     	<c:if test="${stagingVo.stageStatus==1 }">
		     		已缴费
		     	</c:if>
		     	<c:if test="${stagingVo.stageStatus==0}">
		     		未缴费
		     	</c:if>
		     </th>
		     <th>
		     	<c:if test="${stagingVo.stageStatus==0 }">
		     		<a href="javascript:;" class="btn btn-mini btn-success" onclick="pay(${stagingVo.stageId});">结费</a>
		     	</c:if>
			</th>
		 </tr>
	</c:forEach>
	<c:if test="${empty pf.data }">
 		 <tr style="background-color: #f6f6f6;"><td colspan="10">暂无数据</td></tr>
 	</c:if>
</table>
<form id="pay" action="<%=request.getContextPath()%>/student/pay1" method="post">
	<input type="hidden" id="id" name="id"/>
</form>
<script>
$(function(){
	$("#firstItemPage").html("");
})
	function pay(id){
		$("#id").val(id);
		$("#pay")[0].submit();
	}
	
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript">
	$("#firstItemPage").pagination('${pf.rowCount}', {
		next_text : "下一页",
		prev_text : "上一页",
		link_to : "javascript:void(0)",
		current_page : '${pf.pageNo}' - 1,
		num_display_entries : 8,
		items_per_page : '${pf.pageSize}',
		num_edge_entries : 1,
		callback:function(page,jq){
			var pageNo = page + 1;
			search(pageNo);
		}
	});
</script>