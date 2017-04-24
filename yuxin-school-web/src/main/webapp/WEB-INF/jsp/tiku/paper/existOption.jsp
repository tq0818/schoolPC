<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<input type="hidden" value="${topicPage.rowCount }" id="rowCount"/>
<input type="hidden" value="${topicPage.pageNo }" id="pageNo"/>
<input type="hidden" value="${topicPage.pageSize }" id="pageSize"/>

<table class="table table-center table-hover">
	<tbody>
	    <col width="10%">
	    <col width="80%">
	    <col width="10%">
	    <tr>
	        <td style="font-size:14px;background-color:rgba(114, 114, 114, 0.35);">选择</td>
	        <td style="font-size:14px;background-color:rgba(114, 114, 114, 0.35);">题目</td>
	        <td style="font-size:14px;background-color:rgba(114, 114, 114, 0.35);">操作</td>
	    </tr>
	    
	    <c:if test="${fn:length(topicPage.data)<=0}">
	    	<tr>
	    		<td colspan="3">没有可用数据</td>
	    	</tr>
	    </c:if>
	    <c:forEach items="${topicPage.data }" var="t">
	    	<tr >
	    		<td>
	    			<input type="checkbox" data-id="${t.id }" class="addTopic"/>
	    		</td>
	    		<td class="nonimg" align="left" style="text-align: left;padding-left: 15px;"<%--  title="${t.topicName }" --%>>
	    			<%-- <c:if test="${fn:length(t.topicName ) > 40}">
	    				${fn:substring(t.topicName,0,40) }......
	    			</c:if>
	    			<c:if test="${fn:length(t.topicName ) <= 40}"> --%>${t.topicName }<%-- </c:if> --%>
	    		</td>
	    		<td>
	    			<a href="javascript:;" class="btn-detail" data-id="${t.id }">详细</a>
	    		</td>
	    	</tr>
	    </c:forEach>
    </tbody>
</table>

<script type="text/javascript">
	$(function(){
		$(".paginations").html("");
		$(".paginations").pagination($("#rowCount").val(), {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : ($("#pageNo").val() - 1),
			link_to : "javascript:;",
			num_display_entries : 5,
			items_per_page : $("#pageSize").val(),
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				selTopicExist(pageNo);
			}
		});
		
		$(".nonimg").find("img").css({"width":"40px","height":"40px"});
		
		$(".table-hover tr:gt(0)").click(function(){
			var $checkbox = $(this).find("input[type='checkbox']");
			if(!$($checkbox).prop("checked")){
				$($checkbox).prop("checked", true);
		    }else{
		    	$($checkbox).prop("checked", false);
		    }
			for(var i = 0; i < $(".addTopic").length ; i++){
				if($(".addTopic:eq(" + i + ")").prop("checked")){
					$(".btn-ok").removeAttr("disabled");
					break;
				}
				$(".btn-ok").attr("disabled","disabled");
			}
		});
		
		$(".btn-detail").click(function(){
			$(".topicDetail").show();
			$(".add-classes").hide();
			$.ajax({
				url : rootPath + "/question/selTopicDetail",
				type:"post",
				data:{"id":$(this).attr("data-id")},
				dataType:"html",
				success:function(data){
					$(".loadDetail").html(data);
				}
			});
		});
		
	});
</script>
<style>
	
</style>