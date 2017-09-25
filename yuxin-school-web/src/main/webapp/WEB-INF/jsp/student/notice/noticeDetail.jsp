<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<input type="hidden" value="${msgPage.rowCount }" id="rowCount"/>
<input type="hidden" value="${msgPage.pageNo }" id="pageNo"/>
<input type="hidden" value="${msgPage.pageSize }" id="pageSize"/>
    <ul>
    	<c:forEach var="m" items="${msgPage.data }">
	       <li>
	           <table class="table table-noline">
	               <col width="25%">
	               <col width="20%">
	               <col width="20%">
	               <col width="20%">
	               <col width="15%">
	               <tr>
	                   <td rowspan="2">
	                       <span class="c-title">${m.title }</span>
	                   </td>
	                   <td>
	                       <span class="t-title">通知类型：</span>
	                       <span class="t-content">
	                       		<c:if test="${m.messageType == 'STUDENT_MESSAGE_CLASSTYPE' }">
	                       			课程通知
	                       		</c:if>
	                       		<c:if test="${m.messageType == 'STUDENT_MESSAGE_MODULENO' }">
	                       			班号通知
	                       		</c:if>
	                       		<c:if test="${m.messageType == 'STUDENT_MESSAGE_SPECIAL' }">
	                       			指定通知
	                       		</c:if>
	                       		<c:if test="${m.messageType == 'STUDENT_MESSAGE_GROUP' }">
	                       			分组通知
	                       		</c:if>
	                       		<c:if test="${m.messageType == 'STUDENT_MESSAGE_WEIXIN' }">
	                       			微信通知
	                       		</c:if>
	                       </span>
	                   </td>
	                   <td>
	                   <c:if test="${m.messageType == 'STUDENT_MESSAGE_CLASSTYPE' }">
	                       <span class="t-title">课程：</span>
	                       <span class="t-content">${m.classTypeName }</span>
	                   </c:if>
	                   <c:if test="${m.messageType == 'STUDENT_MESSAGE_WEIXIN' }">
	                       <span class="t-title">课程：</span>
	                       <span class="t-content">${m.classTypeName }</span>
	                   </c:if>
	                   <c:if test="${m.messageType == 'STUDENT_MESSAGE_MODULENO' }">
	                       <span class="t-title">班号：</span>
	                       <span class="t-content">${m.moduleNoName }</span>
	                   </c:if>
	                   <c:if test="${m.messageType == 'STUDENT_MESSAGE_GROUP' }">
	                       <span class="t-title">分组：</span>
	                       <span class="t-content">${m.groupOneName }<c:if test="${!empty m.groupTwoName}">/${m.groupTwoName}</c:if></span>
	                   </c:if>
	                   </td>
	                   <td>
	                       <span class="t-title">操作人：</span>
	                       <span class="t-content">
							<c:if test="${!empty m.creatorName}">
								${m.creatorName }
							</c:if>
							<c:if test="${empty m.creatorName && !empty m.username}">
								${m.username }
							</c:if>
							<c:if test="${empty m.creatorName && empty m.username}">
								${m.mobile }
							</c:if>
	                      </span>
	                   </td>
	                   <td rowspan="2">
	                       <a href="javascript:;" class="btn btn-sm btn-default btn-result" data-id="${m.id }">查看通知结果</a>
	                   </td>
	               </tr>
	               <tr>
	                   <td>
	                       <span class="t-title">通知方式：</span>
	                       <span class="t-content">
	                       		<c:if test="${m.messageMethod == 'STUDENT_MESSAGE_MOBILE' }">
	                       			短信通知
	                       		</c:if>
	                       		<c:if test="${m.messageMethod == 'STUDENT_MESSAGE_WEB' }">
	                       			站内信
	                       		</c:if>
	                       		<c:if test="${m.messageMethod == 'STUDENT_MESSAGE_EMAIL' }">
	                       			邮件通知
	                       		</c:if>
	                       		<c:if test="${m.messageMethod == 'STUDENT_MESSAGE_MOBILE_WEIXIN' }">
	                       			微信指定通知
	                       		</c:if>
	                       </span>
	                   </td>
	                   <td>
	                       <span class="t-title">通知人数：</span>
	                       <span class="t-content">${m.sendNum }</span>
	                   </td>
	                   <td>
	                       <span class="t-title">发送时间：</span>
	                       <span class="t-content">
	                       	<fmt:formatDate value="${m.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
	                       </span>
	                   </td>
	               </tr>
	           </table>
	       </li>
    	</c:forEach>
   </ul>
<script type="text/javascript">
	$(function(){
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
				selDetail(pageNo);
			}
		});
		
		$(".btn-result").each(function(){
			$(this).click(function(){
				var objform = document.createElement("form");
				document.body.appendChild(objform);
				objform.action =rootPath +"/classModuleLesson/selMessage";
				objform.method = "post";
				
				var payId = document.createElement("input");
				payId.type = "hidden";
				objform.appendChild(payId);
				payId.value = $(this).attr("data-id");
				payId.name = "msgId";
				
				objform.submit();
			});
		});
	});
</script>