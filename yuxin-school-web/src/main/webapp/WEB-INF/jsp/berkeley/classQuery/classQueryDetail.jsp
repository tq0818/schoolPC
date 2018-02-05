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
				<td>${m.teacherName }</td>
				<td>
					<c:choose>
						<c:when test="${m.faceFlag eq 0 and m.liveFlag eq 1 and m.videoFlag eq 0 }">直播</c:when>
						<c:when test="${m.faceFlag eq 0 and m.liveFlag eq 0 and m.videoFlag eq 1 }">录播</c:when>
						<c:when test="${m.faceFlag eq 1 and m.liveFlag eq 0 and m.videoFlag eq 0 }">面授</c:when>
						<c:otherwise>其他</c:otherwise>
					</c:choose>
				</td>
				<td>${m.buyNumMax }</td>
				<td>${m.actualNum }</td>
				<%--<td>
					<a href="http://${m.domain}/sysConfigItem/selectDetail/${m.id }" target="_blank" data-id="${m.id }">查看详情</a>
				</td>--%>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
         	<td colspan="8">暂无课程数据</td>
        </tr>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
	$(function(){
		$("#rowCount").val("${msgPage.rowCount }");
		$("#pageNo").val("${msgPage.pageNo }");
		$("#pageSize").val("${msgPage.pageSize }");
		if(${msgPage.rowCount <= 10 }){
            $(".pagination").html("");
        }
//		$(".pagination").html("");
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
				classQueryDetail(pageNo,${paixu});
			}
		});
	});
	
</script>
