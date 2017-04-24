<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath=request.getContextPath(); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<tr class="top-tr">
    <td>试卷名称</td>
    <td>题量</td>
    <td>分数</td>
    <td>创建时间</td>
    <td>创建人</td>
    <td></td>
</tr>
<c:if test="${!empty paper.data }">
<c:forEach var="p" items="${paper.data }">
   <tr>
       <td>${p.paperName }</td>
       <td>${p.totalTopicNum }</td>
       <td>${p.totalScore }</td>
       <td><fmt:formatDate value="${p.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
       <td>
       	<c:if test="${!empty p.name }">
       		${p.name }
       	</c:if>
       	<c:if test="${empty p.name }">
       		${p.mobile }
       	</c:if>
       </td>
       <td>
	       <c:if test="${p.status == 1 }">
	       	<span class="use-btn used" data-id="${p.id }" data-name="${p.paperName }" data-cname="${p.cateName }" data-sname="${p.subName }">已使用</span>
	       </c:if>
	       <c:if test="${p.status != 1 }">
	       	<span class="use-btn" data-id="${p.id }" data-name="${p.paperName }" data-cname="${p.cateName }" data-sname="${p.subName }">使用</span>
	       </c:if>
       </td>
   </tr>
</c:forEach>
</c:if>
<c:if test="${empty paper.data }">
	<tr>
		<td colspan="6" align="center">
			当前没有符合条件的试卷，不如去
			<a href="javascript:;" class="btn-create-paper">创建试卷</a>
			试试
		</td>
	</tr>
</c:if>
  
<script type="text/javascript">
	$(function(){
		$(".pagination").html("");
		$(".pagination").pagination('${paper.rowCount}', {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : '${paper.pageNo - 1}',
			link_to : "javascript:;",
			num_display_entries : 5,
			items_per_page : '${paper.pageSize}',
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				selPaper(pageNo);
			}
		});
		//对比
		if($(".showes").length > 0){
			for(var i=0; i<$(".use-btn").length ; i++){
				for(var j=0; j<$(".showes").length; j++){
					if($(".showes:eq(" + j + ")").closest(".exampaper").attr("data-id") 
						== $(".use-btn:eq(" + i + ")").attr("data-id")){
						$(".use-btn:eq(" + i + ")").html("已使用").removeClass("used").addClass("used");
					}
				}
			}
		}
		if($(".hideds").length > 0){
			for(var i=0; i<$(".use-btn").length ; i++){
				for(var j=0; j<$(".hideds").length; j++){
					if($(".hideds:eq(" + j + ")").closest(".exampaper").attr("data-id") 
						== $(".use-btn:eq(" + i + ")").attr("data-id")){
						$(".use-btn:eq(" + i + ")").html("使用").removeClass("used");
					}
				}
			}
		}
	});
</script>
