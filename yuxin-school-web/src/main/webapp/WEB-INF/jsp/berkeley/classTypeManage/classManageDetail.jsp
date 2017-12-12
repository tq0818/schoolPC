<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<c:choose>
	<c:when  test="${not empty msgPage.data }">
		<c:forEach items="${msgPage.data }" var="m" varStatus="status">
			<tr>
			    <td>${(msgPage.pageNo-1)*msgPage.pageSize+status.index+1 }</td>
			    <td>${m.name }</td>
			    <td>${m.itemThirdName }</td>
			    <td>${m.schoolName }</td>
			    <td>
			    	<c:choose>
			    		<c:when test="${m.livestatus eq 0 }">未开始</c:when>
			    		<c:when test="${m.livestatus eq 1 }">进行中</c:when>
			    		<c:when test="${m.livestatus eq 2 }">已结束</c:when>
			    		<c:otherwise>未开始</c:otherwise>
			    	</c:choose>
			    </td>
			    <td class="slink">
			        <a class="" href="javascript:void(0);">课程详情</a>|
			        <a class="classSchedule"  href="javascript:void(0);">课程表</a>|
			    </td>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
         	<td colspan="6">暂无课程数据</td>
        </tr>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
	$(function(){
		$("#rowCount").val("${msgPage.rowCount }");
		$("#pageNo").val("${msgPage.pageNo }");
		$("#pageSize").val("${msgPage.pageSize }");
		$(".pagination").html("");
		$(".pagination").pagination($("#rowCount").val(), {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : ($("#pageNo").val() - 1),
			link_to : "javascript:;",
			num_display_entries : 5,
			items_per_page : $("#pageSize").val(),
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				classManageDetail(pageNo);
			}
		});
	});
	
</script>