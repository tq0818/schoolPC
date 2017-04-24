<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<colgroup>
<col width="20%">
<col width="20%">
<col width="20%">
<col width="20%">
<col width="20%">
</colgroup>
<c:forEach items="${pageFinder.data }" var="payMaster">
<tr>
    <td rowspan="2">
        <span class="t-title">${payMaster.classTypeName }</span>
    </td>
    <td>
        <span class="c-title">学员</span>
        <span class="c-content">${payMaster.student.name }</span>
    </td>
    <td>
        <span class="c-title">价格</span>
        <span class="c-content">${payMaster.totalAmount }</span>
    </td>
    <td>
        <span class="c-title">购买时间</span>
        <span class="c-content">
        <fmt:formatDate value="${payMaster.applyTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
        </span>
    </td>
    <td rowspan="2">
        <p class="text-center">
            <a href="javascript:void(0);" onclick="detail(${payMaster.id})" class="btn btn-default">详情</a>
            <a href="javascript:void(0);" onclick="arrange(${payMaster.id})" class="btn btn-default">课程安排</a>
        </p>
    </td>
</tr>
<tr>
    <td>
        <span class="c-title">手机号</span>
        <span class="c-content">${payMaster.student.mobile }</span>
    </td>
    <td>
        <span class="c-title">邮箱</span>
        <span class="c-content">${payMaster.student.email }</span>
    </td>
    <td>
        <span class="c-title"></span>
        <span class="c-content"></span>
    </td>
   
</tr>
</c:forEach>

<script>
$("#page").pagination('${pageFinder.rowCount}', {
	next_text : "下一页",
	prev_text : "上一页",
	link_to : "javascript:void(0)",
	current_page : '${pageFinder.pageNo}' - 1,
	num_display_entries : 8,
	items_per_page : '${pageFinder.pageSize}',
	num_edge_entries : 1,
	callback:function(page,jq){
		var pageNo = page + 1;
		search(pageNo);
	}
});
function detail(id){
	$("#pmId").val(id);
	//alert($("#payMasterId").val());
	$("#detailForm")[0].submit();
}
function arrange(id){
	$("#pmId").val(id);
	$("#detailForm").attr("action","<%=rootPath%>/studentPayMaster/toArrangeCourse")
	$("#detailForm")[0].submit();
}
</script>