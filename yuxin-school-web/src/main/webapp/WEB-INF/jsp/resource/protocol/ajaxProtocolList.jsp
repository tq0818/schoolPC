<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String rootPath=request.getContextPath(); %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<div class="r-list L-r-list">
<div class="r-list L-r-list-table" id="teacherContent" style="min-height: 350px;position: relative;">
<table class="table table-hover table-center table-list L-table">
        <col width="25%">
        <col width="25%">
        <col width="10%">
        <col width="5%">
        <col width="10%">
        <col width="10%">
        <col width="15%">
        <tr>
            <th>协议名称</th>
            <th>协议标题</th>
            <th>类型</th>
            <th>状态</th>
            <th>创建时间</th>
            <th>创建人</th>
            <th>操作</th>
        </tr>
	<c:forEach items="${pageFinder.data }" var="user">
		<tr>
		    <td>${user.name }</td>
		    <td>${user.title }</td>
		    <c:choose>
		    <c:when test="${user.type eq 0 }">
		    <td>课程协议</td>
		    </c:when>
		    <c:otherwise>
		    <td>课程包协议</td>
		    </c:otherwise>
		    </c:choose>
		    <td class="status" id="status${user.id }">${user.status==1?'启用':'停用' }</td>
		    <td>${user.createTimeStr }</td>
		    <td>${user.createUserName }</td>
		    <td class="spe" style="position:relative;">
	        		<span class="special spe-l changeStatus" marks="${user.id }">${user.status==1?'停用':'启用' }</span>
	        		<span class="special editProtocol" marks="${user.id }">编辑</span>
	        		<span class="special moreOperation" marks="${user.id }" style="border-right:none;">更多</span>
	        		<div class="table-bot table-bot-2 sss" style="">
                                        <div class="table-bot-up " ><a class="protocolDetail" protocolId="${user.id }" href="javascript:void(0);">协议详情</a></div>
                                        <div class="table-bot-cen"></div>
                                        <div class="table-bot-down" ><a class="protocolCourse" protocolId="${user.id }" type="${user.type }" href="javascript:void(0);">${user.type==0?'绑定课程':'绑定课程包' }</a></div>
                                    </div>
		    </td>
		</tr>
	</c:forEach>
	<c:if test="${empty pageFinder.data }">
		<tr>
			<td colspan="7">暂无数据</td>
		</tr>
	</c:if>
</table>
</div>
</div>
 <div class="pages">
	<ul class="pagination"></ul>
 </div>
 
 
<script type="text/javascript">
  $(document).ready(function(){
	  $(".pagination").pagination('${pageFinder.rowCount}', {
	    	 next_text : "下一页",
	    	 prev_text : "上一页",
	    	 current_page :'${pageFinder.pageNo-1}',
	    	 link_to : "javascript:void(0)",
	    	 num_display_entries : 8,
	    	 items_per_page : '${pageFinder.pageSize}',
	    	 num_edge_entries : 1,
	    	 callback:function(page,jq){
		    	 var pageNo = page + 1;
		    	 Form.searchProtocol(pageNo);
		    	 
	    	 }
	   });
  });
</script>