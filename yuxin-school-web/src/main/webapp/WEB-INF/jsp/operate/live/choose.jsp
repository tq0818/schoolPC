<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-center table-hover">
	<tbody>
	    <col width="10%">
	    <col width="90%">
	    <tr>
	        <td style="font-size:14px;background-color:rgba(114, 114, 114, 0.35);">选择</td>
	        <td style="font-size:14px;background-color:rgba(114, 114, 114, 0.35);">名称</td>
	    </tr>
		<c:if test="${!empty zsPage.data }">
	    <c:forEach var="z" items="${zsPage.data }">
	    	<tr >
	    		<td>
	    			<input type="checkbox" data-name="${z.recordId }" class="addTopic"/>
	    		</td>
	    		<td class="nonimg" align="left" style="text-align: left;padding-left: 15px;cursor: pointer;">${z.name }</td>
	    	</tr>
	    </c:forEach>
		</c:if>
		<c:if test="${empty zsPage.data }">
			
		</c:if>
    </tbody>
</table>
<script type="text/javascript">
	$(function(){
		$(".paginationss").html("");
		$(".paginationss").pagination('${zsPage.rowCount }', {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : ('${zsPage.pageNo }' - 1),
			link_to : "javascript:;",
			num_display_entries : 5,
			items_per_page : '${zsPage.pageSize }',
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				selCourse(pageNo);
			}
		});

		for(var i = 0; i < $(".addTopic").length ; i++){
			if($(".addTopic:eq(" + i + ")").prop("checked")){
				$(".btn-ok").removeAttr("disabled");
				break;
			}
			$(".btn-ok").attr("disabled","disabled");
		}
		
		$(document).off("click").on("click",".table-hover input",function(){
			var $checkbox = $(this).find("input[type='checkbox']");
			if(!$($checkbox).prop("checked")){
		    	$($checkbox).prop("checked", false);
		    }else{
				$($checkbox).prop("checked", true);
		    }
			for(var i = 0; i < $(".addTopic").length ; i++){
				if($(".addTopic:eq(" + i + ")").prop("checked")){
					$(".btn-ok").removeAttr("disabled");
					break;
				}
				$(".btn-ok").attr("disabled","disabled");
			}
		});
		
	});
</script>