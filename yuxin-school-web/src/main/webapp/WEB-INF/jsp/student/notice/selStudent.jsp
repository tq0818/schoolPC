<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% String rootPath=request.getContextPath();
%>
<input type="hidden" value="${msgVoPage.rowCount }" id="rowCount"/>
<input type="hidden" value="${msgVoPage.pageNo }" id="pageNo"/>
<input type="hidden" value="${msgVoPage.pageSize }" id="pageSize"/>
<input type="hidden" value="${msgId }" id="msgId"/>
<input type="hidden" value="${status }" id="status"/>
<input type="hidden" value="${msg.companyId }" id="companyId"/>
<input type="hidden" value="${msg.schoolId }" id="schoolId"/>

<table class="table table-center">
    <col width="15%">
    <col width="15%">
    <col width="20%">
    <col width="15%">
    <col width="35%">
    <tr>
        <th>学员姓名</th>
        <th>手机号&邮箱</th>
        <th>课程&班号&分组</th>
        <th>通知时间</th>
        <th>发送结果</th>
    </tr>
    <c:forEach var="s" items="${msgVoPage.data }">
    	<tr class="stuDetail">
    		<td>${s.name }
    			<input type="hidden" value="${s.id }"/>
    		</td>
    		<td><c:if test="${!empty  s.phone }">${s.phone }</c:if><c:if test="${empty  s.phone }">${s.email }</c:if></td>
    		<td>
    			<c:if test="${msg.classTypeId != null }">
    				${msg.classTypeName }
    			</c:if>
    			<c:if test="${msg.moduleNoId != null }">
    				${msg.moduleNoName }
    			</c:if>
    			<c:if test="${!empty msg.groupOneId}">
    				${msg.groupOneName}
    				<c:if test="${msg.groupTwoId}">/${msg.groupTwoName}</c:if>
    			</c:if>
    			<c:if test="${msg.classTypeId == null && msg.moduleNoId == null && empty msg.groupOneId}">
    				指定通知
    			</c:if>
    		</td>
    		<td class="send_time_${s.id }">
		    		<script type="text/javascript">
		    			$(".send_time_${s.id}").html($(".send-time").html());
		    		</script>
<%--     			<fmt:formatDate value="${s.applyTime }" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
    		</td>
    		<td></td>
    	</tr>
    </c:forEach>
</table>
<script type="text/javascript">
	$(function(){
		$(".paginations").html("");
		$(".paginations").pagination($("#rowCount").val(), {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : ($("#pageNo").val() - 1),
			link_to : "javascript:;",
			num_display_entries : 3,
			items_per_page : $("#pageSize").val(),
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				var pageSize = $("#pageSize").val();
				var msgId = $("#msgId").val();
				var status = $("#status").val();
				selStudent(pageNo,pageSize,status,msgId);
			}
		});
		
		$(".stuDetail").each(function(){
			var parent = $(this);
			var id = $(this).find("input").val();
			$.ajax({
				url:rootPath + "/classModuleLesson/selStuResult",
				type:"post",
				data:{"id":id},
				dataType:"json",
				success:function(data){
					parent.find("td:last").html(data.result);
				}
			});
		});
	});
</script>