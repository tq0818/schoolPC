<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<c:choose>
	<c:when  test="${not empty resPage.data }">
		<c:forEach items="${resPage.data }" var="m" varStatus="status">
			<tr>
			    <td>${(resPage.pageNo-1)*resPage.pageSize+status.index+1 }</td>
			    <td>${m.name }</td>
			    <td>${m.itemThirdName }</td>
			    <td>${m.schoolName }</td>
			    <td>
			    	<a  target="_blank" href="http://${m.domain}/sysConfigItem/selectDetail/${m.id }">点击查看</a>
			    </td>
			    <td><button class="btn btn-primary" data_id="${m.id }" onclick="addClassManagement(this);">添加</button></td>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
         	<td colspan="6">暂无课程数据</td>
        </tr>
	</c:otherwise>
</c:choose>
<input type="hidden" id="sRowCount" value="${resPage.rowCount }"/>
<input type="hidden" id="sPageNo" value="${resPage.pageNo -1}"/>
<input type="hidden" id="sPageSize" value="${resPage.pageSize}"/>
<script type="text/javascript">
	/* $(function(){
		$(".classpagination").html("");
		$(".classpagination").pagination("${resPage.rowCount }", {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : ("${resPage.pageNo -1}"),
			link_to : "javascript:;",
			num_display_entries : 5,
			items_per_page : "${resPage.pageSize }",
			num_edge_entries : 1,
			callback : function(page, jq) {
				var pageNo = page + 1;
				classListOfOtherSchool(pageNo);
			}
		});
	}); */
	
</script>