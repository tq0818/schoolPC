<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table class="tt" style="width:100%;border-collapse:collapse;border: 1px solid #ddd;">
	<col width="30%">
	<col width="30%">
	<col width="9%">
	<col width="9%">
	<col width="13%">
	<col width="9%">
	<tr style="border: 1px solid #ddd;">
	  <th>课程名称</th>
	  <th>资料名称</th>
	  <th>资料大小</th>
	  <th>上传人</th>
	  <th>上传时间</th>
	  <th>操作</th>
	</tr>
	<c:if test="${resPage.rowCount == 0}">
		<tr>
			<td colspan="6">没有您要查找的内容</td>
		</tr>
	</c:if>
    <c:forEach var="r" items="${resPage.data }">
    	<tr style="border: 1px solid #ddd;">
    		<td>${r.classTypeName }</td>
    		<td>${r.name }</td>
    		<td>${r.sourceSize }</td>
    		<td>${r.uploader }</td>
    		<td>
    			<fmt:formatDate value="${r.uploadTime }" pattern="yyyy-MM-dd"/>
    		</td>
    		<td>
    			<a href="javascript:;" class="btn btn-mini btn-primary btn-del" data-id="${r.id }">删除</a>
    		</td>
    	</tr>
    </c:forEach>
</table>

<script type="text/javascript">
	$(function(){
		$(".pagination").html("");
		$(".pagination").pagination('${resPage.rowCount }', {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : ('${resPage.pageNo - 1 }'),
			link_to : "javascript:;",
			num_display_entries : 5,
			items_per_page : '${resPage.pageSize }',
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				selClassResource(pageNo);
			}
		});
		
		$(".btn-del").click(function(){
			var resid = $(this).attr("data-id");
			$.confirm("确定要删除吗？",function(b){
				if(b){
					$.ajax({
						url:rootPath + "/classTypeResource/delres",
						type:"post",
						data:{"id":resid},
						dataType:"json",
			  			beforeSend:function(XMLHttpRequest){
			  	              $(".loading").show();
			  	              $(".loading-bg").show();
			  	         },
						success:function(data){
							if(data.msg == "success"){
								selClassResource(1);
							}else{
								$.msg(data.msg,1000);
							}
			  	            $(".loading").hide();
			  	            $(".loading-bg").hide();
						}
					});
				}
			});
		});
	});
</script>
<style>
	table.tt td{text-align: center}
</style>