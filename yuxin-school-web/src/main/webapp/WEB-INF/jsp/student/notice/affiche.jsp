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
	                       <span class="c-title">公告通知</span>
	                   </td>
	                   <td>
	                       <span class="t-title">发送时间：</span>
	                       <span class="t-content"><fmt:formatDate value="${m.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" /> </span>
	                      
	                   </td>
	                   <td>
	                       <span class="t-title">操作人：</span>
	                       <span class="t-content">${m.senderName} </span>
	                      
	                   </td>
	                   <td>
	                    <input id="affiche${m.id }" type="button" <c:if test="${m.status ==0}"> value="公告上架"  status="1" </c:if>  <c:if test="${m.status ==1}"> value="公告下架" status="0"</c:if> onclick="afficheShelving(${m.id})" >
	                   </td>
	                     <td>
	                    <a href="<%=rootPath %>/student/createNotice?addAffiche=addAffiche&afficheId=${m.id}"  >查看公告结果</a>
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
		
		
	});
	
	function afficheShelving(id){
		var status = $('#affiche'+id).attr("status");
		$.ajax({
   			url : "<%=rootPath%>/student/afficheShelving",
   			type:"post",
   			data:{"id":id,"status":status},
   			dataType:"text",
   			success:function(data){
   				if(data =="success"){
   					alert("操作成功");
   					selDetail(1);
   				}
   				
   			}
		
			});
	}
</script>