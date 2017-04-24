<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>

<input type="hidden" value="${stuPage.rowCount }" id="rowCountStu"/>
<input type="hidden" value="${stuPage.pageNo }" id="pageNoStu"/>
<input type="hidden" value="${stuPage.pageSize }" id="pageSizeStu"/>
<input type="hidden" value="${noId }" id="noId"/>
<table class="table table-center">
	<tr>
    	<th>报名时间</th>
        <th>课程</th>
        <th>学员姓名</th>
        <th>手机号</th>
        <th>报名来源</th>
    </tr>
    <c:forEach var="s" items="${stuPage.data }">
    <tr>
    	<td>
    		<fmt:formatDate value="${s.applyTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
    	</td>
        <td>${s.classTypeName }</td>
        <td>${s.name }</td>
        <td>${s.mobile }</td>
        <td>
        	<c:if test="${s.applyChannelCode == 'CHANNEL_OFFLINE' }">线下报名</c:if>
        	<c:if test="${s.applyChannelCode == 'CHANNEL_ONLINE' }">线上报名</c:if>
        </td>
    </tr>
    </c:forEach>
</table>
<script type="text/javascript">
   $(function(){
	    $(".paginations").html("");
		$(".paginations").pagination($("#rowCountStu").val(), {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : ($("#pageNoStu").val() - 1),
			link_to : "javascript:;",
			num_display_entries : 5,
			items_per_page : $("#pageSizeStu").val(),
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				var noId = $("#noId").val();
				selStudent(pageNo,noId);
			}
		});
    });
</script>